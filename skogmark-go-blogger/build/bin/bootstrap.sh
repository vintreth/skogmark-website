#!/usr/bin/env bash

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
CONFIG_LOCATION=${DIR}/conf
LOG_CONF=file:${CONFIG_LOCATION}/logback.xml
JAR_NAME=app.jar

java -jar -Dlog4j.configuration=${LOG_CONF} -Dapp.configLocation=${CONFIG_LOCATION} ${DIR}/${JAR_NAME} <&- &