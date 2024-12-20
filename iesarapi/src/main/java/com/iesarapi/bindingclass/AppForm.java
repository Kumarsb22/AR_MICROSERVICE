package com.iesarapi.bindingclass;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AppForm {
    private Long caseNum;
    private String fullName;
    private String email;
    private String  gender;
    private LocalDateTime dob;
    private String ssn;
    private Integer userId;


}
