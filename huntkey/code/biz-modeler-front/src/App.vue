<template>
  <div id="app">
    <!-- <iframe src="http://192.168.113.120:8081/#/formula-designer/prop-formula/9bbc44e1e1394bfda7d217d9f7a24f0c/64e19108cdfc41a88d98d812184d1954"
    height="768" width="1300" scrolling="on">
      
    </iframe> -->
    <div class="nav-area nav-background">
      <div class="main-func" @click="viewEDMList">EDM列表</div>
      <div class="list-button" @mouseenter="isDropDownShow = true" @mouseleave="isDropDownShow = false">
        方法维护<i class="el-icon-caret-bottom el-icon--right"></i>
      </div>
      <transition name="fade" mode="out-in" appear>
        <ul class="drop-down" v-show="isDropDownShow" @mouseenter="isDropDownShow = true" @mouseleave="isDropDownShow = false">
          <li @click="viewMethod">方法管理</li>
          <li @click="viewMethodModel">方法与模型关系查询</li>
          <li @click="viewConModel">卷积公式与模型关系查询</li>
        </ul>
      </transition>
    </div>
      <div class="main-area">
       <router-view></router-view>
      </div>
      <!-- 弹框组件池 -->
      <el-dialog v-for="item in dialogs" :key="item.id"
        :title="item.options.title"
        :ref="item.name"
        v-model="item.visible"
        :size="item.options.size"
        :top="item.options.top"
        :modal="item.options.modal"
        :modal-append-to-body="item.options.modalAppendToBody"
        :lock-scroll="item.options.lockScroll"
        :custom-class="item.options.customClass"
        :close-on-click-modal="item.options.closeOnClickModal"
        :close-on-press-escape="item.options.closeOnPressEscape"
        :show-close="item.options.showClose"
        :before-close="item.options.beforeClose"
        @close="dialogClose(item)"
        @open="dialogOpen(item)">
        <component :is="item.component" :data-in="item.props"></component>
        <span slot="footer" class="dialog-footer" v-if="item.buttons && item.buttons.length">
          <el-button class="button" v-for="buttonItem in item.buttons" :key="buttonItem.text"
          :icon="buttonItem.icon"
          :type="buttonItem.type"
          @click="buttonItem.handler">{{buttonItem.text}}</el-button>
        </span>
     </el-dialog>
   </div>
</template>

