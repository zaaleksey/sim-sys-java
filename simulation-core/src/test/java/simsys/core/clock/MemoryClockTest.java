package simsys.core.clock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.concurrent.ThreadLocalRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import simsys.core.exception.TimeErrorException;

class MemoryClockTest {

  private Clock clock;

  @BeforeEach
  void setUp() {
    clock = new MemoryClock();
  }

  @Test
  void checkingIfCurrentTimeIsSetCorrectly() {
    double time = 0;
    for (int i = 0; i < 20; i++) {
      time += ThreadLocalRandom.current().nextDouble();
      clock.setCurrentTime(time);
      assertEquals(time, clock.currentTime());
    }
  }

  @Test
  void checkingCorrectnessCalculationOFDeltaTime() {
    double time = ThreadLocalRandom.current().nextDouble();
    clock.setCurrentTime(time);
    clock.setCurrentTime(time + ThreadLocalRandom.current().nextDouble());
    assertEquals(clock.currentTime() - time, clock.deltaTime());
  }

  @Test
  void throwingExceptionWhenSettingLessTime() {
    assertThrows(TimeErrorException.class, () -> {
      clock.setCurrentTime(Double.MAX_VALUE);
      clock.setCurrentTime(Double.MIN_VALUE);
    });
  }

  @Test
  void throwingExceptionWhenSettingNegativeTimeValue() {
    assertThrows(TimeErrorException.class, () -> clock.setCurrentTime(-1));
  }

}