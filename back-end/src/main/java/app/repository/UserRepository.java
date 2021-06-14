package app.repository;

import app.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String username);

    @Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
    @Query(
            value = "select u from User u where u.email = ?1"
    )
    User getAndIncrementVersion(String email);
}
