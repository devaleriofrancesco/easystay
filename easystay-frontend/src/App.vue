<template>
  <ApplicationHeader />
  <RouterView />
  <ApplicationFooter />
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

.btn-cta {
  background-color: #35565f;
  color: white;
  border: none;
  border-radius: 5px;
  padding: 10px 20px;
  font-size: 20px;
}
</style>
