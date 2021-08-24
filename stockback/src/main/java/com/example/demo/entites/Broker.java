package com.example.demo.entites;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name="brokers")
@AllArgsConstructor
@NoArgsConstructor
public class Broker implements Serializable {
//    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id")
    private Integer id;
    @Column(name="name")
    private String name;
    @Column(name="experience")
    private Integer experience;
    @Column(name="age")
    private Integer age;
    @Column(name="create_date")
    private Timestamp createDate;
    @Column(name="update_date")
    private Timestamp updateDate;
}
