package eatingPhilo;

/**
 * Created by Anton on 14.05.17.
 */
public class PhilosopherMonitor {
    //Состояния философов
    private enum State {THINKING, HUNGRY, EATING};
    // Стол состояний
    private State[] philosopherState;

    // Сначала все филосовы думают
    public PhilosopherMonitor (int numPhilosophers) {
        philosopherState = new State[numPhilosophers];
        for (int i = 0; i < philosopherState.length; i++) {
            philosopherState[i] = State.THINKING;
        }
    }

    // Если голоден берет две вилки, иначе ждет пока соседи поедят
    public synchronized void takeSpoons(int sequenceOfPhil) throws InterruptedException {

        philosopherState[sequenceOfPhil] = State.HUNGRY;
        System.out.println("Философ " + sequenceOfPhil + " хочет кушать!\n");

        // проверка кушают ли рядом, если кушают то ждать
        while (checkNeigbours(sequenceOfPhil)) {
            wait();
        }

        // меняем состояние что он теперь ест
        philosopherState[sequenceOfPhil] = State.EATING;
        System.out.println("Философ " + sequenceOfPhil + " уже ест!!\n");

    }

    //Проверить едят ли соседи, true - если да
    private boolean checkNeigbours(int sequenceOfPhil) {
        // проверка с одной стороны, берем остаток от деления, чтобы не было IndexOutOfBounds (как по кругу)
        if (philosopherState[(sequenceOfPhil + 1) % philosopherState.length] == State.EATING){
            return true;
        }

        // проверка с другой стороны
        if (philosopherState[(sequenceOfPhil + philosopherState.length - 1) % philosopherState.length] == State.EATING){
            return true;
        }

        // никто не ест
        return false;
    }

    // Положить вилки после еды
    public synchronized void putDownChopsticks(int sequenceOfPhil) {
        philosopherState[sequenceOfPhil] = State.THINKING;
        notifyAll();
    }

}
