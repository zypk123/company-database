<template>
  <div v-if="isLoaded" :class="{'cur-move': dragFlag}" @mouseup="moveEnd" @mousemove="moving">
    <div class="left-Area" ref="leftArea" 
    :style="{
      'width': `${leftAreaSize}px`
    }">
      <div v-show="!isLeftAreaCllapsed">
        <div class="title default-color">
          <span>{{currentVersion.edmdVer}}</span><span class="maring-left-10">{{currentVersion.edmdStatusName}}</span>
        </div>
        <industry-selecter class="input" v-model="filterForm.industries" multiple></industry-selecter>
        <el-input class="input" v-model="filterForm.className" placeholder="请输入类名" icon="search"/>
        <div class="icon-area icon-hover" v-if="$route.params.mode === 'edit'">
          <el-tooltip content="新增" placement="top">
            <i class="el-icon-plus" @click="createClass"></i>
          </el-tooltip>
          <el-tooltip content="编辑" placement="top">
          <i class="el-icon-edit" @click="editClass"></i>
          </el-tooltip>
          <el-tooltip content="复制" placement="top">
          <i class="el-icon-document" @click="copyClass"></i>
          </el-tooltip>
          <el-tooltip content="上移" placement="top">
          <i class="el-icon-arrow-up" @click="toUp"></i>
          </el-tooltip>
          <el-tooltip content="下移" placement="top">
          <i class="el-icon-arrow-down" @click="toDown"></i>
          </el-tooltip>
        </div>
        <div class="class-tree auto-height">
          <el-tree 
            ref="classTree"
            :data="classTreeData"
            :props="{
              'label': 'viewName',
              'children': 'children'
            }"
            :indent="10"
            node-key="id"
            highlight-current
            :default-expanded-keys="defaultExpandedKeys"
            :expand-on-click-node="false"
            :current-node-key="currentClass.id"
            :filter-node-method="classFilter"
            @current-change="changeCurrent">
          </el-tree>
        </div>
      </div>
      <!--收缩展开按钮-->
      <div class="left-operate button-hover" @click="leftAreaToggle">
        <i :class="isLeftAreaCllapsed ? 'el-icon-arrow-right' : 'el-icon-arrow-left'"></i>
      </div>
    </div>
    <div class="size-operater" @mousedown="moveStart" :style="{
      'left': `${leftAreaSize - 3}px`
    }">
    </div>
    <div class="main-area" ref="mainArea"
    :style="{
      'left': `${leftAreaSize}px`
    }">
      <div class="info-area">
        <div class="info">
          <label>类编码：</label>
          <span>{{currentClass.edmcCode}}</span>
        </div>
        <div class="split"></div>
        <div class="info">
          <label>类简称：</label>
          <span>{{currentClass.edmcShortName}}</span>
        </div>
        <div class="split"></div>
        <div class="info">
          <label>类名称：</label>
          <span>{{currentClass.edmcName}}</span>
        </div>
        <div class="split"></div>
        <div class="info">
          <label>类英文名称：</label>
          <span>{{currentClass.edmcNameEn ? currentClass.edmcNameEn: '无'}}</span>
        </div>
        <div class="split"></div>
        <div class="info">
          <label>是否实类：</label>
          <span>{{currentClass.isEntity === 1 ? '是':'否'}}</span>
        </div>
        <div class="split"></div>
        <div class="info">
          <label>适用行业：</label>
          <span>{{currentClass.industrieName}}</span>
        </div>
      </div>
      <div class="data-area">
        <el-button-group class="button-area">
          <el-button :type="currentManager === getManagerName('MANAGER_ATTR')? 'primary' : 'default'"
            @click="gotoManager(getManagerName('MANAGER_ATTR'))">属性</el-button>
          <el-button :type="currentManager === getManagerName('MANAGER_METHOD')? 'primary' : 'default'"
            @click="gotoManager(getManagerName('MANAGER_METHOD'))">方法</el-button>
          <el-button :type="currentManager === getManagerName('MANAGER_CONST')? 'primary' : 'default'"
            @click="gotoManager(getManagerName('MANAGER_CONST'))">常量</el-button>
          <el-button :type="currentManager === getManagerName('MANAGER_BASIC_INFO')? 'primary' : 'default'"
            @click="gotoManager(getManagerName('MANAGER_BASIC_INFO'))">基础信息</el-button>
            <el-button :type="currentManager === getManagerName('MANAGER_INDEX_MAINTENANCE')? 'primary' : 'default'"
            @click="gotoManager(getManagerName('MANAGER_INDEX_MAINTENANCE'))">索引</el-button>
        </el-button-group>
        <router-view></router-view>
      </div>
    </div>
  </div>
