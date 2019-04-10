package com.ttn.linkSharing.co;

import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LinkResourceCo {
    @NotBlank(message = "Please enter url!")
    @URL(message = "Please enter a valid url!")
    private String link;

    @NotBlank(message = "Please enter description!")
    @Size(min = 5, message = "Description must contain at least 5 characters")
    private String description;

    @NotNull(message = "Please select a topic!")
    private Long topicId;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}