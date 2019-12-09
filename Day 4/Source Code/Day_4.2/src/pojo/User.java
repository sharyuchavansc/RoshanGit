package pojo;

import java.sql.Date;
import java.time.LocalDate;

public class User 
{
	private int userId;
	private String fullName, email, password;
	private Date birthDate, creationDate;
	public User() 
	{	
		LocalDate ldt = LocalDate.now();
		this.creationDate = new Date( ldt.getYear() - 1900, ldt.getMonthValue() - 1, ldt.getDayOfMonth());
	}
	
	public User(String fullName, String email, String password, Date birthDate ) 
	{
		this.fullName = fullName;
		this.email = email;
		this.password = password;
		this.birthDate = birthDate;
		LocalDate ldt = LocalDate.now();
		this.creationDate = new Date( ldt.getYear() - 1900, ldt.getMonthValue() - 1, ldt.getDayOfMonth());
	}
	public User(String fullName, String email, String password, Date birthDate, Date creationDate) 
	{
		this.fullName = fullName;
		this.email = email;
		this.password = password;
		this.birthDate = birthDate;
		this.creationDate = creationDate;
	}

	public User(int userId, String fullName, String email, String password, Date birthDate, Date creationDate) 
	{
		this.userId = userId;
		this.fullName = fullName;
		this.email = email;
		this.password = password;
		this.birthDate = birthDate;
		this.creationDate = creationDate;
	}

	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Override
	public String toString() 
	{
		return "User [userId=" + userId + ", fullName=" + fullName + ", email=" + email + ", password=" + password
				+ ", birthDate=" + birthDate + ", creationDate=" + creationDate + "]";
	}
}
