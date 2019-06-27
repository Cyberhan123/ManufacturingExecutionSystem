package cn.hselfweb.mes.arithmetic.GA.copy;

import cn.hselfweb.mes.arithmetic.GA.linkedlist.DoubleLinkedList;
import cn.hselfweb.mes.enity.CraftExtend;
import cn.hselfweb.mes.enity.Device;

import java.util.*;
//
// class JobScheduleIndividualTest extends Individual {
//
//	private int chromlength;//染色体最大位数
////	private int chromlengthtrue;//染色体真实位数
//	private int wpnum;//工件数量
//	private int gxnum;//工序数量最大值
//	//private int jbchrom[];
//	private int T[][];
//	private int JM[][];//后被赋值为0
//	private int JMM[][];
////	private CraftExtendService ceService;
//	private List<DoubleLinkedList> dlllist;
//	private List<Device> listcj;
//	private List<List<CraftExtend>> listgx;
//	private List<List<CraftExtend>> listgxx;
//	private Map<String,Integer> cjmap;
////	private int fitness = 100000000;
//	private int fitness = 0;
//	JobScheduleIndividualTest(int JM1[][],List<DoubleLinkedList> dlllist1,int TT[][],List<Device> listcj1,Map<String,Integer> cjmap1,List<List<CraftExtend>> lists){
//		wpnum = JM1.length;
//		gxnum = JM1[0].length;
//		JM = copyJM(JM1);
//		chromlength = getChromlength();
//		dlllist = dlllist1;
//		listgx = lists;
//		listgxx = lists;
//		T = TT;
//		chromIndividual = generateIndividual();
//		listcj = listcj1;
//		cjmap = cjmap1;
////		chromlengthtrue = 1;
//	}
//
////获取真实的染色体位数
//	public int getChromlength(){
//		int length = 0;
//		for(int i=0;i<wpnum;i++){
//			int k = 0;
//			for(int j=0;j<gxnum;j++){
//				if(JM[i][j]!=0){
//					k++;
//				}
//			}
//			length = length+k;
//		}
//		return length;
//	}
//
//
////对二维数组进行copy,因为在生成染色体时会将该数组置为0
//	public int[][] copyJM(int Arr[][]){
//	    int JM1[][] = new int[wpnum][gxnum];
//		for(int i=0;i<wpnum;i++){
//			for(int j=0;j<gxnum;j++){
//				JM1[i][j] = Arr[i][j];
//			}
//		}
//		return JM1;
//	}
// //对数组进行copy,因为在生成染色体时会将该数组置为0
//		public int[] copyArr(int Arr[]){
//		    int JM1[] = new int[Arr.length];
//			for(int i=0;i<Arr.length;i++){
//				JM1[i] = Arr[i];
//			}
//			return JM1;
//		}
// //对listcj进行copy,因为在每次解码时都需要一个全新的listcj
//		public List<Device> copyList(List<Device> listcj){
//		    List<Device> listcjcopy = new ArrayList<Device>();
//			for(Device bd:listcj){
//		    	Device bdd = new Device();
//		    	bdd.setName(bd.getName());
//		    	listcjcopy.add(bdd);
//		    }
//			return listcjcopy;
//		}
////	生成随机个体
//	public int[] generateIndividual(){
//		int c[] = new int[chromlength];
//		JMM = copyJM(JM);
//
//		String s = "";
//		do{
//		   JMM = copyJM(JM);
//	       c = codingVariable();
////	       System.out.println("Check"+CheckChromosome(c));
//		}while(!CheckChromosome(c));
//
////		System.out.println("chenggong");
//
//		for(int in=0;in<chromlength;in++){
//			s= s+c[in];
//		}
//		System.out.println("生成染色体成功");
//		System.out.println(s);
//
//		return c;
//	}
//
//
//	//染色体编码（返回int数组类型）
//	public int[] codingVariable(){
//		int i = 0;
//		int chrom[] = new int[chromlength];//染色体的数组类型
//		int k=0;
////		for(int k=0;k<chromlength;k++){//标识位，染色体标识
//	    do{
//			 i = new Random().nextInt(wpnum) + 1;//随机产生整数(1-wpnum)代表工件
//		     if(JMM[i-1][0]!=0){//工件的工序在集合中不为空
////		    	 System.out.print(JMM[i-1][0]);
//
//		    	 chrom[k] = i;
//		    	 k++;
////		    	 chromlengthtrue++;
//		    	 //将JM序列中的各元素一次左移一位
//				    for(int kk=0;kk<gxnum-1;kk++){
//				    	JMM[i-1][kk]=JMM[i-1][kk+1];
//				    }
//				    JMM[i-1][gxnum-1] = 0;//最后一位用0填充
//		     }
//	    }while(k!=chromlength);
////		}
//
//		return chrom;
//	}
//
//	//染色体编码（返回int数组类型）
//	public String changeToString(int chrom[]){
//		//数组类型的染色体变成string类型
//		String s = new String();
//		for(int p=0;p<chromlength;p++){
//			if(chrom[p]!=0){
//			s=s+chrom[p];
//			}
//		}
//		return s.toString();
//	}
//
////	//车间设备信息初始化(从数据库中获取相关车间信息)改成初始化时由构造函数输入
////	public List<CheJian> CheJianInit(){
////		List<CheJian> listcj = new ArrayList<CheJian>();
////		CheJian cj1 = new CheJian();
////		CheJian cj2 = new CheJian();
////		CheJian cj3 = new CheJian();
////		CheJian cj4 = new CheJian();
////		CheJian cj5 = new CheJian();
////		CheJian cj6 = new CheJian();
////		CheJian cj7 = new CheJian();
////		CheJian cj8 = new CheJian();
////		listcj.add(cj1);
////		listcj.add(cj2);
////		listcj.add(cj3);
////		listcj.add(cj4);
////		listcj.add(cj5);
////		listcj.add(cj6);
////		listcj.add(cj7);
////		listcj.add(cj8);
////		listcj.add(cj3);
////		return listcj;
////	}
//
////	//设备初始化编号转化从test中获取
////	public Map<Integer,CheJian> CheJianNum(List<CheJian> listcj){
////
////		int cjnum = 1;
////		for(CheJian cj:listcj){
////			cjmap.put(cjnum,  cj);
////			cjnum++;
////		}
////
////		return cjmap;
////	}
//
//	//染色体解码（返回车间集合排序）
//	public List<Device> decodeGene(){
//		for(List<CraftExtend> lce:listgx){
//			for(CraftExtend ce:lce){
////				ce.setBendtime(0);
//				ce.setEndtime(0);
//			}
//
//		}
//
////		//讲设备中的工序加工序列清空
////		for(Device bd:listcj){
////			if(bd.getListgx().size()!=0){
////				for(int i = 0;i<bd.getListgx().size();i++){
////					System.out.println(bd.getListgx().get(i).getGname());
////					bd.getListgx().remove(i);
////				}
////			}
//////			bd.getListgx().removeAll(listgx);
//////			System.out.println("bdlistgx的size"+bd.getListgx().size());
////		}
//		List<Device> listcjcopy = copyList(listcj);
//		JMM = copyJM(JM);
//		String ss = "";
//		for(int in=0;in<chromlength;in++){
//			ss= ss+chromIndividual[in];
//		}
//
////        System.out.println("解码染色体为："+ss);
//
//		int k[] = new int[wpnum];
//		int g = 1;
//
//		//GongXu lastgx = new GongXu();//染色体位上的前一道工序
////		List<CheJian> listcj = CheJianInit();
//		//List<GongXu> listgx = new ArrayList<GongXu>();//工序的集合信息
// 		for(int i=0;i<wpnum;i++){//初始化标示位集合
// 			k[i]=0;
// 		}
// 		do{
// 			if(g==1){//染色体编码的第一位
//
// 				int wp = chromIndividual[g-1];//工件号
// 	 			int dnum = JMM[wp-1][k[wp-1]];//机器号
// 	 			k[wp-1] = k[wp-1]+1;
// 	 			CraftExtend gx = new CraftExtend();
// 	 			//将限制条件加入车间集合中(这里加入限制信息)
//
// 	 			String dbname = changeToDevice(dnum);
//// 	 			System.out.println("dbname"+dbname);
//// 	 			System.out.println("adsadswp"+wp);
// 	 			//选取特定的工序(与实际工序进行关联)
// 	 			for(List<CraftExtend> list:listgx){
// 	 				if(list.get(0).getWpid()==wp){
// 	 					for(int i=0;i<list.size();i++){
// 	 						if(list.get(i).getGid()==k[wp-1]){
// 	 							gx = list.get(i);
//// 	 							System.out.println(gx.getWpname()+gx.getGid());
// 	 							break;
// 	 						}
// 	 					}
// 	 				}
// 	 			}
// 	 			if(gx.getWpid()==0){
// 						System.out.println("错误信息1");
//	 	 		    }
//// 	 			System.out.println("gx"+gx.getWpid()+gx.getGxid());
// 	 			gx.setTime(T[wp-1][k[wp-1]-1]);
//// 	 		    System.out.println(gx.getTime());
// 	 			//解码时对工序进行初次排序，确保同一工件工序的关联顺序
// 	 			for(Device cj:listcjcopy){
// 	 				if(cj.getName().equals(dbname)){
//// 	 					System.out.println(cj.getBdName());
// 	 					//	gx.setFirst(true);//第一道工序
// 	 						gx.setBegintime(0);
// 	 						gx.setEndtime(gx.getTime());
// 	 						cj.getListgx().add(gx);
// 	 						break;
// 	 				}
// 	 			}
// 	 			g++;
// 			}else{//其余的染色体位
//
// 				int wp = chromIndividual[g-1];//工件号
//
// 	 			int dnum = JMM[wp-1][k[wp-1]];//机器号
// 	 			k[wp-1] = k[wp-1]+1;
// 	 			CraftExtend gx = new CraftExtend();
// 	 			//将限制条件加入车间集合中(这里加入限制信息)
// 	 			String dbname = changeToDevice(dnum);
//// 	 			System.out.println("dbname"+dbname);
//// 	 			System.out.println("adsadswp"+wp);
// 	 		    //选取特定的工序(与真实工序工序之间的关联)
// 	 			for(List<CraftExtend> list:listgx){
// 	 				if(list.get(0).getWpid()==wp){
// 	 					for(int i=0;i<list.size();i++){
// 	 						if(list.get(i).getGid()==k[wp-1]){
// 	 							gx = list.get(i);
// 	 							break;
// 	 						}
// 	 					}
// 	 			    }
// 	 		    }
//
// 	 			if(gx.getWpid()==0){
//
//					System.out.println("错误信息2");
//					String s = "";
//					for(int in=0;in<chromlength;in++){
//						s= s+chromIndividual[in];
//					}
////                    System.out.println(s);
////					System.out.println(g);
// 	 		    }
//	 			//System.out.println("gx"+gx.getWpid()+gx.getName());
// 	 			gx.setTime(T[wp-1][k[wp-1]-1]);
//
// 	 			//解码时对工序进行排序，确保同一工件工序的关联顺序
// 	 			for(Device cj:listcjcopy){
// 	 				if(cj.getName().equals(dbname)){
//// 	 					if(cj.getListgx().size()==0){
//// 	 						gx.setFirst(true);//第一道工序
//// 	 						gx.setBegintime(0);
//// 	 						gx.setEndtime(gx.getBegintime()+gx.getGxtime());
//// 	 						cj.getListgx().add(gx);
//// 	 						break;
//// 	 					}else{
//// 	 						GongXu currentLast = cj.getListgx().get(cj.getListgx().size()-1);
//// 	 						gx.setBegintime(currentLast.getEndtime());
//// 	 						gx.setEndtime(currentLast.getEndtime()+gx.getGxtime());
//// 	 						cj.getListgx().add(gx);
//// 	 	 					break;
// 	 					    int cjet = CheJianEndTime(cj);
// 	 					    int gxet = GongXuEndTime(gx);
// 	 					    int cet = ConnectionEndTime(gx);
//// 	 					    System.out.println("现阶段车间的结束时间cjet"+cjet);
//// 	 					    System.out.println("前道工序的结束时间gxet"+gxet);
//// 	 					    System.out.println("有关联关系的结束时间cet"+cet);
// 	 					    gx.setBegintime(MaxInThree(cjet,gxet,cet));
//// 	 					    System.out.println("MaxInThree"+MaxInThree(cjet,gxet,cet));
// 	 					    gx.setEndtime(gx.getBegintime()+gx.getTime());
//// 	 					    System.out.println("2:"+gx.getEndtime());
// 	 					    cj.getListgx().add(gx);
////	 					    System.out.println(cj.getListgx().size());
// 	 					    break;
// 	 					}
// 	 				}
// 	 	//		lastgx = gx;
// 	 			g++;
// 	 			}
//// 			}
// 		}while(g<=chromIndividual.length);
//// 		for(Device bd:listcj){
////			List<CraftExtend> listec = bd.getListgx();
////			System.out.println(listec.size());
//////			for(CraftExtend ec:listec){
//////				System.out.println(ec.getEcName()+ec.getGid());
//////			}
////		}
//		return listcjcopy;
//	}
//
//
//
//	//CheJian设备上的前一道工序的结束时间
//	public int CheJianEndTime(Device cj){
//		if(cj.getListgx().size()==0){
//			return 0;
//		}else{
//			return cj.getListgx().get(cj.getListgx().size()-1).getEndtime();
//		}
//	}
//
//	//同工件的前一道工序的结束时间
//
//	public int GongXuEndTime(CraftExtend gx){
///*		System.out.println("gx.gid="+gx.getGid());
//		System.out.println("gx.gname="+gx.getName());
//		System.out.println("gx.wpname="+gx.getWpname());
//		System.out.println("gx.pbid="+gx.getPbId());*/
//		if(gx.getGid()==1){
//			return 0;
//		}else{
////			System.out.println("=====3:"+listgx.get(gx.getWpid()-1).get(gx.getGid()-1).getEndtime());
////			System.out.println(gx.getGid()-1);
////			System.out.println(gx.getWpid()-1);
////			System.out.println(listgx.get(gx.getWpid()-1).get(gx.getGid()-1).getName());
//			return listgx.get(gx.getWpid()-1).get(gx.getGid()-2).getEndtime();
//					//gx.getExtendCraftByPreEcId().getEndtime();
//		}
//	}
//	//关联关系中的前道工序的结束时间
//		public int ConnectionEndTime(CraftExtend gx){
//			CraftExtend cgx = hasConnection(gx);
//			if(cgx == null){
//				return 0;
//			}else{
//				return cgx.getEndtime();
//			}
//		}
//
//		//从关联关系集合中查看该工序是够存在关联关系,若存在返回前道关联工序，如不存在则返回null
//			public CraftExtend hasConnection(CraftExtend gx){
//				CraftExtend back = null;
//				if(dlllist.size()==0){
//
//				}else{
//				for(int k = 0;k<dlllist.size();k++){//对关联关系限制条件进行处理
//					   DoubleLinkedList dll = dlllist.get(k);
//				       //对限制信息进行数字转换
////				       for(int kk=0;kk<dll.size();kk++){//对所有的gx进行判断
////				    	   CraftExtend current = (CraftExtend)dll.get(kk);
//					   CraftExtend current = (CraftExtend)dll.get(1);
//					   CraftExtend last = (CraftExtend)dll.get(0);
////				       if((current.getWpid()==gx.getWpid())&&(current.getGid()==gx.getGid())&&(kk>0)){
////					   System.out.println("gxwpid"+gx.getWpid());
////					   System.out.println("gxgid"+gx.getGid());
////					   System.out.println("currentwpid"+current.getWpid());
////					   System.out.println("currentgid"+current.getGid());
//					   if(current.getWpid().equals(gx.getWpid())){
//					   //有关联关系
//						   if(current.getGid()==gx.getGid()){
//							   System.out.println("判断成功！！！！,有前道关联工序");
////							   for(List<CraftExtend> list:listgx){
////				 	 				if(list.get(0).getWpid().equals(gx.getWpid())){
////				 	 					for(int i=0;i<list.size();i++){
////				 	 						if(list.get(i).getGid()==gx.getGid()){
////				 	 							back = list.get(i);
//////				 	 							System.out.println(gx.getWpname()+gx.getGid());
////				 	 							break;
////				 	 						}
////				 	 					}
////				 	 				}
////				 	 			}
//							   for(List<CraftExtend> list:listgx){
//				 	 				if(list.get(0).getWpid().equals(last.getWpid())){
//				 	 					for(int i=0;i<list.size();i++){
//				 	 						if(list.get(i).getGid()==last.getGid()){
//				 	 							back = list.get(i);
////				 	 							System.out.println(gx.getWpname()+gx.getGid());
//				 	 							break;
//				 	 						}
//				 	 					}
//				 	 				}
//				 	 			}
////					    	   back = (CraftExtend)dll.get(0);
//							   System.out.println("back"+back.getWpname()+"sda"+back.getGname());
////					    	   System.out.println("back.endtime"+back.getEndtime());
//						   }else{
//
//						   }
//				    	}else{
//				          //无关联关系
//				    	  //返回空对象
//				    	}
////				       }
//				}
//			 }
//				return back;
//			}
//
//
//	//寻求3个数中的最大值
//	public int MaxInThree(int first,int second,int third){
//		int temp = 0;
//	    temp = Math.max(first, second);
//        temp = Math.max(temp, third);
////        System.out.println("first"+first);
////        System.out.println("second"+second);
////        System.out.println("third"+third);
//        return temp;
//	};
//
//	//机器号切换(返回机器类型名)
//	public String changeToDevice(int dnum){
//    //对于iterator中的遍历it.next不可以多次，将会出现nullpointexception
//		String next = null;
//		Iterator<String> itkey= cjmap.keySet().iterator();
//
//		while(itkey.hasNext()){
////			System.out.println(itkey.next());
////			System.out.println(cjmap.get(itkey.next()));
//		  next = itkey.next();
//		  if(cjmap.get(next)==dnum){
//
//			  break;
//		  }else{
//
//		  }
//		}
//		return next;
//	}
//
//
//	//变异操作
//	public void bianyi(){
//		int temp = 0;
//		int aposition = 0;
//		int awp = 0;
//		int bposition = 0;
//		int bwp = 0;
//		int acj = 0;
//		int bcj = 0;
//		int chromtemp[] = copyArr(chromIndividual);
//		System.out.println("变异前：");
//        toString();
//	do{
//		int a = 0;
//		int b = 0;
//        do{
//    		chromtemp = copyArr(chromIndividual);
//        	temp = 0;
//    	    aposition = 0;
//    	    awp = 0;
//    		bposition = 0;
//    		bwp = 0;
//    	    acj = 0;
//    		bcj = 0;
//        	a = new Random().nextInt(chromlength) + 1;//随机产生整数(1-chrom.length)
//    	    b = new Random().nextInt(chromlength) + 1;//随机产生整数(1-chrom.length)
////    		System.out.println("a"+a);
////    		System.out.println("b"+b);
//			temp = chromtemp[a-1];
////			第几道工序
//			aposition = findposition(chromtemp,a);
////			System.out.println("aposition"+aposition);
//		    awp = temp;
//
//			bposition = findposition(chromtemp,b);
//			bwp = chromtemp[b-1];
//			chromtemp[a-1] = chromtemp[b-1];
//			chromtemp[b-1] = temp;
////			System.out.println("bposition"+bposition);
////			System.out.println(bwp);
//
//        }while((a==b)||(awp==bwp));
//		//可行解判断，变异后判断该工件是否能在该机器上加工
//		acj = JMM[awp-1][aposition-1];
//    	bcj = JMM[bwp-1][bposition-1];
//	}while(acj != bcj);
//		//int dnum = JM[wp-1][k[wp-1]];//机器号
////	    System.out.println("acj"+acj);
////	    System.out.println("bcj"+bcj);
//		System.out.println("变异后：");
//		chromIndividual = copyArr(chromtemp);
//		toString();
//	}
//    //染色体中第几道工序
//	public int findposition(int chrom1[],int a){
////		*******
//		int temp = chrom1[a-1];
////		System.out.println("temp"+temp);
//		int id = 0;
//		for(int i = 0;i<a;i++){
//			if(chrom1[i]==temp){
//				id++;
//			}
//		}
//		return id;
//	}
//
//    //工件工序在染色体上的位置
//	public int findpositionofcraft(int chrom[],int temp,int k){
//        int count = 1;//用于计算temp次数
//        int re = 0;//返回值
//        for(int p=0;p<chrom.length;p++){
//           if(chrom[p] == temp){
//        	   if(k == count){
//        		   re = p;
//        		   break;
//        	   }else{
//        		   count++;
//        	   }
//           }
//        }
//		return re;
//	}
//
//
//	//交换操作
//	public void crossover1(Individual in1,Individual in2){
//		String s1 = "";
//		String s2 = "";
//        for(int iiiiii=0;iiiiii<chromlength;iiiiii++){
//        	s1 = s1+in1.chromIndividual[iiiiii];
//        	s2 = s2+in2.chromIndividual[iiiiii];
//        }
//        System.out.println("交换染色体前");
//        System.out.println(s1);
//		System.out.println(s2);
//		int inn1[] = Cycle(in1.chromIndividual,in2.chromIndividual,1);
//		int inn2[] = Cycle(in1.chromIndividual,in2.chromIndividual,2);
//		in1.chromIndividual = inn1;
//		in2.chromIndividual = inn2;
//		System.out.println("交换染色体成功！");
//		String str1 = "";
//		String str2 = "";
//		for(int in=0;in<chromlength;in++){
//			str1 = str1+inn1[in];
//			str2 = str2+inn2[in];
//		}
//		System.out.println("交换染色体后！");
//		System.out.println(str1);
//		System.out.println(str2);
//	}
//
//	//(Cycle Crossover)循环交叉操作
//	public int[] Cycle(int chrom1[],int chrom2[],int ii){
//		int chromafter[] = new int[chromlength];
//
//		int temp = 0;
//		int bs = 0;
//		if(ii==1){
//
//			chromafter[bs] = chrom1[bs];
//			int k[] = new int[wpnum];
//			for(int i=0;i<wpnum;i++){
//				k[i] = 1;
//			}
//			do{
//			temp = chrom2[bs];
////			System.out.println(findposition(chrom1,temp));
////			for(int i=1;i<chromlength;i++){
////
////				if((chrom1[i]==temp)&&(findposition(chrom1,temp)==k[temp-1])){
////					chromafter[i] = temp;
////					bs = i;
////					k[temp-1]++;
////					break;
////				}
////			}
//
//		    bs = findpositionofcraft(chrom1, temp, k[temp-1]);
//		    k[temp-1]++;
//		    chromafter[bs] = chrom1[bs];
//			}while(chrom2[bs]!=chromafter[0]);
////			chromafter[findpositionofcraft(chrom1, chrom1[0], 2)] = chrom2[0];
//			for(int i=1;i<chromlength;i++){
//				if(chromafter[i]==0){
//					chromafter[i] = chrom2[i];
//				}
//			}
//
//		}else{
//
//			chromafter[bs] = chrom2[bs];
//			int k[] = new int[wpnum];
//			for(int i=0;i<wpnum;i++){
//				k[i] = 1;
//			}
//			do{
//			temp = chrom1[bs];
////			for(int i=1;i<chromlength;i++){
////				if((chrom2[i]==temp)&&(findposition(chrom2,temp)==k[temp-1])){
////					chromafter[i] = temp;
////					bs = i;
////					break;
////				}
////
////			}
//			bs = findpositionofcraft(chrom2, temp, k[temp-1]);
//		    k[temp-1]++;
//		    chromafter[bs] = chrom2[bs];
//			}while(chrom1[bs]!=chromafter[0]);
////			chromafter[findpositionofcraft(chrom2, chrom2[0], 2)] = chrom1[0];
//			for(int i=1;i<chromlength;i++){
//				if(chromafter[i]==0){
//					chromafter[i] = chrom1[i];
//				}
//			}
//		}
//
//		return chromafter;
//
//	}
////	输出染色体字符串
//	public String toString(){
//		String str = "";
//		for(int in=0;in<chromlength;in++){
//			str= str+chromIndividual[in];
//		}
//		System.out.println(str);
//		return 	str;
//	}
//
//	//计算个体适应度
//	public int calFitness(){
////		int fitness = 1000000000;
////		List<CheJian> listcjend = decodeGene();
////		for(CheJian cj:listcjend){
////			if(fitness == 0){
////				fitness = cj.getListgx().get(cj.getListgx().size()-1).getEndtime();
////			}else{
////				if(fitness<cj.getListgx().get(cj.getListgx().size()-1).getEndtime()){
////					fitness = cj.getListgx().get(cj.getListgx().size()-1).getEndtime();
////				}else{
////
////				}
////			}
////		}
//		fitness = 0;
//
//		List<Device> listcjend = decodeGene();
//		for(Device cj:listcjend){
//			if(fitness < cj.getListgx().get(cj.getListgx().size()-1).getEndtime()){
//				fitness = cj.getListgx().get(cj.getListgx().size()-1).getEndtime();
//			}else{
//
//			}
//		}
////		System.out.println("fitness"+fitness);
//		return fitness;
//
//	}
//
////判断该染色体是否为可行性解
//	private boolean CheckChromosome(int chrom[]){
//		int c = 1;
//		if(dlllist.size()==0){
//			System.out.println("没有限制条件！");
//		}else{
//		for(int k = 0;k<dlllist.size();k++){//对关联关系限制条件进行处理
//			   DoubleLinkedList dll = dlllist.get(k);
//
//		       //对限制信息进行数字转换
////		       for(int kk=0;kk+1<dll.size();kk++){//对所有的gx进行判断
//
//		    	   int positiongx1 = 0;
//		    	   int positiongx2 = 0;
//		    	   int t = 0;
//		    	   int tt = 0;
//		    	    CraftExtend gx1 = (CraftExtend)dll.get(0);
////		    	   System.out.println(kk+"gx1"+gx1.getWpname()+gx1.getGxid());
//		    	   CraftExtend gx2 = (CraftExtend)dll.get(1);
////		    	   System.out.println(kk+1+"gx2"+gx2.getWpname()+gx2.getGxid());
//		    	   int wpid1 = gx1.getWpid();
//		    	   int wpid2 = gx2.getWpid();
//		    	   int gxid1 = gx1.getGid();
//		    	   int gxid2 = gx2.getGid();
//		    	   for(int f=0;f<chrom.length;f++){
//		    		   if(chrom[f]==wpid1){
//		    			  t++;
//		    			  if(t == gxid1){
//		    				  positiongx1 = f;
//		    			  }
//		    		   }
//		    		   if(chrom[f]==wpid2){
//		    			  tt++;
//		    			  if(tt == gxid2){
//		    				  positiongx2 = f;
//		    			  }
//		    		   }
//		    	   }
//		    	   //判断是否符合关联关系
//		    	   if(positiongx1<positiongx2){
//		    		   c = c*1;
//		    	   }else{
//		    		   c = c*0;
//		    	   }
////		    	 }
//			}
//		}
//		if(c==1){
//			System.out.println("染色体编码有效！");
//			return true;
//		}else{
//			return false;
//		}
//	}
//
////相等返回0，不相等返回-1
//	public boolean IsCommon(Individual js) {
//		int jb = 1;
//		for(int j=0;j<chromlength;j++){
//		  if(this.chromIndividual[j]==js.chromIndividual[j]){
//			  jb = jb*1;
//		  }else{
//			  jb = jb*0;
//		  }
//		}
//		if(jb==1){
//			return true;
//		}else{
//			return false;
//		}
//
//	}
//
////	public CraftExtendService getCeService() {
////		return ceService;
////	}
////
////	public void setCeService(CraftExtendService ceService) {
////		this.ceService = ceService;
////	}
//
//
//}
