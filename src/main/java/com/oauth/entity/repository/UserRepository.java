package com.oauth.entity.repository;

import java.util.List;
import java.util.Set;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.oauth.entity.User;




@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUsername(String username);
    @Modifying
	@Query("update User u set u.password =:password where u.username =:username")
	void updatePassword(@Param("password")String password, @Param("username")String username);
    User findByEmail(String email);
}
