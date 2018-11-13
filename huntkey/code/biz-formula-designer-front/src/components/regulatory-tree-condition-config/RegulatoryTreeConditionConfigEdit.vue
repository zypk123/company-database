<template>
  <div class="prop-condition-edit" v-loading="!pageConfig.pageIniting" element-loading-text="正在初始化页面">
    <!--按钮区域-->
    <div class="button-area">
      <el-button type="primary" icon="save" @click="save" :loading="pageConfig.isSaving">保存</el-button>
      <!--<el-button icon="save" @click="save(true)" :loading="pageConfig.isSaving" v-if="initData.mode==='add'">保存并继续</el-button>-->
      <el-button icon="cancel" @click="close">取消</el-button>
    </div>
    <!-- 表单区域 -->
    <div class="form-area">
      <el-form label-width="100px" :model="formData" ref="form" :rules="formRules">

        <!--<el-form-item label="属性：" prop="cndrPropOriginCode">-->
        <!--<el-select v-model="formData.cndrPropOriginCode" class="input-width-full">-->
        <!--<el-option v-for="aProp in this.initData.prevRelProp"-->
        <!--:label="aProp.edmpName"-->
        <!--:value="aProp.edmpCode"-->
        <!--:key="aProp.edmpCode" >-->
        <!--</el-option>-->
        <!--</el-select>-->
        <!--</el-form-item>-->
        <!--AA123456-->
        <el-form-item label="属性：" prop="cndrPropOriginCode">
          <el-cascader
            v-model="formData.cndrPropOriginCode"
            class="input-width-full"
            placeholder="请选择"
            :options="propsData"
            filterable
            :props="filterData"
            @change="propTypeChange"
          ></el-cascader>
        </el-form-item>

        <el-form-item label="条件：" prop="cndrOperate">
          <el-select v-model="formData.cndrOperate"  class="input-width-full">
            <el-option v-for="optType in compareTypeDict"
                       :label="optType.label"
                       :value="optType.val"
                       :key="optType.val">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="值:类型" prop="cndtValueType">
          <el-select v-model="formData.cndrValueType" class="input-width-full" @change="cndrValueTypeChange">
            <el-option v-for="optType in compareTargetTypeDict"
                       :label="optType.label"
                       :value="optType.val"
                       :key="optType.val">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="值:常量" prop="cndrProp2OriginCode" v-if="formData.cndrValueType==='const' && formData.constType===1">
          <el-input v-model="formData.cndrProp2OriginCode" class="input-width-full"></el-input>
        </el-form-item>

        <el-form-item label="值:枚举" prop="cndrProp2OriginCode" v-if="formData.cndrValueType==='const' && formData.constType===2">
          <el-select v-model="formData.cndrProp2OriginCode" filterable placeholder="请选择" class="input-width-full">
            <el-option
              v-for="item in constEnums"
              :label="item.word_name"
              :value="item.id"
              :key="item.id">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="值:类" prop="cndrProp2OriginCode" v-if="formData.cndrValueType==='const' && formData.constType===3" required>
          <el-select v-model="formData.cndrProp2OriginCode" filterable placeholder="请选择" key="const-class" class="input-width-full">
            <el-option
              v-for="item in constEnums"
              :label="item.word_name"
              :value="item.id"
              :key="item.id">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="对象编号" prop="cndtObjectNumber" v-if="formData.cndrValueType==='const' && formData.constType===3" required>
          <el-input v-model="formData.cndtObjectNumber" class="input-width-full" key="const-code"></el-input>
        </el-form-item>

        <el-form-item label="值:变量" prop="cndrProp2OriginCode" v-if="formData.cndrValueType==='variable'">
          <el-select v-model="formData.cndrProp2OriginCode" filterable placeholder="请选择" class="input-width-full">
            <el-option
              v-for="item in vars"
              :label="item.vrntVarName"
              :value="item.vrntId"
              :key="item.vrntId">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>
