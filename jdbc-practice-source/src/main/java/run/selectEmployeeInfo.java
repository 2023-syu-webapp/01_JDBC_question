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
import java.sql.ResultSet;


public class selectEmployeeInfo {
        public static void main(String[] args) throws FileNotFoundException {

            Connection con = getConnection();

            PreparedStatement pstmt = null;

            int result = 0;

            Properties prop = new Properties();

            EmployeeDTO employeeDTO = null;

            ResultSet rset = null;

            try {
                prop.loadFromXML(new FileInputStream("src/main/java/mapper/employee-query.xml"));
                String query = prop.getProperty("selectEmployee");

                Scanner sc = new Scanner(System.in);
                System.out.print("조회할 사번을 입력해주세요. : ");
                int empId = sc.nextInt();

                pstmt = con.prepareStatement(query);
                pstmt.setInt(1, empId);
                rset = pstmt.executeQuery();

                if(rset.next()) {
                    employeeDTO = new EmployeeDTO();
                    employeeDTO.setEmpName(rset.getString("EMP_NAME"));
                    employeeDTO.setJobCode(rset.getInt("JOB_NAME"));
                    employeeDTO.setJobCode(rset.getInt("DEPT_CODE"));

                    System.out.println("[" + employeeDTO.getEmpName() + "]([" + employeeDTO.getDeptCode() +  "]) ["
                            + employeeDTO.getJobCode() + "]님 환영합니다." );
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                close(con);
                close(pstmt);
                close(rset);
            }

            }
}

    // 사원 번호를 입력받아 해당 사원을 조회하고 DTO객체에 담아서 출력
    // 출력 구문은 DTO 객체의 toString() 내용과
    // "[이름]([부서명]) [직급명]님 환영합니다." 로 출력.


