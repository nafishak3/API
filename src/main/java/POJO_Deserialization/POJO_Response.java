package POJO_Deserialization;

public class POJO_Response {
    private String instructor;
    private String url;
    private String services;
    private String expertise;
    private Course_ courses;
    private String linkedin;

    public String getInstructor() {
        return instructor;
    }

    public String getUrl() {
        return url;
    }

    public String getServices() {
        return services;
    }

    public String getExpertise() {
        return expertise;
    }

    public Course_ getCourses() {
        return courses;
    }

    public String getLinkedin() {
        return linkedin;
    }
//    ************************************************************************************
    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    public void setCourses(Course_ courses) {
        this.courses = courses;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }




}
