package com.basic.myspringboot.property;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("myboot")
@Getter @Setter //롬복( 자동으로 게터세터설정해주는 )
public class MyBootProperties {
    private String name;
    private int age;
    private String fullName;
}

