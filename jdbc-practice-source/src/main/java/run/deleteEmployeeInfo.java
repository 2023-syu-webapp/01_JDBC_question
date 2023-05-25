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

    // 삭제할 사원의 이름을 사용자로부터 입력받아 삭제
    // 이름이 정확히 일치해야 함
    // 사원 삭제에 성공하면 "[이름]님이 퇴출되었습니다." 출력
    // 사원 삭제에 실패하면 "[이름]님은 퇴출될 수 없습니다." 출력

    public static void main(String[] args) {

        Connection con = getConnection();

        PreparedStatement pstmt = null;
        int result = 0;

        EmployeeDTO empDTO = null;

        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("jdbc-practice-source/src/main/java/mapper/employee-query.xml"));
            String query = prop.getProperty("deleteEmployee");

            empDTO = new EmployeeDTO();
            Scanner sc = new Scanner(System.in);
            System.out.print("삭제할 사원의 이름을 입력하세요 : ");
            String name = sc.next();

            empDTO.setEmpName(name);
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, empDTO.getEmpName());

            result = pstmt.executeUpdate();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con);
            close(pstmt);
        }

        if (result > 0){
            System.out.println("직원 삭제에 성공하였습니다.");
        } else System.out.println("직원 삭제에 실패하였습니다.");
    }
}