package hseneca.personal_website.entity;

import hseneca.personal_website.enums.TechnicalSkillType;
import hseneca.personal_website.repository.UserRepository;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    public String randomAlphaNumeric(int numberOfCharacters) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numberOfCharacters; i++) {
            int number = rand.nextInt(ALPHA_NUMERIC.length());
            char ch = ALPHA_NUMERIC.charAt(number);
            sb.append(ch);
        }
        return sb.toString();
    }
    @Enumerated(EnumType.STRING)
    private TechnicalSkillType techSkillType;

    public class EnumUtils {
        private static final Random rand = new Random();

        public static <T extends Enum<?>> T randomEnum(Class<T> clazz) {
            int x = rand.nextInt(clazz.getEnumConstants().length);
            return clazz.getEnumConstants()[x];
        }
    }

    public void insert() {
        int batchSize = 100000; // batch size for saving to avoid memory issues
        List<User> users = new ArrayList<>(batchSize);

        for (int i = 260000; i < 1000000000; i++) {
            Contact contact = Contact.builder()
                    .github("github" + i)
                    .facebook("facebook" + i)
                    .instagram("instagram" + i)
                    .linkedIn("linkedIn" + i)
                    .phoneNumber(1000000000 + rand.nextInt(1000000000))
                    .build();

            List<Project> projects = IntStream.range(0, rand.nextInt(5) + 1)
                    .mapToObj(j -> Project.builder()
                            .projectName("projectname" + rand.nextInt(100000))
                            .projectDescription("projectdescription" + j)
                            .build())
                    .collect(Collectors.toList());

            List<TechnicalSkill> tech = IntStream.range(0, rand.nextInt(5) +1)
                    .mapToObj(j -> TechnicalSkill.builder()
                            .techSkillName("tech" + j)
                            .techSkillType(EnumUtils.randomEnum(TechnicalSkillType.class))
                            .build())
                    .collect(Collectors.toList());

            User user = User.builder()
                    .userName("user" + i)
                    .password(randomAlphaNumeric(10)) // generate random password
                    .age(rand.nextInt(45) + 15) // age between 15 and 60
                    .email("hoang" + i + "@gmail.com")
                    .contact(contact)
                    .projects(projects)
                    .technicalSkills(tech)
                    .build();

            users.add(user);

            // Save batch when reaching the batch size
            if (users.size() >= batchSize) {
            userRepository.saveAll(users);
                users.clear();
            }
        }
//        // Save remaining users
//        if (!users.isEmpty()) {
//            userRepository.saveAll(users);
//        }
    }
}