package com.sand.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "instructor_detail")
public class InstructorDetail
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "youtube_channel")
  private String youtubeChannel;

  private String hobby;

  @OneToOne(mappedBy = "instructorDetail", cascade = CascadeType.ALL)
  private Instructor instructor;

  public InstructorDetail()
  {
  }

  public InstructorDetail(String youtubeChannel, String hobby)
  {
    this.youtubeChannel = youtubeChannel;
    this.hobby = hobby;
  }

  public String getYoutubeChannel()
  {
    return youtubeChannel;
  }

  public void setYoutubeChannel(String youtubeChannel)
  {
    this.youtubeChannel = youtubeChannel;
  }

  public String getHobby()
  {
    return hobby;
  }

  public void setHobby(String hobby)
  {
    this.hobby = hobby;
  }

  public Instructor getInstructor()
  {
    return instructor;
  }

  public void setInstructor(Instructor instructor)
  {
    this.instructor = instructor;
  }

  @Override
  public String toString()
  {
    return id + ": " + youtubeChannel;
  }
}
