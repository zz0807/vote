package logic;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import bean.User;
import sun.misc.BASE64Encoder;

public class UserService {
	//将用户的注册信息写入数据库
	 public int add(User user) {
	        int result = 0;
	        
	        UserDao dao = new UserDao();
	        User temp = dao.get(user.getAccount());
	        if (temp != null) {
	            result = -1;//该账号已经注册过!
	        } else {
	        	//密码md5加密
	        	try {
					user.setPassword(EncoderByMd5(user.getPassword()));
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            if (dao.add(user) != 1) {
	                result = -2;//增加失败
	            }
	        }
	        return result;
	    }
	 /**利用MD5进行加密*/
	private String EncoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException{
	        //确定计算方法
	        MessageDigest md5=MessageDigest.getInstance("MD5");
	        BASE64Encoder base64en = new BASE64Encoder();
	        //加密后的字符串
	        String newstr=base64en.encode(md5.digest(str.getBytes("utf-8")));
	        return newstr;
	}
	//登录验证
	public int get(User user){
		
		int result = -3;
		
		UserDao dao = new UserDao();
	        User temp = dao.get(user.getAccount());
	        if (temp == null) {
	            result = -1;//该用户未注册
	        }
	        else{
	        	try {
					if(checkpassword(user.getPassword(),temp.getPassword())==true){
						result = 0;//验证成功
					}
					else{
						result = -2;//验证失败
					}
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
		return result;
	}
	 /**判断用户密码是否正确
     *newpasswd  用户输入的密码
     *oldpasswd  正确密码*/
    private boolean checkpassword(String newpasswd,String oldpasswd) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        if(EncoderByMd5(newpasswd).equals(oldpasswd))
            return true;
        else
            return false;
    }
	    
}
