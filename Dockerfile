# 1 stage. Build maven jar
FROM maven:3.8.3-amazoncorretto-11 as MAVEN_BUILD

RUN mvn clean package -Djacoco.skip=true

# copy jar from build stage
RUN cp /build/ms-technical-challengee-core/target/ms-technical-challengee-core-?.?*.jar app.jar

# extract jar as layers
RUN java -Djarmode=layertools -jar app.jar extract

# 2 stage. Copy all layers to root directory
FROM amazoncorretto:11-alpine-jdk

WORKDIR /app/

COPY --from=MAVEN_BUILD /build/dependencies/ ./
COPY --from=MAVEN_BUILD /build/spring-boot-loader/ ./
COPY --from=MAVEN_BUILD /build/snapshot-dependencies/ ./

# TODO: remove workaround. see https://github.com/moby/moby/issues/37965
RUN true

COPY --from=MAVEN_BUILD /build/application/ ./

# run and install curl from alpine apk and add permissions rw to app directory
RUN apk update && \
 apk upgrade && \
 apk add curl unzip && \
 rm -rf /var/hazelCastCache/apk/* && \
 chgrp -R 0 /app/ && \
 chmod -R g=u /app/

#COPY newrelic.yml newrelic/

# change user id
USER 1001

# Java env
ENV JAVA_OPTS="-XX:+UseShenandoahGC -XX:ShenandoahGCMode=iu -XX:+AlwaysPreTouch -XX:+UnlockExperimentalVMOptions -XX:ShenandoahUncommitDelay=1000 -XX:ShenandoahGuaranteedGCInterval=10000 -Xms300m -Xmx800m -noverify -Djava.security.egd=file:/dev/./urandom -Dspring.backgroundpreinitializer.ignore=true"
