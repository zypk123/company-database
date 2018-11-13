<template>
  <div id="atta-define">
    <div class="button-area">
      <el-button type="info" icon="rx-baocun2" :loading="isSaving" @click="save">保存</el-button>
      &nbsp;&nbsp;&nbsp;
      <el-checkbox v-model="extendParentAttr">继承父类属性</el-checkbox>
    </div>
    <el-form class="form"  :model="formData" label-width="86px" :rules="rules" ref="form">
<!--      <el-form-item label="所属类：" >
        <label >{{className}}</label>
      </el-form-item>
      <el-form-item label="属性编码 " prop="edmpCode">
        <label >{{formData.edmpCode}}</label>
      </el-form-item>-->
      <el-form-item label="父类属性" v-show="extendParentAttr" required :error="errorMessage">
        <el-select class="input-width-l" v-model="parentAttr" @change="chooseParentAttr">
          <el-option v-for="item in dataIn.parentAttrs" :key="item.id" :label="item.edmpName" :value="item.id"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="属性编码" prop="edmpCode">
        <el-input class="input-width-l" v-model="formData.edmpCode" size="small" :maxlength="20"
                  :disabled="extendParentAttr"></el-input>
      </el-form-item>
      <el-form-item label="属性名称 " prop="edmpName">
        <el-input class="input-width-l" v-model="formData.edmpName" size="small" :maxlength="20"
                  :disabled="extendParentAttr"></el-input>
      </el-form-item>
      <!--<el-form-item label="属性类别 " prop="edmpValueType">
        <el-select class="input-width-l" v-model="formData.edmpValueType" size="small"  @change="getDataType">
          <el-option v-for="item in allDict.edm_field_type" :key="item.id" :label="item.codeName" :value="item.codeValue"></el-option>
        </el-select>
      </el-form-item>-->
      <el-form-item label="数据类型 " prop="edmpDataType">
        <el-select class="input-width-l" v-model="formData.edmpDataType" size="small"  @change="clearValue" filterable
          :disabled="extendParentAttr">
          <el-option v-for="item in allDict.edm_const_type" :key="item.id" :label="item.codeName" :value="item.codeValue"></el-option>
        </el-select>
      </el-form-item>
     <!-- <el-form-item label="属性限值 " prop="edmpValueLimit">
        <el-input class="input-width-l" v-model="formData.edmpValueLimit" size="small" ></el-input>
      </el-form-item>
      <el-form-item label="属性公式 " prop="edmpFormula">
        <el-input class="input-width-l" v-model="formData.edmpFormula" size="small" ></el-input>
      </el-form-item>-->
      <el-form-item label="属性值 " prop="edmpValue">
        <template scope="scope">
        <class-selecter  placeholder="请选择对象" class="input-width-l" v-model="formData.edmpValue"
        v-if="formData.edmpDataType === 'class'"></class-selecter>
        <object-selecter class="input-width-l" v-model="formData.edmpValue" v-if="formData.edmpDataType === 'object'"
        ></object-selecter>
        <!-- <el-input class="input-width-l" v-model="formData.edmpValue" size="small"  v-if="formData.edmpDataType === 'object'"></el-input> -->
        <el-input class="input-width-l" v-model="formData.edmpValue" size="small"  v-if="formData.edmpDataType === 'value'"
        ></el-input>
        </template>
      </el-form-item>
<!--      <el-form-item label="响应方法 " prop="edmpEdmmId">
        <el-input class="input-width-l" v-model="methodName" size="small" icon="plus"  @focus="OpenTree" @click="OpenTree" ></el-input>
      </el-form-item>
      <el-form-item label="属性描述 " prop="edmpDesc">
        <el-input class="input-width-l" type="textarea" placeholder="字数请限制在800字符内" v-model="formData.edmpDesc" :rows="4" size="small"></el-input>
      </el-form-item>-->
      <!-- <el-form-item label="维护人:">
        {{formData.moduser}}
      </el-form-item>
      <el-form-item label="维护时间:">
        {{formData.modtimeStr}}
      </el-form-item> -->
    </el-form>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import { dictTypes } from '@/types'
