<template>
  <div class="warpper">
    <div class="attribute">
      <span>属性：</span>
      <span class="blod">{{$store.state.classTreeStore.currentClass.edmcName}}</span>.
      <span class="blod">{{dataIn.propertyInfo.edmpName}}</span>
    </div>
    <el-button-group class="tab">
      <el-button class="button" :type="currentTabIndex === 1 ? 'primary':'default'" @click="currentTabIndex = 1">关联</el-button>
      <el-button class="button" :type="currentTabIndex === 2 ? 'primary':'default'" @click="currentTabIndex = 2">联动</el-button>
      <el-button v-if="dataIn.propertyInfo.edmpValueType === 'convolution'" 
        class="button" :type="currentTabIndex === 3 ? 'primary':'default'" @click="currentTabIndex = 3">卷积</el-button>
      <el-button v-if="dataIn.propertyInfo.edmpValueType === 'measurement'" 
      class="button" :type="currentTabIndex === 4 ? 'primary':'default'" @click="currentTabIndex = 4">单位</el-button>
    </el-button-group>
    <div class="content">
      <property-link v-if="currentTabIndex === 1" :property-id="dataIn.propertyInfo.id" :mode="dataIn.mode"></property-link>
      <property-connect v-if="currentTabIndex === 2" :property-id="dataIn.propertyInfo.id" :mode="dataIn.mode"></property-connect>
      <property-convolute v-if="currentTabIndex === 3" :property-id="dataIn.propertyInfo.id" :mode="dataIn.mode"></property-convolute>
      <property-unit v-if="currentTabIndex === 4" :property="dataIn.propertyInfo" :mode="dataIn.mode"></property-unit>
    </div>
  </div>
</template>
<script>
  import PropertyLink from './property-link/PropertyLink'
  import PropertyConnect from './property-connect/PropertyConnect'
  import PropertyConvolute from './property-convolute/PropertyConvolute'
  import PropertyUnit from './property-unit/PropertyUnit'

  export default {
    props: ['dataIn'],
    components: {
      PropertyLink, PropertyConnect, PropertyConvolute, PropertyUnit
    },
    data () {
      return {
        currentTabIndex: 1
      }
    }
  }
</script>
<style scoped>
  .warpper{
    height: 450px;
  }
  .tab{
    margin-top: 20px;
  }
  .button{
    width: 80px;
  }
  .blod {
    font-weight: bold;
  }
  .content{
    margin-top: 20px;
    height: 365px;
  }
</style>
