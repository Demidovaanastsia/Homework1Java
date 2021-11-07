package entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "DISTRICT")
public class District {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "title")
	private String title;
	
	@OneToMany(mappedBy="district", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Address> addresses;
	
	public District(){
		
	}

	public District(String title) {
		super();
		this.title = title;
		addresses = new HashSet<Address>();
	}
	
	public void addAddress(Address address) {
		address.setDistrict(this);
		addresses.add(address);
    }

    public void removeStreet(Address address) {
    	addresses.remove(address);
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

	public Set<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}

	@Override
	public String toString() {
		return "District [id=" + id + ", title=" + title + ", addresses=" + addresses + "]";
	}

}
