<template>
  <div>
    <div class="button-area">
      <span class="title default-color">
        关联属性集
      </span>
      <span class="button" v-if="mode === 'edit'">
        <el-button type="primary" icon="plus" @click="create">新增</el-button>
        <el-button type="danger" icon="delete2" @click="removeUnitProperties">删除</el-button>
      </span>
    </div>
    <el-table
      height="333"
      ref="currentTable"
      :data="tableData"
      @selection-change="handleChecked"
      @row-click="checkCurrent"
      :row-class-name="tableCheckedClass">
      <el-table-column type="selection" align="center" width="50" v-if="mode === 'edit'"></el-table-column>
      <el-table-column label="序号" type="index" align="center"></el-table-column>
      <el-table-column label="关联属性" align="center" prop="edmpName"></el-table-column>
      <el-table-column label="维护人" align="center" prop="moduser"></el-table-column>
      <el-table-column label="维护时间" align="center" prop="modtimeStr"></el-table-column>
    </el-table>
  </div>
</template>
<script>
  import UintCreater from './UintCreater'
  import { propertyService } from '@/api'

  export default {
    name: 'property-unit',
    props: ['property', 'mode'],
    created () {
      // 查询可以配置的属性
      propertyService.getUnitProperties(this.property.edmpEdmcId, this.property.edmpParentId).then(data => {
        console.log(data)
        this.unitProperties = data
        this.getUnitProperties()
      })
    },
    data () {
      return {
        // 可配置的属性
        unitProperties: [],
        // 列表数据
        tableData: [],
        // 选中行
        checkedRows: []
      }
    },
    methods: {
      getUnitProperties () {
        // 查找已配置的属性
        propertyService.getPropertyUnits(this.property.id).then(data => {
          this.tableData = data
        })
      },
      create () {
        // 已经选中的属性
        let selectedIds = []
        if (this.tableData) {
          this.tableData.forEach(item => {
            selectedIds.push(item.edunQtyEdmpId)
          })
        }
        this.$openDialog({
          name: 'create-unit',
          component: UintCreater,
          options: {
            title: '新增单位属性',
            customClass: 'dialog-width-s'
          },
          props: {
            unitProperties: this.unitProperties,
            propertyId: this.property.id,
            selectedIds
          },
          events: {
            createSuccess: () => {
              this.getUnitProperties()
              this.$closeDialog('create-unit')
            },
            close: () => {
              this.$closeDialog('create-unit')
            }
          }
        })
      },
      // 更新选中行
      handleChecked (rows) {
        this.checkedRows = rows
      },
      // 选中行，自动选中复选框
      checkCurrent (row) {
        this.$refs.currentTable.toggleRowSelection(row)
      },
      // 选中高亮样式
      tableCheckedClass (row, index) {
        if (this.checkedRows.indexOf(row) >= 0) {
          return 'table-checked'
        }
        return ''
      },
      removeUnitProperties () {
        if (this.checkedRows.length === 0) {
          this.$message.warning('至少选中一行！')
        } else {
          const ids = []
          this.checkedRows.forEach(item => {
            ids.push(item.id)
          })
          this.$confirm('确定删除？', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            propertyService.deleteUnits(ids).then(data => {
              // 刷新列表
              this.getUnitProperties()
            })
          })
        }
      }
    }
  }
</script>
<style scoped>
  .button-area{
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
  .button{
    float: right;
    margin-right: 10px;
  }
</style>
