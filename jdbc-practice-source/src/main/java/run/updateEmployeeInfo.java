package run;

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
            prop.loadFromXML(new FileInputStream("jdbc-practice-source/src/main/java/mapper/employee-query.xml"));
            String query = prop.getProperty("updateMenu");
            pstmt = con.prepareStatement(query);

            Scanner sc = new Scanner(System.in);
            System.out.println("수정할 사원 번호 입력:");
            int empId = sc.nextInt();
            sc.nextLine();

            System.out.println("전화번호 입력:");
            String phone = sc.nextLine();

            System.out.println("이메일 입력:");
            String email = sc.nextLine();

            System.out.println("부서코드 입력:");
            String deptCode = sc.nextLine();

            System.out.println("급여 입력:");
            int salary = sc.nextInt();

            System.out.println("보너스 입력:");
            double bonus = sc.nextDouble();

            pstmt.setString(1, phone);
            pstmt.setString(2, email);
            pstmt.setString(3, deptCode);
            pstmt.setInt(4, salary);
            pstmt.setDouble(5, bonus);
            pstmt.setInt(6, empId);

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

