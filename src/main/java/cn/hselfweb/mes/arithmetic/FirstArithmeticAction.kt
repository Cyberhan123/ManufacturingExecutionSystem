package cn.hselfweb.mes.arithmetic

import cn.hselfweb.mes.arithmetic.entity.CraftTime
import cn.hselfweb.mes.arithmetic.entity.DeviceTime
import cn.hselfweb.mes.arithmetic.pojo.FirstArithmeticParams
import cn.hselfweb.mes.enity.*
import cn.hselfweb.mes.repository.*
import org.springframework.beans.factory.annotation.Autowired
import java.util.*
import javax.servlet.ServletContext
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpSession

class FirstArithmeticAction {
    private var request: HttpServletRequest? = null
    private var session: HttpSession? = null
    private var application: ServletContext? = null
    @Autowired
    lateinit var mrpBaseRepository: MRPBaseRepository
    @Autowired
    lateinit var mrpExtendRepository: MRPExtendRepository
    @Autowired
    lateinit var craftBaseRepository: CraftBaseRepository
    @Autowired
    lateinit var craftExtendRepository: CraftExtendRepository
    @Autowired
    lateinit var procedureRepository: ProcedureRepository
    @Autowired
    lateinit var procedureTypeRepository: ProcedureTypeRepository
    @Autowired
    lateinit var workpieceRepository: WorkpieceRepository
    @Autowired
    lateinit var deviceRepository: DeviceRepository


    var w: Workpiece? = null


