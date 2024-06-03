package hseneca.personal_website.sercutity;

import hseneca.personal_website.entity.User;
import hseneca.personal_website.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;

public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
//        System.out.println(username+" " + users.getPassword());
        if(username == null){
            throw new UsernameNotFoundException("User doesn't exit");
        }
        return new org.springframework.security.core.userdetails.User(username, user.getPassword(),new ArrayList<>());

    }

}
