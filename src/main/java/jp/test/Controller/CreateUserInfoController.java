package jp.test.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CreateUserInfoController {

	
	
	@RequestMapping("/createuserinfo")
	public ModelAndView createUserInfo() {
		
		ModelAndView mv =new ModelAndView("createuserinfo");
		
		return mv;
	}
	
}
