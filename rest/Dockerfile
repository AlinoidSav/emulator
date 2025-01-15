FROM wrzlbrmft/maven-git:openjdk21
#RUN apt-get update && apt-get install -y openjdk-17-jdk maven git
WORKDIR /app
RUN git clone https://github.com/AlinoidSav/emulator /app
WORKDIR /app/rest
RUN mvn clean package install
WORKDIR /app/rest/target
EXPOSE 8080
CMD ["java", "-jar", "rest-rest_emulator.jar"]


