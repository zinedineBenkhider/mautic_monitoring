package com.example.mautic_mobile.api.entities;

import androidx.annotation.NonNull;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;

public class FormSubmission {
    private FormCore form;
    private Lead lead;
    private Date dateSubmitted;
    private Map<String,String> results;

    public FormSubmission(FormCore form, Lead lead, @NonNull Date dateSubmitted, Map<String, String> results) {
        this.form = form;
        this.lead = lead;
        this.dateSubmitted = dateSubmitted;
        this.results = results;
    }
    public String getLeadFullName(){
        return lead.getFirstname()+" "+lead.getLastname();
    }

    public int getLeadPoints(){
        return lead.getPoints();
    }
    public String getStringResults(){
        Iterator<String> iter=results.keySet().iterator();
        String res="";
        while (iter.hasNext()){
            String key=iter.next();
            res+=key+": "+results.get(key)+"\n";
        }
        return res;
    }
    public FormCore getForm() {
        return form;
    }

    public void setForm(FormCore form) {
        this.form = form;
    }

    public Lead getLead() {
        return lead;
    }

    public void setLead(Lead lead) {
        this.lead = lead;
    }

    public Date getDateSubmitted() {
        return dateSubmitted;
    }

    public void setDateSubmitted(Date dateSubmitted) {
        this.dateSubmitted = dateSubmitted;
    }

    public Map<String, String> getResults() {
        return results;
    }

    public void setResults(Map<String, String> results) {
        this.results = results;
    }

    public class FormCore{
        private String name;

        public FormCore(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
    public class Lead{
        private int points;
        private String color;
        private String firstname;
        private String lastname;
        private String company;
        private String email;
        private String mobile;

        public Lead(int points, String color, String firstname, String lastname, String company, String email, String mobile) {
            this.points = points;
            this.color = color;
            this.firstname = firstname;
            this.lastname = lastname;
            this.company = company;
            this.email = email;
            this.mobile = mobile;
        }

        public int getPoints() {
            return points;
        }

        public void setPoints(int points) {
            this.points = points;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getFirstname() {
            return firstname;
        }

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        public String getLastname() {
            return lastname;
        }

        public void setLastname(String lastname) {
            this.lastname = lastname;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }
    }
}
