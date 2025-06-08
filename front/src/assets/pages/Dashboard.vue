<script setup lang="ts">
import { useAuthStore } from '../store/auth'

const auth = useAuthStore()
</script>

<template>
  <div class="p-6">
    <h1>Bord</h1>
    <h3 class="text-2xl font-bold">Bienvenue, {{ auth.user?.firstname || 'Email non disponible' }}</h3>
    <p>Rôle : {{ auth.user?.name || 'Role non disponible' }}</p>
  </div>
</template>

<style scoped>
.btn-primary {
  background: #ef4444;
  color: white;
  padding: 8px 12px;
  border-radius: 8px;
  font-weight: bold;
}
</style>
