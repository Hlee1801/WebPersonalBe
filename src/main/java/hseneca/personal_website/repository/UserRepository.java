package hseneca.personal_website.repository;

import hseneca.personal_website.entity.Contact;
import hseneca.personal_website.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT COUNT(u) FROM User u")
    long countUsers();

    @Query("SELECT AVG(u.age) FROM User u")
    double averageAge();

    @Query("SELECT STDDEV(u.age) FROM User u")
    double ageStandardDeviation();

    User findByUserName(String userName);

    @Query("SELECT COUNT(u) FROM User u WHERE u.age >= :startAge AND u.age <= :endAge")
    Long countUsersByAgeRange(@Param("startAge") Integer startAge, @Param("endAge") Integer endAge);


    @Query("SELECT u FROM User u WHERE " +
            "(:name IS NULL OR :name = '' OR u.userName = :name) AND " +
            "(:age IS NULL OR u.age = :age) AND " +
            "(:school IS NULL OR :school = '' OR u.school = :school)")
    Page<User> findBy(String name, Integer age, String school, Pageable pageable);
}