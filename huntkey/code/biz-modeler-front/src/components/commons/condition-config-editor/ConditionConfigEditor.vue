<template>
  <div class="wapper">
    <el-input 
      :placeholder="placeholder ? placeholder : '点击编辑对象定位公式'" 
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
    props: ['value', 'text', 'placeholder', 'disabled', 'propertyId', 'connectPropertyId'],
    name: 'condition-config-editor',
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
      openWindow () {
        if (this.isDisabled) return
        // 增加监听
        this.$store.commit(windowTypes.ADD_EVENT, {
          type: 'RelCondition',
          callback: (data) => {
            if (data.operType === 'save') {
              this.currentValue = data.eventData.prplId
              if (data.eventData.prplConditionName) {
                this.currentView = data.eventData.prplConditionName
              } else {
                this.currentView = data.eventData.prplConditionDesc
              }
              this.$emit('input', this.currentValue)
            } else if (data.operType === 'delete') {
              this.currentValue = ''
              this.currentView = ''
              this.$emit('input', this.currentValue)
              this.$emit('removeCondition')
            }
          }
        })
        window.open(`${this.CONFIG.baseUrl.formula}#/rel-condition-config/${this.propertyId}/${this.connectPropertyId}`)
      }
    }
  }
</script>
<style scoped>
  .wapper{
    position: absolute;
  }
</style>
