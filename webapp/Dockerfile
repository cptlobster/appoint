# build the image in a NodeJS container
FROM node

WORKDIR /app

# download dependencies
COPY package.json ./
COPY package-lock.json ./
RUN npm install
# copy in files
COPY . ./
RUN npm run build

CMD node build