package ske.part.partsregister.domain.part;

import com.google.common.collect.Lists;
import org.junit.Test;
import ske.eventsourcing.event.DomainEvent;
import ske.eventsourcing.eventstore.StringEventSourceIdentifier;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PartTest {

    @Test
    public void shouldChangeFirstNameOfPart() {
        //given
        Part part = new Part(new StringEventSourceIdentifier("1"), "Kalle", "Nilsson");

        //when
        part.endreFornavn("Nisse");

        //then
        assertThat(part.getFornavn(), is("Nisse"));
        assertThat(part.getUnsavedEvents().size(), is(2));
    }

    @Test
    public void shouldLoadAggregateFromEvents() {
        //given
        StringEventSourceIdentifier id = new StringEventSourceIdentifier("1");
        Part part = new Part(id);

        PersonOpprettetEvent opprettetEvent = new PersonOpprettetEvent(id, "Kalle", "Olsson");
        FornavnEndretEvent fornavnEndretEvent1 = new FornavnEndretEvent(id, "Nisse");
        FornavnEndretEvent fornavnEndretEvent2 = new FornavnEndretEvent(id, "Anna");
        List<DomainEvent> events = Lists.newArrayList(opprettetEvent, fornavnEndretEvent1, fornavnEndretEvent2);

        //when
        part.load(events);

        //then
        assertThat(part.getFornavn(), is("Anna"));
        assertThat(part.getUnsavedEvents().size(), is(0));

    }


}
