package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@Scope(value = "prototype")
public class Employee {
    private Integer empid;
    private String name;
    @Autowired // searches by type
    @Qualifier("myOwnName") // searches by name - the same name should match in @Component
    private EmpAddress address;

    public void print() {
        System.out.println("empid = " + empid);
        address.setAdd1("102 Address");
        address.setPostCode("IG2 6SA");
        System.out.println("address = " + address.toString());
    }
}
