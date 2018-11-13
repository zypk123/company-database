<template>
  <div>
		<el-form :inline="true"
		         :model="pageData"
		         class="demo-form-inline">
			<el-form-item label="方法分类" style="margin-left: 20px">
				<el-input v-model="pageData.edmm_type_name" size="small"  placeholder=""  icon="plus"  @focus="OpenTree" @click="OpenTree" @blur="blurType" readonly></el-input>
				<input type="hidden" v-model="pageData.edmmType">
			</el-form-item>
			<el-form-item label="程序类型">
        <el-select v-model="pageData.edmmProgramType" size="small" filterable>
					<el-option
            v-for="options in edmm_program_type"
            :key="options.id"
            :label="options.codeName"
            :value="options.codeValue"
            >
          </el-option>
          <!-- <el-option v-for="options in allDict['edmm_program_type']" :key="options.value" :label="options.label" :value="options.value"></el-option> -->
				</el-select>
			</el-form-item>
			<el-form-item label="方法名称">
				<el-input v-model="pageData.edmmName" size="small"
				          placeholder="" ></el-input>
			</el-form-item>
      <el-form-item label="所属类">
        <el-input v-model="pageData.edmm_class_names" size="small"
                  placeholder=""  icon="plus"  @focus="chooseClasses" @click="chooseClasses" @blur="blurClass" readonly></el-input>
        <input type="hidden" v-model="pageData.edmmClass">
      </el-form-item>
			<el-form-item label="方法状态">
				<el-select v-model="pageData.edmmStatus" size="small" filterable>
					<el-option v-for="options in edmm_status" :key="options.id" :label="options.label" :value="options.val"></el-option>
				</el-select>
			</el-form-item>
			<el-form-item>
				<el-button type="info"  @click="query()" icon="search">查询&nbsp</el-button>
				<el-button type="info"  @click="newMethod" >新增</el-button>
<!--
    <el-dialog title="新增" v-model="newVersionDialogVisible" custom-class="dialog-width-xxl">
      <method-manage v-if="newVersionDialogVisible" @saveSuccess="createSuccess"></method-manage>
    </el-dialog>-->
			</el-form-item>
		</el-form>
		<el-table :data="pageData.tableData" highlight-current-row v-loading="isSearching"
    :height="$store.state.windowStore.windowHeight - 185">
			<el-table-column
        type="index"
        align='center'
        label="序号"
        width="70">
      </el-table-column>
			<el-table-column
        prop="edmmTypeName"
        label="方法分类"
			  align='center' show-overflow-tooltip width="100">
        </el-table-column>
			<el-table-column
        prop="edmProgramTypeName"
        label="程序类型"
        align='center' width="100">
        </el-table-column>
			<el-table-column
        prop="edmmName"
			  label="方法名称"
			  header-align="center"
        align='left'
        show-overflow-tooltip
        width="200">
        </el-table-column>
			<el-table-column
        prop="edmmDesc"
			  label="方法描述"
				header-align="center"
        show-overflow-tooltip
			  align='left'>
        </el-table-column>
			<el-table-column label="可覆盖"	align='center' width="80">
        <template scope="scope">
        <div v-if="scope.row.isAuto == 1">是</div>
        <div v-else>否</div>
      </template>
      </el-table-column>
			<el-table-column label="自定义方法" align='center' width="80">
      <template scope="scope">
        <div v-if="scope.row.isDefined == 1">是</div>
        <div v-else>否</div>
      </template>
      </el-table-column>
			<el-table-column
        prop="edmmVer"
			  label="版本号"
			  align='center'
        width="80">
        </el-table-column>
			<el-table-column label="方法状态" align='center' width="100">
        <template scope="scope">
        <div v-if="scope.row.edmmStatus == 1">启用</div>
        <div v-else-if="scope.row.edmmStatus == 0">禁用</div>
      </template>
      </el-table-column>
			<el-table-column
        prop="moduser"
			  label="维护人"
			  align='center' width="120">
        </el-table-column>
			<el-table-column
        prop="modtimeStr"
			  label="维护时间"
			  align='center'
        width="180px"></el-table-column>
			<el-table-column label="操作" align='center' width="80">
				<template scope="scope">
					<el-button size="small" @click="editMethod(scope.row)" type="operate" icon="rx-bianji2" title="编辑"></el-button>
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
import { functionService } from '@/api/services/method'
import methodClass1 from '@/components/method-manager/MethodClassify'
// import methodManageAdd from './MethodManageAdd'
import methodCreater from './MethodCreater'
import chooseClass from './ChooseClasses'
// import methodManageEdit from './MethodManageEdit'
// import { dictTypes } from '@/types'
// import { mapGetters } from 'vuex'

