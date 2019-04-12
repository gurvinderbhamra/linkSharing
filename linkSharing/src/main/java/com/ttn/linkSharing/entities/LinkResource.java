package com.ttn.linkSharing.entities;

import com.ttn.linkSharing.co.LinkResourceCo;
import org.hibernate.validator.constraints.URL;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class LinkResource extends Resource {

    @NotNull(message = "Please enter URL")
    @URL(message = "Invalid URL")
    private String link;

    public LinkResource(){ }

    public LinkResource(LinkResourceCo linkResourceCo, Topic topic){
        this.link = linkResourceCo.getLink();
        this.setDescription(linkResourceCo.getDescription());
        this.setTopic(topic);
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}