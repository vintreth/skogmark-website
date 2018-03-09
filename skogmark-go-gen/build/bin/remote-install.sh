#!/bin/sh

USER=svip
GROUP=svip

APP_VERSION=skogmark-go-gen-1.3-SNAPSHOT
ARCHIVE=$APP_VERSION.tgz
APP_HOME=/opt/generator
APP_BACKUP=$APP_HOME.backup

sudo systemctl stop generator

rm -rf $APP_BACKUP

mv $APP_HOME $APP_BACKUP
sudo mkdir $APP_HOME
sudo chmod 0755 $APP_HOME
sudo chown $USER $APP_HOME
sudo chgrp $GROUP $APP_HOME

cp /home/$USER/$ARCHIVE $APP_HOME
cd $APP_HOME
tar -xzvf $APP_HOME/$ARCHIVE
sudo chown -R $USER $APP_HOME
sudo chgrp -R $GROUP $APP_HOME

sudo systemctl start generator

