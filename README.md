# partsregister-eventsourcing


Test av en enkel [eventsource](https://github.com/eventstore/eventstore/wiki/Event-Sourcing-Basics) versjon av et Partsregister.

Nå brukes en skrivemodell ([Read model](http://cqrs.wordpress.com/documents/cqrs-and-event-sourcing-synergy/)/[Query model](http://martinfowler.com/bliki/CQRS.html)) 
som nå ligger i samme JVM og lytter på nye eventer over en enkel [EventBus](https://code.google.com/p/guava-libraries/wiki/EventBusExplained). En bedre  

[Visit GitHub!](www.github.com)


Bruker Dropwizard. Bygg en Fat JAR med 
```
mvn package
```
Start Jetty med 
```
java -jar target/partsregister-eventsourcing-1.0-SNAPSHOT.jar server partsregister.yml
```

http://localhost:8080  
admin port: 8081
