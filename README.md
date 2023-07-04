# Partior Assignment - Star Wars Information

This is a SpringBoot 3 application which exposes a single endpoint, `GET /information` that returns the information
of relevant **swapi** resources as per the requirements.

## Runtime Requirements

This application runs well with the following -
* **JDK:** Eclipse Temurin 20.0.1 
* **Java Language Compatibility:** Java 17 

## Setup

### Running via IDE
At time of writing, this program does not require any additional configurations for it to start working on a regular IDE (eg. IntelliJ).

Just ensure that the IDE is configured to use Eclipse Temurin 20.0.1 JDK for the project before running the application.

### Running via Local Dev Machine

* Run tests and build JAR
```sh
./gradlew clean build
```
* Run JAR using local dev machine's Java runtime. For minimum compatibility issues, ensure you are using Eclipse Temurin 20.0.1.
```sh
java -jar build/libs/demo-0.0.1-SNAPSHOT.jar
```

### Running via Docker

* Run tests and build JAR
```sh
./gradlew clean build
```
* Build docker image
```sh
docker build . -t starwars:latest
```

* Run docker image. The following command runs it in detached mode but this is optional. 
```sh
docker run -d starwars:latest
```

* The application listens on port 8080 by default, and if successfully started, can now be accessed via `localhost:8080`

## Configuration

The IDs of the relevant **swapi** resources can be configured in `application.properties` if needed