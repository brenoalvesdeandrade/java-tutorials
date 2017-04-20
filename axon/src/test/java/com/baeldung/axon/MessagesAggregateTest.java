package com.nklkarthi.axon;

import com.nklkarthi.axon.aggregates.MessagesAggregate;
import com.nklkarthi.axon.commands.CreateMessageCommand;
import com.nklkarthi.axon.commands.MarkReadMessageCommand;
import com.nklkarthi.axon.events.MessageCreatedEvent;
import com.nklkarthi.axon.events.MessageReadEvent;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

public class MessagesAggregateTest {

    private FixtureConfiguration<MessagesAggregate> fixture;

    @Before
    public void setUp() throws Exception {
        fixture = new AggregateTestFixture<MessagesAggregate>(MessagesAggregate.class);

    }

    @Test
    public void giveAggregateRoot_whenCreateMessageCommand_thenShouldProduceMessageCreatedEvent() throws Exception {
        String eventText = "Hello, how is your day?";
        String id = UUID.randomUUID().toString();
        fixture.given()
                .when(new CreateMessageCommand(id, eventText))
                .expectEvents(new MessageCreatedEvent(id, eventText));
    }

    @Test
    public void givenMessageCreatedEvent_whenReadMessageCommand_thenShouldProduceMessageReadEvent() throws Exception {
        String id = UUID.randomUUID().toString();

        fixture.given(new MessageCreatedEvent(id, "Hello :-)"))
                .when(new MarkReadMessageCommand(id))
                .expectEvents(new MessageReadEvent(id));
    }
}