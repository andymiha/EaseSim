FROM openjdk:17-slim

RUN echo "Etc/UTC" > /etc/timezone

RUN echo '#!/bin/sh\n\
while true; do\n\
  clear\n\
  if [ -f "/var/www/html/.setup_completed" ]; then\n\
    echo "backend setup completed"\n\
    cd /var/www/html && java -jar ./app.jar --server.port=4024\n\
  else\n\
    echo "backend not setup"\n\
    ls -la /var/www/html # List the contents of the directory for debugging\n\
  fi\n\
  sleep 10\n\
done' > /start.sh \
 && chmod 755 /start.sh

EXPOSE 4024

ENTRYPOINT ["/bin/sh", "/start.sh"]