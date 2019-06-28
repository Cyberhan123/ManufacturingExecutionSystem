package cn.hselfweb.mes.arithmetic.GA.copy;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.List;
import com.mes.arithmetic.entity.DeviceTime;
import com.mes.entity.CraftExtend;
import com.mes.entity.Device;

import org.apache.struts2.ServletActionContext;

public class gantt_out {

	public gantt_out() {
		// TODO Auto-generated constructor stub
	}
	//用于写文件的函数
	public static void createFile(String filePath, String fileName, String Mess) {
		Writer writer;
		try {
			writer = new OutputStreamWriter(new FileOutputStream(filePath+ fileName), "UTF-8");
			PrintWriter myFile = new PrintWriter(writer);
			myFile.append(Mess);
			myFile.flush();
			writer.close();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
		}
	}
	//用于根据算法结果生成tasks.json、dependencies.json
	public static void ganttshouw(List<Device> list1,List<DeviceTime> listdt){
		StringBuffer sBuffer = new StringBuffer("[");
		StringBuffer sBuffer2 = new StringBuffer("[");
		
		for (int i = 0; i < list1.size(); i++) {
			System.out.println("list1.size()"+list1.size());
				if(i==0){
				sBuffer.append(" { \"EndDate\" : \"");
				}else {
				sBuffer.append(",\r  {\r	\"EndDate\" : \"");
				}
				int t = listdt.get(i).getDeviceTime()+1;
				int mm=5;
				if(t>30){
					mm++;
					t=t%30;
				}
				if(t<9){
					sBuffer.append("2015-0"+mm+"-0"+t);
					sBuffer.append("\",\r    \"BaselineEndDate\" : \"");
					sBuffer.append("2015-0"+mm+"-0"+t);
				}else{
					sBuffer.append("2015-0"+mm+"-"+t);
					sBuffer.append("\",\r    \"BaselineEndDate\" : \"");
					sBuffer.append("2015-0"+mm+"-"+t);
				}
			sBuffer.append("\",\r    \"Id\" : ");
				sBuffer.append(i+1);
			sBuffer.append(",\r    \"Name\" : \"");
				sBuffer.append(list1.get(i).getName());
				System.out.println("list1.get(i).getName()"+list1.get(i).getName());
			sBuffer.append("\",\r    \"PercentDone\" : ");
				sBuffer.append(100);
			sBuffer.append(",\r    \"Priority\" : ");
				sBuffer.append(1);
			sBuffer.append(",\r    \"Responsible\" : \"");
				sBuffer.append("");
			sBuffer.append("\",\r    \"StartDate\" : \"");
				sBuffer.append("2015-05-01");//需要完善
			sBuffer.append("\",\r    \"BaselineStartDate\" : \"");
				sBuffer.append("2015-05-01");//需要完善
			sBuffer.append("\",\r    \"expanded\" : ");
				sBuffer.append("true");
			sBuffer.append(",\r    \"children\" : [");
			List<CraftExtend> listec = list1.get(i).getListgx();
			System.out.println("listec"+listec);
			CraftExtend ce;
			for(int j=0;j<listec.size();j++){
				ce = listec.get(j);
				t = ce.getEndtime()+1;
				mm=5;
				if(t>30){
					mm++;
					t=t%30;
				}
				sBuffer.append("\r		{\r			\"EndDate\" : \"");
					if(t<9){
					sBuffer.append("2015-0"+mm+"-0"+t);
				sBuffer.append("\",\r			\"BaselineEndDate\" : \"");
					sBuffer.append("2015-0"+mm+"-0"+t);
					}else{
					sBuffer.append("2015-0"+mm+"-"+t);
				sBuffer.append("\",\r			\"BaselineEndDate\" : \"");
					sBuffer.append("2015-0"+mm+"-"+t);
					}
					
				sBuffer.append("\",\r			\"Id\" : ");
					sBuffer.append((i+1)*10+j);
				sBuffer.append(",\r			\"leaf\" : ");
					sBuffer.append("true");
				sBuffer.append(",\r			\"Name\" : \"");
					sBuffer.append(ce.getName());
				sBuffer.append("\",\r			\"ParentId\" : ");
					sBuffer.append(i+1);
				sBuffer.append(",\r			\"PercentDone\" : ");
					sBuffer.append(100);
				sBuffer.append(",\r			\"Priority\" : ");
					sBuffer.append(1);
				sBuffer.append(",\r			\"Responsible\" : \"");
					sBuffer.append("");
				sBuffer.append("\",\r			\"StartDate\" : \"");
					t = ce.getBegintime()+1;
					mm=5;
					if(t>30){
						mm++;
						t=t%30;
					}
					if(t<9){
					sBuffer.append("2015-0"+mm+"-0"+t);
				sBuffer.append("\",\r			\"BaselineStartDate\" : \"");
					sBuffer.append("2015-0"+mm+"-0"+t);	
					}else{
					sBuffer.append("2015-0"+mm+"-"+t);
				sBuffer.append("\",\r			\"BaselineStartDate\" : \"");
					sBuffer.append("2015-0"+mm+"-"+t);
					}
					
					if(j==listec.size()-1){
						sBuffer.append("\"\r		}\r    ]\r  }");
					}else {
						sBuffer.append("\"\r		},");
					}
					/*	{
				      "From":10,
				      "To":11,
				      "Type":2
				   },*/
					sBuffer2.append("	{\r	\"From\":"+((i+1)*10+j)+",");
					sBuffer2.append("	 \r	\"To\":"+((i+1)*10+j+1)+",");
					sBuffer2.append("	 \r	\"Type\":"+2+"\r	},");
			}
		}
		sBuffer.append("\r]");
		sBuffer2.append("\r]");
		String ServerMess = sBuffer.toString();
		String ServerMess2 = sBuffer2.toString();
		//JSONObject jsonObj = new JSONObject(ServerMess);
		String root = ServletActionContext.getServletContext().getRealPath("/ext-gantt/examples/advanced/t");
		String root1 = ServletActionContext.getServletContext().getRealPath("/ext-gantt/examples/advanced/d");
		gantt_out.createFile(root, "asks.json", ServerMess);
		gantt_out.createFile(root1, "ependencies.json", ServerMess2);
		
	}
}
