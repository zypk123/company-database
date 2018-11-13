<template>
  <div class="attribute-view" >
    <div class="main-content" >
      <div class="top-form">
        <el-form :inline="true" :model="data" label-width="100px" class="form">
          <el-form-item label="类版本">
            <el-input v-model="data.edmcVer" value="data.edmcVer" :disabled="true"></el-input>
          </el-form-item>
          <el-form-item label="负责人">
            <el-input v-model="data.edmcPrincipal" value="data.edmcPrincipal" :disabled="true"></el-input>
          </el-form-item>
          <el-form-item label="数据库类型">
            <el-select v-model="data.databaseType" disabled>
            <el-option v-for="option in options" :key="option.codeValue" :label="option.codeName"
                       :value="option.codeValue" >{{option.codeName}}</el-option>
            </el-select>
          </el-form-item>
         <!--  <el-form-item  label="标准的呈现">
            <el-input v-model="data.edmcVer" value="data.normalPresent" :disabled="true"></el-input>
          </el-form-item>
          <el-form-item  label="对象所在云">
            <el-input v-model="data.objectOnCloud" value="data.normalPresent" :disabled="true" ></el-input>
          </el-form-item> -->
          <!-- <el-form-item label="对象呈现格式">
            <el-input icon="plus" placeholder="设置对象呈现格式" v-model="classViewFormat" readonly @focus="setShow" @click="setShow"></el-input>
          </el-form-item> -->
          <el-form-item label="类说明">
            <el-input  type="textarea" :disabled="true" :row="3" size="small" value="data.edmcUseDesc" v-model="data.edmcUseDesc"
            style="width:823px;"></el-input>
          </el-form-item>
          <el-form-item label="维护人：" style="width:285px;">
            <label >{{data.moduser}}</label>
          </el-form-item>
          <el-form-item label="维护时间：">
            <label >{{data.modtimeStr}}</label>
          </el-form-item>
        </el-form>
      </div>
      <div class="button-area">
        <span class="title default-color">
          附件列表
        </span>
        <div class="button" v-if="mode === 'edit'">
          <el-button type="primary" icon="plus" @click="addNew">添加</el-button>
          <el-button type="default" icon="arrow-up" @click="turnUp">上移</el-button>
          <el-button type="default" icon="arrow-down" @click="turnDown">下移</el-button>
          <el-button type="danger" icon="delete2" @click="deleteInfo">删除</el-button>
        </div>
      </div>
      <div style="">
      <el-table :data="tableData"  @selection-change="handleSelectionChange"
                v-loading="isSearching"  :height="$store.state.windowStore.windowHeight - 470" ref="currentTable"
                @row-click="checkCurrent" :row-class-name="tableCheckedClass" highlight-current-row>
        <el-table-column  type="selection"  width="55" align='center' v-if="mode === 'edit'"></el-table-column>
        <el-table-column type="index" align='center' label="序号" width="70"></el-table-column>
        <el-table-column prop="typeValue" label="附件类型" align='center'>
        </el-table-column>
        <el-table-column prop="edmaName" label="附件名称" align='center'>
          <template scope="scope">
              <el-button size="small" @click="viewAtta(scope.row)" type="text">{{scope.row.edmaName}}</el-button>
          </template></el-table-column>
        <el-table-column prop="edmaPath" label="附件路径" align='center' width="400">
          <template scope="scope">
            <div v-if="(scope.row.edmaType === 1 || scope.row.edmaType === '1')">
              <a :href="'https://'+scope.row.edmaPath" target="_blank">{{ scope.row.edmaPath }}</a>
            </div>
            <div v-else-if="(scope.row.edmaType === 2 || scope.row.edmaType === '2')">
              <a  :href="CONFIG.baseUrl.downLoad+'?id='+scope.row.id">{{ scope.row.edmaSourceName }}</a>
            </div>
          </template></el-table-column>
        <el-table-column prop="moduser" label="维护人" align='center'></el-table-column>
        <el-table-column prop="modtimeStr" label="维护时间"  align='center'></el-table-column>
        </el-table-column>
      </el-table>
      </div>
    </div>
  </div>
