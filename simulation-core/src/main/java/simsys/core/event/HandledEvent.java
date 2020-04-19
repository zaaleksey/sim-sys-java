package simsys.core.event;

import simsys.core.event.handler.EventHandler;
import simsys.core.event.handler.Timeout;
import simsys.core.model.SimulationContext;
import simsys.random.RandomVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 *
 */
public class HandledEvent extends AbstractEvent {
    protected ArrayList<EventHandler> handlers = new ArrayList<>();

    public void addHandler(EventHandler<HandledEvent> eventHandler) {
        handlers.add(eventHandler);
    }

    public List<EventHandler> getAllHandlers() {
        return handlers;
    }

    @Override
    public final void activate() {
        for (EventHandler handler :
                handlers) {
            handler.handle(this);
        }
    }


    public static class HandledEventBuilder {
        // обязательный параметр
        private final SimulationContext simulationContext;

        //здесь основная логика события
        private ArrayList<EventHandler> handlers;

        // здесь служебная обработка, например, логи (возможно автоматический сбор некоторой
        // статистики
        private ArrayList<EventHandler> beforeHandlers;
        private ArrayList<EventHandler> afterHandlers;

        // Если событие периодическое, то добавляем этот хендлер
        private Timeout timeoutHandler;

        private Double startTime;

        public HandledEventBuilder(SimulationContext simulationContext) {
            this.simulationContext = simulationContext;
            handlers = new ArrayList<>();
            beforeHandlers = new ArrayList<>();
            afterHandlers = new ArrayList<>();
        }

        public HandledEventBuilder periodic(Timeout timeout) {
            this.timeoutHandler = timeout;
            return this;
        }

        public HandledEventBuilder periodic(RandomVariable randomVariable) {
            this.timeoutHandler = new Timeout(randomVariable);
            return this;
        }

        public HandledEventBuilder periodic(double interval) {
            this.timeoutHandler = new Timeout(interval);
            return this;
        }

        public HandledEventBuilder periodic(Supplier<Double> activateTimes) {
            this.timeoutHandler = new Timeout(activateTimes);
            return this;
        }


        public HandledEventBuilder addHandler(EventHandler eventHandler) {
            handlers.add(eventHandler);
            return this;
        }

        public HandledEventBuilder addBeforeHandler(EventHandler eventHandler) {
            beforeHandlers.add(eventHandler);
            return this;
        }

        public HandledEventBuilder addAfterHandler(EventHandler eventHandler) {
            afterHandlers.add(eventHandler);
            return this;
        }

        public HandledEventBuilder startTime(double time) {
            this.startTime = time;
            return this;
        }


        public HandledEvent build() {
            HandledEvent handledEvent = new HandledEvent();

            handledEvent.handlers.addAll(beforeHandlers);
            handledEvent.handlers.addAll(handlers);

            if (timeoutHandler != null) {
                timeoutHandler.setSimulationContext(simulationContext);
                handledEvent.handlers.add(timeoutHandler);
            }

            handledEvent.handlers.addAll(afterHandlers);

            if (this.startTime != null) {
                handledEvent.setActivateTime(startTime);
            }
            return handledEvent;
        }

    }


}
