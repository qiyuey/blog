FROM java:8
EXPOSE 8080
ADD gradle /tmp/build/gradle
ADD src /tmp/build/src
ADD gradlew /tmp/build
ADD build.gradle /tmp/build
RUN cd /tmp/build && ./gradlew build && cp ./build/libs/*.jar /app.jar && cd / && rm -rf /tmp/build
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]