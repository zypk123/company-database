import Utils from '@/utils/utils'

const TableUtils = {
  // 路由变化时触发查询操作
  doQueryDataFromRouter (tableStore, route) {
    if (tableStore.config.isSearching) {
      return
    }
    tableStore.config.isSearching = true
    tableStore.methods.setQueryParamsFromRoute()
    // 从路由中获取分页参数 为什么PageParam要放在后面: 设置默认值不会被覆盖
    const routeData = {
      pageNum: route.query.pageNum ? parseInt(route.query.pageNum) : 1,
      pageSize: route.query.pageSize ? parseInt(route.query.pageSize) : 20,
      sortName: route.query.sortName ? route.query.sortName : tableStore.initQueryParams.sortName ? tableStore.initQueryParams.sortName : '',
      sortOrder: route.query.sortOrder ? route.query.sortOrder : tableStore.initQueryParams.sortOrder ? tableStore.initQueryParams.sortOrder : ''
    }
    Utils.clonePropsDeep(tableStore.queryParams, routeData, 1)
    tableStore.pager.totalPage = 0
    tableStore.methods.queryTableData()
  },
  // 按钮点击触发查询操作
  doQueryData (tableStore) {
    if (tableStore.config.isSearching) {
      return
    }
    tableStore.config.isSearching = true
    Utils.clonePropsDeep(tableStore.queryParams, tableStore.pager, 1)

    tableStore.pager.totalPage = 0
    tableStore.queryParams.pageNum = 1
    tableStore.methods.queryTableData()
  },
  doResetQueryForm (tableStore) {
    // 清空查询表格
    Utils.clonePropsDeep(tableStore.queryForm, tableStore.initQueryParams, 1)
  },
  // 校验表格查询参数(分页相关部分)
  validatePagerParams (tableStore) {
    // 在使用该混合的组件中覆盖该方法
    return new Promise((resolve, reject) => {
       // 页数不是数字，默认是首页
      if (typeof tableStore.queryParams.pageNum !== 'number' || !tableStore.queryParams.pageNum) {
        tableStore.queryParams.pageNum = 1
      }
      // 分页大小不是数据，默认是默认分页大小
      if (typeof tableStore.queryParams.pageSize !== 'number' || !tableStore.queryParams.pageNum) {
        tableStore.queryParams.pageSize = this.EnvConfig.page.defaultPageSize
      }
      // 请求分页数小于1，分页置为1
      if (tableStore.queryParams.pageNum < 1) {
        tableStore.queryParams.pageNum = 1
      }

      // 存在最大页数，且请求分页数大于最大页数，置为最大页数
      if (!!tableStore.pager.totalPage && tableStore.pager.pageNum > tableStore.pager.totalPage) {
        tableStore.pager.pageNum = tableStore.pager.totalPage
        tableStore.queryParams.pageNum = tableStore.pager.totalPage
      }

      // // 分页大小不在pageList范围内，置为默认分页大小
      if (tableStore.config.defaultPageSizeList.indexOf(tableStore.queryParams.pageSize) < 0) {
        tableStore.queryParams.pageSize = tableStore.config.defaultPageSizeList
      }
      resolve()
    })
  },
  validateQueryParams: (vm, tableStore) => {
    return new Promise((resolve, reject) => {
      vm.$refs[tableStore.config.queryFormRef].validate((valid) => {
        if (valid) {
          resolve()
        } else {
          reject({retCode: -1, message: '表单校验不通过,请检查表单数据.'})
        }
      })
    })
  },
  queryTableData (vm, tableStore) {
    tableStore.methods.validatePagerParams()
    .then(() => {
      return tableStore.methods.validateQueryParams()
    })
    .then(() => {
      Utils.clonePropsDeep(tableStore.queryParams, tableStore.queryForm, 1)
      return tableStore.methods.queryTableDataOper()
    })
    .then((resData) => {
      // console.info('queryTableData.F.333', JSON.stringify(tableStore.queryForm))
      // console.info('queryTableData.P.333', JSON.stringify(tableStore.queryParams))
      // 回填路由
      // console.log('回填路由', JSON.stringify(tableStore.queryParams))
      if (tableStore.config.firstQuery) {
        tableStore.config.firstQuery = false
        vm.$router.replace({query: tableStore.queryParams})
      } else {
        vm.$router.push({query: tableStore.queryParams})
      }

      // 回填tableA.lastQueryParams
      Utils.clonePropsDeep(tableStore.lastQueryParams, tableStore.queryParams, 1)
      // 回填Form表单
      Utils.clonePropsDeep(tableStore.queryForm, tableStore.queryParams, 1)
      // 回填分页信息
      Utils.clonePropsDeep(tableStore.pager, tableStore.queryParams, 1)
      tableStore.pager.totalCount = resData.total
      tableStore.pager.totalPage = Math.ceil(tableStore.pager.totalCount / tableStore.pager.pageSize)

      // 填充表单数据
      tableStore.rows = resData.list

      vm.$nextTick(() => {
        tableStore.config.isSearching = false
      })
      tableStore.methods.querySuccessCallback(resData)
    }).catch((error) => {
      // 回填Form表单
      Utils.clonePropsDeep(tableStore.queryForm, tableStore.queryParams, 1)
      // 回填分页信息
      Utils.clonePropsDeep(tableStore.pager, tableStore.queryParams, 1)
      tableStore.config.isSearching = false
      tableStore.methods.queryErrorCallback(error)
    })
  },
  beforeReQuery: (tableStore) => {
    // 切换页码,每页数据大小,排序信息时, 使用上次的查询参数. 此方法为一下公共的操作
    if (tableStore.config.isSearching) {
      return false
    }
    tableStore.config.isSearching = true
    // 先把上次查询的参数放到路由里, 通过Form来校验, 通过Form来校验
    Utils.clonePropsDeep(tableStore.queryForm, tableStore.lastQueryParams, 1)
    return true
  },
  onPageSizeChange: (tableStore, size) => {
    if (!tableStore.methods.beforeReQuery()) return
    tableStore.queryParams.pageSize = size
    tableStore.queryParams.pageNum = 1
    tableStore.methods.queryTableData()
  },
  onCurrentPageChange: (tableStore, current) => {
    if (!tableStore.methods.beforeReQuery()) return
    tableStore.queryParams.pageNum = current
    tableStore.methods.queryTableData()
  },
  onSortChange: (tableStore, {column, prop, order}) => {
    if (!tableStore.methods.beforeReQuery()) return
    if (order === 'ascending') order = 'asc'
    if (order === 'descending') order = 'desc'
    tableStore.queryParams.sortName = prop || ''
    tableStore.queryParams.sortOrder = order || ''
    tableStore.methods.queryTableData()
  },
  // 把表格的行索引转换成所有分页内数据的行索引
  calcTableRowIndex (tableStore, rowIndex) {
    return (tableStore.pager.pageNum - 1) * tableStore.pager.pageSize + rowIndex + 1
  }
}
export default TableUtils
