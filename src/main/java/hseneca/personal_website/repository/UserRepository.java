package hseneca.personal_website.repository;

import hseneca.personal_website.entity.Contact;
import hseneca.personal_website.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserName(String userName);

    @Query("SELECT u FROM User u WHERE " +
            "(:name IS NULL OR :name = '' OR u.userName = :name) AND " +
            "(:age IS NULL OR u.age = :age) AND " +
            "(:school IS NULL OR :school = '' OR u.school = :school)")
    Page<User> findBy(String name, Integer age, String school, Pageable pageable);

}