package com.dev.backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "users_account")
public class UserAccount implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = false, length = 30)
    private String name;

    @Column(nullable = false, unique = true, length = 50)
    private String email;

    @Column
    private String password;

    @Temporal(TemporalType.TIMESTAMP)
    @Column()
    private Date createAt;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "r_users_permissions",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_permission"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"id_user","id_permission"})
    )
    private List<Permission> permissions;

    public UserAccount() {
    }

    public UserAccount(String name, String email, String password, List<Permission> permissions) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.permissions = permissions;
    }

    @PrePersist
    protected void prePersist() {
        if (this.createAt == null) createAt = new Date();
    }
}
