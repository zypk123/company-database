<template>
  <div class="wapper">
    <el-input 
      :placeholder="placeholder ? placeholder : '点击进行属性限值编辑'" 
      icon="plus" 
      v-model="currentView"
      readonly
      :disabled="isDisabled"
      @focus="openWindow"
      @click="openWindow"></el-input>
  </div>
</template>
<script>
  import { windowTypes } from '@/types'

  export default {
    props: ['value', 'text', 'placeholder', 'disabled', 'propertyId'],
    name: 'prop-limit-editor',
    created () {
      this.isDisabled = this.disabled !== false && this.disabled !== null && this.disabled !== undefined
      // 回填默认值
      if (this.value) {
        this.currentValue = this.value
        this.currentView = this.text
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
    watch: {
      disabled () {
        this.isDisabled = this.disabled !== false && this.disabled !== null && this.disabled !== undefined
      }
    },
    methods: {
      openWindow () {
        if (this.isDisabled) return
        // 增加监听
        this.$store.commit(windowTypes.ADD_EVENT, {
          type: 'PropLimit',
          callback: (data) => {
            if (data.operType === 'save') {
              this.currentValue = data.eventData.prprId
              if (data.eventData.prprConditionName) {
                this.currentView = data.eventData.prprConditionName
              } else {
                this.currentView = data.eventData.prprConditionDesc
              }
              this.$emit('input', this.currentValue)
            } else if (data.operType === 'delete') {
              this.currentView = ''
              this.currentValue = ''
              this.$emit('input', this.currentValue)
              this.$emit('removePropLimit')
            }
          }
        })
        window.open(`${this.CONFIG.baseUrl.formula}#/prop-limit-config/${this.propertyId}`)
      }
    }
  }
</script>
<style scoped>
  .wapper{
    position: absolute;
  }
</style>
