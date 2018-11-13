<template>
  <div class="hello">
    <el-row :gutter="10" class="content-row">
      <el-col :span="8">
        <h3>模拟公式编辑器的入口, 测试用</h3>
      </el-col>
      <el-col :span="16">
        <el-form :inline="true">
          <el-form-item label="属性1:">
            <span v-if="selectedProps.length>0">{{selectedProps[0].edmpName}}</span>
          </el-form-item>

          <el-form-item label="属性2:">
            <span v-if="selectedProps.length>1">{{selectedProps[1].edmpName}}</span>
          </el-form-item>

          <el-form-item class="float-right">
            <el-button :disabled="selectedProps.length<2" size="mini" @click.stop="openPropRel()">打开[关联对象定位公式]编辑页面</el-button>
          </el-form-item>

          <el-form-item class="float-right">
            <el-button :disabled="selectedProps.length<1" size="mini" @click.stop="openProcessFunc()">流程条件设置</el-button>
          </el-form-item>

          <el-form-item class="float-right">
            <el-button :disabled="selectedProps.length<1" size="mini" @click.stop="openAuditConditionConfig()">审核角色设置</el-button>
          </el-form-item>

          <el-form-item class="float-right">
            <el-button :disabled="selectedProps.length<1" size="mini" @click.stop="openRegulatoryTreeConditionConfig()">监管树条件设置</el-button>
          </el-form-item>

          <el-form-item class="float-right">
            <el-button :disabled="selectedProps.length<1" size="mini" @click.stop="openObjectPreFormulaDesigner">对象呈现公式</el-button>
          </el-form-item>

          <el-form-item class="float-right">
            <el-button size="mini" @click.stop="openPPIFormula()">PPI公式</el-button>
          </el-form-item>
        </el-form>
      </el-col>

      <el-col :span="8" class="content-col">
        <el-scrollbar
          tag="div"
          class="list-items-scroll"
          wrap-class="list-items-scroll-wrapper"
          view-class="el-select-dropdown__list"
          >
        <el-tree class="h100"
          :data="edmClassesTreeData"
          :show-checkbox="false"
          :default-expand-all="true"
          :empty-text="emptyText"
          node-key="id"
          :current-node-key="currentNodeKey"
          ref="edmClassesTree"
          highlight-current
          :expand-on-click-node="false"
          :filter-node-method="filterNode"
          :props="defaultProps"
          :render-content="renderContent"
          @node-click="nodeClick"
          >
        </el-tree>
        </el-scrollbar>
        <div v-if="showFilterEmptyText" class="el-tree__filter-empty-block">
          <span class="el-tree__filter-empty-text">{{ filterEmptyText }}</span>
        </div>
      </el-col>
      <el-col :span="16" class="content-col">
        <mock-edm-props-list
          :edmClassProps="edmClassProps"
          @selectProp="selectProp"
          @openPropFormula="openPropFormula"
          @openPropLimit="openPropLimit"
          @openRelTrigger="openRelTrigger"
        >
        </mock-edm-props-list>
      </el-col>
    </el-row>



  <!--   <div v-if="CONST.debug">
    <h3>开发中使用的地址</h3>
    <ul >
      <li>
        <el-button type="text" @click="openNewWin('#/dev/var-edit')">
        [DEBUG]在新窗口打开 属性编辑
        </el-button>
      </li>
      <li>
        <el-button type="text" @click="openNewWin('#/dev/rel-edm-class-edit')">
        [DEBUG]在新窗口打开 相关类设置
        </el-button>
      </li>
      <li>
        <el-button type="text" @click="openNewWin('#/dev/edm-class-tree')">
        [DEBUG]在新窗口打开 类选择树组件
        </el-button>
      </li>
      <li>
        <el-button type="text" @click="openNewWin('#/playground')">
        [DEBUG]在新窗口打开 playground
        </el-button>
      </li>
    </ul>
    </div> -->
  </div>
</template>

