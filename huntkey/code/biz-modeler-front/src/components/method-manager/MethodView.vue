<template>
  <div>
    <div class="button-area" >
      <div v-if="mode === 'edit'">
      <el-button type="primary" icon="plus" @click="openTree">添加</el-button>
      <el-button type="default" icon="arrow-up" @click="turnUp">上移</el-button>
      <el-button type="default" icon="arrow-down" @click="turnDown">下移</el-button>
      <el-button type="danger" icon="delete2" @click="deleteMethod">删除</el-button>
      </div>
    </div>
    <div class="main-content">
      <el-table :data="tableData"
                v-loading="isSearching"
                ref="currentTable"
                :row-class-name="tableCheckedClass"
                :height="$store.state.windowStore.windowHeight - parentTableHeight - 190"
                @row-click="checkCurrent"
                @selection-change="handleSelectionChange"
                highlight-current-row>
        <el-table-column  type="selection"  width="55" align='center'  v-if="mode === 'edit'"></el-table-column>
        <el-table-column type="index" align="center" width="70" label="序号"></el-table-column>
        <el-table-column prop="edmmTypeName" label="方法分类" align='center'></el-table-column>
        <el-table-column prop="edmmName" label="方法名称" align='center'>
          <template scope="scope">
            <el-button size="small" @click="viewMethod(scope.row.id)" type="text">{{scope.row.edmmName}}</el-button>
          </template>
        </el-table-column>
        <!-- <el-table-column prop="isPlatformProgram" label="是否平台方法" align='center'>
          <template scope="scope">
            <p v-if="scope.row.isPlatformProgram===1">是</p>
            <p v-else>否</p>
          </template>
        </el-table-column> -->
        <el-table-column prop="isCover" label="是否可覆盖" align='center'>
          <template scope="scope">
            <p v-if="scope.row.isCover===1">是</p>
            <p v-else>否</p>
          </template>
        </el-table-column>
        <el-table-column prop="edmmTriggerCond" label="触发条件" width="300" align='left' header-align="center" :show-overflow-tooltip='showme'></el-table-column>
        <el-table-column prop="edmmVer" label="版本号" align='center'></el-table-column>
        <el-table-column prop="moduser" label="维护人" align='center'></el-table-column>
        <el-table-column prop="modtimeStr" label="维护时间"  align='center' width="130"></el-table-column>
      </el-table>
      <div><el-button type="text" size="large" @click="showParent" :icon="parentShow? 'caret-bottom':'caret-top'">父类方法</el-button></div>
      <el-table :data="parentData"
                v-show="parentShow"
                v-loading="isSearching1"
                :height="parentTableHeight"
                highlight-current-row>
        <el-table-column type="index" align="center" width="70" label="序号"></el-table-column>
        <el-table-column  prop="edmcName" label="类名" align='center' ></el-table-column>
        <el-table-column prop="edmmTypeName" label="方法分类" align='center'></el-table-column>
        <el-table-column prop="edmmName" label="方法名称" align='center'>
        </el-table-column>
        <el-table-column prop="isPlatformProgram" label="是否平台方法" align='center'>
          <template scope="scope">
            <p v-if="scope.row.isPlatformProgram===1">是</p>
            <p v-else>否</p>
          </template>
        </el-table-column>
        <el-table-column prop="isCover" label="是否可覆盖" align='center'>
          <template scope="scope">
            <!--<el-switch
              v-model="scope.row.isCover"
              :on-value= "1"
              :off-value= "0"
              disabled
              on-text="是"
              off-text="否"
              @change="changeMethod(scope.row)">
            </el-switch>-->
            <p v-if="scope.row.isCover===1">是</p>
            <p v-else>否</p>
          </template>
        </el-table-column>
        <el-table-column prop="edmmTriggerCond" label="触发条件" width="300" align='left' header-align="center" :show-overflow-tooltip='showme'></el-table-column>
        <el-table-column prop="edmmVer" label="版本号" align='center'></el-table-column>
        <el-table-column prop="moduser" label="维护人" align='center'></el-table-column>
        <el-table-column prop="modtimeStr" label="维护时间"  align='center' width="130"></el-table-column>
      </el-table>
    </div>
  </div>
