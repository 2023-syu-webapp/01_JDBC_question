package run;

import model.dto.EmployeeDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

public class insertEmployeeInfo {

    // 사원 정보 전체를 입력받아 DTO객체에 담아서 insert
    // insert 성공하면 "직원 등록에 성공하였습니다." 출력
    // insert 실패하면 "직원 등록에 실패하였습니다." 출력

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

            // insertEmployee 쿼리문을 가져옴
            String query = prop.getProperty("insertEmployeeInfo");

            // 사용자로부터 값을 입력받기 위한 Scanner 객체 생성
            Scanner sc = new Scanner(System.in);
            System.out.print("사원의 ID를 입력하세요 : ");
            int emp_id = sc.nextInt();
            System.out.print("사원의 이름을 입력하세요 : ");
            sc.nextLine();
            String emp_name = sc.nextLine();
            System.out.print("사원의 번호를 입력하세요 : ");
            String emp_no = sc.nextLine();
            System.out.print("사원의 이메일을 입력하세요 : ");
            String email = sc.nextLine();
            System.out.print("사원의 전화번호를 입력하세요 : ");
            String phone = sc.nextLine();
            System.out.print("사원의 부서 번호를 입력하세요 : ");
            String dept_code = sc.nextLine();
            System.out.print("사원의 직급 번호을 입력하세요 : ");
            String job_code = sc.nextLine();
            System.out.print("사원의 급여 레벨을 입력하세요 : ");
            String sal_level = sc.nextLine();
            System.out.print("사원의 급여를 입력하세요 : ");
            int salary = sc.nextInt();
            System.out.print("사원의 보너스를 입력하세요 : ");
            double bonus = sc.nextInt();
            System.out.print("사원의 매니저 ID를 입력하세요 : ");
            int manager_id = sc.nextInt();
            System.out.print("사원의 입사일 입력하세요 (YYYY-MM-DD) : ");
            String hireDateStr = sc.next();
            java.sql.Date hire_date = Date.valueOf(hireDateStr);
            System.out.print("등록할 직원의 퇴사일을 입력하세요 (YYYY-MM-DD) : ");
            String entDateStr = sc.next();
            java.sql.Date ent_date = Date.valueOf(entDateStr);
            System.out.print("등록할 직원의 퇴직 여부을 입력하세요 : ");
            sc.nextLine();
            String ent_yn = sc.nextLine().toUpperCase();

            // DTO 객체 생성 및 값 설정
            EmployeeDTO employeeDTO = new EmployeeDTO();
            employeeDTO.setEmp_id(emp_id);
            employeeDTO.setEmp_name(emp_name);
            employeeDTO.setEmp_no(emp_no);
            employeeDTO.setEmail(email);
            employeeDTO.setPhone(phone);
            employeeDTO.setDept_code(dept_code);
            employeeDTO.setJob_code(job_code);
            employeeDTO.setSal_level(sal_level);
            employeeDTO.setSalary(salary);
            employeeDTO.setManager_id(manager_id);
            employeeDTO.setHire_date(hire_date);
            employeeDTO.setEnt_date(ent_date);
            employeeDTO.setEnt_yn(ent_yn);

            // PreparedStatement 객체 생성 및 값 설정
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, employeeDTO.getEmp_id());
            pstmt.setString(2, employeeDTO.getEmp_name());
            pstmt.setString(3, employeeDTO.getEmp_no());
            pstmt.setString(4, employeeDTO.getEmail());
            pstmt.setString(5, employeeDTO.getPhone());
            pstmt.setString(6, employeeDTO.getDept_code());
            pstmt.setString(7, employeeDTO.getJob_code());
            pstmt.setString(8, employeeDTO.getSal_level());
            pstmt.setInt(9, employeeDTO.getSalary());
            pstmt.setDouble(10, employeeDTO.getBonus());
            pstmt.setInt(11, employeeDTO.getManager_id());
            pstmt.setDate (12, employeeDTO.getHire_date());
            pstmt.setDate(13, employeeDTO.getEnt_date());
            pstmt.setString(14, employeeDTO.getEnt_yn());

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

        if (result > 0) {
            System.out.println("직원 등록에 성공하였습니다.");
        } else {
            System.out.println("직원 등록에 실패하였습니다.");
        }

    }

}
