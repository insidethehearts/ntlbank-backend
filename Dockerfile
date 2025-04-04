FROM amazoncorretto:21
WORKDIR /app

COPY ./target/ntlbank-backend-*.jar /app/ntlbank-backend-application.jar

EXPOSE 8080

CMD ["java", "-jar", "ntlbank-backend-application.jar"]