package cn.hselfweb.mes.arithmetic.GA.copy;

import java.io.*;
//2008-11-21
class GeneticAlgorithms{
	public static double crossoverRate;//交叉概率
	public static double mutateRate;//变异概率
	public static int maxGeneration;//进化代数
	public static int populationSize;//群体大小
	
	static {
		//crossoverRate = 0.6;
		//mutateRate = 0.001;
		//maxGeneration  = 100;
		//populationSize = 500;
		maxGeneration  = 10;
		populationSize = 100;
		crossoverRate = 0.6;
//		mutateRate = 0.001;
		mutateRate = 0.5;
	}
	
	public static void main(String[] args)throws IOException{

//获取相关信息
		
//		Population pop = new Population(populationSize);
//		pop.initPopulation();
//
////		pw.println("初始种群:\n" + pop +"\n");
////		pw.println("");
//		System.out.println("初始种群:\n" + pop +"\n");
//		
//		while(!pop.isEvolutionDone()){
//			pop.evolve();
////			pw.print("第" + pop.getGeneration() + "代Best:" + pop.bestIndividual );
////			pw.print("第" + pop.getGeneration()  + "代current:" + pop.currentBest );
////			pw.println("");	
//			System.out.print("第" + pop.getGeneration() + "代Best:" + pop.bestIndividual);
//			System.out.print("第" + pop.getGeneration()  + "代current:" + pop.currentBest);
//		}
////		pw.println();
////		pw.println("第"+ pop.getGeneration()  + "代群体:\n" + pop);
//		System.out.println("第"+ pop.getGeneration()  + "代群体:\n" + pop);
////		pw.close();
	}
	
	public void print(){

	}
}





