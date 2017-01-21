package com.example.bridgeit.fundoo.model;

/**
 * Created by bridgeit on 13/1/17.
 */
public class TrackingDetailsModel {
    String techStack;
    String bridgelabzStartDate;
    String bridgelabzEndDate;
    String currentWeek;
    String Weeks;
    String numberOfWeeksLeft;
    String week1;

    public TrackingDetailsModel() {

    }

    public TrackingDetailsModel(String techStack, String bridgelabzStartDate, String bridgelabzEndDate,
                                String currentWeek, String weeks, String numberOfWeeksLeft, String week1) {
        this.techStack = techStack;
        this.bridgelabzStartDate = bridgelabzStartDate;
        this.bridgelabzEndDate = bridgelabzEndDate;
        this.currentWeek = currentWeek;
        Weeks = weeks;
        this.numberOfWeeksLeft = numberOfWeeksLeft;
        this.week1 = week1;
    }

    public String getTechStack() {
        return techStack;
    }

    public void setTechStack(String techStack) {
        this.techStack = techStack;
    }

    public String getBridgelabzStartDate() {
        return bridgelabzStartDate;
    }

    public void setBridgelabzStartDate(String bridgelabzStartDate) {
        this.bridgelabzStartDate = bridgelabzStartDate;
    }

    public String getBridgelabzEndDate() {
        return bridgelabzEndDate;
    }

    public void setBridgelabzEndDate(String bridgelabzEndDate) {
        this.bridgelabzEndDate = bridgelabzEndDate;
    }

    public String getCurrentWeek() {
        return currentWeek;
    }

    public void setCurrentWeek(String currentWeek) {
        this.currentWeek = currentWeek;
    }

    public String getWeeks() {
        return Weeks;
    }

    public void setWeeks(String weeks) {
        Weeks = weeks;
    }

    public String getNumberOfWeeksLeft() {
        return numberOfWeeksLeft;
    }

    public void setNumberOfWeeksLeft(String numberOfWeeksLeft) {
        this.numberOfWeeksLeft = numberOfWeeksLeft;
    }

    public String getWeek1() {
        return week1;
    }

    public void setWeek1(String week1) {
        this.week1 = week1;
    }
}
