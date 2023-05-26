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
            String query = prop.getProperty("insertEmployee");


            Scanner sc = new Scanner(System.in);
            System.out.println("사원의 이름을 입력하세요 : ");
            String empName = sc.nextLine();
            System.out.println("사원의 넘버를 입력하세요 : ");
            String empNo =sc.nextLine();
            System.out.println("사원의 이메일을 입력하세요 : ");
            String email = sc.nextLine();
            System.out.println("사원의 전화번호를 입력하세요 : ");
            String phone = sc.nextLine();
            System.out.println("사원의 부서코드를 입력하세요 : ");
            String deptCode = sc.nextLine();
            System.out.println("사원의 직무코드를 입력하세요 : ");
            String jobCode = sc.nextLine();
            System.out.println("사원의 직급을 입력하세요 : ");
            String salLevel = sc.nextLine();
            System.out.println("사원의 급여를 입력하세요 : ");
            int salary = sc.nextInt();
            System.out.println("사원의 보너스를 입력하세요 : ");
            double bonus = sc.nextDouble();
            System.out.println("사원의 매니저아이디를 입력하세요 : ");
            sc.nextLine();
            String managerId = sc.nextLine();
            System.out.println("사원의 입사일을 입력하세요 : ");
            String hire = sc.nextLine();
            java.sql.Date hireDate = java.sql.Date.valueOf(hire);
            System.out.println("사원의 퇴사일을 입력하세요 : ");
            String ent = sc.nextLine();
            java.sql.Date entDate = java.sql.Date.valueOf(ent);
            System.out.println("사원의 퇴사여부를 입력하세요 (Y/N) : ");
            String entYn = sc.nextLine().toUpperCase();

            EmployeeDTO employeeDTO = new EmployeeDTO("", empName, empNo, email, phone, deptCode, jobCode, salLevel, salary, bonus, managerId, hireDate, entDate, entYn);

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, employeeDTO.getEmpName());
            pstmt.setString(2, employeeDTO.getEmpNo());
            pstmt.setString(3, employeeDTO.getEmail());
            pstmt.setString(4, employeeDTO.getPhone());
            pstmt.setString(5, employeeDTO.getDept_code());
            pstmt.setString(6, employeeDTO.getJob_code());
            pstmt.setString(7, employeeDTO.getSal_level());
            pstmt.setInt(8, employeeDTO.getSalary());
            pstmt.setDouble(9, employeeDTO.getBonus());
            pstmt.setString(10, employeeDTO.getHireDate().toString());
            pstmt.setString(11, employeeDTO.getEntDate().toString());
            pstmt.setString(12, employeeDTO.getEntYn());

            result = pstmt.executeUpdate();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con);
            close(pstmt);
        }

        if(result > 0){
            System.out.println("직원 등록에 성공하였습니다.");
        } else {
            System.out.println("직원 등록에 실패하였습니다.");
        }

    }


}
