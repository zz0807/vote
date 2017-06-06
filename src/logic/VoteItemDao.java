package logic;

import com.mysql.jdbc.Statement;

import bean.Vote;
import bean.Item;

import java.sql.SQLException;
import java.sql.ResultSet;

import java.util.ArrayList;
public class VoteItemDao {
	
	//插入vote_item表
	public int add(String item,int vote_id){
		
		String sql = "INSERT INTO vote_item(item,vote_id) VALUES(?,?)";
        DB db = new DB(sql);
        db.setString(1, item);
        db.setInt(2,vote_id);
        int result = db.update();
        db.close();
        return result;
	}
	/**
	 * 
	 * @param id 通过item_id把该选项的票数加1
	 * @return 
	 */
	public int plus(int id){
		
		String sql = "update vote_item set number = number+1 where id = ?";
		DB db = new DB(sql);
        db.setInt(1,id);
        
        int result = db.update();
        db.close();
        return result;	
	}
	//通过vote_id得到选项
	public ArrayList<Item> get(int vote_id){
		ArrayList<Item> result = new ArrayList<Item>();
		String sql = "select * from vote_item where vote_id = ?";
		DB db = new DB(sql);
		db.setInt(1, vote_id);
		ResultSet rs = db.select();
	    try {
			while(rs.next()){
				Item item  = new Item();
				item.setId(rs.getInt("id"));
				item.setItem(rs.getString("item"));
				item.setNumber(rs.getInt("number"));		
                result.add(item);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    return result;
		
	}
}
