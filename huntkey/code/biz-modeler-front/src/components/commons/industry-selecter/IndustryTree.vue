<template>
  <div class="warpper">
    <el-input class="input" placeholder="输入筛选" v-model.trim="filterValue" icon="search"/>
    <!-- 单选树 -->
    <div class="tree-area">
      <el-tree v-if="!dataIn.multiple"
        ref="tree"
        :data="treeData.edm_industry_code ? treeData.edm_industry_code : []"
        :props="{
          'label': 'label',
          'children': 'children'
        }"
        node-key="value"
        highlight-current
        default-expand-all
        :expand-on-click-node="false"
        :current-node-key="currentNodeId"
        :filter-node-method="filter"
        @current-change="currentChange">
      </el-tree>
      <!-- 多选树 -->
      <el-tree v-else
        ref="tree"
        :data="treeData.edm_industry_code ? treeData.edm_industry_code : []"
        :props="{
          'label': 'label',
          'children': 'children'
        }"
        node-key="value"
        show-checkbox
        default-expand-all
        :expand-on-click-node="false"
        :default-checked-keys="currentNodeId"
        :filter-node-method="filter">
      </el-tree>
    </div>
  </div>
</template>
<script>
  import { dictTypes } from '@/types'
  import { mapGetters } from 'vuex'

  export default {
    computed: {
      ...mapGetters({
        'treeData': dictTypes.GET_ALL_TREES
      })
    },
    props: ['dataIn'],
    created () {
      this.currentNodeId = this.dataIn.currentValue
      // 获取数据字典
      this.$store.dispatch(dictTypes.GET_DICT_TREE_BY_CODE, 'edm_industry_code')
    },
    data () {
      return {
        // 当前节点ID
        currentNodeId: null,
        // 查找字符串值
        filterValue: ''
      }
    },
    watch: {
      filterValue (value) {
        // 查找延迟，提高性能
        clearTimeout(this.timeout)
        this.timeout = setTimeout(() => {
          this.$refs.tree.filter(value)
        }, 300)
      }
    },
    methods: {
      // 树过滤器
      filter (value, data) {
        if (value && data.label.indexOf(value) < 0) {
          return false
        }
        if (this.dataIn.filter) {
          return this.dataIn.filter(value, data)
        }
        return true
      },
      // 当前选中节点改变
      currentChange (node) {
        if (!node.isLeaf && !node.expanded) {
          node.expanded = true
        }
        this.currentNodeId = node.value
      },
      // 获得值
      getResult () {
        if (this.dataIn.multiple) {
          // 多选
          const checkedNodes = this.$refs.tree.getCheckedNodes()
          if (checkedNodes.length === 0) {
            this.$message.warning('至少选择一个行业')
          } else {
            const data = []
            checkedNodes.forEach(item => {
              data.push(item.value)
            })
            return data
          }
        } else {
          // 单选
          if (this.currentNodeId) {
            return this.currentNodeId
          } else {
            this.$message.warning('请选择一个行业')
          }
        }
      }
    }
  }
</script>
<style scoped>
.input{
  margin-bottom: 10px;
}
.tree-area{
  height: 300px;
  overflow: auto;
}
</style>
