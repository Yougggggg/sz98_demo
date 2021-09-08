public class ThreadDemo {
    static int num;
    public static void main(String[] args) {
        ThDemo thDemo=new ThDemo();
        thDemo.start();
        ThTest thTest=new ThTest();
        thTest.start();
        num=35;
        System.out.println("main"+num);
//        for (int i = 0; i < 60; i++) {
//            System.out.println("main run"+i);
//        }
    }
}
//多线程的继承
class ThDemo extends Thread{
    public void run(){
        System.out.println(ThreadDemo.num=10);
//        for (int i = 0; i < 60; i++) {
////            Thread.currentThread() 会返回当前执行线程 对象的引用
//            System.out.println((Thread.currentThread()==this)+this.getName()+"demo run"+i);
//        }
    }
}
class ThTest extends Thread{
    @Override
    public void run() {
        System.out.println(ThreadDemo.num=25);
    }
}
//卖票程序
class Ticket extends Thread{
    private static int tick=100;
    @Override
    public void run() {
        while (true){
            if (tick>0){
                System.out.println(Thread.currentThread().getName()+ "sale: "+tick--);
            }
        }
    }
}
class TickDemo{
    public static void main(String[] args) {
        Ticket ticket=new Ticket();
        Ticket ticket2=new Ticket();
        Ticket ticket3=new Ticket();
        Ticket ticket4=new Ticket();
        //4个窗口同时运行 多线程
        ticket.start();
        ticket2.start();
        ticket3.start();
        ticket4.start();
    }
}
class RunDemo implements Runnable{
    private static int tick=100;
    boolean flag=true;
    @Override
    public void run() {
        if (flag){
            while (true){
                //同步函数用的所就是this
                //静态的同步方法使用的锁是该类的字节码文件对象：类名.class
                synchronized (RunDemo.class){//同步代码块，解决多线程安全问题
                    if (tick>0){
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName()+ "sale: "+tick--);
                    }
                }
            }
        }else {
            while (true){
                show();
            }
        }
    }
    public static synchronized void show(){
        if (tick>0) {
            System.out.println(Thread.currentThread().getName() + "show: " + tick--);
        }
    }
}
class RunTest{
    public static void main(String[] args) {
        //使用实现的方式定义线程，就有个好处能将资源独立出来
        //虽然开启多个线程，但是使用的是同一个Runnable对象中的实现类
        RunDemo runDemo=new RunDemo();
        new Thread(runDemo).start();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        runDemo.flag=false;
        new Thread(runDemo).start();
        //new Thread(runDemo).start();
        //new Thread(runDemo).start();
    }
}
//银行存钱示例
class Bank{
    private int sum;
    Object obj=new Object();
    synchronized void add(int n){
        //synchronized (obj) {
            sum += n;
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("SUM: " + sum);
        //}
    }
}
class Cus implements Runnable{
//存钱的动作会被多线程所执行
    Bank bank=new Bank();
    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            bank.add(100);
        }
    }
}
class BankDemo{
    public static void main(String[] args) {
        Cus cus=new Cus();
        Thread t1=new Thread(cus);
        Thread t2=new Thread(cus);
        t1.start();
        t2.start();
    }
}
//死锁示例
class RnTest implements Runnable{
    private boolean flag;
    RnTest(boolean flag){
        this.flag=flag;
    }
    @Override
    public void run() {
        if (flag){
            //死锁就是同步中嵌套同步，而内外两个锁不同
            synchronized (MyLock.locka){
                System.out.println("if loacka");
                synchronized (MyLock.lockb){
                    System.out.println("if loackb");
                }
            }
        }else  {
            synchronized (MyLock    .lockb){
                System.out.println("else loackb");
                synchronized (MyLock.locka){
                    System.out.println("else loacka");
                }
            }
        }
    }
}
class MyLock{
    static Object locka=new Object();
    static Object lockb=new Object();
}
class DemoLock{
    public static void main(String[] args) {
        Thread t1=new Thread(new RnTest(true));
        Thread t2=new Thread(new RnTest(false));
        t1.start();
        t2.start();
    }
}