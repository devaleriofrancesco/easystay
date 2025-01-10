import { defineStore } from 'pinia'
import type { RoomType } from '@/interfaces/roomtype.ts'
import { createBooking } from '@/services/bookingService.ts'

interface BookingDates {
  checkIn: Date
  checkOut: Date
}

export const useBooking = defineStore('booking', {
  state: () => ({
    selectedRoomType: {} as RoomType,
    dates: {
      checkIn: new Date(),
      checkOut: new Date(new Date().setDate(new Date().getDate() + 1)),
    } as BookingDates,
  }),
  getters: {
    getSelectedRoomTypeId: (state) => {
      return state.selectedRoomType.id
    },
    getCheckInDate: (state) => {
      return state.dates.checkIn
    },
    getCheckOutDate: (state) => {
      return state.dates.checkOut
    },
  },
  actions: {
    selectRoomType(roomType: RoomType) {
      this.selectedRoomType = roomType
    },
    setCheckInDate(date: Date) {
      this.dates.checkIn = date
    },
    setCheckOutDate(date: Date) {
      this.dates.checkOut = date
    },
    async bookRoom() {
      // call the booking service to book the room
      await createBooking(this.selectedRoomType.id, this.dates.checkIn, this.dates.checkOut)
    },
  },
})
