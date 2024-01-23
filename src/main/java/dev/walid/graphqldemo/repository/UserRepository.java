package dev.walid.graphqldemo.repository;

import dev.walid.graphqldemo.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Page<User> findAllByFollowers(User user, Pageable pageable);

    Page<User> findAllByFollowing(User user, Pageable pageable);
}
