C:\cygwin64\bin\bash --login -c "cd /cygdrive/c/Users/$USER/Desktop; wget https://github.com/EMendibil/JolasMAKER/archive/master.zip; unzip -j master.zip JolasMAKER-master/executables/JolasMAKER.zip; unzip -j master.zip JolasMAKER-master/jre-11.0.19.zip; rm master.zip; wget https://github.com/EMendibil/blockly-games-iruzkinak/archive/master.zip; unzip master.zip; rm master.zip; rm -r /cygdrive/c/Users/$USER/Documents/blockly-games-iruzkinak; mv -f blockly-games-iruzkinak-master /cygdrive/c/Users/$USER/Documents/blockly-games-iruzkinak"

cd Desktop
tar -xf JolasMAKER.zip
tar -xf jre-11.0.19.zip
mkdir C:\Program_Files\Java

C:\cygwin64\bin\bash --login -c "cd /cygdrive/c/Users/$USER/Desktop; rm -r /cygdrive/c/Program_Files/Java/jre-11.0.19; mv -f jre-11.0.19 /cygdrive/c/Program_Files/Java/jre-11.0.19; export PATH=$PATH:\"C:/Program_Files/Java/jre-11.0.19/bin/\"; cd /cygdrive/c/Users/$USER/Documents/blockly-games-iruzkinak; make deps"
del JolasMAKER.zip "C:\Program_Files\Java\jre-11.0.19.zip" jre-11.0.19.zip blockly-games-iruzkinak-master
