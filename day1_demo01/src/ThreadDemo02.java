import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadDemo02 {
    public static void main(String[] args) {
        Resx r=new Resx();
        new Thread(new Input(r)).start();
        new Thread(new output(r)).start();
    }
}
class Resx{
    private String name;
    private String sex;
    private boolean flag=false;
    //多线程之间的通信，确保同步要保证两个线程都加上锁
    //并且锁的对象要相同 在没有r对象时可以使用两个线程类的class对象
    public synchronized void setSave(String name,String sex){
        if (flag) {
            try {
                //等待和唤醒必须时同一个锁 这里方法都是由对象调用的所以用this作为锁
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.name=name;
        this.sex=sex;
        flag=true;
        this.notify();
    }
    public synchronized void out(){
        if (!flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(name+"......"+sex);
        flag=false;
        this.notify();
    }
}
class Input implements Runnable{
    Resx r;
    Input(Resx r){
        this.r=r;
    }
    @Override
    public void run() {
        int a=0;
        while (true){
            if (a==0)
                r.setSave("maik","man");
            else
                r.setSave("丽丽","女");
            a=(a+1)%2;
        }
    }
}
class output implements Runnable{
    Resx r;
    output(Resx r){
        this.r=r;
    }
    @Override
    public void run() {
        while (true){
            r.out();
       }
   }
}
//生产消费例子
class ProduceConsumerDemo{
    public static void main(String[] args) {
        Resource res=new Resource();
        new Thread(new Producer(res)).start();
        new Thread(new Producer(res)).start();
        new Thread(new Consumer(res)).start();
        new Thread(new Consumer(res)).start();
    }
}
class Resource{
    private String name;
    private int count=1;
    private boolean flag=false;
    //多个操作了共享数据 加上同步锁
    //多线程操作共享数据时，当冻结线程获取执行资格时需要在次判断标记
    synchronized void set(String name){
        //多个线程操作同一个run方法时用while循环判断是否等待标记
        while (flag) {//使用while循环让每个线程被唤醒后都再次判断标记
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.name=name+"..."+count++;
        System.out.println(Thread.currentThread().getName()+"生产者。。。"+this.name);
        flag=true;
        this.notifyAll();//避免多个线程在唤醒时只唤醒自己阵营的线程，而导致所有线程都wait()，所以在唤醒时唤醒线程池中所有线程
    }

    synchronized void out(String name){
        while (!flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName()+"消费者******。。。"+this.name);
        flag=false;
        this.notifyAll();
    }
}
//两个线程 生产者和消费者分别操作
class Producer implements Runnable{
    private Resource res;
    Producer(Resource res){
        this.res=res;
    }
    @Override
    public void run() {
        while(true){
            res.set("+商品+");
        }

    }
}
class Consumer implements Runnable{
    private Resource res;
    Consumer(Resource res){
        this.res=res;
    }
    @Override
    public void run() {
        while (true){
            res.out("-商品-");
        }
    }
}
//生产消费例子 升级版
class ProduceConsumerDemo2{
    public static void main(String[] args) {
        Resource2 res=new Resource2();
        new Thread(new Producer2(res)).start();
        new Thread(new Producer2(res)).start();
        new Thread(new Consumer2(res)).start();
        new Thread(new Consumer2(res)).start();
    }
}
class Resource2{
    private String name;
    private int count=1;
    private boolean flag=false;
    private Lock lock=new ReentrantLock();//替换了synchronized功能，可以加锁/解锁 并且一个锁上可以由多个condition对象
    private Condition con=lock.newCondition();//封装了原因的wait、notify、notifyAll等功能
    private Condition con_pro=lock.newCondition();
    void set(String name){
        lock.lock();
        try {
            while (flag)
                con.await();
            this.name=name+"..."+count++;
            System.out.println(Thread.currentThread().getName()+"生产者。。。"+this.name);
            flag=true;
            con_pro.signal();//多个condition对象的唤醒机制
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    void out(String name){
        lock.lock();
        try {
            while (!flag)
                con_pro.await();
            System.out.println(Thread.currentThread().getName()+"消费者******。。。"+this.name);
            flag=false;
            con.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
//两个线程 生产者和消费者分别操作
class Producer2 implements Runnable{
    private Resource2 res;
    Producer2(Resource2 res){
        this.res=res;
    }
    @Override
    public void run() {
        while(true){
            res.set("+商品+");
        }

    }
}
class Consumer2 implements Runnable{
    private Resource2 res;
    Consumer2(Resource2 res){
        this.res=res;
    }
    @Override
    public void run() {
        while (true){
            res.out("-商品-");
        }
    }
}
//停止线程
class StopThread implements Runnable{
    private boolean flag=true;
    @Override
    public synchronized void run() {
        while (flag) {
            try {
                wait();//导致程序挂起，无法读取标记结束线程
            } catch (InterruptedException e) {
                //捕捉到线程中断异常时，interrupt擦除了线程的冻结状态
                //就对标记进行修改，让线程下一次读取标记后结束线程
                System.out.println(Thread.currentThread().getName()+".."+e.toString());
                flag=false;
            }
            System.out.println(Thread.currentThread().getName()+"...run");

        }
    }

}
class StopThreadDemo{
    public static void main(String[] args) {
        StopThread st=new StopThread();
        Thread t1=new Thread(st);
        Thread t2=new Thread(st);
        t1.setDaemon(true);//线程启动前，通过该方法标记为守护线程
        t2.setDaemon(true);//当正在运行的线程都为守护线程时，jvm会退出（前台线程都结束时，后台线程会自动结束）
        t1.start();
        t2.start();
        int num=0;
        while (true){
            if (num++==60)
            {
                //该方法对线程的冻结状态进行清除，强制恢复到运行状态
                //恢复运行状态进行标记读取，结束线程   该方法会抛出线程中断异常
//                t1.interrupt();
//                t2.interrupt();

                break;
            }
            System.out.println(Thread.currentThread().getName()+"........"+num);
        }
    }
}
//join 会获取当前执行线程的cpu执行权
class DemoJ implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 70; i++) {
            System.out.println(Thread.currentThread().getName()+"..."+i);
            Thread.yield();//暂停当前程序，执行其他线程
        }
    }
}
class JoinDemo{
    public static void main(String[] args) throws InterruptedException {
        DemoJ dj=new DemoJ();
        Thread t1=new Thread(dj);
        Thread t2=new Thread(dj);
        t1.start();
        t1.setPriority(Thread.MIN_PRIORITY);//设置线程优先级，默认为5，1，5，10三级最为明显
        //t1.join();//t1获取主线程的执行权
        //如果t1遇到了wait()，主线程就挂了，可以用interrupt清除主线程的冻结状态
        t2.start();
//        for (int i = 0; i < 80; i++) {
//            System.out.println("main...."+i);
//        }
        System.out.println("over");
    }
}
//独立运算时，就行封装提高程序效率，（开启线程的写法）
class ThreadTest{
    public static void main(String[] args) {
        //匿名内部类
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println(Thread.currentThread().getName()+"run");
                }
            }
        }).start();

        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName()+"run");
        }
        //内部类
        Runnable r=new Runnable(){
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println(Thread.currentThread().getName()+"run");
                }
            }
        };
        new Thread(r).start();
    }
}