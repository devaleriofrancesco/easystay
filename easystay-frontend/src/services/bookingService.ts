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
    console.error('Error fetching bookings:', error)
    throw error
  }
}

// change check-in or check-out date for a booking
export const updateBooking = async (id: string, checkIn: string, checkOut: string) => {
  try {
    await axiosInstance.put(`/user/bookings/${id}`, { checkIn, checkOut })
  } catch (error) {
    console.error('Error updating booking:', error)
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
