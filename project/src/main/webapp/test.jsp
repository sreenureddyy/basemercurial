<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://primefaces.org/ui" prefix="p"%>

<f:view>
	<h:outputText value="SReeeeeeeeeeeeee"></h:outputText><h:form>
		<h:commandButton value="TestJMS" action="#{sendMessageBean.sendMessage}" ></h:commandButton>
	</h:form>
	
</f:view>