FROM maven:3.3-jdk-8-onbuild
RUN git clone https://github.com/qeqs/image-processing-node.git
RUN mvn clean install assembly:assembly -DdescriptorId=jar-with-dependencies
EXPOSE 8080
ENTRYPOINT ["java","-Xms128m", "-Xmx128m","-Xss128k","-XX:MetaspaceSize=128m","-XX:MaxMetaspaceSize=128m","-XX:ReservedCodeCacheSize=16M","-XX:CodeCacheMinimumFreeSpace=1M",
"-XX:CompressedClassSpaceSize=8M","-XX:MinMetaspaceExpansion=1M","-XX:MaxMetaspaceExpansion=4M",
"-XX:CodeCacheExpansionSize=128k" "-Dfile.encoding=UTF-8", "-jar", "/usr/src/app/target/image-processing-node-1.0-SNAPSHOT-jar-with-dependencies.jar"]
