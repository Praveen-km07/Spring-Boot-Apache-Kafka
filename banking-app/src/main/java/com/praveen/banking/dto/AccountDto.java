package com.praveen.banking.dto;

//import lombok.AllArgsConstructor;
//import lombok.Data;
//
//@Data
//@AllArgsConstructor
//public class AccountDto {
//    private Long id;
//    private String accountHolderName;
//    private double balance;
//}

//Using Record class which is new feature in java 17 avoid creation of getter
//setter,constructor it will take care all of this
public record AccountDto(Long id,String accountHolderName,double balance){

}
