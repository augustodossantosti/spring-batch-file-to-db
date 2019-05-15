package com.techprimers.springbatchexample.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techprimers.springbatchexample.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
