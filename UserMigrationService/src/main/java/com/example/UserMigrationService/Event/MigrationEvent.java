package com.example.UserMigrationService.Event;

import java.io.Serializable;

public class MigrationEvent  {
    private String action;

    public MigrationEvent() {}

    public MigrationEvent(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
