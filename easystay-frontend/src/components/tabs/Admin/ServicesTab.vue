<template>
  <div
    :class="`tab-pane fade show ${index == 0 ? 'active' : ''}`"
    :id="tab.id"
    role="tabpanel"
    :aria-labelledby="`${tab.id}-list`"
  >
    <div>
      <p>rwesr</p>
    </div>
  </div>
</template>

<script lang="ts">
import type { Tab } from '@/interfaces/tab.ts'

export default {
  name: 'ServicesTab',
  props: {
    tab: {
      type: Object as () => Tab,
      required: true,
    },
    index: {
      type: Number,
      required: true,
    },
  },
}
</script>
