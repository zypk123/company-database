<template>
  <div>
    <el-form inline :model="formData" :rules="rules">
      <el-form-item label="关键词" prop="keyWord">
        <el-input placeholder="请输入关键词" v-model="formData.keyWord"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button icon="search" type="primary" @click="search">查询</el-button>
      </el-form-item>
    </el-form>
    <el-table height="330"
      :data="tableData"
      highlight-current-row
      @current-change="chooseData">
      <el-table-column label="选择" align="center" width="50">
        <template scope="scope">
          <el-checkbox v-model="scope.row.isSelected" v-on:change.stop="checkedChange(scope.row)"></el-checkbox>
        </template>
      </el-table-column>
      <el-table-column label="序号" align="center" width="50">
        <template scope="scope">
          {{formData.pageSize * (formData.pageNum - 1) + scope.$index + 1}}
        </template>
      </el-table-column>
      <el-table-column label="名称" align="center" prop="name"></el-table-column>
    </el-table>
    <el-pagination style="float:right;margin-top:10px;"
      v-loading="isSearching"
      layout="total, prev, pager, next, jumper" 
      :total="formData.totalCount"
      :page-size="formData.pageSize"
      :current-page="formData.pageNum"
      @size-change="sizeChange"
      v-on:row-click.stop="currentChange"></el-pagination>
  </div>
</template>
<script>
  import { classService } from '@/api'

  export default {
    name: 'object-view',
    props: ['classId'],
    created () {
      this.queryData.classId = this.classId
      this.UTILS.setDataFromOther(this.queryData, this.formData)
      this.query()
    },
    watch: {
      classId: {
        handler (classId) {
          this.queryData.classId = classId
          this.query()
        },
        deep: true
      }
    },
    data () {
      return {
        isSearching: false,
        queryData: {
          classId: null,
          keyWord: '',
          pageSize: this.CONFIG.page.defaultPageSize,
          pageNum: 1
        },
        formData: {
          keyWord: null,
          totalCount: 0,
          pageNum: 1,
          pageSize: this.CONFIG.page.defaultPageSize
        },
        tableData: [],
        rules: {
        }
      }
    },
    methods: {
      search () {
      },
      query () {
        if (this.queryData.classId) {
          // 判断class是否配置了呈现格式
          classService.hasCharacter(this.queryData.classId).then((data) => {
            if (data) {
                // 当前为查询状态，改状态下所有的路由和分页事件都不触发
              this.isSearching = true
              classService.getClassObject(this.queryData).then(data => {
                data.list.forEach(item => {
                  item.isSelected = false
                })
                this.tableData = data.list
                this.formData.totalCount = data.total
                this.$nextTick(() => {
                  this.isSearching = false
                })
              }).catch(() => {
                this.isSearching = false
              })
            } else {
              this.$message.error('未配置呈现格式')
            }
          })
        }
      },
      /**
       * 分页大小改变
       * @param  新分页大小
       */
      sizeChange (size) {
        if (!this.isSearching) {
          // 更改分页大小，重新从第一页开始查
          this.queryData.pageSize = size
          this.queryData.pageNum = 1
          this.query()
        }
      },
      /**
       * 页数改变
       * @param  新页码
       */
      currentChange (current) {
        if (!this.isSearching) {
          this.queryData.pageNum = current
          this.query()
        }
      },
      /**
       * 选择行
       */
      chooseData (currentRow, oldRow) {
        currentRow.isSelected = true
        if (oldRow) {
          oldRow.isSelected = false
        }
      },
      // checkbox改变时，始终为true
      checkedChange (row) {
        row.isSelected = true
      },
      // 获得结果
      getResult () {
        let result
        for (let index in this.tableData) {
          const item = this.tableData[index]
          if (item.isSelected) {
            result = item
            break
          }
        }
        if (result) {
          return result
        } else {
          this.$message.warinng('还未选择对象')
        }
      }
    }
  }
</script>
<style scoped>
  
</style>
