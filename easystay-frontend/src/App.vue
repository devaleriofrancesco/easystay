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
