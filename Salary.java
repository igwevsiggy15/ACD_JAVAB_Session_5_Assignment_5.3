package Assignment3;

abstract class Employee{
	int empId;
	String empName;
	int total_leaves;
	double total_salary;
	
	abstract void calculate_balance_leaves();
	abstract boolean avail_leave(int no_of_leaves, char type_of_leave);
	abstract void calculate_salary();
}

class PermanentEmp extends Employee{
	
	int paid_leave, sick_leave, casual_leave;
	double basic, hra, pfa;
	
	public PermanentEmp (int id, String name, int paid_leave, int sick_leave, int casual_leave, double basic){
		this.empName = name;
		this.empId = id;
		this.basic = basic;
		this.paid_leave = paid_leave;
		this.sick_leave = sick_leave;
		this.casual_leave = casual_leave;
		this.pfa = 0.12 * basic;
		this.hra = .5 * basic;
		calculate_balance_leaves();
		calculate_salary();
	}
	
	void print_leave_details() {
		System.out.println("Employee Name: " + this.empName);
		System.out.println("Paid leave left: " + paid_leave);
		System.out.println("Sick leave left: " + sick_leave);
		System.out.println("Casual leave left: " + casual_leave);
		System.out.println("Total: " + total_leaves + "\n");
	}
	

	@Override
	void calculate_balance_leaves() {
		// TODO Auto-generated method stub
		this.total_leaves = this.casual_leave + this.sick_leave + this.paid_leave;
		print_leave_details();
	}

	@Override
	boolean avail_leave(int no_of_leaves, char type_of_leave) {
		// TODO Auto-generated method stub
		switch(type_of_leave) {
		case 'p':
			if (no_of_leaves <= paid_leave) {
				paid_leave -= no_of_leaves;
				calculate_balance_leaves();
				return true;
			}
		case 's':
			if (no_of_leaves <= sick_leave) {
				sick_leave -= no_of_leaves;
				calculate_balance_leaves();
				return true;
			}
		case 'c':
			if (no_of_leaves <= casual_leave) {
				casual_leave -= no_of_leaves;
				calculate_balance_leaves();
				return true;
			}
		}
		return false;
	}

	@Override
	void calculate_salary() {
		// TODO Auto-generated method stub 
		this.total_salary = this.basic + this.hra - this.pfa;
		System.out.println("Employee name: " + this.empName  
							+ "\nSalary: " + this.total_salary + "\n");
	}
	
}

class TemporaryEmp extends Employee{
	
	public TemporaryEmp(int empId, String name, int total_leaves, double total_salary) {
		this.empId = empId;
		this.empName = name;
		this.total_leaves = total_leaves;
		this.total_salary = total_salary;
	}

	@Override
	void calculate_balance_leaves() {
		// TODO Auto-generated method stub
		System.out.println("Employee name: " + this.empName 
				+ "\nTotal leaves: " + this.total_leaves + "\n");
	}

	@Override
	boolean avail_leave(int no_of_leaves, char type_of_leave) {
		// TODO Auto-generated method stub
		if (no_of_leaves <= this.total_leaves) {
			this.total_leaves -= no_of_leaves;
			calculate_balance_leaves();
			return true;
		}
		return false;
	}

	@Override
	void calculate_salary() {
		// TODO Auto-generated method stub
		System.out.println("Employee name: " + this.empName 
				+ "\nSalary: " + this.total_salary + "\n");
		
	}
	
}

public class Salary {
	public static void main(String[] args) {
		PermanentEmp pe = new PermanentEmp(987, "Iggy", 15,15,15, 1500000);
		TemporaryEmp te = new TemporaryEmp(654, "KK JR", 5, 5000000);
		
		pe.avail_leave(2, 'c');
		pe.avail_leave(3, 'p');
		pe.avail_leave(1, 's');
		pe.print_leave_details();
		
		te.calculate_salary();
		te.avail_leave(2, 'l');
	}
}