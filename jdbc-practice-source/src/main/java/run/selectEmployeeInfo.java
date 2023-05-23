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
        ResultSet rset = null;

        EmployeeDTO selectedEmp = null;

        Scanner sc = new Scanner(System.in);
        System.out.print("조회할 사원의 사원번호를 입력해주세요 : ");
        String empId = sc.nextLine();

        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("jdbc-practice-source/src/main/java/mapper/employee-query.xml"));

            String query = prop.getProperty("selectEmp");

            pstmt = con.prepareStatement(query);

            selectedEmp = new EmployeeDTO();
            selectedEmp.setEmpId(empId);
            pstmt.setString(1, selectedEmp.getEmpId());

            rset = pstmt.executeQuery();

            while (rset.next()) {
                selectedEmp.setEmpId(rset.getString("EMP_ID"));
                selectedEmp.setEmpName(rset.getString("EMP_NAME"));
                selectedEmp.setEmpNo(rset.getString("EMP_NO"));
                selectedEmp.setEmail(rset.getString("EMAIL"));
                selectedEmp.setPhone(rset.getString("PHONE"));
                selectedEmp.setDeptCode(rset.getString("DEPT_CODE"));
                selectedEmp.setJobCode(rset.getString("JOB_CODE"));
                selectedEmp.setSalary(rset.getInt("SALARY"));
                selectedEmp.setSalLevel(rset.getString("SAL_LEVEL"));
                selectedEmp.setBonus(rset.getDouble("BONUS"));
                selectedEmp.setManagerId(rset.getString("MANAGER_ID"));
                selectedEmp.setHireDate(rset.getDate("HIRE_DATE"));
                selectedEmp.setEntDate(rset.getDate("ENT_DATE"));
                selectedEmp.setEntYn(rset.getString("ENT_YN"));
            }

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        } finally {
            close(con);
            close(pstmt);
            close(rset);
        }

        if (selectedEmp != null) {
            System.out.println(selectedEmp);
            System.out.println(selectedEmp.getEmpName() + "(" + selectedEmp.getDeptCode() + ") " + selectedEmp.getSalLevel() + "님 환영합니다.");
        }
    }

}
