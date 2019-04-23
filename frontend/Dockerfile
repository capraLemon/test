FROM node:11.10-alpine AS foryarn

COPY . /frontend
WORKDIR /frontend
RUN yarn && yarn build

FROM nginx:alpine

COPY /nginx/nginx.conf /etc/nginx/
COPY --from=foryarn /frontend/dist /frontend/dist
