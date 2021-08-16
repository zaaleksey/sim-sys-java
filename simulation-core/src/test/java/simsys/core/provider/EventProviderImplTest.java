package simsys.core.provider;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import simsys.core.event.Event;
import simsys.core.event.HandledEvent;

class EventProviderImplTest {

  private EventProvider provider;

  @BeforeEach
  void setUp() {
    provider = new EventProviderImpl(Collections.emptyList());
  }

  @Test
  void checkingCountOfEventsWhenAddingOne() {
    assertEquals(0, provider.count());
    provider.add(new HandledEvent());
    assertEquals(1, provider.count());
  }

  @Test
  void checkingCountOfEventsWhenAddingCollection() {
    assertEquals(0, provider.count());
    Collection<Event> events = List.of(new HandledEvent(), new HandledEvent(), new HandledEvent());
    provider.addAll(events);
    assertEquals(events.size(), provider.count());
  }

  @Test
  void checkingForEmptinessOfEventList() {
    assertTrue(provider.isEmpty());
    provider.add(new HandledEvent());
    assertFalse(provider.isEmpty());
  }

  @Test
  void addingEventAndCheckingForEqualityOfAddedEvent() {
    double time = ThreadLocalRandom.current().nextDouble();
    HandledEvent trueEvent = new HandledEvent();
    trueEvent.setActivateTime(time);
    provider.add(trueEvent);
    assertEquals(trueEvent, provider.peek());
    HandledEvent falseEvent = new HandledEvent();
    falseEvent.setActivateTime(time);
    assertNotEquals(falseEvent, provider.peek());
  }

  @Test
  void addingCollectionAndCheckingForEqualityOfCollections() {
    HandledEvent event1 = new HandledEvent();
    event1.setActivateTime(ThreadLocalRandom.current().nextDouble());
    HandledEvent event2 = new HandledEvent();
    event2.setActivateTime(ThreadLocalRandom.current().nextDouble());
    HandledEvent event3 = new HandledEvent();
    event3.setActivateTime(ThreadLocalRandom.current().nextDouble());
    Collection<Event> events = List.of(event1, event2, event3);
    provider.addAll(events);
    assertEquals(events, provider.getAllEvents());
  }

  @Test
  void addingMultipleEventsAndCheckingToReturnNearestOneWithoutDeletingFromProvider() {
    int numberOfEventsCreatedInThisMethod = 2;
    HandledEvent nearestEvent = new HandledEvent();
    nearestEvent.setActivateTime(Double.MIN_VALUE);
    provider.add(nearestEvent);
    HandledEvent event = new HandledEvent();
    event.setActivateTime(Double.MAX_VALUE);
    provider.add(event);
    assertEquals(numberOfEventsCreatedInThisMethod, provider.count());
    Event peekEvent = provider.peek();
    assertEquals(nearestEvent, peekEvent);
    assertEquals(nearestEvent.getActivateTime(), peekEvent.getActivateTime());
    assertTrue(provider.getAllEvents().contains(nearestEvent));
    assertEquals(numberOfEventsCreatedInThisMethod, provider.count());
  }

  @Test
  void addingMultipleEventsAndCheckingIfEventIsRemovedFromProviderWhenCallingGetterOfNextEvent() {
    int numberOfEventsCreatedInThisMethod = 2;
    HandledEvent nearestEvent = new HandledEvent();
    nearestEvent.setActivateTime(Double.MIN_VALUE);
    provider.add(nearestEvent);
    HandledEvent event = new HandledEvent();
    event.setActivateTime(Double.MAX_VALUE);
    provider.add(event);
    assertEquals(numberOfEventsCreatedInThisMethod, provider.count());
    Event nextEvent = provider.getNext();
    assertEquals(nearestEvent, nextEvent);
    assertEquals(nearestEvent.getActivateTime(), nextEvent.getActivateTime());
    assertFalse(provider.getAllEvents().contains(nearestEvent));
    assertNotEquals(numberOfEventsCreatedInThisMethod, provider.count());
    assertEquals(numberOfEventsCreatedInThisMethod - 1, provider.count());
  }

  @Test
  void checkingIfEventIsRemovedFromProvider() {
    HandledEvent furtherRemovedEvent = new HandledEvent();
    provider.add(furtherRemovedEvent);
    assertEquals(1, provider.count());
    assertTrue(provider.remove(furtherRemovedEvent));
    assertFalse(provider.remove(furtherRemovedEvent));
    assertFalse(provider.getAllEvents().contains(furtherRemovedEvent));
    assertNotEquals(1, provider.count());
    assertEquals(0, provider.count());
  }

}