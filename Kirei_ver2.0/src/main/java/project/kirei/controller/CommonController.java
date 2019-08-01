package project.kirei.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import project.kirei.dto.Image;
import project.kirei.dto.Login;
import project.kirei.dto.Review;
import project.kirei.dto.User;
import project.kirei.service.CommonService;

@Controller
@RequestMapping("/kirei")
public class CommonController {

	private final static Logger logger = LoggerFactory.getLogger(CommonController.class);
	
	@Autowired
	CommonService cservice;

	// ���C�����
	@RequestMapping("")
	private String main(Model model) throws Exception {
		
		ArrayList<Review> reviewList = cservice.selectAllReview();
		model.addAttribute("reviewList", reviewList);

		ArrayList<Image> imageList = findImage(reviewList);
		model.addAttribute("imageList", imageList);	

        return "top";

    }

	//�@���O�C�����
	@GetMapping("/login")
	private String login() throws Exception {

		return "login";

	}

	//�@���O�C��
	@PostMapping("/login")
	private String login(@Valid Login login, HttpServletRequest request, Model model) throws Exception {

		HttpSession session = request.getSession();

		User user = cservice.login(login);
		
		// �������Ȃ��ꍇ
		if (user == null) {

			logger.info("Login Fail!");
			session.setAttribute("userInfo", null);
			
			return "redirect:/kirei/login";

		}

		// ����ł���ꍇ
		logger.info("Login Success! : " + user.getUser_Id());
		session.setAttribute("userInfo", user);
		session.setAttribute("nickname", user.getNickname());
		session.setAttribute("userId", user.getUser_Id());
		session.setAttribute("user_Authority", user.getAuthority_No());
		
		// �Ǘ��҂̏ꍇ
		if (user.getAuthority_No() == 1) {
			return "adminMenu";
		}

		//�@���[�U�[�̏ꍇ
		return "redirect:/kirei";

	}

	// ���O�A�E�g
	@GetMapping("/logout")
	private String logout(HttpServletRequest request) throws Exception {

		logger.info("Logout!");

		HttpSession session = request.getSession();
		session.invalidate();

		return "redirect:/kirei/login";

	}


	// ���r���[�ڍ�
	@GetMapping("/reviewDetail")
	private String reviewDetail(@Valid int review_No, HttpServletRequest request, Model model) throws Exception {

		Review review = cservice.detailReview(review_No);
		model.addAttribute("review", review);
		
		User user = cservice.userReview(review.getUser_No());
		model.addAttribute("user", user);
		
		ArrayList<Image> imageList = cservice.detailImage(review_No);
		model.addAttribute("imageList", imageList);

		return "reviewDetail";

	}
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
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

}
