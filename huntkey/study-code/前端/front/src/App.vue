<template>
  <div id="app" ><!-- class="debug" -->

    <router-view class="router-view common-vgap"></router-view>
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
      <component :is="item.component" :init-data="item.props"></component>
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
import { mapGetters } from 'vuex'
// import { dialogTypes } from '@/types'
import { APP_TYPES } from '@/types'

export default {
  name: 'app',
  computed: {
    ...mapGetters({
      showMenu: APP_TYPES.APP_GET_SHOW_MENU
    })
  },
  data () {
    return {
      dialogs: []
    }
  },
  created () {
    // 注册全局dialog事件
    // 打开一个dialog, 返回promise, reslvoe的参数是弹框内组件的视图模型
    Vue.prototype.OpenGlobalDialog = options => {
      return this.addDialog(options)
    }
    // 关闭一个dialog
    Vue.prototype.CloseGlobalDialog = name => {
      this.closeDialog(name)
    }
  },
  methods: {
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
          // 定义参数
          props: {},
         // 在子组件中，使用vm.$emit('success'), 调用事件
          // 自定义按钮，默认为空
          buttons: [],
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
          // 事件
          dialogEvents: {
            // 关闭事件
            close: null,
            // 打开事件
            open: null
          },
          // 自定义事件, 默认为空
          customEvent: {}
          // 示例
          // success: () => {
          //  方法体
          // }
        }
        // 设置Name
        dialog.name = name
        // 注入组件
        dialog.component = component
        // 设置按钮
        dialog.buttons = buttons
        // 合并参数
        this.Utils.assign(dialog.options, options)
        // 设置原生事件
        this.Utils.assign(dialog.dialogEvents, dialogEvents)
        // 设置参数
        this.Utils.assign(dialog.props, props)
        // 设置事件
        this.Utils.assign(dialog.customEvent, events)
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
        // 添加到state中
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

<style>

.el-menu--horizontal .el-submenu .el-submenu__title {
    height: 60px;
    line-height: 60px;
    border-bottom: 5px solid transparent;
}

.app_menu .main-menu-item{
  color:#fff;
  font-size: 16px;
  font-weight: bold;
  user-select: none;
}

.app_menu .el-menu-item,
.app_menu .el-submenu__title {
  color:#fff;
}

.el-menu--horizontal .el-submenu>.el-menu {
  border:10px solid #blue;
  background-color:#fff;
}
.el-menu--horizontal .el-submenu .el-menu-item {
  color: #000;
  background-color:#fff;
}



.el-menu--horizontal .el-submenu .el-submenu__icon-arrow {
  color:#fff;
}


.el-menu--horizontal .el-menu-item:hover,
.el-menu--horizontal .el-submenu__title:hover {
  color: #fff;
  background-color:#8054F7;
}

.app_menu .main-menu-item>.el-menu-item:hover,
.app_menu .main-menu-item>.el-menu-item.is-active,
.el-menu--horizontal>.el-menu-item:hover,
.el-menu--horizontal>.el-submenu.is-active .el-submenu__title,
.el-menu--horizontal>.el-submenu:hover .el-submenu__title {
  border-bottom:5px solid #C7BCF8;
}


.el-menu-item.is-active {
  font-style: italic;
}

.el-submenu.is-active .el-submenu__title {
  /*border-bottom-color:blue;*/
  border-bottom:15px solid blue;
}

.main-menu-item:hover,
.el-menu-item:hover{
  /*font-style: italic;*/
}
</style>
