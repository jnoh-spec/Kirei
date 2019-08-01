package project.kirei.dto;

public class Image {

	private int image_No;
	private String image;
	private int review_No;

	public Image() {}

	public Image(int image_No, String image, int review_No) {
		this.image_No = image_No;
		this.image = image;
		this.review_No = review_No;
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

	public int getReview_No() {
		return review_No;
	}
	public void setReview_No(int review_No) {
		this.review_No = review_No;
	}

}
