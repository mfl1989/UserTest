package jp.test.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.test.repository.UserInfoRepository;

@Service
public class UserInfoService {
	@Autowired
	UserInfoRepository userInfoRep;

}
