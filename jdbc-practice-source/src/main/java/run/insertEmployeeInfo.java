package run;

import model.dto.EmployeeDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

public class insertEmployeeInfo {

    // 사원 정보 전체를 입력받아 DTO객체에 담아서 insert
    // insert 성공하면 "직원 등록에 성공하였습니다." 출력
    // insert 실패하면 "직원 등록에 실패하였습니다." 출력
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Connection con = getConnection();
        PreparedStatement pstmt = null;
        EmployeeDTO emp = new EmployeeDTO();
        Properties prop = new Properties();
        int result = 0;
        Date hireDate;
        Date entDate;

        try {
            prop.loadFromXML(new FileInputStream("jdbc-practice-source/src/main/java/mapper/employee-query.xml"));
            String query = prop.getProperty("insertEmp");

            System.out.print("직원의 id를 입력하세요 : ");
            emp.setEmpId(sc.nextLine());

            System.out.print("직원의 이름을 입력하세요 : ");
            emp.setEmpName(sc.nextLine());

            System.out.print("직원의 번호를 입력하세요 : ");
            emp.setEmpNo(sc.nextLine());

            System.out.print("직원의 이메일을 입력하세요 : ");
            emp.setEmail(sc.nextLine());

            System.out.print("직원의 전화번호를 입력하세요 : ");
            emp.setPhone(sc.nextLine());

            System.out.print("직원의 부서 번호를 입력하세요 : ");
            emp.setDeptCode(sc.nextLine());

            System.out.print("직원의 직급 번호을 입력하세요 : ");
            emp.setJobCode(sc.nextLine());

            System.out.print("직원의 급여 레벨을 입력하세요 : ");
            emp.setSalLevel(sc.nextLine());

            System.out.print("직원의 급여를 입력하세요 : ");
            emp.setSalary(sc.nextInt());

            System.out.print("직원의 보너스를 입력하세요 : ");
            emp.setBonus(sc.nextDouble());

            System.out.print("직원의 매니저 id를 입력하세요 : ");
            sc.nextLine();
            emp.setManagerId(sc.nextLine());

            System.out.print("직원의 hireDate를 입력하세요 (yyyy-MM-dd) : ");
            hireDate = Date.valueOf(sc.nextLine());
            emp.setHireDate(hireDate);

            System.out.print("직원의 entDate을 입력하세요 (yyyy-MM-dd) : ");
            entDate = Date.valueOf(sc.nextLine());
            emp.setEntDate(entDate);

            System.out.print("직원의 entYn을 입력하세요 : ");
            emp.setEntYn(sc.nextLine().toUpperCase());

            pstmt = con.prepareStatement(query);

            pstmt.setString(1, emp.getEmpId());
            pstmt.setString(2, emp.getEmpName());
            pstmt.setString(3, emp.getEmpNo());
            pstmt.setString(4, emp.getEmail());
            pstmt.setString(5, emp.getPhone());
            pstmt.setString(6, emp.getDeptCode());
            pstmt.setString(7, emp.getJobCode());
            pstmt.setString(8, emp.getSalLevel());
            pstmt.setInt(9, emp.getSalary());
            pstmt.setDouble(10, emp.getBonus());
            pstmt.setString(11, emp.getManagerId());
            pstmt.setObject(12, emp.getHireDate());
            pstmt.setObject(13, emp.getEntDate());
            pstmt.setString(14, emp.getEmpId());

            result = pstmt.executeUpdate();
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);

        } finally {
            close(con);
            close(pstmt);
        }
        if (result > 0) {
            System.out.println("등록이 완료되었습니다");
        } else {
            System.out.println("등록이 완료되지 않았습니다...");
        }

    }
}
