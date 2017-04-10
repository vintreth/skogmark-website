#!/usr/bin/env bash

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
CONFIG_LOCATION=${DIR}/conf
LOG_CONF=file:${CONFIG_LOCATION}/log4j.xml
JAR_PATH=bin/app.jar

java -Dlog4j.configuration=${LOG_CONF} -Dapp.configLocation=${CONFIG_LOCATION} -jar ${DIR}/${JAR_PATH} <&- &