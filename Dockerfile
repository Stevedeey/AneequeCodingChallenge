FROM adoptopenjdk/openjdk11:alpine-jre
ADD target/AneequeCodingChallenge-0.0.1-SNAPSHOT.jar aneequeCodingChallenge.jar
ENTRYPOINT ["java", "-jar", "aneequeCodingChallenge.jar"]


