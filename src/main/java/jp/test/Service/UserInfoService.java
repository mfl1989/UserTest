package jp.test.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.test.Entity.UserInfo;
import jp.test.Object.UserInfoObject;
import jp.test.repository.UserInfoRepository;

@Service
public class UserInfoService {
	@Autowired
	UserInfoRepository userInfoRep;

	public void getInfoData(UserInfoObject userinfoobj) {
		
		UserInfo userinfo =new UserInfo();
		
		userinfo.setName(userinfoobj.getName());
		userinfo.setSex(userinfoobj.getSex());
		userinfo.setBirthday(userinfoobj.getBirthday());
		userinfo.setAddress(userinfoobj.getAddress());
		userinfo.setPostnumber(userinfoobj.getPostnumber());
		userinfo.setName(userinfoobj.getName());
		userInfoRep.save(userinfo);
		
	}

}
