package org.batch.demosntrative.infra.batch.reader;

import lombok.RequiredArgsConstructor;
import org.batch.demosntrative.infra.persistance.BankClientDocument;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.infrastructure.item.data.MongoCursorItemReader;
import org.springframework.batch.infrastructure.item.data.builder.MongoCursorItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class Reader {
    private final MongoTemplate mongoTemplate;

    @Bean
    @StepScope
    public MongoCursorItemReader<BankClientDocument> itemReader() {
        return new MongoCursorItemReaderBuilder<BankClientDocument>()
                .name("reader-mongo")
                .template(mongoTemplate)
                .targetType(BankClientDocument.class)
                .collection("teste-paulo")
                .query(new Query())
                .sorts(Map.of("_id", Sort.Direction.ASC))
                .build();
    }
}
