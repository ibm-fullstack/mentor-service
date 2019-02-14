package com.ibm.mentor.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibm.mentor.model.Mentor;
import com.ibm.mentor.model.Skills;

@Repository
public interface SkillsRepository extends JpaRepository<Skills, Long> {
    Optional<Skills> findByName(String skillName);
    //List<Mentor> findByMentors(List skill);
}
