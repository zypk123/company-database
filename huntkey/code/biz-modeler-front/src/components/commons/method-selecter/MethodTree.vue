<template>
  <div class="warpper">
    <div class="tree-area">
      <el-input class="input" placeholder="输入筛选" v-model.trim="filterValue" icon="search"/>
      <div class="tree">
        <!-- 单选树 -->
        <el-tree v-if="!dataIn.multiple"
          ref="tree"
          :data="treeData"
          :props="{
            'label': 'label',
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
        <!-- 多选树 -->
        <el-tree v-else
          ref="tree"
          :data="treeData"
          :props="{
            'label': 'label',
            'children': 'children'
          }"
          node-key="id"
          highlight-current
          show-checkbox
          @current-change="currentChange"
          :default-expanded-keys="defaultExpandedKeys"
          :expand-on-click-node="false"
          :default-checked-keys="currentNodeId"
          :filter-node-method="filter">
        </el-tree>
      </div>
    </div>
    <div class="info-area">
      <method-view :data-in="viewData" v-if="des"></method-view>
    </div>
  </div>
</template>
<script>
  import { functionService } from '@/api'
  import MethodView from './MethodView'
  import _ from 'lodash'

  export default {
    props: ['dataIn'],
    components: {
      MethodView
    },
    created () {
      // 加载方法树
      functionService.getTypesTree().then(data => {
        this.treeData = data
        // 将选中的父节点添加到默认展开
        if (this.dataIn.multiple) {
          // 设置当前值
          if (this.dataIn.currentValue) {
            this.currentNodeId = this.dataIn.currentValue.split(',')
          }
          if (this.currentNodeId && this.currentNodeId.length > 0) {
            this.currentNodeId.forEach(item => {
              const parentNode = this.getParentNodeById(item)
              if (parentNode && this.defaultExpandedKeys.indexOf(parentNode < 0)) {
                this.defaultExpandedKeys.push(parentNode.id)
              }
            })
          }
        } else {
          // 设置当前值
          this.currentNodeId = this.dataIn.currentValue
          const parentNode = this.getParentNodeById(this.currentNodeId)
          if (parentNode && this.defaultExpandedKeys.indexOf(parentNode < 0)) {
            this.defaultExpandedKeys.push(parentNode.id)
          }
          if (this.dataIn.currentValue) {
            // 查找详情
            functionService.queryMethodIn(this.dataIn.currentValue).then(data => {
              this.viewData = data
            })
          }
        }
      })
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
        defaultExpandedKeys: [],
        // 传递给展示组件
        viewData: {},
        des: false
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
        if (value && data.label.indexOf(value) < 0) {
          return false
        }
        return true
      },
      // 当前选中节点改变
      currentChange (data, node) {
        this.des = false
        if (!node.isLeaf && !node.expanded) {
          node.expanded = true
        }
        if (!data.children) {
          // 查询方法数据
          functionService.queryMethod(data.id).then(data => {
           // console.log(JSON.stringify(data.edmMethodInsertList_show))
            _.assign(this.viewData, data)
            this.des = true
           // console.log(JSON.stringify(this.viewData))
          })
          if (!this.dataIn.multiple) {
            this.currentNodeId = data.id
            this.currentName = data.label
          }
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
          const checkedNodes = this.$refs.tree.getCheckedNodes()
          for (let index = 0; index < checkedNodes.length; index++) {
            // 移除非根节点
            const item = checkedNodes[index]
            if (item.children) {
              checkedNodes.splice(index--, 1)
            }
          }
          if (checkedNodes.length === 0) {
            this.$message.warning('至少选择一个方法')
          } else {
            const data = []
            checkedNodes.forEach(item => {
              data.push({
                value: item.id,
                name: item.label
              })
            })
            return data
          }
        } else {
          // 单选
          if (this.currentNodeId) {
            return {
              value: this.currentNodeId,
              name: this.currentName
            }
          } else {
            this.$message.warning('请选择一个方法')
          }
        }
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
    left: 270px;
    width: 400px;
    height: 420px;
  }
  .tree{
    margin-top: 10px;
    height: 440px;
    overflow: auto;
  }
</style>
