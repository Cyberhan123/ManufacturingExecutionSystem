package cn.hselfweb.mes.enity;

import lombok.Data;

import java.util.Date;

@Data
public class MRPExtend {

    private int id;
    private int mrpbId;
    private int wid;
    private int count;
    private Date startTime;
    private Date endTime;
}
