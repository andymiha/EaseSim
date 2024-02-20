#!/bin/bash

if [ $1 == 'node' ]; then
  user=node
  work_dir=/app
  container=frontend
elif [[ $1 == 'backend' ]]; then
  user=devteam
  work_dir=/var/www/html
  container=backend
else
  user=root
  work_dir=/
  container=$1
fi

docker exec -it --user $user -w $work_dir ease_sim_$container /bin/bash
