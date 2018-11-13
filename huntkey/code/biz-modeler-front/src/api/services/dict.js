import store from '@/store'
const config = store.state.windowStore.config
import axios from 'axios'

export const dictService = {
  // 根据多个code获取数据字典
  // getDictByCodes (codes) {
  //   return axios.get(config.baseUrl.common + 'dict/code', {
  //     params: {
  //       'codes[]': codes.join(',')
  //     }
  //   })
  // },
  getDictByCodes (codes) {
    return new Promise((resolve, reject) => {
      axios.get(config.baseUrl.modeler + 'codes/code', {
        params: {
          codeTypes: codes.join(',')
        }
      }).then(data => {
        for (var index in data) {
          var item = data[index]
          item.forEach(dict => {
            dict.label = dict.codeName
            dict.value = dict.codeValue
          })
        }
        resolve(data)
      })
    })
  },
  // 根据code获得树
  getDictTreeByCode (codeType) {
    return new Promise((resolve, reject) => {
      axios.get(config.baseUrl.modeler + 'codes/tree', {
        params: {
          codeType
        }
      }).then(data => {
        setLabelAndValue(data)
        resolve(data)
        function setLabelAndValue (list) {
          list.forEach(item => {
            item.label = item.codeName
            item.value = item.codeValue
            if (item.children && item.children.length > 0) {
              setLabelAndValue(item.children)
            }
          })
        }
      })
    })
  }
}