    //    @Throws(Exception::class)
    fun execute(): String {

//        request = ServletActionContext.getRequest()
        session = request!!.session
        application = session!!.servletContext
        //基本工艺
        val craftBases = ArrayList<CraftBase>()
        //设备
        val devices = ArrayList<Device>()

        val processDispatchResult = ArrayList<ProcessDispatchResult>()


        //对每个mrp进行排序处理
        val bmrpid = session!!.getAttribute("taskmrp") as Array<String>

        //HttpServletRequest request = ServletActionContext.getRequest();
        //String langtype[]=request.getParameterValues("mrpbid");
        //println("待调度的MRP单id：" + Integer.parseInt(bmrpid[0]))
        //Integer bmrpid = (Integer) application.getAttribute("bmrpid");
        val bmrp = mrpBaseRepository!!.findMRPBaseByMRPBaseId(bmrpid[0].toLong())

        System.out.println("mrp单编号" + bmrp.mrpBaseId)

        val listM_base_sum: MutableList<MutableList<CraftExtend>> = ArrayList()// 生成    设备加工序列排序集合  的集合    (最后的结果集，甘特图展示)
        //(每个元素是一个设备上的加工工序集合 eg： 设备一：工件一的第一道工序，工件三的第二道工序，...;设备二：工件三的第一道工序，工件x的xx，...;....;....;)

        //根据bmrp获取emrp


        val emrps = mrpExtendRepository!!.findMRPExtendsByMRPEBaseId(bmrp.mrpBaseId)

        //====================为每道工序赋值找到所有设备================
        val iter = emrps.iterator()
        while (iter.hasNext()) {
            //			System.out.print("在循环0中");
            val em = iter.next() as MRPExtend
            w = workpieceRepository!!.findWorkpieceByWorkpieceId(em.wid)

            //根据工件获取工艺
            val bc = craftBaseRepository!!.findCraftBaseByWorkpieceId(w!!.workpieceId)

            craftBases.add(bc)
            //根据基本工艺获取详细工艺（工序）
            val ecs = craftExtendRepository!!.findCraftExtendsByCraftBaseId(bc.craftBaseId)

            val it = ecs.iterator()
            while (it.hasNext()) {
                var b = true
                val ec = it.next() as CraftExtend
                ec.craftBaseId = bmrp.mrpBaseId
                ec.gname = w!!.name
                ec.wpname = w!!.name
                ec.wpid = w!!.workpieceId
                //System.out.println("工件名："+ec.getGname());
                //根据CraftExtend生成所需要的设备加工序列
                //				System.out.print("在循环1中");
                //根据详细工艺获取设备
                val bd = deviceRepository!!.findDeviceByDeviceId(procedureTypeRepository!!.findProcedureTypeByProcedureTypeId(procedureRepository!!.findProcedureByProcedureId(ec.wpid).ptId).did)

                if (devices.size == 0) {

                } else {
                    for (bdd in devices) {
                        if (bdd.deviceId == bd.deviceId) {
                            b = false
                        }
                    }
                }
                if (b) {
                    devices.add(bd)//存储所有用到的设备
                }
            }
        }

        val DeviceNumber = devices.size
        println("DeviceNumber:$DeviceNumber")
        for (d in 1..DeviceNumber) {
            val listM_base = ArrayList<CraftExtend>()// 生成设备加工序列排序集合
            listM_base_sum.add(listM_base)
        }


        //车间设备信息初始化(从数据库中获取相关车间信息)

        //=======================为获取的设备进行编号===========================
        /*		Map<String,Integer> bdmap = new HashMap<String,Integer>();
		int bdnum = 1;
		for(Device bd:listbd){
			System.out.println("设备ID："+bd.getId()+" 设备名："+bd.getName()+"设备号："+bdnum);
			bdmap.put(bd.getName(),bdnum);
			bdnum++;
		}*/

        val list_sum: MutableList<MutableList<CraftExtend>> = ArrayList() //用于存储工艺和和工序的关系
        val CraftExtendsCount = craftExtendRepository!!.findCraftExtendsByCraftBaseId(craftBases[0].craftBaseId)
        for (ii in CraftExtendsCount.indices) {//现在仅考虑每个工件都拥有相同数量的加工序列
            val list = ArrayList<CraftExtend>()// 特定工序包含的加工序列集合（纵向）
            list_sum.add(list)
        }


        //=======================用于存储LIST_SUM的每个基本工艺的详细工序=========================
        //
        //  list_sum  第一道工序（）；第二道工序（）；....
        var pidd = 1
        for (bc in craftBases) {
            //用于list_sum的顺序输入
            //根据基本工艺获取详细工艺（工序）
            println("在循环中~~~~~")
            val ecs = craftExtendRepository!!.findCraftExtendsByCraftBaseId(bc.craftBaseId)
            //对具体的每道工序进行遍历
            val it = ecs.iterator()
            var ec = it.next() as CraftExtend

            //ec.getCraftExtendByPreEcId()替换获取前道工艺
            if (ec.pbId != 0.toLong()) {//craftExtendRepository.findCraftExtendByProperty("CraftExtend", "p_id", String.valueOf(procedureRepository.findProcedureById(ec.getPbId()).getId())
                do {
                    ec = craftExtendRepository!!.findCraftExtendsByPbId(ec.pbId)

                } while (ec.pbId != 0.toLong())
                //获取第一个工序
                var i = 1
                ec.gid = i
                ec.wpname = workpieceRepository!!.findWorkpieceByWorkpieceId(craftBaseRepository!!.findCraftBaseByCraftBaseId(ec.craftBaseId).workpieceId).name//ec.getWpname()
                //TODO：这块好像有点问题
                ec.wpid = pidd.toLong()    //所属工件的编号
                //根据ec扩展工艺查找工序
                //pts.findProcedureTypeByProcedureTypeId(ps.findProcedureByProperty("Procedure","p_id",String.valueOf(ec.getPid())).getPtId())
                ec.name = procedureTypeRepository!!.findProcedureTypeByProcedureTypeId(procedureRepository!!.findProcedureByProcedureId(ec.procedureId).getPtId()).getName()
                ec.time = procedureTypeRepository!!.findProcedureTypeByProcedureTypeId(procedureRepository!!.findProcedureByProcedureId(ec.procedureId).getPtId()).getAvgTime()
                ec.device = deviceRepository!!.findDeviceByDeviceId(procedureTypeRepository!!.findProcedureTypeByProcedureTypeId(procedureRepository!!.findProcedureByProcedureId(ec.procedureId).getPtId()).getDid()).getName()
                //这样获取的是  。他的前序工艺，但是是   new的一个新实例。 所以不在数据库中存储的临时变量无法获取。
                //ec.setExtendCraftByPreEcId(craftExtendRepository.findCraftExtendByProperty("CraftExtend", "p_id", String.valueOf(ec.getPbId())));
                //ec.setExtendCraftByAftEcId(craftExtendRepository.findCraftExtendByProperty("CraftExtend", "p_id", String.valueOf(ec.getPaId())));

                println("在循环中~~~~~")
                list_sum[i - 1].add(ec)

                //替换ces.findCraftExtendByProperty("CraftExtend", "p_id", String.valueOf(ps.findProcedureByProcedureId(ec.getPaId()).getId()))
                if (ec.getPaId() != 0.toLong()) {
                    //将后道工序加入list_sum
                    do {
                        //list_sum.get().get()前一道工序的  后续  set  ec
                        ec = craftExtendRepository!!.findCraftExtendsByProcedureId(ec.paId)
                        //TODO:这块需要检查
                        val eclast = list_sum[i - 1][pidd - 1]
                        eclast.extendCraftByAftEcId = ec
                        ec.extendCraftByPreEcId = eclast
                        ec.gid = ++i
                        ec.wpname = workpieceRepository!!.findWorkpieceByWorkpieceId(craftBaseRepository!!.findCraftBaseByCraftBaseId(ec.craftBaseId).workpieceId).name
                        ec.wpid = pidd.toLong()
                        ec.name = procedureTypeRepository!!.findProcedureTypeByProcedureTypeId(procedureRepository!!.findProcedureByProcedureId(ec.procedureId).ptId).name
                        ec.time = procedureTypeRepository!!.findProcedureTypeByProcedureTypeId(procedureRepository!!.findProcedureByProcedureId(ec.procedureId).ptId).avgTime
                        ec.device = deviceRepository!!.findDeviceByDeviceId(procedureTypeRepository!!.findProcedureTypeByProcedureTypeId(procedureRepository!!.findProcedureByProcedureId(ec.procedureId).ptId).did).name

                        //ec.setExtendCraftByPreEcId(craftExtendRepository.findCraftExtendByProperty("CraftExtend", "p_id", String.valueOf(ec.getPbId())));
                        //ec.setExtendCraftByAftEcId(craftExtendRepository.findCraftExtendByProperty("CraftExtend", "p_id", String.valueOf(ec.getPaId())));
                        println("在循环2中~~~~~")

                        list_sum[i - 1].add(ec)
                        //				        System.out.println("在循环3中");
                    } while (ec.paId != 0.toLong())

                }
            } else {
                //获取第一个工序
                var i = 1
                val p = 1
                val q = 2
                ec.gid = i
                ec.wpname = workpieceRepository!!.findWorkpieceByWorkpieceId(craftBaseRepository!!.findCraftBaseByCraftBaseId(ec.craftBaseId).workpieceId).getName()
                ec.wpid = pidd.toLong()
                ec.name = procedureTypeRepository!!.findProcedureTypeByProcedureTypeId(procedureRepository!!.findProcedureByProcedureId(ec.procedureId).getPtId()).getName()
                ec.time = procedureTypeRepository!!.findProcedureTypeByProcedureTypeId(procedureRepository!!.findProcedureByProcedureId(ec.procedureId).getPtId()).getAvgTime()
                ec.device = deviceRepository!!.findDeviceByDeviceId(procedureTypeRepository!!.findProcedureTypeByProcedureTypeId(procedureRepository!!.findProcedureByProcedureId(ec.procedureId).getPtId()).getDid()).getName()

                //ec.setExtendCraftByPreEcId(craftExtendRepository.findCraftExtendByProperty("CraftExtend", "p_id", String.valueOf(ec.getPbId())));
                //ec.setExtendCraftByAftEcId(craftExtendRepository.findCraftExtendByProperty("CraftExtend", "p_id", String.valueOf(ec.getPaId())));
                println("在循环3中~~~~~")
                list_sum[i - 1].add(ec)

                if (ec.procedureId !== 0.toLong()) {
                    do {
                        ec = craftExtendRepository!!.findCraftExtendsByProcedureId(ec.procedureId)
                        val eclast = list_sum[i - 1][pidd - 1]
                        eclast.setExtendCraftByAftEcId(ec)
                        ec.setExtendCraftByPreEcId(eclast)

                        ec.setGid(++i)
                        ec.wpname = workpieceRepository!!.findWorkpieceByWorkpieceId(craftBaseRepository!!.findCraftBaseByCraftBaseId(ec.craftBaseId).workpieceId).getName()
                        //TODO：这块有点问题
                        //  ec.wpid(iid)
                        ec.name = procedureTypeRepository!!.findProcedureTypeByProcedureTypeId(procedureRepository!!.findProcedureByProcedureId(ec.procedureId).getPtId()).getName()
                        ec.time = procedureTypeRepository!!.findProcedureTypeByProcedureTypeId(procedureRepository!!.findProcedureByProcedureId(ec.procedureId).getPtId()).getAvgTime()
                        ec.device = deviceRepository!!.findDeviceByDeviceId(procedureTypeRepository!!.findProcedureTypeByProcedureTypeId(procedureRepository!!.findProcedureByProcedureId(ec.procedureId).getPtId()).getDid()).getName()

                        //ec.setExtendCraftByPreEcId(craftExtendRepository.findCraftExtendByProperty("CraftExtend", "p_id", String.valueOf(ec.getPbId())));
                        //ec.setExtendCraftByAftEcId(craftExtendRepository.findCraftExtendByProperty("CraftExtend", "p_id", String.valueOf(ec.procedureId)));
                        list_sum[i - 1].add(ec)
                        println("在循环4中")
                    } while (ec.procedureId !== 0.toLong())
                }
            }
            //TODO：这块有问题
            // Idd++
        }

        //list_sum.get(1).get(0).setEndtime(888);    //只可查到已有    数据
        //System.out.println("~~~~~~~~第一个工件第三道工序endTime~~~~~~~~"+list_sum.get(1).get(0).getEndtime()+"~~~~~~~~~~"
        //+list_sum.get(3).get(0).getExtendCraftByPreEcId().getExtendCraftByPreEcId().getEndtime());

        //输出list_sum
        /*for(int i = 0;i<list_sum.size();i++){
			System.out.println("*******工件名"+list_sum.get(i).get(0).getWpname()+":"+list_sum.size());
			for(CraftExtend ec:list_sum.get(i)){
				System.out.println("工序编号："+ec.getGid());
				System.out.println("工件名称："+ec.getWpname());
				System.out.println("工件编号："+ec.getWprocedureId());
				System.out.println("工序加工时间："+ec.getTime());
				System.out.println("工序的加工设备："+ec.getDevice());
			}
		}
		*/

        //为每道工序添加效率
        //添加效率
        var xl = 0.0
        var time = 0
        var totaltime = 0
        for (i in list_sum.indices) {
            val list = list_sum[i]
            for (j in list.indices) {
                //	int x=j-1;
                time = 0
                totaltime = 0
                val ec = list[j]
                xl = 0.0
                if (ec.pbId != 0.toLong()) {
                    System.out.println("ec.getPbId()!=0 " + ec.getPbId() + " ec.getTime():" + ec.time + "   ec.getName:" + ec.getName())
                    for (k in 0..i) {
                        time += list_sum[k][j].time
                        //		System.out.println("ec.getTime()--time:"+time);
                    }

                    //time = time + ec.getTime();
                    println("time:$time")
                    for (p in list_sum.indices) {
                        totaltime += list_sum[p][j].time
                        //		System.out.println("ec.getTime()--totaltime:"+totaltime);
                    }
                    xl = time.toDouble() / totaltime.toDouble()
                    //		System.out.println("效率:"+xl+time+totaltime);

                    list[j].xiaolv = xl

                } else {
                    time = ec.time
                    //					System.out.println("time:"+time);
                    for (p in list_sum.indices) {
                        totaltime += list_sum[p][j].time
                        //					System.out.println("ec.getTime()--totaltime:"+totaltime);
                    }

                    xl = time.toDouble() / totaltime.toDouble()
                    list[j].setXiaolv(xl)
                }
            }
        }


        //显示效率计算结果
        for (list in list_sum) {

            list.sort()  //效率排序
            println("List:")

        }
        /*
		for(List<CraftExtend> list : list_sum){


			System.out.println("排序结果");
			for(CraftExtend ec :list){

				if(ec.getExtendCraftByAftEcId()!=null&&ec.getExtendCraftByPreEcId()!=null){
					System.out.println("Device:"+ec.getDevice()+"~Name:"+ec.getName()+"~time:"+ec.getTime()+"~Gid:"+ec.getGid()+"~XiaoLv:"+ec.getXiaolv());
				}else if(ec.getExtendCraftByPreEcId()!=null){
					System.out.println("Device:"+ec.getDevice()+"~Name:"+ec.getName()+"~time:"+ec.getTime()+"~Gid:"+ec.getGid()+"~XiaoLv:"+ec.getXiaolv());
				}else{
					System.out.println("Device:"+ec.getDevice()+"~Name:"+ec.getName()+"~time:"+ec.getTime()+"~Gid:"+ec.getGid()+"~XiaoLv:"+ec.getXiaolv());
				}

			}
		}*/


        //
        //
        //效率计算全部正确。list_sum 正确
        //
        //


        //List<List> lists = new ArrayList<List>();// list1-list4的集合就是我们现在的list_sum
        //调度算法的接口
        //首先创建算法工厂类的对象
        val af = ArithmeticFactory.instance
        val arithmetic = af.creatArithmetic("B")

        println("运行的是效率算法的实例！")

        //算法参数对象的创建
        val aaii = FirstArithmeticParams.getInstance()
        aaii.DeviceNumber = DeviceNumber
        aaii.list_sum = list_sum
        aaii.listM_base_sum = listM_base_sum

        //给算法中传递算法参数对象
        val resultAA = arithmetic!!.sort(aaii)

        for (d in 1..DeviceNumber) {
            for (ec in resultAA.listM_base_sum[d - 1]) {
                val pdr = ProcessDispatchResult()
                //TODO: 这块不知道有没有完全解决
                pdr.mrpBase = bmrp
                w = workpieceRepository.findWorkpieceByWorkpieceId(ec.wpid)
                pdr.workpiece = w
                pdr.extendCraft = ec
                pdr.pdrPreEc = craftExtendRepository.findCraftExtendsByPbId((ec.pbId))
                pdr.pdrAftEc = craftExtendRepository.findCraftExtendsByProcedureId(ec.procedureId)
                pdr.pdrPreEcTime = ec.begintime
                pdr.pdrAftEcTime = ec.endtime
                pdr.baseDevice = deviceRepository.findDeviceByDeviceId(procedureTypeRepository.findProcedureTypeByProcedureTypeId(procedureRepository.findProcedureByProcedureId(ec.procedureId).ptId).did)
                //这里可以添加其他信息
                processDispatchResult.add(pdr)

            }
        }
        //获取每个设备的加工时间最大值
        val listdevicetime = ArrayList<DeviceTime>()

        var name = ""
        for (d in 1..DeviceNumber) {
            var max = 0
            for (ec in resultAA.listM_base_sum[d - 1]) {
                if (ec.getEndtime() > max) {
                    max = ec.getEndtime()
                    name = ec.getDevice()
                } else {

                }
            }
            val dt = DeviceTime()
            dt.deviceName = name
            dt.deviceTime = max
            listdevicetime.add(dt)
        }

        //获取每个工件的加工时间最大值
        val listcrafttime = ArrayList<CraftTime>()

        for (d in 0 until DeviceNumber) {

            val list = resultAA.listM_base_sum[d]
            for (j in list.indices) {

                var ec = list[j]

                if (listcrafttime.size == 0) {

                    //获取最后一道工序
                    if (ec.paId != 0.toLong()) {
                        do {
                            ec = ec.getExtendCraftByAftEcId()
                            //System.out.println("ec.paId ="+ec.paId );
                        } while (ec.paId != 0.toLong())
                        val ct = CraftTime()

                        ct.craftName = ec.getWpname()
                        ct.craftTime = ec.getEndtime()
                        //	System.out.println("ec.getWpname()="+ec.getWpname()+"~~~~~~"+"ec.getEndtime()"+ec.getEndtime());
                        listcrafttime.add(ct)
                    } else {
                        val ct = CraftTime()
                        ct.craftName = ec.getWpname()
                        ct.craftTime = ec.getEndtime()
                        //	System.out.println("ec.getWpname()="+ec.getWpname()+"~~~~~~"+"ec.getEndtime()"+ec.getEndtime());

                        listcrafttime.add(ct)
                    }

                } else {
                    var u = true
                    for (ctt in listcrafttime) {
                        if (ctt.craftName.equals(ec.getWpname())) {
                            u = false
                        } else {

                        }
                    }
                    if (u) {
                        if (ec.paId != 0.toLong()) {
                            do {
                                ec = ec.getExtendCraftByAftEcId()
                            } while (ec.paId != 0.toLong())
                            val ct = CraftTime()
                            ct.craftName = ec.getWpname()
                            ct.craftTime = ec.getEndtime()
                            //		System.out.println("ec.getWpname()="+ec.getWpname()+"~~~~~~"+"ec.getEndtime()"+ec.getEndtime());
                            listcrafttime.add(ct)
                        } else {
                            val ct = CraftTime()
                            ct.craftName = ec.getWpname()
                            ct.craftTime = ec.getEndtime()
                            //		System.out.println("ec.getWpname()="+ec.getWpname()+"~~~~~~"+"ec.getEndtime()"+ec.getEndtime());

                            listcrafttime.add(ct)
                        }
                    }

                }
            }

            //				System.out.println("max1"+max1);
        }

        /*	 for(int x=0.toLong();x<resultAA.getListM_base_sum().size();x++){
			 List<CraftExtend> ecList=resultAA.getListM_base_sum().get(x);
			 List<CraftExtend> listcjend.add(ecList);
		 }*/

        application!!.setAttribute("listdevicetime", listdevicetime)
        application!!.setAttribute("listcrafttime", listcrafttime)
        application!!.setAttribute("listpdr", processDispatchResult)
        /* for(int w=0.toLong();w<resultAA.getListM_base_sum().size();w++){

        	for(int e=0.toLong();e<resultAA.getListM_base_sum().get(w).size();e++){

        		CraftExtend now=resultAA.getListM_base_sum().get(w).get(e);
        		System.out.println("now.getDevice()"+now.getDevice()+"~~~~~~"+"now.getBegintime()"+now.getBegintime()+"~~~~~~"+"now.getEndtime()"+now.getEndtime());
        	}

        }*/
        gantt_out_ef.ganttshouw(resultAA.listM_base_sum, listdevicetime)
        return "success"

    }

    companion object {

        /**
         *
         */
        val serialversionuid = -1660626512992304833L
    }
}
