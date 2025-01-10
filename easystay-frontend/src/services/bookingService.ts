// booking service

// get all bookings for a user
import type { Booking } from '@/interfaces/booking.ts'
import axiosInstance from '@/services/axios.ts'

// get all bookings for a user
export const getBookings = async () => {
  try {
    const response = await axiosInstance.get<Booking[]>(`/user/bookings`)
    return response.data
  } catch (error) {
    throw error
  }
}

// create a booking
export const createBooking = async (roomType: number, dataCheckIn: Date, dataCheckOut: Date) => {
  try {
    const response = await axiosInstance.post<Booking>(`/user/bookings`, {
      roomType,
      dataCheckIn: dataCheckIn.toISOString().slice(0, 10),
      dataCheckOut: dataCheckOut.toISOString().slice(0, 10),
    })
    return response.data
  } catch (error) {
    throw error
  }
}

// change check-in or check-out date for a booking
export const updateBooking = async (id: string, dataCheckIn: Date, dataCheckOut: Date) => {
  try {
    const response = await axiosInstance.put<Booking>(`/user/bookings/change-dates/${id}`, {
      dataCheckIn: dataCheckIn.toISOString().slice(0, 10),
      dataCheckOut: dataCheckOut.toISOString().slice(0, 10),
    })
    return response.data
  } catch (error) {
    throw error
  }
}

// delete a booking
export const deleteBooking = async (id: string) => {
  try {
    await axiosInstance.delete(`/user/bookings/${id}`)
  } catch (error) {
    console.error('Error deleting booking:', error)
    throw error
  }
}
