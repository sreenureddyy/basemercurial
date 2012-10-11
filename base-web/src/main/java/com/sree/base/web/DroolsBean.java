package com.sree.base.web;

import org.apache.log4j.Logger;
import org.drools.KnowledgeBase;
import org.drools.logger.KnowledgeRuntimeLogger;
import org.drools.logger.KnowledgeRuntimeLoggerFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.sree.base.service.drools.Employee;
import com.sree.base.service.drools.EmployeeVo;
import com.sree.base.service.drools.IRuleEngine;

@SuppressWarnings("unused")
@Component("droolsBean")
@Scope(value = "request")
public class DroolsBean extends BaseBean {
	private static Logger log = Logger.getLogger(DroolsBean.class);

	@Autowired
	private IRuleEngine ruleEngine;

	private int amount;

	public int getAmount() {

		KnowledgeBase kbase = ruleEngine.getRuleKnowledgeBaseMap().get(
				"Employee.xls");
		StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
		KnowledgeRuntimeLogger logger = KnowledgeRuntimeLoggerFactory
				.newFileLogger(ksession, System.getProperty("user.dir"));
		// go !
		Employee employee = new Employee();
		employee.setAge(39);
		employee.setEmpName("Sree");
		employee.setGender("F");
		employee.setSubject("COMP");
		ksession.insert(employee);
		ksession.insert(new EmployeeVo());
		ksession.fireAllRules();
		System.out.println("Emp Salary is :: " + EmployeeVo.getEmpSalary());
		logger.close();
		amount = EmployeeVo.getEmpSalary();
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public IRuleEngine getRuleEngine() {
		return ruleEngine;
	}

	public void setRuleEngine(IRuleEngine ruleEngine) {
		this.ruleEngine = ruleEngine;
	}
}
