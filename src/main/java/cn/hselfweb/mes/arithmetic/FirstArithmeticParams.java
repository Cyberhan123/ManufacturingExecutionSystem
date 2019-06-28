package cn.hselfweb.mes.arithmetic;

//import java.util.ArrayList;
//import java.util.List;
//import com.hrbust.pojo.ExtendCraft;

import cn.hselfweb.mes.arithmetic.pojo.ArithmeticParams;

public class FirstArithmeticParams extends ArithmeticParams{
   //继承了ArithmeticArgumentInterface接口中公有的参数和方法，在本类中实现一些私有的参数和方法
	//打算做成singleton模式
	
	private static ArithmeticParams instance = null;

    private FirstArithmeticParams() {
		super();
	}

    public static synchronized ArithmeticParams getInstance() {

    if (instance == null)
    instance = new FirstArithmeticParams();
    return instance;
    }

    public void releaseInstance(){
    	if(instance == null){
    		
    	}else{
    	   instance = null;
    	}
    }
    
    
}



