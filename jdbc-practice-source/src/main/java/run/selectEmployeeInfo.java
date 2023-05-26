package run;

import model.dto.EmployeeDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

public class selectEmployeeInfo {

    // 사원 번호를 입력받아 해당 사원을 조회하고 DTO객체에 담아서 출력
    // 출력 구문은 DTO 객체의 toString() 내용과
    // "[이름]([부서명]) [직급명]님 환영합니다." 로 출력.

    public static void main(String[] args) {

        // 데이터베이스 연결을 위한 Connection 객체 생성
        Connection con = getConnection();

        // PreparedStatement와 reeultSet 선언
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        // 프로퍼티 파일을 읽기 위한 Properties 객체 생성
        Properties prop = new Properties();

        // DTO 객체 생성 및 값 설정
        EmployeeDTO employeeDTO = new EmployeeDTO();
        String emp_Name="";



        try {
            // employee-query.xml 파일을 읽어서 Properties 객체에 로드
            prop.loadFromXML(new FileInputStream("src/main/java/mapper/employee-query.xml"));
            // selectEmployeeInfo 쿼리문을 가져옴
            String query = prop.getProperty("selectEmployeeInfo");

            // 사용자로부터 값을 입력받기 위한 Scanner 객체 생성
            Scanner sc = new Scanner(System.in);
            System.out.print("조회할 사원의 사번을 입력하세요 : ");
            int emp_id = sc.nextInt();

            //전화번호, 이메일, 부서코드, 급여, 보너스
            // PreparedStatement 객체 생성 및 값 설정
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, employeeDTO.getEmp_id());
            rset = pstmt.executeQuery();

            while (rset.next()) {
                employeeDTO.setEmp_id(rset.getInt("EMP_ID"));
                employeeDTO.setEmp_name(rset.getString("EMP_NAME"));
                employeeDTO.setEmp_no(rset.getString("EMP_NO"));
                employeeDTO.setEmail(rset.getString("EMAIL"));
                employeeDTO.setPhone(rset.getString("PHONE"));
                employeeDTO.setDept_code(rset.getString("DEPT_CODE"));
                employeeDTO.setJob_code(rset.getString("JOB_CODE"));
                employeeDTO.setSalary(rset.getInt("SALARY"));
                employeeDTO.setSal_level(rset.getString("SAL_LEVEL"));
                employeeDTO.setBonus(rset.getDouble("BONUS"));
                employeeDTO.setManager_id(rset.getInt("MANAGER_ID"));
                employeeDTO.setHire_date(rset.getDate("HIRE_DATE"));
                employeeDTO.setEnt_date(rset.getDate("ENT_DATE"));
                employeeDTO.setEnt_yn(rset.getString("ENT_YN"));
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
            close(con);
            close(rset);
        }
        System.out.println("[" + employeeDTO.getEmp_name() + "](" + employeeDTO.getDept_title() + ") " + employeeDTO.getJob_name() + "님 환영합니다.");
    }

}



