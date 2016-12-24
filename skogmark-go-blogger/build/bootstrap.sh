#!/usr/bin/env bash

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
CONF=file:${DIR}/conf/logback.xml

java -jar -Dlog4j.configuration=${CONF} ${DIR}/skogmark-go-blogger-1.1-SNAPSHOT.jar <&- &