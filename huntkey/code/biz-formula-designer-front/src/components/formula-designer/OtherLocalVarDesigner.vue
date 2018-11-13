<template>
<!--
  其他局部变量编辑器
  区别于属性公式中的局部变量编辑, 没有parentStore, 需要当前组件重新初始化一相关元素
  但是
    1. 没有直接关联类.
    2. 相关类的列表从属性公式中传过来?
    3. 局部变量中不在使用其他的局部变量
-->
  <div class="formula-designer local-var-designer"
      v-loading="pageConfig.isLoading"
      :element-loading-text="pageConfig.loadingText">
      <!-- 页面内容 -->

      <el-row :gutter="5" class="main-oper-area common-vgap">
        <el-col :span="4">
          <rel-edm-classes-list
            :store="store"
            @selectClass="selectRelClass"
            @refreshMe="reloadRelEdmClasses"
          >
          </rel-edm-classes-list>
        </el-col><!-- /.el-col-4-->

        <el-col :span="4">
          <edm-props-list
            :store="store"
            @selectProp="selectProp"
          >
          </edm-props-list>
        </el-col><!-- /.el-col-4-->

        <el-col :span="12" style=" border-style:none;">
          <div class="formula-label-div error-on-top">
            <el-form :model="store.formula" :inline="true" :rules="varInfoFormRules" ref="varInfoForm">

              <el-form-item prop="returnType">
              <el-select v-model="store.formula.returnType" placeholder="类型"
                class="w100">
                <el-option label="变量类型" value=""></el-option>
                <el-option v-for="dataType in dataTypeDict"
                  :label="dataType.label"
                  :value="dataType.val"
                  :key="dataType.val" >
                </el-option>
              </el-select>
              </el-form-item>

              <el-form-item prop="label" >
                <el-input v-model="store.formula.label" title="字母开头,英文和数字,不能包含空格" placeholder="变量名称"></el-input>
              </el-form-item>

            <div class="formula-editor-oper-div float-right">
              <!-- <el-button type="primary" @click="test">test</el-button> -->
              <el-button @click="checkFormulaStrValid">检查公式合法性</el-button>
              <el-button @click="showFormulaValidatorDlg">校验公式</el-button>
              <el-button type="primary" @click="updateFormulaInfo">保存</el-button>
            </div>
            </el-form>
          </div><!-- /.formula-label-div -->

          <formula-editor ref="editor"
              :store="store"
              @searchTextChange="suggestSearchTextChange"
              @selectSuggest="selectSuggest">
          </formula-editor>
        </el-col><!-- /.el-col-4-->

        <el-col :span="4">
          <el-tabs type="card" class="var-tab h100" v-model="pageConfig.activeRightTab">
            <el-tab-pane label="变量" name="varTab">
              <sys-vars-list
                :store="store" @selectVar="selectVar">
              </sys-vars-list>
            </el-tab-pane>

            <el-tab-pane label="查找" name="varSearchTab">
              <common-search
                :store="store"
                @searchTextChange="commonSearchTextChange"
                @selectSuggest="selectSuggest">
              </common-search>
            </el-tab-pane>
          </el-tabs>
        </el-col><!-- /.el-col-4-->
      </el-row><!-- /.el-row-->

  </div> <!-- /.formula-designer-->
</template>

