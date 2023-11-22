package com.global.users.model;

import com.global.users.dto.PhoneDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tbl_users")
@Getter
@Setter
public class UserModel {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id = null;

    @Column(name = "name")
    private String name = null;

    @Column(name = "email")
    private String email = null;

    @Column(name = "password")
    private String password = null;

    @Column(name = "created")
    private String created = null;

    @Column(name = "last_login")
    private String lastLogin = null;

    @Column(name = "is_active")
    private Boolean isActive = null;

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PhoneModel> phoneModelList = new ArrayList<>();

    public void addPhone(PhoneModel phone) {
        phoneModelList.add(phone);
        phone.setUserId(this);
    }


}
