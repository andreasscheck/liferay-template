import { VueComponent, Prop } from 'vue-typescript';

declare var jQuery

@VueComponent({
    template: require('./main.html'),
    style: require('./main.scss'),
    components: {
    }
})

export class MainComponent {
  created () {
  }
}
