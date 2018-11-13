<template>
  <div id="m_choice">
    <div id="bod2"></div>
    <el-row :gutter="20">
      <el-col id="col-1"  :xs="24" :sm="24" :md="8" :lg="8" class="grid-content" style="padding: 0">
        <div class="top-conent">
          <p style="margin-top:0;padding:13px">方法选择</p>
        </div>
        <div style="overflow-y:auto;height:400px;" class="m_tree">
          <el-input placeholder="输入关键字进行过滤" v-model="filterText" icon="search"></el-input>
          <el-tree  show-checkbox class="filter-tree" node-key="id"  :data="methodClass"
                    @check-change="getNodes" style="border:0"
                    :default-checked-keys="checkId"
                    :default-expanded-keys="checkId"
                    highlight-current :props="defaultProps"  :filter-node-method="filterNode" ref="tree" >
          </el-tree>
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :md="16" :lg="16" class="grid-content2" >
        <div class="medetail">
          <el-form  label-width="160px" :model="methodData">
            <el-form-item label="方法分类">
              <div class="metext" v-text="methodData.edmmTypeName"></div>
            </el-form-item>
            <el-form-item label="方法名称">
              <div class="metext" v-text="methodData.edmmName"></div>
            </el-form-item>
            <el-form-item label="方法描述">
              <div class="metext" v-text="methodData.edmmDesc"></div>
            </el-form-item>
            <el-form-item label="程序类型">
              <div class="metext" v-text="methodData.edmProgramTypeName"></div>
            </el-form-item>
            <el-form-item label="方法输入参数">
              <template scope="scope">
                <el-tooltip  effect="light"  :content="edmInsertArgDesc" placement="top-start">
                  <el-input class="metext" type="text" v-text="edmEdmdInsertArgName"></el-input>
                </el-tooltip>
              </template>
            </el-form-item>
            <el-form-item label="方法返回值">
              <template scope="scope">
                <el-tooltip  effect="light" :content="edmReturnDesc" placement="top-start">
                  <el-input class="metext" type="text" v-text="edmEdmdReturnName"></el-input>
                </el-tooltip>
              </template>
            </el-form-item>
            <el-form-item label="是否可覆盖">
              <div class="metext" v-if="methodData.isCover === 1 ">
                {{ '是' }}
              </div>
              <div class="metext" v-else-if="methodData.isCover === 0 ">
                {{ '否' }}
              </div>
            </el-form-item>
            <el-form-item label="是否平台方法">
              <div class="metext" v-if="methodData.isPlatformProgram === 1 ">
                {{ '是' }}
              </div>
              <div class="metext" v-else-if="methodData.isPlatformProgram === 0 ">
                {{ '否' }}
              </div>
            </el-form-item>
            <el-form-item label="触发条件">
              <div class="metext" v-text="methodData.edmmTriggerCond"></div>
            </el-form-item>
            <el-form-item label="存储位置">
              <div class="metext" v-text="methodData.edmmProgramStorage"></div>
            </el-form-item>
            <el-form-item label="版本号">
              <template scope="scope">
                <el-tooltip  effect="light" :content="methodData.edmmVer" placement="top-start">
                  <el-input type="text" v-text="methodData.edmmVer"></el-input>
                </el-tooltip>
              </template>
            </el-form-item>
            <el-form-item label="研发部门">
              <div class="metext" v-text="methodData.edmmDevelopDept"></div>
            </el-form-item>
            <el-form-item label="程序员">
              <div class="metext" v-text="methodData.edmmProgrammer"></div>
            </el-form-item>
          </el-form>
        </div>
        <!--<div class="aBtn"><el-button :disabled="confirmStatu"  @click="confirm" type="info"><i class="el-icon-circle-check">&nbsp;确认</i></el-button></div>-->
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import { functionService } from '@/api/services/method'
  export default {
    name: 'methodChoice',
    props: ['dataIn'],
    watch: {
      filterText (val) {
        this.$refs.tree.filter(val)
      }
    },
    data () {
      return {
        edmInsertArgDesc: '',
        edmEdmdInsertArgName: '',
        edmReturnDesc: '',
        edmEdmdReturnName: '',
        type: '0',
        id: '1',
        cName: '',
        mName: '',
        ids: [],
        checkId: [],
        confirmStatu: false,
        methodData: {
          edmmTypeName: '',
          edmmVer: ''
        },
        filterText: '',
        methodClass: [],
        defaultProps: {
          children: 'children',
          label: 'label',
          id: 'id'
        }
      }
    },
    mounted: function () {
      console.log(this.dataIn.methodId)
      this.checkId[0] = this.dataIn.methodId
      this.queryTree()
    },
    methods: {
      queryTree: function () {
        // 0/属性编辑和修改  1/方法新增和查询
        this.type = this.dataIn.type
        this.id = this.dataIn.id
        var code = this.dataIn.code
        if (code === 0) {
          // 加载全部方法树
          functionService.getTypesTree()
            .then(data => {
             // console.log('...' + JSON.stringify(data))
              this.methodClass = data
            })
        } else {
          functionService.queryMethodIn(code)
            .then(data => {
             // console.log('...' + JSON.stringify(data))
              this.methodData = data[0]
              this.getmethodTypeName()
            })
        }
      },
      filterNode (value, data) {
        if (!value) return true
        return data.label.indexOf(value) !== -1
      },
      // 共三个参数，依次为：传递给 data 属性的数组中该节点所对应的对象、节点本身是否被选中、节点的子树中是否有被选中的节点
      getNodes (object, node, a) {
        this.edmInsertArgDesc = ''
        this.edmEdmdInsertArgName = ''
        this.edmReturnDesc = ''
        this.edmEdmdReturnName = ''
        this.edmmTypeName = ''
        this.edmmVer = ''
        this.edmmType = ''
        let qID = []
        let length = 0
        // 仅返回被选中的叶子节点的 keys
        qID = this.$refs.tree.getCheckedKeys(true)
        length = qID.length
        // alert(length)
        // console.log(qID[length - 1])
        // console.log(qID)
        // console.log(JSON.stringify(object))
        // console.log(object.id + '...')
        // console.log(node)
        // console.log(a)
        // 存在选中的叶子节点
        if (qID[length - 1]) {
          // 直接点击选中一个叶子节点，显示他的信息
          if (node && !a) {
           // console.log(JSON.stringify(object))
            functionService.queryMethodIn(object.id)
              .then(data => {
                console.log('111' + JSON.stringify(data))
                if (data) {
                  this.methodData = data
                  this.getmethodTypeName()
                }
              })
          } else {
            // 点击父节点时，会选中多个叶子节点，显示最后一个叶子节点的信息
            functionService.queryMethodIn(qID[length - 1])
              .then(data => {
                if (data) {
                  console.log('222' + JSON.stringify(data))
                  this.methodData = data[0]
                  this.getmethodTypeName()
                }
              })
          }
        } else {
          this.methodData = {}
        }
      },
      // 获取分类名字和是否自动方法0、1转换否、是
      getmethodTypeName () {
        // console.log(_this.methodData.edmmType)
        // console.log(_this.methodData.isCover)
        // console.log(_this.methodData.isPlatformProgram)
       /* if (this.methodData.isCover === 1) {
          this.methodData.isCoverName = '是'
        } else {
          this.methodData.isCoverName = '否'
        }
        if (this.methodData.isPlatformProgram === 1) {
          this.methodData.isPlatformProgramName = '是'
        } else {
          this.methodData.isPlatformProgramName = '否'
        } */
        if (this.methodData.edmmType) {
          functionService.getTypes(this.methodData.edmmType)
            .then(data => {
             // console.log('~~~' + JSON.stringify(data))
              this.methodData.edmmTypeName = data[0].edmtName
            })
        } else {
          this.methodData.edmmTypeName = ''
        }
        // 输入参数处理
        // console.log(_this.methodData.id)
        functionService.queryInput(this.methodData.id)
          .then(data => {
           // console.log(':::' + JSON.stringify(data))
            if (data) {
              let l = data.length
              let i = 0
              this.edmEdmdInsertArgName = ''
              this.edmInsertArgDesc = ''
              for (i = 0; i < l; i++) {
                if (i === 0) {
                  this.edmEdmdInsertArgName = data[i].edmaDataTypeName + ' ' + data[i].edmaName
                  this.edmInsertArgDesc = data[i].edmaName + '表示' + data[i].edmaDesc
                } else {
                  this.edmEdmdInsertArgName = this.edmEdmdInsertArgName + ', ' + data[i].edmaDataTypeName + ' ' + data[i].edmaName
                  this.edmInsertArgDesc = this.edmInsertArgDesc + ', ' + data[i].edmaName + '表示' + data[i].edmaDesc
                }
              }
            }
          })
        // 返回参数处理
        functionService.queryReturn(this.methodData.id)
          .then(data => {
            // console.log(r1.data.data.length)
            if (data) {
              let l = data.length
              let i = 0
              this.edmEdmdReturnName = ''
              this.edmReturnDesc = ''
              for (i = 0; i < l; i++) {
                if (i === 0) {
                  this.edmEdmdReturnName = data[i].edmaDataTypeName + ' ' + data[i].edmaName
                  this.edmReturnDesc = data[i].edmaName + '表示' + data[i].edmaDesc
                } else {
                  this.edmEdmdReturnName = this.edmEdmdReturnName + ', ' + data[i].edmaDataTypeName + ' ' + data[i].edmaName
                  this.edmReturnDesc = this.edmReturnDesc + ', ' + data[i].edmaName + '表示' + data[i].edmaDesc
                }
              }
            }
          })
      },
      getResult () {
        let result = {}
        result.id = this.methodData.id
        result.name = this.methodData.edmmName
        console.log(JSON.stringify(result))
        return result
      },
      saveMethods () {
        var idArr = []
        idArr = this.$refs.tree.getCheckedKeys(true)
        console.log(JSON.stringify(idArr) + '...')
        if (idArr.length !== 0) {
          let id = this.dataIn.id
          for (let methodId of idArr) {
            return functionService.classAddMethod(id, methodId)
          }
        } else {
          this.$message({
            type: 'info',
            message: '请选择需要增加的方法!'
          })
        }
      },
      confirm () {
        let type = this.dataIn.type
        // alert('******确认时type:' + type)
        // 1为方法的批量增加
        if (type === 1) {
          var idArr = []
          idArr = this.$refs.tree.getCheckedKeys(true)
          if (idArr.length !== 0) {
            let id = this.dataIn.id
            functionService.classAddMethod(id, idArr)
              .then(() => {
                this.$message({
                  type: 'success',
                  message: '增加成功!'
                })
              })
          } else {
            this.$message({
              type: 'info',
              message: '请选择需要增加的方法!'
            })
          }
          // 0为查看响应方法或者编辑相应方法
        } if (type === 0) {
          // 被选中的叶子节点有多个时
          if (this.$refs.tree.getCheckedKeys(true).length > 1) {
            this.$alert('修改响应方法时只能选择一个！', '提示', {
              confirmButtonText: '确定'
            })
            return false
          } else {
            // console.log(vm.methodData.id)
            // console.log(vm.methodData.edmmName)
           // this.$store.dispatch('methodsChoice', this.methodData.id)
            // this.$store.dispatch('methodsChoiceN', this.methodData.edmmName)
            this.$emit('upup', '')
          }
        } else {
          this.$emit('upup', '')
        }
      }
    }
  }
