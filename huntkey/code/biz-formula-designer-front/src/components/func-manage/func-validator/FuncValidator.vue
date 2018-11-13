<template>
  <div class="formula-validator">
    <el-row>
      <el-col :span="24">
        <div class="lable-row">
          函数名称: <strong>{{funcDescInfo.funName}}</strong>
        </div>
      </el-col>

      <el-col :span="24">
        <div class="lable-row">
          函数描述:
        </div>
      </el-col>

      <el-col :span="24" >
        <div class="formula-display-div" style=""
        v-html="funcDescInfo.funDesc"
        >
        </div><!-- /.formula-editor -->
      </el-col>

      <el-col :span="24">
        <div class="lable-row">
          函数输入参数
        </div>
      </el-col>

      <el-col :span="24" class="common-vgap">
        <el-table :data="funcParamsDatas"
          stripe>
          <el-table-column type="index" label="序号"
            halign='center' align='center' width="70" >
          </el-table-column>

          <el-table-column prop="valDataType" label="参数类型"
            :formatter="tableDataTypeFormat"
            halign='center' align='center' width="180">
          </el-table-column>

          <el-table-column prop="varDataVal" label="参数值"
            halign='center' align='center'>
            <template scope="scope">
              <div class="table-cell-content" :class="{'is-error': scope.row.extraInfo.validateInfo.varDataValIsError,'is-success':scope.row.extraInfo.validateInfo.varDataValIsValid}">
                <el-input class="validate-field" type="text"
                  v-model="scope.row.varDataVal" auto-complete="off"
                   :placeholder='scope.row.varDataValExample'>
                </el-input>
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
          函数返回值
        </div>
      </el-col>

      <el-col :span="24" class="common-vgap">
        <div class="formula-display-div validate-result" style=""
          :class="{'is-success': pageConfig.isValidateSuccess, 'is-error': pageConfig.isValidateError}"
          v-html="funcValidateResultHtml"
          v-loading="pageConfig.isLoadingValidateResult"
          :element-loading-text="pageConfig.loadingValidateResultText"
        >
        </div><!-- /.formula-editor -->
      </el-col>

    </el-row>
  </div>
</template>
<script>
import { funcService } from '@/api'
import formatMixin from '@/mixin/data-format-mixin'
export default {
  name: 'formulaValidator',
  mixins: [formatMixin],
  props: ['initData'],
  data () {
    return {
      funcDescInfo: {
        funName: ''
      },
      funcParamsDatas: [],
      funcValidateResultHtml: '',
      pageConfig: {
        isValidateSuccess: false,
        isValidateError: false,
        isLoadingValidateResult: false,
        loadingValidateResultText: '正在加载校验结果'
      }
    }
  },
  created () {
    this.funcDescInfo = this.initData.funcDescInfo
    // console.info(this.initData.funcDescInfo)
    if (this.funcDescInfo.funDesc) {
      this.funcDescInfo.funDesc = this.funcDescInfo.funDesc.replace(/\n/g, '<br>')
    }
    // console.info(this.funcDescInfo)
    let funcParamsTemp = []
    this.funcDescInfo.paramTypes.forEach(
      (item, itemIndex, arr) => {
        let rowData = {
          valDataType: item.types[0].toLowerCase(),
          varDataVal: '',
          varDataValExample: this.getExmpleByretType(item.returnType),
          extraInfo: {
            validateInfo: {
              varDataValIsError: false,
              varDataValErrMsg: ''
            }
          }
        }
        funcParamsTemp.push(rowData)
      }
    )
    this.funcParamsDatas = funcParamsTemp
  },
  methods: {
    checkValidator () {
      this.pageConfig.isValidateSuccess = false
      this.pageConfig.isValidateError = false
      let allValid = true
      let formulaItems = []
      let formulaStr = `${this.funcDescInfo.funName}(`
      let funcParamsValues = []
      console.info('this.funcDescInfo')
      console.info(this.funcDescInfo)
      formulaItems.push({type: 'func', val: this.funcDescInfo.funName, label: this.funcDescInfo.funName, returnType: this.funcDescInfo.funType})
      formulaItems.push({type: 'str', val: '('})

      let index = 0
      for (let item of this.funcParamsDatas) {
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

        let itemValue = {
          varType: 'prop', // prop/var
          varVal: 'propppppppppppppp' + index, // propid. varname等
          dataValType: item.valDataType, // num, text 等
          dataVal: item.varDataVal // 输入的参数值
        }

        if (item.valDataType === 'varchar') {
          let str = item.varDataVal || ''
          str = str.replace(/'/, '"')
          itemValue.dataVal = `'${str}'`
        }

        funcParamsValues.push(itemValue)

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
          item.valDataType === 'float') {
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
        formulaItems.push({type: 'prop', val: 'propppppppppppppp' + index})
        formulaStr += '#prop_propppppppppppppp' + index + '#'
        index++
        console.info('index' + index)
        if (index < this.funcParamsDatas.length) {
          formulaItems.push({type: 'str', val: ','})
          formulaStr += ','
        }
      }
      formulaStr += ')'
      formulaItems.push({type: 'str', val: ')'})

      if (allValid) {
        this.pageConfig.isLoadingValidateResult = true

        let params = {
          formulaItems: formulaItems,
          formulaStr: formulaStr,
          formulaItemsValues: funcParamsValues
        }
        console.info(params)
        funcService.checkFuncValid(
          params,
          (resData) => {
            if (resData.retCode) {
              if (resData.retCode === 4000) {
                this.pageConfig.isValidateError = true
              }
              this.funcValidateResultHtml = resData.data
            } else {
              // retCode ===  1 返回正确的校验结果
              this.pageConfig.isValidateSuccess = true
              this.funcValidateResultHtml = resData
            }
          },
          (error) => {
            this.pageConfig.isValidateError = true
            this.AppUtils.showError(error.message)
          },
          () => {
            this.$nextTick(
              this.pageConfig.isLoadingValidateResult = false
            )
          }
        )
      } // end of if (allValid) {
    },
    getExmpleByretType (returnType) { // 该方法拷贝自FormulaValidator.vue 作者小陆, 到时候记得同步更新
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


.func-showcase-scroll {
  position: absolute;
  box-sizing:border-box;
  width: 100%;
  top: 41px;
  bottom: 0;
}
.func-showcase-scroll-wrapper {
  height: 100%;
}
</style>
