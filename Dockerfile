FROM gradle:8.5-jdk21 AS build
WORKDIR /home/gradle/project

# Cache das dependências (só reexecuta se os arquivos de build mudarem)
COPY --chown=gradle:gradle build.gradle* settings.gradle* gradle.properties* ./
COPY --chown=gradle:gradle gradle ./gradle
RUN gradle dependencies --no-daemon > /dev/null 2>&1 || true

# Código e build
COPY --chown=gradle:gradle src ./src
RUN gradle bootJar -x test --no-daemon

FROM eclipse-temurin:21-jre-jammy AS final

ARG UID=10001
RUN adduser \
    --disabled-password \
    --gecos "" \
    --home "/nonexistent" \
    --shell "/sbin/nologin" \
    --no-create-home \
    --uid "${UID}" \
    appuser

WORKDIR /app
COPY --from=build /home/gradle/project/build/libs/*.jar app.jar
USER appuser

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]