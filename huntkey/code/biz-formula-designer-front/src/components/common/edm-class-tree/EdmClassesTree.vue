<template>
  <div class="edm-classes-tree" :class="{'empty': showFilterEmptyText}">
    <el-input v-if="!useOuterFilterText"
      placeholder="输入关键字进行过滤"
      v-model="filterText">
    </el-input>
     <el-scrollbar
        tag="div"
        class="edm-classes-tree-scroll"
        wrap-class="edm-classes-tree-scroll-wrapper edm-classes-tree-scroll-wrapper"
        view-class="el-select-dropdown__list"
        >
      <el-tree class="edm-classes-tree-data"
        :data="edmClassesTreeData"
        :show-checkbox="false"
        :default-expand-all="true"
        :empty-text="emptyText"
        node-key="id"
        :current-node-key="currentNodeKey"
        ref="edmClassesTree"
        highlight-current
        :expand-on-click-node="false"
        :filter-node-method="filterNode"
        :props="defaultProps"
        :render-content="renderContent"
        @node-click="nodeClick"
        >
      </el-tree>
     </el-scrollbar>
    <div v-if="showFilterEmptyText" class="el-tree__filter-empty-block">
      <span class="el-tree__filter-empty-text">{{ filterEmptyText }}</span>
    </div>
  </div>
</template>

<script>
  import { EDM_DATA_TYPES } from '@/types'
  import { mapActions } from 'vuex'
  import EdmClassesTreeItem from './EdmClassesTreeItem'
  export default {
    name: 'edm-classes-tree',
    props: ['useOuterFilterText', 'outerFilterText'],
    data () {
      return {
        emptyText: '无数据',
        filterText: '',
        showFilterEmptyText: false,
        filterEmptyText: '过滤后未找到匹配的类',
        edmClassesTreeData: [],
        currentNodeKey: '',
        defaultProps: {
          children: 'children',
          label: 'edmcName'
        }
      }
    },
    methods: {
      ...mapActions({
        fetchEdmcTree: EDM_DATA_TYPES.ACTION_FETCH_CLASSES_TREE
      }),
      renderContent (h, { node, data, store }) {
        return h(EdmClassesTreeItem, {props: { tree: this, node, data, store }})
      },
      filterNode (value, data) {
        if (!value) return true
        return data.edmcName.toLowerCase().indexOf(value.toLowerCase()) !== -1
      },
      countAfterFilter () {
        this.showFilterEmptyText = false
        let root = this.$refs.edmClassesTree.$el
        var nodeIterator = document.createNodeIterator(
          root,
          NodeFilter.SHOW_ELEMENT,
          {
            acceptNode: (node) => {
              if (this.WebUtils.hasClass(node, 'el-tree-node') &&
                !this.WebUtils.hasClass(node, 'is-hidden')) {
                return true
              }
              return false
            }
          }
        )
        var cnt = 0
        while (nodeIterator.nextNode()) {
          cnt++
        }
        if (cnt === 0) {
          this.showFilterEmptyText = true
        }
      },
      nodeClick (nodeData, node, nodeVueEl) {
        this.$emit('selectClass', nodeData)
      }
    },
    created () {
      this.emptyText = '正在查询类数据'
      this.fetchEdmcTree().then(
      (resData) => {
        this.edmClassesTreeData = resData
        this.emptyText = '无数据'
      })
    },
    watch: {
      outerFilterText (val) {
        this.$refs.edmClassesTree.filter(val)
        this.$nextTick(function () {
          this.countAfterFilter()
        })
      },
      filterText (val) {
        this.$refs.edmClassesTree.filter(val)
        this.$nextTick(function () {
          this.countAfterFilter()
        })
      }
    }
  }
</script>
<style scoped>
.edm-classes-tree {
  position: absolute;
  border:1px solid #745ff8;
  background-color: #fff;
}

.empty .edm-classes-tree-scroll {
  height: 20px;
}
.edm-classes-tree-scroll {
  height: 300px;
}
.edm-classes-tree-data  {
  width: 100%;
  height: 100%;
  overflow: auto;
}
.el-tree__filter-empty-block {
    position: relative;
    min-height: 60px;
    text-align: center;
    width: 100%;
    height: 100%;
}
.el-tree__filter-empty-text {
    position: absolute;
    left: 50%;
    top: 50%;
    -ms-transform: translate(-50%,-50%);
    transform: translate(-50%,-50%);
    color: #5e7382;
}
</style>
