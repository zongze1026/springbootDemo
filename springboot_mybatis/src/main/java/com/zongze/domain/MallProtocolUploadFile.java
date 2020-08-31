package com.zongze.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "mall_protocol_upload_file")
public class MallProtocolUploadFile {
    /**
     * id
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 协议id
     */
    @Column(name = "mall_protocol_id")
    private Long mallProtocolId;

    /**
     * 文件名称
     */
    @Column(name = "mall_file_name")
    private String mallFileName;

    /**
     * 文件访问路径
     */
    @Column(name = "mall_file_path")
    private String mallFilePath;

    /**
     * 文件类型 0：图片    1：文件
     */
    @Column(name = "mall_file_type")
    private Integer mallFileType;

    /**
     * 文件描述
     */
    @Column(name = "mall_file_desc")
    private String mallFileDesc;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 获取id
     *
     * @return id - id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取协议id
     *
     * @return mall_protocol_id - 协议id
     */
    public Long getMallProtocolId() {
        return mallProtocolId;
    }

    /**
     * 设置协议id
     *
     * @param mallProtocolId 协议id
     */
    public void setMallProtocolId(Long mallProtocolId) {
        this.mallProtocolId = mallProtocolId;
    }

    /**
     * 获取文件名称
     *
     * @return mall_file_name - 文件名称
     */
    public String getMallFileName() {
        return mallFileName;
    }

    /**
     * 设置文件名称
     *
     * @param mallFileName 文件名称
     */
    public void setMallFileName(String mallFileName) {
        this.mallFileName = mallFileName;
    }

    /**
     * 获取文件访问路径
     *
     * @return mall_file_path - 文件访问路径
     */
    public String getMallFilePath() {
        return mallFilePath;
    }

    /**
     * 设置文件访问路径
     *
     * @param mallFilePath 文件访问路径
     */
    public void setMallFilePath(String mallFilePath) {
        this.mallFilePath = mallFilePath;
    }

    /**
     * 获取文件类型 0：图片    1：文件
     *
     * @return mall_file_type - 文件类型 0：图片    1：文件
     */
    public Integer getMallFileType() {
        return mallFileType;
    }

    /**
     * 设置文件类型 0：图片    1：文件
     *
     * @param mallFileType 文件类型 0：图片    1：文件
     */
    public void setMallFileType(Integer mallFileType) {
        this.mallFileType = mallFileType;
    }

    /**
     * 获取文件描述
     *
     * @return mall_file_desc - 文件描述
     */
    public String getMallFileDesc() {
        return mallFileDesc;
    }

    /**
     * 设置文件描述
     *
     * @param mallFileDesc 文件描述
     */
    public void setMallFileDesc(String mallFileDesc) {
        this.mallFileDesc = mallFileDesc;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}