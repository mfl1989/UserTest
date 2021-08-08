package jp.test.Controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jp.test.Object.UserInfoObject;
import jp.test.Service.UserInfoService;

@Controller
public class CreateUserInfoController {

	@Autowired
	UserInfoService userInfoService;
	
	@RequestMapping("/createuserinfo")
	public ModelAndView createUserInfo() {
		
		ModelAndView mv =new ModelAndView("createuserinfo");
		
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
		String str=postnumber;
		 String str1=str.replaceAll("[\\pP\\p{Punct}]","");
		userinfoobj.setPostnumber(str1);
		userinfoobj.setAddress(address);

		userInfoService.getInfoData(userinfoobj);

		return "redirect:/userpage";
	}

}
