package project.kirei.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.kirei.mapper.UserMapper;
import project.kirei.dto.Image;
import project.kirei.dto.Review;
import project.kirei.dto.User;

@Service
public class UserService {

	@Autowired
    UserMapper mapper;

	public void insertReview(Review review) throws Exception {
		mapper.insertReview(review);
	}

	public int selectReviewNo() throws Exception {
		return mapper.selectReviewNo();
	}

	public void insertImage(Image image) throws Exception {
		mapper.insertImage(image);
	}

	public void insertUser(User user) throws Exception {
		mapper.insertUser(user);
	}

	public ArrayList<Review> userReviewList(int user_No) throws Exception {
		return mapper.userReviewList(user_No);
	}

	public void deleteimage(int review_No) throws Exception {
		mapper.deleteImage(review_No);
	}

	public void deleteReview(int review_No) throws Exception {
		mapper.deleteReview(review_No);
	}

	public void updateReview(Review review) throws Exception {
		mapper.updateReview(review);
	}

	public void updateImage(Image firstImage) throws Exception {
		mapper.updateImage(firstImage);
	}

	public int selectImageNo(int review_No) throws Exception {
		return mapper.selectImageNo(review_No);
	}

	public ArrayList<Review> keywordSearch(String keyword) throws Exception {
		return mapper.keywordSearch(keyword);
	}





}
