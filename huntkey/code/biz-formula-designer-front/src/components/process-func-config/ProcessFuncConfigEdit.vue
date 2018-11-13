<template>
  <div class="rel-condition-cond-edit" v-loading="!pageConfig.pageIniting" element-loading-text="正在初始化页面">
    <!--按钮区域-->
    <div class="button-area">
      <el-button type="primary" icon="save" @click="save" :loading="pageConfig.isSaving">保存</el-button>
      <el-button icon="cancel" @click="close">取消</el-button>
    </div>

    <!--表单区域-->
    <div class="form-area">
      <el-form label-width="100px" :model="formData" ref="form" :rules="formRules">
        <!--<el-form-item label="类型">-->
          <!--<el-select v-model="formData.cndrTypeFlag"  style='width:215px'>-->
            <!--<el-option v-for="item in typeFlagDict"-->
                       <!--:label="item.label"-->
                       <!--:value="item.val"-->
                       <!--:key="item.val">-->
            <!--</el-option>-->
          <!--</el-select>-->
        <!--</el-form-item>-->

        <el-form-item label="类型">
         <span>属性</span>
        </el-form-item>

        <!--class="input-width-full"-->
       <el-form-item label="属性" prop="cndrPropValue" v-if="formData.cndrTypeFlag===1">
        <el-input
          icon="plus"
          style='width:215px'
          readonly
          v-model="formData.cndrPropValue"
          @focus="handleIconClick"
          :on-icon-click="handleIconClick">
        </el-input>
        </el-form-item>

        <el-form-item label="函数名称" prop="cndrPropValue" v-if="formData.cndrTypeFlag===2">
          <el-select v-model="formData.cndrPropValue" style='width:215px'
            :disabled="pageConfig.flag">
            <el-option v-for="item in funcs"
                       :label="item.funcName"
                       :value="item.funcId"
                       :key="item.funcId" >
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="条件" prop="cndrOperate">
          <el-select v-model="formData.cndrOperate" filterable style='width:215px'>
            <el-option v-for="compareType in compareTypeDict"
                       :label="compareType.label"
                       :value="compareType.val"
                       :key="compareType.val" >
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="值:类型" prop="cndrValueType">
          <el-select v-model="formData.cndrValueType" @change="cndrValueTypeChange" style='width:215px'>
            <el-option v-for="optType in cndrValueTypeDict"
                       :label="optType.label"
                       :value="optType.val"
                       :key="optType.val">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="值:常量" prop="cndrProp2Value" v-if="formData.cndrValueType==='const' && formData.constType===1">
          <el-input v-model="formData.cndrProp2Value" style='width:215px'></el-input>
        </el-form-item>

        <el-form-item label="值:枚举" prop="cndrProp2Value" v-if="formData.cndrValueType==='const' && formData.constType===2">
          <el-select v-model="formData.cndrProp2Value" filterable placeholder="请选择" style='width:215px'>
            <el-option
              v-for="item in constEnums"
              :label="item.word_name"
              :value="item.id"
              :key="item.id">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="值:类" prop="cndrProp2Value" v-if="formData.cndrValueType==='const' && formData.constType===3" required>
          <el-select v-model="formData.cndrProp2Value" filterable placeholder="请选择" key="const-class" style='width:215px'>
            <el-option
              v-for="item in constEnums"
              :label="item.word_name"
              :value="item.id"
              :key="item.id">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="对象编号" prop="cndtObjectNumber" v-if="formData.cndrValueType==='const' && formData.constType===3" required>
          <el-input v-model="formData.cndtObjectNumber" style='width:215px' key="const-code"></el-input>
        </el-form-item>

        <el-form-item label="值:变量" prop="cndrProp2Value" v-if="formData.cndrValueType==='variable'" title="变量为该公式范围内的局部变量">
          <el-select v-model="formData.cndrProp2Value" filterable placeholder="请选择" style='width:215px'>
            <el-option v-for="item in vars"
                       :key="item.vrntId"
                       :label="item.vrntVarName"
                       :value="item.vrntId">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>
