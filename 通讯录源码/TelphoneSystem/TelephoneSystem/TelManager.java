package TelephoneSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TelManager extends JFrame implements ActionListener {
    //定义一些控件
    private Object[] types = {"-请选择查询方式-", "按姓名查询", "按邮箱查询", "电话查询","性别","标签"};
    private JComboBox searchType = new JComboBox(types);
    //提供一个下拉列表，用户可以从中选择一个选项，例如，在一个学生管理系统中，可以用 JComboBox 来选择查询方式（按学号、姓名等）。
    JPanel jp1,jp2;
    JLabel jl1;
    JButton jb1,jb2,jb3,jb4;
    JTable jt;
    JScrollPane jsp;
    JTextField jtf1,jtf2;
    String strRS;
    dataModel sm;
    //定义连接数据库的变量
    PreparedStatement ps;
    Connection ct = null;
    ResultSet rs = null;
    //构造函数
    public TelManager(){
        Color cst = new Color(238, 231, 218);
        searchType.setBackground(cst);
        Color cjb1 = new Color(238, 231, 218);
        Color cjb2 = new Color(238, 231, 218);
        Color cjb3 = new Color(238, 231, 218);
        Color cjb4 = new Color(239, 64, 64);
        Color jp1color= new Color(198, 207, 155);
        jp1 = new JPanel();
        jp1.setBackground(jp1color);
        jtf1 = new JTextField(10);
        jtf2 = new JTextField();
        jtf2.setEditable(false);
        jb1 = new JButton("查询");
        jb1.addActionListener(this);
        jl1 = new JLabel("总人数：");
        jp1.add(searchType);
        jp1.add(jtf1);
        jp1.add(jb1);
        jp1.add(jl1);
        jp1.add(jtf2);

        jb2 = new JButton("添加");
        jb2.setSize(100,500);
        jb2.addActionListener(this);
        jb3 = new JButton("修改");
        jb3.addActionListener(this);
        jb4 = new JButton("删除");
        jb4.addActionListener(this);

        jp2 = new JPanel();
        Color customColor =new Color(198, 207, 155);
        jp2.add(jb2);
        jp2.add(jb3);
        jp2.add(jb4);
        jp2.setBackground(customColor);
        //创建模型对象
        sm = new dataModel();
        //初始化总人数
        strRS=String.valueOf(sm.getRowCount());
        jtf2.setText(strRS);
        //初始化表和滚动面板
        jt = new JTable(sm);
        Color jtbg = new Color(250, 238, 209);

        jt.setBackground(jtbg);
        jt.setRowHeight(30);
        jsp = new JScrollPane(jt);


        //将jsp放入到jframe中
        this.add(jsp);
        this.add(jp1,BorderLayout.PAGE_START);
        this.add(jp2,BorderLayout.PAGE_END);
        this.setTitle("通讯录管理系统");
//        this.pack();
        this.setSize(600, 400);
        Color guimana = new Color(210, 227, 200);
        this.setBackground(guimana);
        this.setLocation(500, 200);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);

        jb1.setBackground(cjb1);
        jb2.setBackground(cjb2);
        jb3.setBackground(cjb3);
        jb4.setBackground(cjb4);

        Color backgroundColor = new Color(253, 247, 228); // 使用RGB值创建颜色对象，对应颜色 #FDF7E4
        this.getContentPane().setBackground(backgroundColor); // 设置窗口内容面板的背景色为自定义颜色

    }
    public void actionPerformed(ActionEvent arg0) {
        //判断是哪个按钮被点击
        if(arg0.getSource() == jb1){
            System.out.println("用户希望被查询...");
            int index = searchType.getSelectedIndex();
            String sql = new String();
            if(index == 0){
                sql = "select * from stu ";
            }
            else if(index == 1){
                //因为把对表的数据封装到StuModel中，可以比较简单的完成查询
                String name =this.jtf1.getText().trim();
                //写一个sql语句
                sql = "select * from stu where stuName =\"" + name + "\"";

             }
            else if(index == 2){
                String email =this.jtf1.getText().trim();
                sql = "select * from stu where email = '"+email+"' ";

            }
            else if(index == 3){
                String phonenumber =this.jtf1.getText().trim();
                sql = "select * from stu where phone = '"+phonenumber+"' ";

            }
            else if(index == 4){
                String sex =this.jtf1.getText().trim();
                sql = "select * from stu where sex = '"+sex+"' ";
            }
            else if(index ==5){
                String tag =this.jtf1.getText().trim();
                sql = "select * from stu where tag= '"+tag+"' ";

            }
//            并更新
            sm = new dataModel(sql);

            strRS=String.valueOf(sm.getRowCount());
            jtf2.setText(strRS);
            //更新jtable
            jt.setModel(sm);

        }

        //一、弹出添加界面
        else if(arg0.getSource() == jb2){
            System.out.println("添加...");
            TelAddPerson sa = new TelAddPerson(this,"添加联系人",true);
            //重新再获得新的数据模型,
            sm =  new dataModel();
            strRS=String.valueOf(sm.getRowCount());
            jtf2.setText(strRS);
            jt.setModel(sm);
        }else if(arg0.getSource() == jb4){
            //二、删除记录
            //1.得到学生的ID
            int rowNum = this.jt.getSelectedRow();//getSelectedRow会返回给用户点中的行
            //如果该用户一行都没有选，就返回-1
            if(rowNum == -1){
                //提示
                JOptionPane.showMessageDialog(this, "请选中一行");
                return ;
            }
            //得到名字
            String StuName = (String)sm.getValueAt(rowNum, 0);


            //连接数据库,完成删除任务
            try{
                //1.加载驱动
                Class.forName("com.mysql.cj.jdbc.Driver");
                //2.连接数据库
                String url= "jdbc:mysql://localhost:3306/student?useUnicode=true&characterEncoding=UTF-8";
                String user = "root";
                String passwd = "123456";

                ct = DriverManager.getConnection(url, user, passwd);
//                System.out.println("连接成功");
                ps = ct.prepareStatement("delete from stu where StuName = ?");
                ps.setString(1,StuName);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "删除成功的(。>︿<)_θ", "删除情况",JOptionPane.PLAIN_MESSAGE);
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                try{
                    if(rs!= null){
                        rs.close();
                        rs = null;

                    }
                    if(ps!= null){
                        ps.close();
                        ps = null;
                    }
                    if(ct != null){
                        ct.close();
                        ct = null;
                    }
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
            sm = new dataModel();
            strRS=String.valueOf(sm.getRowCount());
            jtf2.setText(strRS);
            //更新jtable
            jt.setModel(sm);
        }else if(arg0.getSource() == jb3){
//            System.out.println("11111");
            //三、用户希望修改
            int rowNum = this.jt.getSelectedRow();
            if(rowNum == -1){
                //提示
                JOptionPane.showMessageDialog(this, "请选择一行");
                return ;
            }
            //显示对话框
//            System.out.println( "12435");
            TelUpInf su = new TelUpInf(this, "修改学生信息", true, sm, rowNum);
            sm = new dataModel();
            jt.setModel(sm);
        }
    }
}