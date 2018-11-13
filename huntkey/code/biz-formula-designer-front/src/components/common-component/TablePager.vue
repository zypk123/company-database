<template>
  <div class="table-pagination-wrapper">
    <el-pagination
      layout="total, sizes,prev, pager, next, jumper"
      :page-sizes="tableStore.pager.pageSizes"
      :page-size="tableStore.pager.pageSize"
      :total="tableStore.pager.totalCount"
      :current-page="tableStore.pager.pageNum"
      @size-change="tableStore.events.onPageSizeChange"
      @current-change="tableStore.events.onCurrentPageChange">
    </el-pagination>
  </div>
</template>
<script>
export default {
  name: 'tablePager',
  props: ['tableStore']
}
</script>
