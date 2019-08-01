package project.kirei.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import project.kirei.dto.Image;
import project.kirei.dto.Review;
import project.kirei.dto.User;
import project.kirei.service.CommonService;
import project.kirei.service.UserService;

@Controller
@RequestMapping("/kirei")
public class UserController {

	private final static Logger logger = LoggerFactory.getLogger(UserController.class);

	private final static String path = "C:\\sts-bundle\\workspace\\Kirei_ver1.0\\src\\main\\resources\\static\\uploadImage\\";
	private final static String defaultImage = "no_image.png";

	@Autowired
	UserService uservice;

	@Autowired
	CommonService cservice;

	// ���[�U�[�V�K�o�^���
	@GetMapping("/userInsert")
	private String userInsert() throws Exception {

		return "userInsert";

	}

	// ���[�U�[�V�K�o�^
	@PostMapping("/userInsert")
	private String userInsert(User user) throws Exception {

		uservice.insertUser(user);

		return "redirect:/kirei/login";

	}

	// �}�C�y�[�W���
	@GetMapping("/mypage")
	private String mypage() throws Exception {

		return "mypage";

	}

	// �g�b�v���
	@GetMapping("/top")
	private String top(Model model) throws Exception {

		/*
		 * ArrayList<Review> reviewList = cservice.selectAllReview();
		 * model.addAttribute("reviewList", reviewList);
		 * 
		 * ArrayList<Image> imageList = findImage(reviewList);
		 * model.addAttribute("imageList", imageList);
		 */

		return "redirect:/kirei";

	}

	// ���r���[�o�^���
	@GetMapping("/reviewInsert")
	private String reviewInsert() throws Exception {

		return "reviewInsert";

	}

	// ���r���[�o�^
	@PostMapping("/reviewInsert")
	private String reviewInsert(Review review, MultipartFile file, HttpServletRequest request) throws Exception {

		User user = getSession(request);
		
		review.setUser_No(user.getUser_No());

		uservice.insertReview(review);

		Image imageFile = imageAttach(file, review.getReview_No());
		
		uservice.insertImage(imageFile);

		return "redirect:/kirei/mypage";

	}

	// ���r���[�ꗗ(���[�U�[)
	@GetMapping("/userReviewList")
	private String userReviewList(HttpServletRequest request, Model model) throws Exception {

		User user = getSession(request);
		
		ArrayList<Review> reviewList = uservice.userReviewList(user.getUser_No());
		model.addAttribute("reviewList", reviewList);

		ArrayList<Image> imageList = findImage(reviewList);
		model.addAttribute("imageList", imageList);	

		return "userReviewList";

	}

	// ���r���[�폜
	@GetMapping("/deleteReview")
	private String deleteReview(int review_No) throws Exception {

		uservice.deleteimage(review_No);
		uservice.deleteReview(review_No);

		return "redirect:/kirei/userReviewList";

	}

	// ���r���[�X�V���
	@GetMapping("/updateReview")
	private String updateReview(int review_No, Model model) throws Exception {

		Review review = cservice.detailReview(review_No);

		model.addAttribute("review", review);
		model.addAttribute("review_No", review_No);

		return "reviewUpdate";

	}

	// ���r���[�X�V
	@PostMapping("/updateReview")
	private String updateReview(Review review, int review_No, MultipartFile file, HttpServletRequest request)
			throws Exception {

		User user = getSession(request);
		
		review.setUser_No(user.getUser_No());
		review.setReview_No(review_No);

		uservice.updateReview(review);

		Image imageFile = imageAttach(file, review.getReview_No());

		uservice.updateImage(imageFile);

		return "redirect:/kirei/mypage";

	}

	// �L�[���[�h����
	@PostMapping("/keywordSearch")
	private String keywordSearch(String keyword, Model model) throws Exception {

		logger.info("keyword : " + keyword);

		ArrayList<Review> reviewList = uservice.keywordSearch(keyword);
		model.addAttribute("reviewList", reviewList);

		ArrayList<Image> imageList = findImage(reviewList);
		model.addAttribute("imageList", imageList);	

		return "top";

	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// �Z�b�V����
	private User getSession(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("userInfo");
		
		return user;
	}

	
	// �t�@�C���R�s�[(UUID)
	private String uploadFile(String image, byte[] bytes, String path) throws Exception {

		logger.info("Upload Success!");

		UUID uuid = UUID.randomUUID();

		String savedName = uuid.toString() + "_" + image;

		File target = new File(path, savedName);

		FileCopyUtils.copy(bytes, target);

		return savedName;
	}

	// ���r���[�̃C���[�W����
	private ArrayList<Image> findImage(ArrayList<Review> reviewList) throws Exception {

		ArrayList<Image> imageList = new ArrayList<Image>();

		for (int i = 0; i < reviewList.size(); i++) {
			Review review = reviewList.get(i);
			Image image = cservice.selectImage(review.getReview_No());
			imageList.add(image);
		}

		return imageList;
	}
	

	// �C���[�W�Y�t
	private Image imageAttach(MultipartFile file, int review_No) throws IOException, Exception {
		
		String image = "";
		int number = 0;

		if (file.isEmpty()) {
			image = defaultImage;
		} else {
			image = file.getOriginalFilename();
			image = uploadFile(image, file.getBytes(), path);
		}
		
		Image imageFile = new Image();
		
		if (review_No != 0) {
			number = uservice.selectImageNo(review_No);
			imageFile.setImage_No(number);
		}  else {
			number = uservice.selectReviewNo();
		}

		imageFile.setImage(image);
		imageFile.setReview_No(number);
		
		return imageFile;
	}

}
