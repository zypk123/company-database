<template>
<div class="rel-edm-class-cond-edit"
    v-loading="pageConfig.pageIniting"
    element-loading-text="正在初始化页面">
    <div class="button-area">
      <el-button type="primary" icon="save" @click="save" :loading="pageConfig.isSaving">保存</el-button>
      <!--<el-button icon="save" @click="save(true)" :loading="pageConfig.isSaving" v-if="initData.mode==='add'">保存并继续</el-button>-->
      <el-button icon="cancel" @click="close">取消</el-button>
    </div><!-- /.button-area -->
    <div class="form-area">
      <el-form label-width="100px" :model="formData" ref="form" :rules="formRules">

        <el-form-item class="text-only" label="相关类">
          {{initData.relClass.clssAliasName}}
        </el-form-item>

        <el-form-item label="属性" prop="cndtPropOriginCode" required>
          <el-cascader
            v-model="formData.cndtPropOriginCode"
            class="input-width-full"
            placeholder="请选择"
            :options="emdProps"
            filterable
            :props="filterData"
            @change="propTypeChange"
          ></el-cascader>
        </el-form-item>

        <el-form-item label="条件" prop="cndtOperate">
          <el-select v-model="formData.cndtOperate"  filterable  class="input-width-full">
            <el-option v-for="optType in cndtOperatorDict"
               :label="optType.label"
               :value="optType.val"
               :key="optType.val">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="值:类型" prop="cndtValueType">
          <el-select v-model="formData.cndtValueType" @change="cndtValueTypeChange" class="input-width-full">
            <el-option v-for="optType in cndtValueTypeDict"
               :label="optType.label"
               :value="optType.val"
               :key="optType.val">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="类" prop="cndtValueClassId" v-if="formData.cndtValueType==='class'">
          <el-select v-model="formData.cndtValueClassId" @change="cndtValueClassChange"  class="input-width-full" >
              <el-option v-for="aClass in availableClasses"
               :label="aClass.label"
               :value="aClass.id"
               :key="aClass.id">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="属性：" prop="cndtValueClassProp" v-if="formData.cndtValueType==='class'" required>
          <el-cascader
            v-model="formData.cndtValueClassProp"
            class="input-width-full"
            placeholder="请选择"
            :options="cndtValueProps"
            filterable
            :props="filterData"
          ></el-cascader>
        </el-form-item>

        <el-form-item label="值:常量" prop="cndtValueOriginCode" v-if="formData.cndtValueType==='const' && formData.constType===1">
        <!-- @change="rowCndtValueOriginCodeChange(scope.row)" -->
          <el-input v-model="formData.cndtValueOriginCode" class="input-width-full"></el-input>
        </el-form-item>

        <el-form-item label="值:枚举" prop="cndtValueOriginCode" v-if="formData.cndtValueType==='const' && formData.constType===2">
          <el-select v-model="formData.cndtValueOriginCode" filterable placeholder="请选择" class="input-width-full">
            <el-option
              v-for="item in constEnums"
              :label="item.word_name"
              :value="item.id"
              :key="item.id">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="值:类" prop="cndtValueOriginCode" v-if="formData.cndtValueType==='const' && formData.constType===3" required>
          <el-select v-model="formData.cndtValueOriginCode" filterable placeholder="请选择" key="const-class" class="input-width-full">
            <el-option
              v-for="item in constEnums"
              :label="item.word_name"
              :value="item.id"
              :key="item.id">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="对象编号" prop="cndtObjectNumber" v-if="formData.cndtValueType==='const' && formData.constType===3" required>
          <el-input v-model="formData.cndtObjectNumber" class="input-width-full" key="const-code"></el-input>
        </el-form-item>

        <el-form-item label="值:变量" prop="cndtValueOriginCode" v-if="formData.cndtValueType==='variable'"
          title="变量为该公式范围内的局部变量">
        <!-- @change="rowCndtValueOriginCodeChange(scope.row)" -->
          <el-select v-model="formData.cndtValueOriginCode" filterable placeholder="请选择" class="input-width-full">
            <el-option
              v-for="item in vars"
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
import { relConditionService, formulaService } from '@/api'
import { EDM_DATA_TYPES } from '@/types'
import { mapGetters, mapActions } from 'vuex'
export default {
  name: 'relEdmClassCondEdit',
  props: ['initData'],
  data () {
    return {
      cndtIndex: 0,
      pageConfig: {
        pageIniting: true,
        pageInitText: '正在初始化页面',
        isSaving: false
      },
      initFormData: {},
      formData: {
        cndtProp: '',
        cndtPropOriginCode: [],
        cndtOperate: '=',
        cndtValueType: 'const',
        cndtValue: '',
        cndtValueClassId: '',
        cndtValueOriginCode: '',
        constType: 1,
        cndtObjectNumber: '',
        cndtValueClassProp: []
      },
      formRules: {
        cndtValueClassId: [
          {required: true, message: '类不能为空', trigger: 'blur'}
        ],
        cndtPropOriginCode: [{
          validator: (rules, value, callback) => {
            if (_.isEmpty(value)) {
              callback(new Error('属性不能为空'))
            }
            callback()
          },
          trigger: 'blur'
        }],
        cndtValueClassProp: [{
          validator: (rules, value, callback) => {
            if (_.isEmpty(value)) {
              callback(new Error('属性不能为空'))
            }
            callback()
          },
          trigger: 'blur'
        }],
        cndtOperate: [
          {required: true, message: '条件不能为空', trigger: 'blur'}
        ],
        cndtValueOriginCode: [
          {required: true, message: '值不能为空', trigger: 'blur'}
        ],
        cndtObjectNumber: [
          {required: true, message: '对象编号不能为空', trigger: 'blur'}
        ]
      },
      emdProps: [], // 相关类属性下拉列表
      cndtValueProps: [], // 比较值类型为类时, 该类的属性下拉列表
      filterData: {
        label: 'edmpName',
        value: 'addProp',
        children: 'children'
      },
      availableVars: [],
      constEnums: [],
      vars: []
    }
  },
  created () {
    this.init()
  },
  methods: {
    ...mapActions({
      fetchEdmps: EDM_DATA_TYPES.ACTION_FETCH_PROPS
    }),
    init () {
      console.info(this.initData)
      this.cndtIndex = this.initData.index
      this.availableVars = this.initData.store.sysVars
      this.vars = this.initData.store.sysVars
      if (this.initData.cond) {
        this.Utils.clonePropsWhenExist(this.formData, this.initData.cond)
        this.formData.cndtPropOriginCode = this.initData.cond.cndtPropOriginCode.split(' ')
        if (this.formData.cndtValueType === 'const') {
          if (this.formData.constType === 2) {
            this.getEnumOrObjectList()
          }
          if (this.formData.constType === 3) {
            this.getEnumOrObjectList()
          }
        } else if (this.formData.cndtValueType === 'class') {
          if (this.initData.cond.cndtValueOriginCode.indexOf(' ') !== -1) {
            this.formData.cndtValueClassProp = _.split(this.initData.cond.cndtValueOriginCode, ' ')
          } else {
            this.formData.cndtValueClassProp = [this.initData.cond.cndtValueOriginCode]
          }
        } else {
          this.filterVars()
        }
      }
      this.initPropes4Left().then(() => {
        return this.initPropes4Right()
      }).then(() => {
        this.initFormData = this.Utils.cloneDeep(this.formData)
        this.pageConfig.pageIniting = false
      })
    },
    getEnumOrObjectList () {
      let valueStr = this.formData.cndtPropOriginCode[this.formData.cndtPropOriginCode.length - 1]
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
    filterVars () {
      let valueStr = this.formData.cndtPropOriginCode[this.formData.cndtPropOriginCode.length - 1]
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
    // 初始化相关类的属性下拉
    initPropes4Left () {
      return new Promise((resolve, reject) => {
        this.fetchEdmps({
          edmcNameEn: this.initData.relClass.clssEdmcNameEn
        }).then((resData) => {
          this.addObjProp(resData)
          this.emdProps = resData
          resolve()
        })
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
    initPropes4Right () {
      return new Promise((resolve, reject) => {
        if (this.formData.cndtValueType === 'class') {
          let edmcNameEn = this.formData.cndtValueClassId
          if (edmcNameEn.indexOf('#') > -1) {
            edmcNameEn = edmcNameEn.split('#')[1]
          }
          this.fetchEdmps({
            edmcNameEn: edmcNameEn
          }).then((resData) => {
            this.addObjProp(resData)
            this.cndtValueProps = resData
            resolve()
          })
        } else {
          resolve()
        }
      })
    },
    cndtValueTypeChange () {
      this.formData.cndtValueClassId = ''
      this.formData.cndtValue = ''
      this.formData.cndtValueOriginCode = ''
      this.formData.cndtValueClassProp = []
    },
    cndtValueClassChange () {
      let edmcId = this.formData.cndtValueClassId
      if (edmcId.indexOf('#') > -1) {
        edmcId = edmcId.split('#')[1]
      }
      return this.fetchEdmps({
        edmcId: edmcId
      }).then((resData) => {
        this.addObjProp(resData)
        this.cndtValueProps = resData
        this.formData.cndtValue = ''
        this.formData.cndtValueOriginCode = ''
        this.formData.cndtValueClassProp = []
      }).catch((error) => {
        this.AppUtils.showError(error.message)
      })
    },
    save () {
      this.$refs.form.validate((valid) => {
        if (valid) {
          let condData = this.Utils.cloneDeep(this.formData)
          let lastValueStr = condData.cndtPropOriginCode[condData.cndtPropOriginCode.length - 1]
          let lastValueArr = lastValueStr.split('+')
          condData.cndtProp = lastValueArr[2]
          condData.cndtPropOriginCode = _.join(this.formData.cndtPropOriginCode, ' ')
          if (this.formData.cndtValueType === 'const') {
            if (this.formData.constType === 1) {
              condData.cndtValue = condData.cndtValueOriginCode
            }
            if (this.formData.constType === 2) {
              for (let i = 0; i < this.constEnums.length; i++) {
                if (condData.cndtValueOriginCode === this.constEnums[i].id) {
                  condData.cndtValue = this.constEnums[i].word_name
                }
              }
            }
            if (this.formData.constType === 3) {
              for (let i = 0; i < this.constEnums.length; i++) {
                if (condData.cndtValueOriginCode === this.constEnums[i].id) {
                  condData.cndtValue = this.constEnums[i].word_name
                }
              }
            }
          }
          if (this.formData.cndtValueType === 'variable') {
            condData.cndtValue = this.getVrntById(this.formData.cndtValueOriginCode)
          }
          if (this.formData.cndtValueType === 'class') {
            let className = this.Utils.findPropByProp(this.availableClasses, 'id', this.formData.cndtValueClassId, 'label', 'defVal')
            let lastValueStr = condData.cndtValueClassProp[condData.cndtValueClassProp.length - 1]
            let lastValueArr = lastValueStr.split('+')
            condData.cndtValue = className + '.' + lastValueArr[2]
            condData.cndtValueOriginCode = _.join(this.formData.cndtValueClassProp, ' ')
          }
          condData.cndtSeq = this.cndtIndex + 1
          let index = this.cndtIndex
          this.$emit('saveCond', {index, condData})
          this.$emit('close')
        }
      })
    },
    getVrntById (varId) {
      for (let i = 0; i < this.availableVars.length; i++) {
        if (varId === this.availableVars[i].vrntId) {
          return this.availableVars[i].vrntVarName
        }
      }
      return '未知'
    },
    propTypeChange (value) {
      let valueStr = value[value.length - 1]
      let valueArr = valueStr.split('+')
      relConditionService.queryBaseInfoOfRelCondition({propId: valueArr[0]}).then((res) => {
        let propType = res.edmpValueType
        if (propType === 'normalObj') {
          this.formData.constType = 1
          this.formData.cndtValueOriginCode = ''
          this.formData.cndtObjectNumber = ''
          this.vars = _.filter(this.availableVars, function (o) { return o.vrntVarType === res.edmpDataType })
        } else if (propType === 'enum') {
          this.formData.constType = 2
          this.formData.cndtValueOriginCode = ''
          this.formData.cndtObjectNumber = ''
          this.constEnums = []
          this.vars = _.filter(this.availableVars, function (o) { return o.vrntVarType === propType })
          if (res.edmpDataType === null) {
            this.constEnums = []
          } else {
            formulaService.getConstInfoByEnumId(res.edmpDataType).then((data) => {
              this.constEnums = data.dataset
            }).catch((err) => {
              this.AppUtils.showWarning(err.message)
            })
          }
        } else if (propType === 'object' || propType === 'objectLink') {
          this.formData.constType = 3
          this.formData.cndtValueOriginCode = ''
          this.formData.cndtObjectNumber = ''
          this.constEnums = []
          this.vars = _.filter(this.availableVars, function (o) { return o.vrntVarType === propType })
          if (res.edmpDataType === null) {
            this.constEnums = []
          } else {
            formulaService.getConstInfoByObjectId(res.edmpDataType).then((data) => {
              this.constEnums = [{word_name: data.edmcName, id: data.id}]
              if (this.formData.cndtValueType === 'const') {
                this.formData.cndtValueOriginCode = data.id
              }
            }).catch((err) => {
              this.AppUtils.showWarning(err.message)
            })
          }
        } else {
          this.formData.constType = 1
          this.formData.cndtValueOriginCode = ''
          this.formData.cndtObjectNumber = ''
          this.vars = _.filter(this.availableVars, function (o) { return o.vrntVarType === propType })
        }
      }).catch((error) => {
        this.AppUtils.showWarning(error.message)
      })
    },
    close () {
      this.$emit('close')
    }
  },
  computed: {
    ...mapGetters({
      cndtOperatorDict: 'getCndtOperatorDict',
      cndtValueTypeDict: 'getCndtValueTypeDict4RelClass'
    }),
    availableClasses () {
      // 条件右侧的类选择框, 包括直接关联的类和之前的相关类
      let classes = []
      this.initData.store.edmClasses.forEach(
        (item, index, array) => {
          let classInfo = {
            type: 'edmClass',
            id: item.id,
            label: item.edmcName,
            item: item
          }
          classes.push(classInfo)
        }
      )

      // FIXME 这里相关类和直接关联类都统一标记为格式化为类属性, 以后后台处理会不会处理不过来?
      this.initData.store.relEdmClasses.forEach(
        (item, index, array) => {
          // 理论上这里的clssClassId不为空, 但是调试阶段数据不完整, 多判断一下
          if (item.clssClassId) {
            let classInfo = {
              type: 'relClass',
              // id: item.clssId,
              id: `${item.clssId}#${item.clssClassId}`,
              label: item.clssClassRelatedName,
              item: item
            }
            // FIXME 需比较seq
            classes.push(classInfo)
          }
        }
      )
      console.log(classes)
      return _.uniqWith(classes, _.isEqual)
    }
  }
}
</script>
<style scoped>
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
    width: 100%;
  }
</style>
