package jp.test.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jp.test.Entity.UserInfo;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {

	List<UserInfo> findAllByOrderByUserIdAsc();

	List<UserInfo> findByNameContainingOrderByUserIdAsc(String name);

	List<UserInfo> findByBirthdayContainingOrderByUserIdAsc(Date birthday);

	List<UserInfo> findByPostnumberContainingOrderByUserIdAsc(String postnumber);

//	@Query(value = "select * from m_user_info as u where (if (?1 !='',u.name like concat('%',?1,'%'),1=1)"
//			+ "OR if(?2 !=null,u.birthday= ?2,2=2) OR if(?3 !='',u.postnumber= ?3,3=3)", nativeQuery = true)
//	List<UserInfo> searchUserInfo(String name, Date birthday, String postnumber);
	@Query(value = "select * from m_user_info as u where (u.name like concat('%',?1,'%') OR :u.name IS NULL)"
			+ "OR (u.birthday = :birthday OR :u.birthday IS NULL) OR (u.postnumber = :postnumber OR :u.postnumber IS NULL)", nativeQuery = true)
	List<UserInfo> searchUserInfo(String name, Date birthday, String postnumber);
	
}
