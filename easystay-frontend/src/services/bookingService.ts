// booking service

// get all bookings for a user
import type { Booking } from '@/interfaces/booking.ts'
import axiosInstance from '@/services/axios.ts'
import { useUsers } from '@/stores/user.ts'

// get all bookings for a user
export const getBookings = async () => {
  try {
    const response = await axiosInstance.get<Booking[]>(`/user/bookings`)
    return response.data
  } catch (error) {
    throw error
  }
}

// get all bookings by month and year
export const getBookingsByMonth = async (month: number, year: number) => {
  try {
    const response = await axiosInstance.get<Booking[]>(
      `/admin/bookings/month/${month}/year/${year}`,
    )
    return response.data
  } catch (error) {
    throw error
  }
}

// get all bookings by check-in and check-out dates
export const getBookingsByDates = async (checkIn: Date, checkOut: Date) => {
  try {
    const response = await axiosInstance.get<Booking[]>(
      `/admin/bookings/checkin/${checkIn.toISOString().slice(0, 10)}/checkout/${checkOut.toISOString().slice(0, 10)}`,
    )
    return response.data
  } catch (error) {
    throw error
  }
}

// get all bookings by check-in, check-out dates and username
export const getBookingsByDatesAndUser = async (
  checkIn: Date,
  checkOut: Date,
  userName: string,
) => {
  try {
    const response = await axiosInstance.get<Booking[]>(
      `/admin/bookings/checkin/${checkIn.toISOString().slice(0, 10)}/checkout/${checkOut.toISOString().slice(0, 10)}/user/${userName}`,
    )
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
    await axiosInstance.delete(
      `/${useUsers().userData.ruolo === 'ROLE_ADMIN' ? 'admin' : 'user'}/bookings/${id}`,
    )
  } catch (error) {
    console.error('Error deleting booking:', error)
    throw error
  }
}
