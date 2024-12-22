<template>
  <header>
    <nav class="navbar navbar-expand-lg bg-body-tertiary">
      <div class="container-fluid">
        <a class="navbar-brand" href="#">
          <img
            src="@/assets/images/logo2.png"
            alt="logo"
            height="50"
            class="d-inline-block align-text-top"
          />
        </a>
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
        <div class="collapse navbar-collapse justify-content-center" id="navbarNav">
          <ul class="navbar-nav gap-2">
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

<style lang="scss" scoped>

.nav-item {
  border: 2px solid #35565f;
  border-radius: 5px;

  a {
    font-size: 20px;
    color: #35565f;
  }
}

li .router-link-active {
  background-color: #35565f;
  color: white;
}

</style>
