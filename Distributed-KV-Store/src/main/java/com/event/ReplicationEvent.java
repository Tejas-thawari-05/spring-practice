package com.event;

import org.springframework.context.ApplicationEvent;

import com.model.KeyValue;

public class ReplicationEvent extends ApplicationEvent {
    private final KeyValue keyValue;

    public ReplicationEvent(Object source, KeyValue keyValue) {
        super(source);
        this.keyValue = keyValue;
    }

    public KeyValue getKeyValue() {
        return keyValue;
    }
}

