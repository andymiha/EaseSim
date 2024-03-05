#!/bin/sh

docker exec ease_sim_frontend sh -c "ls -la /app/scripts && cd /app/scripts && setup.sh"

$SHELL