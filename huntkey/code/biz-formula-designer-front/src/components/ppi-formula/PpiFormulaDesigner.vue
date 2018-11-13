<!--
  PPI公式.公式编辑器
-->
<template>
  <div class="formula-designer"
       v-loading="pageConfig.isLoading"
       :element-loading-text="pageConfig.loadingText">
    <!-- 页面内容 -->
    <el-row :gutter="10" class="main-oper-area common-vgap">
      <!-- 左侧面板 -->
      <el-col :span="4" :offset="2">
        <ppi-vars-list
          :postData="postData"
          @selectVar="selectVar">
        </ppi-vars-list>
      </el-col>

      <!-- 公式编辑区 -->
      <el-col :span="12" style=" border-style:none;">
        <div class="formula-label-div">
          <div class="formula-cycle">周期</div>
          <div class="formula-radio">
            <el-radio-group v-model="store.cycle">
              <el-radio label="A">全部</el-radio>
              <el-radio label="Y">年</el-radio>
              <el-radio label="S">季</el-radio>
              <el-radio label="M">月</el-radio>
              <el-radio label="W">周</el-radio>
              <el-radio label="D">日</el-radio>
            </el-radio-group>
          </div>
          <div class="formula-label">{{store.ppiName}}=</div>

          <div class="formula-editor-oper-div float-right">
            <el-button @click="checkPPIFormulaStrValid">检查公式合法性</el-button>
            <!--<el-button @click="showFormulaValidatorDlg">校验公式</el-button>-->
            <el-button type="primary" @click="updateFormulaInfo">保存</el-button>
            <!--<el-button @click="clearFormulaInfo">清空</el-button>-->
          </div>
        </div>

        <formula-editor ref="editor"
                        :store="store"
                        @searchTextChange="suggestSearchTextChange"
                        @selectSuggest="selectSuggest">
        </formula-editor>
      </el-col>

      <el-col :span="4">
        <func-search
          :store="store"
          @searchTextChange="funcSearchTextChange"
          @selectSuggest="selectSuggest">
        </func-search>
      </el-col>
    </el-row>
  </div>
