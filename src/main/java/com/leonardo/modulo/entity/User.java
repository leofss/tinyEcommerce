package com.leonardo.modulo.entity;

import com.leonardo.modulo.dto.UserDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@Table(name = "users")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String username;
    private String password;
    private String cep;

    public User(UserDto userDto) {
        this.name = userDto.name();
        this.username = userDto.username();
        this.password = userDto.password();
        this.cep = userDto.cep();
    }

    public User(UserDto userDto, Long id){
        this(userDto);
        this.id = id;
    }

    public UserDto userToDto(){
        return new UserDto(this.id, this.name, this.username, this.password, this.cep);
    }
}
