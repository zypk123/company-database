// import * as types from '../mutation-types'

const state = {
  varStatusDict: [
    {val: 'prohibit', label: '停用'},
    {val: 'inusing', label: '启用'}
  ],
  dataTypeDict: [
    {val: 'text', label: '文本'},
    {val: 'date', label: '日期'},
    {val: 'int', label: '整数'},
    {val: 'varchar', label: '字符串'},
    {val: 'decimal', label: '浮点数'},
    {val: 'number', label: '数字型'},
    {val: 'object', label: '对象'},
    {val: 'assemble', label: '属性集'},
    {val: 'instantiate', label: '对象实例'},
    {val: 'measurement', label: '计量单位'},
    // {val: 'normalObj', label: '普通'},
    {val: 'objectLink', label: '对象链接'},
    {val: 'enum', label: '枚举'},
    {val: 'convolution', label: '卷积属性'},
    {val: 'numberArray', label: '数值数组型'},
    {val: 'any', label: '任何'}
  ],
  cndtOperatorDict: [
    {val: '<', label: '小于'},
    {val: '>', label: '大于'},
    {val: '=', label: '等于'},
    {val: '<=', label: '小于等于'},
    {val: '>=', label: '大于等于'},
    {val: '!=', label: '不等于'},
    {val: 'like', label: '包含'},
    {val: 'not like', label: '不包含'}
  ],
  cndrOperatorDict: [
    {val: '<', label: '小于'},
    {val: '>', label: '大于'},
    {val: '=', label: '等于'},
    {val: '<=', label: '小于等于'},
    {val: '>=', label: '大于等于'},
    {val: '!=', label: '不等于'},
    {val: 'like', label: '包含'},
    {val: 'not like', label: '不包含'},
    {val: 'llike', label: '左包含'},
    {val: 'rlike', label: '右包含'}
  ],
  cndtValueTypeDict4RelClass: [
    {val: 'class', label: '类'},
    {val: 'const', label: '常量'},
    {val: 'variable', label: '变量'}
  ],
  cndtValueTypeDict4PropLimit: [
    {val: 'const', label: '常量'},
    {val: 'variable', label: '变量'}
  ],
  cndtValueTypeDict4RelCndt: [
    {val: 'class', label: '类'},
    {val: 'const', label: '常量'},
    {val: 'variable', label: '变量'}
  ],
  typeFlagDict: [
    {val: 1, label: '属性'}
  ],
  methodTypeFlagDict: [
    {val: 'assignPost', label: '指定岗位'},
    {val: 'assignDepartment', label: '指定部门'},
    {val: 'postOffice', label: '制单岗位'},
    {val: 'appointedPost', label: '任职岗位'},
    {val: 'appointedDepartment', label: '任职部门'}
  ],
  classTypeFlagDict: [
    {val: 1, label: '类'},
    {val: 2, label: '相关类'}
  ],
  funCatagoryDict: [
    {val: '0', label: '数字函数'},
    {val: '1', label: '文本函数'},
    {val: '2', label: '时间函数'},
    {val: '3', label: '逻辑函数'},
    {val: '4', label: '角色函数'},
    {val: '5', label: '自定义函数'}
  ],
  funStatusDict: [
    {val: 'prohibit', label: '停用'},
    {val: 'inusing', label: '启用'}
  ],
  fnccStateDict: [
    {val: '0', label: '有效'},
    {val: '1', label: '无效'}
  ],
  otherFuncList: [
    {
      'funName': '!',
      'funClassify': 'logic',
      'classifyDesc': '逻辑函数',
      'funDesc': '',
      'funClassifyOrder': 0,
      'funType': 'Boolean',
      'funOrder': 0,
      'paramTypes': [{
        'seq': 1,
        'paramOrderType': 'Sequence',
        'option': 'NonOption',
        'types': [
          'Any'
        ]
      }
      ],
      'behaivorOrder': 0
    },
    {
      'funName': '+',
      'funClassify': 'math',
      'classifyDesc': '数学函数',
      'funDesc': '',
      'funClassifyOrder': 0,
      'funType': 'Number',
      'funOrder': 0,
      'paramTypes': [{
        'seq': 1,
        'paramOrderType': 'Sequence',
        'option': 'NonOption',
        'types': [
          'Number'
        ]
      },
      {
        'seq': 2,
        'paramOrderType': 'Sequence',
        'option': 'NonOption',
        'types': [
          'Number'
        ]
      }
      ],
      'behaivorOrder': 0
    },
    {
      'funName': '-',
      'funClassify': 'math',
      'classifyDesc': '数学函数',
      'funDesc': '',
      'funClassifyOrder': 0,
      'funType': 'Number',
      'funOrder': 0,
      'paramTypes': [{
        'seq': 1,
        'paramOrderType': 'Sequence',
        'option': 'NonOption',
        'types': [
          'Number'
        ]
      },
      {
        'seq': 2,
        'paramOrderType': 'Sequence',
        'option': 'NonOption',
        'types': [
          'Number'
        ]
      }
      ],
      'behaivorOrder': 0
    },
    {
      'funName': '*',
      'funClassify': 'math',
      'classifyDesc': '数学函数',
      'funDesc': '',
      'funClassifyOrder': 0,
      'funType': 'Number',
      'funOrder': 0,
      'paramTypes': [{
        'seq': 1,
        'paramOrderType': 'Sequence',
        'option': 'NonOption',
        'types': [
          'Number'
        ]
      },
      {
        'seq': 2,
        'paramOrderType': 'Sequence',
        'option': 'NonOption',
        'types': [
          'Number'
        ]
      }
      ],
      'behaivorOrder': 0
    },
    {
      'funName': '/',
      'funClassify': 'math',
      'classifyDesc': '数学函数',
      'funDesc': '',
      'funClassifyOrder': 0,
      'funType': 'Number',
      'funOrder': 0,
      'paramTypes': [{
        'seq': 1,
        'paramOrderType': 'Sequence',
        'option': 'NonOption',
        'types': [
          'Number'
        ]
      },
      {
        'seq': 2,
        'paramOrderType': 'Sequence',
        'option': 'NonOption',
        'types': [
          'Number'
        ]
      }
      ],
      'behaivorOrder': 0
    },
    {
      'funName': '%',
      'funClassify': 'math',
      'classifyDesc': '数学函数',
      'funDesc': '',
      'funClassifyOrder': 0,
      'funType': 'Number',
      'funOrder': 0,
      'paramTypes': [{
        'seq': 1,
        'paramOrderType': 'Sequence',
        'option': 'NonOption',
        'types': [
          'Number'
        ]
      },
      {
        'seq': 2,
        'paramOrderType': 'Sequence',
        'option': 'NonOption',
        'types': [
          'Number'
        ]
      }
      ],
      'behaivorOrder': 0
    },
    {
      'funName': '>',
      'funClassify': 'logic',
      'classifyDesc': '逻辑函数',
      'funDesc': '',
      'funClassifyOrder': 0,
      'funType': 'Boolean',
      'funOrder': 0,
      'paramTypes': [{
        'seq': 1,
        'paramOrderType': 'Sequence',
        'option': 'NonOption',
        'types': [
          'Any'
        ]
      },
      {
        'seq': 2,
        'paramOrderType': 'Sequence',
        'option': 'NonOption',
        'types': [
          'Any'
        ]
      }
      ],
      'behaivorOrder': 0
    },
    {
      'funName': '<',
      'funClassify': 'logic',
      'classifyDesc': '逻辑函数',
      'funDesc': '',
      'funClassifyOrder': 0,
      'funType': 'Boolean',
      'funOrder': 0,
      'paramTypes': [{
        'seq': 1,
        'paramOrderType': 'Sequence',
        'option': 'NonOption',
        'types': [
          'Any'
        ]
      },
      {
        'seq': 2,
        'paramOrderType': 'Sequence',
        'option': 'NonOption',
        'types': [
          'Any'
        ]
      }
      ],
      'behaivorOrder': 0
    },
    {
      'funName': '>=',
      'funClassify': 'logic',
      'classifyDesc': '逻辑函数',
      'funDesc': '',
      'funClassifyOrder': 0,
      'funType': 'Boolean',
      'funOrder': 0,
      'paramTypes': [{
        'seq': 1,
        'paramOrderType': 'Sequence',
        'option': 'NonOption',
        'types': [
          'Any'
        ]
      },
      {
        'seq': 2,
        'paramOrderType': 'Sequence',
        'option': 'NonOption',
        'types': [
          'Any'
        ]
      }
      ],
      'behaivorOrder': 0
    },
    {
      'funName': '<=',
      'funClassify': 'logic',
      'classifyDesc': '逻辑函数',
      'funDesc': '',
      'funClassifyOrder': 0,
      'funType': 'Boolean',
      'funOrder': 0,
      'paramTypes': [{
        'seq': 1,
        'paramOrderType': 'Sequence',
        'option': 'NonOption',
        'types': [
          'Any'
        ]
      },
      {
        'seq': 2,
        'paramOrderType': 'Sequence',
        'option': 'NonOption',
        'types': [
          'Any'
        ]
      }
      ],
      'behaivorOrder': 0
    },
    {
      'funName': '==',
      'funClassify': 'logic',
      'classifyDesc': '逻辑函数',
      'funDesc': '',
      'funClassifyOrder': 0,
      'funType': 'Boolean',
      'funOrder': 0,
      'paramTypes': [{
        'seq': 1,
        'paramOrderType': 'Sequence',
        'option': 'NonOption',
        'types': [
          'Any'
        ]
      },
      {
        'seq': 2,
        'paramOrderType': 'Sequence',
        'option': 'NonOption',
        'types': [
          'Any'
        ]
      }
      ],
      'behaivorOrder': 0
    },
    {
      'funName': '!=',
      'funClassify': 'logic',
      'classifyDesc': '逻辑函数',
      'funDesc': '',
      'funClassifyOrder': 0,
      'funType': 'Boolean',
      'funOrder': 0,
      'paramTypes': [{
        'seq': 1,
        'paramOrderType': 'Sequence',
        'option': 'NonOption',
        'types': [
          'Any'
        ]
      },
      {
        'seq': 2,
        'paramOrderType': 'Sequence',
        'option': 'NonOption',
        'types': [
          'Any'
        ]
      }
      ],
      'behaivorOrder': 0
    },
    {
      'funName': '||',
      'funClassify': 'logic',
      'classifyDesc': '逻辑函数',
      'funDesc': '',
      'funClassifyOrder': 0,
      'funType': 'Boolean',
      'funOrder': 0,
      'paramTypes': [{
        'seq': 1,
        'paramOrderType': 'Sequence',
        'option': 'NonOption',
        'types': [
          'Boolean'
        ]
      },
      {
        'seq': 2,
        'paramOrderType': 'Sequence',
        'option': 'NonOption',
        'types': [
          'Boolean'
        ]
      }
      ],
      'behaivorOrder': 0
    },
    {
      'funName': '&&',
      'funClassify': 'logic',
      'classifyDesc': '逻辑函数',
      'funDesc': '',
      'funClassifyOrder': 0,
      'funType': 'Boolean',
      'funOrder': 0,
      'paramTypes': [{
        'seq': 1,
        'paramOrderType': 'Sequence',
        'option': 'NonOption',
        'types': [
          'Boolean'
        ]
      },
      {
        'seq': 2,
        'paramOrderType': 'Sequence',
        'option': 'NonOption',
        'types': [
          'Boolean'
        ]
      }
      ],
      'behaivorOrder': 0
    },
    {
      'funName': '(',
      'funClassify': 'store',
      'classifyDesc': '存储函数',
      'funDesc': '',
      'funClassifyOrder': 0,
      'funType': 'Any',
      'funOrder': 0,
      'paramTypes': [{
        'seq': 1,
        'paramOrderType': 'Sequence',
        'option': 'NonOption',
        'types': [
          'Any'
        ]
      }
      ],
      'behaivorOrder': 0
    },
    {
      'funName': ',',
      'funClassify': 'store',
      'classifyDesc': '存储函数',
      'funDesc': '',
      'funClassifyOrder': 0,
      'funType': 'Any',
      'funOrder': 0,
      'paramTypes': [{
        'seq': 1,
        'paramOrderType': 'Sequence',
        'option': 'NonOption',
        'types': [
          'Any'
        ]
      }
      ],
      'behaivorOrder': 0
    }
  ]
}

// getters
const getters = {
  getVarStatusDict: state => state.varStatusDict,
  getDataTypeDict: state => state.dataTypeDict,
  getCndtOperatorDict: state => state.cndtOperatorDict,
  getCndtValueTypeDict4RelClass: state => state.cndtValueTypeDict4RelClass,
  getCndtValueTypeDict4PropLimit: state => state.cndtValueTypeDict4PropLimit,
  getCndtValueTypeDict4RelCndt: state => state.cndtValueTypeDict4RelCndt,
  getFunCatagoryDict: state => state.funCatagoryDict,
  getFunStatusDict: state => state.funStatusDict,
  getFnccStateDict: state => state.fnccStateDict,
  getOtherFuncList: state => state.otherFuncList,
  getTypeFlagDict: state => state.typeFlagDict,
  getClassTypeFlagDict: state => state.classTypeFlagDict,
  getMethodTypeFlagDict: state => state.methodTypeFlagDict,
  getCndrOperatorDict: state => state.cndrOperatorDict
}

// actions
const actions = {
}

// mutations
const mutations = {
}

export default {
  state,
  getters,
  actions,
  mutations
}
