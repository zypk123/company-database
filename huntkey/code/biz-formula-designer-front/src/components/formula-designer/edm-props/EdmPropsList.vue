<template>
  <div class="edm-props-list">
    <!-- 公式设计器 选择类-->
    <el-card class="box-card has-header-a">
      <div slot="header" class="clearfix">
        <span class="a-card-header-title">请选择属性</span>
      </div>
      <div style="height:100%;"
        v-loading="store.loadingInfo.isLoadingProp"
        element-loading-text="正在加载类属性"
        >
        <el-scrollbar
        tag="div"
        class="list-items-scroll"
        wrap-class="list-items-scroll-wrapper"
        view-class="el-select-dropdown__list"
        >
        <edm-prop-item
          v-for="edmProp in store.curEdmClassProps"
          :key="edmProp.id"
          :initData="edmProp"
          @selectProp="selectProp"
        >
        </edm-prop-item>
        </el-scrollbar>
      </div>
    </el-card>
  </div>
</template>
<script>
import EdmPropItem from './EdmPropItem'
export default {
  name: 'EdmPropsList',
  props: ['store'],
  data () {
    return {
    }
  },
  methods: {
    selectProp (propId) {
      this.$emit('selectProp', propId)
    }
  },
  computed: {
  },
  components: {
    EdmPropItem
  }
}
</script>
<style scoped>
</style>
