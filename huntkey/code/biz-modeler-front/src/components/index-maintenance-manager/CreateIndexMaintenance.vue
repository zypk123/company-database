<template>
  <div class="create-index">
    <div class="button-area">
      <el-button size="small" icon="rx-baocun1" type="primary" @click="saveIndex">保存</el-button>
      <el-button size="small" icon="circle-close" @click="close">取消</el-button>
    </div>
    <div class="form-area">
      <el-form inline :rules="rules" :model="indexData" ref="formName">
        <el-form-item label="类/属性集" prop="edmpAssId">
          <template scope="scope">
            <el-select v-model="indexData.edmpAssId" filterable>
              <el-option
                v-for="item in attributeIndex"
                :key="item.id"
                :label="item.edmpName"
                :value="item.id">
              </el-option>
            </el-select>
          </template>
        </el-form-item>
        <el-form-item label="索引名称" prop="indexName">
          <el-input v-model="indexData.indexName"></el-input>
        </el-form-item>
        <el-form-item label="索引类型" prop="indexType">
          <template scope="scope">
            <el-select v-model="indexData.indexType" filterable>
              <el-option
                v-for="item in allDict['edm_index_type']"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </template>
        </el-form-item>
        <!-- <el-form-item>
          <el-checkbox>唯一</el-checkbox>
        </el-form-item> -->
      </el-form>
    </div>
    <div class="transfer-area">
      <rx-transfer
        ref="transfer"
        filterable
        :titles="['属性', '索引键列']"
        :data="transferData"
        v-model="rightData"
        :props="{
          key: 'id',
          label: 'viewName'
        }">
        <el-button class="transfer-footer" slot="right-footer" size="small" @click="turnUp" :disabled="disabledData">上移</el-button>
        <el-button class="transfer-footer" slot="right-footer" size="small" @click="turnDown" :disabled="disabledData">下移</el-button>
      </rx-transfer>
    </div>
  </div>
