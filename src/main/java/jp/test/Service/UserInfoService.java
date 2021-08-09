package jp.test.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import jp.test.Entity.UserInfo;
import jp.test.Object.UserInfoObject;
import jp.test.repository.UserInfoRepository;

@Service
public class UserInfoService {
	@Autowired
	UserInfoRepository userInfoRep;

	/**
	 * ユーザー情報登録
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
	 * ユーザー情報削除
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
	 * csvで出力
	 * 
	 * @param <T>
	 * @param titles
	 * @param propertys
	 * @param userlist
	 * @return
	 * @throws IOException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static void exportCsv(String titles, String[] propertys, List<String> userlist)
			throws IOException, IllegalArgumentException, IllegalAccessException {
		File file = new File("d:\\test.csv");
		OutputStreamWriter ow = new OutputStreamWriter(new FileOutputStream(file), "utf-8");
		ow.write(titles);
		for (String l : userlist) {
			ow.write(l);
			ow.flush();
		}
		ow.close();
	}

	/**
	 * ｃｓｖ出力内容を設定
	 * 
	 * @throws IOException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public void testCsv(List<UserInfoObject> lists) throws IOException, Exception, Exception {
		String titles = "No.,名前, 性別, 生年月日, 郵便番号\n";
		String[] propertys = new String[] { "userId", "name", "sex", "birthday", "postnubmber" };
//		List<UserInfo> lists = userInfoRep.findAll();
		List<String> userlist = new ArrayList<String>();
		for (UserInfoObject list : lists) {
			String row = new String();
			StringBuffer stringBuilder1 = new StringBuffer(list.getPostnumber());
			stringBuilder1.insert(3, "-");
			list.setPostnumber(stringBuilder1.toString());
			row = list.getUserId() + "," + list.getName() + "," + list.getSex() + "," + list.getBirthday() + ","
					+ list.getPostnumber() + "\n";
			userlist.add(row);
			System.out.println(row);
		}
		UserInfoService.exportCsv(titles, propertys, userlist);

	}

	/**
	 * 検索
	 * 
	 * @param name
	 * @param birthday
	 * @param postnumber
	 * @return
	 */
	public List<UserInfoObject> searchUserInfo(String name, Date birthday, String postnumber) {

		/**
		 * root ：要查询的类型 query：添加查询条件 cb: 构建条件 specification为一个匿名内部类
		 */
		Specification<UserInfo> specification = new Specification<UserInfo>() {

			@Override
			public Predicate toPredicate(Root<UserInfo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				List<Predicate> predicates = new ArrayList<Predicate>();

				if (!name.equals("") && name != null) {
					predicates.add(cb.like(root.get("name"), "%" + name + "%"));
				}
				if (birthday != null) {
					predicates.add(cb.equal(root.get("birthday"), birthday));
				}
				if (!postnumber.equals("") && postnumber != null) {
					predicates.add(cb.equal(root.get("postnumber"), postnumber));
				}
				Predicate[] pre = new Predicate[predicates.size()];

				query.orderBy(cb.desc(root.get("userId")));

				return query.where(predicates.toArray(pre)).getRestriction();

			}
		};

		List<UserInfo> lists = userInfoRep.findAll(specification);
		List<UserInfoObject> userlist = new ArrayList<UserInfoObject>();
		for (UserInfo list : lists) {
			UserInfoObject userobj = new UserInfoObject();
			;
			userobj.setUserId(list.getUserId());
			userobj.setName(list.getName());
			userobj.setSex(list.getSex());
			userobj.setBirthday(list.getBirthday());
			StringBuffer stringBuilder1 = new StringBuffer(list.getPostnumber());
			stringBuilder1.insert(3, "-");
			userobj.setPostnumber(stringBuilder1.toString());
			userlist.add(userobj);

		}
		return userlist;
	}
}
