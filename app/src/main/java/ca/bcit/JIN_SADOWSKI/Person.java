package ca.bcit.JIN_SADOWSKI;

public class Person {
    String Age_Group;
    String Classification_Reported;
    String HA;
    String Reported_Date;
    String Sex;

    public Person() {}

    public Person(String Age_Group, String Classification_Reported, String HA,
                  String Reported_Date, String Sex) {
        this.Age_Group = Age_Group;
        this.Classification_Reported = Classification_Reported;
        this.HA = HA;
        this.Reported_Date = Reported_Date;
        this.Sex = Sex;
    }

    public String getAge_Group() {
        return Age_Group;
    }

    public void setAge_Group(String Age_Group) {
        this.Age_Group = Age_Group;
    }

    public String getClassification_Reported() {
        return Classification_Reported;
    }

    public void setClassification_Reported(String Classification_Reported) {
        this.Classification_Reported = Classification_Reported;
    }

    public String getHA() {
        return HA;
    }

    public void setHA(String HA) {
        this.HA = HA;
    }

    public String getReported_Date() {
        return Reported_Date;
    }

    public void setReported_Date(String Reported_Date) {
        this.Reported_Date = Reported_Date;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String Sex) {
        this.Sex = Sex;
    }
}
