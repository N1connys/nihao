package TelephoneSystem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.table.*;

import static java.sql.DriverManager.getConnection;

/*
  用来刷新、呈现数据库
 * 这是我的一个stu表的模型
 * 可以把对学生表的操作全都封装到这个类
 */
public class dataModel extends AbstractTableModel{

    //rowData存放行数据，columnNames存放列名
    Vector rowData,columnNames;//Vector和ArrayList一样，底层也是一个Object类型的数组Object[]。;    构造一个空向量，使其内部数据数组的大小为10，其标准容量增量为零

    //定义连接数据库的变量
    Statement stat = null;
    Connection ct = null;
    ResultSet rs = null;

    //初始化
    public void init(String sql){
        if(sql.equals("")){
            sql = "select * from stu";
        }
        //中间
        //设置列名
        columnNames = new Vector();//这里是一维向量表示列；
        columnNames.add("名字");
        columnNames.add("邮箱");
        columnNames.add("电话号码");
        columnNames.add("性别");
        columnNames.add("标签");
        columnNames.add("序号（自增）");


        //rowData存放多行
        rowData = new Vector();
        try{
            //1.加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("加载成功");
            //2.连接数据库
            //定义几个常量
//            String url = "jdbc:mysql://localhost:3306/student";
            String url= "jdbc:mysql://localhost:3306/student?useUnicode=true&characterEncoding=UTF-8";
            String user = "root";
            String passwd = "123456";

            ct = getConnection(url,user,passwd);
            stat = ct.createStatement();//创建stat对象
            rs = stat.executeQuery(sql);//查询结果

            while(rs.next()){                     Vector hang = new Vector();
                hang.add(rs.getString(1));
                hang.add(rs.getString(2));
                hang.add(rs.getString(3));
                hang.add(rs.getString(4));
                hang.add(rs.getString(5));
                hang.add(rs.getString(6));


                //加入到rowData中
                rowData.add(hang);//这里是二维向量，表示行；
            }

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(rs!=null){
                    rs.close();
                    rs = null;
                }
                if(stat != null){
                    stat.close();
                    stat = null;
                }
                if(ct != null){
                    ct.close();
                    ct = null;
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }


    //第二个构造函数，通过传递的sql语句来获得数据模型
    public dataModel(String sql){
        this.init(sql);
    }

    //构造函数，用于初始化我的数据模型（表）
    public dataModel(){
        this.init("");
    }

    //得到共有多少行
    public int getRowCount() {
        // TODO Auto-generated method stub
        return this.rowData.size();
    }

    //得到共有多少列
    public  int getColumnCount() {
        // TODO Auto-generated method stub
        return this.columnNames.size();
    }

    //得到某行某列的数据
    public Object getValueAt(int row, int column) {
        // TODO Auto-generated method stub
        return ((Vector)(this.rowData.get(row))).get(column);
    }

    //得到属性名字
    public String getColumnName(int column) {
        // TODO Auto-generated method stub
        return (String)this.columnNames.get(column);
    }
}