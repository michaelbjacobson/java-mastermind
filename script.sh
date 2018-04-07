#!/usr/bin/env bash
cd $JENKINS_HOME
rm -rf .docker
mkdir .docker
mv .dockercfg .docker/config.json