</template>
<script>
  import {methodViewService} from '@/api/services/method-view'
  import { functionService } from '@/api/services/method'
  import MethodTree from '@/components/commons/method-selecter/MethodTree'
  import MethodView from '@/components/commons/method-selecter/MethodView'
  export default {
    name: 'methodView',
    data () {
      return {
        tableData: [],
        parentData: [],
        mode: this.$route.params.mode,
       // tableHeight: '685',
        parentTableHeight: 0,
        parentShow: false,
        methodId: '',
        classId: '',
        multipleSelection: [],
        showme: true,
        isSearching: false,
        isSearching1: false
      }
    },
    created () {
      this.classId = this.$route.params.classId
      // console.log(this.classId)
      this.getMethods()
      this.getParentMethods()
    },
    methods: {
      getMethods () {
        this.isSearching = true
        methodViewService.getMethods(this.classId).then(data => {
         // console.log(JSON.stringify(data))
          this.tableData = data
          this.$nextTick(() => {
            this.isSearching = false
          })
        }).catch(() => {
          this.isSearching = false
        })
      },
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
      deleteMethod () {
          // console.log(id)
        this.$confirm('是否确认继续?', '', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          let ids = []
          for (let obj of this.multipleSelection) {
            let id = obj.id
            ids.push(id)
           /* methodViewService.deleteMethod(this.classId, id).then(() => {
              this.$message({
                type: 'success',
                message: '删除成功!'
              })
              this.getMethods()
            }) */
          }
          methodViewService.deleteMethods(this.classId, ids).then(() => {
            this.$message({
              type: 'success',
              message: '删除成功!'
            })
            this.getMethods()
          })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          })
        })
      },
      getParentMethods () {
        this.isSearching1 = true
        methodViewService.getParentMethods(this.classId).then(data => {
         //  console.log(JSON.stringify(data))
          this.parentData = data
          this.$nextTick(() => {
            this.isSearching1 = false
          })
        }).catch(() => {
          this.isSearching1 = false
        })
      },
      // 改变是否可覆盖
      changeMethod (row) {
      //  console.log(`${val}~~${id}`)
        methodViewService.updateMedthod(row).then(() => {
          console.log('成功了')
        })
      },
      showParent () {
        this.parentShow = !this.parentShow
        if (this.parentShow) {
          this.parentTableHeight = 200
        } else {
          this.parentTableHeight = 0
        }
      },
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
            methodViewService.moveMethod(this.getCurrentIds())
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
            methodViewService.moveMethod(this.getCurrentIds())
          }, 2000)
        }
      },
      // 获得当前数据ID数组
      getCurrentIds () {
        let ids = []
        this.tableData.forEach(item => {
          ids.push(item.edcmId)
        })
        return ids
      },
      editTree: function (row) {
        this.$openDialog({
          name: 'tree-dialog',
          component: MethodTree,
          options: {
            title: '方法分类',
            customClass: 'dialog-width-xl'
          },
          props: {
            currentValue: row.id
          }
        }).then(vm => {
          this.vmPro = vm
        })
      },
      // 查看方法
      viewMethod (id) {
        functionService.queryMethod(id).then(data => {
          this.$openDialog({
            name: 'view-method-' + id,
            component: MethodView,
            options: {
              title: '查看方法',
              customClass: 'dialog-width-s'
            },
            props: {
              edmMethod_show: data.edmMethod_show,
              edmMethodInsertList_show: data.edmMethodInsertList_show,
              edmMethodreturn_show: data.edmMethodreturn_show
            }
          })
        })
      },
      openTree: function () {
        this.$openDialog({
          name: 'tree-dialog',
          component: MethodTree,
          options: {
            title: '方法分类',
            customClass: 'dialog-width-xl'
          },
          props: {
            multiple: true
          },
          /* events: {
           changeType: this.changeType
           } */
          buttons: [{
            text: '确定',
            icon: 'check',
            type: 'primary',
            handler: () => {
              const result = this.vmPro.getResult()
              console.log(JSON.stringify(result))
              let ids = []
              for (let obj of result) {
                ids.push(obj.value)
              }
              this.saveMethod(ids)
/*              if (returnType !== 'undefined') {
                this.vmPro.saveMethods().then(() => {
                  this.$message({
                    type: 'success',
                    message: '增加成功!'
                  })
                  this.getMethods()
                  this.$closeDialog('tree-dialog')
                })
              } */
            }
          }, {
            text: '取消',
            icon: 'close',
            handler: () => {
              this.$closeDialog('tree-dialog')
            }
          }]
        }).then(vm => {
          this.vmPro = vm
        })
      },
      saveMethod (ids) {
        functionService.classAddMethod({classId: this.classId, methodIds: ids}).then(() => {
          this.$message({
            type: 'success',
            message: '增加成功!'
          })
          this.getMethods()
          this.$closeDialog('tree-dialog')
        })
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
