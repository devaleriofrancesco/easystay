<template>
  <main class="main-container">
    <div class="text-center mb-3">
      <h1>Prenota una camera</h1>
      <p>
        Seleziona il tipo di camera di cui necessiti in modo da assicurarti una vacanza all'insegna
        del relax e del comfort.
      </p>
    </div>

    <div class="container mb-3">
      <div class="row justify-content-md-center">
        <div class="col-md-7">
          <p>1. <strong>Clicca sul tipo di camera che vuoi prenotare</strong></p>
          <RoomTypeSlider :roomTypes="roomTypes" />
        </div>
        <div class="col-md-5">
          <p>2. <strong>Seleziona le date</strong></p>
          <DatePicker v-model.range="range" mode="date" />
        </div>
        <button @click.prevent="bookRoom" class="btn btn-cta">Prenota</button>
      </div>
    </div>
  </main>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { DatePicker } from 'v-calendar'
import 'v-calendar/style.css'
import { getRoomTypes } from '@/services/roomTypeService.ts'
import type { RoomType } from '@/interfaces/roomtype.ts'
import RoomTypeSlider from '@/components/sliders/RoomTypeSlider.vue'
import showToast from '@/services/toaster.ts'
import { useBooking } from '@/stores/booking.ts'
import { AxiosError } from 'axios'
import router from '@/router'

const store = useBooking()
const range = ref<{ start: Date; end: Date }>({
  start: store.getCheckInDate,
  end: store.getCheckOutDate,
})
const roomTypes = ref<RoomType[]>([])

const fetchRoomTypes = async (): Promise<void> => {
  try {
    roomTypes.value = await getRoomTypes()
  } catch (error) {
    console.log('Failed to fetch room types:', error)
  }
}

const bookRoom = () => {
  if (!range.value.start || !range.value.end) {
    showToast('Seleziona le date di check-in e check-out', 'error')
    return
  }

  if (!store.getSelectedRoomTypeId) {
    showToast('Seleziona il tipo di camera', 'error')
    return
  }

  store.setCheckInDate(range.value.start)
  store.setCheckOutDate(range.value.end)

  store
    .bookRoom()
    .then(() => {
      showToast('Camera prenotata con successo', 'success')
      router.push({ name: 'profilo' }).then(() => {
        store.$reset()
      })
    })
    .catch((e) => {
      if (e instanceof AxiosError) {
        showToast(e.response?.data.detail, 'error')
      } else {
        showToast('Errore durante la prenotazione', 'error')
      }
    })
}

onMounted(fetchRoomTypes)
</script>

<style lang="scss">
.vc-container {
  display: flex;
  justify-self: center;
}
</style>
