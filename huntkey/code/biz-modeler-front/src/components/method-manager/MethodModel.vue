<template>
  <div>
    <el-form :inline="true"
             :model="queryCon"
             class="demo-form-inline">
      <el-form-item label="方法分类">
        <el-input v-model="queryCon.edmm_type_name" size="small"
                  placeholder=""
                  icon="plus"
                  readonly
                  @click="OpenTree" @focus="OpenTree"></el-input>
        <input type="hidden" v-model="queryCon.edmm_type">
        <input type="hidden"
               v-model="queryCon.edmm_type">
      </el-form-item>
      <el-form-item label="方法名称">
        <el-input v-model="queryCon.edmm_name" size="small"
                  placeholder=""></el-input>
      </el-form-item>
      <el-form-item label="模型版本">
        <el-select v-model="queryCon.edmdVer" size="small" filterable>
          <el-option :key="''" :label="'全部'"
                     :value="''"></el-option>
          <el-option v-for="options in queryCon.edmm_ver_option" :key="options" :label="options"
                     :value="options"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="类名称">
        <el-input v-model="queryCon.edmc_name" size="small"
                  placeholder=""></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="info" size="small"  icon="search"
                   @click="queryData()">查询</el-button>
      </el-form-item>
    </el-form>

    <el-table :data="tableData"
               v-loading="isSearching"
              :height="$store.state.windowStore.windowHeight - 185"
               highlight-current-row>
      <el-table-column type="index" align="center" width="70px" label="序号"></el-table-column>
      <el-table-column align="center" prop="edmtName" label="方法分类" width="100px" show-overflow-tooltip></el-table-column>
      <el-table-column align="center" label="方法名称(版本号)" show-overflow-tooltip width="180px">
        <template scope="scope">
          <el-button @click="ToMethod(scope.row)" type="text">{{scope.row.edmmName}}({{scope.row.edmmVer}})</el-button>
        </template>
      </el-table-column>
      <el-table-column header-align="center" align="left"  prop="edmmDesc" label="方法描述" show-overflow-tooltip></el-table-column>
      <el-table-column align="center" label="模型版本" width="100px">
        <template scope="scope">
          <el-button @click="ToModel(scope.row.edmdId, scope.row.edmdStatus)" type="text">{{scope.row.edmdVersion}}</el-button>
        </template>
      </el-table-column>
      <el-table-column align="center" prop="edmdStatus" label="模型状态" width="100px"></el-table-column>
      <el-table-column align="center" label="类名称" width="120px">
        <template scope="scope">
          <el-button @click="ToClass(scope.row.edmdId, scope.row.edmdStatus, scope.row.edmcId)" type="text">{{scope.row.edmcName}}</el-button>
        </template>
      </el-table-column>
      <el-table-column align="center" prop="moduser" label="维护人" width="100px"></el-table-column>
      <el-table-column align="center" prop="modtimeStr" label="维护时间" width="180px"></el-table-column>
    </el-table>
    <!--<div class="paging">
      <el-pagination @size-change="Query"
      @current-change="Query"
      :current-page="paging.pageNum"
      :page-sizes="[10, 20, 50, 100]"
      :page-size="paging.pageSize"
      layout="total,sizes, prev, pager, next,jumper"
      :total="paging.total">
      </el-pagination>
    </div>-->
  </div>