<script>
import appMixin from '@/mixin/app-mixin'
import { formulaService } from '@/api'
import MockEdmClassesTreeItem from './MockEdmClassesTreeItem'
import MockEdmPropsList from './MockEdmPropsList'
export default {
  name: 'mock',
  mixins: [appMixin],
  data () {
    return {
      emptyText: '无数据',
      filterText: '',
      showFilterEmptyText: false,
      filterEmptyText: '过滤后未找到匹配的类',
      edmClassesTreeData: [],
      currentNodeKey: '',
      defaultProps: {
        children: 'children',
        label: 'edmcName'
      },
      edmClassProps: [],
      selectedProps: []
    }
  },
  created () {
    formulaService.queryEdmClassesTree({
      classID: '47d9ba242a864f05818d3f21581e89dd'
    }).then(
    (resData) => {
      console.log(resData)
      this.edmClassesTreeData = resData
    })
  },
  methods: {
    // 不懂干嘛的
    handleClose (done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done()
        })
        .catch(_ => {})
    },
    openNewWin (url) {
      window.open(url)
    },
    renderContent (h, { node, data, store }) {
      return h(MockEdmClassesTreeItem, {props: { parent: this, node, data, store }})
    },
    filterNode (value, data) {
      if (!value) return true
      return data.edmcName.toLowerCase().indexOf(value.toLowerCase()) !== -1
    },
    // 不懂干嘛的
    countAfterFilter () {
      this.showFilterEmptyText = false
      let root = this.$refs.edmClassesTree.$el
      var nodeIterator = document.createNodeIterator(
        root,
        NodeFilter.SHOW_ELEMENT,
        {
          acceptNode: (node) => {
            if (this.WebUtils.hasClass(node, 'el-tree-node') &&
              !this.WebUtils.hasClass(node, 'is-hidden')) {
              return true
            }
            return false
          }
        }
      )
      var cnt = 0
      while (nodeIterator.nextNode()) {
        cnt++
      }
      if (cnt === 0) {
        this.showFilterEmptyText = true
      }
    },
    nodeClick (nodeData, node, nodeVueEl) {
      formulaService.queryEdmClassCascadePropsByClassID({
        edmcId: nodeData.id
      },
      (resData) => {
        console.log(resData)
        if (resData && resData.length > 0) {
          resData.forEach((ele, index, arr) => {
            ele.propAlias = ele.edmpName
            ele.casEdmpCode = ele.edmpCode
            ele.edmpValueType = this.parseEdmpDataValueType(ele)
          })
          this.recursivlySetPropLabel(resData, null, '', '')
        }
        this.edmClassProps = resData
        console.log(this.edmClassProps)
      },
      (error) => {
        this.AppUtils.showWarning(error.message)
      })
    },
    selectProp (prop) {
      console.info('selectProppush')
      console.info(prop)
      this.selectedProps.push(prop)
      this.selectedProps = this.Utils.takeRight(this.selectedProps, 2)
    },
    openPropFormula (prop) {
      let classId = prop.edmpEdmcId // 9bbc44e1e1394bfda7d217d9f7a24f0c
      let propId = prop.id // 64e19108cdfc41a88d98d812184d1954
      let url = `#/formula-designer/prop-formula/${classId}/${propId}`
      this.openNewWin(url)
    },
    openPropLimit (prop) {
      let propId = prop.id
      let url = `#/prop-limit-config/${propId}`
      this.openNewWin(url)
    },
    openPropRel () {
      let prop1 = this.selectedProps[0]
      let propId1 = prop1.id
      let prop2 = this.selectedProps[1]
      let propId2 = prop2.id
      let url = `#/rel-condition-config/${propId1}/${propId2}`
      this.openNewWin(url)
    },
    openProcessFunc () {
      let prop = this.selectedProps[0]
      let classId = prop.edmpEdmcId
      let url = `#/process-func-config/${classId}`
      this.openNewWin(url)
    },
    openAuditConditionConfig () {
      let prop = this.selectedProps[0]
      let classId = prop.edmpEdmcId
      let url = `#/audit-condition-config/${classId}`
      this.openNewWin(url)
    },
    openRegulatoryTreeConditionConfig () {
      let prop = this.selectedProps[0]
      let classId = prop.edmpEdmcId
      let url = `#/regulatory-tree-condition-config/${classId}`
      this.openNewWin(url)
    },
    openObjectPreFormulaDesigner () {
      let prop = this.selectedProps[0]
      let classId = prop.edmpEdmcId
      let url = `#/object-pre-formula/${classId}`
      this.openNewWin(url)
    },
    openPPIFormula () {
      let ppiId = 'bd6gfbfd5b5656'
      let ppiName = 'PPI指标'
      let cycle = 'M'
      let url = `#/ppi-formula/${ppiId}/${ppiName}/${cycle}`
      this.openTest(url)
    },
    openTest (url) {
      window.open(url)
    },
    openRelTrigger (prop) {
      let propId = prop.id
      let url = `#/rel-tirgger-condition-config/${propId}`
      this.openNewWin(url)
    }
  },
  components: {
    MockEdmPropsList
  }
}
</script>

<style scoped>
ul,li {
  margin-top: 6px;
  margin-bottom: 12px;
}
.hello {
  height: 100%;
}
.el-form-item {
  margin-bottom: 0;
}
.el-form-item__label {
  font-weight: bold;
}
.content-row {
  /*border: 1px solid blue;*/
  height: calc(100% - 95px);
}
.content-col {
  height: calc(100% - 40px);
}
.mock-edm-props-list {
  /*border: 1px solid red;*/
  height: calc(100% - 22px);
}
</style>
