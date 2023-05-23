package run;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Properties;

import model.dto.EmployeeDTO;
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
        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/mapper/employee-query.xml"));

            String query = prop.getProperty("insertEmp");
            System.out.println(query);

            Scanner sc = new Scanner(System.in);
            System.out.println("직원의 사번을 입력하세요: ");
            String empId = sc.nextLine();
            System.out.println("직원의 이름을 입력하세요: ");
            String empName = sc.nextLine();
            System.out.println("직원의 주민번호를 입력하세요: ");
            String empNo = sc.nextLine();
            System.out.println("직원의 이메일을 입력하세요: ");
            String email = sc.nextLine();
            System.out.println("직원의 전화번호를 입력하세요: ");
            String phone = sc.nextLine();
            System.out.println("직원의 부서 코드를 입력하세요: ");
            String deptCode = sc.nextLine();
            System.out.println("직원의 직무 코드를 입력하세요: ");
            String jobCode = sc.nextLine();
            System.out.println("직원의 급여 등급을 입력하세요: ");
            String salLevel = sc.nextLine();
            System.out.println("직원의 급여를 입력하세요: ");
            int salary = sc.nextInt();
            System.out.println("직원의 보너스를 입력하세요: ");
            double bonus = sc.nextDouble();
            System.out.println("직원의 상사 사번을 입력하세요: ");
            String managerId = sc.next();
            sc.nextLine();
            System.out.println("직원의 입사일을 입력하세요 (YYYY-MM-DD): ");
            String hireDate = sc.nextLine();
            System.out.println("직원의 퇴사일을 입력하세요 (YYYY-MM-DD): ");
            String entDate = sc.nextLine();
            System.out.println("직원의 퇴사 여부를 입력하세요 (Y/N): ");
            String entYn = sc.nextLine().toUpperCase();

            // 입력받은 정보로 EmployeeDTO 객체 생성 및 값 설정
            EmployeeDTO employeeDTO = new EmployeeDTO();
            employeeDTO.setEmpId(empId);
            employeeDTO.setEmpName(empName);
            employeeDTO.setEmpNo(empNo);
            employeeDTO.setEmail(email);
            employeeDTO.setPhone(phone);
            employeeDTO.setDeptCode(deptCode);
            employeeDTO.setJobCode(jobCode);
            employeeDTO.setSalLevel(salLevel);
            employeeDTO.setSalary(salary);
            employeeDTO.setBonus(bonus);
            employeeDTO.setManagerId(managerId);
            employeeDTO.setHireDate(Date.valueOf(hireDate));
            employeeDTO.setEntDate(Date.valueOf(entDate));
            employeeDTO.setEntYn(entYn);

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, employeeDTO.getEmpId());
            pstmt.setString(2, employeeDTO.getEmpName());
            pstmt.setString(3, employeeDTO.getEmpNo());
            pstmt.setString(4, employeeDTO.getEmail());
            pstmt.setString(5, employeeDTO.getPhone());
            pstmt.setString(6, employeeDTO.getDeptCode());
            pstmt.setString(7, employeeDTO.getJobCode());
            pstmt.setString(8, employeeDTO.getSalLevel());
            pstmt.setInt(9, employeeDTO.getSalary());
            pstmt.setDouble(10, employeeDTO.getBonus());
            pstmt.setString(11, employeeDTO.getManagerId());
            pstmt.setDate(12, employeeDTO.getHireDate());
            pstmt.setDate(13, employeeDTO.getEntDate());
            pstmt.setString(14, employeeDTO.getEntYn());

            result = pstmt.executeUpdate();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
            close(con);
        }
        if (result > 0) {
            System.out.println("직원 등록에 성공하였습니다.");
        } else {
            System.out.println("직원 등록에 실패하였습니다.");
        }
    }

}
