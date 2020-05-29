package com.godev.budgetgo.domain.currency;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "currencies")
@Data
@NoArgsConstructor
public class Currency {

    public static final int NAME_MAX_LENGTH = 255;

    public static final int ISO_CODE_LENGTH = 3;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = NAME_MAX_LENGTH)
    private String name;

    @Column(name = "iso_code", nullable = false, length = ISO_CODE_LENGTH)
    private String isoCode;

    public Currency cloneShallow() {
        Currency e = new Currency();
        e.setId(id);
        e.setName(name);
        e.setIsoCode(isoCode);
        return e;
    }
}
