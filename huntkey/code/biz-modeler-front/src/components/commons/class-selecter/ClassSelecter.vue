<template>
  <div class="wapper">
    <el-input 
      :placeholder="placeholder ? placeholder : '选择或输入'" 
      icon="plus" 
      v-model="currentView"
      readonly
      :disabled="isDisabled"
      @focus="openChooseDialog"
      @click="openChooseDialog"></el-input>
  </div>
</template>
<script>
  import ClassTree from './ClassTree'
  import { classTypes } from '@/types'
  import { mapGetters } from 'vuex'

  export default {
    /**
     * 输入参数
     * value 值信息
     * placeholder 提示文字信息
     * multiple 是否多选
     * disabled 是否禁用
     */
    props: ['value', 'placeholder', 'multiple', 'filter', 'disabled'],
    name: 'class-selecter',
    created () {
      this.isMultiple = this.multiple !== false && this.multiple !== null && this.multiple !== undefined
      this.isDisabled = this.disabled !== false && this.disabled !== null && this.disabled !== undefined
      // 回填默认值
      if (this.value) {
        this.currentValue = this.value
      }
    },
    computed: {
      ...mapGetters({
        'classTree': classTypes.GET_CURRENT_CLASS_TREE_DATA
      })
    },
    data () {
      return {
        // 是否多选
        isMultiple: false,
        // 是否禁用
        isDisabled: false,
        // 当前值
        currentValue: null,
        // 选中值
        currentView: null,
        // 树组件的vm对象
        treeVm: null
      }
    },
    watch: {
      value () {
        this.currentValue = this.value
      },
      // 监听value的值，更新显示名称
      currentValue (value) {
        if (value) {
          if (this.isMultiple) {
            this.currentView = this.getNameByIds(value).join(',')
          } else {
            this.currentView = this.getNameByIds(value)[0]
          }
        } else {
          this.currentView = ''
        }
      },
      disabled: {
        handler () {
          this.isDisabled = this.disabled !== false && this.disabled !== null && this.disabled !== undefined
        },
        deep: true
      }
    },
    methods: {
      // 弹框
      openChooseDialog () {
        if (this.isDisabled) return
        this.$openDialog({
          name: 'class-tree',
          component: ClassTree,
          options: {
            title: '选择类',
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
          events: {
            confirmNode: this.confirmNode
          },
          buttons: [{
            text: '确认',
            icon: 'circle-check',
            type: 'primary',
            handler: () => {
              const result = this.treeVm.getResult()
              if (result) {
                if (this.currentValue !== result) {
                  this.$emit('change', result, this.currentValue)
                }
                this.currentValue = result
                this.$emit('input', this.currentValue)
                this.$closeDialog('class-tree')
              }
            }
          }, {
            text: '取消',
            icon: 'circle-close ',
            handler: () => {
              // 选择
              this.$closeDialog('class-tree')
            }
          }, {
            text: '清空',
            icon: 'close',
            handler: () => {
              if (this.currentValue !== '') {
                this.$emit('change', '', this.currentValue)
              }
              this.currentValue = ''
              this.$emit('input', this.currentValue)
              this.$closeDialog('class-tree')
            }
          }]
        }).then(treeVm => {
          this.treeVm = treeVm
        })
      },
      // 根据ID获得当前节点数据
      getNameByIds (ids) {
        if (!(ids instanceof Array)) {
          ids = [ids]
        }
        const names = []
        // 返回节点数据
        getNodeInChildren(ids, this.classTree, names)
        return names
        // 递归查找
        function getNodeInChildren (ids, list, names) {
          for (let index in list) {
            const item = list[index]
            if (ids.indexOf(item.id) >= 0) {
              names.push(item.viewName)
            }
            if (item.children && item.children.length > 0) {
              getNodeInChildren(ids, item.children, names)
            }
          }
        }
      },
      // 双击树节点，等于选中并确认
      confirmNode (result) {
        if (result) {
          if (this.currentValue !== result) {
            this.$emit('change', result, this.currentValue)
          }
          this.currentValue = result
          this.$emit('input', this.currentValue)
          this.$closeDialog('class-tree')
        }
      }
    }
  }
</script>
<style scoped>
.wapper{
  position: absolute;
}
</style>
