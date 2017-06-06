package logic;

import bean.Vote;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.jdbc.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
public class VoteInfoDao {
	  /**
     * 按主键查询
     *
     * @param id
     * @return null 表示查询失败
     */
    public Vote get(int id) {
        Vote result = null;
        String sql = "SELECT * FROM vote_info WHERE id = ?";
        DB db = new DB(sql);
        db.setInt(1, id);
        ResultSet rs = db.select();
        try {
            if (rs.next()) {
                result = new Vote();
                result.setId(rs.getInt("id"));
                result.setTheme(rs.getString("theme"));
                result.setStartDate(rs.getString("start_date"));
                result.setEndDate(rs.getString("end_date"));
                result.setType(rs.getString("type"));
                result.setAccount(rs.getString("account"));
                
                int show = 1;
                //比较时间
                Date dt=new Date();
                if(rs.getDate("end_date").compareTo(dt)<=0){
                	show = 0;
                }
                else show = 1;
                result.setShow(show);   

            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally {
            db.close();
        }

        return result;
    }
    /**
     * 按账号查询
     *
     * @param account
     * @return null 表示查询失败,返回数据集
     */
    public ArrayList<Vote> get(String account) {
    	
        Vote result = null;
    	ArrayList<Vote> a = new ArrayList<Vote>();
        String sql = "SELECT * FROM vote_info WHERE account = ? order by start_date desc";
        DB db = new DB(sql);
        db.setString(1, account);
        ResultSet rs = db.select();
       
        try {
			while(rs.next()){
				    result = new Vote();
	                result.setId(rs.getInt("id"));
	                result.setTheme(rs.getString("theme"));
	                result.setStartDate(rs.getString("start_date"));
	                result.setEndDate(rs.getString("end_date"));
	                result.setType(rs.getString("type"));
	                result.setStatus(rs.getInt("status"));
	                a.add(result);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        db.close();
        return a;
    }

    //插入vote_info表
	 public int add(Vote vote) {
		    
	        String sql = "INSERT INTO vote_info(theme,owner,start_date,end_date,type,account) VALUES(?,?,?,?,?,?)";
	        DB db = new DB(sql,Statement.RETURN_GENERATED_KEYS);
	        db.setString(1, vote.getTheme());
	        db.setString(2, vote.getOwner());
	        db.setString(3, vote.getStartDate());
	        db.setString(4, vote.getEndDate());
	        db.setString(5, vote.getType());
	        db.setString(6, vote.getAccount());
	        int id = db.getKey();
	        db.close();
	        return id;
}
	 //根据ID修改状态（禁止或恢复投票）
	 public int modifyStatus(int id,int status){
		 	String sql =  "update vote_info set status = ? where id = ?";
			DB db = new DB(sql);
	        db.setInt(1,status);
	        db.setInt(2, id);
	        int result = db.update();
	        db.close();
	        return result;	
	 }
	 
}
