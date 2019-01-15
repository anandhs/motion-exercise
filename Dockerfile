FROM openjdk:8

RUN apt-get update

RUN ["apt-get", "install", "-y", "maven"]

ENV PATH $JAVA_HOME/bin:$PATH


USER root

WORKDIR /root

ADD . /root

RUN mvn clean package

EXPOSE 8080

CMD ["mvn", "spring-boot" , "run"]