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

        // 데이터베이스 연결을 위한 Connection 객체 생성
        Connection con = getConnection();

        // PreparedStatement와 결과 변수 선언
        PreparedStatement pstmt = null;
        int result = 0;

        // 프로퍼티 파일을 읽기 위한 Properties 객체 생성
        Properties prop = new Properties();
        String emp_Name="";

        try {
            // employee-query.xml 파일을 읽어서 Properties 객체에 로드
            prop.loadFromXML(new FileInputStream("src/main/java/mapper/employee-query.xml"));

            // deleteEmployee 쿼리문을 가져옴
            String query = prop.getProperty("deleteEmployeeInfo");

            // 사용자로부터 입력 받기 위한 Scanner 객체 생성
            Scanner sc = new Scanner(System.in);
            System.out.print("삭제할 사원 이름을 입력하세요 : ");
            emp_Name = sc.nextLine();

            //DTO 객체 생성 및 값 설정
            EmployeeDTO employeeDTO = new EmployeeDTO();
            employeeDTO.setEmp_name(emp_Name);

            // PrepraredStatement 객체 생성 및 값 전달
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, employeeDTO.getEmp_name());

            // 쿼리 실행 및 결과 반환
            result = pstmt.executeUpdate();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
            close(con);
        }

        if(result > 0) {
            System.out.println("[" + emp_Name + "]님이 퇴출되었습니다.");
        } else {
            System.out.println("[" + emp_Name +  "]님은 퇴출될 수 없습니다.");
        }

    }

}
