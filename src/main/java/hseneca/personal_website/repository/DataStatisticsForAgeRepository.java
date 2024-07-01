package hseneca.personal_website.repository;

import hseneca.personal_website.entity.DataStatisticsForAge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataStatisticsForAgeRepository extends JpaRepository<DataStatisticsForAge, Long>{

}
