package com.example.segproject;
import java.io.Serializable;

public class Task implements Serializable{
    private String Title;
    private int Reward;
    private String Date;
    private int Priority;

    public Task(String Title, int Reward, String Date, int Priority){
        this.Title = Title;
        this.Reward = Reward;
        this.Date = Date;
        this.Priority = Priority;
    }
    public String getTitle(){
        return this.Title;
    }
    public int getReward(){
        return this.Reward;
    }
    public String getDate(){
        return this.Date;
    }
    public int getPriority(){
        return this.Priority;
    }
    public void setTitle(String newTitle){
        this.Title = newTitle;
    }
    public void setReward(int newReward){
        this.Reward = newReward;
    }
    public void setDate(String newDate){
        this.Date = newDate;
    }
    public void setPriority(int newPriority){
        this.Priority = newPriority;
    }
}

