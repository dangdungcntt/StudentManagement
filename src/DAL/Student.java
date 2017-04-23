package DAL;

import java.util.Date;

/**
 * Created by Nguyễn Đăng Dũng on 3/6/2017 9:19 PM
 * Project: BaiTapLon
 */
public class Student {
    private int studentId;
    private String studentName;
    private int cityId;
    private Date birth;
    private boolean gender;
    private float math;
    private float physical;
    private float chemistry;

    public Student() {
    }

    public Student(int studentId, String studentName, int cityId, Date birth, boolean gender, float math, float physical, float chemistry) {

        this.studentId = studentId;
        this.studentName = studentName;
        this.cityId = cityId;
        this.birth = birth;
        this.gender = gender;
        this.math = math;
        this.physical = physical;
        this.chemistry = chemistry;
    }

    public int getStudentId() {

        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public float getMath() {
        return math;
    }

    public void setMath(float math) {
        this.math = math;
    }

    public float getPhysical() {
        return physical;
    }

    public void setPhysical(float physical) {
        this.physical = physical;
    }

    public float getChemistry() {
        return chemistry;
    }

    public void setChemistry(float chemistry) {
        this.chemistry = chemistry;
    }

    @Override
    public String toString() {
        return "\nStudent{" +
                "studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", cityId=" + cityId +
                ", birth='" + birth + '\'' +
                ", gender=" + gender +
                ", math=" + math +
                ", physical=" + physical +
                ", chemistry=" + chemistry +
                "}\n";
    }
}
