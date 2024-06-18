package hseneca.personal_website.entity;

import ch.qos.logback.core.net.ssl.SecureRandomFactoryBean;
import hseneca.personal_website.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Random;

import static org.hibernate.annotations.UuidGenerator.Style.RANDOM;

@Service
public class Test {
    @Autowired
    UserRepository userRepository;

    Random rand = new Random();

    private static final String alpha = "abcdefghijklmnopqrstuvwxyz"; // a-z
    private static final String alphaUpperCase = alpha.toUpperCase(); // A-Z
    private static final String digits = "0123456789"; // 0-9
    private static final String specials = "~=+%^*/()[]{}/!@#$?|";
    private static final String ALPHA_NUMERIC = alpha + alphaUpperCase + digits;
    private static final String ALL = alpha + alphaUpperCase + digits + specials;


    public String randomAlphaNumeric(int numberOfCharactor) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numberOfCharactor; i++) {
            int number = rand.nextInt(0, ALPHA_NUMERIC.length() - 1);
            char ch = ALPHA_NUMERIC.charAt(number);
            sb.append(ch);
        }
        return sb.toString();
    }

    public void insert() {

        for (int i = 0; i <= 1000000; i++) {
            Contact contact  = Contact.builder()
                    .github("github"+ i)
                    .facebook("facebook"+ i)
                    .instagram("instagram"+ i )
                    .linkedIn("linkedIn"+ i )
                    .phoneNumber(1000000000 + rand.nextInt(1000000000))
                    .build();

            Project project = Project.builder()
                    .projectName("projectname" + rand.nextInt(10000) )
                    .projectDescription("projectdescription" + i )
                    .build();

            TechnicalSkill technicalSkill = TechnicalSkill.builder().build();

            User user = User.builder()
                    .userName("user"+ i)
                    .password()
                    .age(rand.nextInt(15,60))
                    .email("hoang" + i + "@gmail.com")
                    .contact(contact)
                    .projects(project)
                    .technicalSkills(technicalSkill)
                    .build();
        }
    }
}
