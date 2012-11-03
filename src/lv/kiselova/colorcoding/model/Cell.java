package lv.kiselova.colorcoding.model;
/**
 * Cell model
 * 
 * @author marykiselova
 *
 */
public class Cell {
	private int id;
	private String color;
	private String value;
	private int x;
	private int y;
	private int cardId;
	
	public Cell() {
		super();		
	}
	
	public Cell(String value) {
		super();
		this.value = value;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getCardId() {
		return cardId;
	}

	public void setCardId(int cardId) {
		this.cardId = cardId;
	}

	@Override
	public String toString() {
		return "ID: " + id + ", Color:" + color + ", Value:" + value + ", X:" + x + ", Y:" +y + ", cardId:"+cardId;
	}
	
	
	
	
	
	

}
