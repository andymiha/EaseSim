#!/bin/sh

while true; do
  clear
  if [ -f "/app/.setup_completed" ]; then
    cd /app && yarn dev
  fi
  echo "frontend not setup"
  sleep 10
done;