package com.telstra.RentalToys;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

public class MainService {


	static String url = "jdbc:mysql://localhost:3306/miniproject";

	static Connection cn =null;
	static Statement st = null;
	static ResultSet rs = null;

	public static String toys_available() {
		return "select * from toy where quantity>0;";
	}
	public static String toys_age3p() {
		return "select * from toy where (MIN_AGE<=3) AND (MAX_AGE>=3);";
	}

	public static String desc_cust() {
		return "select * from customer order by Customer_Name;";
	}

	public static String tot_cust_city() {
		return "select City, count(*) As Number_Of_Customers from customer group by city;";
	}

	public static String less1k() {
		return "SELECT * FROM Toy WHERE (Rental_Amount*30)<1000;";
	}

	public static String price_mt145() {
		return " SELECT * FROM Toy WHERE (Price>145) AND (Quantity=5);";
	}

	public static String toys_q20() {
		return "SELECT * FROM Toy WHERE Quantity BETWEEN 21 AND 49;";
	}

	public static String disp_toy_date()
	{
		return"Select * from toy_rental where Rental_start_date = \"2020-05-20\";";
	}
	public static String cust_twotoys()
	{
		return"Select * from customer where Customer_Id in (select Customer_Id from toy_rental group by Customer_ID having count(Toy_Id)>2) ";
	}

	public static String cust_toy_red()
	{
		return"Select Customer_Name, Toy_name, Toy_type, Rental_End_Date  from Customer as C, Toy as T, Toy_Rental as R where R.Toy_Id = T.Toy_Id and C.Customer_Id=R.Customer_Id;";
	}

	public static String cust_maxtoys()
	{
		return" WITH Derived as (Select Customer_Id from toy_rental where Rental_Start_Date>\"2021-04-01\" group by Customer_Id order by count(toy_Id) desc limit 1) select Customer_Name from customer, derived where customer.Customer_Id = derived.Customer_Id;";
	}

	public static String toys_maxcust()
	{
		return"WITH Derived as (Select Toy_Id from toy_rental group by Toy_Id order by count(Customer_Id) desc limit 1) select Toy_Name from toy,derived where toy.Toy_Id = derived.Toy_Id;";
	}

	public static String cust_1more()
	{
		return"Select * from customer where Customer_Id in (select Customer_Id from toy_rental group by Customer_ID having sum(Toy_Id)>1) ";
	}

	public static String pending_rental()
	{
		return " SELECT * FROM CUSTOMER WHERE CUSTOMER_ID IN(SELECT CUSTOMER_ID FROM TOY_RENTAL WHERE RENTAL_END_DATE < CURRENT_DATE() AND STATUS = 'Pending' GROUP BY CUSTOMER_ID);";
	}

	public static String rented_25()
	{
		return"\r\n"
				+ "SELECT CUSTOMER_NAME, TOY_NAME, RENTAL_START_DATE, RENTAL_END_DATE FROM CUSTOMER C, TOY T, TOY_RENTAL R WHERE RENTAL_START_DATE = \"2020-01-25\" AND C.CUSTOMER_ID = R.CUSTOMER_ID AND R.TOY_ID = T.TOY_ID;";
	}
	public static String ever_rental() {
		return "Select customer.Customer_Id,Customer_Name, Rental_Id, Toy_Id, Status from customer LEFT JOIN toy_rental on customer.Customer_Id = toy_rental.Customer_Id;";
	}

	public static String max_ram() {
		return " WITH DERIVED AS(Select Customer_Id, sum(Total_Amount) from toy_rental wherE month(Rental_Start_Date)=5 AND year(Rental_Start_Date)=2021 group by Customer_Id order by sum(Total_Amount) desc limit 1) Select Customer_Name, customer.Customer_Id from customer, derived where customer.Customer_Id = Derived.Customer_Id;";
	}

	public static String month_toy() {
		return "Select Month(Rental_Start_Date), Count(Toy_Id) as t from toy_rental group by Toy_Id order by t desc limit 1;";
	}

