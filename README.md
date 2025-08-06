# british-spoken-time

Java program that outputs the British spoken form of a time given as input. For example, the program will get 12:00 as
input and will give noon as output.

## To package the project (without running tests)
```
mvn clean package -DskipTests
```

## To package the project (with running tests)
```
mvn clean package
```

## To run all tests
```
mvn test
```

## To run the project
```
java -jar target/british-spoken-time-1.0-SNAPSHOT.jar
```

- After running the project, there will be a command line which accepts time in H:mm format
- You can type exit to quit at any time
- Once you enter a valid time input, you will receive the equivalent british spoken time in words and will be prompted again to enter another time or to exit
- If an invalid text was provided, an error will be shown and you will be prompted again to enter another time or to exit