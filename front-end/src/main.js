import Vue from "vue";
import App from "./App.vue";
import router from './router'
import VueTheMask from 'vue-the-mask'

Vue.use(VueTheMask)//import mask lib every lib.
Vue.config.productionTip = false;

new Vue({
    router,
    render: h => h(App)
}).$mount("#app");
