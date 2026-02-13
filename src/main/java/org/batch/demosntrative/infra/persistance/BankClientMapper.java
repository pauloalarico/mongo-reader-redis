package org.batch.demosntrative.infra.persistance;

import org.batch.demosntrative.application.dto.RedisDataDTO;

public class BankClientMapper {
    public  static RedisDataDTO toRedistDto(BankClientDocument document) {
        return new RedisDataDTO(document);
    }
}
