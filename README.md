# partsregister-eventsourcing

Test av en enkel [eventsource](https://github.com/eventstore/eventstore/wiki/Event-Sourcing-Basics) versjon av et Partsregister.

Bruker en skrivemodell ([Read model](http://cqrs.wordpress.com/documents/cqrs-and-event-sourcing-synergy/)/[Query model](http://martinfowler.com/bliki/CQRS.html)) 
som nå ligger i samme JVM og lytter på nye eventer over en EventBus 

## Oppbygging
* Et tydelig skille mellom lese og skrivemodell.
* Skrivemodellen skal være en eventstore.
* Eventer skal publiseres slik at nye lesemodeller kan lages efter behov.
* Publisering kan gjøres med en køløsning, ala Vert.x, feeds eller en distribuert NoSQL som Hadoop.
* Lesemodellen kan i starten finnes sammen med skrivemodellen og flyttes til annen JVM/container etter behov.
* Snapshot kan legges på efter behov.


## API
RESTful API som også følger [HATEOAS](http://en.wikipedia.org/wiki/HATEOAS)

/parter
/parter/opprett
/parter/{id}
	/flytte
	/… kommandoer
	/eventer (også som feeds?)

Hver ressurs har underressurser med kommandoer og eventer. **God strategi?**
Kommandoer passer ikke så bra sammen med ressurs og CRUD.

## Bruk
Start Jetty med 
```
mvn jetty:run
```

localhost:8080

## TODO
* _fiks logback (uten Dropwizard)_ -> enkel konfig til konsoll
* Jobbe mer med REST APIet. Få til Jersys linker bl.a.
* Lage [Text Fixtures](https://github.com/junit-team/junit/wiki/Test-fixtures) for enklere å teste Command - EventSource - state til aggregatet. 
    * Given - denne event historikken 
    * When - denne/disse kommandoer utføres
    * Then - skal tilstanden til aggregatet bli slik (og disse eventer skal bli generert med disse verdier)
* Skille ut lesemodellen som lytter på eventer (først ut i egen pakke ske.part.partview) (/rest/partview?)
* Tilby et API for å hente eventer (som feeds? hente fra en gitt aggregate root og en sekvens id? hente alle med paging?)
* Legge til en ny lesemodell som aggregerer informasjon som f.eks. statistikk - enkelt ´Datavarehus´. Lytte på
eventbussen og aggregere informasjon. REST API til dette. (/rest/partmart?)
* Bruke [Vert.x sin EventBus](http://vertx.io/core_manual_java.html#the-event-bus) isteden for Guava slik at hanlere
kan startes i flere JVMer. (Hvordan håndtere at Vert.x sin eventbus går ned? Hvordan håndtere at handlere går ned?
 API som henter eventer fra en gitt id eller tidspunkt, )
* Bruke [Hadoop](http://hadoop.apache.org/) som persistering for eventstore og [Spark](http://spark.apache.org/) som ´Datavarehus´/eventanalyse.

Og videre...

* Så klart en mer rik funksjonalitet i domenet (part)
* Versjonering av eventer (med oversettere slik at handler metodene kun trenger å håndtere siste versjonen)
* Snapshotting av aggregate state (med [Memento pattern](http://en.wikipedia.org/wiki/Memento_pattern)) 
