package com.example.roadmap.day3.repository;

import com.example.roadmap.day3.Entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
