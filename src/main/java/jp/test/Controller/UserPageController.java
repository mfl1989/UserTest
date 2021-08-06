package jp.test.Controller;

import java.sql.Date;
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
	 * create user info
	 * 
	 * @param name
	 * @param sex
	 * @param birthday
	 * @param postnumber
	 * @param address
	 * @return
	 */
	@PostMapping("/createUserInfo")
	public String createUserInfo(@RequestParam("name") String name, @RequestParam("sex") String sex,
			@RequestParam("birthday") Date birthday, @RequestParam("postnumber") String postnumber,
			@RequestParam("address") String address) {

		UserInfoObject userinfoobj = new UserInfoObject();

		userinfoobj.setName(name);
		userinfoobj.setSex(sex);
		userinfoobj.setBirthday(birthday);
		userinfoobj.setPostnumber(postnumber);
		userinfoobj.setAddress(address);

		userInfoService.getInfoData(userinfoobj);

		return "redirect:/userpage";
	}

	/**
	 * 削除
	 */

	@RequestMapping("/deleteinfo/{id}")
	public String deleteinfo(@PathVariable("id") int userId) {

		userInfoService.delete(userId);
		return "redirect:/userpage";
	}

	@PostMapping("/search")
	public ModelAndView secrch(@RequestParam("key1") String name, @RequestParam("key2") String birthday,
			@RequestParam("key3") String postnumber) {
		ModelAndView mav = new ModelAndView("userpage");
		if (name.equals("")) {
			if (birthday.equals("")) {
				if (postnumber.equals("")) {

				}
				else {
					
					List<UserInfoObject> list = userInfoService.findByPostnumber(postnumber);
					
				}
				
			}
			
			else {
				List<UserInfoObject> list = userInfoService.findByBirthday(birthday);
				
			}
			
		} else {
			// name欄に値があるときに，結果を返却する
			List<UserInfoObject> list = userInfoService.findByName(name);
			mav.addObject("info", list);
		}

		return mav;

	}

}
