package run;

import model.dto.EmployeeDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static common.JDBCTemplate.closes;
import static common.JDBCTemplate.getConnection;

public class insertEmployeeInfo {

    // 사원 정보 전체를 입력받아 DTO객체에 담아서 insert
    // insert 성공하면 "직원 등록에 성공하였습니다." 출력
    // insert 실패하면 "직원 등록에 실패하였습니다." 출력
    public static void main(String[] args) {
        Connection con = getConnection();

        PreparedStatement pstmt = null;
        int result = 0;

        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("C:\\java22\\01_JDBC_question\\jdbc-practice-source\\src\\main\\java\\mapper\\employee-query.xml"));

            String query = prop.getProperty("insert");

            Scanner sc = new Scanner(System.in);
            System.out.println("30이상을 입력하세요");
            String answer = sc.nextLine();
            System.out.println("22");
            String name = sc.nextLine();
            System.out.println("22");
            String name2 = sc.nextLine();
            System.out.println("22");
            String name3 = sc.nextLine();
            System.out.println("22");
            String name4 = sc.nextLine();
            System.out.println("22");
            String name5 = sc.nextLine();
            System.out.println("22");
            String name6 = sc.nextLine();
            System.out.println("22");
            String name7 = sc.nextLine();
            System.out.println("22");
            String name8 = sc.nextLine();
            System.out.println("22");
            String name9 = sc.nextLine();
            System.out.println("22");
            String name10 = sc.nextLine();
            System.out.println("22");
            String name11 = sc.nextLine();
            System.out.println("22");
            String name12 = sc.nextLine();
            System.out.println("22");
            String name13 = sc.nextLine();


            EmployeeDTO eDto = new EmployeeDTO();

            eDto.setEmpId(answer);

            pstmt = con.prepareStatement(query);

            pstmt.setString(1, answer);
            pstmt.setString(2, name);
            pstmt.setString(3, name2);
            pstmt.setString(4, name3);
            pstmt.setString(5, name4);
            pstmt.setString(6, name5);
            pstmt.setString(7, name6);
            pstmt.setString(8, name7);
            pstmt.setString(9, name8);
            pstmt.setString(10, name9);
            pstmt.setString(11, name10);
            pstmt.setString(12, name11);
            pstmt.setString(13, name12);
            pstmt.setString(14, name13);


            result = pstmt.executeUpdate();


        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closes(con);
            closes(pstmt);
        }
        if (result > 0){
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }
}
