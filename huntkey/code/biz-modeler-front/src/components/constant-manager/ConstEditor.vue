<template>
  <div id="atta-define">
    <div class="button-area">
      <el-button type="primary" icon="rx-bianji2" @click="isEditing = true" :disabled="isEditing">编辑</el-button>
      <el-button type="primary" icon="rx-baocun2" :disabled="!isEditing" @click="save" :loading="isUpdating">保存</el-button>
      <el-button icon="rx-chexiao" @click="cancel" :disabled="!isEditing">取消</el-button>
    </div>
    <el-form class="form"  :model="formData" label-width="86px" :rules="rules" ref="form">
<!--      <el-form-item label="所属类：" >
        <label >{{className}}</label>
      </el-form-item>
      <el-form-item label="属性编码 " prop="edmpCode">
        <label >{{formData.edmpCode}}</label>
      </el-form-item>-->
      <el-form-item label="属性编码" prop="edmpCode">
        <el-input class="input-width-l" v-model="formData.edmpCode" size="small" :maxlength="20"
                  :disabled="!isEditing"></el-input>
      </el-form-item>
      <el-form-item label="属性名称 " prop="edmpName">
        <el-input class="input-width-l" v-model="formData.edmpName" size="small" :maxlength="20"
                  :disabled="!isEditing"></el-input>
      </el-form-item>
<!--      <el-form-item label="属性类别 " prop="edmpValueType">
        <el-select class="input-width-l" v-model="formData.edmpValueType" size="small"  @change="getDataType" :disabled="!isEditing">
          <el-option v-for="item in allDict.edm_field_type" :key="item.id" :label="item.codeName" :value="item.codeValue"></el-option>
        </el-select>
      </el-form-item>-->
      <el-form-item label="数据类型 " prop="edmpDataType">
        <el-select class="input-width-l" v-model="formData.edmpDataType" size="small" :disabled="!isEditing" @change="clearValue" filterable>
          <el-option v-for="item in allDict.edm_const_type" :key="item.id" :label="item.codeName" :value="item.codeValue"></el-option>
        </el-select>
      </el-form-item>
<!--      <el-form-item label="属性限值 " prop="edmpValueLimit">
        <el-input class="input-width-l" v-model="formData.edmpValueLimit" size="small" :disabled="!isEditing"></el-input>
      </el-form-item>
      <el-form-item label="属性公式 " prop="edmpFormula">
        <el-input class="input-width-l" v-model="formData.edmpFormula" size="small" :disabled="!isEditing"></el-input>
      </el-form-item>-->
      <el-form-item label="属性值 " prop="edmpValue">
        <template scope="scope">
          <class-selecter  placeholder="请选择对象" class="input-width-m" v-model="formData.edmpValue" :disabled="!isEditing" v-if="formData.edmpDataType === 'class'"></class-selecter>
          <object-selecter class="input-width-l" v-model="formData.edmpValue" v-if="formData.edmpDataType === 'object'"></object-selecter>
          <el-input class="input-width-l" v-model="formData.edmpValue" size="small"  :disabled="!isEditing" v-if="formData.edmpDataType === 'value'"></el-input>
        </template>
<!--        <el-input class="input-width-l" v-model="formData.edmpValue" size="small" :disabled="!isEditing"></el-input>-->
      </el-form-item>
