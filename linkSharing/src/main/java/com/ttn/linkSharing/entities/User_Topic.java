package com.ttn.linkSharing.entities;

import com.ttn.linkSharing.enums.Seriousness;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.EmbeddedId;

@Entity
@Table(name = "user_topic")
public class User_Topic {

    @EmbeddedId
    private User_Topic_Id id;

    private Seriousness seriousness;
}
