package com.stackroute.MuzixAppMysql.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class TrackDTO {

  public static int counter =0;

//  @Id
//  @GeneratedValue(strategy = GenerationType.AUTO)
  //declaring variables
  private int id;

  private String name;

  private String artist;

  private String url;

  private String streamable;

  private int listeners;

  public TrackDTO() {
    id=++counter;
  }

  //generating setters and getters
  public String getTest() {
    return test;
  }

  public void setTest(String test) {
    this.test = test;
  }

  private String test;


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getArtist() {
    return artist;
  }

  public void setArtist(String artist) {
    this.artist = artist;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getStreamable() {
    return streamable;
  }

  public void setStreamable(String streamable) {
    this.streamable = streamable;
  }

  public int getListeners() {
    return listeners;
  }

  public void setListeners(int listeners) {
    this.listeners = listeners;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  //generating construtor for the declared variables
  public TrackDTO(int id, String name, String artist, String url, String streamable, int listeners) {
    this.id = id;
    this.name = name;
    this.artist = artist;
    this.url = url;
    this.streamable = streamable;
    this.listeners = listeners;
  }
}

