package com.its.ex.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
// 테이블이름을 변경하고싶을떄
@Table(name = "test_table")
public class TestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    @Column(name = "test_id") //컬럼만들기
    private Long id;
    // pk

    @Column(name = "test_column1" ,length = 50)
    private String column1;

    @Column(unique = true)
    private String testColumn;
}
