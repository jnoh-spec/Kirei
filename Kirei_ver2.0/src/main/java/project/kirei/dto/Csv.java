package project.kirei.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"review_no", "product_name", "product_price", "review", "insert_date", "user_no", "image_no", "image"})

public class Csv {

	@JsonProperty("review_no")
	private int review_No;

	@JsonProperty("product_name")
	private String product_Name;

	@JsonProperty("product_price")
	private int product_Price;

	@JsonProperty("review")
	private String review;

	@JsonProperty("insert_date")
	private String insert_Date;

	@JsonProperty("user_no")
	private int user_No;

	@JsonProperty("image_no")
	private int image_No;

	@JsonProperty("image")
	private String image;

	public Csv() {}

	public Csv(int review_No, String product_Name, int product_Price, String review, String insert_Date, int user_No,
			int image_No, String image) {
		this.review_No = review_No;
		this.product_Name = product_Name;
		this.product_Price = product_Price;
		this.review = review;
		this.insert_Date = insert_Date;
		this.user_No = user_No;
		this.image_No = image_No;
		this.image = image;
	}

	public int getReview_No() {
		return review_No;
	}
	public void setReview_No(int review_No) {
		this.review_No = review_No;
	}

	public String getProduct_Name() {
		return product_Name;
	}
	public void setProduct_Name(String product_Name) {
		this.product_Name = product_Name;
	}

	public int getProduct_Price() {
		return product_Price;
	}
	public void setProduct_Price(int product_Price) {
		this.product_Price = product_Price;
	}

	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}

	public String getInsert_Date() {
		return insert_Date;
	}
	public void setInsert_Date(String insert_Date) {
		this.insert_Date = insert_Date;
	}

	public int getUser_No() {
		return user_No;
	}
	public void setUser_No(int user_No) {
		this.user_No = user_No;
	}

	public int getImage_No() {
		return image_No;
	}
	public void setImage_No(int image_No) {
		this.image_No = image_No;
	}

	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}

}
