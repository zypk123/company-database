<template>
  <div>
    <div class="info-area" v-if="dataIn.type === getPropertyType('PROPERTY_TYPE_ASSEMBLE')">
      <div class="info">
        <label>所属类或属性：</label>
        <span>{{dataIn.info.parent}}</span>
      </div>
      <div class="split"></div>
      <div class="info">
        <label>属性集：</label>
        <span>{{dataIn.info.current}}</span>
      </div>
      <div class="split"></div>
      <div class="info">
        <label>属性编码：</label>
        <span>{{dataIn.info.code}}</span>
      </div>
    </div>
    <div class="right-area">
      <div class="search-area">
          <el-input placeholder="请输入属性代码或名称" icon="search" v-model="propertyCode"></el-input>
        </div>
      <div class="button-area">
        <div v-if="dataIn.mode === 'edit'">
          <el-button type="primary" icon="plus" @click="createNewProperty">添加</el-button>
          <el-button type="default" icon="arrow-up" @click="turnUp">上移</el-button>
          <el-button type="default" icon="arrow-down" @click="turnDown">下移</el-button>
          <el-button type="danger" icon="delete2" @click="removeProp">删除</el-button>
        </div>
      </div>
    </div>
    <div class="main-content">
      <!-- 当前类属性表格 -->
      <el-table
        ref="currentTable"
        :data="attributeData"
        v-loading="isSearching"
        @selection-change="handleChecked"
        @row-click="checkCurrent"
        :row-class-name="tableCheckedClass"
        :height="dataIn.type === getPropertyType('PROPERTY_TYPE_CLASS') ?
        $store.state.windowStore.windowHeight - parentTableHeight - 190 : 500"><!-- 属性模式自适应，数据集模式高度固定500 -->
        <el-table-column type="selection" width="40px" align="center" fixed v-if="dataIn.mode === 'edit'">
        </el-table-column>
        <el-table-column :width="dataIn.mode === 'edit'? '40px':'50px'" label="序号" type="index" align="center" fixed>
        </el-table-column>
        <el-table-column label="属性编码" prop="edmpCode" :width="dataIn.mode === 'edit'? '110px':'125px'"  fixed>
        </el-table-column>
        <el-table-column label="属性名称" :width="dataIn.mode === 'edit'? '130px':'145px'" align="center"  fixed>
          <template scope="scope">
            <el-badge is-dot v-if="scope.row.isCharacter">
              <el-button type="text" v-if="dataIn.mode === 'edit'" v-on:click.stop="updateProperty(scope.row)">
                {{scope.row.edmpName}}
              </el-button>
              <span v-else class="name-span">{{scope.row.edmpName}}</span>
            </el-badge>
            <span v-else>
              <el-button type="text" v-if="dataIn.mode === 'edit'" v-on:click.stop="updateProperty(scope.row)">
                {{scope.row.edmpName}}
              </el-button>
              <span v-else class="name-span">{{scope.row.edmpName}}</span>
            </span>
          </template>
        </el-table-column>
        <el-table-column label="属性类别" :formatter="getEdmpValueName" width="80px" align="center" >
        </el-table-column>
        <el-table-column label="数据类型" width="90px" align="center">
          <template scope="scope">
            <!-- 普通属性时展示 -->
            <span v-if="scope.row.edmpValueType === 'normalObj'">
              {{UTILS.getDictName('edm_data_type', scope.row.edmpDataType)}}
              <span v-if="scope.row.edmpDataType === 'varchar' || scope.row.edmpDataType === 'decimal'">({{scope.row.edmpValueSize}})</span>
            </span>
            <!-- 对象和对象链接属性展示 -->
            <span v-if="scope.row.edmpValueType === 'object' || scope.row.edmpValueType === 'objectLink'">
              {{getClassName(scope.row.edmpDataType)}}
            </span>
            <!-- 属性集类型 -->
            <el-button type="text" v-if="scope.row.edmpValueType === 'assemble'" v-on:click.stop="viewAssemble(scope.row, dataIn.mode)">属性集</el-button>
            <!-- 计量单位-->
            <span v-if="scope.row.edmpValueType === 'measurement'">单位类</span>
            <!-- 卷积， 常量-->
            <span v-if="scope.row.edmpValueType === 'convolution' || scope.row.edmpValueType === 'const'">统计类</span>
          </template>
        </el-table-column>
        <el-table-column label="属性扩展" width="120px" align="center">
          <template scope="scope">
            <span v-if="scope.row.edmpExtendProperty === '扩展'">
              <el-button v-if="dataIn.mode === 'edit' && scope.row.edmpValueType !== 'assemble'"
                type="default" v-on:click.stop="openExpand(scope.row, dataIn.mode)">扩展</el-button>
              <span v-else>--</span>
            </span>
            <span v-else>
              <el-button type="text" v-on:click.stop="openExpand(scope.row, dataIn.mode)">{{scope.row.edmpExtendProperty}}</el-button>
            </span>
          </template>
        </el-table-column>
        <el-table-column label="属性限值" width="200px" align="center">
          <template scope="scope">
            <el-tooltip class="item" effect="dark" :content="scope.row.propertLimitDesc" placement="top">
              <span v-if="scope.row.propertLimit" style="white-space:nowrap">{{scope.row.propertLimit}}</span>
              <span v-else style="white-space:nowrap">{{scope.row.propertLimitDesc}}</span>
            </el-tooltip>
          </template>
        </el-table-column>
        <el-table-column label="属性公式" width="200px" show-overflow-tooltip  show-overflow-tooltip prop="propertFormula">
        </el-table-column>
        <el-table-column label="属性值" prop="edmpValue" width="100px" align="center">
          
        </el-table-column>
        <el-table-column label="更新响应方法" width="120px" align="center">
          <template scope="scope">
            <span v-if="!!scope.row.edmpEdmmId">
              <el-button v-for="(item, index) in changeToArray(scope.row.edmpEdmmId)" :key="item" type="text" v-on:click.stop="viewMethod(item)">
                {{changeToArray(scope.row.edmpEdmmName)[index]}}
              </el-button>
            </span>
          </template>
        </el-table-column>
        <!-- <el-table-column label="是否索引" width="90px" align="center">
          <template scope="scope">
            <span v-if="scope.row.edmpValueType !== 'assemble'">
              <el-switch @change="upateIsIndex(scope.row)" v-if="dataIn.mode === 'edit'"
                v-model="scope.row.isIndex"
                on-text="是"
                off-text="否"
                :on-value="1"
                :off-value="0"></el-switch>
              <span v-else>{{scope.row.isIndex === 1 ? '是' : '否' }}</span>
            </span>
            <span v-else>--</span>
          </template>
        </el-table-column> -->
        <el-table-column label="是否可见" width="90px" align="center">
          <template  scope="scope">
            <el-switch @change="upateIsVisible(scope.row)" v-if="dataIn.mode === 'edit'"
              v-model="scope.row.isVisible"
              on-text="是"
              off-text="否"
              :on-value="1"
              :off-value="0"></el-switch>
            <span v-else>{{scope.row.isIndex === 1 ? '是' : '否' }}</span>
          </template>
        </el-table-column>
        <el-table-column label="属性描述" prop="edmpDesc" width="200px" show-overflow-tooltip>
          
        </el-table-column>
        <el-table-column label="维护人" prop="moduser" width="100px" align="center">
          
        </el-table-column>
        <el-table-column label="维护时间" prop="modtimeStr" width="100px" align="center" show-overflow-tooltip>
          <template scope="scope">
            <span v-if="scope.row.modtimeStr">
              {{scope.row.modtimeStr.substring(0, 10)}}
            </span>
          </template>
        </el-table-column>
      </el-table>

      <el-button type="text" @click="toggleParentTable" v-if="dataIn.type === getPropertyType('PROPERTY_TYPE_CLASS')"
       :icon="showParent? 'caret-bottom':'caret-top'">父类属性</el-button>
      
      <!-- 父类属性表格 -->
      <el-table v-if="dataIn.type === getPropertyType('PROPERTY_TYPE_CLASS')"
        :data="parentAttributeData"
        v-show="showParent"
        ref="parentTable"
        v-loading="isSearching"
        :height="parentTableHeight">
        <el-table-column width="40px" label="序号" type="index" align="center" fixed>
        </el-table-column>
        <el-table-column label="父类名称" width="80px" align="center" fixed>
          <template scope="scope">
            {{getClassName(scope.row.edmpEdmcId)}}
          </template>
        </el-table-column>
        <el-table-column label="属性编码" prop="edmpCode" width="90px" fixed>
        </el-table-column>
        <el-table-column label="属性名称" width="110px" align="center" fixed>
          <template scope="scope">
            <el-badge is-dot v-if="scope.row.isCharacter">
              <span class="name-span">{{scope.row.edmpName}}</span>
            </el-badge>
            <span v-else class="name-span">
              {{scope.row.edmpName}}
            </span>
          </template>
        </el-table-column>
        <el-table-column label="属性类别" :formatter="getEdmpValueName" width="80px" align="center">
        </el-table-column>
        <el-table-column label="数据类型" width="90px" align="center">
          <template scope="scope">
            <!-- 普通属性时展示 -->
            <span v-if="scope.row.edmpValueType === 'normalObj'">
              {{UTILS.getDictName('edm_data_type', scope.row.edmpDataType)}}
              <span v-if="scope.row.edmpDataType === 'varchar' || scope.row.edmpDataType === 'decimal'">({{scope.row.edmpValueSize}})</span>
            </span>
            <!-- 对象和对象链接属性展示 -->
            <span v-if="scope.row.edmpValueType === 'object' || scope.row.edmpValueType === 'objectLink'">
              {{getClassName(scope.row.edmpDataType)}}
            </span>
            <!-- 属性集类型 -->
            <el-button type="text" v-if="scope.row.edmpValueType === 'assemble'" v-on:click.stop="viewAssemble(scope.row, 'view')">属性集</el-button>
            <!-- 计量单位-->
            <span v-if="scope.row.edmpValueType === 'measurement'">计量单位</span>
            <!-- 卷积， 常量-->
            <span v-if="scope.row.edmpValueType === 'convolution' || scope.row.edmpValueType === 'const'">统计类</span>
          </template>
        </el-table-column>
        <el-table-column label="属性扩展" width="120px" align="center">
          <template scope="scope">
            <span v-if="scope.row.edmpExtendProperty === '扩展'">
              --
            </span>
            <span v-else>
              <el-button type="text" v-on:click.stop="openExpand(scope.row, 'view')">{{scope.row.edmpExtendProperty}}</el-button>
            </span>
          </template>
        </el-table-column>
        <el-table-column label="属性限值" width="200px" align="center">
          <template scope="scope">
            <el-tooltip class="item" effect="dark" :content="scope.row.propertLimitDesc" placement="top" style="text-align:center">
              <span v-if="scope.row.propertLimit" style="white-space:nowrap">{{scope.row.propertLimit}}</span>
              <span v-else style="white-space:nowrap">{{scope.row.propertLimitDesc}}</span>
            </el-tooltip>
          </template>
        </el-table-column>
        <el-table-column label="属性公式" width="200px" show-overflow-tooltip align="center" prop="propertFormula">
          
        </el-table-column>
        <el-table-column label="属性值" prop="edmpValue" width="100px" show-overflow-tooltip align="center">
          
        </el-table-column>
        <el-table-column label="更新响应方法" width="120px" align="center">
          <template scope="scope">
            <span v-if="!!scope.row.edmpEdmmId">
              <el-button v-for="(item, index) in changeToArray(scope.row.edmpEdmmId)" :key="item" type="text" v-on:click.stop="viewMethod(item)">
                {{changeToArray(scope.row.edmpEdmmName)[index]}}
              </el-button>
            </span>
          </template>
        </el-table-column>
        <el-table-column label="是否索引" width="90px" align="center">
          <template scope="scope">
            <span v-if="scope.row.edmpValueType !== 'assemble'">
              {{scope.row.isIndex === 1 ? '是':'否'}}
            </span>
            <span v-else>--</span>
          </template>
        </el-table-column>
        <el-table-column label="是否可见" width="90px" align="center">
          <template scope="scope">
            {{scope.row.isVisible === 1 ? '是':'否'}}
          </template>
        </el-table-column>
        <el-table-column label="属性描述" prop="edmpDesc" width="200px" show-overflow-tooltip align="center">
          
        </el-table-column>
        <el-table-column label="维护人" prop="moduser" width="100px" align="center">
          
        </el-table-column>
        <el-table-column label="维护时间" width="100px" align="center" show-overflow-tooltip>
          <template scope="scope">
            <span v-if="scope.row.modtimeStr">
              {{scope.row.modtimeStr.substring(0, 10)}}
            </span>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>
