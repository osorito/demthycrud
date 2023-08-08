package com.luv2code.demthycrud.entity;





import jakarta.persistence.*;
import jakarta.validation.constraints.*;


@Entity
@Table(name="employee")
public class Employee {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@NotNull(message="First Name is null")
	@NotEmpty(message="First Name must not be empty")
	@Column(name="first_name")
	private String firstName;
	
	@NotNull(message="Last Name is null")
	@NotEmpty(message="Last Name must not be empty")
	@Column(name="last_name")
	private String lastName;
	
	@Pattern(regexp="^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$",message="Invalid email format")
	@Column(name="email")
	private String email;
	
	public Employee()
	{
		
	}

	public Employee(String firstName, String lastName, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public int getId() {
		//System.out.println("getId()");
		return id;
	}

	public void setId(int id) {
		//System.out.println("setId()");
		this.id = id;
	}

	public String getFirstName() {
		//System.out.println("getFirstName()");
		return firstName;
	}

	public void setFirstName(String firstName) {
		//System.out.println("setFirstName()");
		this.firstName = firstName;
	}

	public String getLastName() {
		//System.out.println("getLastName()");
		return lastName;
	}

	public void setLastName(String lastName) {
		//System.out.println("setLastName()");
		this.lastName = lastName;
	}

	public String getEmail() {
		//System.out.println("getEmail()");
		return email;
	}

	public void setEmail(String email) {
		//System.out.println("setEmail()");
		this.email = email;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + "]";
	}
}
