package run;

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

public class deleteEmployeeInfo {

    public static void main(String[] args) {

        Connection con = getConnection();

        PreparedStatement pstmt = null;
        int result = 0;
        Properties prop = new Properties();

        String employeeName = "";
        System.out.println(employeeName);

        try {
            prop.loadFromXML(new FileInputStream("jdbc-practice-source/src/main/java/mapper/employee-query.xml"));
            String query = prop.getProperty("deleteEmployee");

            Scanner sc = new Scanner(System.in);
            System.out.println("삭제할 사원의 이름을 입력하세요");
            employeeName = sc.next();

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, employeeName);

            result = pstmt.executeUpdate();

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        } finally {
            close(con);
            close(pstmt);
        }



        if (result > 0) {
            System.out.println( "[" + employeeName + "]님이 퇴출되었습니다.");
        } else {
            System.out.println( "[" + employeeName + "]님이 퇴출될 수 없습니다.");
        }
    }
}
