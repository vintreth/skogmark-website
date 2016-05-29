#!/bin/bash

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

# Copying war file
scp ${DIR}/build/web-site-1.0.war developer@skogmark.ru:/home/developer/deploy/web-site-1.0.war

# Copying database files
scp ${DIR}/src/main/db/scheme/mysql/website/image.table.sql developer@skogmark.ru:/home/developer/deploy/image.table.sql
scp ${DIR}/src/main/db/scheme/mysql/website/post.table.sql developer@skogmark.ru:/home/developer/deploy/post.table.sql
scp ${DIR}/src/main/db/scheme/mysql/website/tag.table.sql developer@skogmark.ru:/home/developer/deploy/tag.table.sql
scp ${DIR}/src/main/db/scheme/mysql/website/user.table.sql developer@skogmark.ru:/home/developer/deploy/user.table.sql

# Running remote deploy
ssh developer@skogmark.ru
sh /home/developer/deploy/deploy.sh
