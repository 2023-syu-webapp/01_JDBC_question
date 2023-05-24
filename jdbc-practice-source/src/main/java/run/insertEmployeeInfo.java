package run;

import model.dto.EmployeeDTO;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;
import java.sql.Date;

public class insertEmployeeInfo {

    public static void main(String[] args) throws FileNotFoundException {

        Connection con = getConnection();

        PreparedStatement pstmt = null;

        int result = 0;

        Properties prop = new Properties();

        try {
            EmployeeDTO employeeDTO = new EmployeeDTO();

            prop.loadFromXML(new FileInputStream("jdbc-practice-source/src/main/java/mapper/employee-query.xml"));
            String query = prop.getProperty("insertMenu");

            Scanner sc = new Scanner(System.in);
            System.out.print("사원번호를 입력해주세요 : ");
            int empId = sc.nextInt();

            System.out.print("사원의 이름을 입력해주세요 : ");
            String empName = sc.nextLine();

            System.out.print("사원의 주민등록번호를 입력해주세요 : ");
            int empNo = sc.nextInt();

            System.out.print("사원의 이메일을 입력해주세요 : ");
            String empEmail = sc.nextLine();

            System.out.print("사원의 전화번호를 입력해주세요 : ");
            int empPhone = sc.nextInt();

            System.out.print("사원의 부서코드를 입력해주세요 : ");
            String empDeptCode = sc.nextLine();

            System.out.print("사원의 직급코드를 입력해주세요 : ");
            int empJobCode = sc.nextInt();

            System.out.print("사원의 급여등급을 입력해주세요 : ");
            String empSalLevel = sc.nextLine();

            System.out.print("사원의 급여를 입력해주세요 : ");
            int empSalary = sc.nextInt();
            sc.nextLine();

            System.out.print("사원의 보너스를 입력해주세요 : ");
            float empBonus = sc.nextFloat();
            sc.nextLine();

            System.out.print("사원의 관리자사번을 입력해주세요 : ");
            int empManagerId = sc.nextInt();

            System.out.print("사원의 입사일을 입력해주세요 : ");
            Date empHireDate = Date.valueOf(sc.nextLine());

            System.out.print("사원의 퇴사여부 입력해주세요(Y/N) : ");
            String empEntYn = sc.nextLine();

            employeeDTO.setEmpId(empId);
            employeeDTO.setEmpName(empName);
            employeeDTO.setEmpNo(empNo);
            employeeDTO.setEmail(empEmail);
            employeeDTO.setPhone(empPhone);
            employeeDTO.setDeptCode(empDeptCode);
            employeeDTO.setJobCode(empJobCode);
            employeeDTO.setSalLevel(empSalLevel);
            employeeDTO.setSalary(empSalary);
            employeeDTO.setBonus(empBonus);
            employeeDTO.setManagerId(empManagerId);
            employeeDTO.setHireDate(empHireDate);
            employeeDTO.setEntYn(empEntYn);

            pstmt = con.prepareStatement(query);

            pstmt.setInt(1, employeeDTO.getEmpId());
            pstmt.setString(2, employeeDTO.getEmpName());
            pstmt.setInt(3, employeeDTO.getEmpNo());
            pstmt.setString(4, employeeDTO.getEmail());
            pstmt.setInt(5, employeeDTO.getPhone());
            pstmt.setString(6, employeeDTO.getDeptCode());
            pstmt.setInt(7, employeeDTO.getJobCode());
            pstmt.setString(8, employeeDTO.getSalLevel());
            pstmt.setFloat(9, employeeDTO.getSalary());
            pstmt.setDouble(10, employeeDTO.getBonus());
            pstmt.setInt(11, employeeDTO.getManagerId());
            pstmt.setString(12, String.valueOf(employeeDTO.getHireDate()));
            pstmt.setString(13, employeeDTO.getEntYn());
            result = pstmt.executeUpdate();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(con);
            close(pstmt);
        }

        if (result > 0) {
            System.out.println("직원등록을 성공했습니다.");
        } else {
            System.out.println("직원등록을 실패했습니다.");
        }
    }



    // 사원 정보 전체를 입력받아 DTO객체에 담아서 insert
    // insert 성공하면 "직원 등록에 성공하였습니다." 출력
    // insert 실패하면 "직원 등록에 실패하였습니다." 출력

}
