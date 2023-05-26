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

public class deleteEmployeeInfo {

public static void main(String[] args) {

        Connection con = getConnection();

        PreparedStatement ps = null;
        int result = 0;

        EmployeeDTO ed = new EmployeeDTO();

        Properties pp = new Properties();
        try {
        pp.loadFromXML(new FileInputStream("src/main/java/mapper/employee-query.xml"));

        String query = pp.getProperty("deleteEmployeeInfo");
        ps = con.prepareStatement(query);

        Scanner sc = new Scanner(System.in);
        System.out.print("삭제할 사원의 이름을 입력하세요 : ");
        ed.setEmpName(sc.nextLine());

        ps.setString(1, ed.getEmpName());

        result = ps.executeUpdate();
        } catch (IOException e) {
        e.printStackTrace();
        } catch (SQLException e) {
        throw new RuntimeException(e);
        } finally {
        close(ps);
        close(con);
        }
        if(result > 0){
        System.out.println("["+ed.getEmpName()+"]님은 퇴출되었습니다.");
        } else {
        System.out.println("["+ed.getEmpName()+"]님은 퇴출될 수 없습니다.");
        }
        }

        }
