package model.dto;

import java.sql.Date;

public class EmployeeDTO {

    // employee table과 매핑되도록 DTO 작성
    // DTO의 기본 요건 5가지 지켜서 작성할 것


    private int emp_id;
    private String emp_name;
    private String emp_no;
    private String email;
    private String phone;
    private String dept_code;
    private String job_code;
    private String sal_level;
    private int salary;
    private double bonus;
    private int manager_id;
    private java.sql.Date hire_date;
    private java.sql.Date ent_date;
    private String ent_yn;
    private String job_name;
    private String dept_title;

    public EmployeeDTO() {
    }

    public EmployeeDTO(int emp_id, String emp_name, String emp_no, String email, String phone, String dept_code, String job_code, String sal_level, int salary, double bonus, int manager_id, Date hire_date, Date ent_date, String ent_yn, String job_name, String dept_title) {
        this.emp_id = emp_id;
        this.emp_name = emp_name;
        this.emp_no = emp_no;
        this.email = email;
        this.phone = phone;
        this.dept_code = dept_code;
        this.job_code = job_code;
        this.sal_level = sal_level;
        this.salary = salary;
        this.bonus = bonus;
        this.manager_id = manager_id;
        this.hire_date = hire_date;
        this.ent_date = ent_date;
        this.ent_yn = ent_yn;
        this.job_name = job_name;
        this.dept_title = dept_title;
    }

    public int getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(int emp_id) {
        this.emp_id = emp_id;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public String getEmp_no() {
        return emp_no;
    }

    public void setEmp_no(String emp_no) {
        this.emp_no = emp_no;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDept_code() {
        return dept_code;
    }

    public void setDept_code(String dept_code) {
        this.dept_code = dept_code;
    }

    public String getJob_code() {
        return job_code;
    }

    public void setJob_code(String job_code) {
        this.job_code = job_code;
    }

    public String getSal_level() {
        return sal_level;
    }

    public void setSal_level(String sal_level) {
        this.sal_level = sal_level;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public int getManager_id() {
        return manager_id;
    }

    public void setManager_id(int manager_id) {
        this.manager_id = manager_id;
    }

    public Date getHire_date() {
        return hire_date;
    }

    public void setHire_date(Date hire_date) {
        this.hire_date = hire_date;
    }

    public Date getEnt_date() {
        return ent_date;
    }

    public void setEnt_date(Date ent_date) {
        this.ent_date = ent_date;
    }

    public String getEnt_yn() {
        return ent_yn;
    }

    public void setEnt_yn(String ent_yn) {
        this.ent_yn = ent_yn;
    }

    public String getJob_name() {
        return job_name;
    }

    public void setJob_name(String job_name) {
        this.job_name = job_name;
    }

    public String getDept_title() {
        return dept_title;
    }

    public void setDept_title(String dept_title) {
        this.dept_title = dept_title;
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "emp_id=" + emp_id +
                ", emp_name='" + emp_name + '\'' +
                ", emp_no='" + emp_no + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", dept_code='" + dept_code + '\'' +
                ", job_code='" + job_code + '\'' +
                ", sal_level='" + sal_level + '\'' +
                ", salary=" + salary +
                ", bonus=" + bonus +
                ", manager_id=" + manager_id +
                ", hire_date=" + hire_date +
                ", ent_date=" + ent_date +
                ", ent_yn='" + ent_yn + '\'' +
                ", job_name='" + job_name + '\'' +
                ", dept_title='" + dept_title + '\'' +
                '}';
    }
}

