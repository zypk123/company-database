<template>
  <div class="warpper" v-if="dataIn">
    <el-row class="row">
      <el-col :span="6" class="label">方法名称</el-col>
      <el-col :span="18" class="value">{{method.edmmName}}</el-col>
    </el-row>
    <el-row class="row">
      <el-col :span="6" class="label">方法分类</el-col>
      <el-col :span="18" class="value">{{method.edmmTypeName}}</el-col>
    </el-row>
    <el-row>
      <el-col :span="6" class="label">方法描述</el-col>
      <el-col :span="18" class="value">{{method.edmmDesc}}</el-col>
    </el-row>
    <el-row>
      <el-col :span="6" class="label">是否平台方法</el-col>
      <el-col :span="18" class="value">{{method.isPlatformProgram === 1 ? '是':'否'}}</el-col>
    </el-row>
    <el-row>
      <el-col :span="6" class="label">是否可覆盖</el-col>
      <el-col :span="18" class="value">{{method.isCover === 1 ? '是':'否'}}</el-col>
    </el-row>
    <el-row>
      <el-col :span="6" class="label">触发条件</el-col>
      <el-col :span="18" class="value">{{method.edmmTriggerCond}}</el-col>
    </el-row>
    <el-row>
      <el-col :span="6" class="label">版本号</el-col>
      <el-col :span="18" class="value">{{method.edmmVer}}</el-col>
    </el-row>
    <el-row>
      <el-col :span="6" class="label">程序类型</el-col>
      <el-col :span="18" class="value">{{method.edmProgramTypeName}}</el-col>
    </el-row>
    <el-row>
      <el-col :span="6" class="label">方法输入参数</el-col>
      <el-col :span="18" class="value" >
        <el-tooltip class="item" effect="light" :content="item.edmaDesc" placement="right" v-for="item in insertInfo" :key="item.id" v-if="insertInfo">
          <el-button> {{item.edmaDataTypeName}}&nbsp;&nbsp;{{item.edmaName}}</el-button>
        </el-tooltip>
       </el-col>
    </el-row>
    <el-row>
      <el-col :span="6" class="label">方法返回值</el-col>
      <el-col :span="18" class="value" v-if="returnInfo.edmaName">
        <el-tooltip class="item" effect="light" :content="returnInfo.edmaDesc" placement="right" >
          <el-button> {{returnInfo.edmaDataTypeName}}&nbsp;&nbsp;{{returnInfo.edmaName}}</el-button>
        </el-tooltip>
      </el-col>
    </el-row>
  </div>
</template>
<script>
  export default {
    name: 'method-view',
    props: ['dataIn'],
    created () {
      console.log(JSON.stringify(this.dataIn))
      if (this.dataIn.edmMethod_show) {
        this.method = this.dataIn.edmMethod_show
      }
      if (this.dataIn.edmMethodInsertList_show) {
        this.insertInfo = this.dataIn.edmMethodInsertList_show
      }
      if (this.dataIn.edmMethodreturn_show) {
        this.returnInfo = this.dataIn.edmMethodreturn_show
      }
    },
    data () {
      return {
        method: {},
        insertInfo: [],
        returnInfo: {}
      }
    },
    watch: {
      dataIn: {
        handler () {
          this.method = this.dataIn.edmMethod_show
          this.insertInfo = this.dataIn.edmMethodInsertList_show
          this.returnInfo = this.dataIn.edmMethodreturn_show
        },
        deep: true
      }
    }
  }
</script>
<style scoped>
  .label {
    text-align: right;
    height: 35px;
    line-height: 35px
  }
  .value {
    padding-left: 10px;
    height: 35px;
    line-height: 35px
  }
</style>
