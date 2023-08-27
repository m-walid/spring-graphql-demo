package dev.walid.graphqldemo.service.impl;

import dev.walid.graphqldemo.model.Post;
import dev.walid.graphqldemo.model.User;
import dev.walid.graphqldemo.repository.PostRepository;
import dev.walid.graphqldemo.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    @Override
    public Map<User, List<Post>> getPostsForUsers(List<User> users) {
        Map<User, List<Post>> userPostsMap = postRepository.findAllByUserIn(users).stream()
                .collect(groupingBy(Post::getUser));
        users.forEach(user -> userPostsMap.putIfAbsent(user, List.of()));
        return userPostsMap;
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }
}
