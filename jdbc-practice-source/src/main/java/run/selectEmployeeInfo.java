package run;

import model.dto.EmployeeDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

    public class selectEmployeeInfo {

        // 사원 번호를 입력받아 해당 사원을 조회하고 DTO객체에 담아서 출력
        // 출력 구문은 DTO 객체의 toString() 내용과
        // "[이름]([부서명]) [직급명]님 환영합니다." 로 출력.

        public static void main(String[] args) {
            Connection con = getConnection();
            PreparedStatement pstmt = null;
            ResultSet rset = null;

            EmployeeDTO empDTO = null;
            Properties prop = new Properties();

            Scanner sc = new Scanner(System.in);
            System.out.print("사원번호를 입력해주세요 : ");
            String empId = sc.nextLine();

            try {
                prop.loadFromXML(new FileInputStream("src/main/java/mapper/employee-query.xml"));
                String query = prop.getProperty("selectEmployee");
                pstmt = con.prepareStatement(query);

                empDTO = new EmployeeDTO();
                empDTO.setEmpId(empId);
                pstmt.setString(1, empDTO.getEmpId());

                rset = pstmt.executeQuery();

                while (rset.next()) {
                    empDTO.setEmpId(rset.getString("EMP_ID"));
                    empDTO.setEmpName(rset.getString("EMP_NAME"));
                    empDTO.setEmpNo(rset.getString("EMP_NO"));
                    empDTO.setEmail(rset.getString("EMAIL"));
                    empDTO.setPhone(rset.getString("PHONE"));
                    empDTO.setDeptCode(rset.getString("DEPT_CODE"));
                    empDTO.setJobCode(rset.getString("JOB_CODE"));
                    empDTO.setSalary(rset.getInt("SALARY"));
                    empDTO.setSalLevel(rset.getString("SAL_LEVEL"));
                    empDTO.setBonus(rset.getDouble("BONUS"));
                    empDTO.setManagerId(rset.getString("MANAGER_ID"));
                    empDTO.setHireDate(rset.getDate("HIRE_DATE"));
                    empDTO.setEntDate(rset.getDate("ENT_DATE"));
                    empDTO.setEntYn(rset.getString("ENT_YN"));
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                close(con);
                close(pstmt);
                close(rset);
            }

            if (empDTO != null) {
                System.out.println(empDTO);
                System.out.println(empDTO.getEmpName() + "(" + empDTO.getDeptCode() + ") " + empDTO.getSalLevel() + "님 환영합니다.");
            }
        }

    }
