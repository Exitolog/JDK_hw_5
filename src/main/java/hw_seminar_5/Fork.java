package hw_seminar_5;

public class Fork {

    private boolean free;
    private int number;



    public Fork(int number) {
        this.free = true;
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
