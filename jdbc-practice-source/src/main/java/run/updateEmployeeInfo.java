package run;

import model.dto.EmployeeDTO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;
import java.util.Scanner;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

public class updateEmployeeInfo {
        public static void main(String[] args) throws SQLException {

            Connection con = getConnection();
            Properties prop = new Properties();
            PreparedStatement pstmt = null;

            try {
                int result = 0;

                pstmt = null;

                prop.loadFromXML(new FileInputStream("src/main/java/mapper/employee-query.xml"));

                String query = prop.getProperty("updateEmployee");

                System.out.println("query = " + query);

                pstmt = con.prepareStatement(query);

                Scanner sc = new Scanner(System.in);
                //전화번호, 이메일, 부서코드, 급여, 보너스

                System.out.print("바꿀 전화번호를 입력해주세요 :  ");
                String phone = sc.nextLine();

                System.out.print("바꿀 사원의 코드를 입력해주세요 :  ");
                String empCode = sc.nextLine();

                System.out.println("바꿀 이메일을 입력하세요 : ");
                String email = sc.nextLine();

                System.out.print("바꿀 사원의 부서코드를 입력하세요 : ");
                String deptCode = sc.nextLine();

                System.out.println("바꿀 급여를 입력해주세요 : ");
                int salary = sc.nextInt();

                System.out.println("바꿀 보너스를 입력해주세요 : ");
                double bonus = sc.nextDouble();

                EmployeeDTO employeeDTO = new EmployeeDTO();

                employeeDTO.setPhone(phone);
                employeeDTO.setEmail(email);
                employeeDTO.setDeptCode(deptCode);
                employeeDTO.setSalary(salary);
                employeeDTO.setBonus(bonus);

                pstmt.setString(1, employeeDTO.getPhone());
                pstmt.setString(2, employeeDTO.getEmail());
                pstmt.setString(3, employeeDTO.getDeptCode());
                pstmt.setInt(4, employeeDTO.getSalary());
                pstmt.setDouble(5, employeeDTO.getBonus());
                pstmt.setString(6, empCode);

                result = pstmt.executeUpdate();

                if (result == 1){
                    System.out.println("직원 정보 수정에 성공했습니다.");
                } else {
                    System.out.println("직원 정보 수정에 실패했습니다.");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (InvalidPropertiesFormatException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                close(con);
                close(pstmt);
            }
        }
    }



    // 수정할 사원 번호를 입력받고
    // 사원 정보(전화번호, 이메일, 부서코드, 급여, 보너스)를 입력받아 DTO객체에 담아서 update
    // update 성공하면 "직원 정보 수정에 성공하였습니다." 출력
    // update 실패하면 "직원 정보 수정에 실패하였습니다." 출력

