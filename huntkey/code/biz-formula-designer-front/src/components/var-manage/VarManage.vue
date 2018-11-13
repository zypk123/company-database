<template>
  <div class="var-manage">
    <el-row>
      <el-col :span="24">
        <div class="page-header nav-background">
          <span class="page-header__page-title ">变量管理</span>
        </div>
      </el-col>
    </el-row><!-- /.el-row 顶部标题栏 -->

    <el-row class="common-vgap">
      <el-col :span="24">
        <el-form :inline="true" :model="queryParameters" class="page-search-form" :rules="pageData.rules" ref="form">
          <el-form-item label="变量名称">
            <el-input v-model="queryParameters.varName" placeholder="变量名称"></el-input>
          </el-form-item>

          <el-form-item label="变量状态">
            <el-select v-model="queryParameters.varStatus" placeholder="变量状态">
              <el-option label="-全部-" value=""></el-option>
              <el-option v-for="varStatus in varStatusDict"
                :label="varStatus.label"
                :value="varStatus.val"
                :key="varStatus.val" >
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item>
            <el-button type="primary" @click="doSearch">查询</el-button>
          </el-form-item>
          <el-form-item>
            <el-button @click="showVarEditDlg('')">新增</el-button>
          </el-form-item>
        </el-form>
      </el-col>
    </el-row><!-- /.el-row 顶部查询按钮 -->

    <el-row>
      <el-col :span="24">
        <el-table class="mytable"
          :data="pageData.tableData" @sort-change="doSortChange"
          border
          :height="$store.state.app.windowHeight - 285"
          style="width: 100%">
          <el-table-column type="index" align='center' width="70" label="序号"></el-table-column>
          <el-table-column header-align="center" align="center" sortable="custom" show-overflow-tooltip
            prop="vrntVarName"
            label="变量名称"
            width="250">
          </el-table-column>
          <el-table-column header-align="center" align="left" show-overflow-tooltip
            prop="vrntVarDesc"
            label="说明">
          </el-table-column>
          <el-table-column header-align="center" align="center" sortable="custom" :formatter="tableVarStatusFormat"
            prop="vrntState"
            label="变量状态"
            width="180">
          </el-table-column>
           <el-table-column header-align="center" align="center" sortable="custom"
            prop="adduser"
            label="维护人"
            width="180">
          </el-table-column>
           <el-table-column header-align="center" align="center" sortable="custom" :formatter="tableDateFormat"
            prop="addtime"
            label="维护时间"
            width="180">
          </el-table-column>
           <el-table-column header-align="center" align="center"
            label="操作"
            width="180">
            <template scope="scope">
              <el-button size="small" @click="showVarEditDlg(scope.row.vrntId)" type="text">编辑</el-button>
              <el-button size="small" @click="removeSysVar(scope.row.vrntId)" type="text">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
        <div class="table-pagination-wrapper">
          <el-pagination
            @size-change="doVarManageTablePageSizeChange"
            @current-change="doVarMangeTablePageCurrentChange"
            :current-page="queryParameters.pageNum"
            :page-sizes="CONFIG.page.defaultPageSizeList"
            :page-size="queryParameters.pageSize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="pageData.totalCount">
          </el-pagination>
        </div>
      </el-col>
    </el-row> <!-- /.el-row 表格区域 -->

     <!-- :before-close="handleClose" -->
    <el-dialog
      :title="pageData.varEditDlgTitle"
      v-if="pageData.varEditDlgVisible"
      :visible.sync="pageData.varEditDlgVisible"
      size="large"
      class="subpage-dlg__wrapper"
      custom-class="subpage-dlg dialog-width-xl"
      >
      <var-edit :varId="pageData.varEditDlgVarId" @saveSuccess="saveSuccess" @cancleAddVar="cancleAddVar"></var-edit>
    </el-dialog>

  </div> <!-- /.var-manage-->


