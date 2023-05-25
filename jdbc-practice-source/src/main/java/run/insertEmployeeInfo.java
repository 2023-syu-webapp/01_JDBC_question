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

        Connection con = getConnection();

        PreparedStatement pstmt = null;

        int result = 0;

        EmployeeDTO row = null;

        Properties prop = new Properties();
        try {
            prop.loadFromXML(new FileInputStream("jdbc-practice-source/src/main/java/mapper/employee-query.xml"));
            String query = prop.getProperty("insertEmp");
            pstmt = con.prepareStatement(query);
            Scanner sc = new Scanner(System.in);

            System.out.print("사번을 입력해주세요 : ");
            String empId = sc.nextLine();
            System.out.print("직원명을 입력해주세요 : ");
            String empName = sc.nextLine();
            System.out.print("주민번호를 입력해주세요 : ");
            String empNo = sc.nextLine();
            System.out.print("이메일을 입력해주세요 : ");
            String email = sc.nextLine();
            System.out.print("전화번호를 입력해주세요 : ");
            String phone = sc.nextLine();
            System.out.print("부서코드를 입력해주세요 : ");
            String deptCode = sc.nextLine();
            System.out.print("직급코드를 입력해주세요 : ");
            String jobCode = sc.nextLine();
            System.out.print("급여등급 입력해주세요 : ");
            String salLevel = sc.nextLine();
            System.out.print("급여를 입력해주세요 : ");
            int salary = sc.nextInt();
            System.out.print("보너스율을 입력해주세요 : ");
            double bonus = sc.nextDouble();
            System.out.print("관리자사번을 입력해주세요 : ");
            sc.nextLine();
            String managerId = sc.nextLine();
            System.out.print("입사일을 입력해주세요 : ");
            java.sql.Date hireDate = new Date(1998,01,05);
            System.out.print("퇴사일 입력해주세요 : ");
            java.sql.Date entDate = new Date(1998,01,05);
            System.out.print("퇴직여부를 입력해주세요 : ");
            String entYn = sc.nextLine();

            row = new EmployeeDTO();

            row.setEmpId(empId);
            row.setEmpName(empName);
            row.setEmpNo(empNo);
            row.setEmail(email);
            row.setPhone(phone);
            row.setDeptCode(deptCode);
            row.setJobCode(jobCode);
            row.setSalLevel(salLevel);
            row.setSalary(salary);
            row.setManagerId(managerId);
            row.setHireDate(hireDate);
            row.setEntDate(entDate);
            row.setEntYn(entYn);

            pstmt.setString(1,row.getEmpId());
            pstmt.setString(2,row.getEmpName());
            pstmt.setString(3,row.getEmpNo());
            pstmt.setString(4,row.getEmail());
            pstmt.setString(5,row.getPhone());
            pstmt.setString(6,row.getDeptCode());
            pstmt.setString(7,row.getJobCode());
            pstmt.setString(8,row.getSalLevel());
            pstmt.setInt(9,row.getSalary());
            pstmt.setDouble(10,row.getBonus());
            pstmt.setString(11,row.getManagerId());
            pstmt.setDate(12,row.getHireDate());
            pstmt.setDate(13,row.getEntDate());
            pstmt.setString(14,row.getEntYn());

        result = pstmt.executeUpdate();




        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con);
            close(pstmt);
        }

        if(result>0){
            System.out.println("직원등록에 성공했습니다");
        }else{
            System.out.println("직원등록에 실패했습니다.");
        }
    }

}
