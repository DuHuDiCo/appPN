package com.apppn.apppn.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apppn.apppn.Models.Client;
import com.apppn.apppn.Models.User;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByNameOrLastname(String name, String lastName);

    Client findByEmail(String email);

    List<Client> findByUser(User user);

}
