import menu from './menu.json'

export interface MenuItem {
  name: string
  path: string
  class?: string
}

export function getAllMenuItems(): MenuItem[] {
  return menu
}

export function getMenuItemByPath(path: string): MenuItem | undefined {
  return menu.find(item => item.path === path)
}
