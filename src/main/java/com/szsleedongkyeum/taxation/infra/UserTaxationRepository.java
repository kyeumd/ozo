package com.szsleedongkyeum.taxation.infra;

import com.szsleedongkyeum.taxation.model.domain.UsersTaxation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTaxationRepository extends JpaRepository<UsersTaxation, Long> {
}
