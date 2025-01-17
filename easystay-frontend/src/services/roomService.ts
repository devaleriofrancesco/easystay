// get all rooms

import axiosInstance from '@/services/axios.ts'
import type { PagedRoom, Room } from '@/interfaces/room.ts'

export const getAllRooms = async (page: number) => {
  try {
    const response = await axiosInstance.get<PagedRoom>('/admin/rooms/page/' + page)
    return response.data
  } catch (error) {
    console.error('Error fetching rooms:', error)
    throw error
  }
}

// delete room
export const deleteRoom = async (room: Room) => {
  try {
    const response = await axiosInstance.delete<Room>(`/admin/rooms/${room.numeroStanza}`)
    return response.data
  } catch (error) {
    console.error('Error deleting room:', error)
    throw error
  }
}

// update room
export const updateRoom = async (room: Room) => {
  try {
    const response = await axiosInstance.put<Room>(`/admin/rooms/${room.numeroStanza}`, room)
    return response.data
  } catch (error) {
    console.error('Error updating room:', error)
    throw error
  }
}

// create room

export const createRoom = async (room: Room) => {
  try {
    const response = await axiosInstance.post<Room>('/admin/rooms', room)
    return response.data
  } catch (error) {
    console.error('Error creating room:', error)
    throw error
  }
}
