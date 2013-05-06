package com.bb.mongo.model;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;

@Entity(value = "sequence", noClassnameStored = true)
public class Sequence {
    @Id
    private String id;
    private Long currentIdValue;

    public Sequence() {
    }

    public Sequence(String id, Long currentIdValue) {
        super();
        this.id = id;
        this.currentIdValue = currentIdValue;
    }

    public Sequence(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public Long getCurrentIdValue() {
        return currentIdValue;
    }

    public void setCurrentIdValue(Long currentIdValue) {
        this.currentIdValue = currentIdValue;
    }
}
