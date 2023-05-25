package run;

import model.dto.EmployeeDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
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
        EmployeeDTO row = null;

        Properties prop = new Properties();
        try {
            prop.loadFromXML(new FileInputStream("jdbc-practice-source/src/main/java/mapper/employee-query.xml"));
            String query = prop.getProperty("insertMenu");
            System.out.println(query);

            Scanner sc = new Scanner(System.in);
            System.out.println("사원 번호를 입력해주세요");
            int empId= sc.nextInt();
            System.out.println("사원 이름을 입력해주세요:");
            String empName = sc.nextLine();
            System.out.println("사원 생년월일을 입력해주세요:");
            String empNo = sc.nextLine();
            System.out.println("이메일을 입력해주세요:");
            String email = sc.nextLine();
            System.out.println("전화번호를 입력해주세요:");
            String phone = sc.nextLine();
            System.out.println("부서 코드를 입력해주세요:");
            String deptCode = sc.nextLine();
            System.out.println("직급 코드를 입력해주세요:");
            String jobCode = sc.nextLine();
            System.out.println("급여 등급을 입력해주세요:");
            String salLevel = sc.nextLine();
            System.out.println("급여를 입력해주세요:");
            int salary = sc.nextInt();
            System.out.println("보너스를 입력해주세요:");
            double bonus = sc.nextDouble();
            sc.nextLine(); // 버퍼 비우기
            System.out.println("매니저 ID를 입력해주세요:");
            String managerId = sc.nextLine();
            System.out.println("입사일을 입력해주세요 (yyyy-MM-dd 형식으로):");
            String hireDate = sc.nextLine();
            System.out.println("퇴사일을 입력해주세요 (yyyy-MM-dd 형식으로):");
            String entDate  = sc.nextLine();
            System.out.println("재직 여부를 입력해주세요 (Y/N):");
            String entYn = sc.nextLine();
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, empId);
            pstmt.setString(2, empName);
            pstmt.setString(3, empNo);
            pstmt.setString(4, email);
            pstmt.setString(5, phone);
            pstmt.setString(6, deptCode);
            pstmt.setString(7, jobCode);
            pstmt.setString(8, salLevel);
            pstmt.setInt(9, salary);
            pstmt.setDouble(10, bonus);
            pstmt.setString(11,managerId);
            pstmt.setString(12, hireDate);
            pstmt.setString(13, entDate);
            pstmt.setString(14, entYn);


            result = pstmt.executeUpdate();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(con);
            close(pstmt);
        }
        if (result > 0){
            System.out.println("직원 등록에 성공하였습니다.");
        } else{
            System.out.println("직원 등록에 실패하였습니다.");
        }

    }

}
