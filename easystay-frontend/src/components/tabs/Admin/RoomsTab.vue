<template>
  <div
    :class="`tab-pane fade show ${index == 0 ? 'active' : ''}`"
    :id="tab.id"
    role="tabpanel"
    :aria-labelledby="`${tab.id}-list`"
  >
    <div>
      <button @click.prevent="onCreateButtonPressed" type="button" class="btn btn-cta-mini mb-3">
        Aggiungi nuova stanza
      </button>

      <div v-for="room in rooms" :key="room.numeroStanza" class="card mb-3">
        <div class="card-body">
          <h5 class="card-title">Stanza #{{ room.numeroStanza }}</h5>
          <p class="card-text">Tipo stanza: {{ room.tipoStanza.nome }}</p>
          <p class="card-text">Prenotabile: {{ room.prenotabile ? 'Si' : 'No' }}</p>
          <div class="d-flex justify-content-between">
            <a @click.prevent="onEditButtonPressed(room)" class="btn btn-cta-mini">Modifica</a>
            <a @click.prevent="onDeleteButtonPressed(room)" class="btn btn-danger">Elimina</a>
          </div>
        </div>
      </div>

      <!-- Modal -->
      <div v-if="selectedRoom">
        <div
          class="modal fade"
          id="staticBackdrop"
          data-bs-backdrop="static"
          data-bs-keyboard="false"
          tabindex="-1"
          aria-labelledby="staticBackdropLabel"
          aria-hidden="true"
        >
          <div class="modal-dialog">
            <div class="modal-content">
              <form @submit.prevent="saveRoom">
                <div class="modal-header">
                  <h1 class="modal-title fs-5" id="staticBackdropLabel">
                    Modifica stanza {{ selectedRoom.numeroStanza }}
                  </h1>
                  <button
                    type="button"
                    class="btn-close"
                    data-bs-dismiss="modal"
                    aria-label="Close"
                  ></button>
                </div>
                <div class="modal-body">
                  <div class="mb-3" v-if="isNewRoom">
                    <label for="roomNumber" class="form-label">Numero stanza</label>
                    <input
                      type="text"
                      class="form-control"
                      id="roomNumber"
                      v-model="selectedRoom.numeroStanza"
                    />
                  </div>
                  <div class="mb-3">
                    <label for="roomType" class="form-label">Tipo stanza</label>
                    <select class="form-select" id="roomType" v-model="selectedRoom.tipoStanza">
                      <option v-for="roomType in roomTypes" :key="roomType.id" :value="roomType">
                        {{ roomType.nome }}
                      </option>
                    </select>
                  </div>
                  <div class="mb-3 form-check">
                    <input
                      type="checkbox"
                      class="form-check-input"
                      id="bookable"
                      v-model="selectedRoom.prenotabile"
                    />
                    <label class="form-check label" for="bookable">Prenotabile</label>
                  </div>
                </div>
                <div class="modal-footer">
                  <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                    Close
                  </button>
                  <button type="submit" class="btn btn-success">Salva</button>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>

      <!-- Pagination -->
      <div class="d-flex justify-content-center">
        <nav aria-label="Navigazione">
          <ul class="pagination">
            <li
              v-for="index in totalPages"
              :key="index"
              :class="`page-item ${currentPage == index ? 'active' : ''}`"
            >
              <a @click.prevent="onPageSelectorSelected(index)" class="page-link" href="#">{{
                index
              }}</a>
            </li>
          </ul>
        </nav>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import type { Tab } from '@/interfaces/tab.ts'
import { nextTick, onMounted, ref } from 'vue'
import type { Room } from '@/interfaces/room.ts'
import { createRoom, deleteRoom, getAllRooms, updateRoom } from '@/services/roomService.ts'
import { Modal } from 'bootstrap'
import { getRoomTypes } from '@/services/roomTypeService.ts'
import type { RoomType } from '@/interfaces/roomtype.ts'
import { AxiosError } from 'axios'
import showToast from '@/services/toaster.ts'

export default {
  name: 'RoomsTab',
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
  setup() {
    const rooms = ref<Room[]>([])
    const modal = ref<Modal | null>(null)
    const currentPage = ref(1)
    const totalPages = ref(0)
    const getRooms = async () => {
      const response = await getAllRooms(currentPage.value)
      totalPages.value = response.totalPages
      rooms.value = response.rooms
    }

    const roomTypes = ref<RoomType[]>([])
    const getRoomTypesAction = async () => {
      roomTypes.value = await getRoomTypes()
    }

    const selectedRoom = ref<Room>()
    onMounted(getRooms)
    onMounted(getRoomTypesAction)
    return { rooms, getRooms, selectedRoom, modal, roomTypes, currentPage, totalPages }
  },
  data() {
    return {
      isNewRoom: false,
    }
  },
  methods: {
    async onDeleteButtonPressed(room: Room) {
      await deleteRoom(room)
      if (this.rooms.length === 1 && this.currentPage > 1) {
        this.currentPage--
      }
      await this.getRooms()
      showToast('Stanza eliminata con successo', 'success')
    },
    async onPageSelectorSelected(page: number) {
      this.currentPage = page
      await this.getRooms()
    },
    async onEditButtonPressed(room: Room) {
      this.isNewRoom = false
      this.selectedRoom = room
      await nextTick()
      this.modal = new Modal(document.querySelector('#staticBackdrop') as HTMLElement)
      this.modal?.show()
    },
    async onCreateButtonPressed() {
      this.isNewRoom = true
      this.selectedRoom = {
        numeroStanza: 0,
        prenotabile: false,
        tipoStanza: this.roomTypes[0],
      }
      await nextTick()
      this.modal = new Modal(document.querySelector('#staticBackdrop') as HTMLElement)
      this.modal?.show()
    },
    async saveRoom() {
      try {
        this.selectedRoom = this.isNewRoom
          ? await createRoom(this.selectedRoom!)
          : await updateRoom(this.selectedRoom!)
        this.modal?.hide()
        await this.getRooms()
        showToast('Stanza salvata con successo', 'success')
      } catch (e) {
        if (e instanceof AxiosError) {
          showToast(e.response?.data.detail, 'error')
        }
      }
    },
  },
}
</script>
