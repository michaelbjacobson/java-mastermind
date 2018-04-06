rm -rf ~/.docker
mkdir ~/.docker
touch ~/.docker/config.json
echo '{"auths":{}}' >> ~/.docker/config.json