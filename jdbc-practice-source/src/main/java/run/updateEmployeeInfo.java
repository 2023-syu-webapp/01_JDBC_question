package run;

import model.dto.EmployeeDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static common.JDBCTemplate.closes;
import static common.JDBCTemplate.getConnection;

public class updateEmployeeInfo {

    // 수정할 사원 번호를 입력받고
    // 사원 정보(전화번호, 이메일, 부서코드, 급여, 보너스)를 입력받아 DTO객체에 담아서 update
    // update 성공하면 "직원 정보 수정에 성공하였습니다." 출력
    // update 실패하면 "직원 정보 수정에 실패하였습니다." 출력

    public static void main(String[] args) {
        Connection con = getConnection();
        Properties prop = new Properties();
        PreparedStatement pstmt = null;
        int result = 0;

        try {
            prop.loadFromXML(new FileInputStream("C:\\java22\\01_JDBC_question\\jdbc-practice-source\\src\\main\\java\\mapper\\employee-query.xml"));
            String query = prop.getProperty("update");

            Scanner sc = new Scanner(System.in);
            System.out.print("수정 할 사원의 사원 번호를 입력 해 주세요 : ");
            String num = sc.nextLine();
            System.out.print("수정 후 전화번호를 입력 해 주세요 : ");
            String phone = sc.nextLine();
            System.out.print("수정 후 이메일을 입력 해 주세요 : ");
            String email = sc.nextLine();
            System.out.print("수정 후 부서코드를 입력 해 주세요 : ");
            String dept = sc.nextLine();
            System.out.print("수정 후 급여를 입력 해 주세요 : ");
            int salary = sc.nextInt();
            System.out.print("수정 후 보너스를 입력 해 주세요 : ");
            sc.nextLine();
            double bonus = sc.nextDouble();

            EmployeeDTO employeeDTO = new EmployeeDTO();
            employeeDTO.setEmpId(num);
            employeeDTO.setPhone(phone);
            employeeDTO.setEmail(email);
            employeeDTO.setDepCode(dept);
            employeeDTO.setSalary(salary);
            employeeDTO.setBonus(bonus);

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, employeeDTO.getPhone());
            pstmt.setString(2, employeeDTO.getEmail());
            pstmt.setString(3, employeeDTO.getDepCode());
            pstmt.setInt(4, employeeDTO.getSalary());
            pstmt.setDouble(5, employeeDTO.getBonus());
            pstmt.setString(6, employeeDTO.getEmpId());
            result = pstmt.executeUpdate();


        } catch (IOException e) {
            e.printStackTrace();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closes(con);
            closes(pstmt);
        }
        if(result > 0){
            System.out.println(1);
        } else {
            System.out.println(2);
        }


    }
}
