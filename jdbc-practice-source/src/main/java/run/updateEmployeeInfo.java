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

        PreparedStatement ps = null;
        int result = 0;

        EmployeeDTO emp = new EmployeeDTO();

        Properties pp = new Properties();
        try {
            pp.loadFromXML(new FileInputStream("src/main/java/mapper/employee-query.xml"));

            String query = pp.getProperty("updateEmployeeInfo");
            ps = con.prepareStatement(query);

            Scanner sc = new Scanner(System.in);

            System.out.print("수정할 사원의 번호를 입력하세요 : ");
            emp.setEmpId(String.valueOf(sc.nextInt()));
            sc.nextLine();
            System.out.print("수정할 사원의 휴대전화 번호를 입력하세요 : ");
            emp.setPhone(String.valueOf(sc.nextInt()));
            sc.nextLine();
            System.out.print("수정할 사원의 이메일을 입력하세요 : ");
            emp.setEmail(sc.nextLine());
            System.out.print("수정할 사원의 부서 코드를 입력하세요 : ");
            emp.setDeptCode(sc.nextLine());
            System.out.print("수정할 사원의 연봉을 입력하세요 : ");
            emp.setSalary(sc.nextInt());
            sc.nextLine();
            System.out.print("수정할 사원의 보너스를 입력하세요 : ");
            emp.setBonus(sc.nextDouble());
            sc.nextLine();


            ps.setInt(1, Integer.parseInt(emp.getPhone()));
            ps.setString(2, emp.getEmail());
            ps.setString(3, emp.getDeptCode());
            ps.setInt(4, emp.getSalary());
            ps.setDouble(5, emp.getBonus());
            ps.setInt(6, Integer.parseInt(emp.getEmpId()));

            result = ps.executeUpdate();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
           e.printStackTrace();
        } finally {
            close(ps);
            close(con);
        }
        if(result > 0){
            System.out.println("직원 정보 수정에 성공하였습니다.");
        } else {
            System.out.println("직원 정보 수정에 실패하였습니다.");
        }
    }
}
