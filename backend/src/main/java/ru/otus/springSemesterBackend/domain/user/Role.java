package ru.otus.springSemesterBackend.domain.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


public enum Role{
    ADMIN, USER;

//public class Role {
//
//    public Role(String name) {
//        this.name = name;
//    }
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//
//    @Column(nullable = false, unique = true)
//    private String name;

}
