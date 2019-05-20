package com.techprimers.springbatchexample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.techprimers.springbatchexample.model.Model;

public interface ModelRepository extends JpaRepository<Model, Integer> {

   @Query(value = "SELECT NEXTVAL('FILE_ID_SEQ')", nativeQuery = true)
   Long getNextFileId();
}
