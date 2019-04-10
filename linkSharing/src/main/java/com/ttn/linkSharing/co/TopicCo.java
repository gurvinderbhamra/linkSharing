package com.ttn.linkSharing.co;

import com.ttn.linkSharing.enums.Visibility;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class TopicCo {

    @NotBlank(message = "Topic name cannot be empty")
    @Size(min=1, message = "Topic name must contain at least 1 character")
    private String topicName;

    private Visibility visibility;

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }

    @Override
    public String toString() {
        return "TopicCo{" +
                "topicName='" + topicName + '\'' +
                ", visibility=" + visibility +
                '}';
    }
}
