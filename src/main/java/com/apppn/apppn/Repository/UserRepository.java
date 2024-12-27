package com.apppn.apppn.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.apppn.apppn.Models.User;



@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    List<User> findByNameOrLastname(String name, String lastName);


    @Query(value = "SELECT user.* FROM `user` LEFT JOIN user_roles ON user_roles.user_id = user.id_user LEFT JOIN role ON user_roles.role_id = role.id_role WHERE role.id_role = :idRole", nativeQuery = true)
    List<User> findVendedores(@Param("idRole") Long idRole);

}
