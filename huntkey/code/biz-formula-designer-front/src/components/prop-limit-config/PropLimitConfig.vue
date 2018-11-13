<template>
  <div class="prop-limit-config" v-loading.fullscreen.lock="pageConfig.isLoading" :element-loading-text="pageConfig.loadingText">
    <el-row>
      <el-col class="common-vgap" :span="24">
        <div >
          <el-button type="primary" @click="checkSave">保　存</el-button>
          <el-button type="danger" @click="delPropLimitConfig" :disabled="this.prprId === null || this.prprId === ''">删除</el-button>
        </div>
      </el-col>
    </el-row><!-- /.el-row 顶部标题栏 -->

    <el-row>
      <el-col class="common-vgap" :span="24">
        <span>属性:</span><span class="prop-full-lable">{{className + '.' + classPropName}}</span>
      </el-col><!-- /.el-col-24-->

      <el-col class="common-vgap" :span="24">
        <span>条件列表:</span>
        <div class="float-right">
          <el-button type="primary" size="small" icon="plus"
          @click="addNewCond">添加</el-button>
        </div>
      </el-col><!-- /.el-col-24-->

      <el-col class="common-vgap" :span="24">
        <el-table
          :data="propLimitConditionsTableData"
          border
          style="width: 100%">

          <el-table-column align='center' width="80" label="序号">
            <template scope="scope">
              <span class="condition-index-span">
                <el-button type="text" @click="insertSeq(scope.row.cndtSeq)">[ {{scope.row.cndtSeq}} ]</el-button>
              </span>
            </template>
          </el-table-column>

          <el-table-column header-align="center" align="center"
            prop="cndtProp"
            label="属性"
            width="180">
          </el-table-column>

          <el-table-column header-align="center" align="center"
            prop="cndtOperate"
            label="条件"
            width="120"
            :formatter="tableCndrOperateFormat">
          </el-table-column>

          <el-table-column header-align="center" align="center"
            prop="cndtValue"
            label="值">
          </el-table-column>

          <el-table-column header-align="center" align="center"
            label="操作"
            width="180">
            <template scope="scope">
              <el-button size="small"
                @click="editCond(scope.$index)"
                type="text">编辑
              </el-button>
              <el-button size="small"
              @click="removeLine(scope.$index)"
              type="text">删除
              </el-button>
            </template>
          </el-table-column>

        </el-table>
      </el-col><!-- /.el-col-24-->

      <el-col class="common-vgap" :span="24">
      <cond-formula-editor ref="editor"
      :editable="true"
      :initFormulaName="formularName"
      :initFormulaText="formularText"
      :condsDescHtmlArr="condsDescHtmlArr"
      :condsDescTextArr="condsDescTextArr"
      ></cond-formula-editor>
      </el-col><!-- /.el-col-24-->
    </el-row><!-- /.el-row-->
  </div>
</template>
<script>
import { propLimitService, formulaService } from '@/api'
import { mapGetters } from 'vuex'
import PropConditionEdit from './PropConditionEdit'
import CondFormulaEditor from '@/components/common/cond-formula-editor/CondFormulaEditor'

