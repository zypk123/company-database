<template>
  <div class="func-desc-language">
    <el-row>
      <el-col :span="24">
        <div class="page-header nav-background">
          <span class="page-header__page-title ">方法描述语言</span>
        </div>
      </el-col>
    </el-row>
    <el-row class="common-vgap">
      <el-col :span="24">
        <el-form :inline="true" :model="queryData" class="page-search-form" ref="funcDescForm">
          <el-form-item label="变量名称">
            <el-input v-model="queryData.varName" placeholder="变量名称"></el-input>
          </el-form-item>

          <el-form-item label="变量状态">
            <el-select v-model="queryData.varStatus" placeholder="变量状态">
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
            <el-button @click="showEditDlg('')">新增</el-button>
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="24">
        <el-table class="mytable"
                  :data="pageData.tableData" @sort-change="doSortChange"
                  border
                  :height="$store.state.app.windowHeight - 285"
                  style="width: 100%">
          <el-table-column type="index" align='center' width="70" label="序号"></el-table-column>
          <el-table-column header-align="center" align="center" sortable="custom"
                           prop="vrntVarName"
                           label="变量名称"
                           width="180">
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
              <el-button size="small" @click="showEditDlg(scope.row.vrntId)" type="text">编辑</el-button>
              <el-button size="small" @click="removeFuncDesc(scope.row.vrntId)" type="text">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
        <div class="table-pagination-wrapper">
          <el-pagination
            @size-change="doTablePageSizeChange"
            @current-change="doTablePageCurrentChange"
            :current-page="queryData.pageNum"
            :page-sizes="[10, 20, 50, 100]"
            :page-size="queryData.pageSize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="pageData.totalCount">
          </el-pagination>
        </div>
      </el-col>
    </el-row>
  </div>
</template>
<script>
  import { mapGetters } from 'vuex'
  import FuncDescEdit from './FuncDescEdit'
  import funcDescService from '@/api/services/funcDesc-service'
  import dataFormatMixin from '@/mixin/data-format-mixin'
  export default {
    name: 'funcDescLanguage',
    mixins: [dataFormatMixin],
    data () {
      return {
        queryData: {
          pageNum: 1,
          pageSize: 10,
          varName: '',
          varStatus: '',
          sortName: '',
          sortOrder: ''
        },
        pageData: {
          pageNum: 1,
          pageSize: 10,
          pageSizes: [10, 20, 50, 100],
          sortName: '',
          sortOrder: '',
          varName: '',
          varStatus: '',
          totalCount: 0,
          totalPage: 0,
          tableData: []
        },
        isSearching: false,
        isDeleting: false
      }
    },
    created () {
      this.queryFuncDesc()
    },
    methods: {
      doSearch () {
        this.queryData.pageNum = 1
        this.queryData.pageSize = 10
        this.queryFuncDesc()
      },
      queryFuncDesc () {
        this.isSearching = true
        this.validatePageParams()
        funcDescService.queryFuncDescLanguage(this.queryData).then((data) => {
          this.$router.push({query: this.queryData})
          this.Utils.clonePropsWhenExist(this.pageData, this.queryData)
          this.pageData.tableData = data.list
          this.pageData.totalCount = data.total
          this.pageData.totalPage = parseInt(this.pageData.totalCount / this.pageData.pageSize + 1)
          this.$nextTick(() => {
            this.isSearching = false
          })
        }).catch((err) => {
          this.$message.error(err.message)
          this.isSearching = false
        })
      },
      validatePageParams () {
        if (typeof this.queryData.pageNum !== 'number') {
          this.queryData.pageNum = 1
        }
        if (typeof this.queryData.pageSize !== 'number') {
          this.queryData.pageSize = 10
        }
        if (this.queryData.pageNum < 1) {
          this.queryData.pageNum = 1
        }
        if (!!this.pageData.totalPage && this.queryData.pageNum > this.pageData.totalPage) {
          this.queryData.pageNum = this.pageData.totalPage
        }
        if ([10, 20, 50, 100].indexOf(this.queryData.pageSize) < 0) {
          this.queryData.pageSize = 10
        }
      },
      doTablePageSizeChange (pageSize) {
        if (!this.isSearching) {
          this.queryData.pageSize = pageSize
          this.queryData.pageNum = 1
          this.queryFuncDesc()
        }
      },
      doTablePageCurrentChange (current) {
        if (!this.isSearching) {
          this.queryData.pageNum = current
          this.queryFuncDesc()
        }
      },
      removeFuncDesc (varId) {
        if (!this.isDeleting) {
          this.$confirm('是否删除该方法描述语言？', '提示', {
            confirmButtonText: '确定',
            canceButtonText: '取消',
            type: 'warning'
          }).then(() => {
            this.isDeleting = true
            funcDescService.deleteFuncDescLanguage(varId).then(() => {
              this.$message.success('删除成功')
              this.isDeleting = false
              this.removeSuccess()
            }).catch((err) => {
              this.$message.error(err.message)
              this.isDeleting = false
            })
          }).catch(() => {
            this.$message('取消删除')
            this.isDeleting = false
          })
        }
      },
      removeSuccess () {
        if (this.pageData.tableData.length === 1 && this.pageData.pageNum > 1) {
          this.queryData.pageNum = this.queryData.pageNum - 1
          this.queryFuncDesc()
        } else {
          this.queryFuncDesc()
        }
      },
      doSortChange ({ column, prop, order }) {
        this.queryData.sortName = prop || ''
        this.queryData.sortOrder = order === 'ascending' ? 'ASC' : 'DESC'
        this.queryFuncDesc()
      },
      showEditDlg (varId) {
        if (varId) {
          let temp = {mode: 'edit', data: varId}
          this.openNewDialog({name: 'funcDescEdit', title: '方法描述语言维护', props: temp})
        } else {
          let temp = {mode: 'new', data: varId}
          this.openNewDialog({name: 'funcDescEdit', title: '方法描述语言维护', props: temp})
        }
      },
      openNewDialog ({name, title, props}) {
        this.OpenGlobalDialog({
          name: name,
          component: FuncDescEdit,
          props: props,
          options: {
            title: title,
            top: '17%',
            customClass: 'subpage-dlg dialog-width-xxl'
          },
          events: {
            saveSuccess: () => {
              this.CloseGlobalDialog(name)
              this.queryFuncDesc()
            },
            close: () => {
              this.CloseGlobalDialog(name)
            }
          }
        })
      }
    },
    // 路由跳转先不考虑
    computed: {
      ...mapGetters({
        varStatusDict: 'getVarStatusDict'
      })
    }
  }
</script>
<style scope>

</style>
