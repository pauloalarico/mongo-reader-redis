package org.batch.demosntrative.infra.batch.config;

import lombok.extern.slf4j.Slf4j;
import org.batch.demosntrative.application.dto.RedisDataDTO;
import org.batch.demosntrative.infra.persistance.BankClientDocument;
import org.batch.demosntrative.infra.persistance.BankClientMapper;
import org.jspecify.annotations.Nullable;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ProcessorConfig implements ItemProcessor<BankClientDocument, RedisDataDTO> {

    @Override
    public @Nullable RedisDataDTO process(BankClientDocument item) throws Exception {
        log.info("Starting the processor for the document: {}, {}", item.getDocumentNumber(), item.getName());
        return BankClientMapper.toRedistDto(item);
    }
}
