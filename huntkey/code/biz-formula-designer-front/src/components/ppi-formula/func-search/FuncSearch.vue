<template>
  <div class="common-search">
    <el-card class="box-card has-header-a">
      <div slot="header" class="clearfix">
        查找
      </div>
      <div class="content">
        <el-input v-model="searchText" placeholder="请输入函数"
                  icon="search" @change="searchTextChange">
        </el-input>
        <div class="formula-suggestion__wrapper" v-if="showListWrapper">
          <el-scrollbar
            tag="ul"
            class="formula-suggestion__list list-items-scroll"
            wrap-class="list-items-scroll-wrapper"
            view-class="el-select-dropdown__list"
          >
            <li v-if="showEmptyText">
              {{emptyText}}
            </li>
            <li v-for="(item,index) in store.searchResult" class="list-item"
                :class="{'formula-item-func': item.itemType==='func','formula-item-var':item.itemType==='var'}"
                :title="item.itemLabel + '-' + item.returnType"
                @click="listItemClickEvent(item)"
            >
              {{item.itemLabel}}<span class="item-return-type">{{item.returnType}}</span>
            </li>
          </el-scrollbar>
        </div>
      </div>
    </el-card>
  </div>
</template>
<script>
  import designerMixin from '@/mixin/designer-mixin'
  export default {
    name: 'funcSearch',
    mixins: [designerMixin],
    props: ['store'],
    data () {
      return {
        searchText: ''
      }
    },
    methods: {
      listItemClickEvent (item) {
        let delSuggestRange = false
        this.$emit('selectSuggest', {item, delSuggestRange})
      },
      searchTextChange () {
        // 根据suggestionQueryStr查询要提示用的变量, 方法
        this.$emit('searchTextChange', this.searchText)
      }
    },
    computed: {
      showEmptyText () {
        if (!this.searchText) {
          return false
        }
        if (this.searchResult && this.searchResult.length > 0) {
          return false
        }
        return true
      },
      showListWrapper () {
        if (this.store.searchResult && this.store.searchResult.length > 0) {
          return true
        }
        return false
      },
      emptyText () {
        if (this.searchText && this.store.searchResult.length === 0) {
          return '没有匹配的函数或变量'
        }
        return ''
      }
    }
  }
</script>
<style scoped>
  .common-search li {
    list-style: none;
    line-height: 36px;
    padding: 0 10px;
    margin: 0;
    cursor: pointer;
    font-size: 14px;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
  }

  .common-search .el-card>.el-card__body>.content{
    height: 100%;
  }

  .common-search .formula-suggestion__wrapper {
    height:calc(100% - 32px);
  }

</style>
