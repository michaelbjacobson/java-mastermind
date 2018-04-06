#!/bin/bash
DOCKER_CONFIG=$JENKINS_HOME/.docker
cd $JENKINS_HOME
mkdir .docker
cd .docker
touch ./config.json
echo '{"auths":{}}' >> ./config.json
pwd
ls -alt