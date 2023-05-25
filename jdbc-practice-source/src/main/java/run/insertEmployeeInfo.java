package run;

import model.dto.EmployeeDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Connection con = getConnection();

        PreparedStatement pstmt = null;
        int result = 0;
        EmployeeDTO employeeDTO = null;
        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("jdbc-practice-source/src/main/java/mapper/employee-query.xml"));

            String query = prop.getProperty("insertEmployee");


            Scanner sc = new Scanner(System.in);
            System.out.println("입사할 사원의 ID를 입력해 주세요 :");
            String empId = sc.nextLine();
            System.out.println("입사할 사원의 이름을 입력해 주세요 : ");
            String empName = sc.nextLine();
            System.out.println("사원 주민등록번호를 입력해 주세요 : ");
            String empNo = sc.nextLine();
            System.out.println("사원 이메일을 입력해 주세요 : ");
            String empEmail = sc.nextLine();
            System.out.println("사원 전화번호를 입력해 주세요 : ");
            String empPhone = sc.nextLine();
            System.out.println("사원 부서 코드를 입력해 주세요 : ");
            String empDeptCode = sc.nextLine();
            System.out.println("사원 일 코드를 입력해 주세요 : ");
            String empJobCode = sc.nextLine();
            System.out.println("사원 SAL_Level을 입력해 주세요 : ");
            String empSalLevel = sc.nextLine();
            System.out.println("사원 Salary를 입력해 주세요 : ");
            int salary = sc.nextInt();
            System.out.println("사원 Bonus를 입력해주세요 : ");
            Double empBonus = sc.nextDouble();
            System.out.println("사원 MANAGER_ID 를 입력해 주세요 : ");
            sc.nextLine();
            String empManagerId = sc.nextLine();
            System.out.println("사원 HIRE_DATE를 입력해 주세요");
            String empHireDate = sc.nextLine();
            System.out.println("사원 ENT_DATE를 입력해 주세요");
            String empEntDate = sc.nextLine();
            System.out.println("사원 ENT_YN를 입력해 주세요");
            String empEntYn = sc.nextLine();
            employeeDTO = new EmployeeDTO();
            employeeDTO.setEmpId(empId);
            employeeDTO.setEmpName(empName);
            employeeDTO.setEmpNo(empNo);
            employeeDTO.setEmail(empEmail);
            employeeDTO.setPhone(empPhone);
            employeeDTO.setDeptCode(empDeptCode);
            employeeDTO.setJobCode(empJobCode);
            employeeDTO.setSalLevel(empSalLevel);
            employeeDTO.setSalary(salary);
            employeeDTO.setBonus(empBonus);
            employeeDTO.setManagerId(empManagerId);
            java.sql.Date empHD = java.sql.Date.valueOf(empHireDate);
            employeeDTO.setHireDate(empHD);
            java.sql.Date empED = java.sql.Date.valueOf(empEntDate);
            employeeDTO.setEntDate(empED);
            employeeDTO.setEntYn(empEntYn);


            pstmt = con.prepareStatement(query);
            pstmt.setString(1, employeeDTO.getEmpId());
            pstmt.setString(2, employeeDTO.getEmpName());
            pstmt.setString(3, employeeDTO.getEmpNo());
            pstmt.setString(4, employeeDTO.getEmail());
            pstmt.setString(5, employeeDTO.getPhone());
            pstmt.setString(6, employeeDTO.getDeptCode());
            pstmt.setString(7, employeeDTO.getJobCode());
            pstmt.setString(8, employeeDTO.getSalLevel());
            pstmt.setInt(9, employeeDTO.getSalary());
            pstmt.setDouble(10, employeeDTO.getBonus());
            pstmt.setString(11, employeeDTO.getManagerId());
            pstmt.setDate(12, employeeDTO.getHireDate());
            pstmt.setDate(13, employeeDTO.getEntDate());
            pstmt.setString(14, employeeDTO.getEntYn());


            result = pstmt.executeUpdate();

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        } finally {
            close(con);
            close(pstmt);
        }
        if(result>0){
            System.out.println( "직원 등록에 성공하였습니다.");
        }else{
            System.out.println("직원 등록에 실패하였습니다.");
        }
    }

}
