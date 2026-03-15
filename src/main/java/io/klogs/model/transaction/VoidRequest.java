package io.klogs.model.transaction;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class VoidRequest {
    protected String referenceCode;
}
