<template>
  <div>
    <el-form :inline="true" :model="pageData" class="demo-form-inline" :rules="pageData.rules" ref="form">
      <el-form-item label="模型版本" style="padding-left: 20px" prop="edmdVer" :error="errorInfo.edmdVer">
        <el-input v-model="pageData.edmdVer" placeholder="模型版本"></el-input>
      </el-form-item>
      <el-form-item label="更新说明" prop="edmdUpdateDesc">
        <el-input v-model="pageData.edmdUpdateDesc" placeholder="更新说明"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="info" @click="query" icon="search">查询</el-button>
        <el-button type="info" @click="addNewVersion" :disabled="!allowCreateFlag" icon="plus">新增版本</el-button>
      </el-form-item>
    </el-form>
    <el-table :data="pageData.tableData" highlight-current-row v-loading="isSearching"
    :height="$store.state.windowStore.windowHeight - 185">
      <el-table-column align="center" width="70" label="序号">
        <template scope="scope">
          {{pageData.pageSize * (pageData.pageNum - 1) + scope.$index + 1}}
        </template>
      </el-table-column>
      <el-table-column prop="edmdCode" label="模型版本" align='center' width="200">
        <template scope="scope">
          <el-button size="small" @click="viewVersion(scope.row)" type="text">{{scope.row.edmdVer}}</el-button>
        </template>
      </el-table-column>
      <el-table-column prop="edmdStatusName" label="模型状态" align='center' width="200"></el-table-column>
      <el-table-column prop="edmdUpdateDesc" label="更新说明" header-align="center" align='left'
      :show-overflow-tooltip="true"></el-table-column>
      <el-table-column prop="moduser" label="维护人" width="150" align='center'></el-table-column>
      <el-table-column prop="modtimeStr" label="维护时间" width="200" align='center'></el-table-column>
      <el-table-column label="模型操作" width="200" align='center'>
        <template scope="scope">
          <el-button title="编辑" @click="editClass(scope.row)"
          v-if="scope.row.edmdStatus !== 6 && scope.row.edmdStatus !== 7" type="operate" icon="rx-bianji2">
          </el-button>
          <el-button title="浏览" @click="viewClass(scope.row)"
            v-if="scope.row.edmdStatus === 6 || scope.row.edmdStatus === 7" type="operate" icon="rx-yulan">
          </el-button>
          <el-button title="导出" @click="exportModel(scope.row.id)" type="operate" icon="rx-daochu2"></el-button>
        </template>
      </el-table-column>
    </el-table>
    <div class="mright">
      <el-pagination 
      layout="total, sizes,prev, pager, next, jumper" 
      :page-sizes="CONFIG.page.defaultPageSizeList"
      :total="pageData.totalCount"
      :page-size="pageData.pageSize"
      :current-page="pageData.pageNum"
      @size-change="sizeChange"
      @current-change="currentChange"></el-pagination>
    </div>
  </div>
</template>

