import axiosInstance from '@/services/axios.ts'
import type { ServizioWrapper } from '@/interfaces/roomtype.ts'

export const getAvailableServicesByRoomTypeId = async (roomTypeId: number) => {
  try {
    const response = await axiosInstance.get<ServizioWrapper[]>(
      `/admin/services/roomtype/${roomTypeId}`,
    )
    return response.data
  } catch (error) {
    console.error('Error fetching services:', error)
    throw error
  }
}
