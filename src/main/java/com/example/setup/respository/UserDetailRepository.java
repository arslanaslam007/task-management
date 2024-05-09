package com.example.setup.respository;

import com.example.setup.object.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserDetailRepository extends JpaRepository<UserDetail,Long> {
    Optional<UserDetail> getByUsername(String username);
}
