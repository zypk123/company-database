<template>
  <div id="model-define">
    <div class="button-area">
      <el-button type="primary" icon="rx-bianji2" @click="isEditing = true" :disabled="isEditing || formData.edmdStatus === 6 || formData.edmdStatus === 7 ">编辑</el-button>
      <el-button type="primary" icon="rx-baocun2" :disabled="!isEditing" @click="save" :loading="isUpdating">保存</el-button>
      <el-button icon="rx-chexiao" @click="cancel" :disabled="!isEditing">取消</el-button>
      <el-button type="danger" icon="rx-shanchu2" :disabled="!isEditing" :loading="isDeleting" @click="remove">删除</el-button>
    </div>
    <el-form class="form" :model="formData" label-width="86px" :rules="rules" ref="form" :maxlength="20">
      <el-form-item label="模型版本:" prop="edmdVer">
        <el-input class="input-width-l" type="text" v-model="formData.edmdVer" size="small" :disabled="!isEditing"
        @change="checkVersionSuccess = false"></el-input>
        <i class="el-icon-check color-s" v-show="checkVersionSuccess"></i>
      </el-form-item>
      <el-form-item label="模型状态:" prop="edmdStatus">
        <el-select class="input-width-s" size="small" v-model="formData.edmdStatus"  :disabled="!isEditing" filterable>
          <el-option v-for="item in allDict.edm_edmd_status" :key="item.value" :label="item.label" :value="parseInt(item.value)" :maxlength="800"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="更新说明:" prop="edmdUpdateDesc" >
        <el-input class="input-width-l" type="textarea" placeholder="字数请限制在800字符内" v-model="formData.edmdUpdateDesc" :rows="4" size="small" :disabled="!isEditing"
        ></el-input>
      </el-form-item>
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
  import { versionService } from '@/api'

  export default{
    props: ['dataIn'],
    created () {
      // 设置回显数据
      this.UTILS.setDataFromOther(this.formData, this.dataIn.versionData)
      // 获取数据字典
      this.$store.dispatch(dictTypes.GET_DICT_BY_CODES, ['edm_edmd_status'])
    },
    computed: {
      ...mapGetters({
        allDict: dictTypes.GET_ALL_DICTS
      })
    },
    data () {
      return {
        // 是否正则编辑标识
        isEditing: false,
        // 初始表单数据
        formData: {
          id: null,
          edmdVer: null,
          edmdStatus: null,
          edmdUpdateDesc: null,
          moduser: null,
          modtimeStr: null
        },
        // 版本号验证成功标识
        checkVersionSuccess: false,
        // 是否正在保存操作
        isUpdating: false,
        // 是否正在删除操作
        isDeleting: false,
        // 验证规则
        rules: {
          edmdVer: [{
            required: true,
            message: '请输入版本号'
          }, {
            validator: (rules, value, callback) => {
              // 与原数据相同
              if (value === this.dataIn.versionData.edmdVer) {
                callback()
              } else if (!this.UTILS.validate(value, 'version')) {
                callback('版本号格式不正确')
              } else {
                versionService.checkVersion(value).then(() => {
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
    methods: {
      // 取消
      cancel () {
        this.isEditing = false
        this.UTILS.setDataFromOther(this.formData, this.dataIn.versionData)
      },
      // 保存更新
      save () {
        // 表单验证
        this.$refs.form.validate(result => {
          if (result) {
            this.isUpdating = true
            versionService.updateVersion(this.formData).then(() => {
              // 保存成功，触发父组件事件
              this.$message.success('保存成功！')
              this.$emit('updateSuccess')
              this.isUpdating = false
              // 关闭编辑
              this.isEditing = false
            }).catch(() => {
              this.isUpdating = false
            })
          }
        })
      },
      // 删除版本
      remove () {
        this.$confirm('是否删除该版本？确认则该模型下所有数据删除！！！', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.isDeleting = true
          versionService.removeVersion(this.formData.id).then(() => {
            // 删除更改
            this.$message.success('删除成功！')
            this.$emit('deleteSuccess')
          }).catch(() => {
            this.isDeleting = false
          })
        })
      }
    }
  }
</script>

<style scoped>
.form{
  margin-top: 20px;
}
.button-area {
  margin-left: 15px
}
</style>
