package hw_seminar_5;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;

public class Philosopher implements Runnable {


    private String name;
    private Fork forkLeft;
    private Fork forkRight;
    private Table table;
    private CountDownLatch cldAll;
    private int countEat = 0;


    public Philosopher(Table table, String name, Fork forkLeft, Fork forkRight, CountDownLatch cldAll) {
        this.name = name;
        this.forkRight = forkRight;
        this.forkLeft = forkLeft;
        this.table = table;
        this.cldAll = cldAll;
    }

    @Override
    public void run() {
        while (countEat < 3) {
            try {
                if(table.takeForks(forkLeft.getNumber(), forkRight.getNumber())){
                    Thread.sleep(500);
                    System.out.println("Философ " + name  + " начал есть");
                    System.out.println("При этом взяв вилку " + forkLeft.getNumber() + " и " + forkRight.getNumber());
                    Thread.sleep(3000);
                    countEat++;
                    System.out.println("Философ " + name + " поел " + countEat + " раз/раза и начал рассуждать о важном ");
                    Thread.sleep(300);
                    table.getForks(forkLeft.getNumber(), forkRight.getNumber());
                    cldAll.countDown();

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.err.println(name + " наелся");
    }
}
