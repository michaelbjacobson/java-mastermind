#!/bin/bash
DOCKER_CONFIG=$JENKINS_HOME/.docker
cd ~/.docker
touch ./config.json
echo '{"auths":{}}' >> ./config.json