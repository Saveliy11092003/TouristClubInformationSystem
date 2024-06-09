package nsu.trushkov.TouristClub.repositories;


import nsu.trushkov.TouristClub.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
