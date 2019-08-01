package project.kirei.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.kirei.mapper.AdminMapper;
import project.kirei.dto.Csv;
import project.kirei.dto.User;

@Service
public class AdminService {

	@Autowired
    AdminMapper mapper;

	public ArrayList<User> selectUserList() throws Exception {
		return  mapper.selectUserList();
	}

	public ArrayList<User> selectUser(String userid, String nickname) throws Exception {
		return mapper.selectUser(userid, nickname);
	}

	public void insertCsvReview(Csv csv) throws Exception {
		mapper.insertCsvReview(csv);
	}

	public void insertCsvImage(Csv csv) throws Exception {
		mapper.insertCsvImage(csv);
	}

	public void updateCsvReview(int num, Csv csv) throws Exception {
		mapper.updateCsvReview(num, csv);
	}

	public int selectCsvReviewNo() throws Exception {
		return mapper.selectCsvReviewNo();
	}

	public void updateCsvImage(Csv csv) throws Exception {
		mapper.updateCsvImage(csv);
	}

	public ArrayList<Csv> selectCsvReview() throws Exception {
		return mapper.selectCsvReview();
	}

}
