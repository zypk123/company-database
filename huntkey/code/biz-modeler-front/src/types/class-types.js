export const classTypes = {
  // 属性管理
  MANAGER_ATTR: 'attr',
  // 方法管理
  MANAGER_METHOD: 'method',
  // 常量管理
  MANAGER_CONST: 'const',
  // 基础数据管理
  MANAGER_BASIC_INFO: 'basic_info',
  // 索引维护管理
  MANAGER_INDEX_MAINTENANCE: 'index_maintenance',
  // 编辑模式 新增
  EDIT_MODE_CREATE: 'create',
  // 编辑模式 修改
  EDIT_MODE_UPDATE: 'update',
  // 编辑模式 复制
  EDIT_MODE_COPY: 'copy',
  // -----vuex----------
  // 获得当前类树数据
  GET_CURRENT_CLASS_TREE_DATA: 'class/getCurrentClassTreeData',
  // 设置当前类树数据
  SET_CURRENT_CLASS_TREE_DATA: 'class/setCurrentClassTreeData',
  // 设置当前的Class
  SET_CURRENT_CLASS: 'class/setCurrentClass',
  // 添加子节点
  ADD_CHILD_OF_PARENT: 'class/addChildOfParent',
  // 上移
  UP: 'class/up',
  // 下移
  DOWN: 'class/down'
}
