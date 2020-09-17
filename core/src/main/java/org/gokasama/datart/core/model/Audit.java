package org.gokasama.datart.core.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ka wujia@chinamobile.com
 */
@ApiModel(value = "审计日志", description = "Audit")
@Data
@Document
public class Audit implements Serializable {

    private static final long serialVersionUID = -1006050576989582894L;

    //_id
    @ApiModelProperty(value = "id", hidden = true)
    @Id
    private String id;

    //Operation
    @ApiModelProperty(value = "操作")
    private String operation;

    //Args
    @ApiModelProperty(value = "参数")
    private String args;

    //Create date
    @ApiModelProperty(value = "创建时间")
    @CreatedDate
    private Date createdDate;
}
