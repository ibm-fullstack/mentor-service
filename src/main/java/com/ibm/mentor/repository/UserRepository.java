package com.ibm.mentor.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ibm.mentor.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    Optional<User> findByUsernameAndConfirmCode(String username, String confirmCode);
    Optional<User> findByUsernameAndActive(String username, boolean active);
    
//    @Query("SELECT * FROM users a, mentor_skills b, \r\n" + 
//    		"mskills c where a.id = b.mentor_id and b.skill_id = c.id \r\n" + 
//    		"and c.name = (:skillName)")
//    List<User> findBySkill(@Param("skillName") String skillName);
}