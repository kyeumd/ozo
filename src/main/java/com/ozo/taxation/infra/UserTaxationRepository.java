package com.ozo.taxation.infra;

import com.ozo.taxation.model.domain.UsersTaxation;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTaxationRepository extends JpaRepository<UsersTaxation, Long> {

    Optional<UsersTaxation> findByUserId(Long userId);
}
