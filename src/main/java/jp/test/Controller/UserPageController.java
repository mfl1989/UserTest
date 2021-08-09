package jp.test.Controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import jp.test.Object.UserInfoObject;
import jp.test.Service.UserInfoService;
import jp.test.repository.UserInfoRepository;

@Controller
public class UserPageController {
	@Autowired
	UserInfoService userInfoService;
	@Autowired
	UserInfoRepository userInfoRep;

	@RequestMapping("/userpage")
	public ModelAndView userPageView() {

		ModelAndView mv = new ModelAndView("userpage");

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

	/**
	 * 検索
	 * 
	 * @param name
	 * @param birthday
	 * @param postnumber
	 * @return
	 */
	@PostMapping("/search")
	public ModelAndView secrch(@RequestParam("name") String name, @RequestParam("birthday") String birthday,
			@RequestParam("postnumber") String postnumber) {
		ModelAndView mav = new ModelAndView("userpage");

		System.out.println(name);
		Date birthdayDate = null;
		if (!birthday.equals("")) {

			birthdayDate = Date.valueOf(birthday);
		}

		List<UserInfoObject> userInfoList = userInfoService.searchUserInfo(name, birthdayDate, postnumber);
		mav.addObject("info", userInfoList);
		mav.addObject("birthday", birthday);
		mav.addObject("postnumber", postnumber);
		mav.addObject("name", name);
		return mav;

	}

	/**
	 * csvダウンロード
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/csvdownload")
	@ResponseBody
	public String csvOutPut(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String name = request.getParameter("name");
		String postnumber = request.getParameter("postnumber");
		String birthday = request.getParameter("birthday");

		Date birthdayDate = null;
		if (!birthday.equals("")) {

			birthdayDate = Date.valueOf(birthday);
		}

		List<UserInfoObject> userInfoList = userInfoService.searchUserInfo(name, birthdayDate, postnumber);

		try {

			userInfoService.testCsv(userInfoList);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック

			e.printStackTrace();
			return "Error!!!!!!!";

		}

		return "Success!!!!!!!";
	}

}
