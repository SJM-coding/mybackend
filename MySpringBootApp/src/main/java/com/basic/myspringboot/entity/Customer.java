package com.basic.myspringboot.entity;


import jakarta.persistence.*;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name="customers")
@Getter@Setter

@DynamicUpdate
//업데이트할때 전체값을 업데이트하는게아니라 실제변경된 값만 업데이트될수있게끔하는
public class Customer {
    //Primary Kry, pk 값을 persistance provider가 결정해라
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // 유니크한 값을 가지고 , Null도 허용하지않음
    @Column(unique = true, nullable = false)
    private String customerId;

    //Null 값을 허용하지않음
    @Column(nullable = false)
    private String customerName;

}
