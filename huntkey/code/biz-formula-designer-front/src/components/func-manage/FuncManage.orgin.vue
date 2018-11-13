<template>
  <div class="func-manage">
    <!--标题栏-->
    <el-row>
      <el-col :span="24">
        <div class="page-header nav-background">
          <span class="page-header__page-title">函数管理</span>
        </div>
      </el-col>
    </el-row>

    <!--查询条件区-->
    <el-row class="common-vgap">
      <el-col :span="24">
        <el-form :inline="true" :model="queryParameters" class="page-search-form" :rules="pageData.rules" ref="form">
          <el-form-item label="函数分类">
            <el-select v-model="queryParameters.fundFunCatagoryId" placeholder="函数分类">
              <el-option label="-全部-" value=""></el-option>
              <el-option v-for="funCatagory in classifyData"
                :label="funCatagory.fnccClassifyName"
                :value="funCatagory.fnccId"
                :key="funCatagory.fnccId" >
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="函数名称">
            <el-input v-model="queryParameters.fundFunName" placeholder="函数名称"></el-input>
          </el-form-item>

          <el-form-item label="函数状态">
            <el-select v-model="queryParameters.fundState" placeholder="函数状态">
              <el-option label="-全部-" value=""></el-option>
              <el-option v-for="funStatus in funStatusDict"
                :label="funStatus.label"
                :value="funStatus.val"
                :key="funStatus.val" >
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item>
            <el-button type="primary" @click="query">查询</el-button>
          </el-form-item>
          <el-form-item>
            <el-button @click="showFuncEditDlg()">新增</el-button>
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>

    <!-- 数据展示区 -->
    <el-row>
      <el-col :span="24">
        <el-table
          :data="pageData.funManageTableData" @sort-change="doSortChange"
          border
          style="width: 100%"
          :height="$store.state.app.windowHeight - 285"
          >
          <el-table-column type="index" align='center' width="70" label="序号"></el-table-column>
          <el-table-column header-align="center" align="center" sortable="custom"
            prop="fnccClassifyName"
            label="函数分类"
            width="150">
          </el-table-column>
          <el-table-column header-align="center" align="center" show-overflow-tooltip sortable="custom"
            prop="fundFunName"
            label="函数名称"
            width="250">
          </el-table-column>
          <el-table-column header-align="center" align="center" show-overflow-tooltip sortable="custom"
            prop="fundFunDesc"
            label="函数说明">
          </el-table-column>
          <el-table-column header-align="center" align="center" :formatter="tableFunStatusFormat" sortable="custom"
            prop="fundState"
            label="函数状态"
            width="120">
          </el-table-column>
           <el-table-column header-align="center" align="center" sortable="custom"
            prop="moduser"
            label="维护人"
            width="180">
          </el-table-column>
           <el-table-column header-align="center" align="center" sortable :formatter="tableDateFormat" sortable="custom"
            prop="modtime"
            label="维护时间"
            width="180">
          </el-table-column>
           <el-table-column header-align="center" align="center"
            label="操作"
            width="180">
            <template scope="scope">
              <el-button size="small" @click="showFuncEditDlg(scope.row)" type="text">编辑</el-button>
              <el-button size="small" @click="delFun(scope.row.fundId)" type="text">删除</el-button>
            </template>
          </el-table-column>
        </el-table>

        <!--分页区域-->
        <div class="table-pagination-wrapper">
          <el-pagination
            @size-change="sizeChange"
            @current-change="query"
            :current-page="queryParameters.pageNum"
            :page-sizes="[10, 20, 50, 100]"
            :page-size="queryParameters.pageSize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="pageData.totalCount">
          </el-pagination>
        </div>
      </el-col>
    </el-row>

    <!-- 编辑、新增弹出的对话框 -->
    <!-- <el-dialog
      :title="pageConfig.funcEditDlgTitle"
      v-if="pageConfig.funcEditDlgVisible"
      :visible.sync="pageConfig.funcEditDlgVisible"
      size="large"
      class="subpage-dlg__wrapper"
      custom-class="subpage-dlg"
      >
      <func-edit :funcId="pageConfig.funcEditDlgFunId" @saveSuccess="saveSuccess"></func-edit>
    </el-dialog> -->
  </div>
