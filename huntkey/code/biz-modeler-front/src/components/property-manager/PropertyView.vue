<template>
  <property-list :data-in="listData" ref="list"></property-list>
</template>
<script>
  import PropertyList from './PropertyList'
  import { propertyTypes } from '@/types'

  export default {
    components: {
      PropertyList
    },
    watch: {
      $route () {
        // 子组件列表刷新
        this.$refs.list.getProperties()
        this.$refs.list.getParentsPropertier()
        this.listData.info = {
          parent: this.$store.state.classTreeStore.currentClass.edmcName
        }
      }
    },
    data () {
      return {
        // 默认传递数据
        listData: {
          mode: this.$route.params.mode,
          type: propertyTypes.PROPERTY_TYPE_CLASS,
          info: {
            parent: this.$store.state.classTreeStore.currentClass.edmcName
          }
        }
      }
    }
  }
</script>
<style scoped>
.button-area{
  text-align: right;
  height: 38px;
}
</style>
