package ske.eventsourcing.util;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import java.lang.reflect.Method;

import org.junit.Test;
import ske.eventsourcing.domain.SimpleAggregateRoot;
import ske.eventsourcing.event.DomainEvent;
import ske.eventsourcing.eventstore.EventSourceIdentifier;
import ske.eventsourcing.eventstore.StringEventSourceIdentifier;

public class ReflectionUtilsTest {

	@Test
	public void shouldFindMethod() {

		DomainEvent event = new TestFornavnEndretEvent(new StringEventSourceIdentifier("1"), "kalle");

		Method method = ReflectionUtils.findMethod(TestPerson.class, "handle", event.getClass());
		assertThat(method.getName(), is(equalTo("handle")));
	}

	@Test
	public void shouldNotFindMethodNoParameterEvent() {
		DomainEvent event = new TestFornavnEndretEvent(new StringEventSourceIdentifier("1"), "kalle");

		Method method = ReflectionUtils.findMethod(TestPerson.class, "handle", "A".getClass());
		assertThat("Should not find a method with String as parameter to call", method, is(nullValue()));

	}

	// *************************
	// Interne hjelpeklasser
	// *************************

	class TestPerson extends SimpleAggregateRoot {

		String fornavn;

		public TestPerson(StringEventSourceIdentifier id) {
			super(id);
		}

		public void handle(TestFornavnEndretEvent event) {
			this.fornavn = event.getFornavn();
		}
	}

	class TestFornavnEndretEvent extends DomainEvent {

		private final String fornavn;

		public TestFornavnEndretEvent(EventSourceIdentifier id, String fornavn) {
            super(id);
			this.fornavn = fornavn;
		}

		public String getFornavn() {
			return fornavn;
		}
	}


}
