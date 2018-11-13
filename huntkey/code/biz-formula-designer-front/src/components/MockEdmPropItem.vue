<template>
    <div class="edm-prop-item">
        <!-- -->
        <div :key="edmProp.id" class="list-item"
             :style="{'padding-left': (edmProp.level-1)*10 +'px'}"
             @click="listItemClickEvent(edmProp)"
        >
            <i v-if="edmProp.edmpValueType==='assemble'&&!isOpen" class="el-icon-caret-right"
               @click.stop="isOpen=!isOpen"></i>
            <i v-if="edmProp.edmpValueType==='assemble'&&isOpen" class="el-icon-caret-bottom"
               @click.stop="isOpen=!isOpen"></i>
            {{edmProp.edmpName}}
            <!--  {{edmProp.edmpCode}} -->
            {{edmProp.edmpValueType}}
            <span style="float: right; margin-right: 20px">
        <el-button size="mini" @click.stop="openPropFormula(edmProp)">属性公式</el-button>
        <el-button size="mini" @click.stop="openPropLimit(edmProp)">属性限值</el-button>
        <el-button size="mini" @click.stop="openRelTrigger(edmProp)">关联触发条件设置</el-button>
      </span>
        </div>
        <div v-if="edmProp.edmpValueType==='assemble'" v-show="this.isOpen">
            <mock-edm-prop-item
                    v-for="sedmProp in edmProp.children"
                    :key="sedmProp.id"
                    @selectProp="selectProp"
                    @openPropFormula="openPropFormula"
                    @openPropLimit="openPropLimit"
                    @openRelTrigger="openRelTrigger"
                    :initData="sedmProp">
            </mock-edm-prop-item>
        </div>
    </div>
</template>
<script>
  export default {
    name: 'mock-edm-prop-item',
    props: ['initData'],
    data () {
      return {
        edmProp: this.initData,
        isOpen: false
      }
    },
    methods: {
      listItemClickEvent (prop) {
        console.info('listItemClickEvent')
        this.$emit('selectProp', prop)
      },
      selectProp (prop) {
        console.info('selectProp')
        this.$emit('selectProp', prop)
      },
      openPropFormula (prop) {
        this.$emit('openPropFormula', prop)
      },
      openPropLimit (prop) {
        this.$emit('openPropLimit', prop)
      },
      openRelTrigger (prop) {
        this.$emit('openRelTrigger', prop)
      }
    },
    computed: {}
  }
</script>
<style scoped>

</style>
