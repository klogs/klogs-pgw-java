package io.klogs.model.common;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class Address {
    protected String name;
    protected String surname;
    protected String countryCode;
    protected String city;
    protected String district;
    protected String street1;
    protected String street2;
    protected String number;
    protected String postalCode;
    protected String company;
    protected String phone;
    protected String fax;
}
