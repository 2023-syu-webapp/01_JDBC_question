package run;

import model.dto.EmployeeDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

public class selectEmployeeInfo {
    public static void main(String[] args) {

        Connection con = getConnection();

        PreparedStatement ps = null;
        ResultSet rs = null;

        EmployeeDTO emp = new EmployeeDTO();



        Properties pp = new Properties();
        try {
            pp.loadFromXML(new FileInputStream("src/main/java/mapper/employee-query.xml"));

            String query = pp.getProperty("selectEmployeeInfo");
            ps = con.prepareStatement(query);

            Scanner sc = new Scanner(System.in);

            System.out.print("조회할 사원의 번호를 입력하세요 : ");
            emp.setEmpId(String.valueOf(sc.nextInt()));
            sc.nextLine();

            ps.setInt(1, Integer.parseInt(emp.getEmpId()));

            rs = ps.executeQuery();

            while (rs.next()){
                emp.setEmpName(rs.getString("EMP_NAME"));
                emp.setEmpNo(rs.getString("EMP_NO"));
                emp.setEmail(rs.getString("EMAIL"));
                emp.setPhone(rs.getString("PHONE"));
                emp.setDeptCode(rs.getString("DEPT_CODE"));
                emp.setJobCode(rs.getString("JOB_CODE"));
                emp.setSalLevel(rs.getString("SAL_LEVEL"));
                emp.setSalary(rs.getInt("SALARY"));
                emp.setBonus(rs.getDouble("BONUS"));
                emp.setManagerId(rs.getString("MANAGER_ID"));
                emp.setHireDate(rs.getDate("HIRE_DATE"));
                emp.setEntDate(rs.getDate("ENT_DATE"));
                emp.setEntYn(rs.getString("ENT_YN"));
            }

            System.out.println("["+emp.getEmpName()+"](["+emp.getDeptCode()+"]) ["+emp.getSalLevel()+"]님 환영합니다.");
            System.out.println(emp);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rs);
            close(ps);
            close(con);
        }
    }
}
