package main.java.run;

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

        Connection con = getConnection();

        PreparedStatement pre = null;
        int result = 0;

        EmployeeDTO emp = new EmployeeDTO();

        Properties prop = new Properties();
        try {
            prop.loadFromXML(new FileInputStream("src/main/java/mapper/employee-query.xml"));

            String query = prop.getProperty("insertEmployeeInfo");
            pre = con.prepareStatement(query);

            Scanner sc = new Scanner(System.in);

            System.out.print("등록할 사원의 번호를 입력하세요 : ");
            emp.setEmpId(String.valueOf(sc.nextInt()));
            sc.nextLine();
            System.out.print("등록할 사원의 이름을 입력하세요 : ");
            emp.setEmpName(sc.nextLine());
            System.out.print("등록할 사원의 주민등록번호를 입력하세요 : ");
            emp.setEmpNo(sc.nextLine());
            System.out.print("등록할 사원의 이메일을 입력하세요 : ");
            emp.setEmail(sc.nextLine());
            System.out.print("등록할 사원의 휴대전화 번호를 입력하세요 : ");
            emp.setPhone(String.valueOf(sc.nextInt()));
            sc.nextLine();
            System.out.print("등록할 사원의 부서코드를 입력하세요 : ");
            emp.setDeptCode(sc.nextLine());
            System.out.print("등록할 사원의 직업코드를 입력하세요 : ");
            emp.setJobCode(sc.nextLine());
            System.out.print("등록할 사원의 직급을 입력하세요 : ");
            emp.setSalLevel(sc.nextLine());
            System.out.print("등록할 사원의 연봉을 입력하세요 : ");
            emp.setSalary(sc.nextInt());
            sc.nextLine();
            System.out.print("등록할 사원의 보너스를 입력하세요 : ");
            emp.setBonus(sc.nextDouble());
            sc.nextLine();
            System.out.print("등록할 사원의 상사 번호를 입력하세요 : ");
            emp.setManagerId(String.valueOf(sc.nextInt()));
            sc.nextLine();
            System.out.print("등록할 사원의 채용일을 입력하세요 : ");
            emp.setHireDate(Date.valueOf(sc.nextLine()));
            System.out.print("등록할 사원의 퇴사일을 입력하세요 : ");
            emp.setEntDate(Date.valueOf(sc.nextLine()));
            System.out.print("등록할 사원의 퇴사여부를 입력하세요 : (Y/N)");
            emp.setEntYn(sc.nextLine().toUpperCase());

            pre.setInt(1, Integer.parseInt(emp.getEmpId()));
            pre.setString(2, emp.getEmpName());
            pre.setString(3, emp.getEmpNo());
            pre.setString(4, emp.getEmail());
            pre.setInt(5, Integer.parseInt(emp.getPhone()));
            pre.setString(6, emp.getDeptCode());
            pre.setString(7, emp.getJobCode());
            pre.setString(8, emp.getSalLevel());
            pre.setInt(9, emp.getSalary());
            pre.setDouble(10, emp.getBonus());
            pre.setInt(11, Integer.parseInt(emp.getManagerId()));
            pre.setDate(12, emp.getHireDate());
            pre.setDate(13, emp.getEntDate());
            pre.setString(14, emp.getEntYn());


            result = pre.executeUpdate();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pre);
            close(con);
        }
        if(result > 0){
            System.out.println("직원 등록에 성공하였습니다.");
        } else {
            System.out.println("직원 등록에 실패하였습니다.");
        }
    }
}