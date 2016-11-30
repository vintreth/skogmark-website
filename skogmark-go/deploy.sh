#!/bin/bash

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
WAR_NAME="skogmark-go-1.2-SNAPSHOT.war"

# run maven
mvn clean install

# Copying war file
scp ${DIR}/target/${WAR_NAME} svip@skogmark.ru:/home/svip/${WAR_NAME}

# Running remote deploy
ssh svip@skogmark.ru "sh /home/svip/remote-deploy.sh"