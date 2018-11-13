<template>
  <div class="rel-condition-config" v-loading.fullscreen.lock="pageConfig.isLoading" :element-loading-text="pageConfig.loadingText">

    <el-row>
      <el-col class="common-vgap" :span="24">
        <el-button type="primary" @click="checkSave" title="保存条件">保存</el-button>
        <el-button type="danger" @click="delRelCond">删除</el-button>
      </el-col>
    </el-row>

    <el-row>
      <el-row>
        <el-col class="common-vgap" :span="4">
          <span>审核角色设置: <span class="class-name">{{className}}</span></span>
        </el-col>
        <el-col class="common-vgap" :span="20">
          <el-button type="primary" class="float-right" size="small" icon="plus" @click="addAuditCondition">添加</el-button>
        </el-col>
      </el-row>

      <!-- 条件列表数据展示区 -->
      <el-col class="common-vgap" :span="24">
        <el-table
          :data="relConditionConditionsTableData"
          border>
          <el-table-column align='center' width="80" label="序号">
            <template scope="scope">
              <span class="condition-index-span">
                <el-button type="text">{{scope.row.auditSeq}}</el-button>
              </span>
            </template>
          </el-table-column>

          <el-table-column header-align="center" align="center"
                           label="方式"
                           width="150"
                           prop="auditPattern">
          </el-table-column>

          <el-table-column header-align="center" align="center"
                           label="角色人员"
                           width="360"
                           prop="auditRolesStaff"
                           show-overflow-tooltip>
          </el-table-column>

          <el-table-column header-align="center" align="center"
                           label="条件"
                           width="220"
                           prop="auditCondition">
          </el-table-column>

          <el-table-column header-align="center" align="center"
                           label="操作" >
            <template scope="scope">
              <el-button  size="small" @click="editAuditCondition(scope.$index)" type="text">编辑</el-button>
              <el-button  size="small" @click="removeAuditCondition(scope.$index)" type="text">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-col>

      <!--条件公式编辑组件-->
      <el-col class="common-vgap" :span="24">
        <el-form ref="conFormula" :model="conFormulaInfo" :rules="conFormulaRules">
          <el-form-item label="审核角色名称:" prop="formulaName">
            <el-input v-model="conFormulaInfo.formulaName"></el-input>
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>

  </div>