<script>
import { EDM_DATA_TYPES } from '@/types'
import { mapActions, mapGetters } from 'vuex'
import appMixin from '@/mixin/app-mixin'
import designerMixin from '@/mixin/designer-mixin'
import { formulaService } from '@/api'
import EdmClassesList from './edm-classes/EdmClassesList'
import RelEdmClassesList from './rel-edm-classes/RelEdmClassesList'
import EdmPropsList from './edm-props/EdmPropsList'
import FormulaEditor from './formula-editor/FormulaEditor'
import SysVarsList from './sys-vars/SysVarsList'
import LocalVarsList from './local-vars/LocalVarsList'
import CommonSearch from './common-search/CommonSearch'
export default {
  name: 'formulaDesigner',
  mixins: [appMixin, designerMixin],
  props: ['initData'],
  data () {
    return {
      pageConfig: {
        mode: '', // new/edit
        isLoading: true,
        loadingText: '正在加载公式设计器相关资源',
        activeRightTab: 'varTab'
      },
      store: {
        userId: '', // 用于处理用户统计信息
        varId: '',
        // classId: '',
        // propId: '',
        formula: {
          formulaId: '',
          returnType: '',
          label: '',
          formulaStr: '', // 公式字符串, 后台校验时用
          formulaText: '', // 公式字符串, 其他业务系统展示用
          formulaItems: []
        },
        loadingInfo: {
          isLoadingPorp: false
        },
        // parentVarId: '',
        // varId: '',
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
      varInfoFormRules: {
        returnType: [
          {required: true, message: '请选择返回值类型'}
        ],
        label: [
          {required: true, message: '请输入局部变量名称'},
          {
            validator: (rules, value, callback) => {
              if (!this.Utils.validate(value, 'name_en')) {
                // 名称(字母开头,英文和数字,不能包含空格)
                callback(new Error('局部变量名称格式不正确'))
              }
              callback()
            }
          }
        ]
      }
    }
  },
  created () {
    // new  如果是修改, 看看参数
    this.store.varId = this.initData.localVar.vrntId
    this.store.userId = this.parentStore.userId
    this.store.formula.label = this.initData.localVar.vrntVarName || ''
    this.store.formula.returnType = this.initData.localVar.vrntVarType || ''

    // 初始化时, formulaInfo是初始化是带过来的, 非初始化时, 需要重新取一遍
    if (this.initData.mode === 'new') {
      this.store.formula.formulaId = this.initData.initformula.frmuId
      this.store.formula.formulaStr = this.initData.initformula.frmuFormulaContent
      this.store.formula.formulaItems = this.parseFormulaItems(this.initData.initformula.frmuFormulaStyle)
    }
    // if (this.initData.localVar) {

    // }
    // this.store = this.initData.store
    this.init()
  },
  mounted () {
  },
  methods: {
    ...mapActions({
      fetchEdmps: EDM_DATA_TYPES.ACTION_FETCH_PROPS
    }),
    init () {
      this.initOtherVarInfo().then(() => {
        setTimeout(() => {
          this.pageConfig.isLoading = false
        }, 10)
      })
    },
    initOtherVarInfo () {
      return new Promise((resolve, reject) => {
          let varId = this.store.varId
          let userId = this.store.userId
          formulaService.getVarInfoByVarId(
            {varId, userId},
            (resData) => {
              // console.info(resData)
              // 初始化相关类
              this.store.relEdmClasses = resData.tfdClassRelateds
              if (this.store.relEdmClasses && this.store.relEdmClasses.length > 0) {
                this.selectRelClass(this.store.relEdmClasses[0])
              }
              // 初始化常用函数 @see designer-mixin.js
              this.initFreqUsedFuncs(resData.commonlyFormulasList)
              // 初始化所有函数
              this.initAllFuncsInfo(resData.allFuncList)
              // 初始化全局变量
              this.store.sysVars = resData.tvmSysVariants
              // 初始化局部变量
              this.store.localVars = resData.tvmLocVariants
              // 设置公式内容
              this.store.formula.formulaId = resData.tfdFormula.frmuId
              this.store.formula.formulaStr = resData.tfdFormula.frmuFormulaContent
              this.store.formula.formulaItems = this.parseFormulaItems(resData.tfdFormula.frmuFormulaStyle)
              resolve()
            },
            () => {
              this.pageConfig.loadingText = '加载公式初始化数据时发生网络错误, 请稍后重试.'
              reject()
            }
          ) // end of formulaService.getVarInfoByVarId
      })
    },
    reloadRelEdmClasses () {
      let formulaId = this.initData.parentStore.formula.formulaId
      return formulaService.queryRelEdmClassesByFormulaID(
        {formulaId},
        (resData) => {
          // console.info('reloadRelEdmClasses')
          // console.info(resData)
          // // 初始化相关类
          this.store.relEdmClasses = resData
          this.initData.parentStore.relEdmClasses = resData
        },
        () => {
          this.pageConfig.loadingText = '加载公式初始化数据时发生网络错误, 请稍后重试.'
        }
      )
    },
    updateFormulaInfo () {
      this.checkVarInfoValid().then(() => {
        return this.$refs.editor.isFormulaStrValid()
      }).then((formulaInfo) => {
        return formulaService.updateFormulaInfo(formulaInfo,
          () => { // cb
            this.store.formula.formulaStr = formulaInfo.frmuFormulaContent
            this.store.formula.formulaText = formulaInfo.frmuFormulaText
            this.store.formula.formulaItems = this.parseFormulaItems(formulaInfo.frmuFormulaStyle)
          })
      }).then(() => {
        // console.info('updateVariant')
        let varInfo = {
          vrntId: this.store.varId,
          vrntVarName: this.store.formula.label,
          vrntVarDesc: this.store.formula.label,
          vrntVarType: this.store.formula.returnType,
          vrntState: '1',
          isenable: '1'
        }
        return formulaService.updateVariant(varInfo)
      }).then(() => {
        this.$emit('localVarUpdated')
        this.AppUtils.showSuccess('局部变量信息已保存.')
      }).catch((error) => {
        this.AppUtils.showErrorMsg(error)
      })
    },
    checkVarInfoValid () {
      return new Promise((resolve, reject) => {
        this.$refs.varInfoForm.validate((valid) => {
          if (valid) {
            resolve()
          } else {
            reject()
          }
        })
      }) // end of this.$refs.relClassNameForm.validate((valid)
    }
  },
  computed: {
    ...mapGetters({
      dataTypeDict: 'getDataTypeDict'
    })
  },
  components: {
    EdmClassesList,
    RelEdmClassesList,
    EdmPropsList,
    FormulaEditor,
    SysVarsList,
    LocalVarsList,
    CommonSearch
  }
}
</script>

<style scoped>
/*.dev .el-row{
  border: 1px solid blue;
}
.dev [class*=el-col]{
  border: 1px solid purple;
}*/
.local-var-designer {
  width:1210px;
  height: 630px;
}
.page-content {
  height: 100%
}

.formula-designer .main-oper-area {
  /*border:1px solid blue;*/
  min-height: 600px;
  height: 100%; /*顶部 32+12+12=56px , 底部多留6px 还差40哪里出来的?*/
}

.formula-designer .main-oper-area > .el-col {
  min-height: 600px;
  height: 100%;
}

.rel-edm-classes-list {
  height: 100%;
  min-height: 565px;
}

.edm-props-list {
  height: 100%;
  min-height: 600px;
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

.formula-editor-comp {
  height: calc(100% - 32px);
  min-height: 565px;
}

.sys-vars-list {
  height: 100%;
}

.common-search {
  height: 100%;
}

.var-tab {
  height: 100%;
}

</style>
