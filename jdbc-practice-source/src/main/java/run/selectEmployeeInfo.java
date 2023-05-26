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
        EmployeeDTO emp = new EmployeeDTO();

        Scanner sc = new Scanner(System.in);
        Properties prop = new Properties();


        try {
            prop.loadFromXML(new FileInputStream("jdbc-practice-source/src/main/java/mapper/employee-query.xml"));
            String query = prop.getProperty("joinSelectEmp");

            System.out.print("사원 번호를 입력해주세요 : ");
            String empId = sc.nextLine();

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, empId);
            rset = pstmt.executeQuery();

            while (rset.next()) {
                emp.setEmpName(rset.getString("EMP_NAME"));
                emp.setDeptCode(rset.getString("DEPT_TITLE"));
                emp.setJobCode(rset.getString("JOB_NAME"));
            }


        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con);
            close(pstmt);
            close(rset);
        }


        System.out.println(emp.getEmpName() + "(" + emp.getDeptCode() + ") " + emp.getJobCode() + "님 환영합니다.");
    }
}
