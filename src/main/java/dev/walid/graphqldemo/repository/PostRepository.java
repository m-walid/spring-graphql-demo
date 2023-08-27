package dev.walid.graphqldemo.repository;

import dev.walid.graphqldemo.model.Post;
import dev.walid.graphqldemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByUserIn(List<User> users);
}
