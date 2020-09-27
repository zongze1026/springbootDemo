package com.zongze.controller;

/**
 * @Date 2020/9/23 11:01
 * @Created by xzz
 */
public class OrderRenewalInfoDto {

    @Excel(name = "保单年度")
    private Integer assessmentIndex;

    @Excel(name = "保单号")
    private String policyNo;

    @Excel(name = "实收保费")
    private Double payedPremium;

    @Excel(name = "实收日期")
    private String renewalDate;


    public Integer getAssessmentIndex() {
        return assessmentIndex;
    }

    public void setAssessmentIndex(Integer assessmentIndex) {
        this.assessmentIndex = assessmentIndex;
    }

    public String getPolicyNo() {
        return policyNo;
    }

    public void setPolicyNo(String policyNo) {
        this.policyNo = policyNo;
    }

    public Double getPayedPremium() {
        return payedPremium;
    }

    public void setPayedPremium(Double payedPremium) {
        this.payedPremium = payedPremium;
    }

    public String getRenewalDate() {
        return renewalDate;
    }

    public void setRenewalDate(String renewalDate) {
        this.renewalDate = renewalDate;
    }
}
