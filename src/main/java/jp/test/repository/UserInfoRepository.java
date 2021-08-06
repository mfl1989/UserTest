package jp.test.repository;

import java.sql.Date;
import java.util.List;

import javax.persistence.TemporalType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.test.Entity.UserInfo;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer>{

	List<UserInfo> findAllByOrderByUserIdAsc();

	List<UserInfo> findByNameContainingOrderByUserIdAsc(String name);

	
	
	@Query("SELECT * FROM m_user_info  WHERE birthday.beginTimestamp BETWEEN :from AND :to  ")
	List<UserInfo> findByBirthday(
	    @Param("from") @Temporal(TemporalType.TIMESTAMP) Date startDay,
	    @Param("to") @Temporal(TemporalType.TIMESTAMP) Date endDay );
	
}


