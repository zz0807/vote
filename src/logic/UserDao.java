package logic;

import bean.User;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
	
	 public User get(String account) {
	        User result = null;
	        String sql = "SELECT * FROM user WHERE account= ?";
	        DB db = new DB(sql);
	        db.setString(1, account);
	        ResultSet rs = db.select();
	        try {
	            if (rs.next()) {
	                result = new User();
	                result.setId(rs.getInt("id"));
	                result.setAccount(rs.getString("account"));
	                result.setUsername(rs.getString("username"));
	                result.setPassword(rs.getString("password"));
	               
	            }
	        } catch (SQLException ex) {
	            System.out.println("Error: " + ex.getMessage());
	        } finally {
	            db.close();
	        }
	        return result;
	    }

	
	 public int add(User user) {
	        String sql = "INSERT INTO user(account,username,password) VALUES(?,?,?)";
	        DB db = new DB(sql);
	        db.setString(1, user.getAccount());
	        db.setString(2, user.getUsername());
	        db.setString(3, user.getPassword());
	        int result = db.update();
	        db.close();
	        return result;
}
	 
	
}
