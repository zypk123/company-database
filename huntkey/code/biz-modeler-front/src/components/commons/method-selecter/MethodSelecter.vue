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
  import MethodTree from './MethodTree'

  export default {
    /**
     * 输入参数
     * value 值信息
     * name 显示值
     * placeholder 提示文字信息
     * multiple 是否多选
     * disabled 是否禁用
     */
    props: ['value', 'name', 'placeholder', 'multiple', 'disabled'],
    name: 'method-selecter',
    created () {
      this.isMultiple = this.multiple !== false && this.multiple !== null && this.multiple !== undefined
      this.isDisabled = this.disabled !== false && this.disabled !== null && this.disabled !== undefined
      // 回填默认值
      if (this.value) {
        this.currentValue = this.value
      }
      if (this.name) {
        this.currentView = this.name
      }
    },
    data () {
      return {
        // 当前值
        currentValue: null,
        // 选中值
        currentView: null,
        // 树组件的vm对象
        treeVm: null,
        // 是否禁用
        isDisabled: false
      }
    },
    watch: {
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
          name: 'tree',
          component: MethodTree,
          options: {
            title: '方法选择',
            customClass: 'dialog-width-l'
          },
          props: {
            // 是否多选
            multiple: this.isMultiple,
            // 当前值
            currentValue: this.currentValue
          },
          buttons: [{
            text: '确认',
            icon: 'circle-check',
            type: 'primary',
            handler: () => {
              const result = this.treeVm.getResult()
              if (result) {
                if (this.isMultiple) {
                  const values = []
                  const names = []
                  result.forEach(item => {
                    values.push(item.value)
                    names.push(item.name)
                  })
                  this.currentValue = values.join(',')
                  this.currentView = names.join(',')
                  this.$emit('input', this.currentValue)
                  this.$closeDialog('tree')
                } else {
                  this.currentValue = result.value
                  this.currentView = result.name
                  this.$emit('input', this.currentValue)
                  this.$closeDialog('tree')
                }
              }
            }
          }, {
            text: '取消',
            icon: 'circle-close ',
            handler: () => {
              // 选择
              this.$closeDialog('tree')
            }
          }, {
            text: '清空',
            icon: 'close',
            handler: () => {
              this.currentValue = ''
              this.currentView = ''
              this.$emit('input', this.currentValue)
              this.$closeDialog('tree')
            }
          }]
        }).then(treeVm => {
          this.treeVm = treeVm
        })
      }
    }
  }
</script>
<style scoped>
  .wapper{
    position: absolute;
  }
</style>
