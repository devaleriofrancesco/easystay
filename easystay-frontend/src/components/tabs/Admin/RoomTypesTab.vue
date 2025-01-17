<template>
  <div
    :class="`tab-pane fade show ${index == 0 ? 'active' : ''}`"
    :id="tab.id"
    role="tabpanel"
    :aria-labelledby="`${tab.id}-list`"
  >
    <div>
      <button
        data-bs-toggle="modal"
        :data-bs-target="`#editRoomTypeModal-0`"
        class="mb-3 btn btn-cta-mini"
      >
        Aggiungi nuovo tipo stanza
      </button>

      <div v-if="newRoomType">
        <RoomTypeModal ref="newRoomTypeRef" :roomType="newRoomType" :key="newModalComponentKey" />
      </div>

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
            <button @click.prevent="deleteRoomType(roomType)" class="btn btn-danger">
              Elimina
            </button>
          </div>
        </div>

        <!-- Modal -->
        <RoomTypeModal :roomType="roomType" :key="roomType.id" />
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { nextTick, onMounted, ref } from 'vue'
import type { RoomType } from '@/interfaces/roomtype.ts'
import { deleteRoomType, getNewRoomType, getRoomTypes } from '@/services/roomTypeService.ts'
import RoomTypeModal from '@/components/modals/RoomTypeModal.vue'
import showToast from '@/services/toaster.ts'
import { AxiosError } from 'axios'
import { emitter } from '@/services/mitt.ts'

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
    const newRoomType = ref<RoomType>()

    const retrieveNewRoomType = async () => {
      newRoomType.value = await getNewRoomType()
    }

    const getAllRoomTypes = async () => {
      roomTypes.value = await getRoomTypes()
    }

    emitter.on('room-type-saved', () => {
      getAllRoomTypes()
    })

    // when a service is deleted, we need to refresh the list
    emitter.on('service-deleted', () => {
      roomTypes.value = []
      nextTick(() => {
        getAllRoomTypes()
      })
    })

    // when a service is saved, we need to refresh the list
    emitter.on('service-saved', () => {
      roomTypes.value = []
      nextTick(() => {
        getAllRoomTypes()
      })
    })

    onMounted(getAllRoomTypes)
    onMounted(retrieveNewRoomType)
    return { roomTypes, getAllRoomTypes, newRoomType, retrieveNewRoomType }
  },
  data() {
    return {
      newModalComponentKey: 1,
    }
  },
  methods: {
    async deleteRoomType(roomType: RoomType) {
      try {
        await deleteRoomType(roomType)
        await this.getAllRoomTypes()
        showToast('Tipo stanza eliminato correttamente', 'success')
      } catch (error) {
        if (error instanceof AxiosError) {
          showToast(error.response?.data.detail, 'error')
        }
      }
    },
    resetNewModalComponentKey() {
      this.retrieveNewRoomType().then(() => {
        this.newModalComponentKey += 1
      })
    },
  },
  mounted() {
    document.addEventListener('hidden.bs.modal', this.resetNewModalComponentKey)
  },
}
</script>
