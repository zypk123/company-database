<template>
  <div>
    <!--按钮区域-->
    <el-row>
      <el-col :span="24">
        <el-button type="primary" @click="showClassifyDlg()">添加</el-button>
      </el-col>
    </el-row>
    <br/>

    <!-- 数据展示区 -->
    <el-row>
      <el-col :span="24">
        <el-table :data="pageData.classifyTableData" border style="width: 100%">
          <el-table-column type="index" align='center' width="70" label="序号"></el-table-column>
          <el-table-column header-align="center" align="center" prop="fnccClassifyName" label="分类名称" width="150"></el-table-column>
          <el-table-column header-align="center" align="center" show-overflow-tooltip 
              prop="fnccClassifyDesc" label="分类描述" width="250">
          </el-table-column>
          <el-table-column header-align="center" align="center" prop="fnccState" label="有效状态" :formatter="tableFnccStateFormat" width="120">
          </el-table-column>
          <el-table-column header-align="center" align="center" prop="moduser" label="维护人" width="100"></el-table-column>
          <el-table-column header-align="center" align="center"
              prop="modtime" label="维护时间" sortable :formatter="tableDateFormat">
          </el-table-column>
          <el-table-column header-align="center" align="center" label="操作">
            <template scope="scope">
              <el-button size="small" @click="showClassifyDlg(scope.row)" type="text">编辑</el-button>
              <el-button size="small" @click="delClassify(scope.row.fnccId)" type="text">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-col>
    </el-row>
  </div>
</template>
<script>
import { classifyService } from '@/api'
import formatMixin from '@/mixin/data-format-mixin'
import ClassifyEdit from './ClassifyEdit'
export default {
  name: 'classifyManage',
  mixins: [formatMixin],
  // 页面数据
  data () {
    return {
      pageConfig: {
        classifyEditDlgTitle: '函数分类维护-新增',
        classifyEditDlgVisible: false
      },
      // 表单数据
      pageData: {
        // 列表数据
        classifyTableData: [],
        // 验证规则
        rules: {
        }
      },
      // 验证错误信息
      errorInfo: {
      },
      // 是否删除标识
      isDeleting: false
    }
  },
  // 页面初始化调用
  created () {
    this.queryClassify()
  },
  mounted () {
    this.query()
  },
  methods: {
    /**
     * 查询
     */
    query () {
      this.queryClassify()
    },
    /**
     * 调用查询服务，获取数据，注入表格
     */
    queryClassify () {
      classifyService.queryClassify().then((data) => {
        // 回填Form表单
        this.pageData.classifyTableData = data
      })
    },
    /**
     * 删除
     */
    delClassify (fnccId) {
      if (!this.isDeleting) {
        this.$confirm('是否删除该函数分类？', '提示', {
          confirmButtonText: '确定',
          canceButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.isDeleting = true
          classifyService.removeClassify(fnccId).then(() => {
            this.$message.success('删除成功！')
            this.isDeleting = false
            this.queryClassify()
          }).catch((error) => {
            this.$message.error(error.message)
            this.isDeleting = false
          })
        })
      }
    },
    /**
     * 打开新增/编辑弹框
     */
    showClassifyDlg (row) {
      if (row) { // 编辑
        var flag = ''
        classifyService.ishaveChildren(row.fnccId).then((data) => {
          console.info(data)
          if (data[0] == null) {
            // console.log('没有被继承')
            flag = true
          } else {
            // console.log('被继承了')
            flag = false
          }
          let rowData = {
            mode: 'edit',
            data: row,
            display: flag
          }
          this.openEditDialog({name: 'ClassifyEditDlg', component: ClassifyEdit, title: '函数分类-编辑', trdata: rowData})
        })
        // console.log(rowData)
      } else {
        let rowData = {
          mode: 'add',
          display: true
        }
        this.openEditDialog({name: 'ClassifyEditDlg', component: ClassifyEdit, title: '函数分类-新增', trdata: rowData})
      }
    },
    openEditDialog ({name, component, title, trdata}) {
      this.OpenGlobalDialog({
        name: name,
        component: component,
        props: trdata,
        options: {
          title: title,
          top: '20%',
          customClass: 'subpage-dlg dialog-width-edit'
        },
        events: {
          close: () => {
            this.CloseGlobalDialog('ClassifyEditDlg')
          },
          reload: () => {
            this.queryClassify()
          }
        }
      })
    }
  }
}
</script>
<style scope>
.dialog-width-edit{
  width: 28%;
  height: 400px;
}
</style>
