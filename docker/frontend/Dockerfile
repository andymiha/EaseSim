FROM node:20-buster
RUN echo "America/Toronto" > /etc/timezone
# RUN npm install --save --legacy-peer-deps

COPY ./start.sh /start.sh
RUN chmod 755 /start.sh

RUN apt-get update && apt-get install -y git

ENTRYPOINT ["/start.sh"]
