package com.clava1096.musicstreaming.models;


import com.clava1096.musicstreaming.models.dto.AlbumDTO;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.Date;

public class Token {
    @Column(name = "token", nullable = false)
    private String token;

    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(
    //        name = ""
    //)
    @Column
    private User user;

    private String DESCRIPTION;

    private Date EXPIRED;

    private Date CREATED;
}
