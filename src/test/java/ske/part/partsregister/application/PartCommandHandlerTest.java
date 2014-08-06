package ske.part.partsregister.application;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import ske.eventsourcing.event.Event;
import ske.eventsourcing.eventstore.EventSourceIdentifier;
import ske.eventsourcing.eventstore.EventStore;
import ske.eventsourcing.eventstore.InMemoryEventStore;
import ske.eventsourcing.eventstore.StringEventSourceIdentifier;
import ske.part.partsregister.domain.part.EtternavnEndretEvent;
import ske.part.partsregister.domain.part.FornavnEndretEvent;
import ske.part.partsregister.domain.part.Part;
import ske.part.partsregister.domain.part.PersonOpprettetEvent;

public class PartCommandHandlerTest {

    @Test
    public void shouldOppretteEnPart() {

        EventStore eventStore = new InMemoryEventStore();

        PartCommandHandler partCommandHandler = new PartCommandHandler(eventStore, null);
        EventSourceIdentifier id = new StringEventSourceIdentifier("1");

        // when
        partCommandHandler.handle(new OpprettPersonCommand(id, "Nisse", "Andersson"));

        // then
        List<Event> events = eventStore.getEventsForIdentifier(Part.class, id);
        assertThat(events.size(), is(1));
        assertThat(events.get(0).getClass(), is(PersonOpprettetEvent.class.getClass()));

    }

    @Test
    public void shouldEndreNavn() {
        EventStore eventStore = new InMemoryEventStore();

        PartCommandHandler partCommandHandler = new PartCommandHandler(eventStore, null);
        EventSourceIdentifier id = new StringEventSourceIdentifier("12");

        // when
        partCommandHandler.handle(new OpprettPersonCommand(id, "Nisse", "Andersson"));
        partCommandHandler.handle(new EndreNavnCommand(id, "Kalle", "Karlsson"));

        // then
        List<Event> events = eventStore.getEventsForIdentifier(Part.class, id);
        assertThat(events.size(), is(3));
        assertThat(events.get(0).getClass(), is(PersonOpprettetEvent.class.getClass()));
        assertThat(events.get(1).getClass(), is(FornavnEndretEvent.class.getClass()));
        assertThat(events.get(2).getClass(), is(EtternavnEndretEvent.class.getClass()));

        Part part = eventStore.loadEventSource(Part.class, id);

        assertThat(part.getFornavn(), is("Kalle"));
        assertThat(part.getEtternavn(), is("Karlsson"));
    }
}
