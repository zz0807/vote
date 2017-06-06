package bean;

public class Item  implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String item;
	private int number;
	
	public Item(){
		
	}
	public Item(int id,String item){
		this.id = id;
		this.item = item;
	}
	public int getId(){
		return this.id;
	}

	public void setId(int id){
		this.id = id;
	}
	public String getItem(){
		return this.item;
	}
	public void setItem(String item){
		this.item = item;
	}
	public int getNumber(){
		return this.number;
	}
	public void setNumber(int number){
		this.number = number;
	}
	   
}
