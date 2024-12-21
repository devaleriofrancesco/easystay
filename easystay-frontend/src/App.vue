
<template>
  <ApplicationHeader />
  <RouterView />
</template>

<script lang="ts">
import ApplicationHeader from '@/components/ApplicationHeader.vue'
import { RouterView } from 'vue-router'
export default {
  components: {
    ApplicationHeader,
    RouterView,
  },
}
</script>
