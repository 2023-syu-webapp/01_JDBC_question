package run;

import model.dto.EmployeeDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

public class selectEmployeeInfo {

    // 사원 번호를 입력받아 해당 사원을 조회하고 DTO객체에 담아서 출력
    // 출력 구문은 DTO 객체의 toString() 내용과
    // "[이름]([부서명]) [직급명]님 환영합니다." 로 출력.

    public static void main(String[] args) {
        Connection con = getConnection();

        PreparedStatement pstmt = null;
        int result = 0;
        EmployeeDTO employeeDTO = null;
        ResultSet rset = null;
        Properties prop = new Properties();
        String deptTitle = null;
        String jobName = null;

        try {
            prop.loadFromXML(new FileInputStream("jdbc-practice-source/src/main/java/mapper/employee-query.xml"));

            String query = prop.getProperty("selectEmployee");

            Scanner sc = new Scanner(System.in);
            System.out.println("조회할 사원의 ID를 입력해 주세요 :");
            String empId = sc.nextLine();

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, empId);
            rset = pstmt.executeQuery();

            while(rset.next()) {
                employeeDTO = new EmployeeDTO();
                employeeDTO.setEmpId(rset.getString("EMP_ID"));
                employeeDTO.setEmpName(rset.getString("EMP_NAME"));
                employeeDTO.setEmpNo(rset.getString("EMP_NO"));
                employeeDTO.setEmail(rset.getString("EMAIL"));
                employeeDTO.setPhone(rset.getString("PHONE"));
                employeeDTO.setDeptCode(rset.getString("DEPT_CODE"));
                employeeDTO.setJobCode(rset.getString("JOB_CODE"));
                employeeDTO.setSalLevel(rset.getString("SAL_LEVEL"));
                employeeDTO.setSalary(rset.getInt("SALARY"));
                employeeDTO.setBonus(rset.getDouble("BONUS"));
                employeeDTO.setManagerId(rset.getString("MANAGER_ID"));
                employeeDTO.setHireDate(rset.getDate("HIRE_DATE"));
                employeeDTO.setEntDate(rset.getDate("ENT_DATE"));
                employeeDTO.setEntYn(rset.getString("ENT_YN"));
                jobName = rset.getString("JOB_NAME");
                deptTitle = rset.getString("DEPT_TITLE");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con);
            close(pstmt);
            close(rset);
        }

        System.out.println(employeeDTO.toString());
        System.out.println( "[" + employeeDTO.getEmpName() + "]([" + deptTitle + "]) [" + jobName + "]님 환영합니다.");




    }
}
