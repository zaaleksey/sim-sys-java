package simsys.core.event.handler;

import simsys.core.event.Event;


/**
 * Теперь это функциональный интерфейс, надеюсь, что так будет удобнее для использования в дальнейшем
 * Также убрал следующие хендлеры (next), событие просто будет хранить массив хендлеров, это упрощает процесс, и с другой
 * стороны практически эвкиваленто
 * предыдущему подходу
 *
 * @param <T>
 */
@FunctionalInterface
public interface EventHandler<T extends Event> {
    void handle(T event);
}
