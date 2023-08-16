package com.api.repositoriesInterfaces;
import com.api.dbModels.Tbl_User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
@Repository
public interface IUserRepository extends JpaRepository<Tbl_User,Long> {
	
	// @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM tbl_user u WHERE u.email = :email and password =:password")
	// boolean login(String email,String password);
	 Optional<Tbl_User> findByEmailAndPassword(String email,String password);
}
