# This you can set to your own installation
server_home=~/Documents/Minecraft/PaperMC.1.16.4
server_file=paper-282.jar

# We copy the addon into your plugins folder
cp ./target/whistles-*.jar $server_home/plugins

# Then we run the jar from within the folder
cd $server_home
java -jar $server_file