<script>
  import _ from 'lodash'
  import { relConditionService, formulaService } from '@/api'
  import { mapGetters } from 'vuex'
  export default {
    name: 'regulatoryTreeConditionConfigEdit',
    props: ['initData'],
    data () {
      let checkProp = (rule, value, callback) => {
        if (_.isEmpty(value)) {
          return callback(new Error('属性不能为空'))
        }
        callback()
      }
      return {
        flag: true,
        pageCommon: {
          cndrIndex: 0
        },
        pageConfig: {
          pageIniting: true,
          pageInitText: '正在初始化页面',
          isSaving: false
        },
        formData: {
          cndrId: '',
          cndrProp: '',
          cndrOperate: '=',
          cndrValueType: 'const',
          cndrProp2: '',
          cndrPropOriginCode: [],
          cndrProp2OriginCode: '',
          isenable: 1,
          constType: 1,
          cndtObjectNumber: ''
        },
        propsData: [],
        filterData: {
          label: 'edmpName',
          value: 'addProp',
          children: 'children'
        },
        vars: [],
        constEnums: [],
        availableVars: [],
        formRules: {
          // 修改为array校验
          cndrPropOriginCode: [
            { validator: checkProp, trigger: 'blur' }
          ],
          cndrOperate: [
            {required: true, message: '条件不能为空', trigger: 'blur'}
          ],
          cndrProp2OriginCode: [
            {required: true, message: '值不能为空', trigger: 'blur'}
          ],
          cndtObjectNumber: [
            {required: true, message: '对象编号不能为空', trigger: 'blur'}
          ]
        }
      }
    },
    computed: {
      ...mapGetters({
        compareTypeDict: 'getCndrOperatorDict',
        compareTargetTypeDict: 'getCndtValueTypeDict4PropLimit'
      })
    },
    created () {
      console.log('initData', this.initData)
      // 添加代码
      formulaService.queryEdmClassCascadePropsByClassID({
        edmcId: this.initData.clssId
      }).then((data) => {
        // console.log('data', data)
        this.addObjProp(data)
        this.propsData = data
      }).catch((err) => {
        this.AppUtils.showWarning(err.message)
      })
      this.init()
    },
    methods: {
      init () {
        this.pageCommon.cndrIndex = this.initData.index
        if (this.initData.cond) {
          this.Utils.clonePropsWhenExist(this.formData, this.initData.cond)
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
        this.availableVars = this.initData.variableData
        this.vars = this.initData.variableData
      },
      filterVars () {
        let valueStr = this.formData.cndrPropOriginCode[this.formData.cndrPropOriginCode.length - 1]
        let valueArr = valueStr.split('+')
        relConditionService.queryBaseInfoOfRelCondition({propId: valueArr[0]}).then((res) => {
          console.log('initProp', res)
          let propType = res.edmpValueType
          if (propType === 'normalObj') {
            this.vars = _.filter(this.availableVars, function (o) { return o.vrntVarType === res.edmpDataType })
          } else {
            this.vars = _.filter(this.availableVars, function (o) { return o.vrntVarType === propType })
          }
        }).catch((error) => {
          this.AppUtils.showWarning(error.message)
        })
      },
      getEnumOrObjectList () {
        let valueStr = this.formData.cndrPropOriginCode[this.formData.cndrPropOriginCode.length - 1]
        let valueArr = valueStr.split('+')
        relConditionService.queryBaseInfoOfRelCondition({propId: valueArr[0]}).then((res) => {
          // console.log('initProp', res)
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
      addObjProp (nodes) {
        nodes.forEach(
          (item, index, arr) => {
            item.addProp = item.id + '+' + item.casEdmpCode + '+' + item.casEdmpName
            if (item.children && item.children.length > 0) {
              this.addObjProp(item.children)
            }
          }
        )
      },
      save () {
        this.$refs.form.validate((valid) => {
          if (valid) {
            let condData = this.Utils.cloneDeep(this.formData)
            // 获取级联属性Code 级联属性名称
            let lastValueStr = condData.cndrPropOriginCode[condData.cndrPropOriginCode.length - 1]
            let lastValueArr = lastValueStr.split('+')
            condData.cndrProp = this.initData.clssName + '.' + lastValueArr[2]
            condData.cndrProp1Code = lastValueArr[1]
            if (condData.cndrValueType === 'const') {
              if (this.formData.constType === 1) {
                condData.cndrProp2 = condData.cndrProp2OriginCode
              }
              if (this.formData.constType === 2) {
                for (let i = 0; i < this.constEnums.length; i++) {
                  if (condData.cndrProp2OriginCode === this.constEnums[i].id) {
                    condData.cndrProp2 = this.constEnums[i].word_name
                  }
                }
              }
              if (this.formData.constType === 3) {
                for (let i = 0; i < this.constEnums.length; i++) {
                  if (condData.cndrProp2OriginCode === this.constEnums[i].id) {
                    condData.cndrProp2 = this.constEnums[i].word_name
                  }
                }
              }
            } else {
              condData.cndrProp2 = this.getVrntById(condData.cndrProp2OriginCode)
            }
            condData.cndrSeq = this.pageCommon.cndrIndex + 1
            let index = this.pageCommon.cndrIndex
            this.$emit('saveCond', {index, condData})
            this.$emit('close')
          }
        })
      },
      close () {
        this.$emit('close')
      },
      /**
       * 获取变量值
       */
      getVrntById (cndrProp2OriginCode) {
        for (var i = 0; i < this.availableVars.length; i++) {
          if (cndrProp2OriginCode === this.availableVars[i].vrntId) {
            return this.availableVars[i].vrntVarName
          }
        }
        return '未知'
      },
      cndrValueTypeChange () {
        this.formData.cndrProp2OriginCode = ''
        this.formData.cndtObjectNumber = ''
      },
      propTypeChange (value) {
        let valueStr = value[value.length - 1]
        let valueArr = valueStr.split('+')
        relConditionService.queryBaseInfoOfRelCondition({propId: valueArr[0]}).then((res) => {
          // console.log('prop', res)
          let propType = res.edmpValueType
          if (propType === 'normalObj') {
            this.formData.constType = 1
            this.formData.cndrProp2OriginCode = ''
            this.formData.cndtObjectNumber = ''
            this.vars = _.filter(this.availableVars, function (o) { return o.vrntVarType === res.edmpDataType })
          } else if (propType === 'enum') {
            this.formData.constType = 2
            this.formData.cndrProp2OriginCode = ''
            this.formData.cndtObjectNumber = ''
            this.constEnums = []
            this.vars = _.filter(this.availableVars, function (o) { return o.vrntVarType === propType })
            if (res.edmpDataType === null) {
              this.constEnums = []
            } else {
              formulaService.getConstInfoByEnumId(res.edmpDataType).then((data) => {
                // console.log('const', data)
                this.constEnums = data.dataset
              }).catch((err) => {
                this.AppUtils.showWarning(err.message)
              })
            }
          } else if (propType === 'object' || propType === 'objectLink') {
            this.formData.constType = 3
            this.formData.cndrProp2OriginCode = ''
            this.formData.cndtObjectNumber = ''
            this.constEnums = []
            this.vars = _.filter(this.availableVars, function (o) { return o.vrntVarType === propType })
            if (res.edmpDataType === null) {
              this.constEnums = []
            } else {
              formulaService.getConstInfoByObjectId(res.edmpDataType).then((data) => {
                // console.log('response', data)
                this.constEnums = [{word_name: data.edmcName, id: data.id}]
                if (this.formData.cndrValueType === 'const') {
                  this.formData.cndrProp2OriginCode = data.id
                }
              }).catch((err) => {
                this.AppUtils.showWarning(err.message)
              })
            }
          } else {
            this.formData.constType = 1
            this.formData.cndrProp2OriginCode = ''
            this.formData.cndtObjectNumber = ''
            this.vars = _.filter(this.availableVars, function (o) { return o.vrntVarType === propType })
          }
        }).catch((error) => {
          this.AppUtils.showWarning(error.message)
        })
      }
    }
  }
</script>
<style>
  .text-only{
    margin-bottom: 5px;
  }
  .form-area{
    margin-top: 10px;
  }
  .input-short{
    width: 80px;
  }
  .form-item-normal{
    width: 310px;
  }
  .form-item-short{
    width: 280px;
  }
  .input-width-full{
    width: 90%;
  }
</style>
