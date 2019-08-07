package com.stackroute.MuzixAppMysql.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;

@Entity
@Table(name = "Track")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Track {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    
    //declaring variables
    private int id;

    private String name;

    private String artist;

    private String url;

    private String streamable;

    private int listeners;

    //generating setters and getters for the declared variables

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
