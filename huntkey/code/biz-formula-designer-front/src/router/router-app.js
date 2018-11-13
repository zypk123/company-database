import MockEntry from '@/components/MockEntry'
import PropFormulaDesigner from '@/components/formula-designer/PropFormulaDesigner'
import PropLimitConfig from '@/components/prop-limit-config/PropLimitConfig'
import RelConditionConfig from '@/components/rel-condition-config/RelConditionConfig'
import RelTirggerConditionConfig from '@/components/rel-trigger-condition-config/RelTriggerConditionConfig'
import MethodTirggerConditionConfig from '@/components/method-trigger-condition-config/MethodTriggerConditionConfig'
import ProcessFuncConfig from '@/components/process-func-config/ProcessFuncConfig'
import AuditConditionConfig from '@/components/audit-condition-config/AuditConditionConfig'
import RegulatoryTreeConditionConfig from '@/components/regulatory-tree-condition-config/RegulatoryTreeConditionConfig'
import PpiFormulaDesigner from '@/components/ppi-formula/PpiFormulaDesigner'
import ObjectPreFormulaDesigner from '@/components/object-presentation-formula/ObjectPreFormulaDesigner'

import VarManage from '@/components/var-manage/VarManage'
import FuncManage from '@/components/func-manage/FuncManage'
import FuncDescLanguage from '@/components/func-desc-language/FuncDescLanguage'

const routes = [
  {
    path: '/',
    redirect: 'mock-entry'
  },
  {
    path: '/mock-entry', // 模拟公式设计器,等的入口
    component: MockEntry,
    meta: {
      showMenu: true
    }
  },
  {
    path: '/formula-designer/prop-formula/:classId/:propId',
    name: 'propFormulaDesignerNew',
    component: PropFormulaDesigner
  },
  {
    path: '/ppi-formula/:ppiId/:ppiName/:cycle',
    name: 'ppiFormulaDesigner',
    component: PpiFormulaDesigner
  },
  {
    path: '/ppi-formula/:ppiId/:ppiName/:cycle/:varId',
    name: 'ppiFormulaDesignerEdit',
    component: PpiFormulaDesigner
  },
  {
    path: '/object-pre-formula/:classId',
    name: 'objectPreFormulaDesigner',
    component: ObjectPreFormulaDesigner
  },
  {
    path: '/formula-designer/prop-formula/:classId/:propId/:varId',
    name: 'propFormulaDesignerEdit',
    component: PropFormulaDesigner
  },
  {
    path: '/prop-limit-config/:propId',
    name: 'propLimitConfig',
    component: PropLimitConfig
  },
  {
    path: '/rel-condition-config/:propId1/:propId2',
    name: 'relConditionConfig',
    component: RelConditionConfig
  },
  {
    path: '/process-func-config/:classId',
    name: 'processFuncConfig',
    component: ProcessFuncConfig
  },
  {
    path: '/process-func-config/:classId/:prplId',
    name: 'processFuncConfig',
    component: ProcessFuncConfig
  },
  {
    path: '/regulatory-tree-condition-config/:classId',
    name: 'regulatoryTreeConditionConfig',
    component: RegulatoryTreeConditionConfig
  },
  {
    path: '/regulatory-tree-condition-config/:classId/:prplId',
    name: 'regulatoryTreeConditionConfig',
    component: RegulatoryTreeConditionConfig
  },
  {
    path: '/audit-condition-config/:classId',
    name: 'auditConditionConfig',
    component: AuditConditionConfig
  },
  {
    path: '/audit-condition-config/:classId/:alternateField4',
    name: 'auditConditionConfigEdit',
    component: AuditConditionConfig
  },
  {
    path: '/rel-tirgger-condition-config/:propId/:prplId',
    name: 'relTirggerConditionConfig',
    component: RelTirggerConditionConfig
  },
  {
    path: '/rel-tirgger-condition-config/:propId',
    name: 'relTirggerConditionConfig',
    component: RelTirggerConditionConfig
  },
  {
    path: '/method-tirgger-condition-config/:methodId/:classId',
    name: 'methodTirggerConditionConfig',
    component: MethodTirggerConditionConfig
  },
  {
    path: '/var-manage',
    name: 'varManage',
    component: VarManage,
    meta: {
      showMenu: true
    }
  },
  {
    path: '/func-manage',
    name: 'funcManage',
    component: FuncManage,
    meta: {
      showMenu: true
    }
  },
  {
    path: '/func-desc-language',
    name: 'funcDescLanguage',
    component: FuncDescLanguage,
    meta: {
      showMenu: true
    }
  }

]
export default routes
