package run;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.util.Properties;
import java.util.Scanner;

import static common.JDBCTemplate.close;
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
            prop.loadFromXML(new FileInputStream("src/main/java/mapper/employee-query.xml"));
            String query = prop.getProperty("insertEmployee");

            Scanner sc = new Scanner(System.in);
            System.out.println("등록할 직원의 사원번호를 입력하세요 : ");
            String empCode = sc.nextLine();
            System.out.println("등록할 직원 이름을 입력하세요 : ");
            String empName = sc.nextLine();
            System.out.println("등록할 직원의 주민등록번호를 입력하세요 : ");
            String empNo = sc.nextLine();
            System.out.println("등록할 직원의 이메일을 입력하세요 : ");
            String empEmail = sc.nextLine();
            System.out.println("등록할 직원의 전화번호를 입력하세요 : ");
            String empphone = sc.nextLine();
            System.out.println("등록할 직원의 부서코드를 입력하세요 : ");
            String deptCode = sc.nextLine();
            System.out.println("등록할 직원의 직급코드를 입력하세요 : ");
            String empJobCode = sc.nextLine();
            System.out.println("등록할 직원의 급여동급를 입력하세요 : ");
            String empSal = sc.nextLine();
            System.out.println("등록할 직원의 급여를 입력하세요 : ");
            int sal = sc.nextInt();
            System.out.println("등록할 직원의 보너스율을 입력하세요 : ");
            double bonus = sc.nextDouble();
            System.out.println("등록할 직원의 관리자사번을 입력하세요 : ");
            sc.nextLine();
            String managerId = sc.nextLine();
            System.out.println("등록할 직원의 입사일 을 입력하세요 : ");
            String hireDate = sc.nextLine();
            Date hireday = Date.valueOf(hireDate);



            pstmt = con.prepareStatement(query);

            pstmt.setString(1,empCode);
            pstmt.setString(2,empName);
            pstmt.setString(3,empNo);
            pstmt.setString(4,empEmail);
            pstmt.setString(5,empphone);
            pstmt.setString(6,deptCode);
            pstmt.setString(7,empJobCode);
            pstmt.setString(8,empSal);
            pstmt.setInt(9,sal);
            pstmt.setDouble(10,bonus);
            pstmt.setString(11,managerId);
            pstmt.setDate(12,hireday);

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
            System.out.println("직원등록에 성공했습니다");
        }else {
            System.out.println("직원등록에 실패했습니다.");
        }
    }

}