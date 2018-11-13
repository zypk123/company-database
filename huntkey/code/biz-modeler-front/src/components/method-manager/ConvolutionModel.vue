<template>
  <div>
    <el-form :inline="true"
             :model="queryCon"
             class="demo-form-inline">
      <el-form-item label="卷积公式">
        <el-select v-model="queryCon.edcoConvoluteFormula" size="small" filterable>
          <el-option :key="''" :label="'全部'"
                     :value="''"></el-option>
          <el-option v-for="options in queryCon.edco_convolute_formulas" :key="options.id" :label="options.codeName"
                     :value="options.codeValue"></el-option>
        </el-select>
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
        <el-input v-model="queryCon.edmcName" size="small"
                  placeholder=""></el-input>
      </el-form-item>
      <el-form-item label="属性名称">
        <el-input v-model="queryCon.edmpName" size="small"
                  placeholder=""></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="info" size="small"  icon="search"
                   @click="queryData">查询</el-button>
      </el-form-item>
    </el-form>

  <!--  <el-dialog title="属性扩展" v-model="attrVisible" size="full" @close="">
      <attr :id="attrid" :classid="classid" v-if="attrVisible" ></attr>
    </el-dialog>-->

    <el-table :data="tableData"
              v-loading="isSearching"
              :height="$store.state.windowStore.windowHeight - 185"
               highlight-current-row>
      <el-table-column type="index" align="center" width="70px" label="序号"></el-table-column>
      <el-table-column align="center" prop="edcoConvoluteFormulaName" label="卷积公式"></el-table-column>
      <el-table-column align="center" label="模型版本" width="100px">
        <template scope="scope">
          <el-button @click="ToModel(scope.row.edmdId, scope.row.edmdStatus)" type="text">{{scope.row.edmdVer}}</el-button>
        </template>
      </el-table-column>
      <el-table-column align="center" prop="edmdStatusDesc" label="模型状态" width="100px">
      </el-table-column>
      <el-table-column align="center" label="类名称">
        <template scope="scope">
          <el-button @click="ToClass(scope.row.edmdId, scope.row.edmdStatus, scope.row.edmcId)"   type="text">{{scope.row.edmcName}}</el-button>
        </template>
      </el-table-column>
      </el-table-column>
      <el-table-column align="center" label="属性名称">
        <template scope="scope">
          {{scope.row.edmpName}}
        </template>
      </el-table-column>
      <el-table-column align="center" prop="edcoUpdateTimeliness" label="卷积更新时效" width="100px"></el-table-column>
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
    </el-pagination>-->
  </div>
</template>

<script>
  import { versionTypes } from '@/types'
  import {methodModelService} from '@/api/services/method-model'

  export default {
    name: 'methodModel',
    data () {
      return {
        queryCon: {
          edcoConvoluteFormula: '',
          edco_convolute_formulas: [],
          edmpName: '',
          edmdVer: '',
          edmm_ver_option: [],
          edmcName: ''
        },
        queryParameters: {
          edcoConvoluteFormula: '',
          edmdVer: '',
          edmcName: '',
          edmpName: ''
        },
        tableData: [],
        isSearching: false,
        attrVisible: false,
        attrid: '',
        classid: ''
      }
    },
    created: function () {
      methodModelService.getConvolution().then(data => {
        this.queryCon.edco_convolute_formulas = data
      })
      methodModelService.getVersions().then(data => {
        this.queryCon.edmm_ver_option = data
      })
      // 从路由中获取参数查询
      this.setQueryDataFromRout()
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
      ToModel: function (modelId, status) {
        this.$router.push({
          name: 'classView',
          params: {
            id: modelId,
            mode: status === 7 || status === 6 ? versionTypes.VERSION_VIEW : versionTypes.VERSION_EDIT
          }
        })
      },
      ToClass: function (modelId, status, classId) {
        this.$router.push({
          name: 'classView',
          params: {
            id: modelId,
            mode: status === 7 || status === 6 ? versionTypes.VERSION_VIEW : versionTypes.VERSION_EDIT,
            classId
          }
        })
      },
      ToAttr: function (attrId, modelId) {
        // this.$router.push({path: '/attributeVindicate/' + attrId + '/' + modelId})
        this.attrVisible = true
        this.attrid = attrId
        this.classid = modelId
      },
      /**
       * 从路由中获取参数
       */
      setQueryDataFromRout () {
        const routeData = {
          edcoConvoluteFormula: this.$route.query.edcoConvoluteFormula ? this.$route.query.edcoConvoluteFormula : '',
          edmdVer: this.$route.query.edmdVer ? this.$route.query.edmdVer : '',
          edmcName: this.$route.query.edmcName ? this.$route.query.edmcName : '',
          edmpName: this.$route.query.edmpName ? this.$route.query.edmpName : ''
        }
        this.UTILS.setDataFromOther(this.queryParameters, routeData)
      },
      query: function () {
        // 当前为查询状态，改状态下所有的路由和分页事件都不触发
        this.isSearching = true
        methodModelService.getProperties(this.queryParameters).then(data => {
          // 回填路由
          this.$router.push({query: this.queryParameters})
          // 回填Form表单
         // console.log(JSON.stringify(this.queryParameters))
          this.UTILS.setDataFromOther(this.queryCon, this.queryParameters)
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
      }
    }
  }
</script>

<style>

</style>
