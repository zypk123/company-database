<script>
  export default {
    name: 'dialog-component',
    props: ['component', 'props', 'events'],
    render (h) {
      return h(this.component, {
        props: this.props,
        on: this.events
      })
    }
  }
</script>
