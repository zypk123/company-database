<template>
<div id="atta-define">
  <div class="button-area">
    <el-button type="info" icon="rx-baocun2" :loading="isSaving" @click="save('form')" :disabled="upload">保存</el-button>
  </div>
  <el-form class="form" :model="formData" label-width="86px" :rules="rules" ref="form">
    <el-form-item label="附件类型 " prop="edmaType">
      <el-select class="input-width-s" v-model="formData.edmaType" size="small"  filterable>
        <el-option v-for="item in options" :key="item.id" :label="item.codeName" :value="item.codeValue"></el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="附件名称 " prop="edmaName">
      <el-input class="input-width-l" v-model="formData.edmaName" size="small" :maxlength="20"
                @change="checkVersionSuccess = false"></el-input>
      <i class="el-icon-check color-s" v-show="checkVersionSuccess"></i>
    </el-form-item>
    <el-form-item label="附件路径 " prop="edmaPath">
      <div v-if="formData.edmaType === 1 || formData.edmaType === '1'">
        <el-input v-model="formData.edmaPath"></el-input>
      </div>
      <div v-else-if="formData.edmaType === 2 || formData.edmaType === '2'">
        <el-upload
          :action="CONFIG.baseUrl.upload+'attachments/upload'"
          :on-remove="handleRemove"
          :on-progress="handleProgress"
          :on-success="handleSuccess"
          class="upload-demo"
          :on-preview="handlePreview"
          :file-list="fileList"
          :on-change="onChange"
          ref="upload">
          <el-button size="small" type="primary">浏览</el-button>
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
        upload: false,
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
        isSaving: false,
        rules: {
          edmaType: [{ required: true, message: '请选择附件类型', trigger: 'change' }],
          edmaName: [{
            required: true,
            message: '请输入附件名称'
          }, {
            validator: (ruls, value, callback) => {
              // 远端验证版版本号
              basicInfoService.checkDataName({params: {edmaName: value, edmaEdmcId: this.dataIn.classId}}).then(() => {
              // 检查通过
                this.checkVersionSuccess = true
                callback()
              }).catch(err => {
                callback(err.message)
              })
            },
            trigger: 'blur'
          }]
        }
      }
    },
    created () {
      this.getType()
      this.formData.edmaEdmcId = this.dataIn.classId
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
      // 文件正在上传时
      handleProgress () {
        this.upload = true
      },
      //  文件上传成功时
      handleSuccess (response, file, fileList) {
        this.upload = false
        console.log(response.data.edmaSourceName)
        this.formData.edmaSourceName = response.data.edmaSourceName
        this.formData.edmaPath = response.data.edmaPath
      },
      // 点击已上传的文件链接时的钩子, 可以通过 file.response 拿到服务端返回数据
      handlePreview (file) {
        console.log(file)
      },
      save (formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            console.log(JSON.stringify(this.formData))
            basicInfoService.saveAttachment(this.formData).then(() => {
              this.$emit('createSuccess')
              this.$message({
                showClose: true,
                message: '新增成功'
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
