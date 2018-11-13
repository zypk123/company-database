<template>
  <div class="rel-trigger-condition-config" v-loading.fullscreen.lock="pageConfig.isLoading" :element-loading-text="pageConfig.loadingText">
    <el-row>
      <el-col class="common-vgap" :span="24">
        <el-button type="primary" @click="checkSave" title="保存关联触发条件">保存</el-button>
        <el-button type="danger" @click="delRelTrigger">删除</el-button>
      </el-col>
    </el-row>

    <el-row>
      <el-col class="common-vgap" :span="24">
        <span>类名称:</span><span class="prop-full-lable">{{clssName}}</span>
      </el-col>

      <el-col class="common-vgap" :span="24">
        <span>条件列表:</span>
        <div class="float-right">
          <el-button type="primary" size="small" icon="plus" @click="addNewCond">添加</el-button>
        </div>
      </el-col>

      <!-- 条件列表数据展示区 -->
      <el-col class="common-vgap" :span="24">
        <el-table
          :data="relTriggerConditionConditionsTableData"
          border>

          <el-table-column align='center' width="80" label="序号">
            <template scope="scope">
              <span class="condition-index-span" v-if="!scope.row.isAdding">
                <el-button type="text" @click="insertSeq(scope.row.cndrSeq)">[ {{scope.row.cndrSeq}} ]</el-button>
              </span>
            </template>
          </el-table-column>

          <el-table-column header-align="center" align="center"
                           label="属性"
                           width="280"
                           prop="cndrProp">
          </el-table-column>

          <el-table-column header-align="center" align="center"
                           prop="cndrOperate"
                           label="条件"
                           width="150"
                           :formatter="tableCndrOperateFormat">
          </el-table-column>

          <el-table-column header-align="center" align="center"
                           prop="cndrProp2"
                           label="值"
                           width="280">
          </el-table-column>

          <el-table-column header-align="center" align="center"
                           label="操作" >
            <template scope="scope">
              <el-button v-if="!scope.row.isEditing" size="small" @click="editCond(scope.$index)" type="text">编辑</el-button>
              <el-button v-if="!scope.row.isEditing" size="small" @click="removeLine(scope.$index)" type="text">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-col>

      <!--条件公式编辑组件-->
      <el-col class="common-vgap" :span="24">
        <cond-formula-editor ref="editor"
                             :editable="true"
                             :initFormulaName="formularName"
                             :initFormulaText="formularText"
                             :condsDescHtmlArr="condsDescHtmlArr"
                             :condsDescTextArr="condsDescTextArr"
        ></cond-formula-editor>
      </el-col>
    </el-row>

  </div>
