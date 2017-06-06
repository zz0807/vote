package logic;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import bean.User;
import sun.misc.BASE64Encoder;

public class UserService {
	//���û���ע����Ϣд�����ݿ�
	 public int add(User user) {
	        int result = 0;
	        
	        UserDao dao = new UserDao();
	        User temp = dao.get(user.getAccount());
	        if (temp != null) {
	            result = -1;//���˺��Ѿ�ע���!
	        } else {
	        	//����md5����
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
	                result = -2;//����ʧ��
	            }
	        }
	        return result;
	    }
	 /**����MD5���м���*/
	private String EncoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException{
	        //ȷ�����㷽��
	        MessageDigest md5=MessageDigest.getInstance("MD5");
	        BASE64Encoder base64en = new BASE64Encoder();
	        //���ܺ���ַ���
	        String newstr=base64en.encode(md5.digest(str.getBytes("utf-8")));
	        return newstr;
	}
	//��¼��֤
	public int get(User user){
		
		int result = -3;
		
		UserDao dao = new UserDao();
	        User temp = dao.get(user.getAccount());
	        if (temp == null) {
	            result = -1;//���û�δע��
	        }
	        else{
	        	try {
					if(checkpassword(user.getPassword(),temp.getPassword())==true){
						result = 0;//��֤�ɹ�
					}
					else{
						result = -2;//��֤ʧ��
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
	 /**�ж��û������Ƿ���ȷ
     *newpasswd  �û����������
     *oldpasswd  ��ȷ����*/
    private boolean checkpassword(String newpasswd,String oldpasswd) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        if(EncoderByMd5(newpasswd).equals(oldpasswd))
            return true;
        else
            return false;
    }
	    
}
