package com.dev.backend.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;
@Entity
@Table(name = "recipes")
public class Recipe implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private UserAccount userAccount;

    @Column(nullable = false,length = 50)
    private String name;

    @Column(nullable = false,length = 50)
    private String description;

    @Column(nullable = false,length = 30)
    private String url_name;

    @Column(nullable = false)
    private Integer cook;

    @Column(nullable = false)
    private Integer prep;

    @Column(nullable = false)
    private Integer makes;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = true)
    private Date createAt;
}
