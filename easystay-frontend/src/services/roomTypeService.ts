import type { RoomType } from '@/interfaces/roomtype.ts'
import axiosInstance from '@/services/axios.ts'
import type { Image } from '@/interfaces/Image.ts'
import type { SelectorImage } from '@/components/forms/ImageSelector.vue'

export const getRoomTypes = async () => {
  try {
    const response = await axiosInstance.get<RoomType[]>(`/roomtypes`)
    return response.data
  } catch (error) {
    console.error('Error fetching room types:', error)
    throw error
  }
}

export const getRoomTypeImages = (roomType: RoomType) => {
  return roomType.galleriaImmagini.map(
    (image) =>
      <Image>{
        src: `${import.meta.env.VITE_BACKEND_IMAGES_URL}/roomtypes/${image.path_immagine}`,
        title: '',
        description: '',
        posizione: image.posizione,
      },
  )
}

// create roomType
export const createRoomType = async (roomType: RoomType) => {
  try {
    const response = await axiosInstance.post<RoomType>(`/admin/roomtypes`, roomType)
    return response.data
  } catch (error) {
    console.error('Error updating room type:', error)
    throw error
  }
}

// update roomType
export const updateRoomType = async (roomType: RoomType) => {
  try {
    const response = await axiosInstance.put<RoomType>(`/admin/roomtypes/${roomType.id}`, roomType)
    return response.data
  } catch (error) {
    console.error('Error updating room type:', error)
    throw error
  }
}

// delete roomType
export const deleteRoomType = async (roomType: RoomType) => {
  try {
    const response = await axiosInstance.delete<RoomType>(`/admin/roomtypes/${roomType.id}`)
    return response.data
  } catch (error) {
    console.error('Error updating room type:', error)
    throw error
  }
}

export const getSelectorImagesByRoomType = (roomType: RoomType) => {
  return roomType.galleriaImmagini?.map(
    (image) =>
      ({
        src: `${import.meta.env.VITE_BACKEND_IMAGES_URL}/roomtypes/${image.path_immagine}`,
        position: image.posizione,
      }) as SelectorImage,
  )
}

export const getNewRoomType = async () => {
  try {
    const response = await axiosInstance.get<RoomType>(`admin/roomtypes/new`)
    return response.data
  } catch (error) {
    console.error('Error fetching new room type:', error)
    throw error
  }
}
