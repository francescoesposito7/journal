package com.spring.boot.journal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.boot.journal.entities.Role;

@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Long>{

	public Role findByRoles(String role);
}
