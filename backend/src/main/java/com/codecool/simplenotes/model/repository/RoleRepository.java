package com.codecool.simplenotes.model.repository;

import com.codecool.simplenotes.model.ERole;
import com.codecool.simplenotes.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByName(ERole name);

}
