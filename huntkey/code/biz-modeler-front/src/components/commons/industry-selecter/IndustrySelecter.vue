<template>
  <div class="wapper">
    <el-input 
      :placeholder="placeholder ? placeholder : '请选择行业'" 
      icon="plus" 
      v-model="currentView"
      readonly
      @focus="openChooseDialog"
      @click="openChooseDialog"></el-input>
  </div>
</template>
<script>
  import IndustryTree from './IndustryTree'
  import { dictTypes } from '@/types'
  import { mapGetters } from 'vuex'

  export default {
    /**
     * 输入参数
     * value 值信息
     * placeholder 提示文字信息
     * multiple 是否多选
     * filter 对选择项进行筛选的方法
     */
    props: ['value', 'placeholder', 'multiple', 'filter'],
    name: 'industry-selecter',
    created () {
      this.isMultiple = this.multiple !== false && this.multiple !== null && this.multiple !== undefined
      // 获取数据字典
      this.$store.dispatch(dictTypes.GET_DICT_TREE_BY_CODE, 'edm_industry_code').then(() => {
        // 设置默认值
        if (this.value) {
          this.currentValue = this.value
        }
      })
    },
    computed: {
      ...mapGetters({
        'treeData': dictTypes.GET_ALL_TREES
      })
    },
    data () {
      return {
        // 当前值
        currentValue: null,
        // 选中值
        currentView: null,
        // 树组件的vm对象
        treeVm: null,
        // 是否多选
        isMultiple: false
      }
    },
    watch: {
      // 监听value的值，更新显示名称
      currentValue (value) {
        if (this.isMultiple) {
          this.currentView = this.getNameByIds(value).join(',')
        } else {
          this.currentView = this.getNameByIds(value)[0]
        }
      }
    },
    methods: {
      // 弹框
      openChooseDialog () {
        this.$openDialog({
          name: 'industry-tree',
          component: IndustryTree,
          options: {
            title: '选择行业',
            customClass: 'dialog-width-s'
          },
          props: {
            // 是否多选
            multiple: this.isMultiple,
            // 当前选中的值
            currentValue: this.currentValue,
            // 过滤器
            filter: this.filter
          },
          buttons: [{
            text: '确认',
            icon: 'circle-check',
            type: 'primary',
            handler: () => {
              const result = this.treeVm.getResult()
              if (result) {
                this.currentValue = result
                this.$emit('input', this.currentValue)
                this.$closeDialog('industry-tree')
              }
            }
          }, {
            text: '取消',
            icon: 'circle-close ',
            handler: () => {
              // 选择
              this.$closeDialog('industry-tree')
            }
          }, {
            text: '清空',
            icon: 'close',
            handler: () => {
              this.currentValue = ''
              this.currentView = ''
              this.$emit('input', this.currentValue)
              this.$closeDialog('industry-tree')
            }
          }]
        }).then(treeVm => {
          this.treeVm = treeVm
        })
      },
      // 根据ID获得当前节点数据
      getNameByIds (ids) {
        const names = []
        // 返回节点数据
        getNodeInChildren(ids, this.treeData.edm_industry_code, names)
        return names
        // 递归查找
        function getNodeInChildren (ids, list, names) {
          for (let index in list) {
            const item = list[index]
            if (ids.indexOf(item.value) >= 0) {
              names.push(item.label)
            }
            if (item.children && item.children.length > 0) {
              getNodeInChildren(ids, item.children, names)
            }
          }
        }
      }
    }
  }
</script>
<style scoped>
</style>
