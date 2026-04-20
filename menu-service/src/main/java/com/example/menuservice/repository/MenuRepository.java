package com.example.menuservice.repository;

import com.example.menuservice.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    List<Menu> findByCategoryIgnoreCase(String category);
    List<Menu> findByAvailableTrue();
}