<!--      <el-form-item label="响应方法 " prop="edmpEdmmId">
        <el-input class="input-width-l" v-model="methodName" size="small" icon="plus"  @focus="OpenTree" @click="OpenTree" :disabled="!isEditing"></el-input>
      </el-form-item>
      <el-form-item label="属性描述 " prop="edmpDesc">
        <el-input class="input-width-l" type="textarea" placeholder="字数请限制在800字符内" v-model="formData.edmpDesc" :rows="4" size="small" :disabled="!isEditing"></el-input>
      </el-form-item>-->
      <el-form-item label="维护人:">
        {{formData.moduser}}
      </el-form-item>
      <el-form-item label="维护时间:">
        {{formData.modtimeStr}}
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
  import { mapGetters } from 'vuex'
  import { dictTypes } from '@/types'
  import {constService} from '@/api/services/const-view'
  import { propertyService } from '@/api'
  import methodClass from '@/components/method-manager/MethodClassify'
  import ClassSelecter from '@/components/commons/class-selecter/ClassSelecter'
  import ObjectSelecter from '@/components/commons/object-selecter/ObjectSelecter'
  export default{
    name: 'constCreated',
    props: ['dataIn'],
    data () {
      return {
       // className: '',
        tempData: {
          edmpEdmcId: '',
          edmpCode: '',
          edmpName: '',
          //  edmpValueType: '',
          edmpDataType: '',
          // edmpValueLimit: '',
          // edmpFormula: '',
          edmpValue: '',
          // edmpEdmmId: '',
          //  edmpDesc: '',
          moduser: '',
          modtimeStr: ''
        },
        formData: {
          edmpEdmcId: '',
        //  edmpCode: '',
          edmpName: '',
        //  edmpValueType: '',
          edmpDataType: '',
         // edmpValueLimit: '',
         // edmpFormula: '',
          edmpValue: '',
         // edmpEdmmId: '',
        //  edmpDesc: '',
          moduser: '',
          modtimeStr: ''
        },
      //  methodName: '',
        edmpDataTypes: [],
        // options: [],
        isUpdating: false,
        isEditing: false,
        rules: {
          edmpName: [{
            required: true,
            message: '请输入属性名称'
          }, {
            validator: (ruls, value, callback) => {
              if (value === this.dataIn.data.edmpName) {
                callback()
              } else {
                // 远端验证版版本号
                constService.checkDataName({params: {edmpName: this.formData.edmpName, edmpEdmcId: this.dataIn.classId}}).then(() => {
                  callback()
                }).catch(err => {
                  callback(err.message)
                })
              }
            },
            trigger: 'blur'
          }],
          edmpCode: [{
            required: true,
            message: '请输入属性编码'
          }, {
            validator: (ruls, value, callback) => {
              if (value === this.dataIn.data.edmpCode) {
                callback()
              } else {
                propertyService.varlidatePropertyCode(this.formData.edmpEdmcId, null, value).then(() => {
                  callback()
                }).catch(() => {
                  callback('属性编码重复')
                })
              }
            },
            trigger: 'blur'
          }],
          edmpValueType: [{ required: true, message: '请选择属性类别', trigger: 'change' }],
          edmpDataType: [{ required: true, message: '请选择数据类型', trigger: 'change' }]
        }
      }
    },
    components: {
      ClassSelecter, ObjectSelecter
    },
    created () {
      this.formData.edmpEdmcId = this.dataIn.classId
      this.formData = this.dataIn.data
      this.UTILS.setDataFromOther(this.tempData, this.formData)
     // console.log(this.dataIn.data.edmpName)
     // this.getEdmpCode()
     // this.getClassName()
      // 获取数据字典
      this.$store.dispatch(dictTypes.GET_DICT_BY_CODES, ['edm_field_type'])
      // this.getOptions()
    },
    computed: {
      ...mapGetters({
        allDict: dictTypes.GET_ALL_DICTS
      })
    },
    methods: {
      getEdmpCode () {
        constService.getEdmpCode(this.formData.edmpEdmcId).then(data => {
         // console.log(JSON.stringify(data))
          this.formData.edmpCode = data
        })
      },
      getClassName () {
        constService.getClass(this.dataIn.classId).then(data => {
          this.className = data.edmcName
        })
      },
      // 获取属性类别下拉列表数据
      getOptions () {
        constService.getEdmpValueType().then(data => {
          this.options = data
        })
      },
      // 获取数据类型下拉列表数据
      getDataType () {
        // console.log(JSON.stringify(this.formData.edmpValueType))
        constService.getDataType(this.formData.edmpValueType).then(data => {
          if (data.length === 0) {
            this.formData.edmpDataType = ''
          }
          this.edmpDataTypes = data
          // console.log(JSON.stringify(data))
        })
      },
      clearValue () {
        this.formData.edmpValue = ''
      },
      // 取消编辑
      cancel () {
        this.isEditing = false
        this.UTILS.setDataFromOther(this.formData, this.tempData)
        this.$emit('cancelEdit')
       // console.log(JSON.stringify(this.tempData))
      },
      // 保存常量
      save () {
        console.log(JSON.stringify(this.formData))
        constService.updateConst(this.formData).then(() => {
          this.$emit('updateSuccess')
          this.$message({
            showClose: true,
            message: '保存成功'
          })
        })
      },
      // 打开方法选择
      OpenTree () {
        this.$openDialog({
          name: 'tree1-dialog',
          component: methodClass,
          options: {
            title: '方法分类',
            customClass: 'dialog-width-xl'
          },
          buttons: [{
            text: '确定',
            icon: 'check',
            type: 'primary',
            handler: () => {
              const result = this.vmPro.getResult()
              if (result) {
                this.methodName = result.name
                this.formData.edmpEdmmId = result.id
                this.$closeDialog('tree1-dialog')
                console.log(JSON.stringify(result) + '...')
              }
              console.log('确定')
            }
          }, {
            text: '取消',
            icon: 'close',
            handler: () => {
              this.$closeDialog('tree1-dialog')
            }
          }]/* ,
           events: {
           changeType: this.changeType
           } */
        }).then(vm => {
          this.vmPro = vm
        })
      }
    }
  }
</script>

<style>

</style>
