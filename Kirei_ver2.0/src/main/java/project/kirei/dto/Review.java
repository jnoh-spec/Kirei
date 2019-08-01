package project.kirei.dto;

public class Review {

	private int review_No;
	private String product_Name;
	private int product_Price;
	private String review;
	private String insert_Date;
	private int user_No;

	public Review () {}

	public Review(int review_No, String product_Name, int product_Price, String review, String insert_Date,
			int user_No) {
		this.review_No = review_No;
		this.product_Name = product_Name;
		this.product_Price = product_Price;
		this.review = review;
		this.insert_Date = insert_Date;
		this.user_No = user_No;
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

}
