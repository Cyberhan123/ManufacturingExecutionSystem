package cn.hselfweb.mes.arithmetic


import cn.hselfweb.mes.arithmetic.entity.DeviceTime
import cn.hselfweb.mes.enity.CraftExtend
import java.io.*

class gantt_out_ef {
    companion object {
        //用于写文件的函数
        fun createFile(filePath: String, fileName: String, Mess: String) {
            val writer: Writer
            try {
                writer = OutputStreamWriter(FileOutputStream(filePath + fileName), "UTF-8")
                val myFile = PrintWriter(writer)
                myFile.append(Mess)
                myFile.flush()
                writer.close()
            } catch (e: UnsupportedEncodingException) {
                // TODO Auto-generated catch block
                e.printStackTrace()
            } catch (e: FileNotFoundException) {
                // TODO Auto-generated catch block
                e.printStackTrace()
            } catch (e: IOException) {
            }

        }

        //用于根据算法结果生成tasks.json、dependencies.json
        fun ganttshouw(list1: List<List<CraftExtend>>, listdt: List<DeviceTime>) {
            val sBuffer = StringBuffer("[")
            val sBuffer2 = StringBuffer("[")

            for (i in list1.indices) {
                //System.out.println("list1.size()"+list1.size());
                if (i == 0) {
                    sBuffer.append(" { \"EndDate\" : \"")
                } else {
                    sBuffer.append(",\r  {\r	\"EndDate\" : \"")
                }
                var t = listdt[i].deviceTime!! + 1
                var mm = 5
                if (t > 30) {
                    mm = mm + t / 30
                    t = t % 30
                }
                if (t <= 9) {
                    sBuffer.append("2015-0$mm-0$t")
                    sBuffer.append("\",\r    \"BaselineEndDate\" : \"")
                    sBuffer.append("2015-0$mm-0$t")
                } else {
                    sBuffer.append("2015-0$mm-$t")
                    sBuffer.append("\",\r    \"BaselineEndDate\" : \"")
                    sBuffer.append("2015-0$mm-$t")
                }
                sBuffer.append("\",\r    \"Id\" : ")
                sBuffer.append(i + 1)
                sBuffer.append(",\r    \"Name\" : \"")
                sBuffer.append(list1[i][0].getDevice())
                System.out.println("list1.get(i).get(0).getDevice()" + list1[i][0].getDevice())
                sBuffer.append("\",\r    \"PercentDone\" : ")
                sBuffer.append(100)
                sBuffer.append(",\r    \"Priority\" : ")
                sBuffer.append(1)
                sBuffer.append(",\r    \"Responsible\" : \"")
                sBuffer.append("")
                sBuffer.append("\",\r    \"StartDate\" : \"")
                sBuffer.append("2015-05-01")//需要完善
                sBuffer.append("\",\r    \"BaselineStartDate\" : \"")
                sBuffer.append("2015-05-01")//需要完善
                sBuffer.append("\",\r    \"expanded\" : ")
                sBuffer.append("true")
                sBuffer.append(",\r    \"children\" : [")
                val listec = list1[i]
                println("listec$listec")
                var ce: CraftExtend
                for (j in listec.indices) {


                    ce = listec[j]
                    //	System.out.println("now.getDevice()"+ce.getDevice()+"~~~~~~"+"now.getBegintime()"+ce.getBegintime()+"~~~~~~"+"now.getEndtime()"+ce.getEndtime());
                    t = ce.getEndtime() + 1
                    mm = 5
                    if (t > 30) {
                        mm = mm + t / 30
                        t = t % 30
                    }
                    sBuffer.append("\r		{\r			\"EndDate\" : \"")
                    if (t <= 9) {
                        sBuffer.append("2015-0$mm-0$t")
                        sBuffer.append("\",\r			\"BaselineEndDate\" : \"")
                        sBuffer.append("2015-0$mm-0$t")
                    } else {
                        sBuffer.append("2015-0$mm-$t")
                        sBuffer.append("\",\r			\"BaselineEndDate\" : \"")
                        sBuffer.append("2015-0$mm-$t")
                    }

                    sBuffer.append("\",\r			\"Id\" : ")
                    sBuffer.append((i + 1) * 10 + j)
                    sBuffer.append(",\r			\"leaf\" : ")
                    sBuffer.append("true")
                    sBuffer.append(",\r			\"Name\" : \"")
                    sBuffer.append(ce.getName())
                    sBuffer.append("\",\r			\"ParentId\" : ")
                    sBuffer.append(i + 1)
                    sBuffer.append(",\r			\"PercentDone\" : ")
                    sBuffer.append(100)
                    sBuffer.append(",\r			\"Priority\" : ")
                    sBuffer.append(1)
                    sBuffer.append(",\r			\"Responsible\" : \"")
                    sBuffer.append("")
                    sBuffer.append("\",\r			\"StartDate\" : \"")
                    t = ce.getBegintime() + 1
                    mm = 5
                    if (t > 30) {
                        mm = mm + t / 30
                        t = t % 30
                    }
                    if (t <= 9) {
                        sBuffer.append("2015-0$mm-0$t")
                        sBuffer.append("\",\r			\"BaselineStartDate\" : \"")
                        sBuffer.append("2015-0$mm-0$t")
                    } else {
                        sBuffer.append("2015-0$mm-$t")
                        sBuffer.append("\",\r			\"BaselineStartDate\" : \"")
                        sBuffer.append("2015-0$mm-$t")
                    }

                    if (j == listec.size - 1) {
                        sBuffer.append("\"\r		}\r    ]\r  }")
                    } else {
                        sBuffer.append("\"\r		},")
                    }
                    /*	{
				      "From":10,
				      "To":11,
				      "Type":2
				   },*/
                    sBuffer2.append("	{\r	\"From\":" + ((i + 1) * 10 + j) + ",")
                    sBuffer2.append("	 \r	\"To\":" + ((i + 1) * 10 + j + 1) + ",")
                    sBuffer2.append("	 \r	\"Type\":" + 2 + "\r	},")
                }
            }
            sBuffer.append("\r]")
            sBuffer2.append("\r]")
            val ServerMess = sBuffer.toString()
            val ServerMess2 = sBuffer2.toString()
            println(ServerMess)
            println(ServerMess2)
            // JSONObject jsonObj = new JSONObject(ServerMess);
//            val root = ServletActionContext.getServletContext().getRealPath("/ext-gantt/examples/advanced/t")
//            val root1 = ServletActionContext.getServletContext().getRealPath("/ext-gantt/examples/advanced/d")
//            gantt_out_ef.createFile(root, "asks.json", ServerMess)
//            gantt_out_ef.createFile(root1, "ependencies.json", ServerMess2)

        }
    }
}// TODO Auto-generated constructor stub
