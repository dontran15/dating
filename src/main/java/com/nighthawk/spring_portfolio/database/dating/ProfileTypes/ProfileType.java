package com.nighthawk.spring_portfolio.database.dating.ProfileTypes;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class ProfileType {
    protected enum Type {
        PERSONALITY, LIFESTYLE, INTEREST, MISC
    }

    private String detail;

    public String getDetail() {
        return this.detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public abstract String jsonString();

    public ProfileType() {
        this.detail = null;
    }
}
