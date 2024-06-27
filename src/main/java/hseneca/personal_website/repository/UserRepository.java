package hseneca.personal_website.repository;

import hseneca.personal_website.model.request.AgeGroupStats;
import hseneca.personal_website.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT COUNT(u) FROM User u")
    long countUsers();

    @Query("SELECT AVG(u.age) FROM User u")
    double averageAge();

    @Query("SELECT STDDEV(u.age) FROM User u")
    double ageStandardDeviation();

    @Query(value =
            "SELECT " +
                    "  CASE " +
                    "    WHEN u.age >= 0 AND u.age < 10 THEN '0-9' " +
                    "    WHEN u.age >= 10 AND u.age < 20 THEN '10-19' " +
                    "    WHEN u.age >= 20 AND u.age < 30 THEN '20-29' " +
                    "    WHEN u.age >= 30 AND u.age < 40 THEN '30-39' " +
                    "    WHEN u.age >= 40 AND u.age < 50 THEN '40-49' " +
                    "    WHEN u.age >= 50 AND u.age < 60 THEN '50-59' " +
                    "    WHEN u.age >= 60 AND u.age < 70 THEN '60-69' " +
                    "    ELSE '70+' " +
                    "  END AS ageGroup, " +
                    "  COUNT(u) AS total " +
                    "FROM User u " +
                    "GROUP BY ageGroup " +
                    "ORDER BY ageGroup")
    List<AgeGroupStats> countUsersByAgeGroup();


    User findByUserName(String userName);
    User findByEmail(String email);

//    @Query("SELECT COUNT(u) FROM User u WHERE u.age >= :startAge AND u.age <= :endAge")
//    Long countUsersByAgeRange(@Param("startAge") Integer startAge, @Param("endAge") Integer endAge);


    @Query("SELECT u FROM User u WHERE " +
            "(:name IS NULL OR :name = '' OR u.userName = :name) AND " +
            "(:age IS NULL OR u.age = :age) AND " +
            "(:school IS NULL OR :school = '' OR u.school = :school)")
    Page<User> findBy(String name, Integer age, String school, Pageable pageable);
}