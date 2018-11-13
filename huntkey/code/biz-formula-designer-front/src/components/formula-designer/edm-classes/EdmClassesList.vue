<template>
  <div class="edm-classes-list">
    <!-- 公式设计器 选择类-->
    <el-card class="box-card has-header-a">
      <div slot="header" class="clearfix">
        <span class="a-card-header-title">请选择类</span>
      </div>
      <el-scrollbar
        tag="div"
        class="list-items-scroll"
        wrap-class="list-items-scroll-wrapper"
        view-class="el-select-dropdown__list"
        >
      <div v-for="edmClass in store.edmClasses" :key="edmClass.id" class="list-item"
        :class="{'is-current': edmClass.id===store.currentSelectedClass}"
        @click="listItemClickEvent($event,edmClass)">
        <i class="el-icon-caret-right"></i>
        {{edmClass.edmcName}}
        {{edmClass.edmcNameEn}}
      </div>
      </el-scrollbar>
    </el-card>
  </div>
</template>
<script>
export default {
  name: 'EdmClassesList',
  props: ['initData', 'store'],
  data () {
    return {
    }
  },
  methods: {
    listItemClickEvent (e, edmClass) {
      this.$emit('selectClass', edmClass)
    }
  },
  computed: {
  }
}
</script>
<style scoped>

</style>
