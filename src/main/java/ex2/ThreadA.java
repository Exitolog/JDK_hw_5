package ex2;

public class ThreadA implements Runnable{



    private volatile boolean switcher = false;

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(1000);
                switcher = !switcher;
                System.out.println(switcher);
            } catch (InterruptedException e) {
                System.out.println("Программа закончила работу");
                break;
            }
        }
    }

    public boolean isSwitcher() {
        return switcher;
    }
}
