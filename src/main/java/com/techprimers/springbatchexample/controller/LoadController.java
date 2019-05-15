package com.techprimers.springbatchexample.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/load")
public class LoadController {

   private final JobLauncher jobLauncher;
   private final Job job;

   public LoadController(JobLauncher jobLauncher, Job job) {
      this.jobLauncher = jobLauncher;
      this.job = job;
   }

   @GetMapping
   public BatchStatus load() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException,
           JobInstanceAlreadyCompleteException {

      final Map<String, JobParameter> maps = new HashMap<>();
      maps.put("time", new JobParameter(System.currentTimeMillis()));
      final JobParameters parameters = new JobParameters(maps);
      JobExecution jobExecution = jobLauncher.run(job, parameters);
      return jobExecution.getStatus();
   }
}
