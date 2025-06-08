<script setup lang="ts">
import { ref } from 'vue'
import { useAuthStore } from '../../store/auth'
import router from '../../router'

const auth = useAuthStore()

const email = ref('')
const password = ref('')

const handleSubmit = async () => {
  try {
    const response = await auth.login({
      email: email.value, 
      password: password.value
    }) 
    
    if (response.data.token) {
      // Redirection vers le tableau de bord après la connexion réussie
      router.push('/dashboard')
    }
  } catch (error) {
    if (
      typeof error === 'object' &&
      error !== null &&
      'response' in error &&
      typeof (error as any).response === 'object' &&
      (error as any).response !== null &&
      'status' in (error as any).response &&
      (error as any).response.status === 401
    ) {
      // Gérer l'erreur d'authentification
      console.error('Identifiants incorrects')
    } else {
      console.error('Erreur lors de la connexion :', error)
    }
  }
  
}
</script>

<template>
  <div class="min-h-screen flex items-center justify-center bg-gray-100">
    <form @submit.prevent="handleSubmit" class="bg-white p-8 rounded-lg shadow-md w-full max-w-md space-y-4">
      <h2 class="text-2xl font-bold text-center text-gray-800">Connexion</h2>

      <input v-model="email" type="email" placeholder="Email" required
        class="w-full px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500" />

      <input v-model="password" type="password" placeholder="Mot de passe" required
        class="w-full px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500" />

      <button type="submit"
        class="w-full bg-blue-600 text-red font-semibold py-2 rounded-lg hover:bg-blue-700 transition">
        Se connecter
      </button>
    </form>
  </div>
</template>
