package com.jd.highend.model;
import lombok.Data;
@Data
public class Department {
    private String dept_id;
    private String name;
    private String parent_id;
    private String remark;

    public String getDept_id() {
        return dept_id;
    }

    public void setDept_id(String dept_id) {
        this.dept_id = dept_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Department:\t{" +
                "dept_id=" + dept_id +
                ", name='" + name + '\'' +
                ", parent_id=" + parent_id +
                ", remark='" + remark + '\'' +
                '}';
    }

}
