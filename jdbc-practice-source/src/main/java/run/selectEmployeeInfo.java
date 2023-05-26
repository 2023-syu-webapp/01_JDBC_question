package run;

import model.dto.DeptDTO;
import model.dto.EmployeeDTO;
import model.dto.JobDTO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;
import java.util.Scanner;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

public class selectEmployeeInfo {

    public static void main(String[] args) {

        Connection con = getConnection();

        PreparedStatement pstmt = null;
        ResultSet rset = null;
        EmployeeDTO empdto = null;
        JobDTO jobdto = null;
        DeptDTO deptdto = null;
        Scanner sc = new Scanner(System.in);
        Properties prop = new Properties();

        try{
            prop.loadFromXML(new FileInputStream("jdbc-practice-source/src/main/java/mapper/employee-query.xml"));
            String query = prop.getProperty("selectEmployee");

            System.out.println("조회할 사원의 아이디를 입력하세요");
            String empName = sc.next();

            empdto = new EmployeeDTO();
            empdto.setEmpId(empName);
            jobdto = new JobDTO();
            deptdto = new DeptDTO();

            pstmt = con.prepareStatement(query);
            pstmt.setString(1,empdto.getEmpId());

            jobdto.setJobId(empdto.getJobCode());
            deptdto.setDeptId(empdto.getDeptCode());
            rset = pstmt.executeQuery();

            while(rset.next()) {
                empdto.setEmpId(rset.getString("EMP_ID"));
                empdto.setEmpName(rset.getString("EMP_NAME"));
                empdto.setEmpNo(rset.getString("EMP_NO"));
                empdto.setEmail(rset.getString("EMAIL"));
                empdto.setPhone(rset.getString("PHONE"));
                empdto.setDeptCode(rset.getString("DEPT_CODE"));
                empdto.setJobCode(rset.getString("JOB_CODE"));
                empdto.setSalary(rset.getInt("SALARY"));
                empdto.setBonus(rset.getDouble("BONUS"));
                empdto.setManagerId(rset.getString("MANAGER_ID"));
                empdto.setHireDate(rset.getDate("HIRE_DATE"));
                empdto.setEntDate(rset.getDate("ENT_DATE"));
                empdto.setEntYn(rset.getString("ENT_YN"));
                jobdto.setJobName(rset.getString("JOB_NAME"));
                deptdto.setDepttitle(rset.getString("DEPT_TITLE"));
            }

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            close(con);
            close(pstmt);
            close(rset);
        }

        String jobName = "";
        if( empdto != null) {
            System.out.println(empdto.toString());
            System.out.println(empdto.getEmpName()+"("+ deptdto.getDepttitle() +")"+jobdto.getJobName()+ "님 환영합니다.");
        }
    }

    // 사원 번호를 입력받아 해당 사원을 조회하고 DTO객체에 담아서 출력
    // 출력 구문은 DTO 객체의 toString() 내용과
    // "[이름]([부서명]) [직급명]님 환영합니다." 로 출력.

}
