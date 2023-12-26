package TelephoneSystem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;

public class TelLogin extends JFrame {
    private TelLogin self;
    private ImageIcon imageIcon;//存储图标
    private Image image;//存储图片
    private String userid;// 登陆用户名和密码
    private String password;
    private JLabel unLabel = new JLabel("账号：");// 登陆面板控件
    private JTextField unField = new JTextField();//文本框
    private JLabel pwLabel = new JLabel("密码：");//密码框
    private JPasswordField pwField = new JPasswordField();
//    这行代码创建了一个密码字段，用于接收用户输入的密码信息
    private JButton dl = new JButton("登录");
    private JButton d2 = new JButton("重置");
    public TelLogin() {

        this.self = this;
        this.setSize(720, 480);// 设置登陆面板
        ////设置窗口背景图
        //先将contentPane设置成透明的
        ((JPanel)getContentPane()).setOpaque(false);
        //再设置图片
        imageIcon = new ImageIcon("zywoo.jpg");//图标组件
        image = imageIcon.getImage();
        JLabel imgLabel = new JLabel(imageIcon);
        getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
        imgLabel.setBounds(0,0,720,480); //背景图片的位置

        this.setIconImage(image);//设置窗口图像
        this.setLocation(720,480);
        this.setVisible(true);
        this.setResizable(false);
        this.setLayout(null);
//      this.getContentPane().setBackground(Color.BLACK);设置窗口背景色；

        //设置窗口名称
        this.setTitle("通讯录管理系统");
        unLabel.setSize(50, 30);
        unLabel.setLocation(60, 40);
        unLabel.setForeground(Color.black);
        unField.setSize(150, 35);
        unField.setLocation(110, 35);
        pwLabel.setSize(50, 30);
        pwLabel.setLocation(60, 100);
        pwLabel.setForeground(Color.BLACK);
        pwField.setSize(150, 35);
        pwField.setLocation(110, 100);
        dl.setSize(80, 35);
        dl.setLocation(65, 175);
        dl.setBackground(Color.ORANGE);
        d2.setSize(80, 35);
        d2.setLocation(185, 175);
        d2.setBackground(Color.ORANGE);
        dl.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                userid = unField.getText();
                password = pwField.getText();
                if(userid.equals("")&&password.equals("")) {
                    self.setVisible(false);
//                    JOptionPane.showMessageDialog(null, "登录成功", "登录情况",JOptionPane.PLAIN_MESSAGE);
                    new TelManager();
                } else {
                    JOptionPane.showMessageDialog(null, "账号或密码错误！", "登录情况",JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
        d2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                unField.setText("");
                pwField.setText("");
            }
        });
        this.add(unLabel);
        this.add(unField);
        this.add(pwLabel);
        this.add(pwField);
        this.add(dl);
        this.add(d2);

    }

}
