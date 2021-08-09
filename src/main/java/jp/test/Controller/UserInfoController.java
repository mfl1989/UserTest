package jp.test.Controller;

import java.sql.Date;

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
public class UserInfoController {
	@Autowired
	UserInfoService userInfoService;
	
	@RequestMapping("/userinfo/{id}")
	public ModelAndView userInfo(@PathVariable("id") int userId) {
		
		ModelAndView mv =new ModelAndView("userinfo");
		UserInfoObject userInfoObj =userInfoService.findInfoById(userId);
		System.out.println(userInfoObj.userId);
		mv.addObject("userInfo", userInfoObj);
		return mv ;
	}
	/**
	 * 情報を更新する
	 * @param userId
	 * @param name
	 * @param sex
	 * @param birthday
	 * @param postnumber
	 * @param address
	 * @return
	 */
	@PostMapping("/InfoEdit/{id}")
	public String InfoEdit(@PathVariable("id") int userId,
			@RequestParam("name")String name,
			@RequestParam("sex")String sex,
			@RequestParam("birthday")Date birthday,
			@RequestParam("postnumber")String postnumber,
			@RequestParam("address")String address
			) {
		 UserInfoObject userinfoobj =new UserInfoObject();
		userinfoobj.setUserId(userId);
		userinfoobj.setName(name);
		userinfoobj.setSex(sex);
		userinfoobj.setBirthday(birthday);
		String str=postnumber;
		String str1=str.replaceAll("[\\pP\\p{Punct}]","");
		userinfoobj.setPostnumber(str1);
		userinfoobj.setAddress(address);
		
		
		userInfoService.uploadInfo(userId,userinfoobj);
		
		
		
		
		return "redirect:/userpage";
	}

}
