package kg.ssb.sewing.repository;

import kg.ssb.sewing.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByUsername(String username);

    Optional<User> findUserByEmail(String email);

    Optional<User> findById (Long id);

    boolean existsUserByEmailOrUsername(String email, String username);

}
