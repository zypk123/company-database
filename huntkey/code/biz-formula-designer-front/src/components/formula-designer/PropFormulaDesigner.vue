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
    <!-- 页面内容 -->
    <el-row :gutter="10" class="main-oper-area common-vgap">
      <!-- 左侧面板 (直接关联类, 相关类) -->
      <el-col :span="4">
          <edm-classes-list
            :store="store"
            @selectClass="selectEdmClass">
          </edm-classes-list>

          <rel-edm-classes-list
            :store="store"
            @selectClass="selectRelClass"
            @refreshMe="reloadRelEdmClasses"
          >
          </rel-edm-classes-list>
      </el-col>

      <!-- 左侧面板 (属性) -->
      <el-col :span="4">
        <edm-props-list
          :store="store"
          @selectProp="selectProp"
        >
        </edm-props-list>
      </el-col><!-- /.el-col-4-->

      <!-- 公式编辑区 -->
      <el-col :span="12" style=" border-style:none;">
        <div class="formula-label-div">
          <div class="formula-return-type">{{formatDataType(store.formula.returnType)}}</div>
          <div class="formula-label">{{store.formula.label}}=</div>

          <div class="formula-editor-oper-div float-right">
            <!-- <el-button type="primary" @click="test">test</el-button> -->
            <el-button @click="checkFormulaStrValid">检查公式合法性</el-button>
            <el-button @click="showFormulaValidatorDlg">校验公式</el-button>
            <el-button type="primary" @click="updateFormulaInfo">保存</el-button>
            <el-button type="danger" @click="delFormulaInfo">删除</el-button>
          </div>

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
              :store="store"
              @selectVar="selectVar">
            </sys-vars-list>

            <local-vars-list
                @refreshMe="reloadLoacalVars"
                :store="store"
                @selectVar="selectVar">
            </local-vars-list>
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

    </el-row>
  </div><!-- /.formula-designer-->
