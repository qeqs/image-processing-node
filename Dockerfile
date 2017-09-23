FROM java:8
WORKDIR /app
ADD . /app
ADD image-processing-node/target/image-processing-node-*-SNAPSHOT-jar-with-dependencies.jar /app/app.jar
RUN mvn clean deploy assembly:assembly -DdescriptorId=jar-with-dependencies
EXPOSE 8080
ENTRYPOINT ["java", "-Dfile.encoding=UTF-8", "-jar", "/app/app.jar", "-Dspring.profiles.active=$SPRING_PROFILES_ACTIVE"]
