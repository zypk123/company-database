<template>
  <div class="conditions">
    <div v-for="(item, index) in allDataSet">
      <div class="condition">
        <el-button type="text" :icon="index + 1 ? 'caret-bottom':'caret-top'" @click="showCondition(item)"></el-button>
        触发条件{{index + 1}}
        <trigger-condition-editor
          class="input"
          :disabled="mode !== 'edit'"
          :propertyId="propertyId"
          v-model="item.condition.edcoCond"
          :text="item.condition.condFormula ? item.condition.condFormula : item.condition.condFormulaDesc"
          >
        </trigger-condition-editor>
        <!-- v-model="allConditions[index].edcoCond"
        :text="allConditions[index].condFormula ? allConditions[index].condFormula : allConditions[index].condFormulaDesc" -->
        <!-- <el-input class="input" icon="plus" :disabled="mode !== 'edit'"></el-input> -->
        <el-button type="primary" icon="rx-baocun1" v-if="mode === 'edit'" @click="saveCondition">保存条件</el-button>
        <el-button type="danger" v-if="mode === 'edit'" @click="clearCondition(item)">清空条件</el-button>
        <el-button type="danger" v-if="mode === 'edit'" @click="deleteCondition(item)">删除条件</el-button>
      </div>
      <div class="button-area">
        <span class="button" v-if="mode === 'edit'">
          <el-button type="primary" icon="plus" @click="createLink">新增关联</el-button>
          <el-button type="danger" icon="delete2" @click="removeLink">删除关联</el-button>
          <el-button>移动关联</el-button>
        </span>
      </div>
      <div v-show="item.isShowTable">
        <el-table
          ref="currentTable"
          v-loading="isSearching"
          :data="item.tableData"
          @selection-change="handleChecked"
          @row-click="checkCurrent"
          :row-class-name="tableCheckedClass"
          >
          <!-- :data="tableData"
          :data="allTableData[index - 1]"-->
          <!-- v-show="showConditionn[index - 1]" -->
          <el-table-column type="selection" width="40" align="center" v-if="mode === 'edit'"></el-table-column>
          <el-table-column type="index" label="序号" width="40" align="center"></el-table-column>
          <el-table-column label="类名" width="120" align="center" prop="edmcName"></el-table-column>
          <el-table-column label="关联属性" width="110" align="center">
            <template scope="scope">
              <el-button v-if="mode === 'edit'" type="text" v-on:click.stop="editLink(scope.row)">{{scope.row.edmpName}}</el-button>
              <span v-else>{{scope.row.edmpName}}</span>
            </template>
          </el-table-column>
          <el-table-column label="对象定位公式" align="center">
            <template scope="scope">
              <el-tooltip class="item" effect="dark" :content="scope.row.formulaDesc" placement="top">
                <span v-if="scope.row.formula" style="white-space:nowrap">{{scope.row.formula}}</span>
                <span v-else style="white-space:nowrap">{{scope.row.formulaDesc}}</span>
              </el-tooltip>
            </template>
          </el-table-column>
          <el-table-column label="联动方式" align="center" width="100">
            <template scope="scope">
              {{UTILS.getDictName('edm_connect_type', scope.row.edclType)}}
            </template>
          </el-table-column>
          <el-table-column label="保存联动记录" width="100" align="center">
            <template scope="scope">
              <el-switch v-if="mode === 'edit'" :on-value="1" :off-value="0" on-text="是" off-text="否" v-model="scope.row.edcnLinkPreservable"
              @change="changeEdcnLinkPreservable(scope.row)"></el-switch>
              <span v-else>{{scope.row.edcnLinkPreservable === 1 ? '是':'否'}}</span>
            </template>
          </el-table-column>
          <el-table-column label="联动时效"width="120" align="center">
            <template scope="scope">
              <span v-if="scope.row.edclUpdateType === 'sync'">{{UTILS.getDictName('edm_update_type', scope.row.edclUpdateType)}}</span>
              <span v-if="scope.row.edclUpdateType === 'async'">
                {{UTILS.getDictName('edm_update_type', scope.row.edclUpdateType)}}
                ({{UTILS.getDictName('edm_asyncType_priority', scope.row.asyncTypePriority)}})
              </span>
              <span v-if="scope.row.edclUpdateType === 'time'">
                {{UTILS.getDictName('edm_update_type', scope.row.edclUpdateType)}}
                ({{scope.row.edclUpdateTime}})
              </span>
            </template>
          </el-table-column>
          <el-table-column label="维护人" width="80"  align="center" prop="moduser"></el-table-column>
          <el-table-column label="维护时间" width="150" align="center" prop="modtimeStr"></el-table-column>
        </el-table>
      </div>
    </div>
    <div class="addConditions">
      <el-button @click="addCondition">新增条件</el-button>
    </div>
  </div>
