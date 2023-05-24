package model.dto;

import java.sql.Date;

public class EmployeeDTO {

    // employee table과 매핑되도록 DTO 작성
    // DTO의 기본 요건 5가지 지켜서 작성할 것
    private int empId;
    private String empName;
    private int empNo;
    private String email;
    private int phone;
    private String deptCode;
    private int jobCode;
    private String salLevel;
    private int salary;
    private Float bonus;
    private int managerId;
    private String entYn;
    private java.sql.Date hireDate;
    private java.sql.Date entDate;

    public EmployeeDTO() {}

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public EmployeeDTO(int empId, String empName, int empNo, String email, int phone, String deptCode, int jobCode, String salLevel, int salary, Float bonus, int managerId,Date hireDate, Date entDate ,String entYn){

        this.empId = empId;
        this.empName = empName;
        this.empNo = empNo;
        this.email = email;
        this.phone = phone;
        this.deptCode = deptCode;
        this.jobCode = jobCode;
        this.salLevel = salLevel;
        this.salary = salary;
        this.bonus = bonus;
        this.managerId = managerId;
        this.entYn = entYn;
        this.hireDate = hireDate;
        this.entDate = entDate;
    }

    public int getEmpId() {

        return empId;
    }

    public void setEmpId(int empId) {

        this.empId = empId;
    }

    public void setEmpNo(int empNo) {

        this.empNo = empNo;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    public void setPhone(int phone) {

        this.phone = phone;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public void setJobCode(int jobCode) {

        this.jobCode = jobCode;
    }

    public void setSalLevel(String salLevel) {

        this.salLevel = salLevel;
    }

    public void setSalary(int salary) {

        this.salary = salary;
    }

    public void setBonus(Float bonus) {

        this.bonus = bonus;
    }

    public void setManagerId(int managerId) {

        this.managerId = managerId;
    }

    public void setEntYn(String entYn) {

        this.entYn = entYn;
    }


    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate; }

    public Date getEntDate() {
        return entDate; }

    public void setEntDate(Date entDate) {
        this.entDate = entDate;}

    public String getEmpName() {

        return empName;
    }

    public int getEmpNo() {

        return empNo;
    }

    public String getEmail() {

        return email;
    }

    public int getPhone() {

        return phone;
    }

    public String getDeptCode() {

        return deptCode;
    }

    public int getJobCode() {

        return jobCode;
    }

    public String getSalLevel() {

        return salLevel;
    }

    public int getSalary() {

        return salary;
    }

    public Float getBonus() {

        return bonus;
    }

    public int getManagerId() {

        return managerId;
    }

    public String getEntYn() {

        return entYn;
    }


    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "empId=" + empId +
                ", empName='" + empName + '\'' +
                ", empNo=" + empNo +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", deptCode='" + deptCode + '\'' +
                ", jobCode=" + jobCode +
                ", salLevel='" + salLevel + '\'' +
                ", salary=" + salary +
                ", bonus=" + bonus +
                ", managerId=" + managerId +
                '}';
    }
}