package dev.walid.graphqldemo.service.impl;

import dev.walid.graphqldemo.dto.PaginatedResponse;
import dev.walid.graphqldemo.dto.PaginationRequest;
import dev.walid.graphqldemo.dto.PostInput;
import dev.walid.graphqldemo.dto.UserPost;
import dev.walid.graphqldemo.model.Post;
import dev.walid.graphqldemo.model.User;
import dev.walid.graphqldemo.repository.PostRepository;
import dev.walid.graphqldemo.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    @Override
    public Map<User, PaginatedResponse<List<Post>>> getPostsForUsers(List<User> users, PaginationRequest paginationRequest) {

        Map<User, PaginatedResponse<List<Post>>> userPostsMap = new HashMap<>();

        List<UserPost> allPostsForUsersPaginated = postRepository.findAllPostsForUsersPaginated(
                users.stream().map(User::getId).toList(),
                paginationRequest.getPage(),
                paginationRequest.getSize()
        );

        allPostsForUsersPaginated.forEach(userPost -> {
            Post post = UserPost.toPost(userPost);
            PaginatedResponse<List<Post>> userPosts = userPostsMap.getOrDefault(post.getUser(), new PaginatedResponse<>());
            userPosts.setTotalPages(
                    (int) Math.ceil((double) userPost.getTotalUserPosts() / paginationRequest.getSize())
            );
            userPosts.setTotalElements(userPost.getTotalUserPosts());
            if (userPosts.getData() == null) {
                userPosts.setData(List.of());
            }
            userPosts.getData().add(post);
            userPostsMap.put(post.getUser(), userPosts);

        });

        users.forEach(user -> userPostsMap.putIfAbsent(user, new PaginatedResponse<>(List.of(), 0, 0)));

        return userPostsMap;
    }

    @Override
    public PaginatedResponse<List<Post>> getAllPosts(PaginationRequest paginationRequest) {
        Page<Post> postsPage = postRepository.findAll(PageRequest.of(paginationRequest.getPage(), paginationRequest.getSize(), Sort.Direction.DESC, "id"));
        return new PaginatedResponse<>(
                postsPage.getContent(),
                postsPage.getTotalElements(),
                postsPage.getTotalPages()
        );
    }

    @Override
    public PaginatedResponse<List<Post>> getAllPostsByUser(User user, PaginationRequest paginationRequest) {
        Page<Post> userPostsPage = postRepository.findAllPostsByUserId(user.getId(), PageRequest.of(paginationRequest.getPage(), paginationRequest.getSize(), Sort.Direction.DESC, "id"));
        return new PaginatedResponse<>(
                userPostsPage.getContent(),
                userPostsPage.getTotalElements(),
                userPostsPage.getTotalPages()

        );
    }

    @Override
    public Post getPostById(Long id) {
        return postRepository.findById(id).orElseThrow();
    }

    @Override
    public Post createPost(PostInput postInput) {
        return postRepository.save(Post.builder()
                .content(postInput.getContent())
                .title(postInput.getTitle())
                .image(postInput.getImage())
                .user(User.builder().id(postInput.getUserId()).build())
                .build());
    }
}
