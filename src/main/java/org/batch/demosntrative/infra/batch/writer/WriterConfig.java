package org.batch.demosntrative.infra.batch.writer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.batch.demosntrative.application.dto.RedisDataDTO;
import org.springframework.batch.infrastructure.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class WriterConfig {
    private final RedisTemplate<String, Object> redisTemplate;

    @Bean
    public ItemWriter<RedisDataDTO> writer() {
        return items -> {
            for(RedisDataDTO dto : items) {
                Map<String, String> fields = new HashMap<>();
                fields.put("name", dto.name());
                fields.put("document", dto.document());
                fields.put("branch", dto.branch());
                fields.put("account", dto.account());

                String key = "user:" + dto.document();
                log.info("Saving key: {}", key);
                redisTemplate.opsForHash().putAll(key, fields);
                redisTemplate.expire(key, Duration.ofHours(3));
            }
        };
    }
}
