package com.apppn.apppn.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apppn.apppn.Models.Role;

@Repository
public interface RolesRepository extends JpaRepository<Role, Long> {

}
