<template>
<div>
  <div v-if="isMod">
  <el-button type="primary" icon="rx-bianji2" @click="isEdit=!isEdit" :disabled="!isEdit">编辑</el-button>
  <el-button icon="rx-chexiao" @click="cancel" :disabled="isEdit">取消</el-button>
  </div>
  <el-form :model="formData" :rules="rules" ref="form" class="demo-form-inline">
    <el-form-item label="参数名称" prop="edmaName" label-width="75px">
      <el-input   v-model="formData.edmaName"  size="small" style="width: 215px" :disabled="isEdit"></el-input>
    </el-form-item>
    <el-form-item label="参数类型" prop="edmaDataType" label-width="75px">
    <el-select v-model="formData.edmaDataType" :disabled="isEdit" @change="getName" filterable>
      <el-option v-for="options in allDict.edm_para_type" :key="options.id" :label="options.codeName" :value="options.codeValue"></el-option>
    </el-select>
    </el-form-item>
    <el-form-item label="参数描述" prop="edmaDesc" label-width="75px" >
      <el-input   v-model="formData.edmaDesc"  size="small" style="width: 215px" :disabled="isEdit"></el-input>
    </el-form-item>
  </el-form>
</div>
</template>

<script>
import { mapGetters } from 'vuex'
import { dictTypes } from '@/types'

export default {
  props: ['dataIn'],
  data () {
    return {
      isEdit: false,
      isMod: false,
      tempData: {
        edmaName: '',
        edmaDataType: '',
        edmaDataTypeName: '',
        edmaDesc: ''
      },
      formData: {
        edmaName: '',
        edmaDataType: '',
        edmaDataTypeName: '',
        edmaDesc: ''
      },
      rules: {
        edmaName: [
          { required: true, message: '请输入参数名称', trigger: 'blur' },
          { max: 50, message: '长度不能超过50', trigger: 'blur' },
          {
            validator: (rules, value, callback) => {
              // 与原数据相同
              if (this.dataIn.row && this.dataIn.row.edmaName === value) {
                callback()
              } else {
                if (this.dataIn.data && this.dataIn.data.length !== 0) {
                  for (let obj of this.dataIn.data) {
                    if (obj.edmaName === value) {
                      callback(new Error('参数名重复!'))
                    }
                  }
                  callback()
                } else {
                  callback()
                }
              }
            },
            trigger: 'blur'
          }
        ],
        edmaDataType: [
          { required: true, message: '请选择参数类型', trigger: 'onchange' }
        ]
      }
    }
  },
  computed: {
    ...mapGetters({
      allDict: dictTypes.GET_ALL_DICTS
    })
  },
  created () {
    // 获取数据字典
    console.log(JSON.stringify(this.dataIn.data))
    this.$store.dispatch(dictTypes.GET_DICT_BY_CODES, ['edm_para_type'])
    this.getData()
    this.UTILS.setDataFromOther(this.tempData, this.formData)
  },
  methods: {
    getData () {
      if (this.dataIn.row) {
        this.isEdit = true
        this.isMod = true
        this.formData = this.dataIn.row
      }
    },
    // 取消编辑
    cancel () {
      this.isEdit = !this.isEdit
      console.log(JSON.stringify(this.tempData))
      this.UTILS.setDataFromOther(this.formData, this.tempData)
    },
    getName (type) {
      for (let obj of this.allDict.edm_para_type) {
        if (obj.codeValue === type) {
          this.formData.edmaDataTypeName = obj.codeName
        }
      }
     // console.log(this.formData.edmaDataTypeName)
    },
    getResult () {
      return new Promise((resolve, reject) => {
        this.$refs['form'].validate((valid) => {
          if (valid) {
            const result = this.formData
           // console.log('RE' + JSON.stringify(result))
            resolve(result)
          } else {
            console.log('error submit!!')
            reject()
          }
        })
      })
    }
  }
}
</script>

<style>

</style>
