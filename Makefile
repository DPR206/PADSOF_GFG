# No me deja ejecutarlo pero sirve para copiar y pegar los comandos
all:
	javac -d obj -Werror src\*.java

doc:
	javadoc -d doc src\*.java

run:
	java main

clean:
	rm obj/*.class
