FROM openjdk:11
COPY ./target/micro-tp.jar micro-tp.jar
CMD ["java","-jar","micro-tp.jar"]