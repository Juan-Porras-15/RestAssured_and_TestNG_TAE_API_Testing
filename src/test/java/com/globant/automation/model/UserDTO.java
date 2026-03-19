package com.globant.automation.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private Integer userStatus;

    public static UserDTO getFakeUser() {
        long id = System.nanoTime();
        return UserDTO.builder()
                .id(id)
                .username("tester_username" + id)
                .firstName("FirstName")
                .lastName("LastName")
                .email("test" + id + "@mail.com")
                .password("pass123")
                .userStatus(1)
                .build();
    }
}

