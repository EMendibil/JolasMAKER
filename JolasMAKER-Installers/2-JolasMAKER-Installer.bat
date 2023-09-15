C:\cygwin64\bin\bash --login -c "cd /cygdrive/c/Users/$USER/Desktop; wget https://github.com/EMendibil/JolasMAKER/archive/master.zip; unzip -j master.zip JolasMAKER-master/executables/JolasMAKER.zip; unzip -j master.zip JolasMAKER-master/jre-11.0.19.zip; rm master.zip; wget https://github.com/EMendibil/blockly-games-iruzkinak/archive/master.zip; unzip master.zip; rm master.zip; rm /cygdrive/c/Users/$USER/Documents/blockly-games-iruzkinak; mv -f blockly-games-iruzkinak-master /cygdrive/c/Users/$USER/Documents/blockly-games-iruzkinak; cd /cygdrive/c/Users/$USER/Documents/blockly-games-iruzkinak; make deps"
tar -xf JolasMAKER.zip
tar -xf jre-11.0.19.zip
del JolasMAKER.zip jre-11.0.19.zip
mkdir C:\Program_Files Java
move jre-11.0.19.zip "C:\Program Files\Java"