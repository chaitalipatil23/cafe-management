package POJO;

public class Food {
	
	private int food_Id;
	private String foodName;
	private String foodType;
	private String foodCategory;
	private String Des;
	private double Price;
	private String image;
	
	public Food() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Food(int food_Id, String foodName, String foodType, String foodCategory, String des, double price,
			String image) {
		super();
		this.food_Id = food_Id;
		this.foodName = foodName;
		this.foodType = foodType;
		this.foodCategory = foodCategory;
		Des = des;
		Price = price;
		this.image = image;
	}
	

	public Food(String foodName, String foodType, String foodCategory, String des, double price, String image) {
		super();
		this.foodName = foodName;
		this.foodType = foodType;
		this.foodCategory = foodCategory;
		Des = des;
		Price = price;
		this.image = image;
	}

	public int getFood_Id() {
		return food_Id;
	}
	public void setFood_Id(int food_Id) {
		this.food_Id = food_Id;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public String getFoodType() {
		return foodType;
	}
	public void setFoodType(String foodType) {
		this.foodType = foodType;
	}
	public String getFoodCategory() {
		return foodCategory;
	}
	public void setFoodCategory(String foodCategory) {
		this.foodCategory = foodCategory;
	}
	public String getDes() {
		return Des;
	}
	public void setDes(String des) {
		Des = des;
	}
	public double getPrice() {
		return Price;
	}
	public void setPrice(double price) {
		Price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	@Override
	public String toString() {
		return "Food [food_Id=" + food_Id + ", foodName=" + foodName + ", foodType=" + foodType + ", foodCategory="
				+ foodCategory + ", Des=" + Des + ", Price=" + Price + ", image=" + image + "]";
	}
	
}
