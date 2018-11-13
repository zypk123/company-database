<template>
  <div class="ppi-vars-list">
    <el-card class="box-card has-header-a">
      <div slot="header" class="clearfix">
        <span class="a-card-header-title">请选择PPI变量</span>
      </div>
      <el-scrollbar
        tag="div"
        class="list-items-scroll"
        wrap-class="list-items-scroll-wrapper"
        view-class="el-select-dropdown__list"
      >
        <div v-for="item in postData" :key="item.vrntVarName" class="list-item"
             @click="listItemClickEvent($event, item)">
          <i class="el-icon-rx-dot-2x"></i>
          {{item.vrntVarName}}
          <span class="item-return-type">{{item.vrntVarType}}</span>
        </div>
      </el-scrollbar>
    </el-card>
  </div>
</template>
<script>
  export default {
    name: 'ppiVarsList',
    props: ['postData'],
    data () {
      return {
      }
    },
    created () {
      // console.log('post', this.postData)
    },
    mounted () {
    },
    methods: {
      listItemClickEvent (e, ppiVar) {
        this.$emit('selectVar', ppiVar)
      }
    }
  }
</script>
<style scoped>
</style>
