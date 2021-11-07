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
@Table(name = "ADDRESS")
public class Address {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "title")
	private String title;
	
	@ManyToOne(targetEntity = District.class, fetch = FetchType.LAZY)
	@JoinColumn(name="district_id")
	private District district;
	
	@OneToMany(mappedBy="addressParent", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Parent> parents;
	
	@OneToMany(mappedBy="addressEd", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<EducationalInstitution> educationalInstitutions;
	
	public Address(){
		
	}

	public Address(String address, int districtId) {
		super();
		this.title = address;
		parents = new HashSet<Parent>();
		educationalInstitutions = new HashSet<EducationalInstitution>();
	}
	
	public void addParent(Parent parent) {
		parent.setAddress(this);
		parents.add(parent);
    }

    public void removeParent(Parent parent) {
    	parents.remove(parent);
    }
    
    public void addEducationalInstitution(EducationalInstitution educationalInstitution) {
    	educationalInstitution.setAddressEd(this);
    	educationalInstitutions.add(educationalInstitution);
    }

    public void removeEducationalInstitution(EducationalInstitution educationalInstitution) {
    	educationalInstitutions.remove(educationalInstitution);
    }

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public Set<Parent> getParents() {
		return parents;
	}

	public void setParents(Set<Parent> parents) {
		this.parents = parents;
	}

	public Set<EducationalInstitution> getEducationalInstitutions() {
		return educationalInstitutions;
	}

	public void setEducationalInstitutions(Set<EducationalInstitution> educationalInstitutions) {
		this.educationalInstitutions = educationalInstitutions;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", title=" + title + "]";
	}
}
