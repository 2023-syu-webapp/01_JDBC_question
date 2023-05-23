package run;

import model.dto.EmployeeDTO;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Properties;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.close;

public class updateEmployeeInfo {
    public static void main(String[] args) {
        Connection con = getConnection();

        PreparedStatement pstmt = null;
        int result = 0;
        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/mapper/employee-query.xml"));
            String query = prop.getProperty("updateEmp");

            Scanner sc = new Scanner(System.in);
            System.out.println("수정할 사원 번호를 입력하세요: ");
            String empId = sc.nextLine();

            EmployeeDTO employeeDTO = new EmployeeDTO();
            employeeDTO.setEmpNo(empId);

            System.out.println("수정할 전화번호를 입력하세요: ");
            String phone = sc.nextLine();
            System.out.println("수정할 이메일을 입력하세요: ");
            String email = sc.nextLine();
            System.out.println("수정할 부서코드를 입력하세요: ");
            String deptCode = sc.nextLine();
            System.out.println("수정할 급여를 입력하세요: ");
            int salary = sc.nextInt();
            System.out.println("수정할 보너스를 입력하세요: ");
            double bonus = sc.nextDouble();

            employeeDTO.setPhone(phone);
            employeeDTO.setEmail(email);
            employeeDTO.setDeptCode(deptCode);
            employeeDTO.setSalary(salary);
            employeeDTO.setBonus(bonus);

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, employeeDTO.getPhone());
            pstmt.setString(2, employeeDTO.getEmail());
            pstmt.setString(3, employeeDTO.getDeptCode());
            pstmt.setInt(4, employeeDTO.getSalary());
            pstmt.setDouble(5, employeeDTO.getBonus());
            pstmt.setString(6, employeeDTO.getEmpNo());

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
