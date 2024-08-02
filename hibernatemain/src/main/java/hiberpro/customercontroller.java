package hiberpro;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;



public class customercontroller {
  public static void main(String[] args) {
	Scanner src=new Scanner(System.in);
	  int ch;
	  do {
		displaymenu();
		System.out.println("enter your choice");
		ch=Integer.parseInt(src.nextLine());
		switch (ch) {
		case 1:
			insertion();
			
			break;
		case 2:
			delete();
			break;
		case 3:
			update();
			break;
		case 4:
			getall();
			break;
		case 5:
			getbyid();
			break;
		case 6:
			System.exit(ch);
			break;

		default:
			System.out.println("invalid opp");
			break;
		}
		
	} while (ch>0);
}

private static void getbyid() {
	Scanner sc =new Scanner(System.in);
	StandardServiceRegistry ssr =new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
	Metadata mt=new MetadataSources(ssr).getMetadataBuilder().build();
	SessionFactory sf= mt.getSessionFactoryBuilder().build();
	Session s = sf.openSession();

	System.out.println("enter id");
	int id=sc.nextInt();	
	Transaction t= s.beginTransaction();
	customer cu=s.get(customer.class, id);
	if (cu!=null) {
		System.out.println("id :"+cu.getId());
		System.out.println("id :"+cu.getName());
		System.out.println("id :"+cu.getEmail());
		System.out.println("id :"+cu.getPassword());
		System.out.println("id :"+cu.getPhonenumber());
	}
	else {
		System.out.println("not found");
	}
	t.commit();
}

private static void getall() {
	Scanner sc =new Scanner(System.in);
	StandardServiceRegistry ssr =new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
	Metadata mt=new MetadataSources(ssr).getMetadataBuilder().build();
	SessionFactory sf= mt.getSessionFactoryBuilder().build();
	Session s = sf.openSession();
	Transaction t= s.beginTransaction();
	List<customer> li=s.createQuery("from customer",customer.class).list();
	t.commit();
	for(customer c:li) {
		System.out.println("id:"+c.getId());
		System.out.println("name"+c.getName());
		System.out.println("email:"+c.getEmail());
		System.out.println("password: "+c.getPassword());
		System.out.println("phn number"+c.getPhonenumber());
	}
}

private static void update() {
	Scanner sc =new Scanner(System.in);
	StandardServiceRegistry ssr =new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
	Metadata mt=new MetadataSources(ssr).getMetadataBuilder().build();
	SessionFactory sf= mt.getSessionFactoryBuilder().build();
	Session s = sf.openSession();

	System.out.println("enter id");
	int id=sc.nextInt();	
	Transaction t= s.beginTransaction();
	customer cu=s.get(customer.class, id);
	if (cu!=null) {
		System.out.println("enter new name");
		String name =sc.next();
		System.out.println("enter new email :");
		String email =sc.next();
		System.out.println("enter new password");
		String password =sc.next();
		System.out.println("phonenumber");
		long phonenumber =sc.nextLong();
		cu.setName(name);
		cu.setEmail(email);
		cu.setPassword(password);
		cu.setPhonenumber(phonenumber);
		s.update(cu);
		t.commit();
		System.out.println("success");
	}
	else {
		System.out.println("not found");
	}
	
}

private static void delete() {
	Scanner sc =new Scanner(System.in);
	StandardServiceRegistry ssr =new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
	Metadata mt=new MetadataSources(ssr).getMetadataBuilder().build();
	SessionFactory sf= mt.getSessionFactoryBuilder().build();
	Session s = sf.openSession();
	System.out.println("enter id");
	int id =sc.nextInt();
	Transaction t= s.beginTransaction();
	customer c=s.get(customer.class, id);
	s.delete(c);
	t.commit();
	System.out.println("succesful");
	
}

private static void insertion() {
	// TODO Auto-generated method stub
	@SuppressWarnings("deprecation")
	StandardServiceRegistry ssr =new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
	Metadata mt=new MetadataSources(ssr).getMetadataBuilder().build();
	SessionFactory sf= mt.getSessionFactoryBuilder().build();
	Session s = sf.openSession();
	Transaction t= s.beginTransaction();
	 customer h=new customer();
	 Scanner sc =new Scanner(System.in);
	 System.out.println("enter the name");
	 h.setName(sc.next());
	 System.out.println("email");
		h.setEmail(sc.next());
		System.out.println("password");
		h.setPassword(sc.next());
		System.out.println("phn number");
		h.setPhonenumber(sc.nextLong());
		s.save(h);
		t.commit();
		System.out.println("success");
	
}

private static void displaymenu() {
	System.out.println("--------------------------");
	System.out.println("\t1.insertion");
	System.out.println("\t2.delete");
	System.out.println("\t3.update");
	System.out.println("\t4.getall");
	System.out.println("\t5.getbyid");
	System.out.println("\t6.exit ");
	System.out.println("--------------------------");
	
}
}
