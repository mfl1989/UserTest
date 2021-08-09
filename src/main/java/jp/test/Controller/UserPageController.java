package jp.test.Controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.servlet.ModelAndView;

import jp.test.Entity.UserInfo;
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
	public ModelAndView secrch(@RequestParam("userName") String name, @RequestParam("birthday") String birthday,
			@RequestParam("postnumber") String postnumber) {
		ModelAndView mav = new ModelAndView("userpage");

		System.out.println(name);
		Date birthdayDate = null;
		if (!birthday.equals("")) {

			birthdayDate = Date.valueOf(birthday);
		}

		ArrayList<UserInfoObject> userInfoList = (ArrayList<UserInfoObject>) userInfoService.searchUserInfo(name, birthdayDate, postnumber);
		mav.addObject("info", userInfoList);

		return mav;

	}

	@PostMapping("/csvdownload")
	public ModelAndView csvOutPut(@RequestParam("info") List<UserInfoObject> info) throws Exception {
		ModelAndView mav = new ModelAndView("userpage");

		
		
		try {
			userInfoService.testCsv(info);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			System.out.println("没跑到");
			e.printStackTrace();
			
			
		}

		return mav;
	}

}
