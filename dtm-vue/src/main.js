/*
 * @Description: 
 * @Author: Rabbiter
 * @Date: 2023-03-05 20:17:11
 */
import Vue from 'vue'
import App from './App.vue'
import router from './router'
import './permission'
import permission from './directives/permission'
import './api/initialize'
import ElementUI from 'element-ui';
import './assets/font/iconfont.css'
import './assets/font2/iconfont.css'
import 'element-ui/lib/theme-chalk/index.css';
import '@/assets/icons/iconfont.css'
Vue.config.productionTip = false
Vue.use(permission)
Vue.use(ElementUI)
new Vue({
    router,
    render: h => h(App)
}).$mount('#app')
