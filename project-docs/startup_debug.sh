export JAVA_OPTS="-Djava.awt.headless=true -Dfile.encoding=UTF-8 
-server -Xms1536m -Xmx1536m
-XX:NewSize=256m -XX:MaxNewSize=256m -XX:PermSize=256m 
-XX:MaxPermSize=256m -XX:+DisableExplicitGC"
export JPDA_TRANSPORT=dt_socket
export JPDA_ADDRESS=8000

PRGDIR=`dirname "$PRG"`
EXECUTABLE=catalina.sh

# Check that target executable exists
if $os400; then
  # -x will Only work on the os400 if the files are:
  # 1. owned by the user
  # 2. owned by the PRIMARY group of the user
  # this will not work if the user belongs in secondary groups
  eval
else
  if [ ! -x "$PRGDIR"/"$EXECUTABLE" ]; then
    echo "Cannot find $PRGDIR/$EXECUTABLE"
    echo "The file is absent or does not have execute permission"
    echo "This file is needed to run this program"
    exit 1
  fi
fi
exec "$PRGDIR"/"$EXECUTABLE" jpda start "$@"

#exec "$PRGDIR"/"$EXECUTABLE" start "$@"



