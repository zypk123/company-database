<template>
  <div id="atta-define">
    <div class="button-area">
      <el-button type="primary" icon="rx-bianji2" @click="isEditing = true" :disabled="isEditing">编辑</el-button>
      <el-button type="primary" icon="rx-baocun2" :disabled="!isEditing" @click="save('form')" :loading="isUpdating">保存</el-button>
      <el-button icon="rx-chexiao" @click="cancel" :disabled="!isEditing">取消</el-button>
    </div>
    <el-form class="form" :model="formData" label-width="86px" :rules="rules" ref="form">
      <el-form-item label="附件类型 " prop="edmaType">
        <el-select class="input-width-s" v-model="formData.edmaType" size="small"  :disabled="!isEditing" filterable>
          <el-option v-for="item in options" :key="item.id" :label="item.codeName" :value="item.codeValue"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="附件名称 " prop="edmaName">
        <el-input class="input-width-l" v-model="formData.edmaName" size="small" :maxlength="20" :disabled="!isEditing"
                  @change="checkVersionSuccess = false"></el-input>
        <i class="el-icon-check color-s" v-show="checkVersionSuccess"></i>
      </el-form-item>
      <el-form-item label="附件路径 " prop="edmaPath">
        <div v-if="formData.edmaType === 1 || formData.edmaType === '1'">
          <el-input v-model="formData.edmaPath" :disabled="!isEditing"></el-input>
        </div>
        <div v-else-if="formData.edmaType === 2 || formData.edmaType === '2'">
          <el-upload
            :action="CONFIG.baseUrl.upload+'attachments/upload'"
            :on-remove="handleRemove"
            class="upload-demo"
            :on-success="handleSuccess"
            :on-preview="handlePreview"
            :file-list="fileList"
            :on-change="onChange"
            ref="upload">
            {{formData.edmaSourceName}}&nbsp;&nbsp;
            <el-button size="small" type="primary" :disabled="!isEditing">浏览</el-button>
          </el-upload>
        </div>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
  import {basicInfoService} from '@/api/services/basic-info-view'
  export default {
    name: 'attaDefine',
    props: ['dataIn'],
    data () {
      return {
        options: [],
        tempData: {
          edmaType: '',
          edmaName: '',
          edmaPath: '',
          edmaEdmcId: '',
          edmaSourceName: ''
        },
        formData: {
          edmaType: '',
          edmaName: '',
          edmaPath: '',
          edmaEdmcId: '',
          edmaSourceName: ''
        },
        fileList: [],
        // 版本号验证成功标识
        checkVersionSuccess: false,
        isUpdating: false,
        isEditing: false,
        rules: {
          edmaType: [{ required: true, message: '请选择附件类型', trigger: 'change' }],
          edmaName: [{
            required: true,
            message: '请输入附件名称'
          }, {
            validator: (ruls, value, callback) => {
              if (value === this.dataIn.data.edmaName) {
                callback()
              } else {
                // 远端验证版版本号
                basicInfoService.checkDataName({params: {edmaName: value, edmaEdmcId: this.dataIn.classId}}).then(() => {
                  // 检查通过
                  this.checkVersionSuccess = true
                  callback()
                }).catch(err => {
                  callback(err.message)
                })
              }
            },
            trigger: 'blur'
          }]
        }
      }
    },
    created () {
      this.getType()
      this.formData = this.dataIn.data
      this.formData.edmaType = this.formData.edmaType.toString()
      this.UTILS.setDataFromOther(this.tempData, this.formData)
      console.log(JSON.stringify(this.formData))
    },
    methods: {
      // 查询下拉列表选项
      getType () {
        basicInfoService.queryType().then(data => {
          this.options = data
        })
      },
      // 文件列表移除文件时的钩子
      handleRemove (file, fileList) {
        console.log(file, fileList)
      },
      // 点击已上传的文件链接时的钩子, 可以通过 file.response 拿到服务端返回数据
      handlePreview (file) {
        console.log(file)
      },
      //  文件上传成功时
      handleSuccess (response, file, fileList) {
        this.upload = false
        console.log(JSON.stringify(response))
        this.formData.edmaSourceName = response.data.edmaSourceName
        this.formData.edmaPath = response.data.edmaPath
      },
      // 取消编辑
      cancel () {
        this.isEditing = false
        this.UTILS.setDataFromOther(this.formData, this.tempData)
        this.$emit('cancelEdit')
        // console.log(JSON.stringify(this.tempData))
      },
      save (formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            console.log('save' + JSON.stringify(this.formData))
            basicInfoService.updataAttachement(this.formData).then(() => {
              this.$emit('updateSuccess')
              this.$message({
                showClose: true,
                message: '保存成功'
              })
            })
          } else {
            return false
          }
        })
      },
      onChange (file, fileList) {
        if (file.status === 'fail') {
          this.$message({
            type: 'info',
            message: '文件过大!'
          })
        }
        this.fileList = fileList.slice(-1)
      }
    }
  }
</script>

<style>

</style>