</template>
<script>
  import { classService, versionService } from '@/api'
  import { classTypes, dictTypes } from '@/types'
  import ClassEditor from './ClassEditor'
  import IndustrySelecter from '@/components/commons/industry-selecter/IndustrySelecter'
  import { mapGetters } from 'vuex'

  export default {
    computed: {
      ...mapGetters({
        trees: dictTypes.GET_ALL_TREES
      })
    },
    components: {IndustrySelecter},
    created () {
      // 获取行业类别
      this.$store.dispatch(dictTypes.GET_DICT_TREE_BY_CODE, 'edm_industry_code').then(() => {
        // 获取当前版本信息
        versionService.getVersionById(this.$route.params.id).then(data => {
          this.currentVersion = data
          this.getClassTree()
        })
      })
    },
    data () {
      return {
        // 加载完毕标识
        isLoaded: false,
        // 类树过滤字段
        filterForm: {
          industries: [],
          className: null
        },
        // 左边列表收缩状态
        isLeftAreaCllapsed: false,
        // 行业类型数据
        industries: null,
        // 类树
        classTreeData: null,
        // 当前选中的类
        currentClass: null,
        // 当前选中类的class对象
        currentNode: null,
        // 当前进入的功能
        currentManager: null,
        // 当前版本
        currentVersion: null,
        // 默认展开的节点
        defaultExpandedKeys: [],
        // 树选择区宽度
        leftAreaSize: 200,
        // 拖拽标识
        dragFlag: false,
        // 开始拖拽x
        startX: 0
      }
    },
    watch: {
      filterForm: {
        handler: function () {
          this.doFilter()
        },
        deep: true
      }
    },
    methods: {
      // 开始拖拽移动
      moveStart (event) {
        if (!this.dragFlag) {
          this.dragFlag = true
          this.startX = event.clientX
          this.initSize = this.leftAreaSize
        }
      },
      moving (event) {
        if (this.dragFlag) {
          this.leftAreaSize = this.initSize + event.clientX - this.startX
        }
      },
      moveEnd (event) {
        if (this.dragFlag) {
          this.dragFlag = false
        }
      },
      getClassTree () {
        // 获取类树
        classService.getClassTreeById(this.$route.params.id).then(data => {
          // 保存当前树信息
          this.$store.commit(classTypes.SET_CURRENT_CLASS_TREE_DATA, data)
          if (data && data.length > 0) {
            this.classTreeData = data
            this.classTreeData.forEach(item => {
              this.defaultExpandedKeys.push(item.id)
            })
            if (this.$route.name === 'classView') {
              // 没有路由到各个管理模块中
              // 默认选中第一条记录
              this.currentClass = this.classTreeData[0]
              const industrie = this.getIndustrie(this.currentClass.edmcIndustryCode)
              if (industrie) {
                this.currentClass.industrieName = industrie.label
              }
              // 跳转到属性管理
              this.currentManager = classTypes.MANAGER_ATTR
              // 进入对应的路由
              this.$router.replace({
                name: 'propertyView',
                params: {
                  classId: this.currentClass.id
                }
              })
            } else {
              // 从路由中获取ClassId
              const currentClassId = this.$route.params.classId
              this.currentClass = this.getNodeById(currentClassId)
              // 将选中的父节点添加到默认展开
              const parentNode = this.getParentNodeById(currentClassId)
              if (parentNode && this.defaultExpandedKeys.indexOf(parentNode < 0)) {
                this.defaultExpandedKeys.push(parentNode.id)
              }
              const industrie = this.getIndustrie(this.currentClass.edmcIndustryCode)
              if (industrie) {
                this.currentClass.industrieName = industrie.label
              }
              // 从路由中获取当前功能
              this.currentManager = this.getCurrentManagerFromRouter()
            }
            // 在vuex中设置当前的class
            this.$store.commit(classTypes.SET_CURRENT_CLASS, this.currentClass)
            this.isLoaded = true
            this.$nextTick(() => {
              // 树渲染完毕后，获取当前节点
              this.setCurrentNode()
            })
            // 执行默认的过滤
            if (this.filterForm.industries.length > 0 || this.filterForm.className) {
              this.doFilter()
            }
          } else {
            this.isLoaded = true
          }
        })
      },
      // 获取当前树的节点
      setCurrentNode () {
        const rootNode = this.$refs.classTree.root
        this.currentNode = findNodeInChildren(this.currentClass.id, rootNode.childNodes)

        function findNodeInChildren (id, nodes) {
          for (let index in nodes) {
            const item = nodes[index]
            if (item.key === id) {
              return item
            } else {
              if (item.childNodes && item.childNodes.length > 0) {
                const result = findNodeInChildren(id, item.childNodes)
                if (result) {
                  return result
                }
              }
            }
          }
        }
      },
      // 根据ID获得当前节点数据
      getNodeById (id) {
        // 返回节点数据
        return getNodeInChildren(id, this.classTreeData)
        // 递归查找
        function getNodeInChildren (id, children) {
          for (let index in children) {
            const item = children[index]
            if (item.id === id) {
              return item
            } else {
              var node = getNodeInChildren(id, item.children)
              if (node) {
                return node
              } else {
                continue
              }
            }
          }
        }
      },
      // 根据Id查找到父节点
      getParentNodeById (id) {
        for (let index in this.classTreeData) {
          const result = getParentNode(id, this.classTreeData[index])
          if (result) {
            return result
          }
        }

        // 递归查找
        function getParentNode (id, parent) {
          if (parent.children && parent.children.length > 0) {
            for (let index in parent.children) {
              const item = parent.children[index]
              if (item.id === id) {
                return parent
              } else {
                const result = getParentNode(id, item)
                if (result) {
                  return result
                }
              }
            }
          }
        }
      },
      // 根据路由判断当前进入的功能
      getCurrentManagerFromRouter () {
        const routeName = this.$route.name
        if (routeName === 'propertyView') {
          return classTypes.MANAGER_ATTR
        } else if (routeName === 'methodView') {
          return classTypes.MANAGER_METHOD
        } else if (routeName === 'constantView') {
          return classTypes.MANAGER_CONST
        } else if (routeName === 'basicInfoView') {
          return classTypes.MANAGER_BASIC_INFO
        } else if (routeName === 'indexMaintenanceView') {
          return classTypes.MANAGER_INDEX_MAINTENANCE
        } else {
          return null
        }
      },
      // 左边菜单的展开和收缩
      leftAreaToggle () {
        if (this.isLeftAreaCllapsed) {
          // 展开
          this.$refs.leftArea.style.width = `${this.leftAreaSize}px`
          this.$refs.mainArea.style.left = `${this.leftAreaSize}px`
          this.isLeftAreaCllapsed = false
        } else {
          // 收缩
          this.$refs.leftArea.style.width = '0px'
          this.$refs.mainArea.style.left = '0px'
          this.isLeftAreaCllapsed = true
        }
      },
      // 树过滤器
      classFilter (value, data, node) {
        if (this.filterForm.industries.length > 0 && this.filterForm.industries.indexOf(data.edmcIndustryCode) < 0) {
          return false
        }
        if (this.filterForm.className && data.viewName.indexOf(this.filterForm.className) < 0) {
          return false
        }
        this.$nextTick(() => {
          if (node.childNodes && node.childNodes.length > 0) {
            node.expanded = false
            const showChild = node => {
              if (node.childNodes && node.childNodes.length > 0) {
                // 显示所有子节点
                for (let item of node.childNodes) {
                  item.visible = true
                  showChild(item)
                }
              }
            }
            showChild(node)
          }
        })
        return true
      },
      // 执行过滤
      doFilter () {
        // 1秒内只做一次过滤
        clearTimeout(this.timeout)
        this.timeout = setTimeout(() => {
          this.$refs.classTree.filter(this.filterForm)
        }, 300)
      },
      // 选中树节点
      changeCurrent (currentData, node) {
        if (!node.isLeaf && !node.expanded) {
          node.expanded = true
        }
        if (this.currentClass.id !== currentData.id) {
          this.currentClass = currentData
          this.currentNode = node
          const industrie = this.getIndustrie(this.currentClass.edmcIndustryCode)
          if (industrie) {
            this.currentClass.industrieName = industrie.label
          }
          // 保存到vuex中
          this.$store.commit(classTypes.SET_CURRENT_CLASS, this.currentClass)
          // 子功能跳转
          this.gotoManager(classTypes.MANAGER_ATTR)
        }
      },
      // 获得功能名称
      getManagerName (key) {
        return classTypes[key]
      },
      // 进入相应的功能
      gotoManager (managerName) {
        let name
        if (managerName === classTypes.MANAGER_ATTR) {
          this.currentManager = classTypes.MANAGER_ATTR
          name = 'propertyView'
        } else if (managerName === classTypes.MANAGER_METHOD) {
          this.currentManager = classTypes.MANAGER_METHOD
          name = 'methodView'
        } else if (managerName === classTypes.MANAGER_CONST) {
          this.currentManager = classTypes.MANAGER_CONST
          name = 'constantView'
        } else if (managerName === classTypes.MANAGER_BASIC_INFO) {
          this.currentManager = classTypes.MANAGER_BASIC_INFO
          name = 'basicInfoView'
        } else if (managerName === classTypes.MANAGER_INDEX_MAINTENANCE) {
          this.currentManager = classTypes.MANAGER_INDEX_MAINTENANCE
          name = 'indexMaintenanceView'
        }
        // 进入对应的路由
        this.$router.push({
          name,
          params: {
            classId: this.currentClass.id
          }
        })
      },
      // 获得模式
      getMode (key) {
        return classTypes[key]
      },
      // 添加一个新的类
      createClass () {
        this.$openDialog({
          name: 'create-class',
          component: ClassEditor,
          options: {
            title: '新增类',
            customClass: 'dialog-width-l'
          },
          props: {
            mode: classTypes.EDIT_MODE_CREATE,
            parentId: this.currentClass.id,
            edmId: this.$route.params.id
          },
          events: {
            createSuccess: () => {
              // 刷新树
              this.getClassTree()
              this.$closeDialog('create-class')
            },
            close: () => {
              this.$closeDialog('create-class')
            }
          }
        })
      },
      editClass () {
        this.$openDialog({
          name: 'edit-class',
          component: ClassEditor,
          options: {
            title: '编辑类',
            customClass: 'dialog-width-l'
          },
          props: {
            mode: classTypes.EDIT_MODE_UPDATE,
            classInfo: this.currentClass
          },
          events: {
            updateSuccess: () => {
              // 刷新树
              this.getClassTree()
              this.$closeDialog('edit-class')
            },
            removeSuccess: () => {
              this.$router.push({name: 'classView'})
              this.getClassTree()
              this.$closeDialog('edit-class')
            },
            close: () => {
              this.$closeDialog('edit-class')
            }
          }
        })
      },
      copyClass () {
        this.$openDialog({
          name: 'copy-class',
          component: ClassEditor,
          options: {
            title: '复制类',
            customClass: 'dialog-width-l'
          },
          props: {
            mode: classTypes.EDIT_MODE_COPY,
            classInfo: this.currentClass
          },
          events: {
            copySuccess: () => {
              // 刷新树
              this.getClassTree()
              this.$closeDialog('copy-class')
            },
            close: () => {
              this.$closeDialog('copy-class')
            }
          }
        })
      },
      // 上移
      toUp () {
        // 父节点
        const parent = this.currentNode.parent
        // 父节点下子节点
        const children = parent.childNodes
        // 当前节点所在位置
        const index = children.indexOf(this.currentNode)
        if (index > 0) {
          // 更新节点位置
          parent.removeChild(this.currentNode)
          parent.insertChild(this.currentNode, index - 1)
          this.currentNode.parent = parent
          // 更新数据源
          this.$store.commit(classTypes.UP, this.currentClass)
          // 远程更新
          this.updateSeq(children)
        }
      },
      // 下移
      toDown () {
        // 父节点
        const parent = this.currentNode.parent
        // 父节点下子节点
        const children = parent.childNodes
        // 当前节点所在位置
        const index = children.indexOf(this.currentNode)
        if (index < children.length - 1) {
          // 更新节点位置
          parent.removeChild(this.currentNode)
          parent.insertChild(this.currentNode, index + 1)
          this.currentNode.parent = parent
          // 更新数据源
          this.$store.commit(classTypes.DOWN, this.currentClass)
          // 远程更新
          this.updateSeq(children)
        }
      },
      updateSeq (nodes) {
        // 2秒后远程更新, 再次操作刷新时间
        clearTimeout(this.timeout)
        this.timeout = setTimeout(() => {
          let ids = []
          for (let index in nodes) {
            ids.push(nodes[index].key)
          }
          classService.moveClass(ids)
        }, 2000)
      },
      // 重置类顺序
      resetSeq (classes) {
        classes.forEach((item, index) => {
          item.edmcSeq = index
        })
      },
      // 获得行业名称
      getIndustrie (value) {
        // 返回节点数据
        return getNodeInChildren(value, this.trees.edm_industry_code)
        // 递归查找
        function getNodeInChildren (value, children) {
          for (let index in children) {
            const item = children[index]
            if (item.value === value) {
              return item
            } else {
              var node = getNodeInChildren(value, item.children)
              if (node) {
                return node
              } else {
                continue
              }
            }
          }
        }
      }
    }
  }
