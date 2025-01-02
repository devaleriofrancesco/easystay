<template>
  <div class="container my-5">
    <div class="row">
      <div class="col-4">
        <div class="list-group" id="list-tab" role="tablist">
          <a
            v-for="(tab, index) in tabs"
            :key="tab.id"
            :class="`list-group-item list-group-item-action ${index == 0 ? 'active' : ''}`"
            :id="`${tab.id}-list`"
            data-bs-toggle="list"
            :href="`#${tab.id}`"
            role="tab"
            :aria-controls="tab.id"
            >{{ tab.name }}
          </a>
          <a href="#" @click.prevent="logout" class="list-group-item list-group-item-action"
            >Logout</a
          >
        </div>
      </div>
      <div class="col-8">
        <div class="tab-content" id="nav-tabContent">
          <Component
            v-for="(tab, index) in tabs"
            :key="tab.id"
            :is="tab.component"
            :tab="tab"
            :index="index"
          />
        </div>
      </div>
    </div>
  </div>
</template>
<script lang="ts">
import type { Tab } from '@/interfaces/tab.ts'
import BookingListTab from './tabs/BookingListTab.vue'
import ProfileTab from './tabs/ProfileTab.vue'
import { useUsers } from '@/stores/user.ts'

export default {
  name: 'ProfileTabsWrapper',
  components: {
    BookingListTab,
    ProfileTab,
  },
  props: {
    tabs: {
      type: Array as () => Tab[],
      required: true,
    },
  },
  methods: {
    logout() {
      useUsers().logout()
    },
  },
}
</script>
