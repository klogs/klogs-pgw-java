package io.klogs.model.transaction;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class RefundRequest {
    protected String referenceCode;
    
    @Builder.Default
    protected BigDecimal amount = BigDecimal.ZERO;
}
