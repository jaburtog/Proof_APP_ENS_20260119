FROM icr.io/appcafe/open-liberty:full-java17-openj9-ubi

ARG VERSION=1.0.0-SNAPSHOT
ARG REVISION=SNAPSHOT

LABEL \
  org.opencontainers.image.authors="CRUD Application" \
  org.opencontainers.image.version="$VERSION" \
  org.opencontainers.image.revision="$REVISION" \
  name="crud-app" \
  version="$VERSION-$REVISION" \
  summary="CRUD Application with Jakarta EE 10" \
  description="Enterprise CRUD application built with Jakarta EE 10, Liberty, and Clean Architecture"

COPY --chown=1001:0 web/src/main/liberty/config/server.xml /config/server.xml
COPY --chown=1001:0 web/target/crud-app.war /config/apps/

RUN configure.sh
