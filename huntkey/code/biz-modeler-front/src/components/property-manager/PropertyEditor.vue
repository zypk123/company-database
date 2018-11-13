<template>
  <div v-if="!this.initing">
    <div class="button-area" v-if="dataIn.mode !== getMode('EDIT_MODE_VIEW')">
      <el-button type="primary" icon="rx-baocun1" @click="save" :loading="isSaveing">保存</el-button>
      <el-button icon="circle-close" @click="close">取消</el-button>
    </div>
    <div class="form-area">
      <el-form label-width="100px" :model="formData" ref="form" :rules="dataIn.mode === getMode('EDIT_MODE_VIEW')? null:rules">
        <el-form-item class="text-only" label="所属类">
          {{$store.state.classTreeStore.currentClass.viewName}}
        </el-form-item>
        <el-form-item label="属性编码" prop="edmpCode">
          <el-input class="input-width-m" v-model.trim="formData.edmpCode" :maxlength="25"/>
        </el-form-item>
        <el-form-item label="属性名称" prop="edmpName">
          <span v-if="dataIn.mode === getMode('EDIT_MODE_VIEW')">{{formData.edmpName}}</span>
          <el-input v-else class="input-width-m" v-model.trim="formData.edmpName" :maxlength="20"/>
          &nbsp;&nbsp;
          <el-checkbox v-model="formData.isCharacter" :true-label="1" :false-label="0" :disabled="formData.edmpValueType !== 'normalObj'">对象呈现属性</el-checkbox>
          <el-checkbox v-model="formData.isVisible" :true-label="1" :false-label="0">可见</el-checkbox>
        </el-form-item>
        <el-form-item label="属性类别" prop="edmpValueType">
          <span v-if="dataIn.mode === getMode('EDIT_MODE_VIEW')">{{UTILS.getDictName('edm_field_type', formData.edmpValueType)}}</span>
          <el-select v-else class="input-width-m" v-model="formData.edmpValueType" @change="resetFormData" filterable >
            <el-option v-for="item in allDict['edm_field_type']" :key="item.value" :label="item.label" :value="item.value"></el-option>
          </el-select>
          &nbsp;&nbsp;
          <!-- <el-checkbox v-model="formData.isIndex" :true-label="1" :false-label="0" :disabled="formData.edmpValueType === 'assemble'">索引</el-checkbox> -->
        </el-form-item>
        <!-- 属性类别为普通时展示-->
        <el-form-item class="form-item-normal" label="数据类型" prop="edmpDataType" v-if="formData.edmpValueType === 'normalObj'">
          <span v-if="dataIn.mode === getMode('EDIT_MODE_VIEW')">
            {{UTILS.getDictName('edm_data_type', formData.edmpDataType)}}
            <span v-if="formData.edmpDataType === 'varchar' || formData.edmpDataType === 'decimal'">({{formData.edmpValueSize}})</span>
          </span>
          <el-select v-else class="input-width-m" v-model="formData.edmpDataType" @change="changeDataType" filterable>
            <el-option v-for="item in allDict['edm_data_type']" :key="item.value" :label="item.label" :value="item.value"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item class="form-item-short" prop="edmpValueSize"
            :label="formData.edmpDataType === 'varchar' ? '长度' : '精度'" label-width="50px"
            v-if="dataIn.mode !== getMode('EDIT_MODE_VIEW') && formData.edmpValueType === 'normalObj' && 
            (formData.edmpDataType === 'varchar' || formData.edmpDataType === 'decimal')">
          <el-input class="input-short" v-model="formData.edmpValueSize"/>
          <span v-show="formData.edmpDataType === 'varchar'">&nbsp;范围：1~65532</span>
          <span v-show="formData.edmpDataType === 'decimal'">&nbsp;范围：[1~65],[1~17]</span>
        </el-form-item>
        <!-- 属性类别为对象时展示 -->
        <el-form-item label="数据类型" prop="edmpDataType" v-if="formData.edmpValueType === 'object' || formData.edmpValueType === 'objectLink'">
          <span v-if="dataIn.mode === getMode('EDIT_MODE_VIEW')">{{UTILS.getClassById(formData.edmpDataType).viewName}}</span>
          <class-selecter v-else placeholder="请选择对象" class="input-width-m" v-model="formData.edmpDataType"></class-selecter>
        </el-form-item>
        <!-- 属性类别为计量单位的时候展示-->
        <el-form-item label="数据类型" v-show="formData.edmpValueType === 'measurement'">
          单位类
        </el-form-item>
        <!-- 属性类别为枚举的时候展示-->
        <el-form-item label="数据类型" prop="edmpDataType" v-if="formData.edmpValueType === 'enum'">
          <span v-if="dataIn.mode === getMode('EDIT_MODE_VIEW')"></span>
          <object-selecter 
          v-else placeholder="请选择枚举" 
          dialog-title="选择枚举" 
          class="input-width-m" 
          v-model="formData.edmpDataType"
          class-short-name="word"></object-selecter>
        </el-form-item>
        <!-- 属性类别为属性集的时候展示-->
        <el-form-item label="数据类型" v-show="formData.edmpValueType === 'assemble'">
          属性集
        </el-form-item>
        <!-- 属性类别为卷积的时候展示 -->
        <el-form-item label="数据类型" v-show="formData.edmpValueType === 'convolution'">
          类
        </el-form-item>
        <!-- 属性类别为常量的时候展示 -->
        <el-form-item label="数据类型" v-show="formData.edmpValueType === 'const'">
          类
        </el-form-item>
        <!-- 属性类别为类的时候展示 -->
        <el-form-item label="数据类型" v-show="formData.edmpValueType === 'class'">
          类
        </el-form-item>

        <!-- 属性类型为普通时才启用 -->
        <el-form-item label="属性限值" prop="edmpValueLimit">
          <prop-limit-editor
            class="input-width-full"
            v-model="formData.edmpValueLimit"
            :text="formData.propertLimit ? formData.propertLimit : formData.propertLimitDesc"
            :disabled="dataIn.mode === getMode('EDIT_MODE_CREATE') || formData.edmpValueType !== 'normalObj'"
            :property-id="formData.id"
            @removePropLimit="removePropLimit"
          ></prop-limit-editor>
          <!-- <el-input v-model="formData.edmpValueLimit" :disabled="dataIn.mode === getMode('EDIT_MODE_CREATE') || formData.edmpValueType !== 'normalObj'"/> -->
        </el-form-item>
        <el-form-item label="属性公式" prop="edmpFormula">
          <formual-editor
            class="input-width-full"
            v-model="formData.edmpFormula"
            :text="formData.propertFormula"
            :disabled="dataIn.mode === getMode('EDIT_MODE_CREATE') || ['normalObj', 'object', 'objectLink'].indexOf(formData.edmpValueType) < 0"
            :class-id="formData.edmpEdmcId"
            :property-id="formData.id"
            @removeFormula="removeFormula"
          ></formual-editor>
          <!-- <el-input v-model="formData.edmpFormula" :disabled="dataIn.mode === getMode('EDIT_MODE_CREATE') || formData.edmpValueType !== 'normalObj'" icon="plus" @click="editFormula"/> -->
        </el-form-item>
        <el-form-item label="属性值" prop="edmpValue">
          <span v-if="dataIn.mode === getMode('EDIT_MODE_VIEW')">{{formData.edmpValue}}</span>
          <el-input v-else class="input-width-m" v-model.trim="formData.edmpValue" 
            :disabled="formData.edmpValueType !== 'normalObj'"
            :maxlength="formData.edmpDataType === 'int' || formData.edmpDataType === 'decimal' ? 20:200"/>
        </el-form-item>
        <el-form-item label="更新响应方法" prop="edmpEdmmId">
          <span v-if="dataIn.mode === getMode('EDIT_MODE_VIEW')"></span>
          <method-selecter v-model="formData.edmpEdmmId" :name="formData.edmpEdmmName"
          :disabled="formData.edmpValueType !== 'normalObj'"
          class="input-width-full" multiple></method-selecter>
          <!-- <el-input v-else v-model="formData.edmpEdmmId" :disabled="formData.edmpValueType !== 'normalObj'"/> -->
        </el-form-item>
        <el-form-item label="属性描述" prop="edmpDesc">
          <span v-if="dataIn.mode === getMode('EDIT_MODE_VIEW')"></span>
          <el-input v-else type="textarea" v-model="formData.edmpDesc" :maxlength="100"/>
        </el-form-item>
        <el-form-item class="text-only" label="维护人：" label-width="113px" v-if="dataIn.mode !== getMode('EDIT_MODE_CREATE')">
          {{formData.moduser}}
        </el-form-item>
        <el-form-item class="text-only" label="维护时间：" label-width="113px" v-if="dataIn.mode !== getMode('EDIT_MODE_CREATE')">
          {{formData.modtimeStr}}
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>
<script>
  import { propertyTypes, dictTypes } from '@/types'
  import { propertyService } from '@/api'
  import ClassSelecter from '@/components/commons/class-selecter/ClassSelecter'
  import ObjectSelecter from '@/components/commons/object-selecter/ObjectSelecter'
  import MethodSelecter from '@/components/commons/method-selecter/MethodSelecter'
  import FormualEditor from '@/components/commons/formula-editor/FormulaEditor'
  import PropLimitEditor from '@/components/commons/prop-limit-editor/PropLimitEditor'
  import { mapGetters } from 'vuex'
  import _ from 'lodash'

  export default {
    props: ['dataIn'],
    components: {
      ClassSelecter, ObjectSelecter, MethodSelecter, FormualEditor, PropLimitEditor
    },
    created () {
      // console.log('this.allDict', this.allDict)
      this.initing = true
      // 获取需要的数据字典
      this.$store.dispatch(dictTypes.GET_DICT_BY_CODES, ['edm_field_type', 'edm_data_type']).then(() => {
        // 编辑模式，回填
        if (this.dataIn.mode !== propertyTypes.EDIT_MODE_CREATE) {
          _.assign(this.formData, this.dataIn.propertyInfo)
          this.$nextTick(() => {
            this.initing = false
          })
        } else {
          this.formData.edmpParentId = this.dataIn.parentId
          this.initing = false
        }
      })
    },
    computed: {
      ...mapGetters({
        allDict: dictTypes.GET_ALL_DICTS
      })
    },
    data () {
      return {
        // 正在保存
        isSaveing: false,
        // 初始化数据中
        initing: false,
        // 表单数据
        formData: {
          // id
          id: '',
          // 父属性ID
          edmpParentId: '',
          // 类ID
          edmpEdmcId: this.$store.state.classTreeStore.currentClass.id,
          // 属性名称
          edmpName: '',
          // 属性编码
          edmpCode: '',
          // 属性类别
          edmpValueType: 'normalObj',
          // 数据类型
          edmpDataType: 'varchar',
          // 属性限值
          edmpValueLimit: '',
          // 属性公式
          edmpFormula: '',
          // 属性公式显示值
          propertFormula: '',
          // 属性值
          edmpValue: '',
          // 更新响应方法
          edmpEdmmId: '',
          // 更新响应方法名称
          edmpEdmmName: '',
          // 属性描述
          edmpDesc: '',
          // 字段长度
          edmpValueSize: '50',
          // 对象呈现属性, 默认0 不选中
          isCharacter: 0,
          // 是否索引
          isIndex: 1,
          // 是否可见
          isVisible: 1
        },
        rules: {
          edmpName: [{
            required: true,
            message: '请输入属性名称'
          }, {
            validator: (rules, value, callback) => {
              // 编辑模式下，名称与原名称一致，不做效验
              if (this.dataIn.mode === propertyTypes.EDIT_MODE_UPDATE &&
                value === this.dataIn.propertyInfo.edmpName) {
                callback()
              } else {
                // 远程验证唯一性
                propertyService.validatePropertyName(this.formData.edmpEdmcId, this.formData.edmpParentId, value).then(() => {
                  callback()
                }).catch(() => {
                  callback('属性名称重复')
                })
              }
            },
            trigger: 'blur'
          }],
          edmpCode: [{
            required: true,
            message: '请输入属性编码'
          }, {
            validator: (rules, value, callback) => {
              if (this.dataIn.mode === propertyTypes.EDIT_MODE_UPDATE &&
                value === this.dataIn.propertyInfo.edmpCode) {
                callback()
              } else {
                propertyService.varlidatePropertyCode(this.formData.edmpEdmcId, this.formData.edmpParentId, value).then(() => {
                  callback()
                }).catch(() => {
                  callback('属性编码重复')
                })
              }
            },
            trigger: 'blur'
          }],
          edmpValueType: [{
            required: true,
            message: '请选择属性类别'
          }],
          edmpDataType: [{
            required: true,
            message: '请选择数据类型'
          }],
          edmpValueSize: [{
            required: true,
            message: '请输入长度或精度'
          }, {
            validator: (rulse, value, callback) => {
              // 字符型
              if (this.formData.edmpDataType === 'varchar') {
                const length = parseInt(value)
                if (!this.UTILS.validate(value, 'int') || length < 1 || length > 65532) {
                  callback('格式不正确或超出范围')
                } else {
                  callback()
                }
              } else if (this.formData.edmpDataType === 'decimal') {
                const typeArray = value.split(',')
                if (typeArray.length !== 2) {
                  callback('格式不正确或超出范围（示例：18,2）')
                } else {
                  const length1 = parseInt(typeArray[0])
                  const length2 = parseInt(typeArray[1])
                  if (!typeArray[0] || !this.UTILS.validate(typeArray[0], 'int') ||
                    !typeArray[1] || !this.UTILS.validate(typeArray[1], 'int') ||
                    length1 > 65 || length1 < 1 ||
                    length2 > 17 || length2 < 1) {
                    callback('格式不正确或超出范围（示例：18,2）')
                  } else {
                    callback()
                  }
                }
              }
            }
          }],
          edmpValue: [{
            validator: (rules, value, callback) => {
              if (this.formData.edmpDataType === 'int' && !this.UTILS.validate(value, 'int')) {
                // 整型验证
                callback('必须输入整型')
              } else if (this.formData.edmpDataType === 'decimal' && !this.UTILS.validate(value, 'float')) {
                callback('必须输入浮点型')
              } else {
                callback()
              }
            }
          }]
        }
      }
    },
    methods: {
      removeFormula () {
        this.$emit('removeFormula', this.dataIn.propertyInfo)
      },
      removePropLimit () {
        this.$emit('removePropLimit', this.dataIn.propertyInfo)
      },
      getMode (key) {
        return propertyTypes[key]
      },
      close () {
        this.$emit('close')
      },
      resetFormData (value) {
        if (this.initing) return
        this.formData.edmpDataType = null
        this.formData.edmpValueLimit = null
        this.formData.edmpFormula = null
        this.formData.edmpValue = null
        this.formData.edmpEdmmId = null
        this.formData.isCharacter = 0
        if (value === 'assemble') {
          this.formData.isIndex = 0
        } else {
          this.formData.isIndex = 1
        }
      },
      changeDataType (value) {
        if (this.initing) return
        if (value === 'varchar') {
          this.formData.edmpValueSize = '50'
        } else if (value === 'decimal') {
          this.formData.edmpValueSize = '18,2'
        } else {
          this.formData.edmpValueSize = ''
        }
      },
      // 保存
      save () {
        if (this.isSaveing) return
        // 验证表单
        this.$refs.form.validate(result => {
          if (result) {
            this.isSaveing = true
            // 保存操作
            if (this.dataIn.mode === propertyTypes.EDIT_MODE_CREATE) {
              // 添加属性
              propertyService.createProperty(this.formData).then(data => {
                this.isSaveing = false
                this.$emit('createSuccess')
              }).catch(() => {
                this.isSaveing = false
              })
            } else {
              // 编辑属性
              propertyService.updateProperty(this.formData).then(data => {
                this.isSaveing = false
                this.$emit('updateSuccess')
              }).catch(() => {
                this.isSaveing = false
              })
            }
          }
        })
      }
    }
  }
</script>
<style scoped>
  .el-form-item{
    float: left;
    width: 100%;
  }
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
