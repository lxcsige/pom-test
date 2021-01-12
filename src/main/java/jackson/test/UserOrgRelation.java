package jackson.test;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xucheng.liu
 * @date 2019/8/2
 */
@Getter
@Setter
@ToString
public class UserOrgRelation implements Serializable {

    private static final long serialVersionUID = 5441007675618827535L;

    private int id;

    private int userId;

    private String userName;

    private int orgId;

    private String orgName;

    private int level;

    @JsonProperty("isDirect")
    private int direct;

    @JsonProperty("isDeleted")
    private int deleted;

    @JsonDeserialize(using = CustomDateDeserializer.class)
    private Date updateTime;
}
