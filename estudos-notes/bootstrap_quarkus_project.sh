#!/usr/bin/env bash
mvn -U io.quarkus:quarkus-maven-plugin:create \
        -DprojectGroupId=tech.ada.web2.quarkus \
        -DprojectArtifactId=rest-book \
        -DclassName="tech.ada.web2.quarkus.Bancos" \
        -Dpath="/v1/books" \
        -Dextensions="resteasy-jsonb"
