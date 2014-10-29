package geniar.siscar.businessobject;

public class User {
    private String user_id;
    private String name;
    private String email;
    private String carnet;
    
    public User(){}
    
	public User(String user_id, String name, String email, String carnet) {
		super();
		this.user_id = user_id;
		this.name = name;
		this.email = email;
		this.carnet = carnet;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCarnet() {
		return carnet;
	}
	public void setCarnet(String carnet) {
		this.carnet = carnet;
	}
    
    
}
