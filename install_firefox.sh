apt-add-repository ppa:mozillateam/firefox-next
apt-get update
apt-get install firefox xvfb
Xvfb :10 -ac &
export DISPLAY=:10
