package jp.test.repository;

import java.sql.Date;
import java.util.List;

import javax.persistence.TemporalType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import jp.test.Entity.UserInfo;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {

	List<UserInfo> findAllByOrderByUserIdAsc();

	List<UserInfo> findByNameContainingOrderByUserIdAsc(String name);

//	@Query(value = "select * from m_user_info as u where u.name like LIKE concat(concat('%',:username),'%') OR u.birthday = :birthday OR u.postnumber = :postnumber", nativeQuery = true)
//	List<UserInfo> findUserInfoAllOrderByUserIdAsc(@RequestParam("name") String name, @RequestParam("birthday") Date birthday,
//			@RequestParam("postnumber") String postnumber);

}
