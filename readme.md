# CatBot

A simple bot which can send you some animal pictures.

## Table of contents

1. [Building](#building)
2. [Running](#running)
3. [Running with Docker](#running-with-docker)

## Building

Java 11 is recommended for building and running this bot. <br/>
First, clone this repo: <br/>
``git clone https://github.com/bartosz11/catbot.git`` <br/>
Then build it with Gradle: <br/>
``./gradlew shadowJar`` <br/>
The jar file will be out in build/libs directory. <br/>

Building Docker image: 
```bash 
git clone https://github.com/bartosz11/catbot.git
./gradlew shadowJar
docker build -t bartosz11/catbot .
```

## Running

You can start the bot with ``java -jar <jar name>`` <br/>
After running it for the first time, it'll generate a file called config.json. Open it with some text editor and replace REPLACE_THIS_WITH_YOUR_TOKEN with your bot token. You can get one at [Discord Developer Portal](https://discord.com/developers/applications). <br/>
After saving the config, you can start the bot once again. <br/>
To invite the bot to your server, you need to visit the OAuth2 tab in Developer Portal. Go to URL generator. Here you need to select scopes "bot" and "applications.commands". After selecting them, you can copy the URL and paste it to the browser, select the server and invite your bot. <br/>
You can use ``/help`` command (slash command) to display list of commands. <br/>
Note: it can take up to an hour before you'll be able to see and use bot's commands in Discord clients because commands are global and not per-guild. <br/>

## Running with Docker

To run the bot with Docker, you can use this example docker-compose file: <br/>
```yaml
---
version: "3"

services:
  catbot:
    container_name: catbot
    image: bartosz11/catbot
    restart: unless-stopped
    environment:
        # Timezone used in logs
        TZ: Europe/London
        CATBOT_TOKEN: REPLACE_THIS_WITH_YOUR_TOKEN
```
Or a ``docker run`` command: <br/>
``docker run -d --name catbot ---restart unless-stopped -e CATBOT_TOKEN=REPLACE_THIS_WITH_YOUR_TOKEN -e TZ=Europe/London bartosz11/catbot`` <br/>
They both do the same thing. <br/>
