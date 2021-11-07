package entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PARENT")
public class Parent {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "full_name")
	private String  fullName;
	
	@ManyToMany(fetch = FetchType.EAGER,
			cascade = CascadeType.ALL)
    @JoinTable(name = "parent_child",
    		joinColumns = @JoinColumn(name = "parent_id"),
            inverseJoinColumns = @JoinColumn(name = "child_id")
    )
    private List<Child> children = new ArrayList<Child>();
	
	@ManyToOne(targetEntity = Address.class, fetch = FetchType.LAZY)
	@JoinColumn(name="address_id")
	private Address addressParent;
	
	public Parent(){
		
	}
	
	public Parent(String fullName) {
		super();
		this.fullName = fullName;
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

	public List<Child> getChild() {
		return children;
	}

	public void setChildren(List<Child> children) {
		this.children = children;
	}

	public Address getAddress() {
		return addressParent;
	}

	public void setAddress(Address addressParent) {
		this.addressParent = addressParent;
	}

	@Override
	public String toString() {
		return "Parent [id=" + id + ", fullName=" + fullName + ", children=" + children + ", addressParent="
				+ addressParent + "]";
	}
	
}