</template>
<script>
  import { mapGetters } from 'vuex'
  import { relConditionService, auditService, formulaService } from '@/api'
  import AuditConditionConfigEdit from './AuditConditionConfigEdit'

  export default {
    name: 'auditConditionConfig',
    data () {
      return {
        pageConfig: {
          isLoading: false,
          mode: '',
          loadingText: '拼命加载中......'
        },
        postData: {},
        // 输入参数
        propId1: '',
        classId: '',
        className: '',
        prop1Code: '',
        class1NameEn: '',
        clssName1: '',
        clssPropName1: '',
        alternateField4: '', // 公式Id
        loading: false,
        cndrPropOriginCode: '',
        cndrPropOriginCodeRight: '',

        lastSelectedRange: null,
        relConditionConditionsTableData: [],       //  条件列表数据展示区
        deletedRelConditionConditionsTableData: [],  //  条件列表数据删除提交区（不包含添加后删除）
        postOffices: [],
        assignDepartments: [],
        appointedPosts: [],
        appointedDepartments: [],
        assignPosts: [],
        methodTypeFlagDict: [],
        conFormulaInfo: {
          formulaName: ''
        },
        conFormulaRules: {
          formulaName: [
           {required: true, message: '审核角色名称不能为空', trigger: 'blur'}
          ]
        }
      }
    },
    // 页面初始化
    created () {
      if (this.$route.name === 'auditConditionConfig') {
        this.pageConfig.isLoading = true
        this.pageConfig.mode = 'new'
        this.classId = this.$route.params.classId
        console.log(this.classId + '   ' + this.alternateField4)
        this.init()
      } else if (this.$route.name === 'auditConditionConfigEdit') {
        this.pageConfig.isLoading = true
        this.pageConfig.mode = 'edit'
        this.classId = this.$route.params.classId
        this.alternateField4 = this.$route.params.alternateField4
        console.log(this.classId + '   ' + this.alternateField4)
        this.init()
      } else {
        this.AppUtils.showError('审核角色设置打开方式不正确')
      }
    },
    methods: {
      init () {
        // 类Id接口
        this.queryClassInfoById()
        this.queryRelConditionAndConditions()
        this.queryMethodList()
      },
      queryClassInfoById () {
        // 通过类Id查类的基本信息
        formulaService.getClassInfoByClassId(this.classId).then((res1) => {
          console.log('res1', res1)
          this.className = res1.edmcName
        }).catch((error) => {
          this.AppUtils.showWarning('查询类信息失败' + error)
        })
      },
      /**
       * 页面初始化查询方式列表
       */
      queryMethodList () {
        auditService.getMethodList(this.classId).then((resData) => {
          console.log('res', resData)
          this.methodTypeFlagDict = resData
        }).catch((err) => {
          this.AppUtils.showWarning(err.message)
        })
      },
      /**
       * 页面初始化加载条件列表
       */
      queryRelConditionAndConditions () {
        console.log('initConditions')
        if (this.alternateField4) {
          relConditionService.initAuditCond(this.alternateField4).then((res) => {
            console.log('initAuditCond', res)
            let conditions = res
            if (conditions.length > 0) {
              //  初始化条件列表
              conditions.sort(function (a, b) {
                return a.auditSeq > b.auditSeq
              })
              this.relConditionConditionsTableData = conditions
              this.conFormulaInfo.formulaName = this.relConditionConditionsTableData[0].alternateField5
              this.alternateField4 = this.relConditionConditionsTableData[0].alternateField4
            }
            this.pageConfig.isLoading = false
          }).catch((error) => {
            this.AppUtils.showWarning('查询失败' + error)
            this.pageConfig.isLoading = false
          })
        } else {
          this.pageConfig.isLoading = false
        }
      },
      /**
       * 保存属性关联条件及其条件列表
       */
      saveAll () {
        let relatedConditions = this.relConditionConditionsTableData.concat(this.deletedRelConditionConditionsTableData)
        if (relatedConditions.length === 0) {
          this.AppUtils.showWarning('审核条件列表不能为空！')
          return
        }
        for (let i = 0; i < relatedConditions.length; i++) {
          relatedConditions[i].processDocuObjId = this.classId
          relatedConditions[i].alternateField5 = this.conFormulaInfo.formulaName
        }
        let returnObj = {
          formulaId: this.alternateField4,
          formulaName: this.conFormulaInfo.formulaName
        }
        this.postData = returnObj
        console.log(relatedConditions)
        relConditionService.saveAuditCon(relatedConditions).then((res) => {
          this.alternateField4 = res
          this.postData.formulaId = res
          this.deletedRelConditionConditionsTableData = []
          this.$message({
            type: 'success',
            message: '保存成功!'
          })
          let obj = {
            eventType: 'auditCondition',
            operType: 'save',
            eventData: this.postData
          }
          this.postMsg(obj)
        }).catch((error) => {
          this.AppUtils.showWarning('保存失败' + error)
        })
      },
      /**
       * 保存校验
       */
      checkSave () {
        this.$refs.conFormula.validate((valid) => {
          if (valid) {
            this.saveAll()
          }
        })
      },
      addAuditCondition () {
        let index = this.relConditionConditionsTableData.length
        let title = '审核角色设置-新增'
        let mode = 'add'
        this.openConditionEditDialog({index, title, mode})
      },
      editAuditCondition (index) {
        let title = '审核角色设置-编辑'
        let mode = 'edit'
        let cond = this.relConditionConditionsTableData[index]
        this.openConditionEditDialog({index, title, cond, mode})
      },
      openConditionEditDialog ({index, title, cond, mode}) {
        this.OpenGlobalDialog({
          name: 'auditConditionConfigEdit',
          component: AuditConditionConfigEdit,
          title: title,
          props: {
            index: index,
            mode: mode,
            cond: cond,
            methodTypeFlagDict: this.methodTypeFlagDict
          },
          options: {
            title: title,
            top: '20%',
            customClass: 'subpage-dlg dialog-width-s'
          },
          events: {
            saveCond: this.saveCond,
            close: () => {
              this.CloseGlobalDialog('auditConditionConfigEdit')
            }
          }
        })
      },
      /**
       * 条件列表行删除
       */
      removeAuditCondition (index) {
        this.$confirm('是否确认删除?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          let aditId = this.relConditionConditionsTableData[index].aditId
          if (aditId) {   //  如果删除行存在主键Id，则保存至删除区，否则直接删除
            let dataClone = this.Utils.cloneDeep(this.relConditionConditionsTableData[index])
            dataClone.isenable = 0  //  修改可用标识，0：不可用  1：可用
            this.deletedRelConditionConditionsTableData.push(dataClone)   //  添加到删除提交区
            this.relConditionConditionsTableData.splice(index, 1)
          } else {
            this.relConditionConditionsTableData.splice(index, 1)
          }
          //  每次删除后重新排序,TODO 根据需要更改
          for (let i = 0; i < this.relConditionConditionsTableData.length; i++) {
            this.relConditionConditionsTableData[i].auditSeq = i + 1
          }
        }).catch(() => {
          // 取消删除
        })
      },
      /**
       * 保存条件
       */
      saveCond ({index, condData}) {
        console.log(condData)
        this.$set(this.relConditionConditionsTableData, index, condData)
      },
      postMsg (obj) {
        console.info('obj', obj)
        let returnData = {
          formularName: obj.eventData.formulaName,
          formularId: obj.eventData.formulaId
        }
        if (window !== window.parent) {
          window.parent.postMessage(returnData, '*')
        }
        if (window.opener) {
          window.opener.postMessage(returnData, '*')
          window.opener = null
          window.open(' ', '_self')
          window.close()
        }
      },
      delRelCond  () {
        if (!this.alternateField4) {
          this.AppUtils.showWarning('还没有条件,请添加后重试...')
          return
        }
//        if (this.relConditionConditionsTableData.length < 1) {
//          this.AppUtils.showWarning('请添加数据后删除！')
//          return
//        }
        this.$confirm('是否删除该审核条件？', '提示', {
          confirmButtonText: '确定',
          canceButtonText: '取消',
          type: 'warning'
        }).then(() => {
          let alternateField4 = this.alternateField4
          relConditionService.delAuditCond(alternateField4).then(() => {
            let obj = {
              eventType: 'auditCondition',
              operType: 'delete',
              eventData: {
                formulaId: this.alternateField4,
                formulaName: ''
              }
            }
            this.postMsg(obj)
            this.AppUtils.showSuccess('审核条件已删除')
          }).catch((error) => {
            console.log(error)
            this.AppUtils.showErrorMsg(error)
          })
        }).catch(() => {})
      }
    },
    computed: {
      ...mapGetters({
        compareTypeDict: 'getCndtOperatorDict',
        compareTargetTypeDict: 'getCndtValueTypeDict4RelCndt'
      })
    }
  }
