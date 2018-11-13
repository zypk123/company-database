<template>
  <span class="edm-classes-tree-item">
    <span>
      <span >{{node.label}}</span>
    </span>
  </span>
</template>
<script>
// 不懂这个vue干嘛的
export default {
  name: 'edm-classes-tree-item',
  props: {
    parent: Object,
    node: Object,
    data: Object,
    store: Object
  },
  created () {
  },
  methods: {
    selectClass (store, data) {
      // console.log(store, data)
    },
    openPropFormula (store, data) {
      this.parent.openPropFormula(store, data)
    },
    openPropLimit (store, data) {
      this.parent.openPropLimit(store, data)
    }
  }
}
</script>
