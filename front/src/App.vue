<script setup lang="ts">
import { useAuthStore } from './assets/store/auth'

const auth = useAuthStore()
</script>


<template>
  <div>
    <nav>
      <ul>
          <li v-if="auth.isAuthenticated()">
            <router-link to="/dashboard">Bord</router-link>
          </li>
          <li v-if="auth.isAuthenticated()">
           <button @click="auth.logout" class="mt-4 btn-primary">Se déconnecter</button>
          </li>
          <template v-else>
            <li>
              <router-link to="/login">Se connecter</router-link>
            </li>
            <li>
              <router-link to="/register">Créer un compte</router-link>
            </li>
          </template>
          <!-- ...existing code... -->
          <li><router-link to="/">Home</router-link></li>

      </ul>
    </nav>
    <router-view />
  </div>

</template>
