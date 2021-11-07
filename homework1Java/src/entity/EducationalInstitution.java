package entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "EDUCATIONAL_INSTITUTION")
public class EducationalInstitution {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "number")
	private String number;
	
	@ManyToOne(targetEntity = Address.class, fetch = FetchType.LAZY)
	@JoinColumn(name="address_id")
	private Address addressEd;
	
	@OneToMany(mappedBy="educationalInstitution", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Child> children;
	
	public EducationalInstitution(){
		
	}

	public EducationalInstitution(String number) {
		super();
		this.number = number;
		children = new HashSet<Child>();
	}
	
	public void addChild(Child child) {
        child.setEducationalInstitution(this);
        children.add(child);
    }

    public void removeChild(Child child) {
    	children.remove(child);
    }

	public int getId() {
		return id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Address getAddressEd() {
		return addressEd;
	}

	public void setAddressEd(Address addressEd) {
		this.addressEd = addressEd;
	}

	public Set<Child> getChildren() {
		return children;
	}

	public void setChildren(Set<Child> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "EducationalInstitution [id=" + id + ", number=" + number + ", addressEd=" + addressEd + ", children="
				+ children + "]";
	}
	
	
}
