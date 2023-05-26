package run;

public class deleteEmployeeInfo {

    // 삭제할 사원의 이름을 사용자로부터 입력받아 삭제
    // 이름이 정확히 일치해야 함
    // 사원 삭제에 성공하면 "[이름]님이 퇴출되었습니다." 출력
    // 사원 삭제에 실패하면 "[이름]님은 퇴출될 수 없습니다." 출력

    public static void main(String[] args) {

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        int result = 0;
        EmployeeDTO emp = new EmployeeDTO();
        Scanner sc = new Scanner(System.in);

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/mapper/menu-query.xml"));
            String query = prop.getProperty("deleteEmployee");
            System.out.print("삭제할 사원의 이름을 입력하세요 : ");
            String empName = sc.nextLine();
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, empId);
            result = pstmt.executeUpdate();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
            close(con);
        }

        if (result > 0) {
            System.out.println(employeeDTO.getEmpName() + "님이 퇴출되었습니다.");
        } else {
            System.out.println(employeeDTO.getEmpName() + "님은 퇴출될 수 없습니다.");
        }
    }
}
