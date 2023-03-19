FROM eclipse-temurin:17-jre-jammy
RUN useradd -d /home/container -s /bin/bash container
COPY build/libs/catbot.jar /bin/catbot.jar
RUN chmod u+x /bin/catbot.jar
USER container
WORKDIR /home/container
ENTRYPOINT exec java -jar /bin/catbot.jar