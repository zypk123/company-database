<template>
  <div class="rel-condition-config" v-loading.fullscreen.lock="pageConfig.isLoading"
       :element-loading-text="pageConfig.loadingText">

    <el-row>
      <el-col class="common-vgap" :span="24">
        <el-button type="primary" @click="checkSave" title="保存关联条件">保存</el-button>
        <el-button type="danger" @click="delRelCond">删除</el-button>
      </el-col>
    </el-row>

    <el-row>
      <el-col class="common-vgap" :span="24">
        <span>关联属性:</span><span
        class="prop-full-lable">{{clssName1 + '.' + clssPropName1}} 关联 {{clssName2 + '.' + clssPropName2}}</span>
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
          :data="relConditionConditionsTableData"
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
                           label="属性"
                           width="280"
                           prop="cndrProp2">
          </el-table-column>

          <el-table-column header-align="center" align="center"
                           label="操作">
            <template scope="scope">
              <el-button v-if="!scope.row.isEditing" size="small" @click="editCond(scope.$index)" type="text">编辑
              </el-button>
              <el-button v-if="!scope.row.isEditing" size="small" @click="removeLine(scope.$index)" type="text">删除
              </el-button>
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
  import {mapGetters} from 'vuex'
  import {relConditionService, formulaService} from '@/api'
  import RelConditionCondEdit from './RelConditionCondEdit'
  import CondFormulaEditor from '@/components/common/cond-formula-editor/CondFormulaEditor'

  export default {
    name: 'relConditionConfig', // 这个name设置，以后引用组件就用这个名字
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
        propId1: '',
        propId2: '',
        classId1: '',
        classId2: '',
        prop1Code: '',
        prop2Code: '',
        class1NameEn: '',
        class2NameEn: '',

        clssName1: '',
        clssPropName1: '',
        clssName2: '',
        clssPropName2: '',

        prplId: '',   //  关联属性表主键ID ==>关联条件表外键
        loading: false,
        cndrPropOriginCode: '',
        cndrPropOriginCodeRight: '',
        formularName: '',
        formularText: '',

        lastSelectedRange: null,
        relConditionConditionsTableData: [],       //  条件列表数据展示区
        deletedRelConditionConditionsTableData: [],  //  条件列表数据删除提交区（不包含添加后删除）
        variableData: [], // 变量数组
        prevRelClasses: [], // 类数据字典
        prevRelProp: {}, // 属性数据字典
        tempArray: [],
        tempArray2: []
      }
    },
    // 页面初始化方法
    created () {
      // alert('created.........')
      if (this.$route.name === 'relConditionConfig') { // 路由参数赋值
        this.pageConfig.isLoading = true
        this.propId1 = this.$route.params.propId1
        this.propId2 = this.$route.params.propId2
        // 初始化属性1下拉框
        relConditionService.queryBaseInfoOfRelCondition({propId: this.propId1}, (res1) => {
          this.clssName1 = (res1.edmpEdmcName === null ? '' : res1.edmpEdmcName)
          this.class1NameEn = (res1.edmpEdmcNameEn === null ? '' : res1.edmpEdmcNameEn)
          this.classId1 = (res1.edmpEdmcId === null ? '' : res1.edmpEdmcId)
          this.getPropsbyClassId(res1.edmpEdmcId).then((resData) => {
            this.changeTreeToArray(resData)
            for (let i = 0; i < this.tempArray.length; i++) {
              if (this.propId1 === this.tempArray[i].id) {
                this.prop1Code = this.tempArray[i].casEdmpCode
                this.clssPropName1 = this.tempArray[i].casEdmpName
              }
            }
          })
          this.prevRelClasses.push({id: res1.edmpEdmcId, edmpEdmcName: this.clssName1, index: 0})//  初始化类1数据字典
          this.getPropsbyClassId(res1.edmpEdmcId).then((resData) => {
            this.prevRelProp[res1.edmpEdmcId] = resData
          })
          // 初始化属性2下拉框
          relConditionService.queryBaseInfoOfRelCondition({propId: this.propId2}, (res2) => {
            this.clssName2 = (res2.edmpEdmcName === null ? '' : res2.edmpEdmcName)
            this.class2NameEn = (res2.edmpEdmcNameEn === null ? '' : res2.edmpEdmcNameEn)
            this.classId2 = (res2.edmpEdmcId === null ? '' : res2.edmpEdmcId)
            this.getPropsbyClassId(res2.edmpEdmcId).then((resData) => {
              this.changeTreeToArray2(resData)
              for (let i = 0; i < this.tempArray2.length; i++) {
                if (this.propId2 === this.tempArray2[i].id) {
                  this.prop2Code = this.tempArray2[i].casEdmpCode
                  this.clssPropName2 = this.tempArray2[i].casEdmpName
                }
              }
            })
            if (res2.edmpEdmcId !== res1.edmpEdmcId) {
              this.prevRelClasses.push({id: res2.edmpEdmcId, edmpEdmcName: this.clssName2, index: 1})//  初始化类2数据字典
            }
            this.getPropsbyClassId(res2.edmpEdmcId).then((resData) => {
              this.prevRelProp[res2.edmpEdmcId] = resData
            })
            this.queryRelConditionAndConditions()
            this.queryVariableData()
          }).catch((error) => {
            this.AppUtils.showWarning('查询属性2信息失败' + error)
          })
        }).catch((error) => {
          this.AppUtils.showWarning('查询属性1信息失败' + error)
        })
      } else {
        this.AppUtils.showError('关联条件设置打开方式不正确')
      }
    },
    methods: {
      /**
       * 页面初始化加载属性关联条件及其相关条件列表
       */
      queryRelConditionAndConditions () {
        relConditionService.initRelCond({propId: this.propId1}, {propId2: this.propId2}, (res) => {
          if (res && res.relatedProperty && res.relatedProperty.prplId) {
            this.prplId = res.relatedProperty.prplId
            if (this.prplId !== '') {
              relConditionService.queryRelConditionAndConditions({prplId: this.prplId}, (res) => {
                let conditions = res.relatedConditions
                if (conditions.length > 0) {
                  //  初始化条件列表
                  conditions.sort(function (a, b) {
                    return a.cndrSeq > b.cndrSeq
                  })
                  console.log('conditions', conditions)
                  this.relConditionConditionsTableData = conditions
                }
                if (res.relatedProperty) {
                  //  初始化属性关联
                  let relData = res.relatedProperty
                  this.formularName = (relData.prplConditionName === 'null' ? '' : relData.prplConditionName)
                  this.formularText = (relData.prplConditionFormula === 'null' ? '' : relData.prplConditionFormula)
                  // this.prplId = relData.prplId
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
            }
          } else {
            // this.pageConfig.loadingText = '初始化关联条件失败，没有找到该属性！'
            this.pageConfig.isLoading = false
          }
        }, (error) => {
          this.pageConfig.loadingText = '初始化关联条件失败！' + error.message
          this.AppUtils.showWarning('初始化关联条件失败！' + error.message)
        })
      },
      /**
       * 变量模糊查询
       */
      queryVariableData () {
        let params = {varName: '', state: 'inusing'}
        relConditionService.queryVariableData(params, (res) => {
          this.variableData = res
        }, (error) => {
          this.AppUtils.showWarning('查询变量失败！' + error)
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
      changeTreeToArray2 (nodes) {
        nodes.forEach(
          (item, index, arr) => {
            this.tempArray2.push(item)
            if (item.children && item.children.length > 0) {
              this.changeTreeToArray2(item.children)
            }
          }
        )
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
       * 保存属性关联条件及其条件列表
       */
      saveAll () {
        this.$refs.editor.valid().then(() => {
          let relatedProperty = {
            // 属性编码属性名
            prplId: this.prplId,
            prplClassRelatedFrom: this.clssName1 + '.' + this.clssPropName1,
            prplClassRelatedTo: this.clssName2 + '.' + this.clssPropName2,
            prplConditionName: this.formularName,
            prplClass1NameEn: this.class1NameEn,
            prplProp1Code: this.prop1Code,
            prplClass2NameEn: this.class2NameEn,
            prplProp2Code: this.prop2Code,
            prplConditionFormula: this.formularText.trim(),
            prplConditionDesc: this.formularDescripText
          }
          this.postData = relatedProperty
          let propId = this.propId1 + '.' + this.propId2
          let relatedConditions = this.relConditionConditionsTableData.concat(this.deletedRelConditionConditionsTableData)
          relConditionService.saveRelCon({relatedProperty, relatedConditions, propId}, (res) => {
            this.prplId = res
            this.postData.prplId = res
            this.deletedRelConditionConditionsTableData = []
            this.tempArray = []
            this.tempArray2 = []
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
            this.AppUtils.showWarning('保存失败！' + error)
          })
        }).catch((error) => {
          console.log('error', error)
          // 取消保存
        })
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
            dataClone.isenable = '0'  //  修改可用标识，0：不可用  1：可用
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
        return '未知'
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
        let title = '关联条件设置-新增条件'
        let mode = 'add'
        this.openCondEditDialog({index, title, mode})
      },
      /**
       * 编辑条件
       */
      editCond (index) {
        let title = '关联条件设置-编辑条件'
        let mode = 'edit'
        let cond = this.relConditionConditionsTableData[index]
        this.openCondEditDialog({index, title, cond, mode})
      },
      /**
       * 打开新增/编辑条件弹框
       */
      openCondEditDialog ({index, title, cond, mode}) {
        this.OpenGlobalDialog({
          name: 'relConditionCondEdit',
          component: RelConditionCondEdit,
          title: title,
          props: {
            index: index,
            mode: mode,
            cond: cond,
            prevRelClasses: this.prevRelClasses,
            variableData: this.variableData,
            prevRelProp: this.prevRelProp
          },
          options: {
            title: title,
            top: '20%',
            customClass: 'subpage-dlg dialog-width-s'
          },
          events: {
            saveCond: this.saveCond,
            close: () => {
              this.CloseGlobalDialog('relConditionCondEdit')
            }
          }
        })
      },
      /**
       * 保存条件
       */
      saveCond ({index, condData}) {
        console.log(condData)
        condData.cndrPropRelatedId = this.prplId
        this.$set(this.relConditionConditionsTableData, index, condData)
      },
      postMsg (obj) {
        console.info('obj', obj)
        if (window !== window.parent) {
          window.parent.postMessage(obj, '*')
        }
        if (window.opener) {
          window.opener.postMessage(obj, '*')
          window.opener = null
          window.open(' ', '_self')
          window.close()
        }
      },
      delRelCond  () {
        if (!this.prplId) {
          console.log('delRelCond fail......')
          this.AppUtils.showSuccess('还没有关联条件呢,请添加后在重试...')
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
            console.log(error)
            this.AppUtils.showErrorMsg(error)
          })
        }).catch(() => {
        })
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
    margin-left: auto;
    margin-right: auto;
    padding-left: 10px;
    padding-right: 10px;
    border: 1px solid #d1dbe5;
    border-radius: 4px;
    background-color: #f9f9f9;
    overflow: hidden;
    box-shadow: 0 2px 4px 0 rgba(0, 0, 0, .12), 0 0 6px 0 rgba(0, 0, 0, .04);
  }

  .relcondition-page-header {
    position: relative;
    width: 100%;
    height: 40px;
  }

  .relcondition-page-header .page-header__page-title {
    position: absolute;
    color: #000;
    font-size: 15px;
    font-weight: bold;
    line-height: 15px;
    margin-top: 12px;
    margin-bottom: 0px;
    padding-left: 5px; /* 与顶部导航的padding-left一致 */
  }

  .condition-editor-range {
    position: relative;
    margin-top: 10px;
    margin-bottom: 10px;
    padding-top: 2px;
    padding-right: 10px;
    padding-left: 10px;
    padding-bottom: 2px;
    border: 1px solid gray;
    min-height: 25px; /* textarea 1行高度一致 */
    border-radius: 4px;
    font-size: 14px;
  }

  .condition-editor-descrip {
    position: relative;
    margin-top: 10px;
    margin-bottom: 10px;
    padding-top: 2px;
    padding-right: 10px;
    padding-left: 10px;
    padding-bottom: 2px;
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
    padding: 0;
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
