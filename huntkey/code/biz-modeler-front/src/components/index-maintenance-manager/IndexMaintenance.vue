<template>
  <div class="index_maintenance">
    <div class="button-area">
      <div>
        <el-button type="primary" icon="plus" @click="createNewIndex">添加</el-button>
        <!-- <el-button type="default" icon="arrow-up" @click="turnUp">上移</el-button>
        <el-button type="default" icon="arrow-down" @click="turnDown">下移</el-button> -->
        <el-button type="danger" icon="delete2" @click="removeIndex">删除</el-button>
      </div>
    </div>
    <div class="table-area">
      <el-table
        ref="currentTable"
        :data="indexTableData"
        v-loading="isSearching"
        @row-click="checkCurrent"
        @selection-change="selectionChange"
        :height="$store.state.windowStore.windowHeight - parentTableHeight - 190">
        <el-table-column type="selection" width="40px" align="center">
        </el-table-column>
        <el-table-column width="40px" label="序号" type="index" align="center">
        </el-table-column>
        <el-table-column label="索引名称" prop="indexName" width="160" align="center">
          <template scope="scope">
            <el-button @click="editIndex(scope.row)" type="text">{{scope.row.indexName}}</el-button>
          </template>
        </el-table-column>
        <el-table-column label="索引类型" prop="indexType" width="160" align="center">
          <template scope="scope">
            <span>{{UTILS.getDictName('edm_index_type', scope.row.indexType)}}</span>
          </template>
        </el-table-column>
        <!-- <el-table-column label="唯一" prop="edmpCode" width="160" align="center">
          <template scope="scope">
            <el-switch
              on-text="是"
              off-text="否"
              :on-value="1"
              :off-value="0"></el-switch>
          </template>
        </el-table-column> -->
        <el-table-column label="索引键列" prop="indexPropertyNames" align="center">
        </el-table-column>
        <el-table-column label="维护人" prop="moduser" width="160" align="center">
        </el-table-column>
        <el-table-column label="维护时间" prop="modtimeStr" width="160" align="center">
        </el-table-column>
      </el-table>

      <!-- 父类索引维护表格 -->
      <el-button type="text" @click="toggleParentTable" :icon="showParent? 'caret-bottom':'caret-top'">父类索引</el-button>
      <el-table
        :data="parentIndexData"
        v-show="showParent"
        ref="parentTable"
        v-loading="isSearching"
        :height="parentTableHeight">
        <el-table-column type="selection" width="40px" align="center">
        </el-table-column>
        <el-table-column width="40px" label="序号" type="index" align="center">
        </el-table-column>
        <el-table-column label="索引名称" prop="indexName" width="125px" align="center">
        </el-table-column>
        <el-table-column label="索引类型" prop="indexType" width="125px" align="center">
          <template scope="scope">
            <span>{{UTILS.getDictName('edm_index_type', scope.row.indexType)}}</span>
          </template>
        </el-table-column>
        <el-table-column label="索引键列" prop="indexPropertyNames" width="450px" align="center">
        </el-table-column>
        <el-table-column label="维护人" prop="moduser" width="125px" align="center">
        </el-table-column>
        <el-table-column label="维护时间" prop="modtimeStr" align="center">
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>
<script>
  import createIndex from './CreateIndexMaintenance'
  import { indexService } from '@/api'
  import { dictTypes } from '@/types'
  export default {
    name: 'indexMaintenance',
    data () {
      return {
        // 索引（当前）列表数据
        indexTableData: null,
        // 父类列表数据
        parentIndexData: null,
        // 父类表格高度
        parentTableHeight: 0,
        // 是否显示父类表格
        showParent: false,
        // 选中行
        checkedRows: [],
        isSearching: false
      }
    },
    created () {
      this.$store.dispatch(dictTypes.GET_DICT_BY_CODES, ['edm_index_type']).then(() => {
        // 获取索引信息
        this.getIndexes()
        this.getParentIndexes()
      })
    },
    computed: {
    },
    methods: {
      // 获取索引信息
      getIndexes () {
        this.isSearching = true
        indexService.getIndexByClassId(this.$store.state.classTreeStore.currentClass.id).then(data => {
          // console.log(data)
          this.indexTableData = data
          this.isSearching = false
        }).catch(() => {
          this.isSearching = false
        })
      },
      // 获取父类索引信息
      getParentIndexes () {
        this.isSearching = true
        indexService.getParentIndexes(this.$store.state.classTreeStore.currentClass.id).then(data => {
          // console.log(data)
          this.parentIndexData = data
          this.isSearching = false
        }).catch(() => {
          this.isSearching = false
        })
      },
      // 新增
      createNewIndex () {
        this.$openDialog({
          name: 'create-index',
          component: createIndex,
          options: {
            title: '索引维护',
            customClass: 'dialog-width-l'
          },
          events: {
            close: this.close,
            saveSuccess: this.saveSuccess
          }
        })
      },
      // 修改
      editIndex (currentIndex) {
        // console.log(currentIndex)
        this.$openDialog({
          name: 'create-index',
          component: createIndex,
          options: {
            title: '索引维护',
            customClass: 'dialog-width-l'
          },
          props: {
            currentIndex: currentIndex
          },
          events: {
            close: this.close,
            updateSuccess: this.updateSuccess
          }
        })
      },
      close () {
        this.$closeDialog('create-index')
      },
      saveSuccess () {
        this.getIndexes()
        this.$closeDialog('create-index')
        this.$message.success('新增成功')
      },
      updateSuccess () {
        this.getIndexes()
        this.$closeDialog('create-index')
        this.$message.success('修改成功')
      },
      // 上移
      turnUp () {
        if (this.checkedRows.length === 0) {
          this.$message.warning('至少选中一行！')
        } else {
          for (let index = 0; index < this.checkedRows.length; index++) {
            const item = this.checkedRows[index]
            // 当前item在数据列表中的位置
            const position = this.indexTableData.indexOf(item)
            if (position > index) {
              // 与前一位数据交换
              this.indexTableData.splice(position, 1)
              this.indexTableData.splice(position - 1, 0, item)
            }
          }
          // 两秒后与后台进行数据交互
          clearTimeout(this.timeout)
          this.timeout = setTimeout(() => {
            //
          }, 2000)
        }
      },
      // 下移
      turnDown () {
        if (this.checkedRows.length === 0) {
          this.$message.warning('至少选中一行！')
        } else {
          for (let index = 0; index < this.checkedRows.length; index++) {
            const item = this.checkedRows[this.checkedRows.length - index - 1]
            const position = this.indexTableData.indexOf(item)
            if (position < this.checkedRows.length - index - 1) {
              this.indexTableData.splice(position, 1)
              this.indexTableData.splice(position + 1, 0, item)
            }
          }
          // 两秒后与后台进行数据交互
          clearTimeout(this.timeout)
          this.timeout = setTimeout(() => {
            //
          }, 2000)
        }
      },
      // 删除
      removeIndex () {
        if (this.checkedRows.length === 0) {
          this.$message.warning('至少选中一行！')
        } else {
          let ids = []
          for (let i = 0; i < this.checkedRows.length; i++) {
            ids.push(this.checkedRows[i].id)
          }
          ids = ids.toString()
          this.$confirm('确定删除？', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            // 后台交互
            indexService.deleteIndexes(ids).then(data => {
              this.$message.success('删除成功')
              this.$nextTick(() => {
                this.getIndexes()
              })
            })
          })
        }
      },
      // 是否显示父索引维护列表
      toggleParentTable () {
        this.showParent = !this.showParent
        if (this.showParent) {
          this.parentTableHeight = 200
        } else {
          this.parentTableHeight = 0
        }
      },
      // 选中样式高亮
      tableCheckedClass (row, index) {
        if (this.checkedRows.indexOf(row) > 0) {
          return 'table-checked'
        }
        return ''
      },
      // 选中行，自动选中复选框
      checkCurrent (row) {
        this.$refs.currentTable.toggleRowSelection(row)
      },
      // 更新选中行
      selectionChange (rows) {
        // 按照选中序号排序
        rows.sort((a, b) => this.indexTableData.indexOf(a) > this.indexTableData.indexOf(b))
        this.checkedRows = rows
      }
    }
  }
</script>
<style scoped>
  .button-area{
    text-align: right;
    height: 38px;
  }
</style>
