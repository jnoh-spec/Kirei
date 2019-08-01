package project.kirei.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.kirei.mapper.CommonMapper;
import project.kirei.dto.Image;
import project.kirei.dto.Login;
import project.kirei.dto.Review;
import project.kirei.dto.User;

@Service
public class CommonService {

	@Autowired
    CommonMapper mapper;

	public User login(Login login) throws Exception {
		return mapper.login(login);
	}

	public ArrayList<Review> selectAllReview() throws Exception {
		return mapper.selectAllReview();
	}

	public Image selectImage(int review_No) throws Exception {
		return mapper.selectImage(review_No);
	}

	public Review detailReview(int review_No) throws Exception {
		return mapper.detailReview(review_No);
	}

	public User userReview(int user_No) throws Exception {
		return mapper.userReview(user_No);
	}

	public ArrayList<Image> detailImage(int review_No) throws Exception {
		return mapper.detailImage(review_No);
	}

}
