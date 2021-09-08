package day22;

import java.awt.*;
import java.awt.event.*;

public class FrameDemo {

    private Frame f;
    private Button btn;
    private TextField tf;


    public FrameDemo() {
        init();
    }

    private void init() {
        f = new Frame("my Frame");
        f.setBounds(300,200,600,500);
        f.setLayout(new FlowLayout());



        btn = new Button("my button");
        f.add(btn);

        tf=new TextField(80);
        f.add(tf);

        myEvent();
        f.setVisible(true);
    }

    private void myEvent() {

        tf.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (!(e.getKeyCode()>=KeyEvent.VK_0 && e.getKeyCode()<=KeyEvent.VK_9)){
                    System.out.println("非法输入");
                    e.consume();//取消默认事件
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                System.out.println("laialialaialiaali");
            }
        });

        btn.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode()==27)
                    System.exit(0);
                if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_ENTER)
                    System.out.println("runrunrun");

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }


    public static void main(String[] args) {
        new FrameDemo();
    }
}