</template>
<script>
  import RxTransfer from '@/components/commons/transfer/RxTransfer'
  import { mapGetters } from 'vuex'
  import { dictTypes } from '@/types'
  import { indexService } from '@/api'
  export default {
    name: 'createIndex',
    props: ['dataIn'],
    data () {
      return {
        // 穿梭框源数据,非属性集属性
        transferData: [],
        // 右侧穿梭框数据
        rightData: [],
        // 类/属性集属性
        attributeIndex: [],
        // 新增索引数据
        indexData: {
          indexName: null,
          indexType: 'ordinary',
          edmpAssId: this.$store.state.classTreeStore.currentClass.id
        },
        // 所选类/属性集
        // indexData.edmpAssId: null,
        // 上移下移禁用
        disabledData: true,
        rules: {
          edmpAssId: [{ required: true, message: '请选择类/属性集', tirgger: 'blur' }],
          indexName: [
            { required: true, message: '请输入索引名称', tirgger: 'blur' },
            {
              validator: (rules, value, callback) => {
                if (this.dataIn.currentIndex && this.dataIn.currentIndex.indexName === value) {
                  // console.log('this.dataIn.currentIndex.indexName', this.dataIn.currentIndex.indexName)
                  callback()
                } else {
                  // 验证索引名称唯一性
                  indexService.validateIndexName(this.$store.state.classTreeStore.currentClass.id, value).then(() => {
                    callback()
                  }).catch(() => {
                    callback('属性名重复')
                  })
                }
              },
              trigger: 'blur'
            }
          ],
          indexType: [{ required: true, message: '请选择索引类型', tirgger: 'blur' }]
        }
      }
    },
    created () {
      // console.log('this.allDict', this.allDict)
      // 修改时初始数据
      if (this.dataIn.currentIndex) {
        this.rightData = this.dataIn.currentIndex.indexProppertyIds.split(',')
        // console.log('this.rightData', this.rightData)
        this.indexData.edmpAssId = this.dataIn.currentIndex.edmpAssId
        this.indexData.indexType = this.dataIn.currentIndex.indexType
        this.indexData.indexName = this.dataIn.currentIndex.indexName
      }
      this.getAttributeIndex()
      this.getNonAttributeIndex()
    },
    watch: {
      'indexData.edmpAssId': {
        handler: function () {
          this.getNonAttributeIndex()
        }
      },
      rightData: {
        handler: function () {
          this.changeDisable()
        }
      }
    },
    computed: {
      ...mapGetters({
        allDict: dictTypes.GET_ALL_DICTS
      })
    },
    methods: {
      changeDisable () {
        if (this.rightData.length < 2) {
          this.disabledData = true
        }
        if (this.rightData.length > 1) {
          this.disabledData = false
        }
      },
      // sortData () {
      //   if (this.rightData.length !== 0) {
      //     this.rightData.sort((a, b) => this.$refs.transfer.data.indexOf(a) > this.$refs.transfer.data.indexOf(b))
      //   }
      // },
      // 查找类下所有属性集属性
      getAttributeIndex () {
        indexService.getAttributeIndex(this.$store.state.classTreeStore.currentClass.id).then(data => {
          let currentClassIndex = {}
          currentClassIndex.id = this.$store.state.classTreeStore.currentClass.id
          currentClassIndex.edmpName = this.$store.state.classTreeStore.currentClass.edmcName
          this.attributeIndex.push(currentClassIndex)
          this.attributeIndex = this.attributeIndex.concat(data)
          // console.log(this.attributeIndex)
        })
      },
      // 查找类或属性集下所有非属性集属性
      getNonAttributeIndex () {
        // 属性集id  indexData.edmpAssId
        if (this.indexData.edmpAssId === this.$store.state.classTreeStore.currentClass.id) {
          indexService.getNonAttributeIndex(this.$store.state.classTreeStore.currentClass.id).then(data => {
            this.transferData = data
          })
        }
        if (this.indexData.edmpAssId !== this.$store.state.classTreeStore.currentClass.id) {
          indexService.getNonAttributeIndex(this.$store.state.classTreeStore.currentClass.id, this.indexData.edmpAssId).then(data => {
            this.transferData = data
          })
        }
      },
      saveIndex () {
        this.$refs.formName.validate((valid) => {
          if (valid) {
            this.save()
          } else {
            return false
          }
        })
      },
      save () {
        this.indexData.edmcId = this.$store.state.classTreeStore.currentClass.id
        this.indexData.indexProppertyIds = this.rightData.toString()
        // 更新索引
        // console.log(this.indexData.indexProppertyIds)
        if (this.indexData.indexProppertyIds === '') {
          this.$message.warning('索引键列不能为空')
          return
        }
        if (this.dataIn.currentIndex) {
          this.indexData.id = this.dataIn.currentIndex.id
          // console.log('this.indexData1', this.indexData)
          indexService.updateIndex(this.indexData).then(data => {
            this.$emit('updateSuccess')
          })
        } else {
          // 新建索引
          indexService.createIndex(this.indexData).then(data => {
            // console.log('this.indexData2', this.indexData)
            this.$emit('saveSuccess')
          })
        }
      },
      close () {
        // console.log('close')
        this.$emit('close')
      },
      turnUp () {
        this.checkedData = this.$refs.transfer.rightChecked
        this.checkedData.sort((a, b) => this.rightData.indexOf(a) > this.rightData.indexOf(b))
        if (this.checkedData.length === 0) {
          this.$message.warning('至少选中一行！')
        } else {
          for (let index = 0; index < this.checkedData.length; index++) {
            const item = this.checkedData[index]
            const position = this.rightData.indexOf(item)
            if (position > index) {
              this.rightData.splice(position, 1)
              this.rightData.splice(position - 1, 0, item)
            }
          }
        }
      },
      turnDown () {
        this.checkedData = this.$refs.transfer.rightChecked
        this.checkedData.sort((a, b) => this.rightData.indexOf(a) > this.rightData.indexOf(b))
        if (this.checkedData.length === 0) {
          this.$message.warning('至少选中一行！')
        } else {
          for (let index = 0; index < this.checkedData.length; index++) {
            const item = this.checkedData[this.checkedData.length - index - 1]
            const position = this.rightData.indexOf(item)
            if (position < this.rightData.length - index - 1) {
              this.rightData.splice(position, 1)
              this.rightData.splice(position + 1, 0, item)
            }
          }
        }
      }
    },
    components: {
      RxTransfer
    }
  }
</script>
<style scoped>
  .transfer-footer {
    margin-left: 20px;
    height: 26px;
    line-height: 26px;
  }
  .form-area{
    margin-top: 20px;
  }
  .form-area .el-input, .el-select{
    width: 133px;
  }
</style>
