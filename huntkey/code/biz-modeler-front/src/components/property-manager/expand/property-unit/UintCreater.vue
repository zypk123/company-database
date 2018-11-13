<template>
  <div>
    <div class="button-area">
      <el-button type="primary" icon="rx-baocun1" :loading="isSaveing" @click="save">保存</el-button>
      <el-button icon="circle-close" @click="cancel">取消</el-button>
    </div>
    <el-form :model="formData" ref="form" :rules="rules">
      <el-form-item label="属性" label-width="80px" prop="edunQtyEdmpId">
        <el-select placeholder="请选择属性" v-model="formData.edunQtyEdmpId" filterable>
          <el-option v-for="item in dataIn.unitProperties" :key="item.id" :label="item.edmpName" :value="item.id"
          :disabled="checkDisabled(item.id)"></el-option>
        </el-select>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
  import { propertyService } from '@/api'

  export default {
    props: ['dataIn'],
    created () {
      this.formData.edunEdmpId = this.dataIn.propertyId
    },
    data () {
      return {
        isSaveing: false,
        formData: {
          edunEdmpId: '',
          edunQtyEdmpId: ''
        },
        rules: {
          edunQtyEdmpId: [{
            required: true,
            message: '请选择属性'
          }]
        }
      }
    },
    methods: {
      checkDisabled (id) {
        return this.dataIn.selectedIds.indexOf(id) >= 0
      },
      save () {
        // 验证表单
        this.$refs.form.validate(result => {
          if (result) {
            this.isSaveing = true
            propertyService.createUnit(this.formData).then(() => {
              this.$emit('createSuccess')
              this.isSaveing = false
            }).catch(() => {
              this.isSaveing = false
            })
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
</style>
