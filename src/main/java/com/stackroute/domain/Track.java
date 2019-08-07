package com.stackroute.domain;


import lombok.*;

import javax.persistence.Access;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name="Track")

//lombok annotations
@NoArgsConstructor
@Builder
@Setter
@Getter
public class Track {

    @Id
    public int id;
    public String firstName;
    public String lastName;
    public int age;

    public Track(int id, String firstName, String lastName, int age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
