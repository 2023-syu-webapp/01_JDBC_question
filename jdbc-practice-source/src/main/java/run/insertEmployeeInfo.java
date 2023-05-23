package run;

import model.dto.EmployeeDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.Scanner;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

// 사원 정보 전체를 입력받아 DTO객체에 담아서 insert
// insert 성공하면 "직원 등록에 성공하였습니다." 출력
// insert 실패하면 "직원 등록에 실패하였습니다." 출력
public class insertEmployeeInfo {
    public static void main(String[] args) {

        Connection con = getConnection();

        PreparedStatement pstmt = null;
        EmployeeDTO newEmp = null;
        int result = 0;

        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("jdbc-practice-source/src/main/java/mapper/employee-query.xml"));

            String query = prop.getProperty("insertEmp");
            System.out.println(query);

            pstmt = con.prepareStatement(query);

            Scanner sc = new Scanner(System.in);
            newEmp = new EmployeeDTO();

            System.out.print("사원 번호를 입력해주세요 : ");
            newEmp.setEmpId(sc.next());
            System.out.print("사원 이름을 입력해주세요 : ");
            newEmp.setEmpName(sc.next());
            System.out.print("사원 주민등록번호를 입력해주세요 : ");
            newEmp.setEmpNo(sc.next());
            System.out.print("사원 이메일을 입력해주세요 : ");
            newEmp.setEmail(sc.next());
            System.out.print("사원 전화번호를 입력해주세요 : ");
            newEmp.setPhone(sc.next());
            System.out.print("사원 부서번호를 입력해주세요 : ");
            newEmp.setDeptCode(sc.next());
            System.out.print("사원 JOB_CODE를 입력해주세요 : ");
            newEmp.setJobCode(sc.next());
            System.out.print("사원 급여레벨을 입력해주세요 : ");
            newEmp.setSalLevel(sc.next());
            System.out.print("사원 급여를 입력해주세요 : ");
            newEmp.setSalary(sc.nextInt());
            System.out.print("사원 보너스를 입력해주세요 : ");
            newEmp.setBonus(sc.nextDouble());
            System.out.print("사원 상사번호 입력해주세요 : ");
            newEmp.setManagerId(sc.next());

            System.out.print("사원 고용일을 입력해주세요 (yyyy-MM-dd) : ");
            String hireDate = sc.next();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(hireDate, dateTimeFormatter);
            newEmp.setHireDate(Date.valueOf(date));

            System.out.print("ENT 날짜를 입력해주세요 : ");
            String entDate = sc.next();
            dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            date = LocalDate.parse(entDate, dateTimeFormatter);
            newEmp.setEntDate(Date.valueOf(date));

            System.out.print("ENT 여부를 입력해주세요 (Y/N) : ");
            newEmp.setEntYn(sc.next());

            pstmt.setString(1, newEmp.getEmpId());
            pstmt.setString(2, newEmp.getEmpName());
            pstmt.setString(3, newEmp.getEmpNo());
            pstmt.setString(4, newEmp.getEmail());
            pstmt.setString(5, newEmp.getPhone());
            pstmt.setString(6, newEmp.getDeptCode());
            pstmt.setString(7, newEmp.getJobCode());
            pstmt.setString(8, newEmp.getSalLevel());
            pstmt.setInt(9, newEmp.getSalary());
            pstmt.setDouble(10, newEmp.getBonus());
            pstmt.setString(11, newEmp.getManagerId());
            pstmt.setDate(12, newEmp.getHireDate());
            pstmt.setDate(13, newEmp.getEntDate());
            pstmt.setString(14, newEmp.getEntYn());

            result = pstmt.executeUpdate();

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        } finally {
            close(con);
            close(pstmt);
        }

        if (result > 0) {
            System.out.println("직원 등록에 성공하였습니다.");
        } else {
            System.out.println("직원 등록에 실패하였습니다.");
        }
    }
}
