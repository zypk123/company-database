import ClassView from '@/components/class-manager/ClassView.vue'
import PropertyView from '@/components/property-manager/PropertyView'
import MethodView from '@/components/method-manager/MethodView'
import ConstantView from '@/components/constant-manager/ConstantView'
import BasicInfoView from '@/components/basic-info-manager/BasicInfoView'
import indexMaintenanceView from '@/components/index-maintenance-manager/IndexMaintenance'

export default [{
  name: 'classView',
  path: '/class-view/:id/:mode',
  component: ClassView,
  children: [{
    name: 'propertyView',
    path: 'property/:classId',
    component: PropertyView
  }, {
    name: 'methodView',
    path: 'method/:classId',
    component: MethodView
  }, {
    name: 'constantView',
    path: 'constant/:classId',
    component: ConstantView
  }, {
    name: 'basicInfoView',
    path: 'basic-info/:classId',
    component: BasicInfoView
  }, {
    name: 'indexMaintenanceView',
    path: 'index-maintenance/:classId',
    component: indexMaintenanceView
  }]
}]
