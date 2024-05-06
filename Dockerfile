FROM docker.io/library/eclipse-temurin:21-jdk-alpine AS builder

WORKDIR /src/balancekeranjangbelanjahistoryrating
COPY . .
RUN chmod +x ./gradlew
RUN ./gradlew clean bootjar

FROM docker.io/library/eclipse-temurin:21-jre-alpine AS runner

ARG USER_NAME=balancekeranjangbelanjahistoryrating
ARG USER_UID=1000
ARG USER_GID=${USER_UID}

RUN addgroup -g ${USER_GID} ${USER_NAME} \
&& adduser -h /opt/balancekeranjangbelanjahistoryrating -D -u ${USER_UID} -G ${USER_NAME} ${USER_NAME}

USER ${USER_NAME}
WORKDIR /opt/balancekeranjangbelanjahistoryrating
COPY --from=builder --chown=${USER_UID}:${USER_GID} /src/balancekeranjangbelanjahistoryrating/build/libs/*.jar app.jar

ENTRYPOINT ["java"]
CMD ["-jar", "app.jar"]
