package model;

import model.grille.Grille;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class SimpleModelEventPublisherTest {

    private SimpleModelEventPublisher publisher;
    private ModelListener listener1;
    private ModelListener listener2;
    private Grille grille;
    private EventFromModel event;

    @BeforeEach
    void setUp() {
        publisher = new SimpleModelEventPublisher();
        listener1 = mock(ModelListener.class);
        listener2 = mock(ModelListener.class);
        grille = mock(Grille.class);
        event = new EventFromModel(EventFromModelType.AJOUT_SOLUTION, 1, "Test message");
    }

    @Test
    void testAddListenerAndPublish() {
        publisher.addListener(listener1);
        publisher.addListener(listener2);

        publisher.publish(grille, event);

        verify(listener1, times(1)).onEventFromModel(grille, event);
        verify(listener2, times(1)).onEventFromModel(grille, event);
    }

    @Test
    void testPublishWithNoListeners() {
        // Should not throw or do anything
        publisher.publish(grille, event);
        assertTrue(true);
        // No verification needed, just ensuring no exception is thrown
    }
}
