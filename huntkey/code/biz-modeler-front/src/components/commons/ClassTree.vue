<template>
  <div class="warpper">
    <!-- 单选树 -->
    <el-tree v-if="!dataIn.multiple"
      ref="classTree"
      :data="treeData"
      :props="{
        'label': 'viewName',
        'children': 'children'
      }"
      node-key="id"
      highlight-current
      default-expand-all
      :expand-on-click-node="false"
      :current-node-key="dataIn.classValue"
      :filter-node-method="dataIn.filter"
      @current-change="currentChange">
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
      default-expand-all
      :expand-on-click-node="false"
      :default-checked-keys="dataIn.classValue"
      :filter-node-method="dataIn.filter">
    </el-tree>
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
    data () {
      return {
        currentNode: null
      }
    },
    methods: {
      // 当前选中节点改变
      currentChange (node) {
        this.currentNode = node
      },
      // 获得值
      getResult () {
        if (this.dataIn.multiple) {
          // 多选
          const checkedNodes = this.$refs.classTree.getCheckedNodes()
          const data = {
            value: [],
            text: ''
          }
          checkedNodes.forEach(item => {
            data.value.push(item.id)
            data.text += item.viewName + ','
          })
        } else {
          // 单选
          if (this.currentNode) {
            return {
              value: this.currentNode.id,
              text: this.currentNode.viewName
            }
          } else {
            this.$message.warning('请选择一个类')
          }
        }
      },
      // 根据ID获得当前节点数据
      getNodeById (id) {
        // 返回节点数据
        return getNodeInChildren(id, this.treeData)
        // 递归查找
        function getNodeInChildren (id, children) {
          for (let index in children) {
            const item = children[index]
            if (item.id === id) {
              return item
            } else {
              var node = getNodeInChildren(item.children)
              if (node) {
                return node
              } else {
                continue
              }
            }
          }
        }
      }
    }
  }
</script>
<style scoped>
.warpper{
  height: 300px;
}
</style>
