package com.global.users.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.UUID;

@Entity
@Table(name = "tbl_phones")
@Getter
@Setter
public class PhoneModel {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id = null;

    @Column(name = "user_id")
    private UUID userId = null;

    @Column(name = "number")
    private long number = 0;

    @Column(name = "city_code")
    private int cityCode = 0;

    @Column(name = "contry_code")
    private String contryCode = null;

}
