package run;

public class selectEmployeeInfo {

    // 사원 번호를 입력받아 해당 사원을 조회하고 DTO객체에 담아서 출력
    // 출력 구문은 DTO 객체의 toString() 내용과
    // "[이름]([부서명]) [직급명]님 환영합니다." 로 출력.

    public static void main(String[] args) {

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        EmployeeDTO selectedEmp = null;
        Scanner sc = new Scanner(System.in);

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/mapper/menu-query.xml"));
            String query = prop.getProperty("selectEmployee");
            String query = "SELECT * FROM EMPLOYEE WHERE EMP_ID = ?";
            System.out.print("조회할 사원의 사원 번호를 입력하세요 : ");
            String empId = sc.nextLine();
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, empId);
            rset = pstmt.executeQuery();

            if(rset.next()) {

                selectEmp = new EmployeeDTO();
                selectedEmp.setEmpId(rset.getString("EMP_ID"));
                selectedEmp.setEmpName(rset.getString("EMP_NAME"));
                selectedEmp.setEmpNo(rset.getString("EMP_NO"));
                selectedEmp.setEmail(rset.getString("EMAIL"));
                selectedEmp.setPhone(rset.getString("PHONE"));
                selectedEmp.setDeptCode(rset.getString("DEPT_CODE"));
                selectedEmp.setJobCode(rset.getString("JOB_CODE"));
                selectedEmp.setSalLevel(rset.getString("SAL_LEVEL"));
                selectedEmp.setSalary(rset.getInt("SALARY"));
                selectedEmp.setBonus(rset.getDouble("BONUS"));
                selectedEmp.setManagerId(rset.getString("MANAGER_ID"));
                selectedEmp.setHireDate(rset.getDate("HIRE_DATE"));
                selectedEmp.setEntDate(rset.getDate("ENT_DATE"));
                selectedEmp.setEntYn(rset.getString("ENT_YN"));
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
            close(con);
        }

        boolean isSuccess = insertEmployee(dto);
        if(isSuccess) {
            System.out.println(
                    employeeDTO.getEmpName() +
                            " ( " + employeeDTO.getDeptCode() + " ) " +
                            employeeDTO.getsalLevel() +
                            "님 환영합니다.");
        }
    }
}
