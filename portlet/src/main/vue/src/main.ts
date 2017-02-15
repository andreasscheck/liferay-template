import * as Vue from 'vue';

import './vendor' //bootstrap
require('./main.scss'); //global css

import { MainComponent } from './views/main';

declare var window;

window.Vue = window.Vue || Vue;
window.Vue.component('app-content', MainComponent);
