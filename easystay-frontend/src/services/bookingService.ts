// booking service

// get all bookings for a user
import type { Booking } from '@/interfaces/booking.ts'
import axiosInstance from '@/services/axios.ts'

export const getBookings = async () => {
  try {
    const response = await axiosInstance.get<Booking[]>(`/user/bookings`)
    return response.data
  } catch (error) {
    console.error('Error fetching bookings:', error)
    throw error
  }
}
