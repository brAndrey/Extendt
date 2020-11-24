package com.example.extendt.data;

import android.util.Log;

public class Task {

    private String description;
    private boolean isComplete;
    private int priority;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Task(String description, boolean isComplete, int priority){

        this.description = description;
        this.isComplete = isComplete;
        this.priority = priority;
    }

    @Override
    public String toString(){

            StringBuilder sb = new StringBuilder();
            sb.append("\n description:" + this.description);
            sb.append("\n isComplete:" + this.isComplete);
            sb.append("\n priority:" + this.priority);
            sb.append("\n *****************");
            return sb.toString();

        //Log.i(" Task ", description+" "+isComplete+" "+priority+"  "+System.currentTimeMillis());


    }
}
