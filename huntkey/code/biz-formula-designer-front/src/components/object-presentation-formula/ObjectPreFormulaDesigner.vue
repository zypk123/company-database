<!--
  属性公式.公式编辑器
  入口方法: 从EDM中, 以iframe的方式打开当前页面
  入参: 类ID, 属性ID
  FIXME initVarInfo 初始化逻辑 2017-07-26
-->
<template>
  <div class="formula-designer"
       v-loading="pageConfig.isLoading"
       :element-loading-text="pageConfig.loadingText">
    <el-row :gutter="10" class="main-oper-area common-vgap">
      <el-col :span="4">
        <edm-classes-list
          :store="store"
          @selectClass="selectEdmClass">
        </edm-classes-list>
      </el-col>

      <el-col :span="4">
        <edm-props-list
          :store="store"
          @selectProp="selectProp"
        >
        </edm-props-list>
      </el-col>

      <!-- 公式编辑区 -->
      <el-col :span="12" style=" border-style:none;">
        <div class="formula-label-div">

          <div class="formula-editor-oper-div float-right">
            <el-button @click="checkObjectPreFormulaStrValid">检查公式合法性</el-button>
            <el-button type="primary" @click="updateFormulaInfo">保存</el-button>
            <!--<el-button type="danger" @click="delFormulaInfo">删除</el-button>-->
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
  import { APP_TYPES, EDM_DATA_TYPES } from '@/types'
  import { mapActions, mapGetters } from 'vuex'
  import appMixin from '@/mixin/app-mixin'
  import designerMixin from '@/mixin/designer-mixin'
  import { formulaService } from '@/api'
  import EdmClassesList from '@/components/formula-designer/edm-classes/EdmClassesList'
  import EdmPropsList from '@/components/formula-designer/edm-props/EdmPropsList'
  import FormulaEditor from '@/components/formula-designer/formula-editor/FormulaEditor'
  import FuncSearch from '@/components/ppi-formula/func-search/FuncSearch'
  export default {
    name: 'objectPreFormulaDesigner',
    mixins: [appMixin, designerMixin],
    data () {
      return {
        pageConfig: {
          mode: '',
          isLoading: true,
          loadingText: '正在加载公式设计器相关资源',
          activeRightTab: 'searchTab'
        },
        store: {
          userId: '',
          varId: '',
          classId: '',
          propId: '',
          formula: {
            formulaId: '',
            label: '',
            returnType: '',
            formulaStr: '', // 公式字符串, 后台校验时用
            formulaText: '', // 公式字符串, 其他业务系统展示用
            formulaItems: []
          },
          loadingInfo: {
            isLoadingProp: false
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
        tempArray: [],
        className: '',
        casPropName: '',
        linkClassArray: []
      }
    },
    created () {
      this.onPostData()
      this.store.userId = this.currentUserId
      if (this.$route.name === 'objectPreFormulaDesigner') {
        this.pageConfig.mode = 'new'
        this.store.classId = this.$route.params.classId
        this.init()
      } else if (this.$route.name === 'objectPreFormulaDesignerEdit') {
        this.pageConfig.mode = 'edit'
        this.store.classId = this.$route.params.classId
        this.store.propId = this.$route.params.propId
        this.store.varId = this.$route.params.varId
        this.init()
      } else {
        this.pageConfig.loadingText = '初始化属性公式设计器时, 入口参数错误.'
      }
    },
    mounted () {
    },
    methods: {
      ...mapActions({
        fetchEdmps: EDM_DATA_TYPES.ACTION_FETCH_PROPS
      }),
      test () {
        this.$refs.editor.test()
      },
      /**
       * 通过类id查询属性
       */
      getPropsbyClassId2 (classId) {
        return new Promise((resolve, reject) => {
          formulaService.queryEdmClassPropsByClassID({edmcId: classId}).then((resData) => {
            resolve(resData)
          }).catch((error) => {
            this.AppUtils.showWarning(error.message)
          })
        })
      },
      changeTreeToArray (nodes) {
        nodes.forEach(
          (item, index, arr) => {
            this.tempArray.push(item)
            if (item.children && item.children.length > 0) {
              this.changeTreeToArray(item.children)
            }
          }
        )
      },
      init () {
        this.initEmdClassesInfo().then(() => {
          return this.initOtherVarInfo()
        }).then(() => {
          this.pageConfig.isLoading = false
        }).catch((err) => {
          console.log(err.message)
        })
      },
      onPostData () {
        window.onmessage = (message) => {
          console.log('message', message)
          // 获取对象呈现公式
          // this.store.formula.formulaId = resData.tfdFormula.frmuId
          this.store.formula.formulaStr = message.tfdFormula.frmuFormulaContent
          this.store.formula.formulaItems = this.parseFormulaItems(message.data.frmuFormulaStyle)
          this.store.formula.formulaText = message.data.frmuFormulaText
        }
      },
      initEmdClassesInfo () {
        return formulaService.getClassInfoByClassId(this.store.classId).then((resData) => {
          console.log('关联类', resData)
          this.linkClassArray.push(resData)
          console.log(this.linkClassArray)
          let data = this.Utils.cloneDeep(this.linkClassArray)
          if (data.length > 0) {
            data.forEach((obj, index, arr) => {
              if (index === 0) {
                obj.aliasName = 'this'
                this.pageConfig.initClassName = obj.edmcName
              } else {
                obj.aliasName = obj.edmcName
              }
            })
            this.selectEdmClass(data[0])
            this.store.edmClasses = data
          }
        }).catch((err) => {
          console.log(err.message)
          this.pageConfig.loadingText = '加载关联类时发生网络错误, 请稍后重试'
        })
      },
      initOtherVarInfo () {
        let userId = this.store.userId
        return formulaService.queryFuncsInfo({userId}).then((resData) => {
          console.info('resData', resData)
          // 初始化常用函数
          this.initFreqUsedFuncs(resData.commonlyFormulasList)
          // 初始化所有函数
          this.initAllFuncsInfo(resData.allFuncList)
        }).catch((err) => {
          console.log(err.message)
          this.AppUtils.showErrorMsg(err.message)
          this.pageConfig.loadingText = '加载对象呈现公式初始化数据时发生网络错误, 请稍后重试'
        })
      },
      selectEdmClass (edmClass) {
        if (this.store.currentSelectedClass === edmClass.id) {
          return
        }
        this.store.currentSelectedClass = edmClass.id
        if (edmClass.id === this.store.classId) {
          this.store.currentSelectedClassName = 'this'
        } else {
          this.store.currentSelectedClassName = edmClass.edmcName
        }
        this.getPropsbyClassId(edmClass.id)
      },
      updateFormulaInfo () {
        this.$refs.editor.isObjectPreFormulaStrValid().then((formulaInfo) => {
          console.info('保存', formulaInfo)
          this.store.formula.formulaStr = formulaInfo.frmuFormulaContent
          this.store.formula.formulaText = formulaInfo.frmuFormulaText
          this.store.formula.formulaItems = this.parseFormulaItems(formulaInfo.frmuFormulaStyle)
          let obj = {
            eventType: 'objectPreFormula',
            operType: 'save',
            eventData: {
              store: this.store,
              formulaInfo: formulaInfo
            }
          }
          this.postMsg(obj)
        }).catch((error) => {
          console.log(error)
          this.AppUtils.showErrorMsg(error)
        })
      },
      delFormulaInfo  () {
        this.$confirm('是否删除该对象呈现公式？', '提示', {
          confirmButtonText: '确定',
          canceButtonText: '取消',
          type: 'warning'
        }).then(() => {
          let obj = {
            eventType: 'objectPreFormula',
            operType: 'delete',
            eventData: {
              store: this.store
            }
          }
          this.postMsg(obj)
        }).catch(() => {})
      },
      postMsg (obj) {
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
      EdmClassesList,
      EdmPropsList,
      FormulaEditor,
      FuncSearch
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
    font-weight: bold;
  }
  .formula-return-type {
    display: inline-block;
    padding-top:6px;
    padding-right: 12px;
    padding-left: 12px;
    padding-bottom: 6px;
    font-weight: bold;
  }
  .formula-editor-oper-div {
    margin-top:5px;
  }
  /** .formula-label-div end **/

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
