package com.ideaco.dia;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class FirstController {

    private FirstService firstService;

    public FirstController(FirstService firstService){
        this.firstService = firstService;
    }

    @GetMapping("/helloWorld")
    public String helloWorld(/*request*/){
        //response
        return "Hello World";
    }

    @GetMapping("/sendMessage")
    public String sendMessage(@RequestParam("message") String message){
        return firstService.sendMessage(message);
    }

    @GetMapping("/job/{jobId}")
    public JobModel getJob(@PathVariable("jobId") int jobId){
        return firstService.getJobById(jobId);
    }

    @GetMapping("/jobs")
    public List<JobModel> getAllJobs(){
        return firstService.findAllJobs();
    }

    @PostMapping("/job")
    public JobModel createJob(@RequestParam("jobName") String jobName,
                              @RequestParam("jobDesc") String jobDesc,
                              @RequestParam("jobSalary") int jobSalary){

        return firstService.createJob(jobName, jobDesc, jobSalary);
    }
}
