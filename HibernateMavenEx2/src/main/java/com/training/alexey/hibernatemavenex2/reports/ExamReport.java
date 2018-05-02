package com.training.alexey.hibernatemavenex2.reports;

public class ExamReport {
    Integer id;
    String lastname;
    String firstname;
    Integer age;
    String country;
    String subj;
    Integer grade;

    public ExamReport(Integer id, String lastname, String firstname, Integer age, String country, String subj, Integer grade) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.age = age;
        this.country = country;
        this.subj = subj;
        this.grade = grade;
    }

    public Integer getId() {
        return id;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }
    
    public Integer getAge() {
        return age;
    }

    public String getCountry() {
        return country;
    }

    public String getSubj() {
        return subj;
    }

    public Integer getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return id + " " + lastname + " " + firstname + " " + age + " " + country + " " + subj + " " + grade;
    }

    
    
    
}
