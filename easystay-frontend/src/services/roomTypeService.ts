import axios from 'axios'
import type { RoomType } from '@/interfaces/roomtype.ts'

const API_URL = import.meta.env.VITE_BACKEND_API_URL

export const getRoomTypes = async () => {
  try {
    const response = await axios.get<RoomType[]>(`${API_URL}/roomtypes`)
    return response.data
  } catch (error) {
    console.error('Error fetching room types:', error)
    throw error
  }
}
