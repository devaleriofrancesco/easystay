/** @type {import('tailwindcss').Config} */
import flowbite from 'flowbite/plugin'

module.exports = {
  content: [
    "./index.html",
    "./src/**/*.{vue,js,ts,jsx,tsx}",
    "./node_modules/flowbite/**/*.js"
  ],
  darkMode: 'class',
  theme: {
    extend: {},
  },
  plugins: [
    flowbite
  ],
}
