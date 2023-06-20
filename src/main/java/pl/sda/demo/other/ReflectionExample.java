package pl.sda.demo.other;

import org.springframework.stereotype.Component;

@Component
public class ReflectionExample {
    private String privateField = "no one have access";
    private String secondPrivateField = "no one have access also here";

    public String getPrivateField() {
        return privateField;
    }

    public String getSecondPrivateField() {
        return secondPrivateField;
    }
}