<script>
  import { propertyService, functionService } from '@/api'
  import PropertyEditor from './PropertyEditor'
  import Expand from './expand/Expand'
  import MethodView from '@/components/commons/method-selecter/MethodView'
  import { propertyTypes, dictTypes } from '@/types'

  const self = {
    props: ['dataIn'],
    name: 'property-list',
    created () {
      // 取数据字典
      this.$store.dispatch(dictTypes.GET_DICT_BY_CODES, ['edm_field_type', 'edm_data_type']).then(() => {
        // 取数据
        this.getProperties()
        // 获取父类属性
        this.getParentsPropertier()
        // 解决固定列高度适应有偏差的问题
        // 找到固定表格DOM，高度设置为父DOM高度-18（滚动条高度）
        this.$nextTick(() => {
          const fixtedDiv = this.$el.querySelectorAll('.el-table__fixed')
          fixtedDiv.forEach(item => {
            item.style.height = (item.parentNode.offsetHeight - 18) + 'px'
          })
        })
      })
    },
    watch: {
      propertyCode: {
        handler: function () {
          this.doFilter()
        },
        deep: true
      }
    },
    data () {
      return {
        // 列表数据
        attributeData: [],
        copyAttributeData: [],
        // 父类属性
        parentAttributeData: [],
        copyParentAttributeData: [],
        // 选中行
        checkedRows: [],
        // 当时是否正则查找
        isSearching: false,
        // 父类表格高度
        parentTableHeight: 0,
        // 显示父类表格
        showParent: false,
        // 输入查询的属性代码或名称
        propertyCode: null
      }
    },
    methods: {
      // 获得属性
      getPropertyType (key) {
        return propertyTypes[key]
      },
      changeToArray (strs) {
        if (strs) {
          return strs.split(',')
        }
        return []
      },
      // 父类属性表格显示切换
      toggleParentTable () {
        this.showParent = !this.showParent
        if (this.showParent) {
          this.parentTableHeight = 200
        } else {
          this.parentTableHeight = 0
        }
      },
      // 选中高亮样式
      tableCheckedClass (row, index) {
        if (this.checkedRows.indexOf(row) >= 0) {
          return 'table-checked'
        }
        return ''
      },
      // 获取属性
      getProperties () {
        this.attributeData = []
        this.propertyCode = null
        if (this.dataIn.type === propertyTypes.PROPERTY_TYPE_CLASS) {
          // 根据classId查找属性数据
          this.isSearching = true
          propertyService.getPropertiesByClassId(this.$store.state.classTreeStore.currentClass.id).then(data => {
            // 列表刷新
            this.attributeData = data
            this.copyAttributeData = data
            this.isSearching = false
          }).catch(() => {
            this.isSearching = false
          })
        } else {
          // 根据属性查找属性集
          propertyService.getAssembleByPropertyId(this.dataIn.propertyId).then(data => {
            // 列表刷新
            this.attributeData = data
            this.copyAttributeData = data
          })
        }
      },
      // 获取父类属性
      getParentsPropertier () {
        this.parentAttributeData = []
        propertyService.getParentProperties(this.$store.state.classTreeStore.currentClass.id).then(data => {
          this.parentAttributeData = data
          this.copyParentAttributeData = data
        })
      },
      // 输入属性代码或名称进行过滤
      searchProperty () {
        this.attributeData = []
        this.parentAttributeData = []
        // 过滤当前属性
        if (this.copyAttributeData !== null) {
          for (let i = 0; i < this.copyAttributeData.length; i++) {
            if (this.copyAttributeData[i].edmpCode.indexOf(this.propertyCode) !== -1 || this.copyAttributeData[i].edmpName.indexOf(this.propertyCode) !== -1) {
              this.attributeData.push(this.copyAttributeData[i])
            }
          }
          if (this.propertyCode === '' || this.propertyCode === null) {
            this.attributeData = this.copyAttributeData
          }
        }
        // 过滤父类属性
        if (this.copyParentAttributeData !== null) {
          for (let i = 0; i < this.copyParentAttributeData.length; i++) {
            if (this.copyParentAttributeData[i].edmpCode.indexOf(this.propertyCode) !== -1 || this.copyParentAttributeData[i].edmpName.indexOf(this.propertyCode) !== -1) {
              this.parentAttributeData.push(this.copyParentAttributeData[i])
            }
          }
          if (this.propertyCode === '' || this.propertyCode === null) {
            this.parentAttributeData = this.copyParentAttributeData
          }
        }
      },
      // 执行过滤
      doFilter () {
        // 1秒内只做一次过滤
        clearTimeout(this.timeout)
        this.timeout = setTimeout(() => {
          this.searchProperty()
        }, 300)
      },
      // 上移
      turnUp () {
        if (this.checkedRows.length === 0) {
          this.$message.warning('至少选中一行！')
        } else {
          for (let index = 0; index < this.checkedRows.length; index++) {
            const item = this.checkedRows[index]
            // 位置
            const position = this.attributeData.indexOf(item)
            if (position > index) {
              // 与前一位数据交换
              this.attributeData.splice(position, 1)
              this.attributeData.splice(position - 1, 0, item)
            }
          }
          // 2秒后与后台交互，若此期还有同样操作，则刷新时间
          clearTimeout(this.timeout)
          this.timeout = setTimeout(() => {
            propertyService.moveProperty(this.getCurrentIds())
          }, 2000)
        }
      },
      // 下移
      turnDown () {
        if (this.checkedRows.length === 0) {
          this.$message.warning('至少选中一行！')
        } else {
          for (let index = 0; index < this.checkedRows.length; index++) {
            const item = this.checkedRows[this.checkedRows.length - index - 1]
            // 位置
            const position = this.attributeData.indexOf(item)
            if (position < this.attributeData.length - index - 1) {
              // 与后一位数据做交换
              this.attributeData.splice(position, 1)
              this.attributeData.splice(position + 1, 0, item)
            }
          }
          // 2秒后与后台交互，若此期还有同样操作，则刷新时间
          clearTimeout(this.timeout)
          this.timeout = setTimeout(() => {
            propertyService.moveProperty(this.getCurrentIds())
          }, 2000)
        }
      },
      // 获得当前数据ID数组
      getCurrentIds () {
        let ids = []
        this.attributeData.forEach(item => {
          ids.push(item.id)
        })
        return ids
      },
      // 查看方法
      viewMethod (id) {
        functionService.queryMethodIn(id).then(data => {
          this.$openDialog({
            name: 'view-method-' + id,
            component: MethodView,
            options: {
              title: '查看方法',
              customClass: 'dialog-width-s'
            },
            props: data
          })
        })
      },
      // 新建属性
      createNewProperty () {
        this.$openDialog({
          name: 'create-proptery',
          component: PropertyEditor,
          options: {
            title: '新增属性',
            customClass: 'dialog-width-l'
          },
          props: {
            mode: propertyTypes.EDIT_MODE_CREATE,
            parentId: this.dataIn.propertyId
          },
          events: {
            close: () => {
              this.$closeDialog('create-proptery')
            },
            createSuccess: () => {
              // 保存成功，刷新列表
              this.$closeDialog('create-proptery')
              this.getProperties()
            }
          }
        })
      },
      // 更新属性
      updateProperty (row) {
        this.$openDialog({
          name: 'edit-property',
          component: PropertyEditor,
          options: {
            title: '编辑属性',
            customClass: 'dialog-width-l'
          },
          props: {
            mode: propertyTypes.EDIT_MODE_UPDATE,
            propertyInfo: row
          },
          events: {
            close: () => {
              this.$closeDialog('edit-property')
            },
            updateSuccess: () => {
              // 保存成功，刷新列表
              this.$closeDialog('edit-property')
              this.getProperties()
            },
            removeFormula: (row) => {
              row.propertFormula = ''
            },
            removePropLimit: (row) => {
              row.propertLimitDesc = ''
              row.propertLimit = ''
            }
          }
        })
      },
      // 属性查看
      viewProperty (row) {
        this.$openDialog({
          name: 'view-property',
          component: PropertyEditor,
          options: {
            title: '属性查看',
            customClass: 'dialog-width-m'
          },
          props: {
            mode: propertyTypes.EDIT_MODE_VIEW,
            propertyInfo: row
          },
          events: {
            close: () => {
              this.$closeDialog('view-property')
            }
          }
        })
      },
      // 打开扩展
      openExpand (row, mode) {
        this.$openDialog({
          name: 'expand-dialog',
          component: Expand,
          options: {
            title: '属性扩展',
            customClass: 'dialog-width-xxl'
          },
          props: {
            mode,
            propertyInfo: row
          },
          events: {
            update: () => {
              // 刷新列表
              this.getProperties()
            }
          },
          dialogEvents: {
            close: () => {
              // 刷新列表
              if (mode === 'edit') {
                this.getProperties()
              }
            }
          }
        })
      },
      // 编辑，查看属性集
      viewAssemble (row, mode) {
        let currentParentInfo = this.dataIn.info.parent
        if (this.dataIn.info && this.dataIn.info.current) {
          currentParentInfo = currentParentInfo + '.' + this.dataIn.info.current
        }
        this.$openDialog({
          name: 'edit-assemble-dailog' + row.id,
          component: self,
          options: {
            title: '属性集编辑',
            customClass: 'dialog-width-max'
          },
          props: {
            // 编辑模式
            mode: mode,
            // 属性集类型
            type: propertyTypes.PROPERTY_TYPE_ASSEMBLE,
            // 属性id
            propertyId: row.id,
            // 要展示的信息
            info: {
              parent: currentParentInfo,
              current: row.edmpName,
              code: row.edmpCode
            }
          }
        })
      },
      // // 更改是否索引
      // upateIsIndex (row) {
      //   this.$nextTick(() => {
      //     const prop = {
      //       id: row.id,
      //       isIndex: row.isIndex
      //     }
      //     propertyService.updatePropertyPatch(prop)
      //   })
      // },
      // 更新是否可见
      upateIsVisible (row) {
        this.$nextTick(() => {
          const prop = {
            id: row.id,
            isVisible: row.isVisible
          }
          propertyService.updatePropertyPatch(prop)
        })
      },
      // 获取字典名称
      getEdmpValueName (row) {
        return this.UTILS.getDictName('edm_field_type', row.edmpValueType)
      },
      // 获取类名称
      getClassName (id) {
        const result = this.UTILS.getClassById(id)
        if (result) {
          return result.edmcName
        }
        return ''
      },
      // 删除属性
      removeProp () {
        if (this.checkedRows.length === 0) {
          this.$message.warning('至少选中一行！')
        } else {
          const ids = []
          this.checkedRows.forEach(item => {
            ids.push(item.id)
          })
          this.$confirm('确定删除？', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            propertyService.removeProperties(ids).then(data => {
              // 刷新列表
              this.getProperties()
            })
          })
        }
      },
      // 更新选中行
      handleChecked (rows) {
        // 按照序号先排序
        rows.sort((a, b) => this.attributeData.indexOf(a) > this.attributeData.indexOf(b))
        this.checkedRows = rows
      },
      // 选中行，自动选中复选框
      checkCurrent (row) {
        this.$refs.currentTable.toggleRowSelection(row)
      }
    }
  }

  export default self
</script>
<style scoped>
.right-area{
  float: right;
}
.search-area{
  margin-right: 10px;
  width: 180px;
  height: 38px;
  float: left;
}
.button-area{
  /*text-align: right;*/
  height: 38px;
  float: right;
}
.info-area{
  float: left;
  margin-top: 5px
}
.info-area .info{
  float: left;
}
.info-area .split{
  float: left;
  margin-right: 25px;
  margin-left: 25px;
  height: 10px;
  border-left: solid 1px #ddd;
  margin-top: 4px;
}
.name-span{
  padding: 0 5px;
}
</style>
