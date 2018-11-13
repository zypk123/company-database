<template>
  <div class="formula-validator">
    <el-row>
      <el-col :span="24">
        <div class="formula-label-div">
          <div class="formula-return-type">{{formatDataType(formula.returnType)}}</div>
          <div class="formula-label" >{{formula.label}}=</div>
        </div>
      </el-col>

      <el-col :span="24" class="common-vgap">
        <div class="formula-display-div" style=""
          v-html="formula.formulaStrHtml"
        >
        </div><!-- /.formula-editor -->
      </el-col>

      <el-col :span="24">
        <div class="lable-row">
          公式变量
        </div>
      </el-col>

      <el-col :span="24" class="common-vgap">
        <el-table :data="formulaVarDatas"
          stripe>
          <el-table-column type="index" label="序号"
            halign='center' align='center' width="70" >
          </el-table-column>

          <el-table-column prop="varLabel" label="变量名称"
            halign='center'  align='center' width="180">
          </el-table-column>

          <el-table-column prop="valDataType" label="变量数据类型"
            :formatter="tableDataTypeFormat"
            halign='center' align='center' width="180">
          </el-table-column>

          <el-table-column prop="varDataVal" label="变量值"
            halign='center' align='center'>
            <template scope="scope">
              <div class="table-cell-content" :class="{'is-error': scope.row.extraInfo.validateInfo.varDataValIsError,'is-success':scope.row.extraInfo.validateInfo.varDataValIsValid}">
                <el-input class="validate-field" type="text" v-model="scope.row.varDataVal" auto-complete="off" :placeholder='scope.row.varDataValExample'></el-input>
                <div class="table-cell-content__error" v-if="scope.row.extraInfo.validateInfo.varDataValIsError">
                  {{scope.row.extraInfo.validateInfo.varDataValErrMsg}}
                </div>
              </div>
            </template>
          </el-table-column>
        </el-table>
      </el-col>

      <el-col :span="24" class="common-vgap">
        <el-button type="primary" @click="checkValidator">校验</el-button>
      </el-col>

      <el-col :span="24"  class="common-vgap">
        <div class="lable-row">
          公式结果
        </div>
      </el-col>

      <el-col :span="24" class="common-vgap">
        <div class="formula-display-div validate-result" style=""
          :class="{'is-success': pageConfig.isValidateSuccess, 'is-error': pageConfig.isValidateError}"
          v-html="formulaValidateResultHtml"
          v-loading="pageConfig.isLoadingValidateResult"
          :element-loading-text="pageConfig.loadingValidateResultText"
        >
        </div><!-- /.formula-editor -->
      </el-col>

    </el-row>
  </div>
