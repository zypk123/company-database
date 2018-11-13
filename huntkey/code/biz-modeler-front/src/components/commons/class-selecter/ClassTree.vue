<template>
  <div class="warpper">
    <el-input class="input" placeholder="输入筛选" v-model.trim="filterValue" icon="search"/>
    <!-- 单选树 -->
    <div class="tree-area">
      <el-tree v-if="!dataIn.multiple"
        ref="classTree"
        :data="treeData"
        :props="{
          'label': 'viewName',
          'children': 'children'
        }"
        node-key="id"
        highlight-current
        :default-expanded-keys="defaultExpandedKeys"
        :expand-on-click-node="false"
        :current-node-key="currentNodeId"
        :filter-node-method="filter"
        @current-change="currentChange"
        @node-click="nodeClick">
      </el-tree>
      <!-- 多选树 -->
      <el-tree v-else
        ref="classTree"
        :data="treeData"
        :props="{
          'label': 'viewName',
          'children': 'children'
        }"
        node-key="id"
        show-checkbox
        :default-expanded-keys="defaultExpandedKeys"
        :expand-on-click-node="false"
        :default-checked-keys="currentNodeId"
        :filter-node-method="filter">
      </el-tree>
    </div>
  </div>
</template>
<script>
  import { classTypes } from '@/types'
  import { mapGetters } from 'vuex'

  export default {
    computed: {
      ...mapGetters({
        'treeData': classTypes.GET_CURRENT_CLASS_TREE_DATA
      })
    },
    props: ['dataIn'],
    created () {
      // 默认展开父节点
      this.treeData.forEach(item => {
        this.defaultExpandedKeys.push(item.id)
      })
      this.currentNodeId = this.dataIn.currentValue
      // 将选中的父节点添加到默认展开
      const parentNode = this.getParentNodeById(this.currentNodeId)
      if (parentNode && this.defaultExpandedKeys.indexOf(parentNode < 0)) {
        this.defaultExpandedKeys.push(parentNode.id)
      }
      this.$nextTick(() => {
        // 过滤节点
        if (this.dataIn.filter && typeof this.dataIn.filter === 'function') {
          const root = this.$refs.classTree.root
          filterNode(root, this.dataIn.filter)
        }
        function filterNode (node, filter) {
          if (!filter(node.data, node)) {
            // 删除节点
            node.store.remove(node.data)
          } else {
            if (node.childNodes && node.childNodes.length > 0) {
              node.childNodes.forEach(item => {
                filterNode(item, filter)
              })
            }
          }
        }
      })
    },
    data () {
      return {
        // 当前节点id
        currentNodeId: null,
        // 查找字符串值
        filterValue: '',
        // 默认展开的节点
        defaultExpandedKeys: [],
        // 点击次数
        clickNumber: 0
      }
    },
    watch: {
      filterValue (value) {
        // 查找延迟，提高性能
        clearTimeout(this.timeout)
        this.timeout = setTimeout(() => {
          this.$refs.classTree.filter(value)
        }, 300)
      }
    },
    methods: {
      filter (value, data, node) {
        if (value && data.viewName.indexOf(value) < 0) {
          return false
        }
        return true
      },
      // 当前选中节点改变
      currentChange (data, node) {
        if (!node.isLeaf && !node.expanded) {
          node.expanded = true
        }
        this.currentNodeId = data.id
      },
      // 单击选中节点改变，双击等于选中并确定
      nodeClick (data, node) {
        this.clickNumber++
        setTimeout(() => {
          if (this.clickNumber === 1) {
            this.clickNumber = 0
            this.currentChange(data, node)
          }
          if (this.clickNumber > 2) {
            this.clickNumber = 0
            return
          }
        }, 300)
        if (this.clickNumber === 2) {
          this.clickNumber = 0
          this.currentChange(data, node)
          this.$emit('confirmNode', this.currentNodeId)
        }
      },
      // 根据Id查找到父节点
      getParentNodeById (id) {
        for (let index in this.treeData) {
          const result = getParentNode(id, this.treeData[index])
          if (result) {
            return result
          }
        }

        // 递归查找
        function getParentNode (id, parent) {
          if (parent.children && parent.children.length > 0) {
            for (let index in parent.children) {
              const item = parent.children[index]
              if (item.id === id) {
                return parent
              } else {
                const result = getParentNode(id, item)
                if (result) {
                  return result
                }
              }
            }
          }
        }
      },
      // 获得值
      getResult () {
        if (this.dataIn.multiple) {
          // 多选
          const checkedNodes = this.$refs.classTree.getCheckedNodes()
          if (checkedNodes.length === 0) {
            this.$message.warning('至少选择一个类')
          } else {
            const data = []
            checkedNodes.forEach(item => {
              data.push(item.id)
            })
            return data
          }
        } else {
          // 单选
          if (this.currentNodeId) {
            return this.currentNodeId
          } else {
            this.$message.warning('请选择一个类')
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
