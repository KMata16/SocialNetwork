package com.spring.social.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "followersTable")
@ToString
@EqualsAndHashCode
public class Followers {

    @Column(name = "relationNumber")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer relationNumber;

    @Column(name = "primaryUser")
    private Integer primaryUser;

    @Column(name = "secondaryUser")
    private Integer secondaryUser;

    public Followers() {
    }

    public Followers(Integer primaryUser, Integer secondaryUser) {
        this.primaryUser = primaryUser;
        this.secondaryUser = secondaryUser;
    }

    public Followers(Integer relationNumber, Integer primaryUser, Integer secondaryUser) {
        this.relationNumber = relationNumber;
        this.primaryUser = primaryUser;
        this.secondaryUser = secondaryUser;
    }

    public Integer getRelationNumber() {
        return relationNumber;
    }

    public void setRelationNumber(Integer relationNumber) {
        this.relationNumber = relationNumber;
    }

    public Integer getPrimaryUser() {
        return primaryUser;
    }

    public void setPrimaryUser(Integer primaryUser) {
        this.primaryUser = primaryUser;
    }

    public Integer getSecondaryUser() {
        return secondaryUser;
    }

    public void setSecondaryUser(Integer secondaryUser) {
        this.secondaryUser = secondaryUser;
    }

}
