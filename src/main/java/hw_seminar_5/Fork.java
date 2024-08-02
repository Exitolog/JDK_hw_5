package hw_seminar_5;

public class Fork {

    private boolean free = true;
    private int number;



    public Fork(int number) {
        this.number = number;
    }

    public synchronized void setFree(boolean free) {
        this.free = free;
    }

    public synchronized boolean isFree() {
        return free;
    }

    public synchronized int getNumber() {
        return number;
    }
}
