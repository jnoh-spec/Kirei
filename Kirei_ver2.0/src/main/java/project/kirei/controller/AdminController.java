package project.kirei.controller;
 
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.dataformat.csv.CsvGenerator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import project.kirei.DownloadConfig;
import project.kirei.dto.Csv;
import project.kirei.dto.Image;
import project.kirei.dto.Review;
import project.kirei.dto.User;
import project.kirei.service.AdminService;
import project.kirei.service.CommonService;
import project.kirei.service.UserService;

@Controller
@RequestMapping("/kirei")
public class AdminController {

	private final static String path = "C:\\Users\\kitay\\git\\kirei\\Kirei_ver2.0\\src\\main\\resources\\static\\uploadCsv\\";
	private final static String imgPath = "C:\\Users\\kitay\\git\\kirei\\Kirei_ver2.0\\src\\main\\resources\\static\\uploadImage\\";

	private final static String STR_COMMA = ",";

	private final static Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	CommonService cservice;

	@Autowired
	AdminService aservice;

	@Autowired
	UserService uservice;

	@Autowired
    DownloadConfig downloadHelper;

	// 管理者メニュー 画面
	@GetMapping("/adminMenu")
	private String adminMenu() throws Exception {

		return "adminMenu";

	}
	
	// ユーザー一覧
	@GetMapping("/userList")
	private String userList(Model model) throws Exception {

		ArrayList<User> userList = aservice.selectUserList();
		model.addAttribute("userList", userList);

		return "userList";

	}

	//　レビュー一覧
	@GetMapping("/allReviewList")
	private String allReviewList(Model model) throws Exception {

		ArrayList<Review> reviewList = cservice.selectAllReview();
		model.addAttribute("reviewList", reviewList);

		ArrayList<Image> imageList = findImage(reviewList);
		model.addAttribute("imageList", imageList);

		return "allReviewList";

	}
 
	// ユーザー検索
	@PostMapping("/userSearch")
	private String userSearch(String userid, String nickname, Model model) throws Exception {

		logger.info("ID : " + userid + ", Nickname : " + nickname);

		ArrayList<User> userList = aservice.selectUser(userid, nickname);

		model.addAttribute("userList", userList);

		return "userList";

	}

	// キーワード検索
	@PostMapping("/adminKeywordSearch")
	private String adminKeywordSearch(String keyword, Model model) throws Exception {

		logger.info("keyword : " + keyword);

		ArrayList<Review> reviewList = uservice.keywordSearch(keyword);
		model.addAttribute("reviewList", reviewList);

		ArrayList<Image> imageList = findImage(reviewList);
		model.addAttribute("imageList", imageList);	
		
		return "allReviewList";

	}

	// CSVファイルインポート画面
	@GetMapping("/csvInsert")
	private String csvInsert() throws Exception {

		return "csvInsert";

	}

	// CSVファイルインポート
	@PostMapping("/csvInsert")
	private String csvInsert(MultipartFile csvFile, HttpServletRequest request) throws Exception {

		// プロジェクト内にファイルコピー
		String savedName = uploadFile(csvFile.getOriginalFilename(), csvFile.getBytes(), path);

		logger.info("Saved CSV : " + savedName);

		ArrayList<String> csvlist = new ArrayList<String>();
		Csv csv = new Csv();

		// ファイル読み込み
		InputStream inputStream = csvFile.getInputStream();
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
		BufferedReader bufferReader = new BufferedReader(inputStreamReader);

		String line = "";
		int index = 0;

		while ((line = bufferReader.readLine()) != null) {

			line = new String(line.getBytes(), "Shift-JIS");
			String[] columns = line.split(STR_COMMA);

			for (int i = 0; i < columns.length; i++) {
				logger.info("CSV　columns : " + columns[i]);
				if (index != 0) {
					csvlist.add(columns[i]);
				}
			}

			if (index != 0 && csvlist != null) {

				///////////////////////////////////////////////////////
				csv.setReview_No(Integer.parseInt(csvlist.get(0)));
				csv.setProduct_Name(csvlist.get(1));
				csv.setProduct_Price(Integer.parseInt(csvlist.get(2)));
				csv.setReview(csvlist.get(3));
				csv.setInsert_Date(csvlist.get(4));
				csv.setUser_No(Integer.parseInt(csvlist.get(5)));
				csv.setImage_No(Integer.parseInt(csvlist.get(6)));
				csv.setImage(csvlist.get(7));
				///////////////////////////////////////////////////////

				aservice.insertCsvReview(csv);
				int num = aservice.selectCsvReviewNo();
				
				/*aservice.updateCsvReview(num, csv);
				csv.setReview_No(num);*/

				File img = new File(csv.getImage());

				if (img.exists()) {

					logger.info("Image Exist!");
					
					UUID uuid = UUID.randomUUID();
					String saved = uuid.toString() + "_" + img.getName();

					BufferedImage image = null;

					image = ImageIO.read(img);
					
					ImageIO.write(image, "png", new File(imgPath + saved));
					
					System.out.println("saved :: " + saved);
				
					
					csv.setReview_No(num);  
					csv.setImage(saved); 

				}
				 aservice.insertCsvImage(csv);

				/*aservice.updateCsvImage(csv);*/

				csvlist.clear();
			}
			index++;
		}

		bufferReader.close();

		return "redirect:/kirei/adminMenu";

	}

	// CSVダウンロード
	@GetMapping("/csvDownload")
	private ResponseEntity<byte[]> csvDownload(HttpServletResponse response) throws Exception {

		HttpHeaders headers = new HttpHeaders();

        downloadHelper.addContentDisposition(headers, "test.csv");

        return new ResponseEntity<>(getCsvText().getBytes("MS932"), headers, HttpStatus.OK);

	}

	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	 // ファイルコピー(UUID) 
	private String uploadFile(String image, byte[] bytes, String path) throws Exception {
	 
		logger.info("CSV Insert Success!");
		
		 UUID uuid = UUID.randomUUID();
		
		 String savedName = uuid.toString() + "_" + image;
		 
		 File target = new File(path, savedName);
		 
		 FileCopyUtils.copy(bytes, target);
		 
		 return savedName;
	 
	 }
	 

	// DBからCSV内容を登録
	private String getCsvText() throws Exception {

		CsvMapper mapper = new CsvMapper();

		mapper.configure(CsvGenerator.Feature.ALWAYS_QUOTE_STRINGS, true);

		CsvSchema schema = mapper.schemaFor(Csv.class).withHeader();

		ArrayList<Csv> csvList = aservice.selectCsvReview();

		return mapper.writer(schema).writeValueAsString(csvList);

	}
	
	
	// イメージ
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
