package com.ideaco.dia.service;

import com.ideaco.dia.dto.JobDTO;
import com.ideaco.dia.model.JobModel;
import com.ideaco.dia.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FirstService {

    @Autowired
    private JobRepository jobRepository;

    public String sendMessage(String message){
        return "Sending message "+message;
    }

    public JobDTO getJobById(int jobId){
        return convertJob(jobRepository.findById(jobId).get());
    }

    private JobDTO convertJob(JobModel jobModel){
        //tanpa constructor
        JobDTO jobDTO = new JobDTO();
        jobDTO.setJobName(jobModel.getJobName());
        jobDTO.setJobSalary(jobDTO.getJobSalary());
        return jobDTO;
        // dengan constructor
//        return new JobDTO(jobModel.getJobName(), jobModel.getJobSalary());
    }

    public List<JobDTO> findAllJobs(){
        List<JobModel> jobModels = jobRepository.findAll();

        return jobModels.stream().map(this::convertJob)
                .collect(Collectors.toList());
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

    public JobModel createJobWithBody(JobModel jobModel){
        return jobRepository.save(jobModel);
    }

    public JobModel getJobByName(String jobName){
        Optional<JobModel> jobOpt = jobRepository.findByJobName(jobName);
        if(jobOpt.isEmpty()){
            return null;
        }
        return jobOpt.get();
    }

    public JobModel getJobBySalaryAndName(String jobName, int salary){
        Optional<JobModel> jobOpt = jobRepository.findByJobNameAndJobSalary(
                jobName, salary);

        if(jobOpt.isEmpty()){
            return null;
        }
        return jobOpt.get();
    }

    public List<JobModel> searchJob(String jobName){
        return jobRepository.searchJob(jobName);
    }

    public List<JobModel> filterJob(int jobSalary){
        return jobRepository.filterJob(jobSalary);
    }

    public JobModel updateJob(int jobId, JobModel jobModel){
        Optional<JobModel> currentJobOpt = jobRepository.
                findById(jobId);

        if(currentJobOpt.isEmpty()){
            return null;
        }

        return jobRepository.save(jobModel);
    }

    public JobModel updateJobName(int jobId, String jobName){
        Optional<JobModel> currentJobOpt = jobRepository.
                findById(jobId);

        if(currentJobOpt.isEmpty()){
            return null;
        }

        JobModel currentJob = currentJobOpt.get();
        currentJob.setJobName(jobName);

        return jobRepository.save(currentJob);
    }

    public boolean deleteJob(int jobId){
        Optional<JobModel> currentJobOpt = jobRepository.
                findById(jobId);

        if(currentJobOpt.isEmpty()){
            return false;
        }

//        jobRepository.deleteById(jobId);
        jobRepository.delete(currentJobOpt.get());

        return true;
    }

    public boolean deleteJobByName(String jobName){
        Optional<JobModel> currentJobOpt = jobRepository.
                findByJobName(jobName);

        if(currentJobOpt.isEmpty()){
            return false;
        }

        jobRepository.deleteByJobName(jobName);
        return true;
    }
}
















