package org.gokasama.datart.core.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @author ka wujia@chinamobile.com
 */
@ApiModel(value = "应用配置", description = "Application Configurations")
@Data
@Document
public class AppConf implements Serializable {

    private static final long serialVersionUID = 7513538530500570400L;

    //_id
    @ApiModelProperty(value = "id", hidden = true)
    @Id
    private String id;

    //Configuration key
    @ApiModelProperty(value = "key")
    @Indexed(unique = true)
    private String key;

    //Configuration value
    @ApiModelProperty(value = "value")
    private String value;
}