</template>
<script>
  import _ from 'lodash'
  import { mapGetters } from 'vuex'
  import { relConditionService, formulaService } from '@/api'
  import CondFormulaEditor from '@/components/common/cond-formula-editor/CondFormulaEditor'
  import RegulatoryTreeConditionConfigEdit from './RegulatoryTreeConditionConfigEdit'
  export default {
    name: 'regulatoryTreeConditionConfig',
    data () {
      return {
        pageConfig: {
          isLoading: false,
          loadingText: '拼命加载中......',
          isIME: false
        },
        postData: {
          // 保存用于POST的数据
        },
        // 输入参数
        propId: '',
        clssPropName: '',
        clssName: '',
        clssNameEn: '',
        classId: '',
        propCode: '',

        prplId: '',   //  关联属性表主键ID ==>关联条件表外键
        loading: false,
        cndrPropOriginCode: [],
        cndrPropOriginCodeRight: '',
        formularName: '',
        formularText: '',

        lastSelectedRange: null,
        relTriggerConditionConditionsTableData: [],       //  条件列表数据展示区
        deletedRelTriggerConditionConditionsTableData: [],  //  条件列表数据删除提交区（不包含 添加后删除）
        variableData: [], // 变量数组
        prevRelProp: [] // 属性数据字典

      }
    },
    // 页面初始化之前调用该方法
    created () {
      if (this.$route.name === 'regulatoryTreeConditionConfig') { // 路由参数赋值
        this.pageConfig.isLoading = true
        this.classId = this.$route.params.classId
        this.prplId = this.$route.params.prplId
        console.log(this.classId + '====' + this.prplId)

        // 通过类Id查类的基本信息
        formulaService.getClassInfoByClassId(this.classId).then((res) => {
          // console.log('resData', res)
          this.clssName = (res.edmcName === null ? '' : res.edmcName)
          this.clssNameEn = (res.edmcNameEn === null ? '' : res.edmcNameEn)
          this.clssPropName = ''
          this.classId = (res.id === null ? '' : res.id)
          this.propCode = ''
          this.getPropsbyClassId(res.id).then((resData) => {
            for (let i = 0; i < resData.length; i++) {
              this.prevRelProp.push({edmpCode: resData[i].edmpCode, edmpName: resData[i].edmpName, edmpId: resData[i].id})
            }
          })
          this.queryRelTirggerConditionAndConditions()
          this.queryVariableData()
        }).catch((error) => {
          this.AppUtils.showWarning('查询类信息失败' + error)
        })
      } else {
        this.AppUtils.showError('关联触发条件设置打开方式不正确')
      }
    },
    methods: {
      /**
       * 页面初始化加载属性关联条件及其相关条件列表
       */
      queryRelTirggerConditionAndConditions () {
        if (this.prplId) {
          relConditionService.queryRelConditionAndConditions({prplId: this.prplId}).then((res) => {
            // console.log('response', res)
            let conditions = res.relatedConditions
            if (conditions.length > 0) {
              //  初始化条件列表
              conditions.sort(function (a, b) {
                return a.cndrSeq > b.cndrSeq
              })
              // 处理字符串和数组之间的转换
              for (let i = 0; i < conditions.length; i++) {
                if (conditions[i].cndrPropOriginCode.indexOf(' ') !== -1) {
                  conditions[i].cndrPropOriginCode = _.split(conditions[i].cndrPropOriginCode, ' ')
                } else {
                  conditions[i].cndrPropOriginCode = [conditions[i].cndrPropOriginCode]
                }
              }
              this.relTriggerConditionConditionsTableData = conditions
            }
            if (res.relatedProperty) {
              //  初始化属性关联
              let relData = res.relatedProperty
              this.formularName = (relData.prplConditionName === 'null' ? '' : relData.prplConditionName)
              this.formularText = (relData.prplConditionFormula === 'null' ? '' : relData.prplConditionFormula)
              this.prplId = relData.prplId
              this.$refs.editor.formulaInfo = { // 给条件编辑公式组件赋值
                formulaText: this.formularText,
                formulaName: this.formularName
              }
              this.$refs.editor.formulaHtml = this.formularText || ''
            }
            this.pageConfig.isLoading = false
          }).catch((error) => {
            this.AppUtils.showWarning('初始化触发条件失败！' + error.message)
          })
        } else {
          this.pageConfig.isLoading = false
        }
      },
      /**
       * 保存校验
       */
      checkSave () {
        this.$refs.editor.valid().then((resData) => {
          this.formularName = resData.formulaName
          this.formularText = resData.formulaText
          this.formularDescripText = resData.formulaDesc
        }).then(() => {
          this.saveAll()
        })
      },
      /**
       * 保存属性关联条件及其条件列表
       */
      saveAll () {
        this.$refs.editor.valid().then(() => {
          // console.log(this.relTriggerConditionConditionsTableData)
          let related = this.relTriggerConditionConditionsTableData.concat(this.deletedRelTriggerConditionConditionsTableData)
          let relatedConditions = this.Utils.cloneDeep(related)
          for (let i = 0; i < relatedConditions.length; i++) {
            relatedConditions[i].cndrClass1NameEn = this.clssNameEn
            relatedConditions[i].cndrPropOriginCode = _.join(relatedConditions[i].cndrPropOriginCode, ' ')
          }
          // console.log(relatedConditions)
          let relatedProperty = {
            prplId: this.prplId,
            prplClassRelatedFrom: this.clssName,
            prplClassRelatedTo: '',
            prplClass1NameEn: this.clssNameEn,
            prplProp1Code: this.propCode,
            prplClass2NameEn: '',
            prplProp2Code: '',
            prplConditionName: this.formularName,
            prplConditionFormula: this.formularText,
            prplConditionDesc: this.formularDescripText
          }
          this.postData = relatedProperty
          // console.log('postData', this.postData)
          // 属性Id变为类Id
          let classId = this.classId
          relConditionService.saveTriCon({relatedProperty, relatedConditions, classId}).then((res) => {
            this.prplId = res
            this.postData.prplId = res
            this.deletedRelTriggerConditionConditionsTableData = []
            this.$message({
              type: 'success',
              message: '保存成功!'
            })
            let obj = {
              eventType: 'RelTriggerCondition',
              operType: 'save',
              eventData: this.postData
            }
            this.postMsg(obj)
          }).catch((error) => {
            this.AppUtils.showWarning('保存失败' + error)
          })
        }).catch((error) => {
          // 校验失败
          console.log(error)
        })
      },
      /**
       * 通过类id查询属性
       */
      getPropsbyClassId (classId) {
        return new Promise((resolve, reject) => {
          formulaService.queryEdmClassPropsByClassID({edmcId: classId}).then((resData) => {
            resolve(resData)
          }).catch((error) => {
            this.AppUtils.showWarning(error.message)
          })
        })
      },
      /**
       * 变量模糊查询
       */
      queryVariableData () {
        let params = {varName: '', state: 'inusing'}
        relConditionService.queryVariableData(params).then((res) => {
          this.variableData = res
        }).catch((error) => {
          this.AppUtils.showWarning('查询变量失败' + error)
        })
      },
      /**
       * 添加条件
       */
      addNewCond () {
        let index = this.relTriggerConditionConditionsTableData.length
        let title = '关联触发条件设置-新增条件'
        let mode = 'add'
        this.openCondEditDialog({index, title, mode})
      },
      /**
       * 编辑条件
       */
      editCond (index) {
        let title = '关联触发条件设置-编辑条件'
        let mode = 'edit'
        let cond = this.relTriggerConditionConditionsTableData[index]
        this.openCondEditDialog({index, title, cond, mode})
      },
      /**
       * 打开新增/编辑条件弹框
       */
      openCondEditDialog ({index, title, cond, mode}) {
        this.OpenGlobalDialog({
          name: 'regulatoryTreeConditionConfigEdit',
          component: RegulatoryTreeConditionConfigEdit,
          title: title,
          props: {
            index: index,
            mode: mode,
            cond: cond,
            variableData: this.variableData,
            prevRelProp: this.prevRelProp,
            clssId: this.classId,
            clssName: this.clssName
          },
          options: {
            title: title,
            top: '20%',
            customClass: 'subpage-dlg dialog-width-s'
          },
          events: {
            saveCond: this.saveCond,
            close: () => {
              this.CloseGlobalDialog('regulatoryTreeConditionConfigEdit')
            }
          }
        })
      },
      /**
       * 保存条件
       */
      saveCond ({index, condData}) {
        // console.log('condData', condData)
        this.$set(this.relTriggerConditionConditionsTableData, index, condData)
      },
      /**
       * 点击条件列表序号联动条件公式
       */
      insertSeq (seq) {
        this.$refs.editor.insertSeq(seq)
      },
      /**
       * 条件列表行删除
       */
      removeLine (index) {
        this.$confirm('是否确认删除?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          let cndrId = this.relTriggerConditionConditionsTableData[index].cndrId
          if (cndrId) {   //  如果删除行存在主键Id，则保存至删除区，否则直接删除
            let dataClone = this.Utils.cloneDeep(this.relTriggerConditionConditionsTableData[index])
            dataClone.isenable = 0  //  修改可用标识，0：不可用  1：可用
            this.deletedRelTriggerConditionConditionsTableData.push(dataClone)   //  添加到删除提交区
            this.relTriggerConditionConditionsTableData.splice(index, 1)
          } else {
            this.relTriggerConditionConditionsTableData.splice(index, 1)
          }
          //  每次删除后重新排序,TODO 根据需要更改
          for (let i = 0; i < this.relTriggerConditionConditionsTableData.length; i++) {
            this.relTriggerConditionConditionsTableData[i].cndrSeq = i + 1
          }
          //  每次删除后，重置公式
          this.$refs.editor.clearFormula()
        }).catch(() => {
          // 取消删除
        })
      },
      /**
       * 根据操作符 =,>,<等获取字典说明
       */
      getCompareTypeDict (value) {
        for (let i = 0; i < this.compareTypeDict.length; i++) {
          if (value === this.compareTypeDict[i].val) {
            return this.compareTypeDict[i].label
          }
        }
        return '未知操作符'
      },
      tableCndrOperateFormat (row, column) {
        let cndrOperate = row[column.property]
        if (cndrOperate === undefined) {
          return '未知操作符'
        } else {
          for (let i = 0; i < this.compareTypeDict.length; i++) {
            if (cndrOperate === this.compareTypeDict[i].val) {
              return this.compareTypeDict[i].label
            }
          }
          return '未知操作符'
        }
      },
      delRelTrigger  () {
        if (!this.prplId) {
          console.log('delRelCond fail......')
          this.AppUtils.showSuccess('还没有触发条件,请添加后再重试...')
          return
        }
        this.$confirm('是否删除该触发条件？', '提示', {
          confirmButtonText: '确定',
          canceButtonText: '取消',
          type: 'warning'
        }).then(() => {
          let prplId = this.prplId
          relConditionService.delRelTrigger({prplId}).then(() => {
            let obj = {
              eventType: 'RelTriggerCondition',
              operType: 'delete',
              eventData: {
                prplId: this.prplId,
                prplConditionName: ''
              }
            }
            this.postMsg(obj)
            this.AppUtils.showSuccess('触发公式已删除')
          }).catch((error) => {
            console.log(error)
            this.AppUtils.showErrorMsg(error)
          })
        }).catch(() => {})
      },
      postMsg (obj) {
        console.info('obj', obj)
        if (window !== window.parent) {
          window.parent.postMessage(obj, '*')
          // window.parent.$scope.save(formulaName,formulaId)
        }
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
        compareTypeDict: 'getCndrOperatorDict',
        compareTargetTypeDict: 'getCndtValueTypeDict4RelCndt'
      }),
      condsDescHtmlArr () { // 条件的html
        let retArr = []
        for (let i = 0; i < this.relTriggerConditionConditionsTableData.length; i++) {
          let line = `<div class="formula-item formula-item-prop" contenteditable="false">${this.relTriggerConditionConditionsTableData[i].cndrProp}</div>`
          line += `<div class="formula-item formula-item-logic-oper" contenteditable="false">${this.getCompareTypeDict(this.relTriggerConditionConditionsTableData[i].cndrOperate)}</div>`
          line += `<div class="formula-item" contenteditable="false">${this.relTriggerConditionConditionsTableData[i].cndrProp2}</div>`
          retArr.push(line)
        }
        return retArr
      },
      condsDescTextArr () { // 条件的html描述
        let retArr = []
        for (let i = 0; i < this.relTriggerConditionConditionsTableData.length; i++) {
          let line = `${this.relTriggerConditionConditionsTableData[i].cndrProp} ${this.getCompareTypeDict(this.relTriggerConditionConditionsTableData[i].cndrOperate)} ${this.relTriggerConditionConditionsTableData[i].cndrProp2}`
          retArr.push(line)
        }
        return retArr
      }
    },
    components: {
      CondFormulaEditor
    }
  }
</script>
<style>
  .rel-trigger-condition-config {
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

  .reltriggercondition-page-header {
    position: relative;
    width: 100%;
    height: 40px;
  }
  .reltriggercondition-page-header .page-header__page-title {
    position: absolute;
    color:#000;
    font-size:15px;
    font-weight: bold;
    line-height: 15px;
    margin-top: 12px;
    margin-bottom: 0px;
    padding-left: 5px; /* 与顶部导航的padding-left一致 */
  }
  .rel-trigger-condition-config .prop-full-lable {
    padding-left: 12px;
    font-weight: bold;
  }
  .dialog-width-self {
    width: 28%;
  }
</style>