<script>
  import Vue from 'vue'
  import _ from 'lodash'
  export default {
    data () {
      return {
        isDropDownShow: false,
        dialogs: []
      }
    },
    created () {
      // 注册全局dialog事件
      // 打开一个dialog, 返回promise, reslvoe的参数是弹框内组件的视图模型
      Vue.prototype.$openDialog = options => {
        return this.addDialog(options)
      }
      // 关闭一个dialog
      Vue.prototype.$closeDialog = name => {
        this.closeDialog(name)
      }
    },
    methods: {
      viewEDMList () {
        this.$router.push({name: 'versionList'})
      },
      viewMethod () {
        this.$router.push({name: 'methodManage-list'})
      },
      viewMethodModel () {
        this.$router.push({name: 'methodModel'})
      },
      viewConModel () {
        this.$router.push({name: 'ConModel'})
      },
      // 新增一个dialog
      addDialog ({name, component, options, props, dialogEvents, events, buttons}) {
        return new Promise((resolve, reject) => {
          if (!name) {
            reject('名称不能为空')
            return
          }
          if (!component) {
            reject('组件不能为空')
            return
          }
          // 初始化dialog
          const dialog = {
            // 唯一标识，用时间来保证唯一
            id: new Date().getTime(),
            // 名称
            name: null,
            // 要展示的组件
            component: null,
            // 显示状态
            visible: true,
            // 自定义参数
            options: {
              // 标题，默认为空
              title: null,
              // 大小, 默认小
              size: 'small',
              // 距离顶部, 默认15%
              top: '15%',
              // 是否需要遮罩层
              modal: true,
              // 遮罩层插入body中
              modalAppendToBody: true,
              // 是否在 Dialog 出现时将 body 滚动锁定
              lockScroll: true,
              // 自定义类名
              customClass: null,
              // 点击遮罩关闭
              closeOnClickModal: false,
              // 是否可以通过按下 ESC 关闭 Dialog
              closeOnPressEscape: true,
              // 是否显示关闭按钮
              showClose: true,
              // 关闭前的回调，会暂停 Dialog 的关闭
              beforeClose: null
            },
            // 事件
            dialogEvents: {
              // 关闭事件
              close: null,
              // 打开事件
              open: null
            },
            events: {

            },
            // 定义参数
            props: {},
            // 自定义事件, 默认为空
            customEvent: {},
              // 示例
              // success: () => {
              //  方法体
              // }
              // 在子组件中，使用vm.$emit('success'), 调用事件
            // 自定义按钮，默认为空
            buttons: []
            // 示例
            // [{
            //   text: '确定',
            //   type: 'primary',
            //   icon: 'check',
            //   handler: () => {
            //     点击事件方法体
            //   }
            // }, {
            //   text: '取消',
            //   type: 'default',
            //   icon: 'close',
            //   handler: () => {
            //     点击事件方法体
            //   }
            // }]
          }
          // 设置Name
          dialog.name = name
          // 注入组件
          dialog.component = component
          // 设置按钮
          dialog.buttons = buttons
          // 合并参数
          _.assign(dialog.options, options)
          // 设置原生事件
          _.assign(dialog.dialogEvents, dialogEvents)
          // 设置参数
          _.assign(dialog.props, props)
          // 设置事件
          _.assign(dialog.customEvent, events)
          // 设置参数事件
          const defaultCreated = component.created
          component.resolve = resolve
          component.dialog = dialog
          // 创建方法
          component.created = function () {
            component.created = defaultCreated
            this.name = component.dialog.name
            // 设置视图模型
            component.dialog.vm = this
            // 设置自定义事件
            for (let key in component.dialog.customEvent) {
              // 先删除后添加
              this.$on(key, component.dialog.customEvent[key])
            }
            // 执行默认方法
            if (defaultCreated) {
              defaultCreated.apply(this)
            }
            // 返回到promise中
            component.resolve(component.dialog.vm)
          }
          // 数组中
          this.$set(this.dialogs, this.dialogs.length, dialog)
        })
      },
      // 关闭dialig
      closeDialog (name) {
        for (let index = 0; index < this.dialogs.length; index++) {
          if (this.dialogs[index].name === name) {
            this.dialogs[index].visible = false
          }
        }
      },
      // 删除一个dialig
      removeDialog (name) {
        for (let index = 0; index < this.dialogs.length; index++) {
          if (this.dialogs[index].name === name) {
            this.dialogs.splice(index, 1)
          }
        }
      },
      // 关闭事件
      dialogClose (item) {
        // 一秒钟以后销毁dialog
        setTimeout(() => {
          this.removeDialog(item.name)
        }, 1000)
        // 执行原生方法
        if (item.dialogEvents.close) {
          item.dialogEvents.close.call()
        }
      },
      // 打开事件
      dialogOpen (item) {
        if (item.dialogEvents.open) {
          item.dialogEvents.open.call()
        }
      }
    }
  }
</script>

<style scoped>
  .nav-area{
    position: absolute;
    border-radius: 5px;
    height: 50px;
    top: 10px;
    left: 10px;
    right: 10px;
  }
  .main-area{
    position: absolute;
    top: 60px;
    left: 10px;
    right: 10px;
    bottom: 0px;
    overflow-y: auto;
  }
  .main-func, .list-button{
    float: left;
    width: 120px;
    height: 50px;
    line-height: 50px;
    text-align: center;
    color: #fff;
    font-size: 16px;
    font-weight: bold;
    cursor: pointer;
    user-select: none;
  }
  .main-func:hover, .list-button:hover{
    background: url(assets/themes/default/images/background-hover.png) repeat-x bottom
  }
  .list-button{
    width: 80px;
    font-size: 14px;
  }
  .list-button i {
    color: #fff;
  }
  .drop-down{
    position: absolute;
    width: 190px;
    background: #fff;
    left: 75px;
    top: 40px;
    border-radius: 5px;
    border: solid 1px #D7DAE1;
    box-shadow: 0px 0px 15px #C9C4E0;
    padding: 0;
    z-index: 10;
    transition: opacity 0.5s;
  }
  .drop-down li{
    list-style: none;
    padding: 3px 10px;
    font-weight: bold;
    height: 20px;
    line-height: 20px;
    cursor: pointer;
  }
  .drop-down li:hover{
    background-color: #F1F2FF;
    color: #7E55F8;
  }
  .fade-enter-active, .fade-leave-active {
    transition: all 0.5s ease
  }
  .fade-enter, .fade-leave-active{
    opacity: 0
  }
  .button{
    margin-right: 5px;
  }
</style>
