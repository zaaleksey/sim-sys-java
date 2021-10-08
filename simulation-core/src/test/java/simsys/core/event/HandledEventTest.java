package simsys.core.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import simsys.core.event.handler.EventHandler;
import simsys.core.exception.TimeErrorException;

class HandledEventTest {

  private HandledEvent event;

  @BeforeEach
  void setUp() {
    event = new HandledEvent();
  }

  @Test
  void checkingCorrectTimeGettingAndSetting() {
    double time = ThreadLocalRandom.current().nextDouble();
    event.setActivateTime(time);
    assertEquals(time, event.getActivateTime());
  }

  @Test
  void throwingExceptionWhenSettingLessActivationEventTime() {
    assertThrows(TimeErrorException.class, () -> {
      event.setActivateTime(Double.MAX_VALUE);
      event.setActivateTime(Double.MIN_VALUE);
    });
  }

  @Test
  void throwingExceptionWhenSettingNegativeTimeValue() {
    assertThrows(TimeErrorException.class, () -> event.setActivateTime(-1.0));
  }

  @Test
  void checkingDelayOfActivationEventForUnknownTime() {
    double initTime = event.getActivateTime();
    assertEquals(initTime, event.getActivateTime());
    event.postpone();
    assertEquals(Double.POSITIVE_INFINITY, event.getActivateTime());
  }

  @Test
  void checkingDelayOfActivationEventForKnownTime() {
    double initTime = event.getActivateTime();
    assertEquals(initTime, event.getActivateTime());
    double duration = ThreadLocalRandom.current().nextDouble();
    event.postpone(duration);
    assertEquals(initTime + duration, event.getActivateTime());
  }

  @Test
  void checkingAdditionOfNewHandlers() {
    assertEquals(0, event.getAllHandlers().size());
    event.addHandler(e -> {
    });
    assertEquals(1, event.getAllHandlers().size());
    assertEquals(event, event.addHandler(e -> {
    }));
    assertEquals(2, event.getAllHandlers().size());
  }

  @Test
  void checkingCallOfOneHandlerForEvent() {
    AtomicInteger atomic = new AtomicInteger();
    event.addHandler(e -> {
      atomic.getAndIncrement();
    });
    assertEquals(0, atomic.get());
    event.activate();
    assertEquals(1, atomic.get());
  }

  @Test
  void checkingForCallingMultipleHandlersForEvent() {
    int numberOfHandlers = ThreadLocalRandom.current().nextInt(5, 20);
    AtomicInteger atomic = new AtomicInteger();
    EventHandler<HandledEvent> increment = e -> atomic.getAndIncrement();

    for (int i = 1; i <= numberOfHandlers; i++) {
      event.addHandler(increment);
    }

    assertEquals(0, atomic.get());
    event.activate();
    assertEquals(numberOfHandlers, atomic.get());
  }

}