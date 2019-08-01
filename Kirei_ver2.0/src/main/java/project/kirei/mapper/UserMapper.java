package project.kirei.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import project.kirei.dto.Image;
import project.kirei.dto.Review;
import project.kirei.dto.User;

@Mapper
public interface UserMapper {

	public void insertReview(Review review) throws Exception;

	public int selectReviewNo() throws Exception;

	public void insertImage(Image image) throws Exception;

	public void insertUser(User user) throws Exception;

	public ArrayList<Review> userReviewList(int user_No) throws Exception;

	public void deleteReview(int review_No) throws Exception ;

	public void deleteImage(int review_No) throws Exception;

	public void updateReview(Review review) throws Exception;

	public void updateImage(Image firstImage) throws Exception;

	public int selectImageNo(int review_No) throws Exception;

	public ArrayList<Review> keywordSearch(@Param("keyword")String keyword) throws Exception;


}
