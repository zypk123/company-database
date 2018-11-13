<template>
  <div class="wapper">
    <el-input 
      :placeholder="placeholder ? placeholder : '点击进行公式编辑'" 
      icon="plus" 
      v-model="currentView"
      readonly
      :disabled="isDisabled"
      @focus="openDialog"
      @click="openDialog"></el-input>
  </div>
</template>
<script>
  import { windowTypes } from '@/types'

  export default {
    props: ['value', 'text', 'placeholder', 'disabled', 'classId', 'propertyId'],
    name: 'formula-editor',
    created () {
      this.isDisabled = this.disabled !== false && this.disabled !== null && this.disabled !== undefined
      // 回填默认值
      if (this.value) {
        this.currentValue = this.value
        this.currentView = this.text
      }
    },
    watch: {
      disabled () {
        this.isDisabled = this.disabled !== false && this.disabled !== null && this.disabled !== undefined
      }
    },
    data () {
      return {
        // 是否禁用
        isDisabled: false,
        // 当前值
        currentValue: null,
        // 选中值
        currentView: null
      }
    },
    methods: {
      openDialog () {
        if (this.isDisabled) return
        // 增加监听
        this.$store.commit(windowTypes.ADD_EVENT, {
          type: 'PropFormula',
          callback: (data) => {
            if (data.operType === 'save') {
              this.currentValue = data.eventData.varId
              this.currentView = data.eventData.formula.formulaText
              this.$emit('input', this.currentValue)
            } else if (data.operType === 'delete') {
              this.currentValue = ''
              this.currentView = ''
              this.$emit('input', this.currentValue)
              this.$emit('removeFormula')
            }
          }
        })
        window.open(`${this.CONFIG.baseUrl.formula}#/formula-designer/prop-formula/${this.classId}/${this.propertyId}`)
      }
    }
  }
</script>
<style scoped>
  .wapper{
    position: absolute;
  }
</style>
