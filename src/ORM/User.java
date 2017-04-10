package ORM;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@SuppressWarnings("serial")
@Entity
public class User implements Serializable {

	private String userName;
	private String firstName;
	private String lastName;
	
	private List<Record> recordList = new ArrayList<Record>();

	public User() {

	}

	public User(String userName) {
		this.userName = userName;
	}
	
	@Id
	@Column(name = "USERNAME", nullable = false)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "FIRSTNAME")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@Column(name = "LASTNAME")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public List<Record> getRecordList() {
		return recordList;
	}

	public void setRecordList(List<Record> recordList) {
		this.recordList = recordList;
	}

/*	@Override
	public String toString() {
		String str = new String("User Details : [User Name = " + userName + ", First Name = " + firstName + ", Last Name = " + lastName + "]");
		str += "\n     User Records:";
		for(int i = 0; i < recordList.size(); i++)
		{
			str += "\n          ";
			str += recordList.get(i).toString();
		}
		return str;                                        
	}
*/
	@Override
	public String toString() {
	
		return this.firstName + " " + this.lastName;
	}
	

}