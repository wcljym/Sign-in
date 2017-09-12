/**
 * 
 */
package com.stu.dto;


/**
 * @author Administrator
 *
 */
public class Student {

	private String StuNum;
	private String StuName;
	private int id;
	private int status;
	private String time;
	private String sessionName;
	
	public String getSessionName() {
		return sessionName;
	}
	public void setSessionName(String sessionName) {
		this.sessionName = sessionName;
	}
	public String getStuNum() {
		return StuNum;
	}
	public void setStuNum(String stuNum) {
		StuNum = stuNum;
	}
	public String getStuName() {
		return StuName;
	}
	public void setStuName(String stuName) {
		StuName = stuName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((StuName == null) ? 0 : StuName.hashCode());
		result = prime * result + ((StuNum == null) ? 0 : StuNum.hashCode());
		result = prime * result + id;
		result = prime * result + ((sessionName == null) ? 0 : sessionName.hashCode());
		result = prime * result + status;
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (StuName == null) {
			if (other.StuName != null)
				return false;
		} else if (!StuName.equals(other.StuName))
			return false;
		if (StuNum == null) {
			if (other.StuNum != null)
				return false;
		} else if (!StuNum.equals(other.StuNum))
			return false;
		if (id != other.id)
			return false;
		if (sessionName == null) {
			if (other.sessionName != null)
				return false;
		} else if (!sessionName.equals(other.sessionName))
			return false;
		if (status != other.status)
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Student [StuNum=" + StuNum + ", StuName=" + StuName + ", id=" + id + ", status=" + status + ", time="
				+ time + ", sessionName=" + sessionName + "]";
	}
	

}
