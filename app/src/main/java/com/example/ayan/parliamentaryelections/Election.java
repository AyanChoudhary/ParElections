package com.example.ayan.parliamentaryelections;

public class Election {

    private String place;
    private String date_time;
    private String party1;
    private String party2;
    private String party3;
    private String vote1;
    private String vote2;
    private String vote3;

    public Election() {

    }

    public Election(String place, String date_time, String party1, String party2, String party3, String vote1, String vote2, String vote3) {
        this.place = place;
        this.date_time = date_time;
        this.party1 = party1;
        this.party2 = party2;
        this.party3 = party3;
        this.vote1 = vote1;
        this.vote2 = vote2;
        this.vote3 = vote3;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }

    public String getParty1() {
        return party1;
    }

    public void setParty1(String party1) {
        this.party1 = party1;
    }

    public String getParty2() {
        return party2;
    }

    public void setParty2(String party2) {
        this.party2 = party2;
    }

    public String getParty3() {
        return party3;
    }

    public void setParty3(String party3) {
        this.party3 = party3;
    }

    public String getVote1() {
        return vote1;
    }

    public void setVote1(String vote1) {
        this.vote1 = vote1;
    }

    public String getVote2() {
        return vote2;
    }

    public void setVote2(String vote2) {
        this.vote2 = vote2;
    }

    public String getVote3() {
        return vote3;
    }

    public void setVote3(String vote3) {
        this.vote3 = vote3;
    }
}