export default {
  name: 'propLimitConfig',
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
      //  传入参数
      propertyId: '',

      prprId: '',
      recdId: '',
      className: '',
      classPropName: '',
      cndtPropOriginCode: '',

      // 变量参数
      formularName: '',
      formularText: '',
      formularHtml: '',
      formularDescripText: '',

      // 控制参数
      loading: false,  //  变量下拉加载控制
      lastSelectedRange: null,  //  选择范围（光标控制使用）
      saveBtDisable: false,  //  保存按钮是否可用
      sourceMappingType: '2',  //  1:属性公式、2:属性限值、3:关联条件
      // 数据参数
      operations: {AND: '并且', OR: '或者'},  //  操作符
      propLimitConditionsTableData: [],       //  数据展示区
      deletedPropLimitConditionsTableData: [],  //  数据删除提交区（不包含 添加后删除）
      variableData: [],
      tempArray: []
    } // end of return
  }, // end of data

  mounted () {
    // this.queryLimitAndConditions()
    // this.queryVariableData()
    if (this.$route.name === 'propLimitConfig') {
      this.propertyId = this.$route.params.propId
    } else {
      this.AppUtils.showError('属性限值页面打开方式不正确')
    }
    if (this.propertyId !== '') {
      this.analyzeInputParams()
    } else {
      this.AppUtils.showError('属性限值页面入参不正确，初始化异常!')
    }
  },
  methods: {
    analyzeInputParams () {
      this.pageConfig.isLoading = true
      //  解析入参 TODO
      console.info('analyzeInputParams')
      propLimitService.queryBaseInfoOfProp({propertyId: this.propertyId}, (res) => {
        if (res !== null) {
          console.log(res)
          this.className = res.edmpEdmcName
          this.getPropsbyClassId(res.edmpEdmcId).then((resData) => {
            console.log('resData', resData)
            this.changeTreeToArray(resData)
            console.log('tempArray', this.tempArray)
            for (let i = 0; i < this.tempArray.length; i++) {
              if (this.propertyId === this.tempArray[i].id) {
                this.classPropName = this.tempArray[i].casEdmpName
                console.log('ttttttttttttttt', this.classPropName)
              }
            }
            this.cndtPropOriginCode = res.id
            // this.prprId = (res.edmpValueLimit === null ? '' : res.edmpValueLimit)
            console.log('fffffffffffffff', this.classPropName)
            this.formularName = res.edmpEdmcName + '.' + this.classPropName + '-' + '属性限值条件'
            this.$refs.editor.formulaInfo.formularName = this.formularName
            this.insertLimitAndUpdateProp()
            this.pageConfig.isLoading = false
          })
        } else {
          this.pageConfig.loadingText = '初始化属性失败!'
        }
      }, (error) => {
        this.pageConfig.loadingText = '初始化属性失败！' + error.message
        this.AppUtils.showWarning('初始化属性失败！' + error.message)
      })
    },
/** >>>>>>>>>>>>>>>>>>查询区  ******/
    insertLimitAndUpdateProp () {
      // 根据prprId决定是否新增一条属性限值并且保存在本地映射表中
      console.info('=========><<<<-insertLimitAndUpdateProp->>>>执行')
      let params = {sourceMappingId: this.propertyId, sourceMappingType: this.sourceMappingType}
      propLimitService.queryRecord(params, (res) => {
        console.info('映射表结果：', res)
        if (res === null) {
          this.insertUpdate()
        } else {
          this.prprId = (res.insideId === null ? '' : res.insideId)
          this.recdId = (res.recdId === null ? '' : res.recdId)
          let record = {
            recdId: this.recdId
          }
          propLimitService.updateRecordMapping(record, (res) => {
            console.info(res)
            this.queryLimitAndConditions()
            this.queryVariableData()
          }, (error) => {
            this.AppUtils.showWarning('updateRecordMapping接口调用失败！' + error)
          })
        }
      }, (error) => {
        this.AppUtils.showWarning('查询映射表失败！' + error)
      })
    },
    insertUpdate () {
      let tplPropertyLimit = {
        prprPropMata: this.className,
        prprPropDisplay: this.classPropName,
        prprConditionName: this.formularName,
        idenable: 1
      }
      propLimitService.insertPropLimit(tplPropertyLimit, (insertRes) => {
        this.prprId = insertRes
        let record = {
          sourceName: 'modeler',
          sourceMappingId: this.propertyId,
          sourceMappingType: this.sourceMappingType,
          insideType: this.sourceMappingType,
          insideId: this.prprId
        }
        propLimitService.addRecordMapping(record, (res) => {
          console.info(res)
          this.queryVariableData()
        }, (error) => {
          this.AppUtils.showWarning('addRecordMapping接口调用失败！' + error)
        })
      }, (error) => {
        this.AppUtils.showWarning('insertPropLimit接口调用失败！' + error)
      })
    },
    queryLimitAndConditions () {
      // 查询属性限值和属性关联条件
      console.info('=========>查询属性限值和属性关联条件方法<<<<-queryLimitAndConditions->>>>执行' + this.prprId)
      if (this.prprId !== '') {
        propLimitService.queryLimitAndConditions({
          prprId: this.prprId
        }, (res) => {
          console.log(res)
          if (res.tplConditions.length > 0) {
            //  初始化条件列表
            res.tplConditions.sort(function (a, b) {
              return a.cndtSeq > b.cndtSeq
            })
            this.propLimitConditionsTableData = res.tplConditions
          } else {
            this.propLimitConditionsTableData = []
          }
          if (res.tplPropertyLimit) {
             //  初始化属性限制
            let limitData = res.tplPropertyLimit
            this.className = limitData.prprPropMata
            // this.classPropName = limitData.prprPropDisplay
            this.formularText = (limitData.prprConditionFormula === null ? '' : limitData.prprConditionFormula)
            this.formularName = (limitData.prprConditionName === null ? '' : limitData.prprConditionName)
            this.prprId = limitData.prprId
            this.$refs.editor.formulaInfo = {
              formulaText: this.formularText,
              formulaName: this.formularName
            }
            this.$refs.editor.formulaHtml = this.formularText || ''
            //  this.$refs.editor.analyzeTextToFormularHtml(this.formularText)
          } else {
            this.AppUtils.showWarning('queryLimitAndConditions查询=tplPropertyLimit获取失败，这个属性限值ID是否正确====>' + this.prprId)
          }
          // this.addNewDataRow()
        }, (error) => {
          this.AppUtils.showWarning('查询失败！' + error)
        })
      } else {
        // console.log('fsdffsf')
      }
    },
    queryVariableData () {
      // 查询所有变量
      // console.info('=========>查询所有变量方法<<<<-queryVariableData->>>>执行')
      let params = {varName: '', state: 'inusing'}
      propLimitService.queryVariableData(params, (res) => {
        this.variableData = res
        this.variableOptions = this.Utils.cloneDeep(res)
        this.pageConfig.isLoading = false
      }, (error) => {
        this.AppUtils.showWarning('查询变量失败！' + error)
      })
    },

/**  >>>>>>>>>>>>>>>>>>end of 查询区  ******/

/**  >>>>>>>>>>>>>>>>>>保存区  ******/

    saveAll () {
      // 保存属性限值和属性关联条件
      console.info('=========>保存属性限值和属性关联条件方法<<<<-saveAll->>>>执行')
      let tplCondition = this.propLimitConditionsTableData.concat(this.deletedPropLimitConditionsTableData)
      let tplConditions = this.Utils.cloneDeep(tplCondition)
      console.info(tplConditions)
      let tplPropertyLimit = {
        prprPropMata: this.className,
        prprPropDisplay: this.classPropName,
        prprConditionFormula: this.formularText.trim(),
        prprConditionName: this.formularName,
        prprConditionDesc: this.formularDescripText.trim(),
        prprId: this.prprId
      }
      this.postData = tplPropertyLimit
      propLimitService.save({tplPropertyLimit, tplConditions}, (res) => {
        console.info('res', res)
        // 直接pos信息然后关闭页面了, 不需要再刷新什么了.
        // console.info('res=============>' + res)
        // this.prprId = res
        // this.deletedPropLimitConditionsTableData = []
        // this.queryLimitAndConditions()
        this.$message({
          type: 'success',
          message: '保存成功!'
        })
        let obj = {
          eventType: 'PropLimit',
          operType: 'save',
          eventData: this.postData
        }
        this.postMsg(obj)
      }, (error) => {
        this.AppUtils.showWarning('保存失败！' + error)
      })
    },
    checkSave () {
      this.$refs.editor.valid().then(
        (resData) => {
          this.formularName = resData.formulaName
          this.formularText = resData.formulaText
          this.formularDescripText = resData.formulaDesc
        }
      ).then(() => {
        this.saveAll()
      })
    },
    delPropLimitConfig  () {
      if (!this.prprId) {
        this.AppUtils.showError('条件公式尚未保存')
      }
      this.$confirm('是否删除该限值公式？', '提示', {
        confirmButtonText: '确定',
        canceButtonText: '取消',
        type: 'warning'
      }).then(() => {
        let prprId = this.prprId
        propLimitService.deletePropLimit({prprId}).then(() => {
          let eventData = {
            prprId: this.prprId
          }
          let obj = {
            eventType: 'PropLimit',
            operType: 'delete',
            eventData: eventData
          }
          this.postMsg(obj)
          this.AppUtils.showSuccess('限值公式已删除.')
        }).catch((error) => {
          console.log(error)
          this.AppUtils.showErrorMsg(error)
        })
      }).catch(() => {})
    },
/**  >>>>>>>>>>>>>>>>>>end of 保存区  ******/

/**  >>>>>>>>>>>>>>>>>>条件列表  ******/
    addNewCond () {
      let index = this.propLimitConditionsTableData.length
      let title = '条件列表-新增'
      let mode = 'add'
      this.openCondEditDialog({index, title, mode})
    },
    editCond (index) {
      let title = '条件列表-编辑'
      let mode = 'edit'
      let cond = this.propLimitConditionsTableData[index]
      this.openCondEditDialog({index, title, cond, mode})
    },
    openCondEditDialog ({index, title, cond, mode}) {
      this.OpenGlobalDialog({
        name: 'PropConditionEdit',
        component: PropConditionEdit,
        props: {
          className: this.className,
          classPropName: this.classPropName,
          classPropId: this.cndtPropOriginCode,
          variableData: this.variableData,
          index: index,
          mode: mode,
          cond: cond
        },
        options: {
          title: title,
          top: '20%',
          customClass: 'subpage-dlg dialog-width-s'
        },
        events: {
          saveCond: this.saveCond,
          close: () => {
            this.CloseGlobalDialog('PropConditionEdit')
          }
        }
      })
    },
    saveCond ({index, condData}) {
      condData.cndtPropClssId = this.prprId
      this.$set(this.propLimitConditionsTableData, index, condData)
    },
    removeLine (index) {
      // 条件列表行删除
      // console.info('=========>条件列表行删除<<<<-removeLine->>>>执行')
      this.$confirm('是否确认删除?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        let cndtId = this.propLimitConditionsTableData[index].cndtId
        if (cndtId) {   //  如果删除行存在主键Id，则保存至删除区，否则直接删除
          let dataClone = this.Utils.cloneDeep(this.propLimitConditionsTableData[index])
          dataClone.isenable = '0'  //  修改可用标识，0：不可用  1：可用
          this.deletedPropLimitConditionsTableData.push(dataClone)   //  添加到删除提交区
          this.propLimitConditionsTableData.splice(index, 1)
        } else {
          this.propLimitConditionsTableData.splice(index, 1)
        }
        console.info(this.deletedPropLimitConditionsTableData)
        //  每次删除后重新排序,TODO 根据需要更改
        for (let i = 0; i < this.propLimitConditionsTableData.length; i++) {
          this.propLimitConditionsTableData[i].cndtSeq = i + 1
        }
        //  每次删除后，重置公式
        this.$refs.editor.clearFormula()
      }).catch(() => {
        // 取消删除
      })
    },
    insertSeq (seq) {
      this.$refs.editor.insertSeq(seq)
    },
