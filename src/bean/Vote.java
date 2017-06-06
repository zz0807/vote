package bean;
import java.util.ArrayList; 

public class Vote implements java.io.Serializable {

	private int id;
	private String account;
    private String theme;
    private String owner;
    private String startDate;
    private String endDate;
    private String type;//��ѡ��ѡ
    private ArrayList<Item> item;
    private int show;//ͶƱ�����˾Ͳ���Ͷ��0��ʾ���ڣ�1��ʾ����
    private int status;//0��ʾ������1��ʾ�������˽�ֹͶƱ
    

    public Vote() {
    }

    public Vote(int id,String theme, String owner,String startDate,String endDate,String type,ArrayList<Item> item) {
    	
    	this.id = id;
        this.theme = theme;
        this.owner = owner;
        this.startDate = startDate;
        this.endDate = endDate;
        this.type= type;
        this.item = item;
    }

    public int getId(){
    	return this.id;
    }
    public String getAccount(){
    	return this.account;
    }
    public void setAccount(String account){
    	this.account = account;
    }
    public void setId(int id){
    	this.id = id;
    }
    public String getTheme() {
    	
        return this.theme;
    }
    public void setTheme(String theme){
    	this.theme = theme;
    }

    public String getOwner() {
        return owner;
    }
    
    public void setOwner(String owner){
    	this.owner = owner;
    }
    public String getStartDate(){
    	return startDate;
    }
    public void setStartDate(String startDate){
    	this.startDate = startDate;
    }
    public String getEndDate(){
    	return endDate;
    }
    public void setEndDate(String endDate){
    	this.endDate = endDate;
    }
    public String getType(){
    	return type;
    }
    public void setType(String type){
    	this.type = type;
    }
    public ArrayList<Item> getItem(){
    	return item;
    }
    public void setItem(ArrayList<Item> item){
    	this.item = item;
    }
    public int getShow(){
    	return this.show;
    }
    public void setShow(int show){
    	this.show = show;
    }
    public int getStatus(){
    	return this.status;
    }
    public void setStatus(int status){
    	this.status = status;
    }


}
