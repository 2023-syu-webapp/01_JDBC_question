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
        EmployeeDTO deleteEmp = null;

        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("jdbc-practice-source/src/main/java/mapper/employee-query.xml"));
            String query = prop.getProperty("deleteEmp");

            deleteEmp = new EmployeeDTO();
            Scanner sc = new Scanner(System.in);
            System.out.print("삭제할 사원의 이름을 입력하세요 : ");
            deleteEmp.setEmpName(sc.nextLine());

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, deleteEmp.getEmpName());

            result = pstmt.executeUpdate();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        } finally {
            close(con);
            close(pstmt);
        }

        if (result > 0) {
            System.out.println(deleteEmp.getEmpName() + "님이 퇴출되었습니다.");
        } else {
            if (deleteEmp != null) {
                System.out.println(deleteEmp.getEmpName() + "님은 퇴출될 수 없습니다.");
            }
        }
    }
}
