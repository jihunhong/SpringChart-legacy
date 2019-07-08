package com.cafe24.demo.VO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table
public class Video{

    @Column
    private String videoId;

    @Column
    private String title;

    @Column
    private String artist;

    // @Column
    // private String Thumbnail;

}