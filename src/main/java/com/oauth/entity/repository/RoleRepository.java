package com.oauth.entity.repository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.oauth.entity.Role;

@Repository
@Transactional
public interface RoleRepository extends JpaRepository<Role, Integer> {
	
}