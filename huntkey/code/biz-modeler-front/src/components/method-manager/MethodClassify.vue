<template>
  <div id="classify">
    <div class="top-conent" >
      <p style="margin-top:0;padding:13px">方法分类</p>
    </div>
    <div style="overflow-y:auto;height:400px;">
      <el-input placeholder="输入关键字进行过滤" v-model="filterText" icon="search"></el-input>
      <el-tree style="border:0" class="filter-tree" node-key="id"  :data="treeData"
        @node-click="handleNodeClick"
        @current-change="dbNodeClick"
        highlight-current :props="defaultProps"  ref="tree2" :filter-node-method="filterNode">
      </el-tree>
    </div>
  </div>
</template>
<script>
  import { functionService } from '@/api/services/method'
  import methodClassficationMain from '@/components/method-manager/MethodClassficationMain'
  import _ from 'lodash'
  export default {
    name: 'methodClassify',
    data () {
      return {
        filterText: '',
        treeData: [],
        defaultProps: {
          children: 'children',
          label: 'edmtName'
        },
        clickNumber: 0,
        currrentNode: null
      }
    },
    watch: {
      filterText (val) {
        this.$refs.tree2.filter(val)
      }
    },
    // 加载时运行
    created: function () {
      functionService.getTypes().then((data) => {
        // 类树
        this.treeData = data
        // 移动树
        this.filterData(data)
        this.moveOptions = data
        // 父类树
       // this.filterData(data)
      })
    },
    methods: {
      filterData (data) {
        let arr = []
        if (data && data.length !== 0) {
          for (let obj of data) {
            if (obj.children && obj.children.length === 0) {
              obj.children = null
              arr.push(obj)
            } else {
              this.filterData(obj.children)
            }
          }
        }
        return arr
      },
      filterself (id, data) {
        if (data.length !== 0) {
          for (let index = 0; index < data.length; index++) {
            const obj = data[index]
            if (obj.id === id) {
              data.splice(index--, 1)
            } else {
              if (obj.children && obj.children !== null) {
                this.filterself(id, obj.children)
              }
            }
          }
        }
      },
      // 对树节点进行筛选时执行的方法
      filterNode (value, data) {
        if (!value) return true
        return data.label.indexOf(value) !== -1
      },
      // 分类树点击触发
      handleNodeClick (node) {
        this.currentNode = node
        functionService.getType(node.id).then(data => {
          this.methodData = data[0]
          this.parentOptions = _.cloneDeep(this.treeData)
          this.filterself(this.methodData.id, this.parentOptions)
          this.filterData(this.parentOptions)
         // console.log(JSON.stringify(this.methodData.id))
          // 找父类名字
          if (this.methodData.edmtParentId) {
            functionService.getType(this.methodData.edmtParentId)
              .then(data => {
                // console.log('ww')
                this.edmtParentName = data[0].edmtName
              })
          } else {
            this.edmtParentName = ''
          }
        })
      },
      // 双击选中方法分类
      dbNodeClick () {
        this.clickNumber++
        setTimeout(() => {
          if (this.clickNumber === 1) {
            this.clickNumber = 0
            return
          }
          if (this.clickNumber > 2) {
            this.clickNumber = 0
            return
          }
        }, 300)
        if (this.clickNumber === 2) {
          this.clickNumber = 0
          let result = {}
          result.id = this.methodData.id
          result.name = this.methodData.edmtName
          // console.log('result', result)
          this.$emit('confirmNode', result)
        }
      },
      getResult () {
        let result = {}
        result.id = this.methodData.id
        result.name = this.methodData.edmtName
        console.log(JSON.stringify(result))
        return result
      },
      methodClassficationMain () {
        // console.log(this.currentNode)
        this.$openDialog({
          name: 'method-classfication',
          component: methodClassficationMain,
          options: {
            title: '分类维护',
            customClass: 'dialog-width-xl'
          },
          props: {
            currentNode: this.currentNode
          }
        })
      }
    }
  }
</script>
<style>
  #classify .grid-content {
    border: 1px solid #D3DCE6;
    margin-top: 0px;
    border-radius: 4px;
  }
  #classify .grid-content2 {
    margin-top: 0px;
    border-radius: 4px;
  }
  #classify .top-conent {
    background: #f5f6ff;
    height: 45px;
    text-align: center;
  }
  #classify .marginR {
    margin-right: 30px
  }
  #classify .el-button{
    margin-right: 10px;
  }
.el-cascader-menu__item.is-active{
    background-color: #7459FC;
  }
  .el-cascader-menu__item.is-active:hover{
    background-color: #8F80FF;
  }
</style>

