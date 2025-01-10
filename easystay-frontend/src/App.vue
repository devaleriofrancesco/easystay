<template>
  <div class="page-container">
    <ApplicationHeader />
    <RouterView />
    <ApplicationFooter />
  </div>
</template>

<script lang="ts">
import ApplicationHeader from '@/components/ApplicationHeader.vue'
import { RouterView } from 'vue-router'
import ApplicationFooter from '@/components/ApplicationFooter.vue'

export default {
  components: {
    ApplicationFooter,
    ApplicationHeader,
    RouterView,
  },
}
</script>

<style lang="scss">
.main-container {
  margin-top: 80px;
}

.page-container {
  display: flex;
  min-height: 100vh;
  flex-direction: column;
}

main {
  flex: 1;
}

.btn-cta {
  background-color: #35565f;
  color: white;
  border: none;
  border-radius: 5px;
  padding: 10px 20px;
  font-size: 20px;
  &:hover {
    background-color: #2a4349;
    color: white;
  }
}
</style>
