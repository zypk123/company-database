<template>
  <div>
    <div class="button-area">
      <el-button type="primary" icon="rx-baocun1" :loading="isSaveing" @click="save">保存</el-button>
      <el-button icon="circle-close" @click="cancel">取消</el-button>
    </div>
    <el-form :model="formData" ref="form" :rules="rules" label-width="90px">
      <el-form-item label="关联类" prop="edmlEdmcIdLink">
        <class-selecter class="input-width" v-model="formData.edmlEdmcIdLink" @change="changeClass"></class-selecter>
      </el-form-item>
      <el-form-item label="关联属性" prop="edmlEdmpLinkId">
        <el-cascader
          class="input-width-xl"
          :options="currentProperties"
          v-model="currentChooseProperty"
          :props="{
            value: 'id',
            label: 'edmpName',
            children
          }"
        ></el-cascader>
        <!-- children -->
        <!-- <el-select class="input-width" v-model="formData.edmlEdmpLinkId" filterable>
          <el-option v-for="item in currentProperties" :key="item.id" :label="item.edmpName" :value="item.id"></el-option>
        </el-select> -->
      </el-form-item>
      <el-form-item label="对象定位公式">
        <condition-config-editor
        class="input-width-xxl"
        v-model="formData.edmlFormula"
        :property-id="formData.edmlEdmpId"
        :connect-property-id="formData.edmlEdmpLinkId"
        :text="formData.formula ? formData.formula : formData.formulaDesc"
        :disabled="!formData.edmlEdmpLinkId"
        @removeCondition="removeCondition"></condition-config-editor>
        <!-- <el-input class="input-width-xxl" v-model="formData.edmlFormula"></el-input> -->
      </el-form-item>
      <el-form-item label="联动方式">
        <el-select class="input-width" v-model="formData.edclType" filterable>
          <el-option v-for="item in allDicts['edm_connect_type']" :key="item.value" :label="item.label" :value="item.value"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="保存联动记录" >
        <el-switch on-text="是" off-text="否" :on-value="1" :off-value="0"  v-model="formData.edcnLinkPreservable"></el-switch>
      </el-form-item>
      <el-form-item label="联动时效" prop="edclUpdateType">
        <el-select class="input-width" v-model="formData.edclUpdateType" @change="clearInfo" filterable>
          <el-option v-for="item in allDicts['edm_update_type']" :key="item.value" :label="item.label" :value="item.value"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="优先级" v-if="formData.edclUpdateType === 'async'">
        <el-select class="input-width-small" v-model="formData.asyncTypePriority" filterable>
          <el-option v-for="item in allDicts['edm_asyncType_priority']" :key="item.value" :label="item.label" :value="item.value"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="时间"  v-if="formData.edclUpdateType === 'time'" prop="edclUpdateTime">
        <el-time-select
          v-model="formData.edclUpdateTime"
          :editable="false"
          :clearable="false"
          popper-class="el-time-selecter-width"
          :picker-options="{
            start: '00:00',
            step: '00:15',
            end: '23:45'
          }"
          placeholder="选择时间"></el-time-select>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
  import ClassSelecter from '@/components/commons/class-selecter/ClassSelecter'
  import ConditionConfigEditor from '@/components/commons/condition-config-editor/ConditionConfigEditor'
  import { mapGetters } from 'vuex'
  import { dictTypes, propertyTypes } from '@/types'
  import { propertyService } from '@/api'

  export default {
    props: ['dataIn'],
    components: {
      ClassSelecter, ConditionConfigEditor
    },
    created () {
      // 获取所需数据字典
      this.$store.dispatch(dictTypes.GET_DICT_BY_CODES, ['edm_asyncType_priority', 'edm_update_type', 'edm_connect_type'])
      if (this.dataIn.mode === propertyTypes.EDIT_MODE_CREATE) {
        // 设置属性ID
        this.formData.edmlEdmpId = this.dataIn.propertyId
      } else {
        this.UTILS.setDataFromOther(this.formData, this.dataIn.linkInfo)
        this.formData.edmlEdmcIdLink = this.dataIn.linkInfo.edmcId
        // 获取属性
        propertyService.getPropertyConnectAble(this.formData.edmlEdmcIdLink).then(data => {
          this.currentProperties = data
          this.findCurrentChooseProperty()
        })
      }
    },
    computed: {
      ...mapGetters({
        'allDicts': dictTypes.GET_ALL_DICTS
      })
    },
    watch: {
      currentChooseProperty: {
        handler (value) {
          if (value && value.length > 0) {
            this.formData.edmlEdmpLinkId = value[value.length - 1]
          } else {
            this.formData.edmlEdmpLinkId = ''
          }
        },
        deep: true
      }
    },
    data () {
      return {
        // 当前属性列表
        currentProperties: [],
        // 当前正在保存
        isSaveing: false,
        // 当前级联选择项
        currentChooseProperty: [],
        // 属性值
        formData: {
          // 唯一标识
          id: '',
          // 属性ID
          edmlEdmpId: '',
          // 关联类ID,
          edmlEdmcIdLink: '',
          // 关联属性ID
          edmlEdmpLinkId: '',
          // 对象定位公式
          edmlFormula: '',
          // 公式名称
          formula: '',
          // 公式描述
          formulaDesc: '',
          // 联动方式
          edclType: 'sum',
          // 是否保存联动记录
          edcnLinkPreservable: 1,
          // 联动时效
          edclUpdateType: '',
          // 优先级
          asyncTypePriority: '3',
          // 更新时间
          edclUpdateTime: '00:00'
        },
        rules: {
          edmlEdmcIdLink: [{
            required: true,
            message: '请选择关联类'
          }],
          edmlEdmpLinkId: [{
            required: true,
            message: '请选择关联属性'
          }, {
            validator: (rules, value, callback) => {
              console.log('value', value)
              // 编辑模式，与原始数据相同，不做验证
              if (this.dataIn.mode === propertyTypes.EDIT_MODE_UPDATE && value === this.dataIn.linkInfo.edmlEdmpLinkId) {
                callback()
              } else {
                // 远程验证
                propertyService.validateLinkProperty(this.formData.edmlEdmpId, value).then(() => {
                  callback()
                }).catch(() => {
                  callback('该属性已经关联')
                })
              }
            },
            trigger: 'change'
          }],
          edclUpdateType: [{
            required: true,
            message: '请选择联动时效'
          }],
          asyncTypePriority: [{
            required: true,
            message: '请选择优先级'
          }]
        }
      }
    },
    methods: {
      removeCondition () {
        // 删除关联公式
        this.$emit('removeCondition', this.dataIn.linkInfo)
      },
      findCurrentChooseProperty () {
        let findFlag = false
        const findInProperty = array => {
          if (findFlag) return
          for (let item of array) {
            this.currentChooseProperty.push(item.id)
            if (item.children && item.children.length) {
              findInProperty(item.children)
              if (!findFlag) {
                this.currentChooseProperty.pop()
              } else {
                return
              }
            } else {
              if (item.id === this.formData.edmlEdmpLinkId) {
                findFlag = true
                return
              } else {
                this.currentChooseProperty.pop()
              }
            }
          }
        }
        findInProperty(this.currentProperties)
      },
      // 清空相关的属性
      clearInfo () {
        this.formData.asyncTypePriority = '3'
        this.formData.edclUpdateTime = '00:00'
      },
      // 类改变，获取属性值
      changeClass (value) {
        // 查找属性列表
        if (value) {
          propertyService.getPropertyConnectAble(value).then(data => {
            this.formData.edmlEdmpLinkId = ''
            this.currentChooseProperty = []
            this.currentProperties = data
          })
        } else {
          this.formData.edmlEdmpLinkId = ''
          this.currentChooseProperty = []
          this.currentProperties = []
        }
      },
      // 保存
      save () {
        // 验证表单
        this.$refs.form.validate(result => {
          if (result) {
            this.isSaveing = true
            if (this.dataIn.mode === propertyTypes.EDIT_MODE_CREATE) {
              // 保存关联属性
              propertyService.savePropertyLink(this.formData).then(() => {
                this.isSaveing = false
                this.$emit('createSuccess')
              }).catch(() => {
                this.isSaveing = false
              })
            } else {
              // 更新关联属性
              propertyService.updatePropertyLink(this.formData).then(() => {
                this.isSaveing = false
                this.$emit('updateSuccess')
              }).catch(() => {
                this.isSaveing = false
              })
            }
          }
        })
      },
      cancel () {
        this.$emit('close')
      }
    }
  }
</script>
<style scoped>
  .button-area{
    margin-bottom: 20px;
  }
  .input-width{
    width: 193px;
  }
  .input-width-small{
    width: 120px;
  }
</style>
