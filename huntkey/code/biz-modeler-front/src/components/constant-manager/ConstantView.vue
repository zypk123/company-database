<template>
  <div>
    <div class="button-area">
      <div v-if="mode === 'edit'">
      <el-button type="primary" icon="plus" @click="newConst">添加</el-button>
      <el-button type="default" icon="arrow-up" @click="turnUp">上移</el-button>
      <el-button type="default" icon="arrow-down" @click="turnDown">下移</el-button>
      <el-button type="danger" icon="delete2" @click="deleteConst">删除</el-button>
      </div>
    </div>
    <div class="main-content">
      <el-table :data="tableData"  @selection-change="handleSelectionChange"  highlight-current-row
                v-loading="isSearching" :height="$store.state.windowStore.windowHeight - parentTableHeight - 190"
                :row-class-name="tableCheckedClass" @row-click="checkCurrent" ref="currentTable">
        <el-table-column  type="selection"  width="55" align='center' v-if="mode === 'edit'"></el-table-column>
        <el-table-column type="index" align='center' label="序号" width="70"></el-table-column>
        <el-table-column prop="edmpCode" label="属性编码" align='center'></el-table-column>
        <el-table-column prop="edmpName" label="属性名称" align='center'>
          <template scope="scope">
            <el-button size="small" @click="viewConst(scope.row)" type="text">{{scope.row.edmpName}}</el-button>
          </template>
        </el-table-column>
        <el-table-column prop="edmpValueName" label="数据类型" align='center'></el-table-column>
        <el-table-column prop="edmpDataName" label="属性值" align='center'></el-table-column>
        <el-table-column prop="moduser" label="维护人" align='center'></el-table-column>
        <el-table-column prop="modtimeStr" label="维护时间"  align='center'></el-table-column>
      </el-table>
      <div><el-button type="text" size="large" @click="showParent" :icon="parentShow? 'caret-bottom':'caret-top'">父类常量</el-button></div>
      <el-table :data="parentData" v-show="parentShow"  highlight-current-row
                v-loading="isSearching1" :height="parentTableHeight">
        <el-table-column type="index" align='center' label="序号" width="70"></el-table-column>
        <el-table-column prop="edmpCode" label="类名" align='center'></el-table-column>
        <el-table-column prop="edmpCode" label="属性编码" align='center'></el-table-column>
        <el-table-column prop="edmpName" label="属性名称" align='center'></el-table-column>
        <el-table-column prop="edmpValueName" label="数据类型" align='center'></el-table-column>
        <el-table-column prop="edmpDataName" label="属性值" align='center'></el-table-column>
        <el-table-column prop="moduser" label="维护人" align='center'></el-table-column>
        <el-table-column prop="modtimeStr" label="维护时间"  align='center'></el-table-column>
      </el-table>
    </div>
  </div>
