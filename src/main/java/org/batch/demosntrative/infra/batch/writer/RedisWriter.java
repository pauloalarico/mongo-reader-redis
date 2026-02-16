package org.batch.demosntrative.infra.batch.writer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.batch.demosntrative.application.dto.RedisDataDTO;
import org.springframework.batch.infrastructure.item.Chunk;
import org.springframework.batch.infrastructure.item.ItemWriter;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.Duration;
import java.util.Map;

@RequiredArgsConstructor
@Slf4j
public class RedisWriter implements ItemWriter<RedisDataDTO> {
    private final RedisTemplate<String, Object> redisTemplate;
    private final static String USER = "user:";
    private final static String COVENANTS = "covenats:";
    private final static Duration TTL = Duration.ofHours(3);

    @Override
    public void write(Chunk<? extends RedisDataDTO> chunk) throws Exception {
        for (RedisDataDTO dto : chunk) {
            Map<String, String> fields = Map.of(
                    "name", dto.name(),
                    "document", dto.document()
            );

            Map<String, String> covenants = Map.of(
                    "branch", dto.branch(),
                    "account", dto.account()
            );


            String keyUser = USER + dto.document();
            String keyCovenants = COVENANTS + dto.account();
            log.info("Saving keyUser: {}", keyUser);

            redisTemplate.executePipelined((RedisCallback<?>) connection -> {
                redisTemplate.opsForHash().putAll(keyUser, fields);
                redisTemplate.opsForHash().putAll(keyCovenants, covenants);
                redisTemplate.expire(keyUser, TTL);
                redisTemplate.expire(keyCovenants, TTL);
                return null;
            });

        }
    };
}
