package project.kirei.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import project.kirei.dto.Csv;
import project.kirei.dto.User;

@Mapper
public interface AdminMapper {

	public ArrayList<User> selectUserList() throws Exception;

	// AnnotationÇégÇÌÇ»ÇØÇÍÇŒÅAxmlÇ≈#{arg1}, #{arg0}, #{param1} Ç»Ç«ÇégÇ§
	public ArrayList<User> selectUser(@Param("userid")String userid, @Param("nickname")String nickname) throws Exception;

	public void insertCsvReview(Csv csv) throws Exception;

	public void insertCsvImage(Csv csv) throws Exception;

	public void updateCsvReview(int num, Csv csv) throws Exception;

	public int selectCsvReviewNo() throws Exception;

	public void updateCsvImage(Csv csv) throws Exception;

	public ArrayList<Csv> selectCsvReview() throws Exception;

}
