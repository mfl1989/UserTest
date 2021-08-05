package jp.test.Object;

import java.util.Date;

public class UserInfoObject {

	public int userId;
	public String name;
	public String sex;
	public String birthday;
	public String postnumber;
	public String address;

	public String getPostnumber() {
		return postnumber;
	}

	public void setPostnumber(String postnumber) {
		this.postnumber = postnumber;
	}
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

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
