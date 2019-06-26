package cn.hselfweb.mes.arithmetic.GA.copy;

import cn.hselfweb.mes.enity.CraftExtend;
import cn.hselfweb.mes.enity.Device;
import cn.hselfweb.mes.arithmetic.GA.linkedlist.DoubleLinkedList;

import java.util.List;
import java.util.Map;

public class PopulationTest {
    private int generation; //种群的代数
    private int size;            //群体大小
    private Individual[] pop;   //种群
    private double averageFitness;    //平均适应度
    private double[] relativeFitness;    //相对适应度
    private int JJMM[][];//机器顺序阵
    private int TT[][];//加工时间阵
    private List<DoubleLinkedList> listddl;
    private List<Device> listcj;
    private List<List<CraftExtend>> listgx;
    //	private List<CheJian> listcj = new ArrayList<CheJian>();
    Individual bestIndividual;//当前代适应度最好的个体
    Individual worstIndividual;//当前代适应度最差的个体
    Individual currentBest;//到目前代为止最好的个体
    Individual currentWorst;//到目前代为止最坏的个体
    private int worstIndex;//worstIndividual对应的数组下标
    private Map<String, Integer> cjmap;


    public PopulationTest(int size, int JM[][], int T[][], List<Device> listcj1, Map<String, Integer> cjmap1, List<List<CraftExtend>> list_sum, List<DoubleLinkedList> listddl1) {
        this.generation = 0;
        this.size = size;
        this.averageFitness = 0;
        this.relativeFitness = new double[size];
        this.JJMM = JM;
        this.TT = T;
        this.listcj = listcj1;

        this.cjmap = cjmap1;
        this.listgx = list_sum;
        this.listddl = listddl1;
//		this.pop = new Individual[size];
        this.pop = initPopulation();

    }

    //初始化种群
    public Individual[] initPopulation() {
        pop = new Individual[size];
        int h = 0;
        int kk = 1;
        do {
            if (h == 0) {
                pop[h] = new JobScheduleIndividualTest(JJMM, listddl, TT, listcj, cjmap, listgx);
                h++;
//			 System.out.println("h"+h); 
            } else {

                do {
                    pop[h] = new JobScheduleIndividualTest(JJMM, listddl, TT, listcj, cjmap, listgx);
                    //判断是否为重复个体
                    kk = 1;
                    for (int i = 0; i < h; i++) {
                        if (pop[h].IsCommon(pop[i])) {
                            kk = kk * 1;
                        } else {
                            kk = kk * 0;
                        }
                    }
                } while (kk == 1);
                h++;
//			System.out.println("h"+h); 
            }
        } while (h < 10);
        //判断是否有重复的个体
//		int i = 0;
//		for(Individual inn:pop){
//			System.out.println(i+"号染色体："+inn.toString());
//			i++;
//		}
//		findBestAndWorstIndividual();
        System.out.println("初始化种群集合：");
        for (Individual p : pop) {
            String s = "";
            for (int in = 0; in < p.chromIndividual.length; in++) {
                s = s + p.chromIndividual[in];

            }
//		  System.out.println(s);
            int i = p.calFitness();
            System.out.println("染色体" + p.toString() + "fitness" + p.calFitness());
//		  System.out.println(p.calFitness());
        }
        System.out.println("初始种群结束");
        findBestAndWorstIndividual();
        return pop;

    }


    //----------------------------------------------------
    //比例选择
    public void select() {
        double[] rouletteWheel; //赌盘
        Individual[] childPop = new Individual[size];

        calRelativeFitness();

        //产生赌盘
        rouletteWheel = new double[size];
        rouletteWheel[0] = relativeFitness[0];
        for (int i = 1; i < size - 1; i++) {
            rouletteWheel[i] = relativeFitness[i] + rouletteWheel[i - 1];
        }
        rouletteWheel[size - 1] = 1;

        //进行赌盘选择,产生新种群
        for (int i = 0; i < size; i++) {
            double rnd = rand();
            for (int j = 0; j < size; j++) {
                if (rnd < rouletteWheel[j]) {
                    childPop[i] = pop[j];
                    break;
                }
            }
        }

        for (int i = 0; i < size; i++) {
            pop[i] = childPop[i];
        }
        System.out.println("选择后的种群集合：");
        for (Individual p : pop) {
            System.out.println("染色体" + p.toString() + "fitness" + p.calFitness());

        }
        System.out.println("选择种群结束");
        findBestAndWorstIndividual();
        //return 	childPop;
    }

    //求总适应度
    private double calTotalFitness() {
        double total = 0;
        for (int i = 0; i < size; i++)
            total += pop[i].calFitness();
        return total;
    }

    //计算相对适应度
    public double[] calRelativeFitness() {
        double totalFitness = calTotalFitness();
        for (int i = 0; i < size; i++) {
            relativeFitness[i] = (10000 - pop[i].calFitness()) / ((10000 * size) - totalFitness);
        }
        return relativeFitness;
    }
    //================================


