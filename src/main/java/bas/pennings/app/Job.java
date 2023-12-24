package bas.pennings.app;

public enum Job {
    Empty("No Job", "", 0F),
    SoftwareDeveloper("Software Developer", "Develops software", 16.28F);

    public final String jobName;
    public final String jobDescription;
    public final float baseSalary;
    Job(String jobName, String jobDescription, float baseSalary) {
        this.jobName = jobName;
        this.jobDescription = jobDescription;
        this.baseSalary = baseSalary;
    }
}