export default {
  name: 'methodManage',
  data () {
    return {
      // 新增方法显示标识
      // newVersionDialogVisible: false,
      treeVisible: false,
      vmPro: '',
      // 查询参数,默认都为空字符串
      queryParameters: {
        pageNum: 1,
        pageSize: this.CONFIG.page.defaultPageSize,
        edmmType: '',
        edmmProgramType: '',
        edmmName: '',
        edmmStatus: '',
        edmmClasses: ''
      },
      // 表格数据
      pageData: {
        pageNum: 1,
        pageSize: this.CONFIG.page.defaultPageSize,
        edmm_type_name: '',
        edmmType: '',
        edmmProgramType: '',
        edmmName: '',
        // 所属类
        edmm_class_names: '',
        edmmClasses: '',
        edmmStatus: '',
        // 列表总数
        totalCount: 0,
        // 总页数
        totalPage: 0,
        // 列表数据
        tableData: [],
        edmm_program_type: [],
        edmm_status: [{
          id: -1,
          val: '',
          label: '全部'
        }, {
          id: 1,
          val: '1',
          label: '启用'
        }, {
          id: 0,
          val: '0',
          label: '禁用'
        }]
      },
      isSearching: false,
      edmm_program_type: [],
      edmm_status: [{
        id: -1,
        val: '',
        label: '全部'
      }, {
        id: 1,
        val: '1',
        label: '启用'
      }, {
        id: 0,
        val: '0',
        label: '禁用'
      }]
    }
  },
  created: function () {
    functionService.getProgramType().then(data => {
      this.edmm_program_type = data
      this.pageData.edmm_program_type = data
    })
    // 从路由中获取参数查询
    this.setQueryDataFromRout()
    // 做初始表单验证
    // 查询数据
    this.queryMethods()
  },
  watch: {
    $route (to, from) {
      // 从路由获取参数查询
      if (!this.isSearching) {
        this.setQueryDataFromRout()
        // 查询数据
        this.queryMethods()
      }
    }
  },
  // computed: {
  //   ...mapGetters({
  //     allDict: dictTypes.GET_ALL_DICTS
  //   })
  // },
  methods: {
    /**
     * 打开新增窗口，废弃
     */
   /* addNewMethod: function () {
      this.$openDialog({
        name: 'add-dialog',
        component: methodManageAdd,
        options: {
          title: '新增方法',
          customClass: 'dialog-width-xxl'
        },
        events: {
          createSuccess: this.createSuccess
        },
        props: {
          type: '0',
          page: 'manage'
        }
      })
    }, */
    blurType () {
      if (this.pageData.edmm_type_name === '') {
        this.pageData.edmmType = ''
      }
    },
    newMethod: function () {
      this.$openDialog({
        name: 'new-dialog',
        component: methodCreater,
        options: {
          title: '新增方法',
          customClass: 'dialog-width-xl'
        },
        events: {
          createSuccess: this.createSuccess
        }
      })
    },
    editMethod (row) {
      this.$openDialog({
        name: 'edit-dialog',
        component: methodCreater,
        options: {
          title: '编辑方法',
          customClass: 'dialog-width-xl'
        },
        props: {
          methodId: row.id
        },
        events: {
          updateSuccess: this.updateSuccess,
          deleteSuccess: this.deleteSuccess
        }
      })
    },
    /**
     * 查看方法详情，废弃
     * @param  查看的行数据
     */
   /* viewMethod (row) {
      console.log(row.id)
      this.$openDialog({
        name: 'edit-dialog1',
        component: methodManageAdd,
        options: {
          title: '编辑方法',
          customClass: 'dialog-width-xxl'
        },
        props: {
          methodData: row,
          type: '1',
          page: 'manage'
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
    }, */
    /**
     * 添加成功事件
     */
    createSuccess () {
      // 保存成功，刷新回到首页
      this.queryParameters.pageNum = 1
      this.$closeDialog('new-dialog')
      this.queryMethods()
    },
    /**
     * 更新成功事件
     */
    updateSuccess () {
     // console.log(1111)
      this.$closeDialog('edit-dialog')
      // 更新成功，留在当前页
      this.queryMethods()
    },
    /**
     * 删除成功事件
     */
    deleteSuccess () {
      // 关闭dialog
      this.$closeDialog('edit-dialog')
      if (this.pageData.tableData.length === 1 && this.pageData.pageNum > 1) {
        // 当前页条数为1，且不是第一页，返回当前页的上一页
        this.queryParameters.pageNum = this.queryParameters.pageNum - 1
        this.queryMethods()
      } else {
        // 留在当前页
        this.queryMethods()
      }
    },
    /**
     * 从路由中获取参数
     */
    setQueryDataFromRout () {
      const routeData = {
        pageNum: this.$route.query.pageNum ? parseInt(this.$route.query.pageNum) : 1,
        pageSize: this.$route.query.pageSize ? parseInt(this.$route.query.pageSize) : this.CONFIG.page.defaultPageSize,
        edmmType: this.$route.query.edmmType ? this.$route.query.edmmType : '',
        edmmProgramType: this.$route.query.edmmProgramType ? this.$route.query.edmmProgramType : '',
        edmmName: this.$route.query.edmmName ? this.$route.query.edmmName : '',
        edmmStatus: this.$route.query.edmmStatus ? this.$route.query.edmmStatus : '',
        edmmClasses: this.$route.query.edmmClasses ? this.$route.query.edmmClasses : ''
      }
      this.UTILS.setDataFromOther(this.queryParameters, routeData)
    },
    /**
     * 调用查询服务，获取数据，注入表格
     */
    queryMethods () {
      // 当前为查询状态，改状态下所有的路由和分页事件都不触发
      this.isSearching = true
      // 验证分页参数
      this.validatePageParams()
      // 查询数据
      console.log(this.queryParameters)
      functionService.queryMethods(this.queryParameters).then(data => {
        // 回填路由
        this.$router.push({query: this.queryParameters})
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
    },
    /**
     * 查询
     */
    query () {
      // 表单验证
      this.UTILS.setDataFromOther(this.queryParameters, this.pageData)
      this.queryParameters.pageNum = 1
      this.queryMethods()
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
     * 分页大小改变
     * @param  新分页大小
     */
    sizeChange (size) {
      if (!this.isSearching) {
        // 更改分页大小，重新从第一页开始查
        this.queryParameters.pageSize = size
        this.queryParameters.pageNum = 1
        this.queryMethods()
      }
    },
    /**
     * 页数改变
     * @param  新页码
     */
    currentChange (current) {
      if (!this.isSearching) {
        this.queryParameters.pageNum = current
        this.queryMethods()
      }
    },
    /**
     *方法分类树
     */
    OpenTree: function () {
      this.$openDialog({
        name: 'tree1-dialog',
        component: methodClass1,
        options: {
          title: '方法分类',
          customClass: 'dialog-width-s'
        },
        events: {
          confirmNode: this.confirmNode
        },
        buttons: [{
          text: '确定',
          icon: 'check',
          type: 'primary',
          handler: () => {
            const result = this.vmPro.getResult()
            if (result) {
              this.pageData.edmm_type_name = result.name
              this.pageData.edmmType = result.id
              this.$closeDialog('tree1-dialog')
              console.log(JSON.stringify(result) + '...')
            }
            console.log('确定')
          }
        }, {
          text: '分类维护',
          handler: () => {
            // this.$openDialog({
            //   name: 'method-classfication',
            //   component: methodClassficationMain,
            //   options: {
            //     title: '分类维护',
            //     customClass: 'dialog-width-xl'
            //   }
            // })
            this.vmPro.methodClassficationMain()
          }
        }, {
          text: '取消',
          icon: 'close',
          handler: () => {
            this.$closeDialog('tree1-dialog')
          }
        }]/* ,
        events: {
          changeType: this.changeType
        } */
      }).then(vm => {
        this.vmPro = vm
      })
    },
    /**
     *方法分类树选择
     */
    changeType: function (data) {
     // console.log(data)
      this.pageData.edmm_type_name = data.name
      this.pageData.edmm_type = data.id
      console.log(this.pageData.edmm_type_name + '...')
      this.$closeDialog('tree1-dialog')
    },
    // 双击确认
    confirmNode (data) {
      this.pageData.edmm_type_name = data.name
      this.pageData.edmm_type = data.id
      this.$closeDialog('tree1-dialog')
    },
    // 所属类
    chooseClasses () {
      this.$openDialog({
        name: 'choose-class',
        component: chooseClass,
        options: {
          title: '选择所属类',
          customClass: 'dialog-width-s'
        },
        buttons: [{
          text: '确定',
          icon: 'check',
          type: 'primary',
          handler: () => {
            // console.log('this.vmCho', this.vmCho)
            const result = this.vmCho.getResult()
            if (result) {
              // 多选。循环？
              // this.pageData.edmm_class_name = JSON.parse(JSON.stringify(result.names))
              this.pageData.edmm_class_names = result.names.toString()
              this.pageData.edmmClasses = result.ids.toString()
              this.$closeDialog('choose-class')
            }
          }
        }, {
          text: '取消',
          icon: 'close',
          handler: () => {
            this.$closeDialog('choose-class')
          }
        }]
      }).then(vm => {
        this.vmCho = vm
      })
    },
    blurClass () {
      if (this.pageData.edmm_class_names === '') {
        this.pageData.edmmClasses = ''
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