</script>
<style scoped>
.left-Area{
  position: absolute;
  left: 0px;
  top: 0px;
  bottom: 0px;
  box-shadow: 0px 0px 15px #F6F6FB;
  z-index: 1;
}
.left-operate{
  position: absolute;
  height: 60px;
  line-height: 60px;
  width: 16px;
  background-color: #fff;
  right: -16px;
  top: 50%;
  margin-top: -30px;
  cursor: pointer;
  border-radius: 0 5px 5px 0px;
  z-index: 2;
}
.size-operater{
  position: absolute;
  top: 0px;
  bottom: 0px;
  width: 5px;
  z-index: 2;
  cursor: ew-resize;
}
.cur-move {
  cursor: ew-resize;
}
.main-area{
  position: absolute;
  top: 0px;
  right: 0px;
  bottom: 0px;
}
.info-area{
  position: absolute;
  left: 0px;
  right: 0px;
  top: 0px;
  height: 50px;
  background-color: #fff;
  padding-left: 25px;
}
.data-area{
  position: absolute;
  left: 0px;
  top: 50px;
  right: 0px;
  bottom: 0px;
  background-color: #F1F3FA;
  padding: 10px 25px;
}
.title{
  height: 55px;
  line-height: 55px;
  font-size: 22px;
  text-align: center;
}
.input{
  width: 90%;
  margin-left: 5%;
  margin-top: 10px;
}
.choose-industry{
  position: absolute;
  width: 180px;
  left: 10px;
  top: 100px;
  z-index: 10;
  overflow-x: auto;
  border-radius: 4px;
  overflow: hidden;
  background-color: #fff;
  box-shadow: 0px 0px 15px #C9C4E0;
  padding-bottom: 10px;
}
.choose-industry .button{
  height: 25px;
  line-height: 25px;
  text-align: right;
}
.cover{
  position: absolute;
  left: 0px;
  top: 0px;
  right: 0px;
  bottom: 0px;
  z-index: 9;
}
.icon-area{
  width: 90%;
  margin-left: 5%;
  margin-top: 20px;
  font-size: 16px;
  text-align: center;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  -o-user-select: none;
  user-select: none;
}
.icon-area i{
  margin-right: 5px;
  cursor: pointer;
}
.class-tree{
  margin-top: 10px;
}
.fade-enter-active, .fade-leave-active {
  transition: all 0.3s ease
}
.fade-enter, .fade-leave-active{
  opacity: 0
}
.info-area{
  height: 50px;
  line-height: 50px;
}
.info-area .info{
  float: left;
}
.info-area .split{
  float: left;
  margin-right: 25px;
  margin-left: 25px;
  margin-top: 19px;
  height: 10px;
  border-left: solid 1px #ddd;
}
.button-area{
  position: absolute;
  left: 25px;
  top: 10px;
}
.button-area  .el-button{
  width: 80px;
}
.auto-height{
  height: calc(100vh - 240px);
  overflow: auto;
}
.maring-left-10{
  margin-left: 10px;
}
</style>
