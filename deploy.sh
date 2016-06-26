#!/bin/bash

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

# Copying war file
scp ${DIR}/target/web-site-1.0.war developer@skogmark.ru:/home/developer/deploy/web-site-1.0.war

# Copying database files
cd ${DIR}/src/main/resources/db
tar -czvf database.scheme.tar.gz .
scp database.scheme.tar.gz developer@skogmark.ru:/home/developer/deploy/db/database.scheme.tar.gz
rm database.scheme.tar.gz

# Running remote deploy
ssh developer@skogmark.ru "sh /home/developer/deploy/deploy.sh"
