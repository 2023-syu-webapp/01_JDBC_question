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

        Properties prop = new Properties();
        try {
            prop.loadFromXML(new FileInputStream("/Users/heesue/Desktop/WebApp/01_JDBC_question/jdbc-practice-source/src/main/java/mapper/employee-query.xml"));
            String query = prop.getProperty("updateEmp");

            Scanner sc = new Scanner(System.in);
            System.out.print("수정할 사원 번호를 입력해주세요 : ");
            String empId = sc.nextLine();
            System.out.print("수정할 사원 전화번호를 입력해주세요 : ");
            String empPhone = sc.nextLine();
            System.out.print("수정할 사원 이메일을 입력해주세요 : ");
            String empEmail = sc.nextLine();
            System.out.print("수정할 사원의 부서코드를 입력해주세요 : ");
            String empDepCode = sc.nextLine();
            System.out.print("수정할 사원 급여를 입력해주세요 : ");
            int empSalary = sc.nextInt();
            sc.nextLine();
            System.out.print("수정할 사원 보너스를 입력해주세요 : ");
            double empBonus = sc.nextDouble();
            sc.nextLine();

            pstmt = con.prepareStatement(query);

            pstmt.setString(1, empPhone);
            pstmt.setString(2, empEmail);
            pstmt.setString(3, empDepCode);
            pstmt.setInt(4, empSalary);
            pstmt.setDouble(5, empBonus);
            pstmt.setString(6, empId);

            result = pstmt.executeUpdate();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con);
            close(pstmt);
        }

        if (result > 0) {
            System.out.println("직원 정보 수정에 성공하였습니다.");
        } else {
            System.out.println("직원 정보 수정에 실패하였습니다.");
        }

    }

}
