package it.partec.webappspringsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.partec.webappspringsecurity.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
