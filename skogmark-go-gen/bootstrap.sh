#!/usr/bin/env bash
java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=4000,suspend=n -jar target/skogmark-go-gen-1.3-SNAPSHOT.jar