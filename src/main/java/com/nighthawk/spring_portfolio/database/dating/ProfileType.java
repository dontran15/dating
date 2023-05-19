package com.nighthawk.spring_portfolio.database.dating;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ProfileType {
    protected enum Type {
        PERSONALITY, LIFESTYLE, INTEREST, MISC
    }

    private Type type;

    private String detail;

    public String getDetail() {
        return this.detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    // TODO: Format as JSON to send for training
    public String toString() {
        return "";
    }

    public ProfileType(String type) {
        this.detail = null;

        switch (type) {
            case "personality":
                this.type = Type.PERSONALITY;
                break;
            case "interest":
                this.type = Type.INTEREST;
                break;
            case "lifestyle":
                this.type = Type.LIFESTYLE;
                break;
            default:
                this.type = Type.MISC;
                break;
        }
    }
}