<script>
  import _ from 'lodash'
  import { mapGetters } from 'vuex'
  import ClassPropConfig from './ClassPropConfig'
  import { relConditionService, formulaService } from '@/api'
  export default {
    name: 'processFuncConfigEdit',
    props: ['initData'],
    data () {
      return {
        initFlag: 0,
        pageCommon: {
          cndrIndex: 0
        },
        pageConfig: {
          pageIniting: true,
          pageInitText: '正在初始化页面',
          isSaving: false,
          flag: true
        },
        formData: {
          cndrId: '',
          cndrTypeFlag: 1,
          cndrPropValue: '',
          cndrClass1NameEn: '',
          cndrOperate: '=',
          cndrValueType: '',
          cndrProp2Value: '',
          classTypeFlag: '',
          classId: '',
          classPropId: [],
          cndrProp1Code: '',
          isenable: 1,
          linkClssId: '',
          flag: true,
          constType: 1,
          cndtObjectNumber: ''
        },
        formRules: {
          cndrValueType: [
            {required: true, message: '值类型不能为空', trigger: 'change'}
          ],
          cndrPropValue: [
            {required: true, message: '属性不能为空', trigger: 'change'}
          ],
          cndrOperate: [
            {required: true, message: '条件不能为空', trigger: 'blur'}
          ],
          cndrProp2Value: [
            {required: true, message: '值不能为空', trigger: 'blur'}
          ],
          cndtObjectNumber: [
            {required: true, message: '对象编号不能为空', trigger: 'blur'}
          ]
        },
        funcs: [],
        variableData: [],
        classes: [],
        relClasses: [],
        constEnums: [],
        vars: []
      }
    },
    created () {
      this.init()
    },
    methods: {
      init () {
        this.pageCommon.cndrIndex = this.initData.index
        this.funcs = this.initData.funcs
        this.variableData = this.initData.variableData
        this.vars = this.initData.variableData
        this.classes = this.initData.classes
        this.relClasses = this.initData.relClasses
        // console.log(this.initData.cond)
        if (this.initData.cond) {
          this.formData = this.Utils.cloneDeep(this.initData.cond)
          console.log(this.formData)
          if (this.formData.cndrValueType === 'const') {
            if (this.formData.constType === 2) {
              this.getEnumOrObjectList()
            }
            if (this.formData.constType === 3) {
              this.getEnumOrObjectList()
            }
          } else {
            this.filterVars()
          }
        }
      },
      filterVars () {
        let valueStr = this.formData.classPropId[this.formData.classPropId.length - 1]
        let valueArr = valueStr.split('+')
        relConditionService.queryBaseInfoOfRelCondition({propId: valueArr[0]}).then((res) => {
          console.log('initProp', res)
          let propType = res.edmpValueType
          if (propType === 'normalObj') {
            this.vars = _.filter(this.variableData, function (o) { return o.vrntVarType === res.edmpDataType })
          } else {
            this.vars = _.filter(this.variableData, function (o) { return o.vrntVarType === propType })
          }
        }).catch((error) => {
          this.AppUtils.showWarning(error.message)
        })
      },
      getEnumOrObjectList () {
        let valueStr = this.formData.classPropId[this.formData.classPropId.length - 1]
        let valueArr = valueStr.split('+')
        relConditionService.queryBaseInfoOfRelCondition({propId: valueArr[0]}).then((res) => {
          console.log('initProp', res)
          let propType = res.edmpValueType
          if (propType === 'enum') {
            this.constEnums = []
            // this.vars = _.filter(this.availableVars, function (o) { return o.vrntVarType === propType })
            formulaService.getConstInfoByEnumId(res.edmpDataType).then((data) => {
              // console.log('const', data)
              this.constEnums = data.dataset
            }).catch((err) => {
              this.AppUtils.showWarning(err.message)
            })
          } else if (propType === 'object' || propType === 'objectLink') {
            this.constEnums = []
            // this.vars = _.filter(this.availableVars, function (o) { return o.vrntVarType === propType })
            formulaService.getConstInfoByObjectId(res.edmpDataType).then((data) => {
              // console.log('response', data)
              this.constEnums = [{word_name: data.edmcName, id: data.id}]
            }).catch((err) => {
              this.AppUtils.showWarning(err.message)
            })
          }
        }).catch((error) => {
          this.AppUtils.showWarning(error.message)
        })
      },
      /**
       * 保存
       */
      save () {
        this.$refs.form.validate((valid) => {
          if (valid) {
            let condData = this.Utils.cloneDeep(this.formData)
            // 变量名 cndrProp2 函数名 cndrProp 索引 cndrSeq
            if (this.formData.cndrTypeFlag === 2) {
              condData.classTypeFlag = ''
              condData.classId = ''
              condData.classPropId = []
              condData.cndrProp = this.getFuncNameById(this.formData.cndrPropValue)
            } else {
              condData.cndrProp = this.formData.cndrPropValue
            }
            if (this.formData.cndrValueType === 'variable') {
              condData.cndrProp2 = this.getVrntById(this.formData.cndrProp2Value)
            } else {
              if (this.formData.constType === 1) {
                condData.cndrProp2 = this.formData.cndrProp2Value
              }
              if (this.formData.constType === 2) {
                for (let i = 0; i < this.constEnums.length; i++) {
                  if (condData.cndrProp2Value === this.constEnums[i].id) {
                    condData.cndrProp2 = this.constEnums[i].word_name
                  }
                }
              }
              if (this.formData.constType === 3) {
                for (let i = 0; i < this.constEnums.length; i++) {
                  if (condData.cndrProp2Value === this.constEnums[i].id) {
                    condData.cndrProp2 = this.constEnums[i].word_name
                  }
                }
              }
            }
            condData.cndrSeq = this.pageCommon.cndrIndex + 1
            let index = this.pageCommon.cndrIndex
            this.$emit('saveCond', {index, condData})
            this.$emit('close')
          }
        })
      },
      propTypeChange (value) {
        console.log(value)
        relConditionService.queryBaseInfoOfRelCondition({propId: value}).then((res) => {
          console.log('prop', res)
          let propType = res.edmpValueType
          if (propType === 'normalObj') {
            this.formData.constType = 1
            this.formData.cndrProp2Value = ''
            this.formData.cndtObjectNumber = ''
            this.vars = _.filter(this.variableData, function (o) { return o.vrntVarType === res.edmpDataType })
          } else if (propType === 'enum') {
            this.formData.constType = 2
            this.formData.cndrProp2Value = ''
            this.formData.cndtObjectNumber = ''
            this.constEnums = []
            this.vars = _.filter(this.variableData, function (o) { return o.vrntVarType === propType })
            if (res.edmpDataType === null) {
              this.constEnums = []
            } else {
              formulaService.getConstInfoByEnumId(res.edmpDataType).then((data) => {
                console.log('const', data)
                this.constEnums = data.dataset
              }).catch((err) => {
                this.AppUtils.showWarning(err.message)
              })
            }
          } else if (propType === 'object' || propType === 'objectLink') {
            this.formData.constType = 3
            this.formData.cndrProp2Value = ''
            this.formData.cndtObjectNumber = ''
            this.constEnums = []
            this.vars = _.filter(this.variableData, function (o) { return o.vrntVarType === propType })
            if (res.edmpDataType === null) {
              this.constEnums = []
            } else {
              formulaService.getConstInfoByObjectId(res.edmpDataType).then((data) => {
                console.log('response', data)
                this.constEnums = [{word_name: data.edmcName, id: data.id}]
                if (this.formData.cndrValueType === 'const') {
                  this.formData.cndrProp2Value = data.id
                }
              }).catch((err) => {
                this.AppUtils.showWarning(err.message)
              })
            }
          } else {
            this.formData.constType = 1
            this.formData.cndrProp2Value = ''
            this.formData.cndtObjectNumber = ''
            this.vars = _.filter(this.variableData, function (o) { return o.vrntVarType === propType })
          }
        }).catch((error) => {
          this.AppUtils.showWarning(error.message)
        })
      },
      /**
       * 通过ID获取变量名
       */
      getVrntById (varId) {
        for (let i = 0; i < this.variableData.length; i++) {
          if (varId === this.variableData[i].vrntId) {
            return this.variableData[i].vrntVarName
          }
        }
        return '未知变量'
      },
      getFuncNameById (funcId) {
        for (let i = 0; i < this.funcs.length; i++) {
          if (funcId === this.funcs[i].funcId) {
            return this.funcs[i].funcName
          }
        }
        return '未知函数'
      },
      /**
       * 关闭按钮方法
       */
      close () {
        this.$emit('close')
      },
      /**
       * 值类型下拉框发生改变
       */
      cndrValueTypeChange () {
        console.log('测试')
        if (this.initFlag === 0) {
          this.initFlag += 1
        } else {
          this.formData.cndrProp2Value = ''
          this.formData.cndtObjectNumber = ''
        }
      },
      handleIconClick () {
        if (this.initData.cond) {
          this.editProp()
        } else {
          this.addNewProp()
        }
      },
      addNewProp () {
        let title = '属性设置'
        let mode = 'add'
        this.openCondEditDialog({title, mode})
      },
      /**
       * 编辑条件
       */
      editProp () {
        let title = '属性设置'
        let mode = 'edit'
        let cond = this.formData
        this.openCondEditDialog({title, mode, cond})
      },
      /**
       * 打开新增/编辑条件弹框
       */
      openCondEditDialog ({title, mode, cond}) {
        this.OpenGlobalDialog({
          name: 'classPropConfig',
          component: ClassPropConfig,
          title: title,
          props: {
            mode: mode,
            cond: cond,
            classId: this.initData.classId,
            classes: this.classes,
            relClasses: this.relClasses,
            variableData: this.initData.variableData
          },
          options: {
            title: title,
            top: '20%',
            customClass: 'subpage-dlg dialog-width-xs'
          },
          events: {
            saveCond: this.saveCond,
            updateRelClass: this.updateRelClass,
            close: () => {
              this.CloseGlobalDialog('classPropConfig')
            }
          }
        })
      },
      updateRelClass () {
        this.$emit('updateRelClass')
      },
      /**
       * 保存条件
       */
      saveCond (condData) {
        console.log(condData)
        if (condData.linkClssId) {
          this.formData.linkClssId = condData.linkClssId
        }
        this.formData.classTypeFlag = condData.classTypeFlag
        this.formData.classId = condData.classId
        this.formData.classPropId = condData.classPropId
        this.formData.cndrClass1NameEn = condData.cndrClass1NameEn
        // 将获取的类和属性Id数组转为类属性名称
        let lastValueStr = condData.classPropId[condData.classPropId.length - 1]
        let lastValueArr = lastValueStr.split('+')
        this.formData.cndrProp1Code = lastValueArr[1]
        this.formData.cndrPropValue = condData.cndrPropValue + '.' + lastValueArr[2]
        this.propTypeChange(lastValueArr[0])
      }
    },
    computed: {
      ...mapGetters({
        compareTypeDict: 'getCndtOperatorDict',
        cndrValueTypeDict: 'getCndtValueTypeDict4PropLimit',
        typeFlagDict: 'getTypeFlagDict'
      })
    }
  }
</script>
<style>

</style>
