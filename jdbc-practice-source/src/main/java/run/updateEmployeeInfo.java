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

public class updateEmployeeInfo {

    // 수정할 사원 번호를 입력받고
    // 사원 정보(전화번호, 이메일, 부서코드, 급여, 보너스)를 입력받아 DTO객체에 담아서 update
    // update 성공하면 "직원 정보 수정에 성공하였습니다." 출력
    // update 실패하면 "직원 정보 수정에 실패하였습니다." 출력

    public static void main(String[] args) {

        // 데이터베이스 연결을 위한 Connection 객체 생성
        Connection con = getConnection();

        // PreparedStatement와 결과 변수 선언
        PreparedStatement pstmt = null;
        int result = 0;

        // 프로퍼티 파일을 읽기 위한 Properties 객체 생성
        Properties prop = new Properties();

        try {
            // employee-query.xml 파일을 읽어서 Properties 객체에 로드
            prop.loadFromXML(new FileInputStream("src/main/java/mapper/employee-query.xml"));
            // updateEmployeeInfo 쿼리문을 가져옴
            String query = prop.getProperty("updateEmployeeInfo");

            // 사용자로부터 값을 입력받기 위한 Scanner 객체 생성
            Scanner sc = new Scanner(System.in);
            System.out.print("변경할 사원의 사번을 입력하세요 : ");
            int emp_id = sc.nextInt();
            System.out.print("변경할 사원의 전화번호를 입력하세요 : ");
            String phone = sc.nextLine();
            sc.nextLine();
            System.out.print("변경할 사원의 이메일을 입력하세요 : ");
            String email = sc.nextLine();
            System.out.print("변경할 사원의 부서코드를 입력하세요 : ");
            String dept_code = sc.nextLine();
            System.out.print("변경할 사원의 급여를 입력하세요 : ");
            int salary = sc.nextInt();
            System.out.print("변경할 사원의 보너스를 입력하세요 : ");
            double bonus = sc.nextDouble();

            // DTO 객체 생성 및 값 설정
            EmployeeDTO employeeDTO = new EmployeeDTO();
            employeeDTO.setEmp_id(emp_id);
            employeeDTO.setPhone(phone);
            employeeDTO.setEmail(email);
            employeeDTO.setDept_code(dept_code);
            employeeDTO.setSalary(salary);
            employeeDTO.setBonus(bonus);

            //전화번호, 이메일, 부서코드, 급여, 보너스
            // PreparedStatement 객체 생성 및 값 설정
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, employeeDTO.getEmp_id());
            pstmt.setString(2, employeeDTO.getPhone());
            pstmt.setString(3, employeeDTO.getEmail());
            pstmt.setString(4, employeeDTO.getDept_code());
            pstmt.setInt(5, employeeDTO.getSalary());
            pstmt.setDouble(6, employeeDTO.getBonus());

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
            System.out.println("직원 정보 수정에 성공하였습니다.");
        } else {
            System.out.println("직원 정보 수정에 실패하였습니다.");
        }

    }

}

