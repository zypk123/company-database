<template>
  <div v-if="loaded">
    <el-form ref="form" :model="formData" inline class="form">
      <el-form-item label="联动方式：" label-width="80px">
        <el-select class="input-width-s" v-model="formData.edcnType" filterable>
          <el-option v-for="item in allDicts['edm_connect_type']" :key="item.value" :label="item.label" :value="item.value"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="保存联动记录：" label-width="110px" style="width:700px">
        <el-switch on-text="是" off-text="否" :on-value="1" :off-value="0" v-model="formData.edcnLinkPreservable"
          :disabled="mode !== 'edit'"></el-switch>
      </el-form-item>
      <el-form-item label="联动更新时效：">
        <el-radio-group v-model="formData.edcnUpdateType" :disabled="mode !== 'edit'">
          <el-radio v-for="item in allDicts.edm_update_type" :key="item.value" :label="item.value">{{item.label}}</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="优先级：" label-width="80px" v-if="formData.edcnUpdateType === 'async'">
        <el-select class="input-width" v-model="formData.asyncTypePriority" :disabled="mode !== 'edit'" filterable>
          <el-option v-for="item in allDicts['edm_asyncType_priority']" :key="item.value" :label="item.label" :value="item.value"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="时间：" label-width="70px" v-if="formData.edcnUpdateType === 'time'">
        <el-time-select :disabled="mode !== 'edit'"
          v-model="formData.edcnUpdateTime"
          :editable="false"
          :clearable="false"
          popper-class="el-time-selecter-width"
          :picker-options="{
            start: '00:00',
            step: '00:15',
            end: '23:45'
          }"
          placeholder="选择时间"></el-time-select>
      </el-form-item>
      <el-form-item>
        <el-button icon="rx-baocun1" type="primary" @click="save" :loading="isSaveing" v-if="mode === 'edit'">保存</el-button>
        <el-button icon="delete" type="danger" @click="remove" :loading="isDeleting" v-if="mode === 'edit' && isUpdate">删除</el-button>
      </el-form-item>
    </el-form>
    <el-table
      height="271"
      :data="tableData">
      <el-table-column label="序号" type="index" align="center" width="50"></el-table-column>
      <el-table-column label="类名" align="center" width="150" prop="edmcName"></el-table-column>
      <el-table-column label="属性名" align="center" width="150" prop="edmpName"></el-table-column>
      <el-table-column label="触发条件" align="center">
         <template scope="scope">
          <el-tooltip class="item" effect="dark" :content="scope.row.condDesc" placement="top">
            <span v-if="scope.row.condName" style="white-space:nowrap">{{scope.row.condName}}</span>
            <span v-else style="white-space:nowrap">{{scope.row.condDesc}}</span>
          </el-tooltip>
        </template>
      </el-table-column>
      <el-table-column label="对象定位公式" align="center">
        <template scope="scope">
          <el-tooltip class="item" effect="dark" :content="scope.row.formulaDesc" placement="top">
            <span v-if="scope.row.formula" style="white-space:nowrap;">{{scope.row.formula}}</span>
            <span v-else style="white-space:nowrap">{{scope.row.formulaDesc}}</span>
          </el-tooltip>
        </template>
      </el-table-column>
      <el-table-column label="维护人" align="center" width="120" prop="moduser"></el-table-column>
      <el-table-column label="维护时间" align="center" width="150" prop="modtimeStr"></el-table-column>
    </el-table>
  </div>
</template>
<script>
  import { mapGetters } from 'vuex'
  import { dictTypes } from '@/types'
  import { propertyService } from '@/api'

  export default {
    name: 'property-connect',
    props: ['propertyId', 'mode'],
    created () {
      // 获取数据字典
      this.$store.dispatch(dictTypes.GET_DICT_BY_CODES, ['edm_asyncType_priority', 'edm_update_type', 'edm_connect_type'])

      // 查询联动属性
      propertyService.getPropertyConnect(this.propertyId).then(data => {
        this.tableData = data
      })

      // 查询联动配置
      propertyService.getConnectSetting(this.propertyId).then(data => {
        if (data) {
          this.isUpdate = true
          this.UTILS.setDataFromOther(this.formData, data)
        } else {
          this.formData.edcnEdmpId = this.propertyId
        }
        this.loaded = true
      })
    },
    computed: {
      ...mapGetters({
        'allDicts': dictTypes.GET_ALL_DICTS
      })
    },
    data () {
      return {
        // 保存标识
        isSaveing: false,
        // 删除标识
        isDeleting: false,
        // 是否是更新
        isUpdate: false,
        // 是否正则加载中
        loaded: false,
        // 列表数据
        tableData: [],
        // 表单数据
        formData: {
          id: '',
          // 联动方式
          edcnType: 'sum',
          // 属性ID
          edcnEdmpId: '',
          // 保存联动记录
          edcnLinkPreservable: 1,
          // 联动时效
          edcnUpdateType: 'sync',
          // 时间
          edcnUpdateTime: '00:00',
          // 优先级
          asyncTypePriority: '3'
        }
      }
    },
    methods: {
      save () {
        this.isSaveing = true
        if (this.isUpdate) {
          propertyService.updatePropertyConnect(this.formData).then(() => {
            this.$message.success('更新成功')
            this.isSaveing = false
          }).catch(() => {
            this.isSaveing = false
          })
        } else {
          propertyService.updatePropertyConnect(this.formData).then((data) => {
            this.$message.success('保存成功')
            this.isUpdate = true
            this.isSaveing = false
            this.formData.id = data
          }).catch(() => {
            this.isSaveing = false
          })
        }
      },
      remove () {
        this.$confirm('确定删除？', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.isDeleting = true
          propertyService.deletePropertyConnect(this.formData.id).then(data => {
            this.isUpdate = false
            this.isDeleting = false
            this.formData = {
              id: '',
              // 联动方式
              edcnType: 'sum',
              // 属性ID
              edcnEdmpId: this.propertyId,
              // 保存联动记录
              edcnLinkPreservable: 1,
              // 联动时效
              edcnUpdateType: 'sync',
              // 时间
              edcnUpdateTime: '00:00',
              // 优先级
              asyncTypePriority: '3'
            }
          }).catch(() => {
            this.isDeleting = false
          })
        })
      }
    }
  }
</script>
<style scoped>
  .button-area{
    margin-bottom: 20px;
  }
  .full{
    width: 100%;
  }
  .input-width{
    width: 120px;
  }
</style>
