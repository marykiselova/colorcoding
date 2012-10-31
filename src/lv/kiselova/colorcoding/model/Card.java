package lv.kiselova.colorcoding.model;

/**
 * Card model 
 * 
 * @author marykiselova
 *
 */
public class Card {
	
	private int id;
	private String name;
	private int sizeX;
	private int sizeY;
	private String createdOn;
	
	public Card(int id, String name, int sizeX, int sizeY, String createdOn) {
		super();
		this.id = id;
		this.name = name;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.createdOn = createdOn;
	}

	public Card() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSizeX() {
		return sizeX;
	}

	public void setSizeX(int sizeX) {
		this.sizeX = sizeX;
	}

	public int getSizeY() {
		return sizeY;
	}

	public void setSizeY(int sizeY) {
		this.sizeY = sizeY;
	}

	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	@Override
	public String toString() {		
		return "ID: " + id + ", Name:" + name + ", Size X:" + sizeX + ", Size Y:" + sizeY;
	}
	
	
	
	
	
	

}
