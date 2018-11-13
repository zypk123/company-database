<template>
  <div class="sys-vars-list">
    <!-- 公式设计器 选择系统变量-->
    <el-card class="box-card has-header-a">
      <div slot="header" class="clearfix">
        <span class="a-card-header-title">请选择系统变量</span>
      </div>
            <!--   :class="{ 'is-empty':  initData.length === 0 }"
        v-show="initData.length > 0 " -->
      <el-scrollbar
        tag="div"
        class="list-items-scroll"
        wrap-class="list-items-scroll-wrapper"
        view-class="el-select-dropdown__list"
        >
        <div v-for="sysVar in store.sysVars" :key="sysVar.id" class="list-item"
          @click="listItemClickEvent($event,sysVar)">
          <i class="el-icon-rx-dot-2x"></i>
          {{sysVar.vrntVarName}}
          <span class="item-return-type">{{sysVar.vrntVarType}}</span>
        </div>
      </el-scrollbar>
    </el-card>
  </div>
</template>
<script>
export default {
  name: 'sysVarsList',
  props: ['store'],
  created () {
    // console.log(this.$parent)
  },
  data () {
    return {
    }
  },
  methods: {
    listItemClickEvent (e, sysVar) {
      this.$emit('selectVar', sysVar)
    }
  }
}
</script>
<style scoped>
</style>
