import { createApp } from 'vue'
import { createPinia } from 'pinia'
import axios from './lib/axios'
import { useAuthStore } from './assets/store/auth'
import './style.css'
import App from './App.vue'
import router from './assets/router'


const app = createApp(App)
const pinia = createPinia()

app.use(pinia) 
app.use(router)

// Initialisation l'utilisateur connecté
const authStore = useAuthStore()
authStore.fetchUser().then(() => {
    app.mount('#app')
})

app.config.globalProperties.$axiosConfig = axios


