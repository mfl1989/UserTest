package jp.test.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.test.Entity.UserInfo;
import jp.test.Object.UserInfoObject;
import jp.test.repository.UserInfoRepository;

@Service
public class UserInfoService {
	@Autowired
	UserInfoRepository userInfoRep;

	/**
	 * 登録の情報を取得
	 * 
	 * @param userinfoobj
	 */
	@Transactional
	public void getInfoData(UserInfoObject userinfoobj) {

		UserInfo userinfo = new UserInfo();
		userinfo.setName(userinfoobj.getName());
		userinfo.setSex(userinfoobj.getSex());
		userinfo.setBirthday(userinfoobj.getBirthday());
		userinfo.setAddress(userinfoobj.getAddress());
		userinfo.setPostnumber(userinfoobj.getPostnumber());
		userinfo.setName(userinfoobj.getName());
		Timestamp now = new Timestamp(System.currentTimeMillis());
		userinfo.setCreateDate(now);
		userInfoRep.saveAndFlush(userinfo);

	}

	/**
	 * ｄｂの情報を出力
	 * 
	 * @return
	 */
	public List<UserInfoObject> findAll() {
		List<UserInfo> userinfo = userInfoRep.findAllByOrderByUserIdAsc();

		List<UserInfoObject> userinfoobj = new ArrayList<UserInfoObject>();

		for (UserInfo user : userinfo) {
			UserInfoObject userobj = new UserInfoObject();
			userobj.setUserId(user.getUserId());
			userobj.setName(user.getName());
			userobj.setSex(user.getSex());
			userobj.setBirthday(user.getBirthday());
			userobj.setPostnumber(user.getPostnumber());
			userobj.setAddress(user.getAddress());
			userinfoobj.add(userobj);
		}

		return userinfoobj;
	}

	/**
	 * delete
	 * 
	 * @param userId
	 */

	public void delete(int userId) {
		// TODO 自動生成されたメソッド・スタブ
		userInfoRep.deleteById(userId);
	}

	/**
	 * idによって 情報を表示する
	 * 
	 * @param userId
	 * @return
	 */

	public UserInfoObject findInfoById(int userId) {
		UserInfo userinfo = userInfoRep.findById(userId).get();
		UserInfoObject userInfoObj = new UserInfoObject();
		userInfoObj.setUserId(userId);
		userInfoObj.setName(userinfo.getName());
		userInfoObj.setSex(userinfo.getSex());
		userInfoObj.setBirthday(userinfo.getBirthday());
		userInfoObj.setPostnumber(userinfo.getPostnumber());
		userInfoObj.setAddress(userinfo.getAddress());
		return userInfoObj;
	}

	/**
	 * 情報を更新する
	 * 
	 * @param userId
	 * @param userinfoobj
	 */
	@Transactional
	public void uploadInfo(int userId, UserInfoObject userinfoobj) {
		UserInfo userinfo = userInfoRep.findById(userId).get();
		userinfo.setUserId(userId);
		userinfo.setName(userinfoobj.getName());
		userinfo.setSex(userinfoobj.getSex());
		userinfo.setBirthday(userinfoobj.getBirthday());
		userinfo.setPostnumber(userinfoobj.getPostnumber());
		userinfo.setAddress(userinfoobj.getAddress());
		userinfo.setUpdateDate(new Timestamp(System.currentTimeMillis()));

		userInfoRep.saveAndFlush(userinfo);
	}

	/**
	 * 検索
	 * 
	 * @param name
	 * @param birthday
	 * @param postnumber
	 * @return
	 */
	public List<UserInfoObject> search(String name, String birthday, String postnumber) {
		List<UserInfo> results = userInfoRep.findAll();
		List<UserInfoObject> listuser = new ArrayList<>();
		for (UserInfo result : results) {
			UserInfoObject userInfoObj = new UserInfoObject();
			userInfoObj.setName(result.getName());
			userInfoObj.setBirthday(result.getBirthday());
			userInfoObj.setPostnumber(result.getPostnumber());
			listuser.add(userInfoObj);
		}
		return listuser;
	}

	/**
	 * 名前で検索
	 * 
	 * 
	 * @param name
	 * @return
	 */
	public List<UserInfoObject> findByName(String name) {
		// TODO 自動生成されたメソッド・スタブ

		List<UserInfo> results = userInfoRep.findByNameContainingOrderByUserIdAsc(name);
		List<UserInfoObject> listuser = new ArrayList<>();
		for (UserInfo result : results) {
			UserInfoObject userInfoObj = new UserInfoObject();
			userInfoObj.setUserId(result.getUserId());
			userInfoObj.setName(result.getName());
			userInfoObj.setSex(result.getSex());
			userInfoObj.setBirthday(result.getBirthday());
			StringBuffer stringBuilder1=new StringBuffer(result.getPostnumber());
			StringBuffer sb=stringBuilder1.insert(3,"-");
			userInfoObj.setPostnumber(sb.toString());
			listuser.add(userInfoObj);
		}
		return listuser;
	}

	public List<UserInfoObject> findByBirthday(String birthday) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public List<UserInfoObject> findByPostnumber(String postnumber) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

}