</template>
<script>
  import LinkEditor from './LinkEditor'
  import TriggerConditionEditor from '@/components/commons/trigger-condition-editor/TriggerConditionEditor'
  import { dictTypes, propertyTypes } from '@/types'
  import { propertyService } from '@/api'

  export default {
    name: 'property-link',
    props: ['propertyId', 'mode'],
    components: {
      TriggerConditionEditor
    },
    created () {
      console.log('this.propertyId', this.propertyId)  // 当前属性row行的id
      // 自动生成第一个触发条件
      this.addCondition()
      // 获得数据字典
      this.$store.dispatch(dictTypes.GET_DICT_BY_CODES, ['edm_asyncType_priority', 'edm_update_type', 'edm_connect_type']).then(() => {
        // 获取关联属性
        this.getLinks()
        // 获取触发条件
        // propertyService.queryCondition(this.propertyId).then(data => {
        //   if (data) {
        //     this.isUpdate = true
        //     this.UTILS.setDataFromOther(this.allDataSet, data)
        //     console.log(data, this.allDataSet)
        //   } else {
        //     this.isUpdate = false
        //     // this.allDataSet.edcoEdmpId = this.propertyId
        //   }
        // })
      })
    },
    data () {
      return {
        conditions: {
          edcoEdmpId: '',
          edcoCond: ''
        },
        condition: {
          edcoCond: '',
          condFormula: '',
          condFormulaDesc: '',
          id: '',
          edcoEdmpId: ''
        },
        // 查询中
        isSearching: false,
        // 列表数据
        tableData: [],
        // 选中行
        checkedRows: [],
        // 是否是编辑状态
        isUpdate: false,
        // 所有数据集合
        allDataSet: []
        // 是否显示触发条件n
        // showConditionn: [],
        // showCondition1: '',
        // // 多个列表数据
        // allTableData: [],
        // allConditions: [],
      }
    },
    methods: {
      // 是否显示触发条件n
      showCondition (item) {
        item.isShowTable = !item.isShowTable
      },
      addCondition () {
        // 新增时调用后端接口，返回id
        this.conditions.edcoEdmpId = this.propertyId
        propertyService.createCondition(this.conditions).then(data => {
          let allData = {
            condition: {
              edcoCond: '',
              condFormula: '',
              condFormulaDesc: '',
              id: '',
              edcoEdmpId: ''
            },
            isSearching: false,
            tableData: [],
            checkedRows: [],
            isUpdate: false,
            isShowTable: true,
            edmlCond: data
          }
          this.allDataSet.push(allData)
          console.log(this.allDataSet)
        })
      },
      // 删除触发条件
      deleteCondition (index, item) {
        this.allDataSet.splice(index, 1)
        // item.isShowTable = this.allDataSet[index].isShowTable
        // console.log(this.allDataSet)
      },
      // 清空触发条件
      clearCondition (item) {
      },
      // 查找关联数据
      getLinks (index) {
        // this.isSearching = true
        propertyService.getPropertyLinks(this.propertyId).then(data => {
        })
      },
      // 添加关联属性
      createLink () {
        this.$openDialog({
          name: 'create-link',
          component: LinkEditor,
          options: {
            title: '新增关联属性',
            customClass: 'dialog-width-m'
          },
          props: {
            mode: propertyTypes.EDIT_MODE_CREATE,
            propertyId: this.propertyId
          },
          events: {
            close: () => {
              this.$closeDialog('create-link')
            },
            createSuccess: () => {
              // 刷新列表
              this.getLinks()
              this.$closeDialog('create-link')
            }
          }
        })
      },
      editLink (row) {
        this.$openDialog({
          name: 'edit-link',
          component: LinkEditor,
          options: {
            title: '编辑关联属性',
            customClass: 'dialog-width-m'
          },
          props: {
            mode: propertyTypes.EDIT_MODE_UPDATE,
            linkInfo: row
          },
          events: {
            close: () => {
              this.$closeDialog('edit-link')
            },
            updateSuccess: () => {
              // 刷新列表
              this.getLinks()
              this.$closeDialog('edit-link')
            },
            removeCondition: (row) => {
              // 删除关联公式
              row.formulaDesc = ''
              row.formula = ''
            }
          }
        })
      },
      changeEdcnLinkPreservable (row) {
        this.$nextTick(() => {
          propertyService.updatePropertyLink({
            id: row.id,
            edcnLinkPreservable: row.edcnLinkPreservable
          })
        })
      },
      // 更新选中行
      handleChecked (rows) {
        if (this.mode === 'edit') {
          // this.checkedRows = rows
        }
      },
      // 选中行，自动选中复选框
      checkCurrent (row) {
        this.$refs.currentTable.toggleRowSelection(row)
      },
      // 选中高亮样式
      tableCheckedClass (row, index) {
        if (this.checkedRows.indexOf(row) >= 0) {
          return 'table-checked'
        }
        return ''
      },
      removeLink () {
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
            propertyService.deletePropertyLink(ids).then(data => {
              // 刷新列表
              this.getLinks()
            })
          })
        }
      },
      // 保存触发条件公式
      saveCondition () {
        if (this.condition.edcoCond) {
          if (this.isUpdate) {
            // 更新
            propertyService.updateCondition(this.condition).then(data => {
              this.$message.success('更新成功！')
            })
          } else {
            // 保存
            propertyService.createCondition(this.condition).then(data => {
              this.$message.success('保存成功！')
              this.condition.id = data
              this.isUpdate = true
            })
          }
        } else {
          this.$message.warning('请先编辑触发条件公式！')
        }
      }
    }
  }
</script>
<style scoped>
  .conditions{
    height: 350px;
    overflow-y: auto;
  }
  .input{
    width: 300px;
  }
  .condition{
    height: 35px;
    line-height: 35px;
    float: left;
  }
  .button-area{
    height: 35px;
    line-height: 35px;
    float: right;
    margin-right: 10px;
  }
  .title{
    float:left;
    font-size: 14px;
    font-weight: bold;
    margin-left: 20px;
  }
  .condition2{
    margin-top: 15px;
  }
  .addConditions{
    margin-top: 15px;
    float: right;
  }
</style>