import { propertyService } from '@/api'
import {constService} from '@/api/services/const-view'
import methodClass from '@/components/method-manager/MethodClassify'
import ClassSelecter from '@/components/commons/class-selecter/ClassSelecter'
import ObjectSelecter from '@/components/commons/object-selecter/ObjectSelecter'
export default{
  name: 'constCreated',
  props: ['dataIn'],
  data () {
    return {
      // 继承父类属性
      extendParentAttr: false,
      // 父类属性
      parentAttr: null,
      // 属性验证错误信息
      errorMessage: '',
     // className: '',
      formData: {
        edmpEdmcId: '',
        edmpCode: '',
        edmpName: '',
       // edmpValueType: '',
        edmpDataType: 'value',
       // edmpValueLimit: '',
       // edmpFormula: '',
        edmpValue: ''
       // edmpEdmmId: '',
       // edmpDesc: '',
      },
     // methodName: '',
      edmpDataTypes: [],
     // options: [],
      isSaving: false,
      rules: {
        edmpName: [{
          required: true,
          message: '请输入属性名称'
        }, {
          validator: (ruls, value, callback) => {
            // 远端验证版版本号
            constService.checkDataName({params: {edmpName: this.formData.edmpName, edmpEdmcId: this.dataIn.classId}}).then(() => {
              // 检查通过
              callback()
            }).catch(err => {
              callback(err.message)
            })
          },
          trigger: 'blur'
        }],
        edmpCode: [{
          required: true,
          message: '请输入属性编码'
        }, {
          validator: (ruls, value, callback) => {
            propertyService.varlidatePropertyCode(this.formData.edmpEdmcId, null, value).then(() => {
              callback()
            }).catch(() => {
              callback('属性编码重复')
            })
          },
          trigger: 'blur'
        }],
       // edmpValueType: [{ required: true, message: '请选择属性类别', trigger: 'change' }],
        edmpDataType: [{ required: true, message: '请选择数据类型', trigger: 'change' }]
      }
    }
  },
  components: {
    ClassSelecter, ObjectSelecter
  },
  created () {
    this.formData.edmpEdmcId = this.dataIn.classId
   // this.getEdmpCode()
   // this.getClassName()
    // 获取数据字典
    this.$store.dispatch(dictTypes.GET_DICT_BY_CODES, ['edm_const_type'])
    // this.getOptions()
  },
  computed: {
    ...mapGetters({
      allDict: dictTypes.GET_ALL_DICTS
    })
  },
  methods: {
    // 选择父类属性
    chooseParentAttr (id) {
      this.errorMessage = ''
      this.chooseFlag = true
      const parentAttr = this.dataIn.parentAttrs.find(item => item.id === id)
      this.UTILS.setDataFromOther(this.formData, parentAttr)
      this.formData.edmpEdmcId = this.dataIn.classId
      this.$nextTick(() => {
        this.chooseFlag = false
      })
    },
    // 获取类编码，此界面不使用
    getEdmpCode () {
      constService.getEdmpCode(this.formData.edmpEdmcId).then(data => {
        this.formData.edmpCode = data
      })
    },
    // 获取类名，此界面不使用
    getClassName () {
      constService.getClass(this.dataIn.classId).then(data => {
        this.className = data.edmcName
      })
    },
    // 获取属性类别下拉列表数据,不使用
    getOptions () {
      constService.getEdmpValueType().then(data => {
        this.options = data
      })
    },
    // 获取数据类型下拉列表数据,不使用
    getDataType () {
      constService.getDataType(this.formData.edmpValueType).then(data => {
        if (data.length === 0) {
          this.formData.edmpDataType = ''
        }
        this.edmpDataTypes = data
      })
    },
    clearValue () {
      if (this.chooseFlag) return
      this.formData.edmpValue = ''
    },
    // 保存常量
    save () {
      // 验证继承父类
      if (this.extendParentAttr && !this.parentAttr) {
        this.errorMessage = '请选择父类属性'
        return
      }
      // this.formData.edmpValueType = 1
      this.$refs.form.validate(result => {
        if (result) {
          constService.saveConst(this.formData).then(() => {
            this.$emit('createSuccess')
            this.$message({
              showClose: true,
              message: '新增成功'
            })
          })
        }
      })
    },
    // 打开方法选择,不使用
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
            }
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
