package hello.core;

import lombok.*;

//자동으로 만들어줌
@Getter
@Setter
@ToString
@NoArgsConstructor
//@AllArgsConstructor
public class HelloLombok {
    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("hi");
        String name = helloLombok.getName();
        System.out.println("helloLombok = " + helloLombok);
        System.out.println("name = " + name); //soutv 시 필드 자동으로 매칭하여 출력 
    }
}
