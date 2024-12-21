<template>
  <header>
    <nav class="navbar navbar-expand-lg bg-body-tertiary">
      <div class="container-fluid">
        <a class="navbar-brand" href="#">Navbar</a>
        <button
          class="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarNav"
          aria-controls="navbarNav"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
          <ul class="navbar-nav">
            <li v-for="item in menu" :key="item.path" class="nav-item">
              <RouterLink :to="item.path" class="nav-link">{{ item.name }}</RouterLink>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  </header>
</template>

<script lang="ts">
import { RouterLink } from 'vue-router'

import menu from '@/router/menu.json'

export default {
  components: { RouterLink },
  data() {
    return {
      menu: menu,
    }
  },
}
</script>

<style scoped>
 li .router-link-active {
    color: red;
 }
</style>
