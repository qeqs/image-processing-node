FROM maven:3.3-jdk-8-onbuild
RUN git clone https://github.com/qeqs/image-processing-node.git
RUN mvn clean install assembly:assembly -DdescriptorId=jar-with-dependencies
EXPOSE 8080
ENTRYPOINT ["java", "-Dfile.encoding=UTF-8", "-jar", "/usr/src/app/target/image-processing-node-1.0-SNAPSHOT-jar-with-dependencies.jar"]
