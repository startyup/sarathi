# pull official base image
FROM node:16.16.0-alpine AS build

# set working directory
WORKDIR /app

# add `/app/node_modules/.bin` to $PATH
ENV PATH /app/node_modules/.bin:$PATH

# install app dependencies
COPY package.json ./
RUN npm install --legacy-peer-deps

# add app
COPY . ./


CMD ["npm", "start"]
