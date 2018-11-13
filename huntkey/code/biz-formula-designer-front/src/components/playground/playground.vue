<template>
  <!--  -->
  <div class="playground">
  啊发顺丰
  啊发顺丰
    <div class="formula-editor formula-display-div" style="margin-top:6px;" ref="editor"
      contenteditable
    >
    <div data-v-e9d30628="" contenteditable="true" class="formula-editor formula-display-div" style="margin-top: 6px;">   <div class="formula-item formula-item-prop" data-type="prop" data-value="6f8e394866e211e7b2e4005056bc4879_51525c22677911e7b2e4005056bc4879_dech001" data-return-type="int" data-label="this.委托时限" contenteditable="false">this.委托时限</div><div class="formula-item formula-item-prop" contenteditable="false" data-type="prop" data-value="6f8e394866e211e7b2e4005056bc4879_51530923677911e7b2e4005056bc4879_dech002" data-label="this.委托人" data-return-type="object">this.委托人</div>   <div class="formula-item formula-item-prop" contenteditable="false" data-type="prop" data-value="6f8e394866e211e7b2e4005056bc4879_51530923677911e7b2e4005056bc4879_dech002" data-label="this.委托人" data-return-type="object">this.委托人</div><div class="formula-item formula-item-prop" contenteditable="false" data-type="prop" data-value="6f8e394866e211e7b2e4005056bc4879_51530923677911e7b2e4005056bc4879_dech002" data-label="this.委托人" data-return-type="object">this.委托人</div></div>
    </div><!-- /.formula-editor -->

    <div class="formula-editor formula-display-div" style="margin-top:6px;" ref="editor2"
      >
      {{JSON.stringify(this.currentSelection)}}
    </div>
  </div><!-- /.playground-->
</template>
<script>
  export default {
    name: 'playground',
    props: ['index', 'forumlarItem'],
    data () {
      return {
        currentSelection: null
      }
    },
    methods: {
    },
    computed: {
    }
  }
</script>
<style>
.formula-editor {
  border: 1px solid red;
  caret-color: red;
}

.formula-item {
  /*padding:10px;
  margin:10px;*/
}
</style>
