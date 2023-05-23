package run;

import model.dto.EmployeeDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Properties;
import java.util.Scanner;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

public class insertEmployeeInfo {

    // 사원 정보 전체를 입력받아 DTO객체에 담아서 insert
    // insert 성공하면 "직원 등록에 성공하였습니다." 출력
    // insert 실패하면 "직원 등록에 실패하였습니다." 출력

    public static void main(String[] args) {

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        int result = 0;
        EmployeeDTO emp = null;

        Properties prop = new Properties();
        try {
            prop.loadFromXML(new FileInputStream("/Users/heesue/Desktop/WebApp/01_JDBC_question/jdbc-practice-source/src/main/java/mapper/employee-query.xml"));
            String query = prop.getProperty("insertEmp");

            emp = new EmployeeDTO();

            Scanner sc = new Scanner(System.in);
            System.out.println("=== 등록할 지원의 정보를 입력해 주세요 ===");
            System.out.print("사번 : ");
            emp.setEmpId(sc.nextLine());
            System.out.print("이름 : ");
            emp.setEmpName(sc.nextLine());
            System.out.print("주민등록번호 : ");
            emp.setEmpNo(sc.nextLine());
            System.out.print("이메일 : ");
            emp.setEmail(sc.nextLine());
            System.out.print("휴대폰 번호 : ");
            emp.setPhone(sc.nextLine());
            System.out.print("부서 코드 : ");
            emp.setDepCode(sc.nextLine());
            System.out.print("직급 코드 : ");
            emp.setJobCode(sc.nextLine());
            System.out.print("급여등급 : ");
            emp.setSalLevel(sc.nextLine());
            System.out.print("급여 : ");
            emp.setSalary(sc.nextInt());
            System.out.print("보너스율 : ");
            emp.setBonus(sc.nextDouble());
            System.out.print("관리자사번 : ");
            sc.nextLine();
            emp.setManagerId(sc.nextLine());

            System.out.print("입사일 (yyyy-mm-dd) : ");
            String inputHireDate = sc.nextLine();
            Date HireDate= Date.valueOf(inputHireDate);
            emp.setHireDate(HireDate);

            System.out.print("퇴사일 (yyyy-mm-dd) : ");
            String inputEntDate = sc.nextLine();
            Date entDate = Date.valueOf(inputEntDate);
            emp.setEntDate(entDate);

            System.out.print("퇴직여부 (Y/N) : ");
            emp.setEntYn(sc.nextLine().toUpperCase());

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, emp.getEmpId());
            pstmt.setString(2, emp.getEmpName());
            pstmt.setString(3, emp.getEmpNo());
            pstmt.setString(4, emp.getEmail());
            pstmt.setString(5, emp.getPhone());
            pstmt.setString(6, emp.getDepCode());
            pstmt.setString(7, emp.getJobCode());
            pstmt.setString(8, emp.getSalLevel());
            pstmt.setInt(9, emp.getSalary());
            pstmt.setDouble(10, emp.getBonus());
            pstmt.setString(11, emp.getManagerId());
            pstmt.setDate(12, emp.getHireDate());
            pstmt.setDate(13, emp.getEntDate());
            pstmt.setString(14, emp.getEntYn());

            result = pstmt.executeUpdate();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con);
            close(pstmt);
        }

        if(result > 0 ) {
            System.out.println("직원 등록에 성공하였습니다.");
        } else {
            System.out.println("직원 등록에 실패하였습니다.");
        }

    }

}
