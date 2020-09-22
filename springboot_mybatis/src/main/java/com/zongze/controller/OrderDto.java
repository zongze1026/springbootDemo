package com.zongze.controller;


/**
 * Created by witch on 2018/11/26.
 */
public class OrderDto {

    @Excel(name = "机构编号")
    private String orgCode;

    @Excel(name = "车牌")
    private String channel;

    @Excel(name = "保险公司编码")
    private String companyCode;

    @Excel(name = "保单号")
    private String policyNo;

    @Excel(name = "批单号")
    private String totalAmount;

    @Excel(name = "签单时间")
    private String orderTimeStr;

    @Excel(name = "保单生效日期")
    private String effStartDateStr;

    @Excel(name = "保单失效日期")
    private String effEndDateStr;

    @Excel(name = "投保人")
    private String applicantName;

    @Excel(name = "被保险人")
    private String batchOrderNum;

    @Excel(name = "代理人姓名")
    private String applicantId;

    @Excel(name = "代理人工号")
    private String applicantPhone;

    @Excel(name = "产品编码")
    private String applicantIdType;

    @Excel(name = "签单保费（含税）")
    private Double insurantName;

    @Excel(name = "实缴保费（不含税）")
    private Double insurantIdType;

    @Excel(name = "手续费（含税）")
    private Double personRelation;

    @Excel(name = "手续费（不含税）")
    private Double payPremium;

    @Excel(name = "代收保费")
    private Double insrTimeLimit;

    @Excel(name = "缴费方式")
    private String payTimeLimit;

    @Excel(name = "保险金额")
    private Double agentName;

    @Excel(name = "被保险人证件号码")
    private String agentCode;


    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getPolicyNo() {
        return policyNo;
    }

    public void setPolicyNo(String policyNo) {
        this.policyNo = policyNo;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getOrderTimeStr() {
        return orderTimeStr;
    }

    public void setOrderTimeStr(String orderTimeStr) {
        this.orderTimeStr = orderTimeStr;
    }

    public String getEffStartDateStr() {
        return effStartDateStr;
    }

    public void setEffStartDateStr(String effStartDateStr) {
        this.effStartDateStr = effStartDateStr;
    }

    public String getEffEndDateStr() {
        return effEndDateStr;
    }

    public void setEffEndDateStr(String effEndDateStr) {
        this.effEndDateStr = effEndDateStr;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public String getBatchOrderNum() {
        return batchOrderNum;
    }

    public void setBatchOrderNum(String batchOrderNum) {
        this.batchOrderNum = batchOrderNum;
    }

    public String getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(String applicantId) {
        this.applicantId = applicantId;
    }

    public String getApplicantPhone() {
        return applicantPhone;
    }

    public void setApplicantPhone(String applicantPhone) {
        this.applicantPhone = applicantPhone;
    }

    public String getApplicantIdType() {
        return applicantIdType;
    }

    public void setApplicantIdType(String applicantIdType) {
        this.applicantIdType = applicantIdType;
    }

    public Double getInsurantName() {
        return insurantName;
    }

    public void setInsurantName(Double insurantName) {
        this.insurantName = insurantName;
    }

    public Double getInsurantIdType() {
        return insurantIdType;
    }

    public void setInsurantIdType(Double insurantIdType) {
        this.insurantIdType = insurantIdType;
    }

    public Double getPersonRelation() {
        return personRelation;
    }

    public void setPersonRelation(Double personRelation) {
        this.personRelation = personRelation;
    }

    public Double getPayPremium() {
        return payPremium;
    }

    public void setPayPremium(Double payPremium) {
        this.payPremium = payPremium;
    }

    public Double getInsrTimeLimit() {
        return insrTimeLimit;
    }

    public void setInsrTimeLimit(Double insrTimeLimit) {
        this.insrTimeLimit = insrTimeLimit;
    }

    public String getPayTimeLimit() {
        return payTimeLimit;
    }

    public void setPayTimeLimit(String payTimeLimit) {
        this.payTimeLimit = payTimeLimit;
    }

    public Double getAgentName() {
        return agentName;
    }

    public void setAgentName(Double agentName) {
        this.agentName = agentName;
    }

    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }
}
