package jp.test.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jp.test.Entity.UserInfo;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer>, JpaSpecificationExecutor<UserInfo> {

	List<UserInfo> findAllByOrderByUserIdAsc();

	List<UserInfo> findByNameContainingOrderByUserIdAsc(String name);

	List<UserInfo> findByBirthdayContainingOrderByUserIdAsc(Date birthday);

	List<UserInfo> findByPostnumberContainingOrderByUserIdAsc(String postnumber);

	@Query(value = "    select * from m_user_info as u \r\n"
			+ "    where (u.name like concat(concat('%', :name ),'%') and u.name is not null) or (u.birthday = :birthday and u.birthday is not null) \r\n"
			+ "    or (u.postnumber = :postnumber and u.postnumber is not null)", nativeQuery = true)
	List<UserInfo> searchUserInfo(String name, Date birthday, String postnumber);
//	@Query(value = "select * from m_user_info as u where (u.name like concat('%',name,'%') OR :u.name IS NULL)"
//			+ "OR (u.birthday = :birthday OR :u.birthday IS NULL) OR (u.postnumber = :postnumber OR :u.postnumber IS NULL)", nativeQuery = true)
//	List<UserInfo> searchUserInfo(String name, Date birthday, String postnumber);
//	JpaSpecificationExecutor<UserInfo> {
//
//	}
//	List<UserInfo> findAll(Specification<UserInfo> specification);
}
