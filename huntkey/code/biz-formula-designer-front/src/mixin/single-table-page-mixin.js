/*
 * 单列表页面混合
 * 为什么要用混合而不用工具方法: 因为需要操作数据, 钩子, 表格事情等.
 * 对于表格事件来说, 入参固定, 无法自由获取到其他的表格相关数据, 因此无法使用工具方法, 只能使用混合.
 * 对于查询事件有几种触发机制.
 * 1. 页面点击查询按钮(或者在查询form里的回车时间) 触发查询操作. [表单中的查询参数+页面绑定的分页大小, 页码置为1]
 * 2. 页面切换分页(页码, 每页数据条数),修改排序字段 触发查询操作, [使用上次查询成功的参数替代表单中的查询参数+页面绑定的分页大小, 页码]
 * 3. 路由变化触发查询操作.  对于路由变化, 先把路由数据设置到tableA.config
 *
 * 把所有的数据都放在一个tableStore(tableA)对象里, 方便把一些方法, 事件作为参数的一部分传到工具方法或者其他插件里.
 * 方法命名: on... :事件,  do.. 表示由用户行为导致的方法
 */
const singleTablePageMixin = {
  data () {
    return {
      tableA: {
        queryForm: { // 绑定查询条件的Form, 各种查询时的非分页排序的查询条件都放在Form里校验
        },
        queryFormRules: {
        },
        rows: [],
        initQueryParams: {}, // 初始化的查询参数, 用于查询表格的重置查询等.
        lastQueryParams: {}, // 最后一次经过验证的查询条件,切换分页时, 清空form中的内容, 使用该条件查询
        queryParams: { // 各种操作不直接操作 tableA.queryParams , 仅仅在调用查询接口之前, 把tableA.config中的查询参数拷贝过来
          pageNum: 1,
          pageSize: this.CONFIG.page.defaultPageSize, // 20,
          sortName: '',
          sortOrder: ''
        },
        pager: { // 绑定表单的pager信息
          pageNum: 1,
          pageSize: this.CONFIG.page.defaultPageSize, // 20,
          pageSizes: this.CONFIG.page.defaultPageSizeList, // [20, 50, 100],
          totalCount: 0,
          totalPage: 0 // 总页数
        },
        events: {
          // 为什么要把事件放在tableA里面? 可以把事件当成参数传个pager组件
          onPageSizeChange: (size) => {
            this.TableUtils.onPageSizeChange(this.tableA, size)
          },
          onCurrentPageChange: (current) => {
            this.TableUtils.onCurrentPageChange(this.tableA, current)
          },
          // 为什么要把SortChange事件放在tableA里面? 没想好, 凑个热闹
          onSortChange: ({column, prop, order}) => {
            this.TableUtils.onSortChange(this.tableA, {column, prop, order})
          }
        },
        methods: {
          // 为什么要从init方法的入口created里挪到mounted里? 因为增加了表单数据的校验, 在created里, 表单元素可能没加载完. 2017-08-22
          init: () => {
            // 会在组件中设置tableA.queryParams, 在组件创建后, 把设置的值保留一份作为可恢复的初始值
            this.Utils.clonePropsDeep(this.tableA.initQueryParams, this.tableA.queryParams)
            this.Utils.clonePropsDeep(this.tableA.lastQueryParams, this.tableA.initQueryParams)
            this.tableA.methods.doQueryDataFromRouter()
          },
          // 按钮点击触发查询操作
          doQueryData: () => {
            this.TableUtils.doQueryData(this.tableA)
          },
          doResetQueryForm: () => {
            // 清空查询表格
            // this.TableUtils.doResetQueryForm(this.tableA.queryForm, this.tableA.initQueryParams, 1)
            console.log('重置')
            this.$refs[this.tableA.config.queryFormRef].resetFields()
          },
          // methods 中的方法可以根据实际情况被覆盖
          // 从路由中获取其他参数
          doQueryDataFromRouter: () => {
            this.TableUtils.doQueryDataFromRouter(this.tableA, this.$route)
          },
          setQueryParamsFromRoute: () => {
            if (this.$route.query) {
              this.Utils.clonePropsDeep(this.tableA.queryForm, this.$route.query, 1)
            }
          },
          // 校验表格查询参数(分页相关部分)
          validatePagerParams: () => {
            return this.TableUtils.validatePagerParams(this.tableA)
          },
          // 校验表格查询参数(业务相关部分)
          validateQueryParams: () => {
            return this.TableUtils.validateQueryParams(this, this.tableA)
          },
          beforeReQuery: () => {
            return this.TableUtils.beforeReQuery(this.tableA)
          },
          // 查询表格数据相关逻辑
          queryTableData: () => {
            return this.TableUtils.queryTableData(this, this.tableA)
          },
          queryTableDataOper: () => {
            // 把查询调用接口这一步单抽出来, 如果需要对查询结果做处理或者不是使用ApiUtils.get方法获取数据, 则覆盖此方法
            return this.ApiUtils.get(this.tableA.config.url, {}, {
              params: this.tableA.queryParams
            })
          },
          querySuccessCallback: (resData) => {
            // console.log('querySuccessCallback', resData)
            // 根据实际业务在使用该混合的组件中覆盖该方法
          },
          queryErrorCallback: (error) => {
            console.log('queryErrorCallback', error)
            // 根据实际业务在使用该混合的组件中覆盖该方法
          },
          // 把表格的行索引转换成所有分页内数据的行索引
          calcRowIndex: (rowIndex) => {
            return this.TableUtils.calcTableRowIndex(this.tableA, rowIndex)
          }
        },
        config: {
          url: '',
          firstQuery: true, // 初始化时, firstQuery为true, 控制回填路由时用 router.replace 还是 router.push
          isSearching: false,
          queryFormRef: 'queryParamsForm', // 对应查询表单的ref, 用于表单校验用
          defaultPageSizeList: this.CONFIG.page.defaultPageSizeList
        }
      }
    }
  },
  mounted () {
    this.tableA.methods.init()
  },
  methods: {
  }, // end of methods
  watch: {
    $route (to, from) {
      // 从路由获取参数查询
      this.tableA.methods.doQueryDataFromRouter()
    }
  }
} // end of mixin
export default singleTablePageMixin
