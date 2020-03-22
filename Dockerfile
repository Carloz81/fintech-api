FROM openjdk:8-jre-alpine

ADD /target/*.jar /home/fintech-api.jar

CMD java -jar /home/fintech-api.jar