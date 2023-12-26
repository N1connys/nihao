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
/*
// * 是修改学生信息

 */
public class TelUpInf extends JDialog implements ActionListener {
    //定义我需要的swing组件
    JLabel jl1,jl2,jl3,jl4,jl5,jl6,jl7;
    JTextField jf1,jf2,jf3,jf4,jf5,jf6,jf7;
    JPanel jp1,jp2,jp3;
    JButton jb1,jb2;
    //owner代笔父窗口,title是窗口的名字,modal指定是模式窗口()或者非模式窗口
    public TelUpInf(Frame owner, String title, boolean modal, dataModel sm, int rowNum){
        //调用父类方法
        super(owner,title,modal);

        jl1 = new JLabel("名字");
        jl2 = new JLabel("邮箱");
        jl3 = new JLabel("电话号码");
        jl4 = new JLabel("性别");
        jl5 = new JLabel("标签");



        jf1 = new JTextField(30);
        jf1.setText((sm.getValueAt(rowNum, 0)).toString());

        jf2 = new JTextField(30);
        jf2.setText((String)sm.getValueAt(rowNum, 1));
        jf3 = new JTextField(30);
        jf3.setText(sm.getValueAt(rowNum, 2).toString());
        jf4 = new JTextField(30);
        jf4.setText((sm.getValueAt(rowNum, 3)).toString());
        jf5 = new JTextField(30);
        jf5.setText((String)sm.getValueAt(rowNum, 4));
        jf6=new JTextField(30);
        jf6.setText((String)sm.getValueAt(rowNum, 5));
;

        jb1 = new JButton("修改");
        jb1.addActionListener(this::actionPerformed);
        jb2 = new JButton("取消");
        jb2.addActionListener(this::actionPerformed);

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


        this.add(jp1, BorderLayout.WEST);
        this.add(jp2, BorderLayout.CENTER);
        this.add(jp3, BorderLayout.SOUTH);
        this.setLocation(600, 350);
        this.setSize(300,200);
        this.setVisible(true);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        String orgin=jf1.getText();
        // TODO Auto-generated method stub
        if(e.getSource() == jb1){
            Connection ct = null;
            PreparedStatement pstmt=null;
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
                String strsql = "UPDATE stu SET stuName=?, email=?, phone=?, sex=?, tag=? WHERE id=?";
                System.out.println("修改了22222222222222222222");
//                String strsql = "update stu set stuName = '"+jf1.getText()+"',email = '"+jf2.getText()+"',phone = '"+jf3.getText()+"',sex='"+jf4.getText()+"',tag='"+jf5.getText()+"' where stuName = '"+jf1.getText()+"'";
//                pstmt = ct.prepareStatement(strsql);
//
//                pstmt.executeUpdate();
                // 设置参数
               pstmt = ct.prepareStatement(strsql);
                pstmt.setString(1, jf1.getText().trim());
                System.out.println("到12345了");
                System.out.println(jf1.getText());
                System.out.println(pstmt.toString());

                pstmt.setString(2, jf2.getText().trim());
                pstmt.setString(3, jf3.getText().trim());
                pstmt.setString(4, jf4.getText().trim());
                pstmt.setString(5, jf5.getText().trim());
                pstmt.setString(6,jf6.getText()); // 使用 WHERE 子句的参数
                System.out.println();
                pstmt.executeUpdate();
                // 执行更新
                System.out.println("执行 ");
                JOptionPane.showMessageDialog(null, "修改成功(*￣0￣)φ(*￣0￣)", "修改情况",JOptionPane.PLAIN_MESSAGE);
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
            this.dispose();//关闭学生对话框
        }

    }


}