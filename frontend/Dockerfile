# Etapa 1: Build de Angular
FROM node:20 AS builder
WORKDIR /app
COPY . .
RUN npm install
RUN npm run build

# Etapa 2: Servir con Nginx
FROM nginx:alpine
COPY --from=builder /app/dist/frontend/browser /usr/share/nginx/html

COPY nginx.conf /etc/nginx/conf.d/default.conf
EXPOSE 80

