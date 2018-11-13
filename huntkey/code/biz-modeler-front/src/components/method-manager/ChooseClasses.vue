<template>
  <div class="choose-class">
    <div style="overflow-y:auto;height:400px;">
      <el-input placeholder="输入关键字进行过滤" v-model="filterText" icon="search"></el-input>
      <el-tree style="border:0" class="filter-tree" node-key="id"
        :data="classTreeData"
        show-checkbox
        @node-click="handleNodeClick"
        highlight-current
        :props="{
            'label': 'viewName',
            'children': 'children'}"
        ref="classTree"
        :default-expanded-keys="expandedKeys"
        :default-checked-keys="checkedKeys">
      </el-tree>
    </div>
  </div>
</template>
<script>
  import { classService } from '@/api'
  export default {
    name: 'chooseClass',
    props: ['dataIn'],
    data () {
      return {
        // 过滤条件
        filterText: '',
        // 类树
        classTreeData: null,
        // 默认展开节点
        expandedKeys: [],
        // 选择节点
        checkNodes: null,
        // 编辑时默认展开节点
        checkedKeys: []
      }
    },
    created () {
      this.getClassTree()
      if (this.dataIn.edmcId) {
        this.checkedKeys = this.dataIn.edmcId.split(',')
      }
    },
    watch: {
      filterText (val) {
        this.$refs.classTree.filter(val)
      }
    },
    methods: {
      // 获取类树
      getClassTree () {
        classService.getClassTreeById('9314121da04b4642a02e2c2f0e3920bd').then(data => {
          console.log(data)
          if (data && data.length > 0) {
            this.classTreeData = data
            this.classTreeData.forEach(item => {
              this.expandedKeys.push(item.id)
            })
          }
        })
      },
      handleNodeClick () {
        console.log('111')
      },
      getResult () {
        this.checkNodes = this.$refs.classTree.getCheckedNodes()
        // console.log(this.$refs.classTree.getCheckedNodes())
        let result = {}
        result.ids = []
        result.names = []
        for (let i = 0; i < this.checkNodes.length; i++) {
          result.ids.push(this.checkNodes[i].id)
          result.names.push(this.checkNodes[i].edmcName)
        }
        return result
      }
    }
  }
</script>
<style scoped></style>
