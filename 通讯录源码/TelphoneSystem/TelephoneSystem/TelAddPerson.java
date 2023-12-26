package TelephoneSystem;

import javax.swing.JDialog;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.*;
//用来实现增添读者功能
public class TelAddPerson extends JDialog implements ActionListener {
    //定义我需要的swing组件
    JLabel jl1,jl2,jl3,jl4,jl5,jl6,jl7;
    JTextField jf1,jf2,jf3,jf4,jf5,jf6,jf7;
    JPanel jp1,jp2,jp3;
    JButton jb1,jb2;
    //owner代笔父窗口,title是窗口的名字,modal指定是模式窗口()或者非模式窗口
    public TelAddPerson(Frame owner, String title, boolean modal){
        //调用父类方法
        super(owner,title,modal);
//        columnNames.add("名字");
//        columnNames.add("邮箱");
//        columnNames.add("电话号码");
//        columnNames.add("性别");
//        columnNames.add("标签");

        jl1 = new JLabel("名字");
        jl2 = new JLabel("邮箱");
        jl3 = new JLabel("电话号码");
        jl4 = new JLabel("性别");
        jl5 = new JLabel("标签");



        jf1 = new JTextField(30);
        jf2 = new JTextField(30);
        jf3 = new JTextField(30);
        jf4 = new JTextField(30);
        jf5 = new JTextField(30);

        Color cjf1 =new Color(253,247,228);
        Color cjf2 =new Color(253,247,228);
        Color cjf3 =new Color(253,247,228);
        Color cjf4 =new Color(253,247,228);
        Color cjf5 =new Color(253,247,228);
        jf1.setBackground(cjf1);
        jf2.setBackground(cjf2);
        jf3.setBackground(cjf3);
        jf4.setBackground(cjf4);
        jf5.setBackground(cjf5);


        jb1 = new JButton("添加");
        jb1.addActionListener(this::actionPerformed);
        jb2 = new JButton("取消");
        jb2.addActionListener(this::actionPerformed);
        Color cjb1=new Color(222, 208, 182);
        Color cjb2=new Color(222, 208, 182);
        jb1.setBackground(cjb1);
        jb2.setBackground(cjb2);
        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();

        //设置布局
        jp1.setLayout(new GridLayout(5,1));
        jp2.setLayout(new GridLayout(5,1));

        jp3.add(jb1);
        jp3.add(jb2);

        jp1.add(jl1);
        jp1.add(jl2);
        jp1.add(jl3);
        jp1.add(jl4);
        jp1.add(jl5);


        jp2.add(jf1);
        jp2.add(jf2);
        jp2.add(jf3);
        jp2.add(jf4);
        jp2.add(jf5);

        jp1.setBackground(new Color(255, 247, 212));
        this.add(jp1, BorderLayout.WEST);
        this.add(jp2, BorderLayout.CENTER);
        this.add(jp3, BorderLayout.SOUTH);
        this.setLocation(1200, 190);
        this.setSize(600,400);
        this.setVisible(true);


    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if(e.getSource() == jb1){
            Connection ct = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;

            try{
                //1.加载驱动
                Class.forName("com.mysql.cj.jdbc.Driver");
                System.out.println("加载成功");
                //2.连接数据库
                //定义几个常量
                String url= "jdbc:mysql://localhost:3306/student?useUnicode=true&characterEncoding=UTF-8";
                String user = "root";
                String passwd = "123456";
                ct = DriverManager.getConnection(url,user,passwd);

                //与编译语句对象

                String strsql = "insert into stu(stuName,email,phone,sex,tag) values(?,?,?,?,?)";
                pstmt = ct.prepareStatement(strsql);

                //给对象赋值
                pstmt.setString(1,jf1.getText());
                pstmt.setString(2,jf2.getText());
                pstmt.setString(3,jf3.getText());
                pstmt.setString(4,jf4.getText());
                pstmt.setString(5,jf5.getText());


                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "添加成功看( •̀ ω •́ )✧", "添加情况",-1);
                this.dispose();//关闭学生对话框

            }catch(Exception arg1){
                arg1.printStackTrace();
            }finally{
                try{
                    if(rs!=null){
                        rs.close();
                        rs = null;
                    }
                    if(pstmt != null){
                        pstmt.close();
                        pstmt = null;
                    }
                    if(ct != null){
                        ct.close();
                        ct = null;
                    }
                }catch(Exception arg2){
                    arg2.printStackTrace();
                }
            }

        }else{
            this.dispose();
        }

    }
}