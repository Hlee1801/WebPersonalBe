package hseneca.personal_website.repository;

import hseneca.personal_website.entity.TechnicalSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnicalSkillRepository extends JpaRepository<TechnicalSkill, Long> {
}
