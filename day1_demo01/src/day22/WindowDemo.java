package day22;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class WindowDemo {

    private Frame frame;
    private TextField tf;
    private Button btn;
    private TextArea tx;
    private MenuBar menuBar;
    private Menu menu;
    private MenuItem open;
    private MenuItem save;
    private MenuItem exit;
    private FileDialog openDg;
    private FileDialog saveDg;
    private File file;

    public WindowDemo() {
        init();
    }

    private void init() {
        frame = new Frame("My Frame");
        frame.setBounds(300,200,600,500);
        frame.setLayout(new FlowLayout());

        menuBar=new MenuBar();
        menu=new Menu("File");
        open=new MenuItem("Open File");
        save=new MenuItem("Save File");
        exit=new MenuItem("exit");

        tf=new TextField(60);
        btn=new Button("Button");
        tx=new TextArea(25,70);

        frame.add(tf);
        frame.add(btn);
        frame.add(tx);

        menu.add(open);
        menu.add(save);
        menu.add(exit);
        menuBar.add(menu);
        frame.setMenuBar(menuBar);

        myEvent();
        frame.setVisible(true);

    }

    private void showDir(){
        String dirPath = tf.getText();
        File file=new File(dirPath);
        if (file.exists() && file.isDirectory()){
            tx.setText("");
            String[] list = file.list();
            for (String s: list) {
                tx.append(s+"\r\n");
            }
        }else {
            Dialog dialog=new Dialog(frame,"提示信息",true);//如果位true，所属窗口无法操作
            dialog.setBounds(400,200,240,150);
            dialog.setLayout(new FlowLayout());
            Label label = new Label();
            Button button = new Button("确定");
            dialog.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    dialog.setVisible(false);
                }
            });
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dialog.setVisible(false);
                }
            });
            button.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode()==KeyEvent.VK_ENTER)
                        dialog.setVisible(false);
                }
            });

            label.setText("输入路径"+dirPath+"错误");
            dialog.add(label);
            dialog.add(button);
            dialog.setVisible(true);
        }
    }
    private void myEvent() {

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (file==null){
                    saveDg=new FileDialog(frame,"file sava",FileDialog.SAVE);
                    saveDg.setVisible(true);
                    String directory = saveDg.getDirectory();
                    String f = saveDg.getFile();
                    if (directory==null||f==null)
                        return;
                    file = new File(directory, f);
                }
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                    String s=tx.getText();
                    writer.write(s);
                    //writer.flush();
                    writer.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }
        });

        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openDg=new FileDialog(frame,"File Open",FileDialog.LOAD);
                openDg.setVisible(true);
                String directory = openDg.getDirectory();
                String f = openDg.getFile();
                if (directory==null||f==null)
                    return;
                tx.setText("");
                BufferedReader reader=null;
                file = new File(directory,f);
                try {
                    reader = new BufferedReader(new FileReader(file));
                    String line;
                    while ((line=reader.readLine())!=null) {
                        tx.append(line + "\r\n");
                    }
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }finally {
                    try {
                        reader.close();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
        });

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveDg=new FileDialog(frame,"File Save",FileDialog.SAVE);
                saveDg.setVisible(true);
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        tf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_ENTER)
                    showDir();
            }
        });

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDir();
            }
        });

    }

    public static void main(String[] args) {
        new WindowDemo();
    }
}
