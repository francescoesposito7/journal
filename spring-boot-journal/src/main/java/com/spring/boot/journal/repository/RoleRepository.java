package com.spring.boot.journal.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.spring.boot.journal.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

	public Role findByRole(String role);
}
