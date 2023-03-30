FROM eclipse-temurin:17-jdk-alpine as build

COPY gradle gradle
COPY build.gradle settings.gradle gradlew ./
COPY src src

RUN ./gradlew clean build -x test

FROM eclipse-temurin:17-alpine
COPY --from=build build/libs/*.jar hvz-backend.jar
VOLUME /tmp
ENTRYPOINT ["java","-jar","hvz-backend.jar"]