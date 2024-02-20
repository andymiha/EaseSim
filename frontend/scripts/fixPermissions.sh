#!/bin/bash

DIR=$( dirname `dirname -- "$0"`; )
source $DIR/.env
if [ ${APP_ENV^^} == 'PROD' ]; then
  USER=devteam
  GROUP=devteam
elif [ ${APP_ENV^^} == 'STAGING' ]; then
  USER=devteam
  GROUP=devteam
else
  USER=`id --user --name`
  GROUP=`id --group --name`
fi

sudo find $DIR/ -user root -exec chown $USER:$GROUP {} \; -exec chmod g+w {} \;
sudo find $DIR/ ! -group $GROUP ! -group www-data -exec chown $USER:$GROUP {} \; -exec chmod g+w {} \;
