package dev.walid.graphqldemo.data;

import dev.walid.graphqldemo.model.Comment;
import dev.walid.graphqldemo.model.Gender;
import dev.walid.graphqldemo.model.Post;
import dev.walid.graphqldemo.model.User;
import dev.walid.graphqldemo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class FakeDataInitializer implements CommandLineRunner {
    private final UserRepository userRepository;
    private final Faker faker = new Faker();
    private final Random random = new Random();


    @Override
    public void run(String... args) {

        List<User> users = new ArrayList<>();
        // create 50 users
        for (int i = 0; i < 50; i++) {
            User user = User.builder()
                    .fullName(faker.name().fullName())
                    .email(faker.internet().emailAddress())
                    .birthDate(faker.date().birthday().toLocalDateTime())
                    .country(faker.address().country())
                    .gender(random.nextInt(2) == 0 ? Gender.MALE : Gender.FEMALE)
                    .password(faker.internet().password())
                    .profileImage(faker.avatar().image())
                    .createdAt(faker.date().past(50, TimeUnit.DAYS).toLocalDateTime())
                    .build();
            users.add(user);
        }

        // each user has 10 friends
        users.forEach(user -> {
            List<User> friends = new ArrayList<>(users);
            friends.remove(user);
            Collections.shuffle(friends);
            user.setFriends(new HashSet<>(friends.subList(0, 10)));
        });

        // create 10 posts for each user
        users.forEach(user -> {
            Set<Post> posts = new HashSet<>();
            for (int i = 0; i < 10; i++) {
                Post post = Post.builder()
                        .title(faker.lorem().sentence())
                        .content(faker.lorem().characters(100))
                        .image(faker.internet().image())
                        .user(user)
                        .build();
                posts.add(post);
            }
            user.setPosts(posts);
        });

        // create 10 comments for each post
        users.forEach(user -> user.getPosts().forEach(post -> {
            Set<Comment> comments = new HashSet<>();
            for (int i = 0; i < 10; i++) {
                Comment comment = Comment.builder()
                        .content(faker.lorem().characters(50))
                        .post(post)
                        .user(user)
                        .build();
                comments.add(comment);

            }
            post.setComments(comments);
        }));

        users.get(1).setPosts(new HashSet<>());
        userRepository.saveAll(users);
    }
}
