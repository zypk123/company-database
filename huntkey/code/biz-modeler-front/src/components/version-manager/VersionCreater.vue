<template>
  <div id="model-define">
    <div class="button-area">
      <el-button type="info" icon="rx-baocun2" :loading="isSaving" @click="save">保存</el-button>
    </div>
    <el-form class="form" :model="formData" label-width="86px" :rules="rules" ref="form">
      <el-form-item label="模型版本 " prop="edmdVer">
        <el-input class="input-width-l" v-model="formData.edmdVer" size="small" :maxlength="20"
          @change="checkVersionSuccess = formData.edmdVer === checkedVersion ? true:false"></el-input>
        <i class="el-icon-check color-s" v-show="checkVersionSuccess"></i>
      </el-form-item>
      <el-form-item label="模型状态 " prop="edmdStatus">
        <el-select class="input-width-s" v-model="formData.edmdStatus" size="small" :disabled="true" filterable>
          <el-option v-for="item in allDict.edm_edmd_status" :key="item.value" :label="item.label" :value="item.value"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="更新说明 " prop="edmdUpdateDesc">
        <el-input class="input-width-l" type="textarea" placeholder="字数请限制在800字符内" v-model="formData.edmdUpdateDesc" :rows="4" :maxlength="800"></el-input>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
  import { mapGetters } from 'vuex'
  import { dictTypes } from '@/types'
  import { versionService } from '@/api'

  export default {
    computed: {
      ...mapGetters({
        allDict: dictTypes.GET_ALL_DICTS
      })
    },
    data () {
      return {
        // 表单数据
        formData: {
          edmdVer: null,
          edmdStatus: '1',
          edmdUpdateDesc: null
        },
        // 版本号验证成功标识
        checkVersionSuccess: false,
        // 是否正在保存操作
        isSaving: false,
        // 表单规则
        rules: {
          edmdVer: [{
            required: true,
            message: '请输入版本号'
          }, {
            validator: (ruls, value, callback) => {
              // 远端验证版版本号
              if (!this.UTILS.validate(value, 'version')) {
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
    created () {
      // 获取数据字典
      this.$store.dispatch(dictTypes.GET_DICT_BY_CODES, ['edm_edmd_status'])
      // 获取最大版本号
      versionService.getMaxVersion().then(data => {
        this.formData.edmdVer = data
      })
    },
    methods: {
      save () {
        this.$refs.form.validate(result => {
          if (result) {
            this.isSaving = true
            versionService.saveVersion(this.formData).then(() => {
              // 保存成功
              this.$emit('createSuccess')
            }).catch(() => {
              this.isSaving = false
            })
          }
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
