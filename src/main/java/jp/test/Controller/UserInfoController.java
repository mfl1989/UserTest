package jp.test.Controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jp.test.Object.UserInfoObject;
import jp.test.Service.UserInfoService;

@Controller
public class UserInfoController {
	@Autowired
	UserInfoService userInfoService;

	@RequestMapping("/userpage")
	public ModelAndView userPageView() {

		ModelAndView mv = new ModelAndView("userpage");

		return mv;

	}
/**
 * create user info
 * @param name
 * @param sex
 * @param birthday
 * @param postnumber
 * @param address
 * @return
 */
	@PostMapping("/createUserInfo")
	public String createUserInfo(
			@RequestParam("name") String name,
			@RequestParam("sex") String sex,
			@RequestParam("birthday") String birthday,
			@RequestParam("postnumber") String postnumber,
			@RequestParam("address") String address) {
				
		UserInfoObject userinfoobj =new UserInfoObject();
		
		userinfoobj.setName(name);
		userinfoobj.setSex(sex);
		userinfoobj.setBirthday(birthday);
		userinfoobj.setPostnumber(postnumber);
		userinfoobj.setAddress(address);
		userInfoService.getInfoData(userinfoobj);
		
		
		return "redirect:/userpage";
	}
	
	
	
}
