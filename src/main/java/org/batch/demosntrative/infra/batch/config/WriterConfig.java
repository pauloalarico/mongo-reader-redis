package org.batch.demosntrative.infra.batch.config;

import lombok.RequiredArgsConstructor;
import org.batch.demosntrative.application.dto.RedisDataDTO;
import org.batch.demosntrative.infra.batch.writer.RedisWriter;
import org.springframework.batch.infrastructure.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
@RequiredArgsConstructor
public class WriterConfig {
    private final RedisTemplate<String, Object> redisTemplate;

    @Bean
    public ItemWriter<RedisDataDTO> writer() {
        return new RedisWriter(redisTemplate);
    }
}
