package com.ideaco.dia.repository;

import com.ideaco.dia.model.JobModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

// JobModel -> model yang kita buat, Integer -> tipe data primary key dari si JobModel
public interface JobRepository extends JpaRepository<JobModel, Integer> {
    Optional<JobModel> findByJobName(String jobName);

    Optional<JobModel> findByJobNameAndJobSalary(String jobName, int salary);

    @Query(value = "select * from tab_job " +
            "where job_name like %:jobName%", nativeQuery = true)
    List<JobModel> searchJob(@Param("jobName") String jobName);

    @Query(value = "select j from JobModel j " +
            "where j.jobSalary >= :salary")
    List<JobModel> filterJob(@Param("salary") int jobSalary);

    @Transactional
    void deleteByJobName(String jobName);
}
