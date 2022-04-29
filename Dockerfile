FROM eclipse-temurin:11-alpine
RUN adduser --home /home/container -D container
USER container
WORKDIR /home/container
COPY build/libs/catbot.jar catbot.jar
ENTRYPOINT [ "java", "-jar", "catbot.jar" ]                