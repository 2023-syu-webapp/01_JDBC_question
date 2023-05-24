package run;

import model.dto.EmployeeDTO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

        Properties prop = new Properties();

        EmployeeDTO row = null;

        Scanner sc = new Scanner(System.in);
        System.out.print("삭제할 사원의 이름을 입력하세요 : ");
        String name = sc.nextLine();

        try {
            prop.loadFromXML(new FileInputStream("jdbc-practice-source/src/main/java/mapper/employee-query.xml"));
            String query = prop.getProperty("deleteMenu");

            row = new EmployeeDTO();
            row.setEmpName(name);

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, row.getEmpName());

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
            System.out.printf("[%s]님이 퇴출되었습니다.", name);
        }else {
            System.out.printf("[%s]님은 퇴출될 수 없습니다.", name);
        }
    }
}
