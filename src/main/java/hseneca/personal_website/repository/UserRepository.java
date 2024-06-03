package hseneca.personal_website.repository;

import hseneca.personal_website.entity.Contact;
import hseneca.personal_website.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserRepository extends JpaRepository <User, Long> {
  static Page<User> findBy(String userName, Integer age, String school, Contact contact, Pageable pageable) {
      return null;
  };

  User findByUserName(String userName);
}