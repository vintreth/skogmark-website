#!/bin/sh

APP_VERSION=skogmark-go-gen-1.3-SNAPSHOT
TARGET=IdeaProjects/skogmark-website/skogmark-go-gen/target/$APP_VERSION.tgz
USER=svip

scp $TARGET $USER@skogmark.ru:/home/$USER
#ssh $USER@skogmark.ru sudo /bin/sh /home/$USER/remote-install.sh > remote-install.log