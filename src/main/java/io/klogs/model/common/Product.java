package io.klogs.model.common;

import java.math.BigDecimal;

public class Product {
    public String id;
    public String category;
    public String code;
    public String description;
    public double quantity;
    public BigDecimal price = BigDecimal.ZERO;
}
