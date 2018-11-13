<template>
  <div>
    <el-form ref="form" :model="formData" :rules="rules" inline label-width="100px">
      <el-form-item label="卷积公式" prop="edcoConvoluteFormula">
        <el-select placeholder="请选择卷积公式" @change="changeConvoluteFormula" filterable
          v-model="formData.edcoConvoluteFormula" :disabled="mode !== 'edit'">
          <el-option v-for="item in allDicts['edm_convolute_formula']" :key="item.value" :label="item.label" :value="item.value"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button icon="rx-baocun1" type="primary" @click="save" :loading="isSaveing" v-if="mode === 'edit'">保存</el-button>
        <el-button icon="delete" type="danger" @click="remove" :loading="isDeleting" v-if="mode === 'edit' && isUpdate">删除</el-button>
      </el-form-item>
      <el-form-item label="卷积公式描述" class="width-full">
        {{formData.edcoFormulaDesc}}
      </el-form-item>
    </el-form>
    <div class="title-area">
      <span class="title default-color">
        绩效指标集
      </span>
    </div>
    <el-table
      height="223"
      :data="tableData">
      <el-table-column label="序号" type="index" align="center" width="50"></el-table-column>
      <el-table-column label="指标编码" align="center" width="150"></el-table-column>
      <el-table-column label="指标名称" align="center" width="150"></el-table-column>
      <el-table-column label="适用周期" align="center"></el-table-column>
      <el-table-column label="维护人" align="center" width="120"></el-table-column>
      <el-table-column label="维护时间" align="center" width="150"></el-table-column>
    </el-table>
  </div>
</template>
<script>
  import { mapGetters } from 'vuex'
  import { dictTypes } from '@/types'
  import { propertyService } from '@/api'

  export default {
    name: 'property-convolute',
    props: ['propertyId', 'mode'],
    computed: {
      ...mapGetters({
        allDicts: dictTypes.GET_ALL_DICTS
      })
    },
    created () {
      this.$store.dispatch(dictTypes.GET_DICT_BY_CODES, ['edm_convolute_formula']).then(() => {
        // 获得卷积属性
        propertyService.getPropertyConvolute(this.propertyId).then(data => {
          console.log(data)
          if (data) {
            this.UTILS.setDataFromOther(this.formData, data)
            this.isUpdate = true
          } else {
            this.formData.edcoEdmpId = this.propertyId
          }
        })
      })
    },
    data () {
      return {
        // 是否正在保存
        isSaveing: false,
        // 是否正则删除
        isDeleting: false,
        // 是否是编辑模式
        isUpdate: false,
        tableData: [],
        formData: {
          id: '',
          edcoEdmpId: '',
          edcoConvoluteFormula: '',
          edcoFormulaDesc: ''
        },
        rules: {
          edcoConvoluteFormula: [{
            required: true,
            message: '请选择卷积公式'
          }]
        }
      }
    },
    methods: {
      changeConvoluteFormula (value) {
        for (var index in this.allDicts.edm_convolute_formula) {
          const item = this.allDicts.edm_convolute_formula[index]
          if (item.value === value) {
            this.formData.edcoFormulaDesc = item.codeDesc
            break
          }
        }
      },
      remove () {
        this.$confirm('确定删除？', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.isDeleting = true
          propertyService.deletePropertyConvolute(this.formData.id).then(data => {
            this.isUpdate = false
            this.isDeleting = false
            this.formData = {
              id: '',
              edcoEdmpId: this.propertyId,
              edcoConvoluteFormula: '',
              edcoFormulaDesc: ''
            }
          }).catch(() => {
            this.isDeleting = false
          })
        })
      },
      save () {
        // 验证表单
        this.$refs.form.validate(result => {
          if (result) {
            this.isSaveing = true
            if (this.isUpdate) {
              // 修改卷积公式
              propertyService.updateConvolute(this.formData).then(data => {
                this.$message.success('修改成功')
                this.isSaveing = false
              }).catch(() => {
                this.isSaveing = false
              })
            } else {
              // 保存卷积公式
              propertyService.saveConvolute(this.formData).then(data => {
                this.$message.success('保存成功')
                this.isUpdate = true
                this.isSaveing = false
                this.formData.id = data
              }).catch(() => {
                this.isSaveing = false
              })
            }
          }
        })
      }
    }
  }
</script>
<style scoped>
  .width-full{
    width: 100%;
  }
  .title-area{
    margin-top: 10px;
    background-color: #F1F3FF;
    height: 37px;
    line-height: 37px;
    border: solid 1px #DFE6EC;
    border-bottom: none;
  }
  .title{
    float:left;
    font-size: 14px;
    font-weight: bold;
    margin-left: 20px;
  }
</style>
