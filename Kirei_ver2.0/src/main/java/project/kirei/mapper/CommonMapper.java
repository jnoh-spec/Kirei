package project.kirei.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import project.kirei.dto.Image;
import project.kirei.dto.Login;
import project.kirei.dto.Review;
import project.kirei.dto.User;

@Mapper
public interface CommonMapper {

	public User login(Login login) throws Exception;

	public ArrayList<Review> selectAllReview() throws Exception;

	public Image selectImage(int review_No) throws Exception;

	public Review detailReview(int review_No) throws Exception;

	public User userReview(int user_No) throws Exception;

	public ArrayList<Image> detailImage(int review_No) throws Exception;

}
