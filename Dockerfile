FROM eclipse-temurin
ENV tinkoffToken=t.QGdyibIR0Bhajtul67frDsA0RFG-p3cKOOXZ3-ke2o6d-yAst8qR3Q0alHke0tkEPItc4XKg4cUhq9y3-IxeBw
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