</template>
<script>
import { formulaService } from '@/api'
import appMixin from '@/mixin/app-mixin'
import formatMixin from '@/mixin/data-format-mixin'
import { parseFormula } from '@/mixin/parse-formula-mixin'
import _ from 'lodash'
export default {
  name: 'formulaValidator',
  mixins: [appMixin, formatMixin],
  props: ['initData'],
  data () {
    return {
      formula: {},
      formulaHtmlStr: '',
      formulaVarDatas: [],
      formulaValidateResultHtml: '',
      pageConfig: {
        isValidateSuccess: false,
        isValidateError: false,
        isLoadingValidateResult: false,
        loadingValidateResultText: '正在加载校验结果'
      },
      jsFuncs: ['ABS', 'AVG', 'COUNT', 'INT', 'MAX', 'MIN', 'MOD', 'POWER', 'PRODUCT', 'ROUND',
        'SQRT', 'CONTAINS', 'EXACT', 'ISEMPTY', 'LEN', 'LOWER', 'MID',
        'NUMBER', 'REPLACE', 'SEARCH', 'STRING', 'TRIM', 'UPPER', 'VALUE', 'NOW', 'TODAY']
    }
  },
  created () {
    let formulaVarDatasTemp = []
    let formulaHtmlStrTemp = ''
    this.formula = this.initData.formula
    this.formula.formulaItems.forEach(
      (item, itemIndex, arr) => {
        if (item.type === 'var' || item.type === 'prop') {
          item.returnType = item.returnType.toLowerCase() || item.returnType
          let rowData = {
            varType: item.type,
            varVal: item.val,
            varLabel: item.label,
            valDataType: item.returnType,
            varDataVal: '',
            varDataValExample: this.getExmpleByretType(item.returnType),
            extraInfo: {
              validateInfo: {
                varDataValIsError: false,
                varDataValErrMsg: ''
              }
            }
          }
          formulaVarDatasTemp.push(rowData)
        }
      }
    )
    // 去除数组中变量名或属性名相同的对象
    this.formulaVarDatas = _.uniqWith(formulaVarDatasTemp, _.isEqual)
    // console.log(this.formulaVarDatas)
    this.formulaHtmlStr = formulaHtmlStrTemp
  },
  methods: {
    checkValidator () {
      this.pageConfig.isValidateSuccess = false
      this.pageConfig.isValidateError = false
      let allValid = true
      let formulaItemsValues = []

      for (let item of this.formulaVarDatas) {
        let itemValue = {
          varType: item.varType, // prop/var
          varVal: item.varVal, // propid. varname等
          dataValType: item.valDataType, // num, text 等
          dataVal: item.varDataVal // 输入的参数值
        }

        item.extraInfo.validateInfo.varDataValIsValid = false
        item.extraInfo.validateInfo.varDataValIsError = false
        item.extraInfo.validateInfo.varDataValErrMsg = ''
        item.varDataVal = item.varDataVal.trim()

        if (item.varDataVal === '') {
          allValid = false
          item.extraInfo.validateInfo.varDataValIsError = true
          item.extraInfo.validateInfo.varDataValErrMsg = '变量值不能为空'
          continue
        }

        if (item.valDataType === 'varchar') {
          let str = item.varDataVal || ''
          str = str.replace(/'/, '"')
          itemValue.dataVal = `'${str}'`
        }

        formulaItemsValues.push(itemValue)

        item.extraInfo.validateInfo.varDataValIsValid = true

        if (item.valDataType === 'date') {
          // 后台要求所以的日期都是时间戳格式
          if (!this.Utils.validate(item.varDataVal, 'datetime')) {
            allValid = false
            item.extraInfo.validateInfo.varDataValIsError = true
            item.extraInfo.validateInfo.varDataValErrMsg = '变量值不是一个有效的日期格式'
            continue
          }
          item.extraInfo.validateInfo.varDataValIsValid = true
        }
        // text  set assemble instantiate varchar float object const
        if (item.valDataType === 'logic') {
          if (!this.Utils.validate(item.varDataVal, 'logic_str')) {
            allValid = false
            item.extraInfo.validateInfo.varDataValIsError = true
            item.extraInfo.validateInfo.varDataValErrMsg = '变量值不是一个有效的不是值[true/false]'
            continue
          }
          item.extraInfo.validateInfo.varDataValIsValid = true
          continue
        }

        if (item.valDataType === 'int') {
          if (!this.Utils.validate(item.varDataVal, 'integer')) {
            allValid = false
            item.extraInfo.validateInfo.varDataValIsError = true
            item.extraInfo.validateInfo.varDataValErrMsg = '变量值不是一个有效的整数'
            continue
          }
          item.extraInfo.validateInfo.varDataValIsValid = true
          continue
        }

        if (item.valDataType === 'num' ||
          item.valDataType === 'decimal') {
          // FIXME 整数数字浮点数的校验规则需要完善
          if (!this.Utils.validate(item.varDataVal, 'number')) {
            allValid = false
            item.extraInfo.validateInfo.varDataValIsError = true
            item.extraInfo.validateInfo.varDataValErrMsg = '变量值不是一个有效的数字'
            continue
          }
          item.extraInfo.validateInfo.varDataValIsValid = true
          continue
        }
      }
      // console.info(formulaItemsValues)

      if (allValid) {
        this.pageConfig.isLoadingValidateResult = true
        let funcs = []
        this.formula.formulaItems.forEach(
          (item, index, arr) => {
            if (item.type === 'func') {
              funcs.push(item.label)
            }
          }
        )
        // 两个数组求补集
        let temp = _.difference(funcs, this.jsFuncs)
        console.log(temp)
        // 判断前后端校验的条件
        // FIXME 公式为后端校验计算，前端校验计算后续开发
        if (!_.isEmpty([])) {
          // 前端校验
          let params = {
            formulaId: this.formula.formulaId,
            formulaItems: this.formula.formulaItems,
            formulaItemsStr: this.formula.formulaItemsStr,
            formulaStr: this.formula.formulaStr,
            formulaItemsValues: formulaItemsValues
          }
          parseFormula(params).then((resData) => {
            console.log(resData)
            if (resData === undefined) {
              this.pageConfig.isValidateError = true
            } else {
              this.pageConfig.isValidateSuccess = true
              this.formulaValidateResultHtml = resData
            }
          }).catch((err) => {
            this.pageConfig.loadingValidateResultText = err.message
            this.pageConfig.isValidateError = true
            this.AppUtils.showError(err.message)
          })
          this.$nextTick(
            this.pageConfig.isLoadingValidateResult = false
          )
        } else {
          // 后端校验
          let params = {
            formulaId: this.formula.formulaId,
            formulaItems: this.formula.formulaItems,
            formulaItemsStr: this.formula.formulaItemsStr,
            formulaStr: this.formula.formulaStr,
            formulaItemsValues: formulaItemsValues
          }
          formulaService.checkFormulaValid(
            params,
            (resData) => {
              console.log(resData)
              if (resData.retCode) {
                if (resData.retCode === 4000) {
                  this.pageConfig.isValidateError = true
                }
                this.formulaValidateResultHtml = resData.data
              } else {
                // retCode ===  1 返回正确的校验结果
                this.pageConfig.isValidateSuccess = true
                this.formulaValidateResultHtml = resData
              }
            },
            (error) => {
              this.pageConfig.isValidateError = true
              this.AppUtils.showError(error.message)
              // this.formulaValidateResultHtml = error.message
            },
            () => {
              this.$nextTick(
                this.pageConfig.isLoadingValidateResult = false
              )
            }
          )
        }
      }
    },
    getExmpleByretType (returnType) {
      if (returnType === 'text') {
        return '例: 文本'
      } else if (returnType === 'int') {
        return '例: ' + 2008
      } else if (returnType === 'varchar') {
        return '例: huntkey'
      } else if (returnType === 'boolean') {
        return '例: ' + true
      } else if (returnType === 'decimal') {
        return '例: ' + 20.08
      } else if (returnType === 'number') {
        return '例: ' + 2008 + '或' + 20.08
      } else if (returnType === 'date') {
        return '例: ' + '2008-10-01 08:00:00'
      } else {
        return returnType
      }
    }
  }
}
</script>
<style scoped>
/** .formula-label-div begin **/
.formula-label-div {
  height: 35px;
  /*border:1px solid red;*/
}

.formula-return-type {
  display: inline-block;
  padding-top:6px;
  padding-right: 12px;
  padding-left: 12px;
  padding-bottom: 6px;
  font-weight: bold;
}

.formula-label {
  display: inline-block;
  padding-top: 6px;
  font-weight: bold;
}
/** .formula-label-div end **/
.validate-result {
  min-height: 100px;
}
</style>
