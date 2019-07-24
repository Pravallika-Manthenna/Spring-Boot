package com.stackroute.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Access;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name="User")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    int id;
    String firstName;
    String lastName;
    int age;


}
