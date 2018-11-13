<template>
  <div class="edm-prop-item">
      <!-- -->
    <div :key="edmProp.id" class="list-item"
      :style="{'padding-left': (edmProp.level-1)*10 +'px'}"
       @click="listItemClickEvent()"
      >
      <i v-if="edmProp.edmpValueType==='assemble'&&!isOpen" class="el-icon-caret-right"
        @click.stop="isOpen=!isOpen"></i>
      <i v-if="edmProp.edmpValueType==='assemble'&&isOpen" class="el-icon-caret-bottom"
        @click.stop="isOpen=!isOpen"></i>
      <i v-if="edmProp.edmpValueType!=='assemble'" class="el-icon-rx-dot-2x"
        @click.stop="isOpen=!isOpen"></i>
      {{edmProp.edmpName}}
     <!--  {{edmProp.edmpCode}} -->
      {{edmProp.edmpValueType}}
    </div>
    <div v-if="edmProp.edmpValueType==='assemble'" v-show="this.isOpen">
      <edm-prop-item
        v-for="sedmProp in edmProp.children"
        :key="sedmProp.id"
        @selectProp="selectProp"
        :initData="sedmProp">
      </edm-prop-item>
    </div>
  </div>
</template>
<script>
import appMixin from '@/mixin/app-mixin'
export default {
  name: 'edm-prop-item',
  mixins: [appMixin],
  props: ['initData'],
  data () {
    return {
      edmProp: this.initData,
      isOpen: false
    }
  },
  created () {
    this.edmProp.edmpValueType = this.parseEdmpDataValueType(this.edmProp)
  },
  methods: {
    listItemClickEvent () {
      this.$emit('selectProp', this.edmProp)
    },
    selectProp (prop) {
      this.$emit('selectProp', prop)
    }
  },
  computed: {
  }
}
</script>
<style scoped>

</style>
