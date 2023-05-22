package com.nighthawk.spring_portfolio.database.dating;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ProfileDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private enum Type {
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
    // @Override
    // public String toString() {
    // return "{ \"id\": " + id + ", \"type\": " + type + ", \"detail\": " + detail
    // + " }";
    // }

    public ProfileDetail(String type) {
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
