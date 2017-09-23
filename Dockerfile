FROM maven:3.3-jdk-8-onbuild
#WORKDIR /app
#ADD . /app
#RUN ./image-processing-node/mvnw clean install assembly:assembly -DdescriptorId=jar-with-dependencies
#ADD image-processing-node/target/image-processing-node-*-SNAPSHOT-jar-with-dependencies.jar /app/app.jar
RUN git clone https://github.com/qeqs/image-processing-node.git
RUN mvn clean install assembly:assembly -DdescriptorId=jar-with-dependencies
ADD image-processing-node/target/image-processing-node-*-SNAPSHOT-jar-with-dependencies.jar /app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Dfile.encoding=UTF-8", "-jar", "app.jar"]
