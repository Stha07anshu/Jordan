package com.example.sneakoverse.repo;

import com.example.sneakoverse.entity.AdminPage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminPageRepo extends JpaRepository<AdminPage,Integer> {
}
