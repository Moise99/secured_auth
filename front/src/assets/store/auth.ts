import { defineStore } from 'pinia'
import api from '../../lib/axios' 

export const useAuthStore = defineStore('auth', {
  state: () => ({
    token: '',
    user: null as Record<string, any> | null,
  }),
  actions: {
    setToken(token: string) {
      this.token = token
      localStorage.setItem('token', token) // stocker localement pour persistance
    },
    getToken() {
      return this.token || localStorage.getItem('token') || ''
    },
    isAuthenticated() {
      return !!this.getToken()
    },
    logout() {
      this.token = ''
      this.user = null
      localStorage.removeItem('token')
      window.location.href = '/' // rediriger vers le home
    },
    async register(payload: { firstname: string; lastname: string; email: string; password: string }) {
      const response = await api.post('/api/auth/register', payload)
      if (response.data.token) {
        this.setToken(response.data.token)
        this.user = response.data.userResponse
      }
      return response
    },
    async login(payload: { email: string; password: string} ) {
      const response = await api.post('/api/auth/login', payload)
      if (response.data.token) {
        this.setToken(response.data.token)
        this.user = response.data.userResponse
      }
      return response
      
    },
    async fetchUser() {
      const token = this.getToken()
      if (token) {
        try {
          const responseData = await api.get('/api/auth/me', {
            headers: { Authorization: `Bearer ${token}` },
          })
          this.user = responseData.data.user
          console.log("Utilisateur récupéré:", this.user )
        } catch (error) {
          console.error("Erreur lors de la récupération de l'utilisateur:", error)
          this.logout() // Déconnexion si le token est invalide
        }
      }
    },
  }
})