</template>
<script>
import { mapGetters } from 'vuex'
import { varService } from '@/api'
import formatMixin from '@/mixin/data-format-mixin'
import VarEdit from './VarEdit' // 导入组件
export default {
  name: 'varManage',
  mixins: [formatMixin],
  data () {
    return {
      queryParameters: {
        pageNum: this.CONFIG.page.defaultPageNumber,
        pageSize: this.CONFIG.page.defaultPageSize,
        varName: '', // 变量名
        varStatus: '', // 变量状态
        sortName: '',
        sortOrder: ''
      },
      pageData: {
        pageNum: this.CONFIG.page.defaultPageNumber,
        pageSize: this.CONFIG.page.defaultPageSize,
        pageSizes: this.CONFIG.page.defaultPageSizeList,
        sortName: '',
        sortOrder: '',
        varName: '',
        varStatus: '',
        totalCount: 0,
        totalPage: 0,
        tableData: [],
        rules: {
          varName: {
            validator: (rules, value, callback) => {
              // TODO
              callback()
            }
          }
        },
        varEditDlgTitle: '变量维护-新增',
        varEditDlgVisible: false,
        varEditDlgVarId: ''
      },
      errorInfo: { // 验证的错误信息
        // TODO
        varManage: ''
      },
      versionToview: null,
      allowCreateFalg: false, // 需要查看的版本信息
      newSystemVariantDialogVisible: false, // 允许新增标识
      editSystemVariantDialogVisible: false, // 编辑版本窗口显示标志
      isSearching: false, // loading 效果标识
      isDeleting: false
    } // end of return
  }, // end or data
  created () {
    // this.setQueryDataFromRout()
    this.querySystemVariants()
  },
  methods: {
    doSearch () {
      // this.Utils.clonePropsWhenExist(this.queryParameters, this.pagaData)
      this.$refs.form.validate(result => {
        if (result) {
          this.queryParameters.pageNum = 1
          this.querySystemVariants()
        }
      })
    },
    querySystemVariants () {
      this.isSearching = true
      this.validataPageParams()
      this.queryParameterValidate().then(() => {
        varService.querySysVarsPage(this.queryParameters).then((data) => {
          this.$router.push({query: this.queryParameters})
          this.Utils.clonePropsWhenExist(this.pageData, this.queryParameters) // TODO UTILS
          this.pageData.tableData = data.list
          this.pageData.totalCount = data.total
          this.pageData.totalPage = parseInt(this.pageData.totalCount / this.pageData.pageSize + 1)
          setTimeout(() => {
            this.isSearching = false
          })
        }).catch(() => {
          this.isSearching = false
        })
      }).catch(err => {
        this.errorInfo.varManage = err.varManage
        this.isSearching = false
      })
    },
    validataPageParams () {
      if (typeof this.queryParameters.pageNum !== 'number') {
        this.queryParameters.pageNum = 1
      }
      if (typeof this.queryParameters.pageSize !== 'number') {
        this.queryParameters.pageSize = this.CONFIG.page.defaultPageSize
      }
      if (this.queryParameters.pageNum < 1) {
        this.queryParameters.pageNum = 1
      }
      if (!!this.pageData.totalPage && this.queryParameters.pageNum > this.pageData.totalPage) {
        this.queryParameters.pageNum = this.pageData.totalPage
      }
      if (this.CONFIG.page.defaultPageSizeList.indexOf(this.queryParameters.pageSize) < 0) {
        this.queryParameters.pageSize = this.CONFIG.page.defaultPageSize
      }
    },
    queryParameterValidate () {
      return new Promise((resolve, reject) => {
        const varName = this.queryParameters.varName
        resolve()
        if (!varName) {
          // TODO 校验
        }
      })
    },
    setQueryDataFromRout () {
      const routeData = {
        pageNum: parseInt(this.$route.query.pageNum),
        pageSize: parseInt(this.$route.query.pageSize),
        varName: this.$route.query.varName,
        varStatus: this.$route.query.varStatus
      }
      this.utils.clonePropsWhenExist(this.queryParameters, routeData)
    },
    doVarManageTablePageSizeChange (pageSize) {
      if (!this.isSearching) {
        this.queryParameters.pageSize = pageSize
        this.queryParameters.pageNum = 1
        this.querySystemVariants()
      }
    },
    doVarMangeTablePageCurrentChange (current) {
      if (!this.isSearching) {
        this.queryParameters.pageNum = current
        this.querySystemVariants()
      }
    },
    removeSysVar (varId) {
      if (!this.isDeleting) {
        this.$confirm('是否删除该系统变量？', '提示', {
          confirmButtonText: '确定',
          canceButtonText: '取消',
          type: 'warning'
        }).then(() => {
          varService.checkSysVarInUse(varId).then((data) => {
            this.isDeleting = true
            varService.removeSysVar(varId).then(() => {
              this.$message.success('删除成功！')
              this.isDeleting = false
              this.removeSuccess()
            }).catch(() => {
              this.isDeleting = false
            })
          }).catch((data) => {
            this.$message.warning(data.message)
            this.isDeleting = false
          })
        })
      }
    },
    createSuccess () {
      this.queryParameters.pageNum = 1
      this.newSystemVariantDialogVisible = false
      this.querySystemVariants()
    },
    updateSuccess () {
      this.querySystemVariants()
    },
    removeSuccess () {
      this.editSystemVariantDialogVisible = false
      if (this.pageData.tableData.length === 1 && this.pageData.pageNum > 1) {
        this.queryParameters.pageNum = this.queryParameters.pageNum - 1
      } else {
        this.querySystemVariants()
      }
    },
    saveSuccess () {
      this.pageData.varEditDlgVisible = false
      this.querySystemVariants()
    },
    doSortChange ({ column, prop, order }) {
      this.queryParameters.sortName = prop || ''
      this.queryParameters.sortOrder = order === 'ascending' ? 'ASC' : 'DESC'
      this.querySystemVariants()
    },
    showVarEditDlg (varId) {
      this.pageData.varEditDlgTitle = '变量维护-新增'
      this.pageData.varEditDlgVarId = ''
      if (varId) {
        this.pageData.varEditDlgTitle = '变量维护-编辑'
        this.pageData.varEditDlgVarId = varId
        console.info(this.pageData.varEditDlgVarId)
      }
      this.pageData.varEditDlgVisible = true
    },
    cancleAddVar () {
      this.pageData.varEditDlgVisible = false
      this.querySystemVariants()
    }
  },
  watch: {
    $route (to, from) {
      if (!this.isSearching) {
        this.setQueryDataFromRout()
        this.querySystemVariants()
      }
    }
  },
  computed: { // 计算属性
    ...mapGetters({
      varStatusDict: 'getVarStatusDict'
    })
  },
  components: { // 组件注册
    VarEdit
  }
}
</script>
<style scoped>

</style>
