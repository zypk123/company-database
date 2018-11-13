<template>
  <div class="user-manage">
   <!--标题栏-->
    <el-row>
      <el-col :span="24">
        <div class="page-header nav-background">
          <span class="page-header__page-title">用户管理</span>
        </div>
      </el-col>
    </el-row>

    <!--查询条件区-->
    <el-row class="common-vgap">
      <el-col :span="24">
        <el-form :inline="true" :model="queryParameters" class="page-search-form" :rules="pageData.rules" ref="form">
          <el-form-item label="姓名：">
            <el-input v-model="queryParameters.userName" placeholder="姓名"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" icon="search" @click="queryUser">查询</el-button>
          </el-form-item>
          <el-form-item class="float:right">
            <el-button type="primary" icon="plus" @click="showUserEditDlg()">添加</el-button>
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>

    <!-- 数据展示区 -->
    <el-row>
      <el-col :span="24">
        <el-table
          :data="pageData.userTableData" @sort-change="doSortChange"
          border
          style="width: 100%"
          :height="$store.state.app.windowHeight - 285">
          <el-table-column type="index" align='center' width="70" label="序号"></el-table-column>
          <el-table-column header-align="center" align="center"
            prop="userName"
            label="用户名"
            width="260">
          </el-table-column>
          <el-table-column header-align="center" align="center" show-overflow-tooltip
            prop="nickName"
            label="昵称"
            width="260">
          </el-table-column>
          <el-table-column header-align="center" align="center" show-overflow-tooltip
            prop="email"
            label="邮箱"
            width="260">
          </el-table-column>
          <el-table-column header-align="center" align="center"
            prop="passWord"
            label="密码"
            width="260">
          </el-table-column>
           <el-table-column header-align="center" align="center" sortable="custom" :formatter="tableDateFormat"
            prop="regTime"
            label="注册时间"
            width="260">
          </el-table-column>
          <el-table-column header-align="center" align="center"
            label="操作">
            <template scope="scope">
              <el-button size="small" @click="showUserEditDlg(scope.row)" type="text">编辑</el-button>
              <el-button size="small" @click="delUser(scope.row.id)" type="text">删除</el-button>
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
  </div>
</template>
<script>
import { mapGetters } from 'vuex'
import { userService } from '@/api'
import formatMixin from '@/mixin/data-format-mixin'
import UserEdit from './UserEdit' // 导入组件
export default {
  name: 'userManage',
  mixins: [formatMixin],
  // 页面数据
  data () {
    return {
      // 查询参数， 默认都为空字符串
      queryParameters: {
        pageNum: 1,
        pageSize: this.CONFIG.page.defaultPageSize,
        sortOrder: '',
        sortName: '',
        userName: ''
      },
      // 表单数据
      pageData: {
        // 分页数据
        pageNum: 1,
        pageSize: this.CONFIG.page.defaultPageSize,
        // 查询条件数据
        userName: '',
        // 列表总数
        totalCount: 0,
        // 总页数
        totalPage: 0,
        // 列表数据
        userTableData: [],
        // 验证规则
        rules: {
          userName: {
            validator: (rules, value, callback) => {
              // TODO
              callback()
            }
          }
        }
      },
      // 验证错误信息
      errorInfo: {
        userManage: ''
      },
      newUserDialogVisible: false,
      editUserDialogVisible: false,
      // loading效果标识
      isSearching: false,
      isDeleting: false
    }
  },
  // 监听
  watch: {
    $route (to, from) {
      // 从路由获取参数查询
      if (!this.isSearching) {
        this.setQueryDataFromRout()
        // 查询数据
        this.queryUser()
      }
    }
  },
  // 页面构建之前自动调用该方法
  mounted () {
    this.init()
  },
  methods: {
    init () {
      this.queryUser()
    },
    /**
     * 查询参数验证
     * @return {[type]} 通过与否的promise
     */
    queryParameterValidate () {
      return new Promise((resolve, reject) => {
        const userName = this.queryParameters.userName
        resolve()
        if (!userName) {
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
          this.queryParameters.pageNum = 1
          this.queryUser()
        }
      })
    },
    /**
     * 调用查询服务，获取数据，注入表格
     */
    queryUser () {
      // 当前为查询状态，该状态下所有的路由和分页事件都不触发
      this.isSearching = true
      // 验证分页参数
      // 做参数验证，从路由获取的参数不经过表单，需要再次验证
      console.info('queryParameters', this.queryParameters)
      this.queryParameterValidate().then(() => {
        // 查询数据
        userService.queryUser(this.queryParameters).then(data => {
          console.log('data', data)
          // 回填路由
          this.$router.push({query: this.queryParameters})
          // 回填Form表单
          this.Utils.clonePropsWhenExist(this.pageData, this.queryParameters)
          this.pageData.userTableData = data.list
          this.pageData.totalCount = data.total
          this.pageData.totalPage = parseInt(this.pageData.totalCount / this.pageData.pageSize + 1)
          this.$nextTick(() => {
            this.isSearching = false
          })
        }).catch(() => {
          this.isSearching = false
        })
      }).catch(err => {
        console.log(err)
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
        this.queryUser()
      }
    },
    /**
     * 页数改变
     * @param  新页码
     */
    currentChange (current) {
      if (!this.isSearching) {
        this.queryParameters.pageNum = current
        this.queryUser()
      }
    },
    /**
     * 新增/编辑打开弹窗
     */
    showUserEditDlg (row) {
      console.log('showUserEditDlg')
      if (row) {
        let trdata = {mode: 'edit', data: row}
        console.log('trdata', trdata)
        this.openNewDialog({name: 'userEditDialog', title: '用户维护-编辑', props: trdata})
      } else {
        let trdata = {mode: 'add', data: this.queryParameters}
        this.openNewDialog({name: 'userEditDialog', title: '用户维护-新增', props: trdata})
      }
    },
    openNewDialog ({name, title, props}) {
      this.OpenGlobalDialog({
        name: name,
        component: UserEdit,
        props: props,
        options: {
          title: title,
          top: '17%',
          customClass: 'subpage-dlg dialog-width-edit'
        },
        events: {
          saveCond: this.saveCond,
          close: () => {
            this.CloseGlobalDialog('userEditDialog')
          },
          reload: () => {
            this.queryUser()
          }
        }
      })
    },
    /**
     * 删除用户
     */
    delUser (id) {
      if (!this.isDeleting) {
        this.$confirm('是否删除该用户？', '提示', {
          confirmButtonText: '确定',
          canceButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.isDeleting = true
          userService.removeUser(id).then(() => {
            this.$message.success('删除成功！')
            this.isDeleting = false
            this.removeSuccess()
          }).catch(() => {
            this.isDeleting = false
          })
        })
      }
    },
    /**
     * 删除用户成功后执行的方法
     */
    removeSuccess () {
      this.editUserDialogVisible = false
      if (this.pageData.userTableData.length === 1 && this.pageData.pageNum > 1) {
        this.queryParameters.pageNum = this.queryParameters.pageNum - 1
      } else {
        this.queryUser()
      }
    },
    doSortChange ({ column, prop, order }) {
      this.queryParameters.sortName = prop || ''
      this.queryParameters.sortOrder = order === 'ascending' ? 'ASC' : 'DESC'
      this.queryUser()
    }
  },
  computed: {
    ...mapGetters({
      // funCatagoryDict: 'getFunCatagoryDict',
    })
  }
}
</script>
<style scope>
.dialog-width-edit {
  width: 25%;
  height: 400px;
}
</style>
