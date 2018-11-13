<template>
<div class="prop-condition-edit" v-loading="!pageConfig.pageIniting"
    element-loading-text="正在初始化页面">
    <div class="button-area">
      <el-button type="primary" icon="save" @click="save" :loading="pageConfig.isSaving">保存</el-button>
      <!--<el-button icon="save" @click="save(true)" :loading="pageConfig.isSaving" v-if="initData.mode==='add'">保存并继续</el-button>-->
      <el-button icon="cancel" @click="close">取消</el-button>
    </div><!-- /.button-area -->
    <div class="form-area">
      <el-form label-width="100px" :model="formData" ref="form" :rules="formRules">
        <el-form-item class="text-only" label="属性">
          {{pageCommon.className + '.' + pageCommon.classPropName}}
        </el-form-item>

        <el-form-item label="条件" prop="cndtOperate" required>
          <el-select v-model="formData.cndtOperate"  class="input-width-full">
            <el-option v-for="optType in compareTypeDict"
               :label="optType.label"
               :value="optType.val"
               :key="optType.val">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="值:类型" prop="cndtValueType">
          <el-select v-model="formData.cndtValueType"  class="input-width-full" @change="cndtTypeChange">
            <el-option v-for="optType in compareTargetTypeDict"
               :label="optType.label"
               :value="optType.val"
               :key="optType.val">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="值:常量" prop="cndtValueOriginCode" v-if="formData.cndtValueType==='const' && formData.constType===1" required>
          <el-input v-model="formData.cndtValueOriginCode" class="input-width-full" key="const"></el-input>
        </el-form-item>

        <el-form-item label="值:枚举" prop="cndtValueOriginCode" v-if="formData.cndtValueType==='const' && formData.constType===2" required>
          <el-select v-model="formData.cndtValueOriginCode" filterable placeholder="请选择" key="const-enum" class="input-width-full">
            <el-option
              v-for="item in constEnums"
              :label="item.word_name"
              :value="item.id"
              :key="item.id">
            </el-option>
          </el-select>
        </el-form-item>

        <!--<el-form-item label="值:类" v-if="formData.cndtValueType==='const' && formData.constType===3">-->
          <!--{{pageCommon.classValue}}-->
        <!--</el-form-item>-->

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

        <el-form-item label="值:变量" prop="cndtValueOriginCode" v-if="formData.cndtValueType==='variable'" required>
          <el-select v-model="formData.cndtValueOriginCode" filterable placeholder="请选择" key="variable" class="input-width-full">
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
import { mapGetters } from 'vuex'
export default {
  name: 'propConditionEdit',
  props: ['initData'],
  data () {
    return {
      pageCommon: {
        cndtIndex: 0,
        className: '',
        classPropName: ''
      },
      pageConfig: {
        pageIniting: true,
        pageInitText: '正在初始化页面',
        isSaving: false
      },
      formData: {
        cndtOperate: '=',
        cndtValueType: 'const',
        cndtValue: '',
        cndtValueOriginCode: '',
        isenable: 1,
        cndtProp: '',
        cndtPropClssId: '',
        cndtPropOriginCode: '',
        cndtObjectNumber: '',
        constType: 0
      },
      availableVars: [],
      formRules: {
        cndtObjectNumber: [{
          validator: (rules, value, callback) => {
            if (!value) {
              callback(new Error('对象编号不能为空'))
            }
            callback()
          },
          trigger: 'blur'
        }],
        cndtOperate: [{
          validator: (rules, value, callback) => {
            if (!value) {
              callback(new Error('条件不能为空'))
            }
            callback()
          },
          trigger: 'blur'
        }],
        cndtValueOriginCode: [{
          validator: (rules, value, callback) => {
            if (!value) {
              callback(new Error('值不能为空'))
            }
            callback()
          },
          trigger: 'blur'
        }]
      },
      constEnums: [],
      vars: []
    }
  },
  computed: {
    ...mapGetters({
      compareTypeDict: 'getCndtOperatorDict',
      compareTargetTypeDict: 'getCndtValueTypeDict4PropLimit'
    })
  },
  created () {
    console.info('initData', this.initData)
    this.init()
  },
  methods: {
    init () {
      this.pageCommon.className = this.initData.className
      this.pageCommon.classPropName = this.initData.classPropName
      this.pageCommon.cndtIndex = this.initData.index
      this.formData.cndtProp = this.initData.classPropName
      this.formData.cndtPropOriginCode = this.initData.classPropId
      this.Utils.clonePropsWhenExist(this.formData, this.initData.cond)
      this.availableVars = this.initData.variableData
      this.vars = this.initData.variableData
      console.log(this.formData)
      this.propTypeInit(this.initData.classPropId)
    },
    propTypeInit (propId) {
      relConditionService.queryBaseInfoOfRelCondition({propId: propId}).then((res) => {
        console.log('prop', res)
        let propType = res.edmpValueType
        if (propType === 'normalObj') {
          this.formData.constType = 1
          this.vars = _.filter(this.availableVars, function (o) { return o.vrntVarType === res.edmpDataType })
        } else if (propType === 'enum') {
          this.formData.constType = 2
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
          this.constEnums = []
          this.vars = _.filter(this.availableVars, function (o) { return o.vrntVarType === propType })
          if (res.edmpDataType === null) {
            this.constEnums = []
          } else {
            formulaService.getConstInfoByObjectId(res.edmpDataType).then((data) => {
              this.constEnums = [{word_name: data.edmcName, id: data.id}]
              this.formData.cndtValueOriginCode = data.id
            }).catch((err) => {
              this.AppUtils.showWarning(err.message)
            })
          }
        } else {
          this.formData.constType = 1
          this.vars = _.filter(this.availableVars, function (o) { return o.vrntVarType === propType })
        }
      }).catch((error) => {
        this.AppUtils.showWarning(error.message)
      })
    },
    cndtTypeChange () {
      this.formData.cndtValueOriginCode = ''
      this.formData.cndtObjectNumber = ''
    },
    save () {
      this.$refs.form.validate((valid) => {
        if (valid) {
          let condData = this.Utils.cloneDeep(this.formData)
          if (condData.cndtValueType === 'const') {
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
          } else {
            condData.cndtValue = this.getVrntById(condData.cndtValueOriginCode)
          }
          condData.cndtSeq = this.pageCommon.cndtIndex + 1
          let index = this.pageCommon.cndtIndex
          this.$emit('saveCond', {index, condData})
          this.$emit('close')
        } else {
          console.log('校验失败')
        }
      })
    },
    close () {
      this.$emit('close')
    },
    getVrntById (cndtValueOriginCode) {
      // 获取变量值
      for (var i = 0; i < this.availableVars.length; i++) {
        if (cndtValueOriginCode === this.availableVars[i].vrntId) {
          return this.availableVars[i].vrntVarName
        }
      }
      return '未知'
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
