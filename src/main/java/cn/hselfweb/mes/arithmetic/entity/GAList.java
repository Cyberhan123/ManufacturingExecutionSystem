package cn.hselfweb.mes.arithmetic.entity;

import cn.hselfweb.mes.enity.CraftExtend;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class GAList {
    private static final String dataPath = "GAData.json";
    private int DeviceNumber;
    private List<List<CraftExtend>> list_sum = new ArrayList<List<CraftExtend>>();

    public int getDeviceNumber() {
        return DeviceNumber;
    }

    public void setDeviceNumber(int deviceNumber) {
        DeviceNumber = deviceNumber;
    }

    public List<List<CraftExtend>> getList_sum() {
        return list_sum;
    }

    public void setList_sum(List<List<CraftExtend>> list_sum) {
        this.list_sum = list_sum;
    }

    /**
     * 实例化  调度工序列表
     *
     * @throws FileNotFoundException
     */
    public static final GAList Load() throws FileNotFoundException {
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new InputStreamReader(new FileInputStream(dataPath)));
        GAList ListC = gson.fromJson(reader, GAList.class);
        int gjCount = ListC.getList_sum().size();
        int gxCount = ListC.getList_sum().get(0).size();
        for (int i = 0; i < gjCount; i++) {

            for (int j = 0; j < gxCount; j++) {
                ListC.getList_sum().get(i).get(j).setGid(j + 1);
                ListC.getList_sum().get(i).get(j).setWpid(i + 1);
                if (j != 0) {
                    //设置前序工序
                    ListC.getList_sum().get(i).get(j).setExtendCraftByPreEcId(ListC.getList_sum().get(i).get(j - 1));
                }
                if (j < gxCount - 1) {
                    //设置后序工序
                    ListC.getList_sum().get(i).get(j).setExtendCraftByAftEcId(ListC.getList_sum().get(i).get(j + 1));
                }

            }
        }
        return ListC;
    }

}
