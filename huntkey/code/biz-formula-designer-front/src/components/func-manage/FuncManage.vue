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
        <el-form :inline="true" class="page-search-form"
          :model="tableA.queryForm" :rules="tableA.queryFormRules"
          :ref="tableA.config.queryFormRef"
          @keydown.esc.native="tableA.methods.doResetQueryForm"
          @keydown.enter.native="tableA.methods.doQueryData"
          >
          <el-form-item label="函数分类">
            <el-select v-model="tableA.queryForm.fundFunCatagoryId" placeholder="函数分类">
              <el-option label="-全部-" value=""></el-option>
              <el-option v-for="funCatagory in classifyData"
                :label="funCatagory.fnccClassifyName"
                :value="funCatagory.fnccId"
                :key="funCatagory.fnccId" >
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="函数名称">
            <el-input v-model="tableA.queryForm.fundFunName" placeholder="函数名称"></el-input>
          </el-form-item>

          <el-form-item label="函数状态">
            <el-select v-model="tableA.queryForm.fundState" placeholder="函数状态">
              <el-option label="-全部-" value=""></el-option>
              <el-option v-for="funStatus in funStatusDict"
                :label="funStatus.label"
                :value="funStatus.val"
                :key="funStatus.val" >
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item>
            <el-button type="primary" @click="tableA.methods.doQueryData" :loading="tableA.config.isSearching">查询</el-button>
            <!--<el-button @click="tableA.methods.doResetQueryForm">重置查询</el-button>-->
            <el-button @click="showFuncEditDlg()">新增</el-button>
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>

    <!-- 数据展示区 -->
    <el-row>
      <el-col :span="24">
        <!-- v-loading="tableA.config.isSearching"
            element-loading-text="正在查询数据" -->
        <el-table
          border stripe highlight-current-row style="width:100%;"
            :data="tableA.rows"
            :height="AppUtils.calcHeight(285)"
            @sort-change = "tableA.events.onSortChange"

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
      </el-col>

      <el-col :span="24">
        <table-pager :table-store="tableA"></table-pager>
      </el-col>
    </el-row>
  </div>
</template>
<script>
import { mapGetters } from 'vuex'
import { funcService, classifyService } from '@/api'
import formatMixin from '@/mixin/data-format-mixin'
import singleTablePageMixin from '@/mixin/single-table-page-mixin'
import TablePager from '@/components/common-component/TablePager'
import FuncEdit from './FuncEdit' // 导入组件
export default {
  name: 'funcManage',
  mixins: [formatMixin, singleTablePageMixin],
  // 页面数据
  data () {
    return {
      tableA: {
        queryForm: {
          fundFunCatagoryId: '',
          fundFunName: '',
          fundState: ''
        },
        queryFormRules: {
        },
        queryParams: {
          fundFunCatagoryId: '',
          fundFunName: '',
          fundState: '',
          sortName: 'modtime',
          sortOrder: 'desc'
        },
        config: {
          url: this.CONFIG.baseUrl.formula + '/defineFunction/queryDefineFunction'
        }
      },
      newFuncDialogVisible: false,
      editFuncDialogVisible: false,
      // loading效果标识
      isDeleting: false,
      //  分类函数字典
      classifyData: [],
      classifyObj: {}
    }
  },
  mounted () { // 页面初始化调用的方法
    this.init()
  },
  methods: {
    init () {
      classifyService.queryClassifyNameList().then((data) => {
        // console.info('初始化分类函数字典')
        console.info(data)
        this.classifyData = data
        // this.tableA.methods.doQueryData()()
      })
    },
    /**
     * 新增/编辑打开弹窗
     */
    showFuncEditDlg (row) {
      if (row) {
        let trdata = {
          mode: 'edit',
          data: row,
          display: false
        }
        this.openNewDialog({name: 'funcEditDialog', title: '函数维护', props: trdata})
      } else {
        let trdata = {
          mode: 'new',
          data: this.tableA.queryForm,
          display: true
        }
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
          close: () => {
            this.CloseGlobalDialog(name)
            // this.createSuccess()
          },
          reload: () => {
            this.tableA.methods.doQueryData()
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
            this.tableA.methods.doQueryData()
          }).catch((error) => {
            this.AppUtils.showErrorMsg(error)
            this.isDeleting = false
          })
        })
      }
    }
  },
  computed: {
    ...mapGetters({
      funCatagoryDict: 'getFunCatagoryDict',
      funStatusDict: 'getFunStatusDict'
    })
  },
  components: {
    FuncEdit,
    TablePager
  }
}
</script>
<style scope>
/*.dialog-width-self1{
  width: 85%;
  height: 650px;
}*/
</style>
