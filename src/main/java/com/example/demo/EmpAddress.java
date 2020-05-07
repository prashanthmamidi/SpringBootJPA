package com.example.demo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component("myOwnName")
@Getter
@Setter
@ToString
public class EmpAddress {
    private String add1;
    private String postCode;
}
