<template>
  <div class="el-transfer">
    <transfer-panel
      v-bind="$props"
      :data="sourceData"
      :title="titles[0] || t('el.transfer.titles.0')"
      :default-checked="leftDefaultChecked"
      :placeholder="filterPlaceholder || t('el.transfer.filterPlaceholder')"
      @checked-change="onSourceCheckedChange">
      <slot name="left-footer"></slot>
    </transfer-panel>
    <div class="el-transfer__buttons">
      <el-button
        type="primary"
        size="small"
        @click.native="addToLeft"
        :disabled="rightChecked.length === 0">
        <i class="el-icon-arrow-left"></i>
        <span v-if="buttonTexts[0] !== undefined">{{ buttonTexts[0] }}</span>
      </el-button>
      <el-button
        type="primary"
        size="small"
        @click.native="addToRight"
        :disabled="leftChecked.length === 0">
        <span v-if="buttonTexts[1] !== undefined">{{ buttonTexts[1] }}</span>
        <i class="el-icon-arrow-right"></i>
      </el-button>
    </div>
    <transfer-panel
      v-bind="$props"
      :filterable="false"
      :data="targetData"
      :title="titles[1] || t('el.transfer.titles.1')"
      :default-checked="rightDefaultChecked"
      :placeholder="filterPlaceholder || t('el.transfer.filterPlaceholder')"
      @checked-change="onTargetCheckedChange">
      <slot name="right-footer"></slot>
    </transfer-panel>
  </div>
</template>

<script>
  import Emitter from './mixins/emitter'
  import Locale from './mixins/locale'
  import transferPanel from './transfer-panel'

  export default {
    name: 'ElTransfer',

    mixins: [Emitter, Locale],

    components: {
      transferPanel
    },

    props: {
      data: {
        type: Array,
        default () {
          return []
        }
      },
      titles: {
        type: Array,
        default () {
          return []
        }
      },
      buttonTexts: {
        type: Array,
        default () {
          return []
        }
      },
      filterPlaceholder: {
        type: String,
        default: ''
      },
      filterMethod: Function,
      leftDefaultChecked: {
        type: Array,
        default () {
          return []
        }
      },
      rightDefaultChecked: {
        type: Array,
        default () {
          return []
        }
      },
      renderContent: Function,
      value: {
        type: Array,
        default () {
          return []
        }
      },
      footerFormat: {
        type: Object,
        default () {
          return {}
        }
      },
      filterable: Boolean,
      props: {
        type: Object,
        default () {
          return {
            label: 'label',
            key: 'key',
            disabled: 'disabled'
          }
        }
      }
    },

    data () {
      return {
        leftChecked: [],
        rightChecked: []
      }
    },

    computed: {
      sourceData () {
        return this.data.filter(item => this.value.indexOf(item[this.props.key]) === -1)
      },

      targetData () {
        let currentData = []
        for (let itemValue of this.value) {
          for (let itemData of this.data) {
            // if (itemData.key === itemValue) {
            //   currentData.push(itemData)
            // }
            if (itemData[this.props.key] === itemValue) {
              currentData.push(itemData)
            }
            // if (itemData.id === itemValue) {
            //   currentData.push(itemData)
            // }
          }
        }
        return currentData
        // return this.data.filter(item => this.value.indexOf(item[this.props.key]) > -1)
      }
    },

    watch: {
      value (val) {
        this.dispatch('ElFormItem', 'el.form.change', val)
      }
    },

    methods: {
      onSourceCheckedChange (val) {
        this.leftChecked = val
      },

      onTargetCheckedChange (val) {
        this.rightChecked = val
      },

      addToLeft () {
        let currentValue = this.value.slice()
        this.rightChecked.forEach(item => {
          const index = currentValue.indexOf(item)
          if (index > -1) {
            currentValue.splice(index, 1)
          }
        })
        this.$emit('input', currentValue)
        this.$emit('change', currentValue, 'left', this.rightChecked)
      },

      addToRight () {
        let currentValue = this.value.slice()
        this.leftChecked.forEach(item => {
          if (this.value.indexOf(item) === -1) {
            currentValue = currentValue.concat(item)
          }
        })
        // var dataId = []
        // for (let i = 0; i < this.data.length; i++) {
        //   dataId.push(this.data[i].id)
        // }
        // this.leftChecked.sort((a, b) => dataId.indexOf(a) > dataId.indexOf(b))
        this.$emit('input', currentValue)
        this.$emit('change', currentValue, 'right', this.leftChecked)
      }
    }
  }
</script>
