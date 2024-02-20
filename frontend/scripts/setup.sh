#!/bin/bash
cd /app

runuser -u node -- npm install --save --legacy-peer-deps
touch /app/.setup_completed
