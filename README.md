partsregister-eventsourcing
===========================

Test av en enkel eventsource versjon av et Partsregister.

Bruker Dropwizard. Bygg en Fat JAR med 
```
mvn package
```
Start Jetty med 
```
java -jar target/partsregister-eventsourcing-1.0-SNAPSHOT.jar server partsregister.yml
```

http://localhost:8080
admin: 8081