</template>
<script>
  import {methodModelService} from '@/api/services/method-model'
  import { versionTypes } from '@/types'
  import methodClass from '@/components/method-manager/MethodClassify'
  import methodCreater from './MethodCreater'
  export default {
    name: 'methodModel',
    data () {
      return {
        queryCon: {
          edmm_type_name: '',
          edmm_type: '',
          edmdVer: '',
          edmm_name: '',
          edmc_name: '',
          edmm_ver_option: []
        },
        queryParameters: {
          edmm_type_name: '',
          edmm_type: '',
          edmm_name: '',
          edmc_name: '',
          edmdVer: ''
        },
        // paging: {
        //   pageSize: 20,
        //   pageNum: 1,
        //   total: 0
        // },
        pageData: {
          edmm_type_name: '',
          edmm_name: '',
          edmc_name: '',
          edmdVer: ''
        },
        tableData: [],
        treeVisible: false,
        isSearching: false
      }
    },
    created: function () {
      methodModelService.getModelType().then(data => {
        this.queryCon.edmm_ver_option = data
      })
      // 从路由中获取参数查询
      this.setQueryDataFromRout()
      // 做初始表单验证
      // 查询数据
      this.query()
    },
    watch: {
      $route (to, from) {
        // 从路由获取参数查询
        if (!this.isSearching) {
          this.setQueryDataFromRout()
          // 查询数据
          this.query()
        }
      }
    },
    methods: {
      /**
       * 从路由中获取参数
       */
      setQueryDataFromRout () {
        const routeData = {
          edmm_type_name: this.$route.query.edmm_type_name ? this.$route.query.edmm_type_name : '',
          edmm_type: this.$route.query.edmm_type ? this.$route.query.edmm_type : '',
          edmm_name: this.$route.query.edmm_name ? this.$route.query.edmm_name : '',
          edmc_name: this.$route.query.edmc_name ? this.$route.query.edmc_name : '',
          edmdVer: this.$route.query.edmdVer ? this.$route.query.edmdVer : ''
        }
        this.UTILS.setDataFromOther(this.queryParameters, routeData)
      },
      ToModel: function (modelId, status) {
        this.$router.push({
          name: 'classView',
          params: {
            id: modelId,
            mode: status === '已发布' || status === '作废' ? versionTypes.VERSION_VIEW : versionTypes.VERSION_EDIT
          }
        })
      },
      ToClass: function (modelId, status, classId) {
        this.$router.push({
          name: 'propertyView',
          params: {
            id: modelId,
            mode: status === '已发布' || status === '作废' ? versionTypes.VERSION_VIEW : versionTypes.VERSION_EDIT,
            classId
          }
        })
      },
      ToMethod: function (row) {
        this.$openDialog({
          name: 'edit-dialog',
          component: methodCreater,
          options: {
            title: '编辑方法',
            customClass: 'dialog-width-xxl'
          },
          props: {
            methodId: row.id
          },
          events: {
            updateSuccess: this.updateSuccess,
            deleteSuccess: this.deleteSuccess
          },
          buttons: [{
            text: '确定',
            icon: 'check',
            type: 'primary',
            handler: () => {
              console.log('确定')
            }
          }, {
            text: '取消',
            icon: 'close',
            handler: () => {
              this.$closeDialog('edit-dialog')
            }
          }]
        })
      },
      /**
       * 更新成功事件
       */
      updateSuccess () {
        this.$closeDialog('edit-dialog')
        // 更新成功，留在当前页
        this.query()
      },
      /**
       * 删除成功事件
       */
      deleteSuccess () {
        // 关闭dialog
        this.$closeDialog('edit-dialog')
        this.query()
      },
      OpenTree: function () {
        this.$openDialog({
          name: 'tree-dialog',
          component: methodClass,
          options: {
            title: '方法分类',
            customClass: 'dialog-width-xl'
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
              if (result) {
                this.queryCon.edmm_type_name = result.name
                this.queryCon.edmm_type = result.id
                this.$closeDialog('tree-dialog')
                // console.log(JSON.stringify(result) + '...')
              }
              // console.log('确定')
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
      // 查询
      query: function () {
        // 当前为查询状态，改状态下所有的路由和分页事件都不触发
        this.isSearching = true
        methodModelService.queryData({type: this.queryParameters.edmm_type_name, name: this.queryParameters.edmm_name, version: this.queryParameters.edmdVer, className: this.queryParameters.edmc_name}).then(data => {
          // 回填路由
          this.$router.push({query: this.queryParameters})
          // 回填Form表单
         // console.log(JSON.stringify(this.queryParameters))
          this.UTILS.setDataFromOther(this.queryCon, this.queryParameters)
          // 回填表格
          this.tableData = data
          this.$nextTick(() => {
            this.isSearching = false
          })
        }).catch(() => {
          this.isSearching = false
        })
      },
      /**
       * 查询按钮
       */
      queryData () {
        // 表单验证
        // onsole.log(JSON.stringify(this.queryCon))
        this.UTILS.setDataFromOther(this.queryParameters, this.queryCon)
        this.query()
      },
      changeType: function (data) {
        this.queryCon.edmm_type_name = data.name
        this.queryCon.edmm_type = data.id
        this.$closeDialog('tree-dialog')
      },
      ClearType: function () {
        this.queryCon.edmm_type_name = ''
        this.queryCon.edmm_type = ''
      }
    }
  }
</script>
<style scoped>
</style>
