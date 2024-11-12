package com.apppn.apppn.Repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apppn.apppn.Models.Permission;



@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {

}
