package cn.hselfweb.mes.arithmetic.GA;

import cn.hselfweb.mes.enity.Device;

import java.util.List;


abstract class Individual implements Cloneable{
	
	protected int genelen;//基因长度
	public int[] chromIndividual;
//	protected double fitness;//适应度
//	protected double targetValue;//目标函数值
	public abstract String changeToString(int chrom[]);//染色体编码（返回int数组类型）
	
//	public abstract List<CheJian> CheJianInit();//车间设备信息初始化(从数据库中获取相关车间信息)
		
//	public abstract Map<Integer,CheJian> CheJianNum(List<CheJian> listcj);//设备初始化编号转化
	
	public abstract List<Device> decodeGene();	//染色体解码（返回车间集合排序）
	
	public abstract void bianyi();	//变异操作

	public abstract void crossover1(Individual pop,Individual pop2);//交换操作
	
	public abstract int calFitness();//计算个体适应度
	
	public abstract String toString();//计算个体适应度
	
//	public void mutateSingleBit(int index){//变异单个位的遗传码
//		String gene , gn;
//		gene = chrom.getGene(index , index);
//		gn = gene.equals("0") ? "1":"0";
//		chrom.setGene(index , index , gn);
//	}
	public abstract boolean IsCommon(Individual js) ;
	
	@Override
	public Object clone(){
		Individual indv = null;
		
		try{
			indv = (Individual)super.clone();
		}catch(CloneNotSupportedException e ){
			System.out.println(e.getMessage());
		}
		
		return indv;
	}
	
}