</script>
<style scoped>
  .class-name{
    font-weight: bold;
    margin-left: 5px;
  }
  .rel-condition-config {
    max-width: 1024px;
    margin-left:auto;
    margin-right:auto;
    padding-left: 10px;
    padding-right: 10px;
    border: 1px solid #d1dbe5;
    border-radius: 4px;
    background-color: #f9f9f9;
    overflow: hidden;
    box-shadow: 0 2px 4px 0 rgba(0,0,0,.12), 0 0 6px 0 rgba(0,0,0,.04);
  }

  .relcondition-page-header {
    position: relative;
    width: 100%;
    height: 40px;
  }
  .relcondition-page-header .page-header__page-title {
    position: absolute;
    color:#000;
    font-size:15px;
    font-weight: bold;
    line-height: 15px;
    margin-top: 12px;
    margin-bottom: 0px;
    padding-left: 5px; /* 与顶部导航的padding-left一致 */
  }
  .condition-editor-range {
    position: relative;
    margin-top: 10px;
    margin-bottom:10px;
    padding-top:2px;
    padding-right: 10px;
    padding-left:10px;
    padding-bottom:2px;
    border: 1px solid gray;
    min-height: 25px; /* textarea 1行高度一致 */
    border-radius: 4px;
    font-size: 14px;
  }
  .condition-editor-descrip {
    position: relative;
    margin-top: 10px;
    margin-bottom:10px;
    padding-top:2px;
    padding-right: 10px;
    padding-left:10px;
    padding-bottom:2px;
    border: 1px solid gray;
    min-height: 25px; /* textarea 1行高度一致 */
    border-radius: 4px;
    font-size: 14px;
  }
  .rel-condition-config .prop-full-lable {
    padding-left: 12px;
    font-weight: bold;
  }
  .compare-type-selector .el-select-dropdown__item {
    padding: 1px 8px;
    height: 30px;
  }
  .compare-type-selector .el-select-dropdown__list {
    padding:0;
  }
  .target-type-select {
    width: 100px;
  }
  .target-value-input {
    width: calc(100% - 120px);
  }
  .condition-index-span {
    color: blue;
  }
  .seq-base {
    color: blue;
    font-size: 14px;
  }
  .ope-base {
    color: green;
    font-size: 14px;
  }
  .prop-base {
    color: blue;
    font-size: 14px;
  }
  .condition-base {
    color: #FF00FF;
    font-size: 14px;
  }
  .cndtValue-base {
    color: #000000;
    font-size: 14px;
  }
  .dialog-width-self {
    width: 23%;
  }
</style>