</template>
<script>
import { mapGetters } from 'vuex'
import { funcService, classifyService } from '@/api'
import formatMixin from '@/mixin/data-format-mixin'
import FuncEdit from './FuncEdit' // 导入组件
export default {
  name: 'funcManage',
  mixins: [formatMixin],
  // 页面数据
  data () {
    return {
      // pageConfig: {
      //   fu"ncEditDlgTitle: '函数维护-新增',
      //   funcEditDlgVisible: false,
      //   funcEditDlgFunId: ''
      // },
      // 查询参数， 默认都为空字符串
      queryParameters: {
        pageNum: 1,
        pageSize: this.CONFIG.page.defaultPageSize,
        sortOrder: '',
        sortName: '',
        fundFunCatagoryId: '',
        fundFunName: '',
        fundState: ''
      },
      // 表单数据
      pageData: {
        // 分页数据
        pageNum: 1,
        pageSize: this.CONFIG.page.defaultPageSize,
        // 查询条件数据
        fundFunCatagoryId: '',
        fundFunName: '',
        fundState: '',
        // 列表总数
        totalCount: 0,
        // 总页数
        totalPage: 0,
        // 列表数据
        funManageTableData: [],
        // 验证规则
        rules: {
          fundFunName: {
            validator: (rules, value, callback) => {
              // TODO
              callback()
            }
          }
        }
      },
      // 验证错误信息
      errorInfo: {
        funcManage: ''
      },
      // // 需要查看的版本信息
      // versionToView: null,
      // // 允许新增标识
      // allowCreateFlag: false,
      // 新增版本窗口显示标识
      newFuncDialogVisible: false,
      editFuncDialogVisible: false,
      // loading效果标识
      isSearching: false,
      isDeleting: false,
      //  分类函数字典
      classifyData: [],
      classifyObj: {}
    }
  },
  watch: {
    $route (to, from) {
      // 从路由获取参数查询
      if (!this.isSearching) {
        this.setQueryDataFromRout()
        // 查询数据
        this.queryFunc()
      }
    }
  },
  mounted () { // 页面初始化调用的方法
    this.init()
  },
  methods: {
    init () {
      classifyService.queryClassify().then((data) => {
        // console.info('初始化分类函数字典')
        // console.info(data)
        this.classifyData = data
        this.queryFunc()
      })
    },
    /**
     * 查询参数验证
     * @return {[type]} 通过与否的promise
     */
    queryParameterValidate () {
      return new Promise((resolve, reject) => {
        const fundFunName = this.queryParameters.fundFunName
        resolve()
        if (!fundFunName) {
          // TODO 校验
        }
      })
    },
    /**
     * 从路由中获取参数
     */
    setQueryDataFromRout () {
      const routeData = {
        pageNum: this.$route.query.pageNum ? parseInt(this.$route.query.pageNum) : 1,
        pageSize: this.$route.query.pageSize ? parseInt(this.$route.query.pageSize) : this.CONFIG.page.defaultPageSize
      }
      this.utils.clonePropsWhenExist(this.queryParameters, routeData)
    },
    /**
     * 查询
     */
    query () {
      // 表单验证
      this.$refs.form.validate(result => {
        if (result) {
          // this.Utils.clonePropsWhenExist(this.queryParameters, this.pageData)
          this.queryParameters.pageNum = 1
          this.queryFunc()
        }
      })
    },
    /**
     * 调用查询服务，获取数据，注入表格
     */
    queryFunc () {
      // console.log('执行函数queryFunc')
      // 当前为查询状态，该状态下所有的路由和分页事件都不触发
      this.isSearching = true
      // 验证分页参数
      // this.validatePageParams()
      // 做参数验证，从路由获取的参数不经过表单，需要再次验证
      console.info('queryParameters')
      console.info(this.queryParameters)
      this.queryParameterValidate().then(() => {
        // 查询数据
        funcService.queryFunc(this.queryParameters).then(data => {
          // console.log(data)
          // 回填路由
          this.$router.push({query: this.queryParameters})
          // 回填Form表单
          this.Utils.clonePropsWhenExist(this.pageData, this.queryParameters)
          this.pageData.funManageTableData = data.list
          this.pageData.totalCount = data.total
          this.pageData.totalPage = parseInt(this.pageData.totalCount / this.pageData.pageSize + 1)
          this.$nextTick(() => {
            this.isSearching = false
          })
        }).catch(() => {
          this.isSearching = false
        })
      }).catch(err => {
        // this.Utils.clonePropsWhenExist(this.pageData, this.queryParameters)
        this.errorInfo.funcManage = err.funcManage
        this.isSearching = false
      })
    },
    /**
     * 验证分页参数，自动调整
     */
    validatePageParams () {
      // 页数不是数字，默认是首页
      if (typeof this.queryParameters.pageNum !== 'number' || !this.queryParameters.pageNum) {
        this.queryParameters.pageNum = 1
      }
      // 分页大小不是数据，默认是默认分页大小
      if (typeof this.queryParameters.pageSize !== 'number' || !this.queryParameters.pageNum) {
        this.queryParameters.pageSize = this.CONFIG.page.defaultPageSize
      }
      // 请求分页数小于1，分页置为1
      if (this.queryParameters.pageNum < 1) {
        this.queryParameters.pageNum = 1
      }
      // 存在最大页数，且请求分页数大于最大页数，置为最大页数
      if (!!this.pageData.totalPage && this.queryParameters.pageNum > this.pageData.totalPage) {
        this.queryParameters.pageNum = this.pageData.totalPage
      }
      // 分页大小不在pageList范围内，置为默认分页大小
      if (this.CONFIG.page.defaultPageSizeList.indexOf(this.queryParameters.pageSize) < 0) {
        this.queryParameters.pageSize = this.CONFIG.page.defaultPageSize
      }
    },
    /**
     * 分页大小改变
     * @param  新分页大小
     */
    sizeChange (size) {
      if (!this.isSearching) {
        // 更改分页大小，重新从第一页开始查
        this.queryParameters.pageSize = size
        this.queryParameters.pageNum = 1
        this.queryFunc()
      }
    },
    /**
     * 页数改变
     * @param  新页码
     */
    currentChange (current) {
      if (!this.isSearching) {
        this.queryParameters.pageNum = current
        this.queryFunc()
      }
    },
    /**
     * 新增/编辑打开弹窗
     */
    showFuncEditDlg (row) {
      if (row) {
        let trdata = {mode: 'edit', data: row}
        this.openNewDialog({name: 'funcEditDialog', title: '函数维护', props: trdata})
      } else {
        let trdata = {mode: 'new', data: this.queryParameters}
        this.openNewDialog({name: 'funcEditDialog', title: '函数维护', props: trdata})
      }
    },
    openNewDialog ({name, title, props}) {
      this.OpenGlobalDialog({
        name: name,
        component: FuncEdit,
        props: props,
        options: {
          title: title,
          top: '17%',
          customClass: 'subpage-dlg dialog-width-xxl'
        },
        events: {
          saveCond: this.saveCond,
          close: () => {
            this.CloseGlobalDialog('funcEditDialog')
          }
        }
      })
    },
    delFun (funcId) {
      if (!this.isDeleting) {
        this.$confirm('是否删除该函数？', '提示', {
          confirmButtonText: '确定',
          canceButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.isDeleting = true
          funcService.removeFunc(funcId).then(() => {
            this.$message.success('删除成功！')
            this.isDeleting = false
            this.removeSuccess()
          }).catch(() => {
            this.isDeleting = false
          })
        })
      }
    },
    createSuccess () {
      this.queryParameters.pageNum = 1
      this.newFuncDialogVisible = false
      this.queryFunc()
    },
    updateSuccess () {
      this.queryFunc()
    },
    removeSuccess () {
      this.editFuncDialogVisible = false
      if (this.pageData.funManageTableData.length === 1 && this.pageData.pageNum > 1) {
        this.queryParameters.pageNum = this.queryParameters.pageNum - 1
      } else {
        this.queryFunc()
      }
    },
    saveSuccess () {
      this.pageConfig.funcEditDlgVisible = false
      this.queryFunc()
    },
    doSortChange ({ column, prop, order }) {
      this.queryParameters.sortName = prop || ''
      this.queryParameters.sortOrder = order === 'ascending' ? 'ASC' : 'DESC'
      this.queryFunc()
    }
  },
  computed: {
    ...mapGetters({
      funCatagoryDict: 'getFunCatagoryDict',
      funStatusDict: 'getFunStatusDict'
    })
  },
  components: {
    'func-edit': FuncEdit
  }
}
</script>
<style scope>
/*.dialog-width-self1{
  width: 85%;
  height: 650px;
}*/
</style>
