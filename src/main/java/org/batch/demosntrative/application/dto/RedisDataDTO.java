package org.batch.demosntrative.application.dto;

import org.batch.demosntrative.infra.persistance.BankClientDocument;

public record RedisDataDTO(
        String id,
        String name,
        String document,
        String branch,
        String account
) {
    public RedisDataDTO(BankClientDocument document) {
        this(document.getId(), document.getName(), document.getDocumentNumber(),
                document.getBranch(), document.getAccount());
    }
}
