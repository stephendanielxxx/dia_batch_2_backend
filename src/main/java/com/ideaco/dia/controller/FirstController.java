package com.ideaco.dia.controller;

import com.ideaco.dia.dto.JobDTO;
import com.ideaco.dia.model.JobModel;
import com.ideaco.dia.response.DataResponse;
import com.ideaco.dia.response.HandlerResponse;
import com.ideaco.dia.service.FirstService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class FirstController {

    @Autowired
    private FirstService firstService;

    @GetMapping("/helloWorld")
    public String helloWorld(/*request*/) {
        //response
        return "Hello World";
    }

    @GetMapping("/sendMessage")
    public String sendMessage(@RequestParam("message") String message) {
        return firstService.sendMessage(message);
    }

    @GetMapping("/job/{jobId}")
    public JobDTO getJob(@PathVariable("jobId") int jobId) {
        return firstService.getJobById(jobId);
    }

    @GetMapping("/jobs")
    public void getAllJobs(HttpServletRequest request, HttpServletResponse response) {
        List<JobDTO> allJobs = firstService.findAllJobs();
        DataResponse<List<JobDTO>> dataResponse = new DataResponse<>();
        dataResponse.setData(allJobs);

        HandlerResponse.responseSuccessWithData(response, dataResponse);
    }

    @PostMapping("/job")
    public JobModel createJob(@RequestParam("jobName") String jobName,
                              @RequestParam("jobDesc") String jobDesc,
                              @RequestParam("jobSalary") int jobSalary) {

        return firstService.createJob(jobName, jobDesc, jobSalary);
    }

    @PostMapping("/job/body")
    public void createJobWithBody(HttpServletRequest request, HttpServletResponse response,
                                  @RequestBody JobModel jobModel) {
        JobDTO jobWithBody = firstService.createJobWithBody(jobModel);
        DataResponse<JobDTO> dataResponse = new DataResponse<>();
        dataResponse.setData(jobWithBody);
        HandlerResponse.responseSuccessWithData(response, dataResponse);
    }

    @GetMapping("/job/name/{jobName}")
    public JobModel getJobByName(@PathVariable("jobName") String jobName) {
        JobModel jobByName = firstService.getJobByName(jobName);
        if (jobByName != null) {
            return jobByName;
        } else {
            return new JobModel();
        }
    }

    @GetMapping("/job/name/salary")
    public JobModel getJobByNameAndSalary(@RequestParam("jobName") String jobName,
                                          @RequestParam("jobSalary") int salary) {
        JobModel jobByName = firstService.getJobBySalaryAndName(jobName, salary);
        if (jobByName != null) {
            return jobByName;
        } else {
            return new JobModel();
        }
    }

    @GetMapping("/job/search")
    public void searchJob(HttpServletRequest request, HttpServletResponse response,
                          @RequestParam("jobName") String jobName) {
        List<JobDTO> jobDTOS = firstService.searchJob(jobName);
        DataResponse<List<JobDTO>> dataResponse = new DataResponse<>();
        dataResponse.setData(jobDTOS);

        HandlerResponse.responseSuccessWithData(response, dataResponse);

    }

    @GetMapping("/job/filter")
    public List<JobModel> filterJob(@RequestParam("jobSalary") int jobSalary) {
        return firstService.filterJob(jobSalary);
    }

    @PutMapping("/job/{jobId}")
    public JobModel updateJob(@PathVariable("jobId") int jobId,
                              @RequestBody JobModel jobModel) {
        JobModel updatedJob = firstService.updateJob(jobId, jobModel);
        if (updatedJob != null) {
            return updatedJob;
        } else {
            return new JobModel();
        }
    }

    @PatchMapping("/job/update")
    public JobModel updateJobName(@RequestParam("jobId") int jobId,
                                  @RequestParam("jobName") String jobName) {
        JobModel updatedJob = firstService.updateJobName(jobId, jobName);
        if (updatedJob != null) {
            return updatedJob;
        } else {
            return new JobModel();
        }
    }

    @DeleteMapping("/job/{jobId}")
    public boolean deleteJob(@PathVariable("jobId") int jobId) {
        return firstService.deleteJob(jobId);
    }

    @DeleteMapping("/job/name/{jobName}")
    public boolean deleteJob(@PathVariable("jobName") String jobName) {
        return firstService.deleteJobByName(jobName);
    }

//    @PostMapping("/visitor/registration")
//    public void registerVisitor(HttpServletRequest request, HttpServletResponse response){
//
//    }

    @GetMapping("/job/response")
    public void getJobWithResponse(HttpServletRequest request, HttpServletResponse response,
                                   @RequestParam("jobId") int jobId) {
        JobDTO jobDTO = firstService.getJobById(jobId);

        DataResponse<JobDTO> data = new DataResponse<>();
        data.setData(jobDTO);
        HandlerResponse.responseSuccessWithData(response, data);

//        HandlerResponse.responseBadRequest(response, "001", "Job id not found");
//        HandlerResponse.responseInternalServerError(response, "Something wrong");
//        HandlerResponse.responseSuccessOK(response,"Success get job");
//        HandlerResponse.responseUnauthorized(response, "User not authorized");
    }
}










