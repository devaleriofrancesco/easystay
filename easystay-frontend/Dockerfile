# Dockerfile for Vue.js project
FROM node:23.5.0-alpine as build-stage
LABEL authors="devaleriofrancesco"

WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build

FROM nginx:alpine
COPY --from=build-stage /app/dist /usr/share/nginx/html