</template>
<script>
  import {basicInfoService} from '@/api/services/basic-info-view'
  import created from './AttachmentCreater.vue'
  import Editor from './AttachmentEditor.vue'
  import SetShow from './SetShow'
  export default{
    name: 'basicInfo',
    data () {
      return {
        data: {},
        classId: '',
        isSearching: false,
        mode: this.$route.params.mode,
        tableData: [],
        fileOption: [],
        options: [],
        multipleSelection: [],
        // 呈现格式
        classViewFormat: '',
        // 格式列表
        classFormats: []
      }
    },
    created () {
      this.classId = this.$route.params.classId
      this.getDBType()
      this.getBasicInfo()
      this.getAttachment()
      // 获取呈现格式
      basicInfoService.getClassFormats(this.classId).then(data => {
        this.classFormats = data
      })
      // this.getType()
    },
    watch: {
      classFormats: {
        handler (value) {
          if (value) {
            this.classViewFormat = ''
            value.forEach(item => {
              if (item.edmpName) {
                this.classViewFormat += item.edmpName
              }
              if (item.edmfConnector) {
                this.classViewFormat += item.edmfConnector
              }
            })
          }
        },
        deep: true
      }
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
      getDBType () {
        basicInfoService.getDBTypes().then(data => {
          this.options = data
        })
      },
      getBasicInfo () {
        basicInfoService.getBasicInfo(this.classId).then(data => {
         // console.log(JSON.stringify(data))
          this.data = data
        })
      },
      // 查询下拉列表选项
      /* getType () {
        basicInfoService.queryType().then(data => {
          this.fileOption = data
        })
      }, */
      // 校验附件名称
      checkDataName (list) {
        let newEdmpName = list.srcElement.value
        if (newEdmpName) {
          basicInfoService.checkDataName({params: {edmaName: newEdmpName, edmaEdmcId: this.classId}})
            .then(() => {
              console.log('校验成功')
            })
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
            basicInfoService.moveAtta(this.getCurrentIds())
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
            basicInfoService.moveAtta(this.getCurrentIds())
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
      deleteInfo () {
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
          }
          basicInfoService.deleteInfo(ids).then(() => {
            this.$message({
              type: 'success',
              message: '删除成功!'
            })
            this.getAttachment()
          })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          })
        })
      },
      addNew () {
        this.$openDialog({
          name: 'create-dialog',
          component: created,
          props: {
            classId: this.classId
          },
          options: {
            title: '新增附件',
            customClass: 'dialog-width-m'
          },
          events: {
            createSuccess: this.createSuccess
          }
        })
      },
      createSuccess () {
        this.$closeDialog('create-dialog')
        this.getAttachment()
      },
      // 维护附件信息
      viewAtta (row) {
        this.$openDialog({
          name: 'view-dialog',
          component: Editor,
          options: {
            title: '编辑附件信息',
            customClass: 'dialog-width-m'
          },
          props: {
            data: row
          },
          events: {
            updateSuccess: this.updateSuccess,
            cancelEdit: this.cancelEdit
          }
        })
      },
      cancelEdit () {
        this.getAttachment()
       // this.$closeDialog('view-dialog')
      },
      updateSuccess () {
        this.$closeDialog('view-dialog')
        this.getAttachment()
      },
      // 查询表格数据
      getAttachment () {
        this.isSearching = true
        basicInfoService.queryAttachment(this.classId).then(data => {
         // console.log(JSON.stringify(data))
          this.tableData = data
          this.$nextTick(() => {
            this.isSearching = false
          })
        }).catch(() => {
          this.isSearching = false
        })
      },
      // 配置对象呈现格式
      setShow () {
        this.$openDialog({
          name: 'set-show',
          component: SetShow,
          options: {
            title: '设置对象呈现格式',
            customClass: 'dialog-width-s'
          },
          props: {
            classId: this.classId,
            classFormats: this.classFormats
          },
          buttons: [{
            text: '确认',
            icon: 'circle-check',
            type: 'primary',
            handler: () => {
              var result = this.dialogVm.getResult()
              if (result) {
                // 保存
                basicInfoService.saveClassFormats(result)
                this.classFormats = result
                this.$closeDialog('set-show')
              }
            }
          }, {
            text: '取消',
            icon: 'circle-close ',
            handler: () => {
              this.$closeDialog('set-show')
            }
          }]
        }).then(dialogVm => {
          this.dialogVm = dialogVm
        })
      }
    }
  }
</script>
<style scoped>
.main-content{
  margin-top: 40px;
  padding: 20px;
  background-color: #fff;
}
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
.form{
  width: 1000px;
}
</style>