</template>
<script>
import { APP_TYPES, EDM_DATA_TYPES } from '@/types'
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
  name: 'propFormulaDesigner',
  mixins: [appMixin, designerMixin],
  data () {
    return {
      pageConfig: {
        mode: '', // new/edit
        isLoading: true,
        loadingText: '正在加载公式设计器相关资源',
        activeRightTab: 'varTab'
      }, // end of pageConfig
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
    } // end of return
  }, // end of data
  created () {
    this.store.userId = this.currentUserId // fetched from store
    if (this.$route.name === 'propFormulaDesignerNew') {
      this.pageConfig.mode = 'new'
      this.store.classId = this.$route.params.classId
      this.store.propId = this.$route.params.propId
      this.init()
    } else if (this.$route.name === 'propFormulaDesignerEdit') {
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
  }, // end of mounted
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
    queryCasPropNameByClassId () {
      return this.getPropsbyClassId2(this.store.classId).then((resData) => {
        // console.log('resData', resData)
        this.changeTreeToArray(resData)
        // console.log('tempArray', this.tempArray)
        for (let i = 0; i < this.tempArray.length; i++) {
          if (this.store.propId === this.tempArray[i].id) {
            this.casPropName = this.tempArray[i].casEdmpName
          }
        }
      })
    },
    queryClassNameByClassId () {
      // 通过类Id查类名称
      return formulaService.getClassInfoByClassId(this.store.classId).then((res1) => {
        // console.log('res1', res1)
        this.className = res1.edmcName
      }).catch((error) => {
        this.AppUtils.showWarning('查询类信息失败' + error)
      })
    },
    init () {
      // 初始化公式设计器页面
      // 1. 初始化变量信息
      this.initVarInfo().then(() => {
        return this.initEmdClassesInfo()
      }).then(() => {
        return this.initOtherVarInfo()
      }).then(() => {
        this.pageConfig.isLoading = false
      })
    }, // end of init
    initVarInfo () {
      // console.info('initVarInfo')
      let varId = this.store.varId || ''
      // console.log('varId', varId)
      let propId = this.store.propId || ''
      return formulaService.initFormulaInfo({varId, propId}).then((resData) => {
        console.info('initVarInfo', resData)
        this.store.varId = resData.varId
        // this.store.formula.label = resData.varName || '这里修改完接口就好了'
        this.store.formula.returnType = resData.varType || 'unknow'
      }).then(() => {
        return this.queryClassNameByClassId()
      }).then(() => {
        return this.queryCasPropNameByClassId()
      }).then(() => {
        // console.log('className', this.className)
        // console.log('casPropName', this.casPropName)
        this.store.formula.label = this.className + '.' + this.casPropName
      }).catch((err) => {
        this.AppUtils.showWarning(err.message)
      })
      // return new Promise((resolve, reject) => {
      //   if (this.pageConfig.mode === 'new') {

      //   } else if (this.pageConfig.mode === 'edit') {

      //   } else {
      //     this.pageConfig.loadingText = '初始化属性公式设计器时, 模式不正确'
      //     reject()
      //   }
      // })// end of new Promise
    }, // end of initVarInfo
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
        this.pageConfig.loadingText = '加载属性的直接关联类时发生网络错误, 请稍后重试.'
      })
    }, // end of initEmdClassesInfo
    initOtherVarInfo () {
      let varId = this.store.varId
      let userId = this.store.userId
      // console.log(varId, userId)
      return formulaService.queryVarInfo4InitFormulaEditorByVarID({varId, userId}).then((resData) => {
        console.info('初始化相关类', resData)
        // 初始化相关类
        this.store.relEdmClasses = resData.tfdClassRelateds
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
      }).catch((err) => {
        console.log(err.message)
        this.pageConfig.loadingText = '加载公式初始化数据时发生网络错误, 请稍后重试.'
      })
    },
    reloadRelEdmClasses () {
      let formulaId = this.store.formula.formulaId
      return formulaService.queryRelEdmClassesByFormulaID({formulaId}).then((resData) => {
        // console.info('reloadRelEdmClasses')
        // console.info(resData)
        // // 初始化相关类
        this.store.relEdmClasses = resData
      }).catch(() => {
        this.pageConfig.loadingText = '加载公式初始化数据时发生网络错误, 请稍后重试.'
      })
    },
    reloadLoacalVars () {
      // console.info('initLoacalVars')
      let formulaId = this.store.formula.formulaId
      return formulaService.queryLoacalVars({formulaId}).then((resData) => {
        // console.info(resData)
        this.store.localVars = resData
      }).catch(() => {
        this.pageConfig.loadingText = '加载公式初始化数据时发生网络错误, 请稍后重试.'
      })
    },
    // 选中EdmClass
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
      this.$refs.editor.isFormulaStrValid().then((formulaInfo) => {
        console.info('保存', formulaInfo)
        return formulaService.updateFormulaInfo(formulaInfo).then(() => {
          this.store.formula.formulaStr = formulaInfo.frmuFormulaContent
          this.store.formula.formulaText = formulaInfo.frmuFormulaText
          this.store.formula.formulaItems = this.parseFormulaItems(formulaInfo.frmuFormulaStyle)
          let obj = {
            eventType: 'PropFormula',
            operType: 'save',
            eventData: {
              varId: this.store.varId,
              formula: this.store.formula
            }
          }
          this.postMsg(obj)
          this.AppUtils.showSuccess('公式信息已保存.')
        })
      }).catch((error) => {
        console.log(error)
        this.AppUtils.showErrorMsg(error)
      })
    },
    delFormulaInfo  () {
      this.$confirm('是否删除该属性公式？', '提示', {
        confirmButtonText: '确定',
        canceButtonText: '取消',
        type: 'warning'
      }).then(() => {
        let varId = this.store.varId
        formulaService.delFormulaInfo({varId}).then(() => {
          let obj = {
            eventType: 'PropFormula',
            operType: 'delete',
            eventData: {
              varId: this.store.varId,
              formula: this.store.formula
            }
          }
          this.postMsg(obj)
          this.AppUtils.showSuccess('公式信息已删除.')
        }).catch((error) => {
          console.log(error)
          this.AppUtils.showErrorMsg(error)
        })
      }).catch(() => {})
    },
    postMsg (obj) {
      // console.info('postMsg')
      // console.info(obj)
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
    } // end of postMsg

  },
  computed: {
    ...mapGetters({
      currentUserId: APP_TYPES.APP_GET_CURRENT_USER_ID
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
  height: 50%;
  min-height: 300px;
}

.rel-edm-classes-list {
  height: 50%;
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
