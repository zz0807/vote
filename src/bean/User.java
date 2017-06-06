package bean;
public class User implements java.io.Serializable {

	private int id;
    private String account;
    private String username;
    private String password;

    public User() {
    }

    public User(String account, String username,String password) {
    	
        this.account = account;
        this.username = username;
        this.password = password;
    }

    public int getId(){
    	return this.id;
    }
    public void setId(int id){
    	this.id = id;
    }
    public String getAccount() {
    	
        return this.account;
    }
 
    public void setAccount(String account) {
        this.account = account;
    }
    public String getUsername(){
    	return this.username;
    }

    public void setUsername(String username){
    	this.username =  username;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
