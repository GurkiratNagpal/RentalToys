package com.telstra.RentalToys;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

/**
 * Hello world!
 *
 */
public class App 
{
	public static void main( String[] args )
	{

		Scanner sc = new Scanner(System.in);
		System.out.println("Call out a business logic");
		int ch = sc.nextInt(); 
		String command="";
		String user = "gurkirat";
		String pass = "gurkirat";

		switch(ch) {
		case 1: {
			System.out.println("Call out a query");
			int qu = sc.nextInt();
			switch(qu) {
			case 1: 
				command = MainService.toys_available();
				break;
			case 2: 
				command = MainService.toys_age3p();
				break;
			case 3:
				command = MainService.desc_cust();
				break;
			case 4: 
				command = MainService.tot_cust_city();
				break;  
			default: System.out.println("Enter valid number");
			break;
			}
		}
		case 2:{
			System.out.println("Call out a query");
			int qu = sc.nextInt();
			switch(qu) {
			case 1: 
				command = MainService.less1k();
				break;
			case 2: 
				command = MainService.price_mt145();
				break;
			case 3:
				command = MainService.toys_q20();
				break; 
			default: System.out.println("Enter valid number");
			break;
			}
		}
		case 3:
		{
			System.out.println("Call out a query");
			int qu = sc.nextInt();
			switch(qu) {
			case 1: 
				command = MainService.disp_toy_date();
				break;
			case 2: 
				command = MainService.cust_twotoys();
				break;
			case 3:
				command = MainService.cust_toy_red();
				break; 
			case 4: 
				command = MainService.cust_maxtoys();
				break;
			case 5: 
				command = MainService.toys_maxcust();
				break;
			case 6:
				command = MainService.cust_1more();
				break; 
				
			case 7:
				command = MainService.pending_rental();
				break;
			case 8:
				command = MainService.rented_25();
			case 9: 
				command = MainService.ever_rental();
				break;
			case 10: 
				command = MainService.max_ram();
				break;
			default: System.out.println("Enter valid number");
			break;
			}
		}
		case 4:{
			System.out.println("Call out a query");
			int qu = sc.nextInt();
			switch(qu) {

			case 1:
				command = MainService.month_toy();
				break; 
			case 2:
				command = MainService.max_rental_time();
				break;
			case 4: 
				command = MainService.top3();
				break;
			case 5:
				command = MainService.top2();
				break; 
			case 3:
				command = MainService.toytop();
				break;
			default: System.out.println("Enter valid number");
			break;
			}
		}
		case 5:{
			System.out.println("Call out a query");
			int qu = sc.nextInt();
			switch(qu) {

			case 1:
				command = MainService.next_mon();
				break; 
			case 2:
				command = MainService.toy_deets("Barbie");
				break;
			case 3: 
				command = MainService.city_find("Mumbai");
				break;
			case 4:
				command = MainService.toy_find("Dolls");
				break; 
			case 5:
				command = MainService.toy_unrented();
				break;
			case 6:
				command = MainService.cust_deets("Anil");
				break;
			default: System.out.println("Enter valid number");
			break;
			}
			
		}
			

		}
		MainService.execquery(command, user, pass);










	}
}

