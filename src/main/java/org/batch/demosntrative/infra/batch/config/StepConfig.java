package org.batch.demosntrative.infra.batch.config;

import org.batch.demosntrative.application.dto.RedisDataDTO;
import org.batch.demosntrative.infra.persistance.BankClientDocument;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.batch.infrastructure.item.ItemReader;
import org.springframework.batch.infrastructure.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class StepConfig {
    @Bean
    public Step initialStep(PlatformTransactionManager transactionManager,
                            ItemReader<BankClientDocument> reader,
                            ItemProcessor<BankClientDocument, RedisDataDTO> processor,
                            ItemWriter<RedisDataDTO> writer,
                            JobRepository jobRepository) {
        return new StepBuilder("save-data-redis", jobRepository)
                .<BankClientDocument, RedisDataDTO>chunk(10)
                .transactionManager(transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }
}
