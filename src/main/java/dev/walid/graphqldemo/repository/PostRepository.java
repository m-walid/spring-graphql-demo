package dev.walid.graphqldemo.repository;

import dev.walid.graphqldemo.dto.UserPost;
import dev.walid.graphqldemo.model.Post;
import dev.walid.graphqldemo.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByUserIn(List<User> users);

    @Query(
            value= """
                    select *
                    from (
                        select *,
                         (row_number() over( partition by user_id order by id desc)) as user_post_number,
                         (count(id) over(partition by user_id)) as total_user_posts
                        from posts
                        where user_id in :usersIds
                    )
                    where user_post_number between :page * :size - :size + 1 and :page * :size
                    """
            , nativeQuery = true
    )
    List<UserPost> findAllPostsForUsersPaginated(List<Long> usersIds, int page, int size);

    Page<Post> findAllPostsByUserId(Long id, Pageable pageable);
}
