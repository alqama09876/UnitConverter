package com.example.unitconverter.Model;

public class ConversionsModel {
    String id, userInput, km, m, cm, mm, nm, mile, yard, foot, inch;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserInput() {
        return userInput;
    }

    public void setUserInput(String userInput) {
        this.userInput = userInput;
    }

    public String getKm() {
        return km;
    }

    public void setKm(String km) {
        this.km = km;
    }

    public String getM() {
        return m;
    }

    public void setM(String m) {
        this.m = m;
    }

    public String getCm() {
        return cm;
    }

    public void setCm(String cm) {
        this.cm = cm;
    }

    public String getMm() {
        return mm;
    }

    public void setMm(String mm) {
        this.mm = mm;
    }

    public String getNm() {
        return nm;
    }

    public void setNm(String nm) {
        this.nm = nm;
    }

    public String getMile() {
        return mile;
    }

    public void setMile(String mile) {
        this.mile = mile;
    }

    public String getYard() {
        return yard;
    }

    public void setYard(String yard) {
        this.yard = yard;
    }

    public String getFoot() {
        return foot;
    }

    public void setFoot(String foot) {
        this.foot = foot;
    }

    public String getInch() {
        return inch;
    }

    public void setInch(String inch) {
        this.inch = inch;
    }

    public ConversionsModel() {
    }

    public ConversionsModel(String userInput, String km, String m, String cm, String mm, String nm, String mile, String yard, String foot, String inch) {
        this.userInput = userInput;
        this.km = km;
        this.m = m;
        this.cm = cm;
        this.mm = mm;
        this.nm = nm;
        this.mile = mile;
        this.yard = yard;
        this.foot = foot;
        this.inch = inch;
    }

    public ConversionsModel(String id, String userInput, String km, String m, String cm, String mm, String nm, String mile, String yard, String foot, String inch) {
        this.id = id;
        this.userInput = userInput;
        this.km = km;
        this.m = m;
        this.cm = cm;
        this.mm = mm;
        this.nm = nm;
        this.mile = mile;
        this.yard = yard;
        this.foot = foot;
        this.inch = inch;
    }
}