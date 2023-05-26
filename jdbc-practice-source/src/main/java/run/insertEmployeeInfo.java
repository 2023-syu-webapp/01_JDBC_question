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

import static common.JDBCTemplate.closes;
import static common.JDBCTemplate.getConnection;

public class insertEmployeeInfo {

    // 사원 정보 전체를 입력받아 DTO객체에 담아서 insert
    // insert 성공하면 "직원 등록에 성공하였습니다." 출력
    // insert 실패하면 "직원 등록에 실패하였습니다." 출력
    public static void main(String[] args) {
        Connection con = getConnection();

        PreparedStatement pstmt = null;
        int result = 0;
        EmployeeDTO eDTO = new EmployeeDTO();

        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/mapper/employee-query.xml"));
            String query = prop.getProperty("insertEmployee");

            Scanner sc = new Scanner(System.in);
            System.out.println("등록하실 사원의 번호를 입력하세요 : ");
            String empId = sc.nextLine();
            System.out.println("등록하실 사원의 이름을 입력하세요 : ");
            String empName = sc.nextLine();
            System.out.println("등록하실 사원의 주민번호를 입력하세요 : ");
            String empNo = sc.nextLine();
            System.out.println("등록하실 사원의 이메일을 입력하세요 : ");
            String email = sc.nextLine();
            System.out.println("등록하실 사원의 전화번호를 입력하세요 : ");
            String phone = sc.nextLine();
            System.out.println("등록하실 사원의 부서 번호를 입력하세요 : ");
            String deptCode = sc.nextLine();
            System.out.println("등록하실 사원의 job 번호를 입력하세요 : ");
            String jobCode = sc.nextLine();
            System.out.println("등록하실 사원의 봉급레벨을 입력하세요 : ");
            String salLevel = sc.nextLine();
            System.out.println("등록하실 사원의 봉금을 입력하세요 : ");
            int salary = sc.nextInt();
            System.out.println("등록하실 사원의 보너스를 입력하세요 : ");
            Double bonus = sc.nextDouble();
            System.out.println("등록하실 사원의 사수 번호를 입력하세요 : ");
            String managerId = sc.nextLine();
            sc.nextLine();
            System.out.println("등록하실 사원의 생년월일을 입력하세요 : ");
            Date hireDate = Date.valueOf(sc.nextLine());
            System.out.println("등록하실 사원의 퇴사 여부를 입력하세요 : ");
            String entYn = sc.nextLine();

            eDTO.setEmpId(empId);
            eDTO.setEmpName(empName);
            eDTO.setEmpNo(empNo);
            eDTO.setEmail(email);
            eDTO.setPhone(phone);
            eDTO.setDepCode(deptCode);
            eDTO.setJobCode(jobCode);
            eDTO.setSallLevel(salLevel);
            eDTO.setSalary(salary);
            eDTO.setBonus(bonus);
            eDTO.setManagerId(managerId);
            eDTO.setHireDate(hireDate);
            eDTO.setEntYn(entYn);

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, eDTO.getEmpId());
            pstmt.setString(2, eDTO.getEmpName());
            pstmt.setString(3, eDTO.getEmpNo());
            pstmt.setString(4, eDTO.getEmail());
            pstmt.setString(5, eDTO.getPhone());
            pstmt.setString(6, eDTO.getDepCode());
            pstmt.setString(7, eDTO.getJobCode());
            pstmt.setString(8, eDTO.getSallLevel());
            pstmt.setInt(9, eDTO.getSalary());
            pstmt.setDouble(10, eDTO.getBonus());
            pstmt.setString(11, eDTO.getManagerId());
            pstmt.setString(12, String.valueOf(eDTO.getHireDate()));
            pstmt.setString(13, eDTO.getEntYn());

            result = pstmt.executeUpdate();

        } catch (IOException e) {
           e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closes(con);
            closes(pstmt);
        }

        if (result > 0) {
            System.out.println("직원등록에 성공했습니다.");
            System.out.println("result = " + result);
        } else {
            System.out.println("직원등록에 실패했습니다..");
            System.out.println("result = " + result);
        }
    }
}
