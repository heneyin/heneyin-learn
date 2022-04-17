#!/usr/bin/env bash

projectName=Distribution

export PROJECT_HOME="$(cd "$(dirname "$0")"/../; pwd)/"
echo "Project ${projectName} dir: ${PROJECT_HOME}"

ps -ef | grep ${projectName} | grep -v grep | awk '{print $2;}' | while read proPid; do
    echo "Project ${projectName} running on PID: ${proPid}, please stop it first."
	exit 5;
done;

if [ $? -eq 5 ];then 
    exit 1
fi

java -cp ${PROJECT_HOME}/lib/ com.heneyin.distribution.core.Distribution

if [ $? -eq 0 ]; then 
    echo "Start ${projectName} success on $!"
else 
    echo "Start ${projectName} failed."
fi