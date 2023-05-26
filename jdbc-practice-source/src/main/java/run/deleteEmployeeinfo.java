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

public class deleteEmployeeinfo {
    // 삭제할 사원의 이름을 사용자로부터 입력받아 삭제
    // 이름이 정확히 일치해야 함
    // 사원 삭제에 성공하면 "[이름]님이 퇴출되었습니다." 출력
    // 사원 삭제에 실패하면 "[이름]님은 퇴출될 수 없습니다." 출력
    public static void main(String[] args) {
        Connection con = getConnection();

        Scanner sc = new Scanner(System.in);
        Properties prop = new Properties();
        EmployeeDTO emp = new EmployeeDTO();

        PreparedStatement pstmt = null;
        int result = 0;

        try {

            prop.loadFromXML(new FileInputStream("jdbc-practice-source/src/main/java/mapper/employee-query.xml"));
            String query = prop.getProperty("deleteEmp");

            System.out.print("퇴출할 사원의 이름을 입력해주세요 : ");
            emp.setEmpName(sc.nextLine());

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, emp.getEmpName());

            result = pstmt.executeUpdate();

        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con);
            close(pstmt);
        }

        if (result > 0) {
            System.out.println(emp.getEmpName() + "님이 퇴출되었습니다.");
        } else {
            System.out.println(emp.getEmpName() + "님은 퇴출될 수 없습니다");
        }
    }
}