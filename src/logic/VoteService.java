package logic;

import bean.Vote;
import bean.User;
import bean.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
public class VoteService {
	/*
	 * �������ͶƱ��Ϣд�����ݿ�
	 */
	public int add(Vote vote){
		
		int result = 0;
		VoteInfoDao vinfod = new VoteInfoDao();
		VoteItemDao vitemd = new VoteItemDao();
		int vote_id = vinfod.add(vote);
		System.out.println("vote_id:"+vote_id);
			if(vote_id <= 0)
				result =  -1;//���ʧ��
		int item_rs;
		for(Item item:vote.getItem()){
	    item_rs = vitemd.add(item.getItem(),vote_id);
	    if(item_rs!=1)
	    	result = -1;//���ʧ��
		}
			
		return result;
	}
	//��ҳ��ʾ��Ϣ,��ʾ5����Ϣ
	public ArrayList<Vote> get(){
		
		   Vote result = null;
	    	ArrayList<Vote> a = new ArrayList<Vote>();
	        String sql = "SELECT * FROM vote_info join user on vote_info.account = user.account  order by start_date limit 5" ;
	        DB db = new DB();
	        ResultSet rs = db.select(sql);
	       
	        try {
				while(rs.next()){
					    result = new Vote();
		                result.setId(rs.getInt("id"));
		                result.setTheme(rs.getString("theme"));
		                result.setStartDate(rs.getString("start_date"));
		                result.setEndDate(rs.getString("end_date"));
		                result.setType(rs.getString("type"));
		                result.setOwner(rs.getString("username"));
		                result.setStatus(rs.getInt("status"));
		            
		                int show = 1;
		                //�Ƚ�ʱ��
		                Date dt=new Date();
		                if(rs.getDate("end_date").compareTo(dt)<=0){
		                	show = 0;
		                }
		                else show = 1;
		                result.setShow(show);   
		                
		                a.add(result);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        db.close();
	        return a;     
		
	}
	 public ArrayList<Vote> search(String theme) {
	    	
	        Vote result = null;
	    	ArrayList<Vote> a = new ArrayList<Vote>();
	        String sql = "SELECT * FROM vote_info join user on vote_info.account = user.account  where theme like ? order by start_date desc" ;
	        DB db = new DB(sql);
	        db.setString(1,"%"+theme+"%");
	        ResultSet rs = db.select();
	       
	        try {
				while(rs.next()){
					    result = new Vote();
		                result.setId(rs.getInt("id"));
		                result.setTheme(rs.getString("theme"));
		                result.setStartDate(rs.getString("start_date"));
		                result.setEndDate(rs.getString("end_date"));
		                result.setType(rs.getString("type"));
		                result.setOwner(rs.getString("username"));

		                a.add(result);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        db.close();
	        return a;
}
	//����vote_id�ҵ��������ͶƱ��Ϣ
	public Vote get(int id){
		
		VoteInfoDao vind = new VoteInfoDao();
		UserDao ud = new UserDao();
		Vote vote = vind.get(id);
		User user = ud.get(vote.getAccount());
		//������
		vote.setOwner(user.getUsername());
		VoteItemDao vitemd = new VoteItemDao();
		ArrayList<Item> item = vitemd.get(id);

		vote.setItem(item);

		return vote;
	}
	//���ݶ�ѡ��ѡ��ID����ͶƱ����һ
	public int plus(String[] item_ids){
		
		int flag = 1 ;
		VoteItemDao vitem = new VoteItemDao();
		for(String str : item_ids){
			if(vitem.plus(Integer.parseInt(str))!=1)
				flag = 0;
		}
		return flag;
	}
	

}
