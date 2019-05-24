package com.cafe24.demo.VO;

import javax.persistence.Entity;

import lombok.Data;

@Data
public class Music {

    private int rank;
    private String title;
    private String artist;
    private String id;
    private String album;

}