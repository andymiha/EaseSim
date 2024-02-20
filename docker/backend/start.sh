#!/bin/sh

while true; do
  clear
  if [ -f "/var/www/html/.setup_completed" ]; then
    echo "backend setup completed"
    cd /var/www/html && java -jar ./app.jar --server.port=4024
  else
    echo "backend not setup"
    ls -la /var/www/html # List the contents of the directory for debugging
  fi
  # echo "backend not setup"
  sleep 10
done;