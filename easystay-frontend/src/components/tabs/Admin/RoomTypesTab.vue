<template>
  <div
    :class="`tab-pane fade show ${index == 0 ? 'active' : ''}`"
    :id="tab.id"
    role="tabpanel"
    :aria-labelledby="`${tab.id}-list`"
  >
    <div>
      <div v-for="(roomType, index) in roomTypes" :key="index" class="card w-75 mb-3">
        <div class="card-body">
          <h5 class="card-title">{{ roomType.nome }}</h5>
          <p class="card-text">
            {{ roomType.descrizione }}
          </p>
          <div class="d-flex justify-content-between">
            <button
              class="btn btn-cta-mini"
              data-bs-toggle="modal"
              :data-bs-target="`#editRoomTypeModal-${roomType.id}`"
            >
              Modifica
            </button>
            <button class="btn btn-danger">Elimina</button>
          </div>
        </div>

        <!-- Modal -->
        <RoomTypeModal :roomType="roomType" :key="roomType.id" />
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { onMounted, ref } from 'vue'
import type { RoomType } from '@/interfaces/roomtype.ts'
import { getRoomTypes } from '@/services/roomTypeService.ts'
import RoomTypeModal from '@/components/modals/RoomTypeModal.vue'

export default {
  name: 'RoomTypesTab',
  components: { RoomTypeModal },
  props: {
    tab: {
      type: Object,
      required: true,
    },
    index: {
      type: Number,
      required: true,
    },
  },
  setup() {
    const roomTypes = ref<RoomType[]>([])

    const getAllRoomTypes = async () => {
      roomTypes.value = await getRoomTypes()
    }

    onMounted(getAllRoomTypes)
    return { roomTypes, getAllRoomTypes }
  },
}
</script>
