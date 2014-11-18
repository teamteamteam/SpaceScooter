.PHONY: clean run stat debug compile

game.jar: compile
	echo 'Main-Class: de.teamteamteam.spacescooter.Main' >compiled/manifest.txt
	jar cvfe game.jar 'de.teamteamteam.spacescooter.Main' -C compiled de -C compiled images -C compiled sounds -C compiled music

compile: clean
	mkdir -p compiled
	find src/ -type f -name '*.java' -print0 | xargs -0 javac -cp src/ -d compiled/
	cp -r res/* compiled/

run: game.jar
	java -jar game.jar

debug: compile
	java -cp bin/ de.teamteamteam.spacescooter.Main

clean:
	rm -rf compiled
	rm -f game.jar

stat:
	find . -type f -name '*.java' -print0 | xargs -0 wc -l
