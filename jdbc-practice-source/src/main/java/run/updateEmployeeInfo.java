package run;

import model.dto.EmployeeDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.close;

public class updateEmployeeInfo {

    // 수정할 사원 번호를 입력받고
    // 사원 정보(전화번호, 이메일, 부서코드, 급여, 보너스)를 입력받아 DTO객체에 담아서 update
    // update 성공하면 "직원 정보 수정에 성공하였습니다." 출력
    // update 실패하면 "직원 정보 수정에 실패하였습니다." 출력

    public static void main(String[] args) {
        Connection con = getConnection();

        PreparedStatement pstmt = null;
        int result = 0;

        Properties prop = new Properties();

        EmployeeDTO row = null;

        Scanner sc  = new Scanner(System.in);
        System.out.print("수정할 사원 번호를 입력하세요 : ");
        String empId = sc.nextLine();
        System.out.print("변경할 전화번호를 입력하세요 : ");
        String phone = sc.nextLine();
        System.out.print("변경할 이메일을 입력하세요 : ");
        String email = sc.nextLine();
        System.out.print("변경할 부서코드를 입력하세요 : ");
        String deptCode = sc.nextLine();
        System.out.print("변경할 급여를 입력하세요 : ");
        int salary = sc.nextInt();
        System.out.print("변경할 보너스를 입력하세요 : ");
        double bonus = sc.nextDouble();

        try {
            prop.loadFromXML(new FileInputStream("jdbc-practice-source/src/main/java/mapper/employee-query.xml"));
            String query = prop.getProperty("updateMenu");

            row = new EmployeeDTO();
            row.setEmpId(empId);
            row.setPhone(phone);
            row.setEmail(email);
            row.setDeptCode(deptCode);
            row.setSalary(salary);
            row.setBonus(bonus);

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, row.getPhone());
            pstmt.setString(2, row.getEmail());
            pstmt.setString(3, row.getDeptCode());
            pstmt.setInt(4, row.getSalary());
            pstmt.setDouble(5, row.getBonus());
            pstmt.setString(6, row.getEmpId());

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
            System.out.println("직원 정보 수정에 성공하였습니다.");
        }else {
            System.out.println("직원 정보 수정에 실패하였습니다.");
        }
    }
}
