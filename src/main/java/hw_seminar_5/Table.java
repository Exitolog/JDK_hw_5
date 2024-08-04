package hw_seminar_5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;


public class Table implements Runnable {
    private List<Fork> forkList;
    private final int COUNT_PHILLOSOPHER = 5;
    private List<Philosopher> philosopherList;
    private CountDownLatch cdlAll;


    public Table() {

        this.cdlAll = new CountDownLatch(COUNT_PHILLOSOPHER);

         this.forkList = new ArrayList<>(Arrays.asList(
                new Fork(0),
                new Fork(1),
                new Fork(2),
                new Fork(3),
                new Fork(4)
        ));
        this.philosopherList = new ArrayList<>(Arrays.asList(
                new Philosopher(this,"Аристотель", forkList.get(0), forkList.get(1),cdlAll),
                new Philosopher(this, "Гомер",  forkList.get(1), forkList.get(2), cdlAll),
                new Philosopher(this,"Платон",  forkList.get(2), forkList.get(3),cdlAll),
                new Philosopher(this,"Пифагор", forkList.get(3), forkList.get(4),cdlAll),
                new Philosopher(this,"Архимед", forkList.get(4), forkList.get(0),cdlAll)
        ));


    }

        public synchronized boolean takeForks(int leftFork, int rightFork){
            if(forkList.get(leftFork).isFree() && forkList.get(rightFork).isFree()){
                forkList.get(leftFork).setFree(false);
                forkList.get(rightFork).setFree(false);
                return true;
            }
            return false;
        }

        public void getForks(int leftFork, int rightFork){
            forkList.get(leftFork).setFree(true);
            forkList.get(rightFork).setFree(true);
        }

    @Override
    public void run() {
        System.out.println("Все философы сели за стол");
        try {
            for (Philosopher philosopher : philosopherList) {
                new Thread(philosopher).start();
            }
            cdlAll.await();
            Thread.sleep(2000);
            System.out.println("Все философы поели 3 раза");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

