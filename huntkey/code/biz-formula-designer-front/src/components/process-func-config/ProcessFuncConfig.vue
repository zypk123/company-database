<template>
  <div class="rel-condition-config" v-loading.fullscreen.lock="pageConfig.isLoading" :element-loading-text="pageConfig.loadingText">

    <el-row>
      <el-col class="common-vgap" :span="24">
        <el-button type="primary" @click="checkSave" title="保存条件">保存</el-button>
        <el-button type="danger" @click="delRelCond">删除</el-button>
      </el-col>
    </el-row>

    <el-row>
      <!--<el-col class="common-vgap" :span="24">-->
        <!--<span>流程名称:</span><span class="prop-full-lable">流程处理</span>-->
      <!--</el-col>-->

      <el-col class="common-vgap" :span="24">
        <span>条件列表:</span>
        <div class="float-right">
          <el-button type="primary" size="small" icon="plus" @click="addNewCond">添加</el-button>
        </div>
      </el-col>

      <!-- 条件列表数据展示区 -->
      <el-col class="common-vgap" :span="24">
        <el-table
          :data="relConditionConditionsTableData"
          border>

          <el-table-column align='center' width="80" label="序号">
            <template scope="scope">
              <span class="condition-index-span">
                <el-button type="text" @click="insertSeq(scope.row.cndrSeq)">[ {{scope.row.cndrSeq}} ]</el-button>
              </span>
            </template>
          </el-table-column>

          <el-table-column header-align="center" align="center"
                           label="名称"
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
                           label="值"
                           width="280"
                           prop="cndrProp2">
          </el-table-column>

          <el-table-column header-align="center" align="center"
                           label="操作" >
            <template scope="scope">
              <el-button size="small" @click="editCond(scope.$index)" type="text">编辑</el-button>
              <el-button size="small" @click="removeLine(scope.$index)" type="text">删除</el-button>
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
  import ProcessFuncConfigEdit from './ProcessFuncConfigEdit'
  import CondFormulaEditor from '@/components/common/cond-formula-editor/CondFormulaEditor'

  export default {
    name: 'processFuncConfig',
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
        propId1: '',
        classId: '',
        prop1Code: '',
        class1NameEn: '',
        clssName1: '',
        clssPropName1: '',

        prplId: '',   //  关联属性表主键ID ==>关联条件表外键
        loading: false,
        cndrPropOriginCode: '',

        formularName: '',
        formularText: '',
        relConditionConditionsTableData: [],       //  条件列表数据展示区
        deletedRelConditionConditionsTableData: [],  //  条件列表数据删除提交区（不包含添加后删除）
        variableData: [], // 变量数组
        funcs: [],
        relClasses: [], // 相关类
        classes: [], // 类
        linkClassArray: []
      }
    },
    // 页面初始化方法
    created () {
      if (this.$route.name === 'processFuncConfig') {
        this.pageConfig.isLoading = true
        this.classId = this.$route.params.classId
        this.prplId = this.$route.params.prplId
        console.log(this.classId + '====' + this.prplId)
//        this.relConditionConditionsTableData = [{
//          cndrSeq: 1,
//          typeFlag: 'propType',
//          cndrProp: '责任人类.辨别属性.身份证号',
//          cndrPropValue: '责任人类.辨别属性.身份证号',
//          cndrOperate: '<',
//          cndrValueType: 'const',
//          cndrProp2: '32',
//          cndrProp2Value: '32',
//          classTypeFlag: 'class',
//          classId: '20170121',
//          classPropId: ['01', '012']
//        }, {
//          cndrSeq: 2,
//          typeFlag: 'propType',
//          cndrProp: '企业类.企业编号',
//          cndrPropValue: '企业类.企业编号',
//          cndrOperate: '>',
//          cndrValueType: 'variable',
//          cndrProp2: 'num',
//          cndrProp2Value: '0002',
//          classTypeFlag: 'relClass',
//          classId: '20170123',
//          classPropId: ['08']
//        }]
        // 通过类Id查类的基本信息
        formulaService.getClassInfoByClassId(this.classId).then((res1) => {
          console.log('res1', res1)
          this.clssName1 = res1.edmcName
          this.class1NameEn = res1.edmcNameEn
          this.classId1 = res1.id
          this.queryClasses()
          this.queryRelClasses()
          this.queryRelConditionAndConditions()
          this.queryVariableData()
        }).catch((error) => {
          this.AppUtils.showWarning('查询类信息失败' + error)
        })
        this.pageConfig.isLoading = false
      } else {
        this.AppUtils.showError('关联条件设置打开方式不正确')
      }
    },
    methods: {
      /** 查询关联类 **/
      queryClasses () {
        formulaService.getClassInfoByClassId(this.classId).then((data) => {
          console.log('关联类', data)
          this.linkClassArray.push(data)
          console.log(this.linkClassArray)
          this.classes = this.Utils.cloneDeep(this.linkClassArray)
        }).catch((err) => {
          console.log(err.message)
        })
      },
      /** 查询相关类 **/
      queryRelClasses () {
        formulaService.queryRelClassesByClassId({edmcId: this.classId, type: 2}).then((data) => {
          console.log('相关类', data)
          this.relClasses = data
        }).catch((err) => {
          // console.log(err.message)
          this.AppUtils.showWarning('查询相关类失败' + err)
        })
      },
      /** 页面初始化加载属性关联条件及其条件列表 **/
      queryRelConditionAndConditions () {
        if (this.prplId !== undefined) {
          relConditionService.queryRelConditionAndConditions({prplId: this.prplId}, (res) => {
            console.log('action', res)
            let conditions = res.relatedConditions
            if (conditions.length > 0) {
              //  初始化条件列表
              conditions.sort(function (a, b) {
                return a.cndrSeq > b.cndrSeq
              })
              // 处理字符串和数组之间的转换
              for (let i = 0; i < conditions.length; i++) {
                if (conditions[i].cndrPropOriginCode.indexOf(' ') !== -1) {
                  conditions[i].classPropId = _.split(conditions[i].cndrPropOriginCode, ' ')
                } else {
                  conditions[i].classPropId = [conditions[i].cndrPropOriginCode]
                }
                conditions[i].cndrProp2Value = conditions[i].cndrProp2OriginCode
                conditions[i].classId = conditions[i].linkClassOrClassId
                conditions[i].cndrPropValue = conditions[i].cndrProp
              }
              this.relConditionConditionsTableData = conditions
              // console.log(this.relConditionConditionsTableData)
            }
            if (res.relatedProperty) {
              //  初始化属性关联
              let relData = res.relatedProperty
              // console.log('relData', relData)
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
            this.AppUtils.showWarning('查询失败！' + error)
          })
        } else {
          console.log('undefined')
          this.pageConfig.isLoading = false
        }
      },
      /** 变量模糊查询 **/
      queryVariableData () {
        let params = {varName: '', state: 'inusing'}
        relConditionService.queryVariableData(params, (res) => {
          this.variableData = res
        }, (error) => {
          this.AppUtils.showWarning('查询变量失败' + error)
        })
      },
      /** 保存属性关联条件及其条件列表 **/
      saveAll () {
        this.$refs.editor.valid().then(() => {
          let relatedProperty = {
            prplId: this.prplId,
            prplClassRelatedFrom: this.clssName1,
            prplConditionName: this.formularName,
            prplClassRelatedTo: '',
            prplClass1NameEn: this.class1NameEn,
            prplProp1Code: '',
            prplConditionFormula: this.formularText.trim(),
            prplConditionDesc: this.formularDescripText,
            prplClass2NameEn: '',
            prplProp2Code: ''
          }
          this.postData = relatedProperty
          // 属性Id改为类Id
          let relatedConditions = this.relConditionConditionsTableData.concat(this.deletedRelConditionConditionsTableData)
          console.log(relatedConditions)
          for (let i = 0; i < relatedConditions.length; i++) {
            relatedConditions[i].cndrPropOriginCode = _.join(relatedConditions[i].classPropId, ' ')
            relatedConditions[i].cndrProp2OriginCode = relatedConditions[i].cndrProp2Value
            relatedConditions[i].linkClassOrClassId = relatedConditions[i].classId
          }
          console.log('test', relatedConditions)
          relConditionService.saveRelCondition({relatedProperty, relatedConditions}).then((res) => {
            this.prplId = res
            this.postData.prplId = res
            this.deletedRelConditionConditionsTableData = []
            this.$message({
              type: 'success',
              message: '保存成功!'
            })
            let obj = {
              eventType: 'RelCondition',
              operType: 'save',
              eventData: this.postData
            }
            this.postMsg(obj)
          }).catch((error) => {
            this.AppUtils.showWarning('保存失败' + error)
          })
        }).catch((error) => {
          console.log('error', error)
        })
      },
      /** 保存校验 **/
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
       * 条件列表行删除
       */
      removeLine (index) {
        this.$confirm('是否确认删除?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          let cndrId = this.relConditionConditionsTableData[index].cndrId
          if (cndrId) {   //  如果删除行存在主键Id，则保存至删除区，否则直接删除
            let dataClone = this.Utils.cloneDeep(this.relConditionConditionsTableData[index])
            dataClone.isenable = 0  //  修改可用标识，0：不可用  1：可用
            this.deletedRelConditionConditionsTableData.push(dataClone)   //  添加到删除提交区
            this.relConditionConditionsTableData.splice(index, 1)
          } else {
            this.relConditionConditionsTableData.splice(index, 1)
          }
          //  每次删除后重新排序,TODO 根据需要更改
          for (let i = 0; i < this.relConditionConditionsTableData.length; i++) {
            this.relConditionConditionsTableData[i].cndrSeq = i + 1
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
      /**
       * 点击条件列表序号联动条件公式
       */
      insertSeq (seq) {
        this.$refs.editor.insertSeq(seq)
      },
      /**
       * 添加条件
       */
      addNewCond () {
        let index = this.relConditionConditionsTableData.length
        let title = '流程函数-新增'
        let mode = 'add'
        this.openCondEditDialog({index, title, mode})
      },
      /**
       * 编辑条件
       */
      editCond (index) {
        let title = '流程函数-编辑'
        let mode = 'edit'
        let cond = this.relConditionConditionsTableData[index]
        this.openCondEditDialog({index, title, cond, mode})
      },
      /**
       * 打开新增/编辑条件弹框
       */
      openCondEditDialog ({index, title, cond, mode}) {
        this.OpenGlobalDialog({
          name: 'processFuncConfigEdit',
          component: ProcessFuncConfigEdit,
          title: title,
          props: {
            index: index,
            mode: mode,
            cond: cond,
            classId: this.classId,
            funcs: this.funcs,
            variableData: this.variableData,
            classes: this.classes,
            relClasses: this.relClasses
          },
          options: {
            title: title,
            top: '20%',
            customClass: 'subpage-dlg dialog-width-s'
          },
          events: {
            saveCond: this.saveCond,
            updateRelClass: this.updateRelClass,
            close: () => {
              this.CloseGlobalDialog('processFuncConfigEdit')
            }
          }
        })
      },
      updateRelClass () {
        formulaService.queryRelClassesByClassId({edmcId: this.classId, type: 2}).then((data) => {
          console.log('更新相关类', data)
          this.relClasses = data
        }).catch((err) => {
          console.log(err.message)
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
        let returnData = {
          formularName: obj.eventData.prplConditionName,
          formularId: obj.eventData.prplId
        }
        if (window !== window.parent) {
          window.parent.postMessage(returnData, '*')
          // window.parent.$scope.save(obj.eventData.prplConditionName, obj.eventData.prplId)
        }
        if (window.opener) {
          window.opener.postMessage(returnData, '*')
          // window.parent.$scope.save(obj.eventData.prplConditionName, obj.eventData.prplId)
          window.opener = null
          window.open(' ', '_self')
          window.close()
        }
      },
      delRelCond  () {
        if (!this.prplId) {
          this.AppUtils.showSuccess('还没有关联条件,请添加后再重试...')
          return
        }
        this.$confirm('是否删除该关联条件？', '提示', {
          confirmButtonText: '确定',
          canceButtonText: '取消',
          type: 'warning'
        }).then(() => {
          let prplId = this.prplId
          relConditionService.delRelCond({prplId}).then(() => {
            let obj = {
              eventType: 'RelCondition',
              operType: 'delete',
              eventData: {
                prplId: this.prplId,
                prplConditionName: ''
              }
            }
            this.postMsg(obj)
            this.AppUtils.showSuccess('关联条件已删除')
          }).catch((error) => {
            this.AppUtils.showErrorMsg(error)
          })
        }).catch(() => {})
      }
    },
    computed: {
      ...mapGetters({
        compareTypeDict: 'getCndtOperatorDict',
        compareTargetTypeDict: 'getCndtValueTypeDict4RelCndt'
      }),
      condsDescHtmlArr () { // 条件的html
        let retArr = []
        for (let i = 0; i < this.relConditionConditionsTableData.length; i++) {
          let line = `<div class="formula-item formula-item-prop" contenteditable="false">${this.relConditionConditionsTableData[i].cndrProp}</div>`
          line += `<div class="formula-item formula-item-logic-oper" contenteditable="false">${this.getCompareTypeDict(this.relConditionConditionsTableData[i].cndrOperate)}</div>`
          line += `<div class="formula-item" contenteditable="false">${this.relConditionConditionsTableData[i].cndrProp2}</div>`
          retArr.push(line)
        }
        return retArr
      },
      condsDescTextArr () { // 条件的html描述
        let retArr = []
        for (let i = 0; i < this.relConditionConditionsTableData.length; i++) {
          let line = `${this.relConditionConditionsTableData[i].cndrProp} ${this.getCompareTypeDict(this.relConditionConditionsTableData[i].cndrOperate)} ${this.relConditionConditionsTableData[i].cndrProp2}`
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
<style scoped>
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
