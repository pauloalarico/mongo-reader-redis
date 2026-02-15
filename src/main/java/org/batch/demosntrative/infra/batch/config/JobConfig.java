package org.batch.demosntrative.infra.batch.config;

import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.parameters.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.Step;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobConfig {

    @Bean
    public Job job(JobRepository jobRepository,
                   Step initalStep) {
        return new JobBuilder("persist-redis", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(initalStep)
                .build();
    }
}
