package com.ideaco.dia;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FirstService {

    private JobRepository jobRepository;

    public FirstService(JobRepository jobRepository){
        this.jobRepository = jobRepository;
    }

    public String sendMessage(String message){
        return "Sending message "+message;
    }

    public JobModel getJobById(int jobId){
        return jobRepository.findById(jobId).get();
    }

    public List<JobModel> findAllJobs(){
        return jobRepository.findAll();
    }

    public JobModel createJob(String jobName,
                              String jobDesc,
                              int jobSalary){

        // valiadasi apakah nama job already exist
        JobModel newJob = new JobModel();
        newJob.setJobName(jobName);
        newJob.setJobDesc(jobDesc);
        newJob.setJobSalary(jobSalary);

        return jobRepository.save(newJob);
    }
}
