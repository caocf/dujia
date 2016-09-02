package cn.com.gome.dujia.vo.json.order;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by sunming on 2016/4/26.
 */
public class RefundorChangeDetail  implements Serializable {
    private static final long serialVersionUID = 1L;
    @JsonProperty(value="CreateDate")
    private Date CreateDate;
    @JsonProperty(value="FeedbackType")
    private String FeedbackType;
    @JsonProperty(value="OperateRemark")
    private String OperateRemark;
    @JsonProperty(value="OperateResult")
    private String operateResult;
    @JsonProperty(value="ProblemDescription")
    private String problemDescription;

    public Date getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(Date createDate) {
        CreateDate = createDate;
    }

    public String getFeedbackType() {
        return FeedbackType;
    }

    public void setFeedbackType(String feedbackType) {
        FeedbackType = feedbackType;
    }

    public String getOperateRemark() {
        return OperateRemark;
    }

    public void setOperateRemark(String operateRemark) {
        OperateRemark = operateRemark;
    }

    public String getOperateResult() {
        return operateResult;
    }

    public void setOperateResult(String operateResult) {
        this.operateResult = operateResult;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }
}
