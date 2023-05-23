package run;

import model.dto.EmployeeDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

public class updateEmployeeInfo {

    // 수정할 사원 번호를 입력받고
    // 사원 정보(전화번호, 이메일, 부서코드, 급여, 보너스)를 입력받아 DTO객체에 담아서 update
    // update 성공하면 "직원 정보 수정에 성공하였습니다." 출력
    // update 실패하면 "직원 정보 수정에 실패하였습니다." 출력

    public static void main(String[] args) {

        Connection con = getConnection();

        PreparedStatement pstmt = null;
        int result = 0;
        EmployeeDTO updateEmp = null;

        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("jdbc-practice-source/src/main/java/mapper/employee-query.xml"));
            String query = prop.getProperty("updateEmp");

            updateEmp = new EmployeeDTO();
            Scanner sc = new Scanner(System.in);
            System.out.print("변경할 사원의 사원번호를 입력하세요 : ");
            updateEmp.setEmpId(sc.next());
            System.out.print("변경할 사원의 전화번호를 입력하세요 : ");
            updateEmp.setPhone(sc.next());
            System.out.print("변경할 사원의 이메일을 입력하세요 : ");
            updateEmp.setEmail(sc.next());
            System.out.print("변경할 사원의 부서코드를 입력하세요 : ");
            updateEmp.setDeptCode(sc.next());
            System.out.print("변경할 사원의 급여를 입력하세요 : ");
            updateEmp.setSalary(sc.nextInt());
            System.out.print("변경할 사원의 보너스를 입력하세요 : ");
            updateEmp.setBonus(sc.nextDouble());

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, updateEmp.getPhone());
            pstmt.setString(2, updateEmp.getEmail());
            pstmt.setString(3, updateEmp.getDeptCode());
            pstmt.setInt(4, updateEmp.getSalary());
            pstmt.setDouble(5, updateEmp.getBonus());
            pstmt.setString(6, updateEmp.getEmpId());

            result = pstmt.executeUpdate();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
            close(con);
        }

        if (result > 0) {
            System.out.println("직원 정보 수정에 성공하였습니다.");
        } else {
            System.out.println("직원 정보 수정에 실패하였습니다.");
        }
    }
}
