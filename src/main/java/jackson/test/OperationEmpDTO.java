package jackson.test;

/**
 * @author xucheng.liu
 * @date 2019/8/12
 */
public class OperationEmpDTO {

    private static final long serialVersionUID = -8871176678937825148L;
    /**
     * 个人信息
     */
    private String empId;
    private String jobNumber;
    private String source;
    private String name;
    private String mis;
    private String jobStatus;

    /**
     * 汇报信息
     */
    private String reportEmpId;
    private String reportEmpName;
    private String reportEmpIdPath; //汇报链格式:12-3213-31231231-1312313123

    /**
     * 组织信息
     */
    private String orgId;
    private String orgName;

    /**
     * 分配信息
     */
    private Integer assignLimit;    //分配上限
    private Integer status;         //人员在系统中的状态

    /**
     * 电话信息
     */
    private String phoneExtNo;  //分机号
    private String skillGroup;  //技能组

    /**
     * KPI字段
     */
    private Integer rechargeAccountNumberGoal;  //续费账户数目标
    private Integer callNumberGoal;             //有效通话数量目标
    private Integer callSecondsGoal;            //有效通话时长目标
    private Integer callAccountNumberGoal;      //电话成功沟通帐号数目标
    private Integer callAccountSecondsGoal;     //电话成功沟通时长目标
    private Integer contactAccountNumberGoal;   //有效成功沟通帐号数目标
    private Integer touchAccountNumberGoal;     //触达帐号数目标
    private Integer rechargeAmountGoal;         //续充收入目标
    private Integer activeAccountNumberGoal;    //活跃帐号数(现金消耗达标帐号数)目标

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMis() {
        return mis;
    }

    public void setMis(String mis) {
        this.mis = mis;
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }

    public String getReportEmpId() {
        return reportEmpId;
    }

    public void setReportEmpId(String reportEmpId) {
        this.reportEmpId = reportEmpId;
    }

    public String getReportEmpName() {
        return reportEmpName;
    }

    public void setReportEmpName(String reportEmpName) {
        this.reportEmpName = reportEmpName;
    }

    public String getReportEmpIdPath() {
        return reportEmpIdPath;
    }

    public void setReportEmpIdPath(String reportEmpIdPath) {
        this.reportEmpIdPath = reportEmpIdPath;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Integer getAssignLimit() {
        return assignLimit;
    }

    public void setAssignLimit(Integer assignLimit) {
        this.assignLimit = assignLimit;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPhoneExtNo() {
        return phoneExtNo;
    }

    public void setPhoneExtNo(String phoneExtNo) {
        this.phoneExtNo = phoneExtNo;
    }

    public String getSkillGroup() {
        return skillGroup;
    }

    public void setSkillGroup(String skillGroup) {
        this.skillGroup = skillGroup;
    }

    public Integer getRechargeAccountNumberGoal() {
        return rechargeAccountNumberGoal;
    }

    public void setRechargeAccountNumberGoal(Integer rechargeAccountNumberGoal) {
        this.rechargeAccountNumberGoal = rechargeAccountNumberGoal;
    }

    public Integer getCallNumberGoal() {
        return callNumberGoal;
    }

    public void setCallNumberGoal(Integer callNumberGoal) {
        this.callNumberGoal = callNumberGoal;
    }

    public Integer getCallSecondsGoal() {
        return callSecondsGoal;
    }

    public void setCallSecondsGoal(Integer callSecondsGoal) {
        this.callSecondsGoal = callSecondsGoal;
    }

    public Integer getCallAccountNumberGoal() {
        return callAccountNumberGoal;
    }

    public void setCallAccountNumberGoal(Integer callAccountNumberGoal) {
        this.callAccountNumberGoal = callAccountNumberGoal;
    }

    public Integer getCallAccountSecondsGoal() {
        return callAccountSecondsGoal;
    }

    public void setCallAccountSecondsGoal(Integer callAccountSecondsGoal) {
        this.callAccountSecondsGoal = callAccountSecondsGoal;
    }

    public Integer getContactAccountNumberGoal() {
        return contactAccountNumberGoal;
    }

    public void setContactAccountNumberGoal(Integer contactAccountNumberGoal) {
        this.contactAccountNumberGoal = contactAccountNumberGoal;
    }

    public Integer getTouchAccountNumberGoal() {
        return touchAccountNumberGoal;
    }

    public void setTouchAccountNumberGoal(Integer touchAccountNumberGoal) {
        this.touchAccountNumberGoal = touchAccountNumberGoal;
    }

    public Integer getRechargeAmountGoal() {
        return rechargeAmountGoal;
    }

    public void setRechargeAmountGoal(Integer rechargeAmountGoal) {
        this.rechargeAmountGoal = rechargeAmountGoal;
    }

    public Integer getActiveAccountNumberGoal() {
        return activeAccountNumberGoal;
    }

    public void setActiveAccountNumberGoal(Integer activeAccountNumberGoal) {
        this.activeAccountNumberGoal = activeAccountNumberGoal;
    }

    @Override
    public String toString() {
        return "OperationEmpDTO{" +
                "empId='" + empId + '\'' +
                ", jobNumber='" + jobNumber + '\'' +
                ", source='" + source + '\'' +
                ", name='" + name + '\'' +
                ", mis='" + mis + '\'' +
                ", jobStatus='" + jobStatus + '\'' +
                ", reportEmpId='" + reportEmpId + '\'' +
                ", reportEmpName='" + reportEmpName + '\'' +
                ", reportEmpIdPath='" + reportEmpIdPath + '\'' +
                ", orgId='" + orgId + '\'' +
                ", orgName='" + orgName + '\'' +
                ", assignLimit=" + assignLimit +
                ", status=" + status +
                ", phoneExtNo='" + phoneExtNo + '\'' +
                ", skillGroup='" + skillGroup + '\'' +
                ", rechargeAccountNumberGoal=" + rechargeAccountNumberGoal +
                ", callNumberGoal=" + callNumberGoal +
                ", callSecondsGoal=" + callSecondsGoal +
                ", callAccountNumberGoal=" + callAccountNumberGoal +
                ", callAccountSecondsGoal=" + callAccountSecondsGoal +
                ", contactAccountNumberGoal=" + contactAccountNumberGoal +
                ", touchAccountNumberGoal=" + touchAccountNumberGoal +
                ", rechargeAmountGoal=" + rechargeAmountGoal +
                ", activeAccountNumberGoal=" + activeAccountNumberGoal +
                '}';
    }
}
