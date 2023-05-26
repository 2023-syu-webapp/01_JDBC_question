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
        Scanner sc = new Scanner(System.in);

        Connection con = getConnection();
        EmployeeDTO emp = new EmployeeDTO();
        PreparedStatement pstmt = null;
        Properties prop = new Properties();
        int result = 0;

        try {
            prop.loadFromXML(new FileInputStream("jdbc-practice-source/src/main/java/mapper/employee-query.xml"));
            String query = prop.getProperty("updateEmp");

            System.out.print("회원의 번호를 입력해주세요 : ");
            emp.setEmpId(sc.nextLine());

            System.out.print("회원의 전화번호를 입력해주세요 : ");
            emp.setPhone(sc.nextLine());

            System.out.print("회원의 이메일을 입력해주세요 : ");
            emp.setEmail(sc.nextLine());

            System.out.print("회원의 부서코드를 입력해주세요 : ");
            emp.setDeptCode(sc.nextLine());

            System.out.print("회원의 급여를 입력해주세요 : ");
            emp.setSalary(sc.nextInt());

            System.out.print("회원의 보너스를 입력해주세요 : ");
            emp.setBonus(sc.nextDouble());

            pstmt = con.prepareStatement(query);

            pstmt.setString(1, emp.getPhone());
            pstmt.setString(2, emp.getEmail());
            pstmt.setString(3, emp.getDeptCode());
            pstmt.setInt(4, emp.getSalary());
            pstmt.setDouble(5, emp.getBonus());
            pstmt.setString(6, emp.getEmpId());

            result = pstmt.executeUpdate();
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con);
            close(pstmt);
        }


        if (result > 0) {
            System.out.println("정보수정을 성공하였습니다.");
        } else {
            System.out.println("정보 수정에 실패하였습니다...");
        }
    }
}