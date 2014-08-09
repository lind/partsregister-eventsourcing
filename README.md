# partsregister-eventsourcing

Test av en enkel [eventsource](https://github.com/eventstore/eventstore/wiki/Event-Sourcing-Basics) versjon av et Partsregister.

Bruker en lesemodell ([Read model](http://cqrs.wordpress.com/documents/cqrs-and-event-sourcing-synergy/)/[Query model](http://martinfowler.com/bliki/CQRS.html)) 
som nå ligger i samme JVM og lytter på nye eventer over en EventBus. Kjører med Jetty og kan lett brukes i en container som [Docker](https://www.docker.com/). 

## Oppbygging
* Et tydelig skille mellom lese og skrivemodell.
* Skrivemodellen skal være en eventstore.
* Eventer skal publiseres slik at nye lesemodeller kan lages efter behov. (Guava sin [EventBus](https://code.google.com/p/guava-libraries/wiki/EventBusExplained) i eksemplet)
* Publisering kan gjøres med en køløsning, [Vert.x](http://vertx.io/), feeds eller en distribuert NoSQL som [Hadoop](http://hadoop.apache.org/), [MongoDB](http://www.mongodb.org/), [CouchDB](http://couchdb.apache.org/) evt. [EventStore](http://geteventstore.com/).
* Lesemodellen kan i starten finnes sammen med skrivemodellen og flyttes til annen JVM/container etter behov.
* Snapshot kan legges på efter behov for bedre ytelse.

**API**

Hver ressurs har underressurser med kommandoer og eventer. _God strategi?_ -> Kommandoer passer ikke så bra sammen med ressurs og CRUD. Eller kommandoer som "rot" ressurser? /navneendring /flytteendring etc. 

## Bruk
### Embedded server 
Kjør `TestServer` -> `localhost:8180/rest/`

### Jetty med mvn  
Start Jetty med 
`
mvn jetty:run
`
-> `localhost:8080/rest/`

### URLer
_baseurl_/parter  
_baseurl_/parter/{id}/events  
_baseurl_/parter/{id}/endre_navn  
_baseurl_/navneendring  
_baseurl_/partview/{id}  

## Videre mulgheter
* Jobbe mer med REST APIet. Få til Jersys linker bl.a. For å følge [HATEOAS](http://en.wikipedia.org/wiki/HATEOAS).
* Skille ut lesemodellen som lytter på eventer (i egen pakke nå: ske.part.partview)
* Tilby et API for å hente eventer (som feeds? hente fra en gitt aggregate root og en sekvens id? hente alle med paging?)
* Legge til en ny lesemodell som aggregerer informasjon som f.eks. statistikk - enkel [event monitorering](http://en.wikipedia.org/wiki/Event_monitoring) . Lytte på
eventbussen og aggregere informasjon. REST API til dette. (/rest/partmart?)
* Bruke [Vert.x sin EventBus](http://vertx.io/core_manual_java.html#the-event-bus) isteden for Guava slik at handlere
kan startes i flere JVMer. (Hvordan håndtere at Vert.x sin eventbus går ned? Hvordan håndtere at handlere går ned?)
 API som henter eventer fra en gitt id eller tidspunkt, )
* Lage [Text Fixtures](https://github.com/junit-team/junit/wiki/Test-fixtures) for enklere å teste Command - EventSource - state til aggregatet. 
    * Given - denne event historikken 
    * When - denne/disse kommandoer utføres
    * Then - skal tilstanden til aggregatet bli slik (og disse eventer skal bli generert med disse verdier)

**Kanskje det mest spennende for tiden:** 

* Bruke [Hadoop](http://hadoop.apache.org/) som persistering for eventstore (eller annen kilde som kan leses av [Spark](http://spark.apache.org/)) og [Spark](http://spark.apache.org/) for analyse/[Event monitorering](http://en.wikipedia.org/wiki/Event_monitoring)/[Complex event processing](http://en.wikipedia.org/wiki/Complex_event_processing).
* Og evt. [Akka persistence](http://doc.akka.io/docs/akka/current/scala/persistence.html) som Aggregater med en [Journal plugin](http://akka.io/community/) 

**Og videre...**

* ...en mer rik funksjonalitet i domenet
* Versjonering av eventer (med oversettere slik at handler metodene kun trenger å håndtere siste versjonen)
* Snapshotting av aggregate state (med [Memento pattern](http://en.wikipedia.org/wiki/Memento_pattern)) 