</template>
<script>
import {constService} from '@/api/services/const-view'
import creater from './ConstCreater.vue'
import Editor from './ConstEditor.vue'
export default {
  name: 'constantView',
  data () {
    return {
      tableData: [],
      parentData: [],
      classId: '',
      mode: this.$route.params.mode,
      multipleSelection: [],
      tableHeight: '685',
      parentTableHeight: 0,
      parentShow: false,
      isSearching: false,
      isSearching1: false
    }
  },
  created () {
    this.classId = this.$route.params.classId
    this.getConst()
    this.getParentConst()
  },
  methods: {
    // 选中行，自动选中复选框
    checkCurrent (row) {
      this.$refs.currentTable.toggleRowSelection(row)
    },
    // 选中高亮样式
    tableCheckedClass (row, index) {
      if (this.multipleSelection.indexOf(row) >= 0) {
        return 'table-checked'
      }
      return ''
    },
    viewConst (row) {
      this.$openDialog({
        name: 'view-dialog',
        component: Editor,
        options: {
          title: '编辑常量信息',
          customClass: 'dialog-width-m'
        },
        props: {
          data: row,
          classId: this.classId
        },
        events: {
          updateSuccess: this.updateSuccess,
          cancelEdit: this.cancelEdit
        }
      })
    },
    cancelEdit () {
      this.getConst()
     // this.$closeDialog('view-dialog')
    },
    updateSuccess () {
      this.$closeDialog('view-dialog')
      this.getConst()
    },
    // 查询列表数据
    getConst () {
      this.isSearching = true
      constService.getConst(this.classId).then(data => {
        console.log(JSON.stringify(data))
        this.tableData = data
        this.$nextTick(() => {
          this.isSearching = false
        })
      }).catch(() => {
        this.isSearching = false
      })
    },
    // 查询列表数据
    getParentConst () {
      this.isSearching1 = true
      constService.getParentConst(this.classId).then(data => {
       // console.log(JSON.stringify(data))
        this.parentData = data
        this.$nextTick(() => {
          this.isSearching1 = false
        })
      }).catch(() => {
        this.isSearching1 = false
      })
    },
    newConst () {
      this.$openDialog({
        name: 'create-dialog',
        component: creater,
        props: {
          classId: this.classId,
          parentAttrs: this.parentData
        },
        options: {
          title: '新增常量',
          customClass: 'dialog-width-m'
        },
        events: {
          createSuccess: this.createSuccess
        }
      })
    },
    createSuccess () {
      this.$closeDialog('create-dialog')
      this.getConst()
    },
    showParent () {
      this.parentShow = !this.parentShow
      if (this.parentShow) {
        this.parentTableHeight = 200
      } else {
        this.parentTableHeight = 0
      }
    },
    // 选择表格行时
    handleSelectionChange (val) {
      val.sort((a, b) => this.tableData.indexOf(a) > this.tableData.indexOf(b))
      this.multipleSelection = val
      // console.log(JSON.stringify(this.multipleSelection))
    },
    // 上移
    turnUp () {
      if (this.multipleSelection.length === 0) {
        this.$message.warning('至少选中一行！')
      } else {
        for (let index = 0; index < this.multipleSelection.length; index++) {
          const item = this.multipleSelection[index]
          // 位置
          const position = this.tableData.indexOf(item)
          if (position > index) {
            // 与前一位数据交换
            this.tableData.splice(position, 1)
            this.tableData.splice(position - 1, 0, item)
          }
        }
        // 2秒后与后台交互，若此期还有同样操作，则刷新时间
        clearTimeout(this.timeout)
        this.timeout = setTimeout(() => {
          constService.moveConst(this.getCurrentIds())
        }, 2000)
      }
    },
    // 下移
    turnDown () {
      if (this.multipleSelection.length === 0) {
        this.$message.warning('至少选中一行！')
      } else {
        for (let index = 0; index < this.multipleSelection.length; index++) {
          const item = this.multipleSelection[this.multipleSelection.length - index - 1]
          // 位置
          const position = this.tableData.indexOf(item)
          if (position < this.tableData.length - index - 1) {
            // 与后一位数据做交换
            this.tableData.splice(position, 1)
            this.tableData.splice(position + 1, 0, item)
          }
        }
        // 2秒后与后台交互，若此期还有同样操作，则刷新时间
        clearTimeout(this.timeout)
        this.timeout = setTimeout(() => {
          constService.moveConst(this.getCurrentIds())
        }, 2000)
      }
    },
    // 获得当前数据ID数组
    getCurrentIds () {
      let ids = []
      this.tableData.forEach(item => {
        ids.push(item.id)
      })
      return ids
    },
    // 删除常量
    deleteConst () {
      for (let obj of this.multipleSelection) {
        let id = obj.id
        console.log(id)
        this.$confirm('是否确认继续?', '', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          constService.deleteConst(id).then(() => {
            this.$message({
              type: 'success',
              message: '删除成功!'
            })
            this.getConst()
          })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          })
        })
      }
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