/**  >>>>>>>>>>>>>>>>>>end of 条件列表   ******/

/**  >>>>>>>>>>>>>>>>>>解析或者公共方法  ******/
    getCompareTypeDict (value) {
      // 根据操作符 =,>,<等获取字典说明
      for (let i = 0; i < this.compareTypeDict.length; i++) {
        if (value === this.compareTypeDict[i].val) {
          return this.compareTypeDict[i].label
        }
      }
      return '未知操作符'
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
    getVrntById (cndtValueOriginCode) {
      // 获取变量值
      for (var i = 0; i < this.variableData.length; i++) {
        if (cndtValueOriginCode === this.variableData[i].vrntId) {
          return this.variableData[i].vrntVarName
        }
      }
      return '未知'
    },
    queryVrntOption (query) {
      // 获取变量搜索下拉
      if (query !== '') {
        this.loading = true
        setTimeout(() => {
          this.loading = false
          this.variableOptions = this.variableData.filter(item => {
            return item.vrntVarName.toLowerCase().indexOf(query.toLowerCase()) > -1
          })
        }, 100)
      } else {
        this.variableOptions = this.variableData
      }
    },
    postMsg (obj) {
      // console.info('postMsg')
      // console.info(obj)
      if (window !== window.parent) {
        window.parent.postMessage(obj, '*')
      }
      if (window.opener) {
        window.opener.postMessage(obj, '*')
        window.opener = null
        window.open(' ', '_self')
        window.close()
      }
    } // end of postMsg
/**  >>>>>>>>>>>>>>>>>>end of 解析或者公共方法  ******/

  },
  computed: {
    ...mapGetters({
      compareTypeDict: 'getCndtOperatorDict',
      compareTargetTypeDict: 'getCndtValueTypeDict4PropLimit'
    }),
    condsDescHtmlArr () { // 条件的html
      let retArr = []
      // console.info('condsDescHtmlArr=====================')
      for (let i = 0; i < this.propLimitConditionsTableData.length; i++) {
        let line = `<div class="formula-item formula-item-prop" contenteditable="false">${this.propLimitConditionsTableData[i].cndtProp}</div>`
        line += `<div class="formula-item formula-item-logic-oper" contenteditable="false">${this.getCompareTypeDict(this.propLimitConditionsTableData[i].cndtOperate)}</div>`
        line += `<div class="formula-item" contenteditable="false">${this.propLimitConditionsTableData[i].cndtValue}</div>`
        retArr.push(line)
      }
      return retArr
    },
    condsDescTextArr () { // 条件的html描述
      let retArr = []
      // console.info('condsDescTextArr=====================')
      for (let i = 0; i < this.propLimitConditionsTableData.length; i++) {
        let line = `${this.propLimitConditionsTableData[i].cndtProp} ${this.getCompareTypeDict(this.propLimitConditionsTableData[i].cndtOperate)} ${this.propLimitConditionsTableData[i].cndtValue}`
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
.prop-limit-config {
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
.limit-page-header {
  position: relative;
  width: 100%;
  height: 40px;
}

.limit-page-header .page-header__page-title {
  position: absolute;
  color:#000;
  font-size:15px;
  font-weight: bold;
  line-height: 15px;
  margin-top: 12px;
  margin-bottom: 0px;
  padding-left: 5px; /* 与顶部导航的padding-left一致 */
}
.prop-limit-config .prop-full-lable {
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
</style>
