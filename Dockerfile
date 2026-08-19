# ---- Build stage: compile the WAR with Maven ----
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn -q clean package -DskipTests

# ---- Run stage: deploy the WAR into Tomcat ----
FROM tomcat:10.1-jdk17
# Remove Tomcat's default sample apps
RUN rm -rf /usr/local/tomcat/webapps/*
# Copy our built WAR in as ROOT.war so it serves at the domain root ("/")
COPY --from=build /app/target/chittagong-district-quiz.war /usr/local/tomcat/webapps/ROOT.war
EXPOSE 8080
CMD ["catalina.sh", "run"]
