#!/bin/bash

if [ ! -d backend ]; then
    echo "backend folder not found"
    exit 1
fi

if [ ! -d frontend ]; then
    echo "frontend folder not found"
    exit 1
fi

env UID=${UID} GID=${GID} docker-compose up -d