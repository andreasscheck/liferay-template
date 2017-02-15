"use strict";
var Vue = require("vue");
require("./vendor");
require('./main.scss');
var main_1 = require("./views/main");
window.Vue = window.Vue || Vue;
window.Vue.component('app-content', main_1.MainComponent);
//# sourceMappingURL=main.js.map