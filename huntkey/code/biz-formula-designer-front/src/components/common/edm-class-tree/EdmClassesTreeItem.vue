<template>
  <span class="edm-classes-tree-item">
    <span>
      <span >{{node.label}}</span>
    </span>
<!--     <span style="float: right; margin-right: 20px">
      <el-button size="mini" @click.stop="append(store, data)">Append</el-button>
      <el-button size="mini" @click.stop="remove(store, data)">Delete</el-button>
    </span> -->
  </span>
</template>
<script>
export default {
  name: 'edm-classes-tree-item',
  props: {
    tree: Object,
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
    append (store, data) {
      this.tree.append(store, data)
    },
    remove (store, data) {
      this.tree.remove(store, data)
    }
  }
}
</script>
