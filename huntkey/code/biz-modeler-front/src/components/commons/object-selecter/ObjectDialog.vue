<template>
  <div class="warpper">
    <div class="tree-area" v-if="!dataIn.classId">
      <el-input class="input" placeholder="输入筛选" v-model.trim="filterValue" icon="search"/>
      <div class="tree">
        <el-tree
          ref="tree"
          :data="dataIn.treeData"
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
          @current-change="currentChange">
        </el-tree>
      </div>
    </div>
    <div class="info-area" :style="{
        left: dataIn.classId ? 20 : '270px',
        width: dataIn.classId ? '460px' : '420px'
      }">
      <object-view ref="view" :class-id="currentNodeId"></object-view>
    </div>
  </div>
</template>
<script>
  import ObjectView from './ObjectView'

  export default {
    props: ['dataIn'],
    components: {
      ObjectView
    },
    created () {
      if (!this.dataIn.classId) {
        // 默认展开父节点
        this.dataIn.treeData.forEach(item => {
          this.defaultExpandedKeys.push(item.id)
        })
        this.currentNodeId = this.dataIn.currentValue
        // 将选中的父节点添加到默认展开
        const parentNode = this.getParentNodeById(this.currentNodeId)
        if (parentNode && this.defaultExpandedKeys.indexOf(parentNode < 0)) {
          this.defaultExpandedKeys.push(parentNode.id)
        }
      } else {
        this.currentNodeId = this.dataIn.classId
      }
    },
    data () {
      return {
        treeData: [],
        // 当前节点id
        currentNodeId: null,
        // 当前节点名称
        currentName: null,
        // 查找字符串值
        filterValue: '',
        // 默认展开的节点
        defaultExpandedKeys: []
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
      filter (value, data, node) {
        if (value && data.viewName.indexOf(value) < 0) {
          return false
        }
        return true
      },
      // 当前选中节点改变
      currentChange (data, node) {
        this.currentNodeId = data.id
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
        return this.$refs.view.getResult()
      }
    }
  }
</script>
<style scoped>
  .warpper{
    height: 400px;
  }
  .tree-area{
    position: absolute;
    margin-top: -20px;
    margin-left: -20px;
    width: 250px;
    height: 484px;
    border-right: solid 1px #F6F6FB;
    box-shadow: 10px 0px 15px -5px #F6F6FB;
    padding: 5px;
    overflow: hidden;
  }
  .info-area{
    position: absolute;
    width: 420px;
    height: 420px;
  }
  .tree{
    margin-top: 10px;
    height: 440px;
    overflow: auto;
  }
</style>
