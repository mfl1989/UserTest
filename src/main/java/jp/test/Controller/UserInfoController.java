package jp.test.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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

}
