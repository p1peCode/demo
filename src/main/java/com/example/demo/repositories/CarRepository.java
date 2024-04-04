package com.example.demo.repositories;

import com.example.demo.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    @Query("SELECT c FROM Car c JOIN c.user u WHERE u.id = ?1")
    Car findByUserId(int userId);
}
