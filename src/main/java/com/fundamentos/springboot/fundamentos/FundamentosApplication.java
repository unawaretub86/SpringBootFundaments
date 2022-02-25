package com.fundamentos.springboot.fundamentos;

import com.fundamentos.springboot.fundamentos.bean.MyBean;
import com.fundamentos.springboot.fundamentos.bean.MyBeanWithDependecy;
import com.fundamentos.springboot.fundamentos.component.ComponentDependency;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {


	private ComponentDependency componentDependency;
	private MyBean myBean;
	private MyBeanWithDependecy myBeanWithDependecy;

	//take care about write from where class you want to inject the implementation
	public FundamentosApplication(@Qualifier("componentTwoImplement") ComponentDependency componentDependency, MyBean myBean, MyBeanWithDependecy myBeanWithDependecy){
		this.componentDependency = componentDependency;
		this.myBean = myBean;
		this.myBeanWithDependecy = myBeanWithDependecy;
	}

	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);


	}

	@Override
	public void run(String... args) throws Exception {
		componentDependency.saludar();
		myBean.print();
		myBeanWithDependecy.printWithDependecy();
	}
}
