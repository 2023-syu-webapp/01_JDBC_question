package run;

import model.dto.EmployeeDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

public class insertEmployeeInfo {

    public static void main(String[] args) {

        Connection con = getConnection();

        PreparedStatement pstmt = null;
        PreparedStatement pstmt2 = null;

        int result = 0;
        ResultSet result2 = null;

        Properties prop = new Properties();
        // String empName, String empNo, String email, String phone, String deptCode, String jobCode, String salLevel,
        // int salary, double bonus, String mamagerId, Date hireDate, Date entDate, String entYn

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/mapper/employee-query.xml"));

            String query = prop.getProperty("insertEmployee");

            System.out.println("query = " + query);

            pstmt = con.prepareStatement(query);

            Scanner sc = new Scanner(System.in);

            System.out.print("사원의 번호를 입력 하세요 : ");
            String empId = sc.nextLine();

            System.out.print("사원의 이름을 입력 하세요 : ");
            String empName = sc.nextLine();

            System.out.print("사원번호를 입력하세요 : ");
            String emp_no = sc.nextLine();

            System.out.print("이메일을 입력하세요 : ");
            String email = sc.nextLine();

            System.out.println("전화번호를 입력하세요 : ");
            String phone = sc.nextLine();

            System.out.println("부서 번호를 입력하세요 : ");
            String deptCode = sc.nextLine();

            System.out.println("직업 번호를 입력하세요 : ");
            String jobCode = sc.nextLine();

            System.out.println("판매 레벨을 입력해주세요 : ");
            String salLevel = sc.nextLine();

            System.out.println("연봉을 입력하세요.  :");
            int salary = sc.nextInt();

            System.out.println("보너스 율을 입력하세요. : ");
            double bonus = sc.nextDouble();

            System.out.println("관리 아이디를 입력하세요: ");
            int managerId = sc.nextInt();

            sc.nextLine();

            System.out.println("들어온 날짜를 입력하세요 : ");
            String hireDate = sc.nextLine();


            System.out.println("끝난 날짜를 입력하세요. : ");
            String entDate = sc.nextLine();

            System.out.println("퇴사자인지 아닌지를 입력해주세요 (예:Y, 아니오:N): ");
            String entYn = sc.nextLine().toUpperCase();

            EmployeeDTO employeeDTO = new EmployeeDTO();

            employeeDTO.setEmpId(empId);
            employeeDTO.setEmpName(empName);
            employeeDTO.setEmpNo(emp_no);
            employeeDTO.setEmail(email);
            employeeDTO.setPhone(phone);
            employeeDTO.setDeptCode(deptCode);
            employeeDTO.setJobCode(jobCode);
            employeeDTO.setSalLevel(salLevel);
            employeeDTO.setSalary(salary);
            employeeDTO.setBonus(bonus);
            employeeDTO.setManagerId(managerId);
            employeeDTO.setEntYn(entYn);

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
            pstmt.setInt(11, employeeDTO.getManagerId());
            pstmt.setString(12, hireDate);
            pstmt.setString(13, entDate);
            pstmt.setString(14, employeeDTO.getEntYn());

            result = pstmt.executeUpdate();


        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con);
            close(pstmt);
        }
        if (result > 0){
            System.out.println("직원 등록에 성공하였습니다.");
        } else {
            System.out.println("직원 등록에 실패하셨습니다.");
        }
    }


    // 사원 정보 전체를 입력받아 DTO객체에 담아서 insert
    // insert 성공하면 "직원 등록에 성공하였습니다." 출력
    // insert 실패하면 "직원 등록에 실패하였습니다." 출력

}
