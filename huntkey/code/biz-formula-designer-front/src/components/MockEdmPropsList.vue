<template>
  <div class="mock-edm-props-list">
    <!-- 公式设计器 选择类-->
    <el-card class="box-card">
<!--       <div slot="header" class="clearfix">
        <span class="a-card-header-title">属性</span>
      </div> -->
      <div style="height:100%;"
        element-loading-text="正在加载类属性"
        >
        <el-scrollbar
        tag="div"
        class="list-items-scroll"
        wrap-class="list-items-scroll-wrapper"
        view-class="el-select-dropdown__list"
        >
        <mock-edm-prop-item
          v-for="edmProp in edmClassProps"
          :key="edmProp.id"
          :initData="edmProp"
          @selectProp="selectProp"
          @openPropFormula="openPropFormula"
          @openPropLimit="openPropLimit"
          @openRelTrigger="openRelTrigger"
        >
        </mock-edm-prop-item>
        </el-scrollbar>
      </div>
    </el-card>
  </div>
</template>
<script>
import MockEdmPropItem from './MockEdmPropItem'
export default {
  name: 'mockEdmPropsList',
  props: ['edmClassProps'],
  data () {
    return {
    }
  },
  methods: {
    selectProp (prop) {
      console.info('selectProp')
      this.$emit('selectProp', prop)
    },
    openPropFormula (prop) {
      this.$emit('openPropFormula', prop)
    },
    openPropLimit (prop) {
      this.$emit('openPropLimit', prop)
    },
    openRelTrigger (prop) {
      this.$emit('openRelTrigger', prop)
    }
  },
  computed: {
  },
  components: {
    MockEdmPropItem
  }
}
</script>
<style scoped>
</style>
