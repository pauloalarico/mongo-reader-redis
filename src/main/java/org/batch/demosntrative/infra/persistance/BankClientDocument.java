package org.batch.demosntrative.infra.persistance;

import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "teste-paulo")
@Data
public class BankClientDocument {
    @Id
    private String id;
    private String name;
    private String documentNumber;
    private String branch;
    private String account;
    private BigDecimal value;
    private String referenceDate;
}
