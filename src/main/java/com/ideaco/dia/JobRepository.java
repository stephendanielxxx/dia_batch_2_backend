package com.ideaco.dia;

import org.springframework.data.jpa.repository.JpaRepository;

// JobModel -> model yang kita buat, Integer -> tipe data primary key dari si JobModel
public interface JobRepository extends JpaRepository<JobModel, Integer> {
}