<script>
  import versionEditor from './VersionEditor'
  import versionCreater from './VersionCreater'
  import { versionService } from '@/api'
  import { versionTypes } from '@/types'

  export default {
    name: 'list',
    data () {
      return {
        // 查询参数,默认都为空字符串
        queryParameters: {
          pageNum: 1,
          pageSize: this.CONFIG.page.defaultPageSize,
          edmdVer: '',
          edmdUpdateDesc: ''
        },
        // 表单数据
        pageData: {
          pageNum: 1,
          pageSize: this.CONFIG.page.defaultPageSize,
          edmdVer: '',
          edmdUpdateDesc: '',
          // 列表总数
          totalCount: 0,
          // 总页数
          totalPage: 0,
          // 列表数据
          tableData: [],
          // 验证规则
          rules: {
            edmdVer: {
              validator: (rules, value, callback) => {
                if (!value || this.UTILS.validate(value, 'version')) {
                  callback()
                } else {
                  callback('版本号格式不正确')
                }
              }
            }
          }
        },
        // 验证错误信息
        errorInfo: {
          edmdVer: ''
        },
        // 需要查看的版本信息
        versionToView: null,
        // 允许新增标识
        allowCreateFlag: false,
        // loading效果标识
        isSearching: false
      }
    },
    watch: {
      $route (to, from) {
        // 从路由获取参数查询
        if (!this.isSearching) {
          this.setQueryDataFromRout()
          // 查询数据
          this.queryVersions()
        }
      }
    },
    created () {
      // 从路由中获取参数查询
      this.setQueryDataFromRout()
      // 做初始表单验证
      // 查询数据
      this.queryVersions()
      // 查询编辑状态
      this.queryVersionStatus()
    },
    methods: {
      /**
       * 查询参数验证
       * @return {[type]} 通过与否的promise
       */
      quertParameterValidate () {
        return new Promise((resolve, reject) => {
          const edmdVer = this.queryParameters.edmdVer
          if (!edmdVer || this.UTILS.validate(edmdVer, 'version')) {
            // 验证不通过
            resolve()
          } else {
            reject({edmdVer: '版本号格式不正确'})
          }
        })
      },
      /**
       * 从路由中获取参数
       */
      setQueryDataFromRout () {
        const routeData = {
          pageNum: this.$route.query.pageNum ? parseInt(this.$route.query.pageNum) : 1,
          pageSize: this.$route.query.pageSize ? parseInt(this.$route.query.pageSize) : this.CONFIG.page.defaultPageSize,
          edmdVer: this.$route.query.edmdVer ? this.$route.query.edmdVer : '',
          edmdUpdateDesc: this.$route.query.edmdUpdateDesc ? this.$route.query.edmdVer : ''
        }
        this.UTILS.setDataFromOther(this.queryParameters, routeData)
      },
      /**
       * 查询
       */
      query () {
        // 表单验证
        this.$refs.form.validate(result => {
          if (result) {
            this.UTILS.setDataFromOther(this.queryParameters, this.pageData)
            this.queryParameters.pageNum = 1
            this.queryVersions()
          }
        })
      },
      /**
       * 调用查询服务，获取数据，注入表格
       */
      queryVersions () {
        // 当前为查询状态，改状态下所有的路由和分页事件都不触发
        this.isSearching = true
        // 验证分页参数
        this.validatePageParams()
        // 做参数验证，从路由获取的参数不经过表单，需要再次验证
        this.quertParameterValidate().then(() => {
          // 查询数据
          versionService.queryVersion(this.queryParameters).then(data => {
            // 回填路由
            this.$router.replace({query: this.queryParameters})
            // 回填Form表单
            this.UTILS.setDataFromOther(this.pageData, this.queryParameters)
            this.pageData.tableData = data.list
            this.pageData.totalCount = data.total
            this.pageData.totalPage = parseInt(this.pageData.totalCount / this.pageData.pageSize + 1)
            this.$nextTick(() => {
              this.isSearching = false
            })
          }).catch(() => {
            this.isSearching = false
          })
        }).catch(err => {
          this.UTILS.setDataFromOther(this.pageData, this.queryParameters)
          this.errorInfo.edmdVer = err.edmdVer
          this.isSearching = false
        })
      },
      /**
       * 验证分页参数，自动调整
       * @return {[type]} [description]
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
       * 查询版本状态，判断当前是否可新增版本
       */
      queryVersionStatus () {
        versionService.getVersionStatus().then(data => {
          if (data.addStatus === 0) {
            this.allowCreateFlag = false
          } else {
            this.allowCreateFlag = true
          }
        })
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
          this.queryVersions()
        }
      },
      /**
       * 页数改变
       * @param  新页码
       */
      currentChange (current) {
        if (!this.isSearching) {
          this.queryParameters.pageNum = current
          this.queryVersions()
        }
      },
      /**
       * 查看版本详情
       * @param  查看的行数据
       */
      viewVersion (row) {
        this.$openDialog({
          name: 'view-dialog',
          component: versionEditor,
          options: {
            title: '编辑版本',
            customClass: 'dialog-width-m'
          },
          props: {
            versionData: row
          },
          events: {
            updateSuccess: this.updateSuccess,
            deleteSuccess: this.deleteSuccess
          }
        })
      },
      /**
       * 打开新增版本窗口
       */
      addNewVersion: function () {
        this.$openDialog({
          name: 'create-dialog',
          component: versionCreater,
          options: {
            title: '新增版本',
            customClass: 'dialog-width-m'
          },
          events: {
            createSuccess: this.createSuccess
          }
        })
      },
      /**
       * 编辑版本
       * @param  {[type]} id   [description]
       * @param  {[type]} code [description]
       */
      editClass: function (versionData) {
        this.$router.push({
          name: 'classView',
          params: {
            id: versionData.id,
            mode: versionTypes.VERSION_EDIT
          }
        })
      },
      viewClass: function (versionData) {
        this.$router.push({
          name: 'classView',
          params: {
            id: versionData.id,
            mode: versionTypes.VERSION_VIEW
          }
        })
      },
      /**
       * 浏览版本
       * @param  {[type]} id   [description]
       * @param  {[type]} code [description]
       */
      browsemod: function (id, code) {
        this.$router.push('/modelMain/0/' + id)
        this.$store.dispatch('edmCode', code)
      },
      /**
       * 导出版本
       * @param  {[type]} id [description]
       * @return {[type]}    [description]
       */
      exportModel: function (id) {
        window.location.href = this.CONFIG.baseUrl.upload + 'modelers/export/?id=' + id
      },
      /**
       * 添加成功事件
       */
      createSuccess () {
        // 保存成功，刷新回到首页
        this.queryParameters.pageNum = 1
        // 关闭dialog
        this.$closeDialog('create-dialog')
        this.queryVersions()
        this.queryVersionStatus()
      },
      /**
       * 更新成功事件
       */
      updateSuccess () {
        // 更新成功，留在当前页
        this.$closeDialog('view-dialog')
        this.queryVersions()
        this.queryVersionStatus()
      },
      /**
       * 删除成功事件
       */
      deleteSuccess () {
        // 关闭dialog
        this.$closeDialog('view-dialog')
        if (this.pageData.tableData.length === 1 && this.pageData.pageNum > 1) {
          // 当前页条数为1，且不是第一页，返回当前页的上一页
          this.queryParameters.pageNum = this.queryParameters.pageNum - 1
          this.queryVersions()
          this.queryVersionStatus()
        } else {
          // 留在当前页
          this.queryVersions()
          this.queryVersionStatus()
        }
      }
    }
  }
</script>

<style scoped>
  /*翻页*/
  .mright {
    float:right;
    margin-right: 70px;
    margin-top: 15px;
    overflow: hidden;
  }
</style>
