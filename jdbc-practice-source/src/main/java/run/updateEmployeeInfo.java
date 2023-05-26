package run;

public class updateEmployeeInfo {

    // 수정할 사원 번호를 입력받고
    // 사원 정보(전화번호, 이메일, 부서코드, 급여, 보너스)를 입력받아 DTO객체에 담아서 update
    // update 성공하면 "직원 정보 수정에 성공하였습니다." 출력
    // update 실패하면 "직원 정보 수정에 실패하였습니다." 출력

    public static void main(String[] args) {

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        int result = 0;
        EmployeeDTO emp = new EmployeeDTO();
        Scanner sc = new Scanner(System.in);
        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/mapper/menu-query.xml"));
            String query = prop.getProperty("updateEmployee");

            System.out.print("수정할 사원 번호를 입력하세요 : ");
            String empId = sc.nextLine();
            System.out.print("전화번호를 입력하세요 : ");
            String phone = sc.nextLine();
            System.out.print("이메일을 입력하세요 : ");
            String email = sc.nextLine();
            System.out.print("부서코드를 입력하세요 : ");
            String deptCode = sc.nextLine();
            System.out.print("급여를 입력하세요 : ");
            int salary = sc.nextInt();
            System.out.print("보너스를 입력하세요 : ");
            Double bonus = sc.nextDouble();

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, employeeDTO.getEmpId());
            pstmt.setString(2, employeeDTO.getPhone());
            pstmt.setString(3, employeeDTO.getEmail());
            pstmt.setString(4, employeeDTO.getDeptCode());
            pstmt.setInt(5, employeeDTO.getSalary());
            pstmt.setDouble(6, employeeDTO.getBonus());

            result = pstmt.executeUpdate();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
            close(con);
        }

        if(result > 0) {
            System.out.println("직원 정보 수정에 성공하였습니다.");
        } else {
            System.out.println("직원 정보 수정에 실패하였습니다.");
        }
    }
}
