package run;

import model.dto.EmployeeDTO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;
import java.util.Scanner;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;
public class updateEmployeeInfo {

    public static void main(String[] args) {

        Connection con = getConnection();
        EmployeeDTO empdto = null;
        PreparedStatement pstmt = null;
        int result = 0;

        Properties prop = new Properties();
        try {
            prop.loadFromXML(new FileInputStream("jdbc-practice-source/src/main/java/mapper/employee-query.xml"));
            String query = prop.getProperty("updateEmployee");

            Scanner sc = new Scanner(System.in);
            System.out.println("변경할 사원의 아이디를 입력하세요");
            String changeEmpid = sc.next();
            System.out.println("변경할 사원의 전화번호를 입력하세요");
            String changeEmpphone = sc.next();
            System.out.println("변경할 사원의 이메일을 입력하세요");
            String changeEmpemail = sc.next();
            System.out.println("변경할 사원의 부서코드를 입력하세요");
            String changeDeptno = sc.next();
            System.out.println("변경할 사원의 급여를 입력하세요");
            int changesalay = sc.nextInt();
            System.out.println("변경할 사원의 보너스를 입력하세요");
            Double changeBonus = sc.nextDouble();

            empdto = new EmployeeDTO();
            empdto.setEmpId(changeEmpid);
            empdto.setPhone(changeEmpphone);
            empdto.setEmail(changeEmpemail);
            empdto.setDeptCode(changeDeptno);
            empdto.setSalary(changesalay);
            empdto.setBonus(changeBonus);

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, empdto.getPhone());
            pstmt.setString(2, empdto.getEmail());
            pstmt.setString(3, empdto.getDeptCode());
            pstmt.setInt(4, empdto.getSalary());
            pstmt.setDouble(5, empdto.getBonus());
            pstmt.setString(6, empdto.getEmpId());

            result = pstmt.executeUpdate();

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
            close(con);
        }

        if (result > 0) {
            System.out.println("직원 정보 수정에 성공했습니다.");
        } else {
            System.out.println("직원 등록에 실패하여습니다. ");
        }
    }
    // 수정할 사원 번호를 입력받고
    // 사원 정보(전화번호, 이메일, 부서코드, 급여, 보너스)를 입력받아 DTO객체에 담아서 update
    // update 성공하면 "직원 정보 수정에 성공하였습니다." 출력
    // update 실패하면 "직원 정보 수정에 실패하였습니다." 출력

}
