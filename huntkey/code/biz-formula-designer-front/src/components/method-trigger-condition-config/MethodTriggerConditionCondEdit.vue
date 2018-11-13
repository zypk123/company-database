<template>
  <div class="prop-condition-edit" v-loading="!pageConfig.pageIniting" element-loading-text="正在初始化页面">
    <!--按钮区域-->
    <div class="button-area">
      <el-button type="primary" icon="save" @click="save(false)" :loading="pageConfig.isSaving">保存</el-button>
      <el-button icon="save" @click="save(true)" :loading="pageConfig.isSaving" v-if="initData.mode==='add'">保存并继续</el-button>
      <el-button icon="cancel" @click="close">取消</el-button>
    </div>
    <!-- 表单区域 -->
    <div class="form-area">
      <el-form label-width="100px" :model="formData" ref="form" :rules="formRules">
        <el-form-item label="属性：" prop="cndrPropOriginCode">
          <el-select v-model="formData.cndrPropOriginCode" class="input-width-full">
            <el-option v-for="aProp in this.initData.prevRelProp"
              :label="aProp.edmpName"
              :value="aProp.edmpCode"
              :key="aProp.edmpCode" >
            </el-option>
          </el-select>
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

        <el-form-item label="值:常量" prop="cndrProp2OriginCode" v-if="formData.cndrValueType==='const'">
          <el-input v-model="formData.cndrProp2OriginCode" class="input-width-full"></el-input>
        </el-form-item>

        <el-form-item label="值:变量" prop="cndrProp2OriginCode" v-if="formData.cndrValueType==='variable'">
          <el-select v-model="formData.cndrProp2OriginCode" filterable placeholder="请选择" class="input-width-full">
            <el-option
              v-for="item in availableVars"
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
import { mapGetters } from 'vuex'
export default {
  name: 'methodTriggerConditionCondEdit',
  props: ['initData'],
  data () {
    return {
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
        cndrPropOriginCode: '',
        cndrProp2OriginCode: '',
        isenable: 1
      },
      availableVars: [],
      formRules: {
        cndrPropOriginCode: [
          {required: true, message: '属性不能为空', trigger: 'blur'}
        ],
        cndrOperate: [
          {required: true, message: '条件不能为空', trigger: 'blur'}
        ],
        cndrProp2OriginCode: [
          {required: true, message: '值不能为空', trigger: 'blur'}
        ]
      }
    }
  },
  computed: {
    ...mapGetters({
      compareTypeDict: 'getCndtOperatorDict',
      compareTargetTypeDict: 'getCndtValueTypeDict4PropLimit'
    })
  },
  created () {
    console.log('initData', this.initData)
    this.init()
  },
  methods: {
    init () {
      // console.log(this.initData)
      this.pageCommon.cndrIndex = this.initData.index
      this.Utils.clonePropsWhenExist(this.formData, this.initData.cond)
      this.availableVars = this.initData.variableData
    },
    save (addNext) {
      this.$refs.form.validate((valid) => {
        if (valid) {
          let condData = this.Utils.cloneDeep(this.formData)
          condData.cndrProp1Code = condData.cndrPropOriginCode
          condData.cndrProp = this.initData.clssName + '.' + this.getEdmpNameByEdmpCode(condData.cndrPropOriginCode)
          condData.cndrProp2 = condData.cndrValueType === 'const' ? condData.cndrProp2OriginCode : this.getVrntById(condData.cndrProp2OriginCode)
          condData.cndrSeq = this.pageCommon.cndrIndex + 1
          let index = this.pageCommon.cndrIndex
          this.$emit('saveCond', {index, condData})
          if (addNext) {
            this.formData.cndrPropOriginCode = ''
            this.formData.cndrProp2OriginCode = ''
            this.formData.cndrValueType = 'const'
            this.formData.cndeSeq += 1
            this.pageCommon.cndrIndex += 1
          } else {
            this.$emit('close')
          }
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
    /**
     * 通过属性Code查找属性Name
     */
    getEdmpNameByEdmpCode (edmpCode) {
      for (var i = 0; i < this.initData.prevRelProp.length; i++) {
        if (edmpCode === this.initData.prevRelProp[i].edmpCode) {
          return this.initData.prevRelProp[i].edmpName
        }
      }
    },
    cndrValueTypeChange () {
      this.formData.cndrProp2OriginCode = ''
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