</template>
<script>
  import { APP_TYPES } from '@/types'
  import { mapGetters } from 'vuex'
  import appMixin from '@/mixin/app-mixin'
  import designerMixin from '@/mixin/designer-mixin'
  import { formulaService } from '@/api'
  import PpiVarsList from './PpiVarsList'
  import FormulaEditor from '@/components/formula-designer/formula-editor/FormulaEditor'
  import FuncSearch from './func-search/FuncSearch'
  export default {
    name: 'ppiFormulaDesigner',
    mixins: [appMixin, designerMixin],
    data () {
      return {
        pageConfig: {
          isLoading: true,
          loadingText: '正在加载PPI公式设计器相关资源'
        },
        store: {
          ppiId: '',
          ppiName: '',
          cycle: '',
          userId: '',
          formula: {
            formulaId: '',
            label: '',
            returnType: '',
            formulaStr: '', // 公式字符串, 后台校验时用
            formulaText: '', // 公式字符串, 其他业务系统展示用
            formulaItems: []
          },
          edmClasses: [],
          relEdmClasses: [],
          curEdmClassProps: [],
          sysVars: [],
          localVars: [],
          suggestResult: [],
          searchResult: [],
          currentSelectedClass: '', // 当前选择的类, edmcId 或 clssId, 该信息会带到子组件里,用于选中类的高亮
          freqUsedFuncs: [], // frequently used function
          allFuncs: [],
          initFuncs: [] // 原始未处理的函数列表. 用于公式校验
        },
        postData: []
      }
    },
    created () {
      this.onPostData()
      this.store.userId = this.currentUserId
      if (this.$route.name === 'ppiFormulaDesigner') {
        this.store.ppiId = this.$route.params.ppiId
        this.store.ppiName = this.$route.params.ppiName
        this.store.cycle = this.$route.params.cycle
        this.init()
      } else {
        this.pageConfig.loadingText = '初始化PPI公式设计器时, 入口参数错误'
      }
    },
    mounted () {
    },
    methods: {
      test () {
        this.$refs.editor.test()
      },
      init () {
        this.initOtherVarInfo().then(() => {
          this.pageConfig.isLoading = false
        })
      },
      onPostData () {
        window.onmessage = (message) => {
          console.log('message', message)
          // 获取PPI公式变量
          this.postData = message.data.message
          // 获取公式内容
          this.store.formula.formulaText = message.data.frmuFormulaText
          this.store.formula.formulaItems = this.parseFormulaItems(message.data.frmuFormulaStyle)
        }
      },
      initOtherVarInfo () {
        let userId = this.store.userId
        return formulaService.queryFuncsInfo({userId}).then((resData) => {
          // 初始化常用函数
          this.initFreqUsedFuncs(resData.commonlyFormulasList)
          // 初始化所有函数
          this.initAllFuncsInfo(resData.allFuncList)
        }).catch((err) => {
          console.log(err.message)
          this.pageConfig.loadingText = '加载PPI公式初始化数据时发生网络错误, 请稍后重试'
        })
      },
      updateFormulaInfo () {
        this.$refs.editor.isPPIFormulaStrValid().then((formulaInfo) => {
          this.store.formula.formulaStr = formulaInfo.frmuFormulaContent
          this.store.formula.formulaText = formulaInfo.frmuFormulaText
          this.store.formula.formulaItems = this.parseFormulaItems(formulaInfo.frmuFormulaStyle)
          let obj = {
            eventType: 'PPIFormula',
            operType: 'save',
            eventData: {
              formula: this.store,
              formulaInfo: formulaInfo
            }
          }
          this.postMsg(obj)
        }).catch((error) => {
          this.AppUtils.showErrorMsg(error)
        })
      },
      // 清空PPI公式
      clearFormulaInfo  () {
        this.$confirm('是否清空该PPI公式？', '提示', {
          confirmButtonText: '确定',
          canceButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$refs.editor.clearPPIFormulaInfo()
        }).catch(() => {})
      },
      postMsg (obj) {
        console.info(obj)
        if (window !== window.parent) {
          window.parent.postMessage(obj, '*')
        }
        console.info(window.opener)
        if (window.opener) {
          window.opener.postMessage(obj, '*')
          window.opener = null
          window.open(' ', '_self')
          window.close()
        }
      }
    },
    computed: {
      ...mapGetters({
        currentUserId: APP_TYPES.APP_GET_CURRENT_USER_ID
      })
    },
    components: {
      PpiVarsList,
      FuncSearch,
      FormulaEditor
    }
  }
</script>
<style scoped>
  .formula-designer {
    min-width: 1200px;
  }

  .formula-designer .main-oper-area {
    min-height: 600px;
    height: calc(100% - 24px);
  }

  .formula-designer .main-oper-area > .el-col {
    min-height: 600px;
    height: 100%;
  }

  .edm-classes-list {
    height: 100%;
    min-height: 300px;
  }

  .edm-props-list {
    height: 100%;
    min-height: 600px;
  }

  .formula-editor-comp {
    height: calc(100% - 35px);
    min-height: 565px;
  }

  /** .formula-label-div begin **/
  .formula-label-div {
    /*display: block;*/
    height: 35px;
    /*border:1px solid red;*/
  }
  .formula-label {
    display: inline-block;
    padding-top: 6px;
    padding-left: 30px;
    font-weight: bold;
  }
  .formula-cycle{
    display: inline-block;
    font-weight: bold;
    padding-top: 6px;
  }
  .formula-radio{
    display: inline-block;
    padding-top: 6px;
    padding-left: 10px;
    font-weight: bold;
  }
  .formula-return-type {
    display: inline-block;
    padding-top:6px;
    padding-left: 25px;
    padding-bottom: 6px;
    font-weight: bold;
  }
  .formula-editor-oper-div {
    margin-top:5px;
  }
  /** .formula-label-div end **/
  .ppi-vars-list {
    height: 100% ;/*calc(60% - 1px);*/
    min-height: 300px; /* 350 - 顶部32 bottom 2*/
  }
  .sys-vars-list {
    height: 50% ;/*calc(60% - 1px);*/
    min-height: 300px; /* 350 - 顶部32 bottom 2*/
  }
  .local-vars-list {
    height: 50%;
    min-height: 300px;
  }
  .common-search {
    height: 100%;
  }
  .var-tab {
    height: 100%;
  }
</style>
