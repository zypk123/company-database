<template>
  <el-input
    :placeholder="placeholder ? placeholder : '点击编辑触发条件'"
    icon="plus"
    v-model="currentView"
    readonly
    :disabled="isDisabled"
    @focus="openWindow"
    @click="openWindow"></el-input>
</template>
<script>
  import { windowTypes } from '@/types'

  export default {
    props: ['value', 'text', 'placeholder', 'disabled', 'propertyId'],
    name: 'trigger-condition-editor',
    created () {
      this.isDisabled = this.disabled !== false && this.disabled !== null && this.disabled !== undefined
      // 回填默认值
      if (this.value) {
        this.currentValue = this.value
        this.currentView = this.text
      }
    },
    watch: {
      disabled (val) {
        this.isDisabled = this.disabled !== false && this.disabled !== null && this.disabled !== undefined
      },
      value (val) {
        this.currentValue = val
      },
      text (val) {
        this.currentView = val
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
          type: 'RelTriggerCondition',
          callback: (data) => {
            this.currentValue = data.eventData.prplId
            if (data.eventData.prplConditionName) {
              this.currentView = data.eventData.prplConditionName
            } else {
              this.currentView = data.eventData.prplConditionDesc
            }
            this.$emit('input', this.currentValue)
          }
        })
        window.open(`${this.CONFIG.baseUrl.formula}#/rel-tirgger-condition-confit/${this.propertyId}`)
      }
    }
  }
</script>
<style scoped>
</style>
