<template>
  <div class="form">
    <span>
      属性&nbsp;&nbsp;
      <el-select style="width:120px;" v-model="formatItem.edmfEdmpId" @change="setProName" filterable>
        <el-option v-for="item in viewProperties" :key="item.id" :value="item.id" :label="item.edmpName"></el-option>
      </el-select>
    </span>
    <span>
      连接符&nbsp;&nbsp;<el-input class="input-width" :maxlength="4" v-model="formatItem.edmfConnector"></el-input>
    </span>
    <div class="button-area">
      <span class="title default-color">
        呈现格式
      </span>
      <div class="button">
        <el-button @click="add">追加</el-button>
        <el-button @click="pop">撤回</el-button>
      </div>
    </div>
    <div class="content">
      <span v-text="currentView"></span>
    </div>
  </div>
</template>
<script>
  import { basicInfoService } from '@/api'

  export default {
    props: ['dataIn'],
    created () {
      // 查询呈现属性
      basicInfoService.queryViewProperty(this.dataIn.classId).then(data => {
        this.viewProperties = data
        this.formatItem.edmfEdmcId = this.dataIn.classId
        if (this.dataIn.classFormats) {
          this.classFormats = this.dataIn.classFormats
        }
      })
    },
    data () {
      return {
        // 当前编辑的格式
        formatItem: {
          // 连接符
          edmfConnector: '',
          // 类ID
          edmfEdmcId: '',
          // 属性Id
          edmfEdmpId: '',
          // 属性名称
          edmpName: ''
        },
        // 呈现属性列表
        viewProperties: [],
        // 当前设置的格式
        classFormats: [],
        // 当前显示格式
        currentView: ''
      }
    },
    watch: {
      classFormats: {
        handler (value) {
          if (value) {
            this.currentView = ''
            value.forEach(item => {
              if (item.edmpName) {
                this.currentView += item.edmpName
              }
              if (item.edmfConnector) {
                this.currentView += item.edmfConnector
              }
            })
          }
        },
        deep: true
      }
    },
    methods: {
      add () {
        if (this.formatItem.edmfConnector || this.formatItem.edmpName) {
          this.classFormats.push(this.formatItem)
          this.formatItem = {
            // 连接符
            edmfConnector: '',
            // 类ID
            edmfEdmcId: this.dataIn.classId,
            // 属性Id
            edmfEdmpId: '',
            // 属性名称
            edmpName: ''
          }
        } else {
          this.$message.warning('属性和连接符至少选择一个')
        }
      },
      pop () {
        this.classFormats.pop()
      },
      // 设置属性名称
      setProName (id) {
        this.viewProperties.forEach(item => {
          if (item.id === id) {
            this.formatItem.edmpName = item.edmpName
          }
        })
      },
      // 特征值
      getResult () {
        if (this.classFormats.length > 0) {
          return this.classFormats
        }
        this.$message.warning('请先配置呈现格式')
      }
    }
  }
</script>
<style scoped>
.input-width{
  width: 100px;
}
.form span{
  margin-right: 20px;
}
.content{
  border: solid 1px #ddd;
  width: 100%;
  height: 50px;
  line-height: 50px;
  font-size: 16px;
}
.content span {
  margin: 0 5%;
}
.button-area{
  margin-top: 10px;
  background-color: #F1F3FF;
  height: 37px;
  line-height: 37px;
  border: solid 1px #DFE6EC;
  border-bottom: none;
}
.title{
  float:left;
  font-size: 14px;
  font-weight: bold;
  margin-left: 20px;
}
.button{
  float: right;
  margin-right: 10px;
}
</style>
