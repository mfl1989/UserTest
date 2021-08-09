package jp.test.Object;


import java.sql.Timestamp;
import java.util.Date;

public class UserInfoObject {

	public int userId;
	public String name;
	public String sex;
	public Date birthday;
	public String postnumber;
	public String address;
	public Timestamp createDate;
	public Timestamp updateDate;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getPostnumber() {
		return postnumber;
	}
	public void setPostnumber(String postnumber) {
		this.postnumber = postnumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	public Timestamp getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}
	
	/**
	 *	 キャラクター変換"-"
	 * @param insert
	 */
	public void setPostnumber(StringBuffer insert) {
		// TODO 自動生成されたメソッド・スタブ
		
	}
	

}
