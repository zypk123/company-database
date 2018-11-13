<template>
  <div class="formula-suggestion" v-bind:style="{ top: posTop + 'px', left: posLeft+ 'px' }">
    <div class="formula-suggestion__wrapper">
      <ul class="formula-suggestion__list" style="position: relative;">
          <li v-for="(item,index) in searchResult"
            :class="{'formula-item-func': item.itemType==='func','formula-item-var':item.itemType==='var','focus': suggestFocusIndex === index}"
            :title="item.itemLabel + '-' + item.returnType"
            @click="listItemClickEvent(item)"
            >
            {{item.itemLabel}}<span class="item-return-type">{{item.returnType}}</span>
          </li>
      </ul>
    </div><!--/.formula-suggestion__wrapper-->
  </div><!--/.formula-suggestion-->
</template>

<script>
export default {
  name: 'formulaSuggestion',
  props: ['posTop', 'posLeft', 'searchResult', 'suggestFocusIndex'],
  data () {
    return {
    }
  },
  methods: {
    listItemClickEvent (item) {
      this.$emit('selectSuggest', item)
    }
  },
  computed: {
  }
}
</script>

<style scoped>
.formula-suggestion {
  position:absolute;
  left:100px;
  top: 200px;
  background-color: #fff;
/*  box-shadow: 0px 0px 15px #F6F6FB;*/
}
.formula-suggestion .formula-suggestion__wrapper {
  max-height: 280px;
  overflow: auto;
  border: 1px solid #d1dbe5;
  padding: 6px 0;
  border-radius: 2px;
  box-sizing: border-box;
}
.formula-suggestion .formula-suggestion__list {
  margin: 0;
  padding: 0;
}

.formula-suggestion .formula-suggestion__list li {
  list-style: none;
  line-height: 36px;
  padding: 0 10px;
  margin: 0;
  cursor: pointer;
  font-size: 14px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
</style>
