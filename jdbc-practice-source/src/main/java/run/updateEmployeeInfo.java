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



    public static void main(String[] args) {

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        int result = 0;
        EmployeeDTO employeeDTO = null;
        Properties prop = new Properties();
        try {
            prop.loadFromXML(new FileInputStream("jdbc-practice-source/src/main/java/mapper/employee-query.xml"));

            String query = prop.getProperty("updateEmployee");

            // 수정할 사원 번호를 입력받고
            Scanner sc = new Scanner(System.in);
            System.out.println("수정할 사원의 ID를 입력해 주세요 :");
            String empId = sc.nextLine();
            System.out.println("수정할 사원의 이메일을 입력해 주세요 :");
            String empEmail = sc.nextLine();
            System.out.println("수정할 사원의 부서코드를 입력해 주세요 :");
            String empDeptCode = sc.nextLine();
            System.out.println("수정할 사원의 급여를 입력해 주세요 :");
            int empSalary = sc.nextInt();
            System.out.println("수정할 사원의 보너스를 입력해 주세요 :");
            Double empBonus = sc.nextDouble();

            // 사원 정보(전화번호, 이메일, 부서코드, 급여, 보너스)를 입력받아 DTO객체에 담아서 update
            employeeDTO = new EmployeeDTO();
            employeeDTO.setEmpId(empId);
            employeeDTO.setEmail(empEmail);
            employeeDTO.setDeptCode(empDeptCode);
            employeeDTO.setSalary(empSalary);
            employeeDTO.setBonus(empBonus);
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, employeeDTO.getEmail());
            pstmt.setString(2, employeeDTO.getDeptCode());
            pstmt.setInt(3, employeeDTO.getSalary());
            pstmt.setDouble(4, employeeDTO.getBonus());
            pstmt.setString(5, employeeDTO.getEmpId());
            result = pstmt.executeUpdate();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con);
            close(pstmt);
        }
        // update 성공하면 "직원 정보 수정에 성공하였습니다." 출력
        // update 실패하면 "직원 정보 수정에 실패하였습니다." 출력
        if(result >0){
            System.out.println("직원 정보 수정에 성공하였습니다.");
        } else{
            System.out.println("직원 정보 수정에 실패하였습니다.");
        }


    }
}
