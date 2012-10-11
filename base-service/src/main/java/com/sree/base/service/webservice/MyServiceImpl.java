package com.sree.base.service.webservice;

import javax.jws.WebService;

@WebService(endpointInterface = "com.sree.base.service.webservice.MyService")
//@HandlerChain(file = "handlers.xml")
public class MyServiceImpl implements MyService {

    public String sayHello(String name) {
        if (name == null || name.trim().length() <= 0)
            throw new IllegalArgumentException("name");

        System.out.println("Going to say Hellow to " + name);
        return "Hello " + name + "!";
    }

   /* @SuppressWarnings("deprecation")
    @Override
    public Person getPerson(Integer id) {
        Person person = new Person();
        person.setId(id);
        person.setName("Sreenivasa Reddy Y");

        System.out.println("Serving getPerson(" + id + "): " + person);

        return person;
    }

    @Override
    public String xmlData(String data) {
        System.out.println("Received XML data from client: " + data);
        return "<greeting>" + "Hello! " + data.substring(data.indexOf(">") + 1, data.lastIndexOf("<")) + "</greeting>";
    }
*/
   /* public String getPurchaseOrder() {
        StringBuilder data = new StringBuilder(2048);
        try {
            BufferedReader fReader = new BufferedReader(new InputStreamReader(MyServiceImpl.class
                    .getResourceAsStream("/PurchaseOrder.xml")));
            String line;
            do {
                line = fReader.readLine();
                if (line != null)
                    data.append(line).append("\n");
            } while (line != null);
        } catch (Exception e) {
            e.printStackTrace();
            data.append(e.getMessage());
        }
        return data.toString();
    }

	@Override
	public String xmlData(String data) {
		return getPurchaseOrder();
	}
*/
	//@Override
	/*public void sendPerson(Person person) {
		System.out.println("--------------------->>>>>>>>>>>"+person.getName());
	}*/
}
