#!/bin/bash

__dir="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"

file=$(cd ${__dir}/../build/libs && ls -1 m7r-infra-*.jar | head -n 1)

java -cp "${__dir}/../build/libs/${file}:${__dir}/../build/dependencies/*" org.mentalizr.scheduler.M7rScheduler "$@" & > /dev/null 2>&1
