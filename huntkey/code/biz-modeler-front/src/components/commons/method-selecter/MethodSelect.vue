<template>
  <div class="wapper">
    <el-input
      :placeholder="placeholder ? placeholder : '选择或输入'"
      icon="plus"
      v-model="currentView"
      readonly
      @focus="openChooseDialog"
      @click="openChooseDialog"></el-input>
  </div>
</template>
<script>
  import methodClass from '@/components/method-manager/MethodChoice'

  export default {
    /**
     * 输入参数
     * value 值信息
     * placeholder 提示文字信息
     * multiple 是否多选
     * filter 对选择项进行筛选的方法
     */
    props: ['value', 'placeholder', 'classId', 'methodId'],
    name: 'method-selecter',
    created () {
      // 回填默认值
      if (this.value) {
        this.currentValue = this.value
      }
    },
    data () {
      return {
        // 当前值
        currentValue: null,
        // 选中值
        currentView: null,
        // 树组件的vm对象
        treeVm: null
      }
    },
/*    watch: {
      // 监听value的值，更新显示名称
      currentValue (value) {
        if (this.isMultiple) {
          this.currentView = this.getNameByIds(value).join(',')
        } else {
          this.currentView = this.getNameByIds(value)[0]
        }
      }
    }, */
    methods: {
      // 弹框
      openChooseDialog () {
        this.$openDialog({
          name: 'class-tree',
          component: methodClass,
          options: {
            title: '选择类',
            customClass: 'dialog-width-xl'
          },
          props: {
            code: 0,
            type: 1,
            id: this.classId,
            methodId: this.methodId,
            // 当前选中的值
            currentValue: this.currentValue
          },
          buttons: [{
            text: '确认',
            icon: 'circle-check',
            type: 'primary',
            handler: () => {
              const result = this.treeVm.getResult()
              if (result) {
                this.currentValue = result.id
                this.currentView = result.name
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
              this.currentValue = ''
              this.$emit('input', this.currentValue)
              this.$closeDialog('class-tree')
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
