#!/bin/bash
DOCKER_CONFIG=$JENKINS_HOME/.docker
cd $JENKINS_HOME
pwd
ls -alt
touch ./config.json
echo '{"auths":{}}' >> ./config.json