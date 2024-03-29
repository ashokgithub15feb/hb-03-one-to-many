package com.luv2code.hibernate.demo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "instructor")
public class Instructor {

	// annotate the class as an entity and map to db table.

	// define the field

	// annotate the filed with db column names

	//** set up mapping to InstructorDetail entity
	
	// create constructors

	// getter and setter methods

	// generate toString method

//	@Id
//	@GeneratedValue(generator = "inc-gen")
//	@GenericGenerator(name="gen", strategy="foreign", parameters={@Parameter(name="property", value="txn")})
//	@GeneratedValue(generator="gen")
//	@GenericGenerator(name = "gen", strategy="foreign", parameters={@Parameter(value = "instructorDetail", name = "property")})
	
	@Id
	@GenericGenerator(name = "inc-gen", strategy="identity")
	@Column(name = "id")
	private int id;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastname;
	
	@Column(name = "email")
	private String email;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "instructor_detail_id")
	private InstructorDetail instructorDetail;
	
	//reference by course class
	@OneToMany(mappedBy = "instructor", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}) 
	private List<Course> courses;
	
	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public Instructor() {
	}

	public Instructor(String firstName, String lastname, String email) {
		this.firstName = firstName;
		this.lastname = lastname;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public InstructorDetail getInstructorDetail() {
		return instructorDetail;
	}

	public void setInstructorDetail(InstructorDetail instructorDetail) {
		this.instructorDetail = instructorDetail;
	}

	@Override
	public String toString() {
		return "Instructor [id=" + id + ", firstName=" + firstName + ", lastname=" + lastname + ", email=" + email
				+ ", instructorDetail=" + instructorDetail + ", courses=" + courses + "]";
	}
	
	//add convenience method for Bi-Directional relationship
	public void add(Course tempCourse)
	{
		if(courses  == null)
		{
			courses = new ArrayList<>();
		}
		
		courses.add(tempCourse);
		
		tempCourse.setInstructor(this);
	}

}
