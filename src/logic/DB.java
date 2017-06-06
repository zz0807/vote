package logic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {

    public static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    public static final String DB_URL
            = "jdbc:mysql://127.0.0.1:3306/vote_system?useUnicode=true&characterEncoding=utf-8";
    public static final String USER = "root";
    public static final String PWD = "root";

    private Connection con = null;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;

    public DB() {
        createConnection();
        try {
            stmt = con.createStatement();
        } catch (SQLException ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }

    public DB(String preparedSql) {
        createConnection();
        try {
            pstmt = con.prepareStatement(preparedSql);
        } catch (SQLException ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }
    public DB(String preparedSql,int s) {
        createConnection();
        try {
            pstmt = con.prepareStatement(preparedSql,s);
        } catch (SQLException ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }

    private void createConnection() {
        try {
            Class.forName(DB_DRIVER);
            con = DriverManager.getConnection(DB_URL, USER, PWD);
        } catch (ClassNotFoundException ex) {
            System.err.println("Error: 类不存在!" + ex.getMessage());
        } catch (SQLException ex) {
            System.err.println("Error: 连接数据库失败!" + ex.getMessage());
        }
    }

    public ResultSet select(String sql) {
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException ex) {
            System.err.println("Error: " + ex.getMessage());
        }
        return rs;
    }

    public int update(String sql) {
        int result = 0;
        try {
            result = stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            System.err.println("Error: " + ex.getMessage());
        }
        return result;
    }

    //preparedStatement的查询方法
    public ResultSet select() {
        ResultSet rs = null;
        try {
            rs = pstmt.executeQuery();
        } catch (SQLException ex) {
            System.err.println("Error: " + ex.getMessage());
        }
        return rs;
    }
    //返回影响条数
    public int update() {
        int result = 0;
        try {
            result = pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error: " + ex.getMessage());
        }
        return result;
    }
     //返回插入后生产的主键
    public int getKey(){
    	int id = 0;
    	 try {
			pstmt.execute();
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs != null&&rs.next()) {  
			    id=rs.getInt(1);
			}  
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return id;
    	
    }

    //以下方法为使用动态SQL语句方式时，设置prestmt的参数的方法---------------------
    //其他类型的参数对应的方法,请自行补充
    public void setString(int index, String value) {
        try {
            pstmt.setString(index, value);
        } catch (SQLException ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }

    public void setInt(int index, int value) {
        try {
            pstmt.setInt(index, value);
        } catch (SQLException ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }

    public void setDouble(int index, double value) {
        try {
            pstmt.setDouble(index, value);
        } catch (SQLException ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }

    public void close() {
        try {
            if (stmt != null) {
                stmt.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }
}
