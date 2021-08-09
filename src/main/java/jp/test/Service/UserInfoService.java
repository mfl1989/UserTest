package jp.test.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
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
	 * 検索
	 * 
	 * @param name
	 * @param birthday
	 * @param postnumber
	 * @return
	 */
	public List<UserInfoObject> searchUserInfo(String name, Date birthday, String postnumber) {
		
		List<UserInfo> results = userInfoRep.searchUserInfo(name, birthday, postnumber);
		List<UserInfoObject> userlist = new ArrayList<>();

		for (UserInfo result : results) {
			UserInfoObject userInfoObj = new UserInfoObject();
			userInfoObj.setUserId(result.getUserId());
			userInfoObj.setName(result.getName());
			userInfoObj.setSex(result.getSex());
			userInfoObj.setBirthday(result.getBirthday());
			StringBuffer stringBuilder1=new StringBuffer(result.getPostnumber());
			stringBuilder1.insert(3,"-");
			userInfoObj.setPostnumber(stringBuilder1.toString());
			userlist.add(userInfoObj);
		}
		return userlist;
	}

/**
 * csvで出力
 * @param <T>
 * @param titles
 * @param propertys
 * @param list
 * @return
 * @throws IOException
 * @throws IllegalArgumentException
 * @throws IllegalAccessException
 */
	public static <T> String exportCsv(String[] titles, String[] propertys, List<T> list)
			throws IOException, IllegalArgumentException, IllegalAccessException {
		File file = new File("d:\\test.csv");
		// 构建输出流，同时指定编码
		OutputStreamWriter ow = new OutputStreamWriter(new FileOutputStream(file), "utf-8");

		// csv文件是逗号分隔，除第一个外，每次写入一个单元格数据后需要输入逗号
		for (String title : titles) {
			ow.write(title);
			ow.write(",");
		}
		// 写完文件头后换行
		ow.write("\r\n");
		// 写内容
		for (Object obj : list) {
			// 利用反射获取所有字段
			Field[] fields = obj.getClass().getDeclaredFields();
			for (String property : propertys) {
				for (Field field : fields) {
					// 设置字段可见性
					field.setAccessible(true);
					if (property.equals(field.getName())) {
						ow.write(field.get(obj).toString());
						ow.write(",");
						continue;
					}
				}
			}
			// 写完一行换行
			ow.write("\r\n");
		}
		ow.flush();
		ow.close();
		return "0";
	}
/**
 * ｃｓｖ出力内容を設定
 * @throws IOException
 * @throws IllegalArgumentException
 * @throws IllegalAccessException
 */
	public void testCsv() throws IOException, IllegalArgumentException, IllegalAccessException {
		String[] titles = new String[] { "No.", "名前", "性別", "生年月日", "郵便番号" };
		String[] propertys = new String[] { "userId", "name", "sex", "birthday", "postnubmber" };
		List<UserInfo> lists = userInfoRep.findAll();
		List<UserInfoObject> userlist = new ArrayList<UserInfoObject>();
		for (UserInfo list : lists) {
			UserInfoObject userobj = new UserInfoObject();
			;
			userobj.setUserId(list.getUserId());
			userobj.setName(list.getName());
			userobj.setSex(list.getSex());
			userobj.setBirthday(list.getBirthday());
			userobj.setPostnumber(list.getPostnumber());
			userlist.add(userobj);

		}
		UserInfoService.exportCsv(titles, propertys, userlist);

	}

}