</script>

<style scoped>
  #m_choice .top-conent {
    background: #f5f6ff;
    height: 48px;
    text-align: center;
  }
  /*#m_choice .el-form-item .el-form-item__label {
    color: #9496a0;
    padding: 11px 30px 11px 0;
  }
  #m_choice .el-form-item .el-tree-node__label {
    color: #6b6b74;
  }*/
  /*.metext {
    color: #6b6b74;
  }*/
  .grid-content {
    border-radius: 4px;
    /* border: 1px solid #D3DCE6;*/
  }
  .grid-content2 {
    border-radius: 4px;
  }
  .medetail {
    overflow-y:auto;
    height:400px;
    /*  border: 1px solid #D3DCE6;*/
  }
  .aBtn {
    position: absolute;
    bottom: 10px;
    right: 60px;
  }
  .medetail .el-form-item__label {
    font-size: 14px;
    color: #9496a0;
    padding: 11px 30px 11px 0;
  }
  .medetail .el-form-item__content {
    font-size: 14px;
    color: #6b6b74;
  }
  /*输入框高度*/
  #m_choice .el-input__inner{
    height: 48px;
  }
  /*#m_choice .el-tree-node__content{
    height: 48px;
    line-height: 48px;
  }*/
  /*中间阴影条样式*/
  #bod2{
    display: inline-block;
    margin: 2px 0 2px 10px;
    height: 99%;
    width: 10px;
    position: absolute;
    top: 0px;
    left: 33.33%;
    box-shadow: 10px 0 5px rgba(205, 207, 236, 0.16);
  }
</style>
