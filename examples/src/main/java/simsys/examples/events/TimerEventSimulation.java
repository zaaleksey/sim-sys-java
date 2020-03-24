package simsys.examples.events;


import simsys.core.model.SimulationModelImpl;

public class TimerEventSimulation {


    public static void main(String[] args) {

        System.out.println("Timer simulation");

        //Написать таймер: единственное повторяемое событие обработчик которого выводит на экран текущее время
        //условие останова - достижение некоторого момента времени
        SimulationModelImpl timer = new SimulationModelImpl();

    }
}
