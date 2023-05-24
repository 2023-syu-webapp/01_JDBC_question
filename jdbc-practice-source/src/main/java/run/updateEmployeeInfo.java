package run;

import model.dto.EmployeeDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

public class updateEmployeeInfo {

    // 수정할 사원 번호를 입력받고
    // 사원 정보(전화번호, 이메일, 부서코드, 급여, 보너스)를 입력받아 DTO객체에 담아서 update
    // update 성공하면 "직원 정보 수정에 성공하였습니다." 출력
    // update 실패하면 "직원 정보 수정에 실패하였습니다." 출력

    public static void main(String[] args) {

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        int result =0;

        EmployeeDTO row = null;

        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/mapper/employee-query.xml"));
            String query = prop.getProperty("updateEmployee");

            Scanner sc = new Scanner(System.in);
            System.out.println("수정할 사원의 번호를 입력하시오 : ");
            String empNum = sc.nextLine();
            System.out.println("전화번호를 수정하시오 : ");
            String empPhone = sc.nextLine();
            System.out.println("이메일을 수정하시오 : ");
            String empEmail = sc.nextLine();
            System.out.println("부서코드를 수정하시오 : ");
            String deptCode = sc.nextLine();
            System.out.println("급여를 수정하시오 : ");
            int empsal = sc.nextInt();
            System.out.println("보너스를 수정하시오 : ");
            double empbonus = sc.nextDouble();
            sc.nextLine();

            row = new EmployeeDTO();
            row.setPhone(empPhone);
            row.setEmail(empEmail);
            row.setDeptCode(deptCode);
            row.setSalary(empsal);
            row.setBonus(empbonus);
            row.setEmpId(empNum);

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, row.getPhone());
            pstmt.setString(2,row.getEmail());
            pstmt.setString(3, row.getDeptCode());
            pstmt.setInt(4,row.getSalary());
            pstmt.setDouble(5,row.getBonus());
            pstmt.setString(6,row.getEmpId());


            result = pstmt.executeUpdate();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(con);
            close(pstmt);
        }

        if(result>0){
            System.out.println("직원 정보수정에 성공했습니다.");
        }else {
            System.out.println("직원 정보수정에 실패했습니다.");
        }

    }

}