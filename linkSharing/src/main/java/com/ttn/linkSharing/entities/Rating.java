package com.ttn.linkSharing.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Rating {

    @Id
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Topic topic;

    private Integer rating;
}
