package com.example.setup.object;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="user_detail")
@NoArgsConstructor
@Getter
@Setter
public class UserDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String role;

    public UserDetail(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
