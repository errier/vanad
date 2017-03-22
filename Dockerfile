FROM maven:3-jdk-8

RUN mkdir -p /usr/src/app

RUN cd /usr/src/app && \
    git clone https://github.com/errier/vanad.git

WORKDIR /usr/src/app/vanad
