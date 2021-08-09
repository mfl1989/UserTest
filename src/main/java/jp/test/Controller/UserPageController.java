package jp.test.Controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jp.test.Object.UserInfoObject;
import jp.test.Service.UserInfoService;

@Controller
public class UserPageController {
	@Autowired
	UserInfoService userInfoService;

	@RequestMapping("/userpage")
	public ModelAndView userPageView() {

		ModelAndView mv = new ModelAndView("userpage");
//		List<UserInfoObject> userinfoobj = userInfoService.findAll();
//		mv.addObject("info", userinfoobj);
		return mv;

	}

	/**
	 * 削除
	 */

	@RequestMapping("/deleteinfo/{id}")
	public String deleteinfo(@PathVariable("id") int userId) {

		userInfoService.delete(userId);
		return "redirect:/userpage";
	}

//	@PostMapping("/search")
//	public ModelAndView secrch(@RequestParam("userName") String name, @RequestParam("birthday") String birthday,
//			@RequestParam("postnumber") String postnumber) {
//		ModelAndView mav = new ModelAndView("userpage");
//
//		java.sql.Date birthdayDate = null;
//
//		if (!birthday.equals("") && birthday != null) {
//			birthdayDate = java.sql.Date.valueOf(birthday);
//		}
//		System.out.println(name + ":" + birthday + ":" + postnumber);
//		// 検索
//		List<UserInfoObject> userInfoList = userInfoService.searchUserInfo(name, birthdayDate, postnumber);
//		mav.addObject("info", userInfoList);
//
//		return mav;
//
//	}
	
	@PostMapping("/search")
	public ModelAndView secrch(@RequestParam("userName") String name, @RequestParam("birthday") String birthday,
			@RequestParam("postnumber") String postnumber) {
		ModelAndView mav = new ModelAndView("userpage");

		

		if (!birthday.equals("") && birthday != null) {
		
		}
		SimpleDateFormat fmt =new SimpleDateFormat("yyyy-MM-dd");
		String s=birthday;
		Date date=null;
		
		try {
			date=fmt.parse(s);
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		// 検索
		List<UserInfoObject> userInfoList = userInfoService.searchUserInfo(name, date, postnumber);
		mav.addObject("info", userInfoList);

		return mav;

	}

	
	

	@RequestMapping("/csvdownload")
	public void csvOutPut() {

		try {
			userInfoService.testCsv();
		} catch (IllegalArgumentException | IllegalAccessException | IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	}

}
