import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap'
import { vMaska } from "maska"

import './assets/styles/app.scss'

const app = createApp(App)

app.use(router)
app.directive("maska", vMaska)

app.mount('#app')
