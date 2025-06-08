<script setup lang="ts">
import { ref } from 'vue'
import { useAuthStore } from '../../store/auth'
import router from '../../router'

const auth = useAuthStore()

const firstname = ref('')
const lastname = ref('')
const email = ref('')
const password = ref('')

const handleSubmit = async () => {
  try {
    const response = await auth.register({
      firstname: firstname.value,
      lastname: lastname.value,
      email: email.value,
      password: password.value
    })
    if (response.data.token) {
      // Redirection ou message de succès
       router.push('/dashboard') 
      // Vous pouvez rediriger l'utilisateur ou afficher un message de succès ici
    }
  } catch (error) {
    console.error('Erreur lors de l’inscription :', error)
  }
}
</script>

<template>
  <div class="min-h-screen flex items-center justify-center bg-gray-100">
    <form @submit.prevent="handleSubmit" class="bg-white p-8 rounded-lg shadow-md w-full max-w-md space-y-4">
      <h2 class="text-2xl font-bold text-center text-gray-800">Créer un compte</h2>

      <input v-model="firstname" type="text" placeholder="Prénom" required
        class="w-full px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500" />

      <input v-model="lastname" type="text" placeholder="Nom" required
        class="w-full px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500" />

      <input v-model="email" type="email" placeholder="Email" required
        class="w-full px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500" />

      <input v-model="password" type="password" placeholder="Mot de passe" required
        class="w-full px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500" />

      <button type="submit"
        class="w-full bg-red-500 text-red font-semibold py-2 rounded-lg hover:bg-blue-700 transition">
        S’inscrire
      </button>
    </form>
  </div>
</template>