    //------------------------------------------------------
    //单点交叉
    public void crossover() {
//		for(int i = 0 ; i < size/2*2; i+=2){
        for (int i = 0; i < size - 1; i += 2) {
//			int rnd;
//			//随机两两配对
//			rnd = rand(i , size);
//
//			if(rnd != i)
//				exchange(pop , i , rnd);
//				
//			rnd = rand(i , size);
//			if(rnd != i+1)
//				exchange(pop , i + 1 , rnd);	

            //交叉
            double random = rand();

            if (random < GeneticAlgorithms.crossoverRate) {

                pop[i].crossover1(pop[i], pop[i + 1]);
            }

//			findBestAndWorstIndividual();
        }
    }

    //执行交叉操作
//	private void cross(int i){
//		pop[i].crossover(pop[i],pop[i+1]);			
//	}

    //产生随机数
//	private int rand(int start , int end){//产生区间为[start , end)的随机整数
//		return (int)(rand()*(end - start) + start);
//	}

//	//交换
//	private void exchange(Individual[] p ,int src , int dest){
//		Individual temp;
//		temp = p[src];
//		p[src] = p[dest];
//		p[dest] = temp;	
//	}
    //==============================

    //-----------------------------------------------------
    //变异
    public void mutate() {
        //对所有的群体中的个体进行变异操作
        for (int i = 0; i < size; i++) {

            double random = rand();

            if (random < GeneticAlgorithms.mutateRate) {
                pop[i].bianyi();
            }

        }

//		findBestAndWorstIndividual();
    }
    //==============================

    //-----------------------------------------------------
    //进化
    public void evolve() {
        select();
        crossover();
        mutate();
        evaluate();
        findBestAndWorstIndividual();
    }


    //==============================
    //计算目标函数值、适应度、找出最优个体。
    public void evaluate() {
        //同步目标函数值和适应度
//		for(int i = 0; i < size; i++){
////			pop[i].calTargetValue();
////			pop[i].calFitness();
//		}
//		
//		//使用最优保存策略(Elitist Model)保存最优个体
        findBestAndWorstIndividual();
        pop[worstIndex] = (Individual) currentBest.clone();

        generation++;
    }

    //找出适应度最大的个体
    public void findBestAndWorstIndividual() {
        bestIndividual = worstIndividual = pop[0];
        for (int i = 1; i < size; i++) {
            System.out.println(bestIndividual.calFitness());
            System.out.println(worstIndividual.calFitness());
            if (pop[i].calFitness() < bestIndividual.calFitness()) {
                bestIndividual = pop[i];
            }
            if (pop[i].calFitness() > worstIndividual.calFitness()) {
                worstIndividual = pop[i];
                worstIndex = i;
            }
        }

        if (generation == 0) {//初始种群
            currentBest = (Individual) bestIndividual.clone();
            currentWorst = (Individual) worstIndividual.clone();
        } else {
            if (bestIndividual.calFitness() < currentBest.calFitness()) {
                currentBest = (Individual) bestIndividual.clone();
            }
            if (worstIndividual.calFitness() > currentWorst.calFitness()) {
                currentWorst = (Individual) worstIndividual.clone();
            }
        }
        System.out.println("bestIndividual:" + bestIndividual.toString());
        System.out.println("bestIndividualFitness:" + bestIndividual.calFitness());
        System.out.println("worstIndividual:" + worstIndividual.toString());
        System.out.println("worstIndividualFitness:" + worstIndividual.calFitness());
        System.out.println("currentBest:" + currentBest.toString());
        System.out.println("currentBestFitness:" + currentBest.calFitness());
        System.out.println("currentWorst:" + currentWorst.toString());
        System.out.println("currentWorstFitness:" + currentWorst.calFitness());
    }

    //判断进化是否完成
    public boolean isEvolutionDone() {
        System.out.println("generation" + generation);
        System.out.println("maxGeneration" + GeneticAlgorithms.maxGeneration);
        if (generation < GeneticAlgorithms.maxGeneration)
            return false;
        return true;
    }

    //计算平均适应度
    public double calAverageFitness() {
        for (int i = 0; i < size; i++) {
            averageFitness += pop[i].calFitness();
        }
        averageFitness /= size;

        return averageFitness;
    }

    //产生随机数
    private double rand() {
        return Math.random();
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }

    public int getGeneration() {
        return generation;
    }
	/*
	public String printPop(Individual[] pop){
		String str="";
		for(int i = 0 ; i < size ; i++)
			str += pop[i];
		return str;
	}*/

    public String toString() {
        System.out.println("pop遍历");
        String str = "";
        for (int i = 0; i < size; i++) {
            str = "";
            str += pop[i];
        }
        return str;
    }
}