	public static String max_rental_time()
	{
		return"SELECT * FROM ( SELECT toy.Toy_Id, (Rental_End_Date - Rental_Start_Date) FROM toy_rental,toy WHERE toy_rental.Toy_Id = toy.Toy_Id ORDER BY (Rental_End_Date - Rental_Start_Date) DESC ) as b limit 1;";
	}

	public static String toytop() {
		return "select toy_Id, count( DISTINCT Customer_Id) as c from toy_rental group by toy_Id order by c desc limit 1;";
	}

	public static String top3()
	{
		return"SELECT * FROM (SELECT toy_rental.Customer_Id, COUNT(toy_rental.Rental_Id) as TOTAL_RENTALS FROM toy_rental GROUP BY toy_rental.Customer_Id ORDER BY TOTAL_RENTALS DESC) as z limit 3;";
	}
	public static String top2() {
		return "SELECT * FROM (SELECT Customer_Name FROM customer C, (SELECT toy_rental.Customer_Id, SUM(toy_rental.Total_Amount) as TOTAL_COST FROM toy_rental GROUP BY toy_rental.Customer_Id ORDER BY TOTAL_COST DESC) as R WHERE C.Customer_Id = R.Customer_Id ORDER BY TOTAL_COST DESC) as q limit 2;";
	}






	public static String next_mon() {
		return "SELECT Toy_Id, Toy_Name FROM toy WHERE Toy_Id IN (SELECT Toy_Id FROM toy_rental WHERE Status = 'Rented' and Month(Rental_End_Date)>Month(Current_Date()));";
	}

	public static String toy_deets(String toyname) {
		return " SELECT * FROM toy_rental WHERE Toy_Id IN (SELECT Toy_Id FROM toy WHERE Toy_Name = '"+ toyname + "');";
	}

	public static String city_find(String cityname) {
		return "SELECT Toy_Name FROM toy WHERE Toy_Id IN (SELECT Toy_Id FROM toy_rental WHERE Status =  'Rented' AND Customer_Id IN (SELECT Customer_Id FROM customer WHERE City = '"+cityname+"'));";
	}
	
	public static String toy_find(String toyname) {
		return " SELECT DISTINCT customer.Customer_Id, Customer_Name, City FROM customer, toy_rental, toy WHERE customer.Customer_Id = toy_rental.Customer_Id AND toy_rental.Toy_Id = toy.Toy_Id AND Toy_Type = '"+ toyname+"' ORDER BY customer.Customer_Id;";
	}
	 
	public static String toy_unrented() {
		return "SELECT Toy_Name FROM toy WHERE NOT EXISTS (SELECT Toy_Id FROM toy_rental WHERE toy.Toy_Id = toy_rental.Toy_Id);";
	}
	public static String cust_deets(String custname) {
		return "SELECT * FROM toy_rental WHERE Customer_Id IN (SELECT Customer_Id FROM customer WHERE Customer_Name = '"+custname+"');";
	}

	
	

	//	public static String less1k() {
	//		return "SELECT * FROM Toy WHERE (Rental_Amount*30)<1000;";
	//	}
	//	
	//	public static String price_mt145() {
	//		return " SELECT * FROM Toy WHERE (Price>145) AND (Quantity=5);";
	//	}
	//	
	//	public static String toys_q20() {
	//		return "SELECT * FROM Toy WHERE Quantity BETWEEN 21 AND 49;";
	//	}
	//	







	public static void execquery(String command, String user, String pass) {
		try {
			cn = DriverManager.getConnection(url, user, pass);
			st = cn.createStatement();
			rs =  st.executeQuery(command);
			ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
			while(rs.next()) {
				for (int i = 1; i <= columnsNumber; i++) {
					if (i > 1) System.out.print(" ");
					String columnValue = rs.getString(i);
					System.out.print(rsmd.getColumnName(i)+ ":"+columnValue+ "||");
				}
				System.out.println(" ");	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//	public static String toys_available() {
	//		return "select * from toy where quantity>0";
	//	}
	//	

}
