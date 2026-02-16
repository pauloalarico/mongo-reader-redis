package org.batch.demosntrative.infra.batch.config;

import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JdbcJobRepositoryFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class JobRepositoryConfig {

    @Bean
    @Primary
    public JobRepository jobRepository (DataSource dataSource, PlatformTransactionManager transactionManager) throws Exception {
        var factory = new JdbcJobRepositoryFactoryBean();
        factory.setDataSource(dataSource);
        factory.setTransactionManager(transactionManager);
        factory.setDatabaseType("POSTGRES");
        return factory.getObject();
    }
}
