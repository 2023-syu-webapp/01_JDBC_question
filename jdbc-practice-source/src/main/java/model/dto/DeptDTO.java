package model.dto;

public class DeptDTO {
    private String deptId;
    private String depttitle;
    private String locationId;

    public DeptDTO() {

    }

    public DeptDTO(String deptId, String depttitle, String locationId) {
        this.deptId = deptId;
        this.depttitle = depttitle;
        this.locationId = locationId;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDepttitle() {
        return depttitle;
    }

    public void setDepttitle(String depttitle) {
        this.depttitle = depttitle;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }
}
