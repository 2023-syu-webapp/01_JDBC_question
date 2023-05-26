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
        ResultSet rs = null;

        Properties prop = new Properties();
        EmployeeDTO row = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("사원번호를 입력하세요 : ");
        String empNo = sc.nextLine();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/mapper/employee-query.xml"));

            String query = prop.getProperty("selectEmployee");
            pstmt =con.prepareStatement(query);
            pstmt.setString(1,empNo);

            rs = pstmt.executeQuery();

            if(rs.next()){
                row = new EmployeeDTO();
                row.setEmpName(rs.getString("EMPLOYEE.EMP_NAME"));
                row.setEmpId(rs.getString("EMP_ID"));
                row.setDepartmentTitle(rs.getString("DEPT_TITLE"));
                row.setJobName(rs.getString("JOB_NAME"));
            }

            System.out.println("["+row.getEmpName()+"]"+"(["+row.getDepartmentTitle()+"])"+"(["+row.getJobName()+"])");



        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(con);
            close(pstmt);
        }
    }
}