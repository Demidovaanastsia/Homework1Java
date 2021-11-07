package entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CHILD")
public class Child {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "full_name")
	private String fullName;
	
	@Column(name = "age")
	private int age;
	
	@ManyToMany(mappedBy = "children")
    private Set<Parent> parents = new HashSet<Parent>();
	
	@ManyToOne(targetEntity = EducationalInstitution.class, fetch = FetchType.LAZY)
	@JoinColumn(name="ed_inst_id")
	private EducationalInstitution educationalInstitution;
	
	public Child(){
		
	}

	public Child(String fullName, int age) {
		super();
		this.fullName = fullName;
		this.age = age;
	}

	public int getId() {
		return id;
	}


	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Set<Parent> getParents() {
		return parents;
	}

	public void setParents(Set<Parent> parents) {
		this.parents = parents;
	}

	public EducationalInstitution getEducationalInstitution() {
		return educationalInstitution;
	}

	public void setEducationalInstitution(EducationalInstitution educationalInstitution) {
		this.educationalInstitution = educationalInstitution;
	}

	@Override
	public String toString() {
		return "Child [id=" + id + ", fullName=" + fullName + ", age=" + age + ", educationalInstitution=" + educationalInstitution + "]";
	}
}
