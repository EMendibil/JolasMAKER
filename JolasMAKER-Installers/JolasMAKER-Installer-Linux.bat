sudo apt install subversion
sudo apt install wget
sudo apt install default-jre
sudo apt install python3
sudo apt install sed

wget https://github.com/EMendibil/blockly-games-iruzkinak/archive/master.zip
unzip master.zip
rm master.zip
rm /home/$USER/Documents/blockly-games-iruzkinak
mv -f blockly-games-iruzkinak-master /home/$USER/Documents/blockly-games-iruzkinak
cd /home/$USER/Documents/blockly-games-iruzkinak
make deps

wget https://github.com/EMendibil/JolasMAKER/archive/master.zip
unzip -j master.zip JolasMAKER-master/executables/JolasMAKER.zip
unzip JolasMAKER.zip
rm master.zip
rm JolasMAKER.zip