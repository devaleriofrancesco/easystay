<template>
  <div
    :class="`tab-pane fade show ${index == 0 ? 'active' : ''}`"
    :id="tab.id"
    role="tabpanel"
    :aria-labelledby="`${tab.id}-list`"
  >
    <p>componente {{ tab.name }}</p>
  </div>
</template>

<script lang="ts">
import type { Tab } from '@/interfaces/tab.ts'

export default {
  name: 'ProfileTab',
  props: {
    tab: {
      type: {} as Tab,
      required: true,
    },
    index: {
      type: Number,
      required: true,
    },
  },
}
</script>
