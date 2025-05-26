package org.machinestalk.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
public class User {

  @Id @GeneratedValue private Long id;

  @NotBlank(message = "firstName is mandatory")
  private String firstName;

  @NotBlank(message = "lastName is mandatory")
  private String lastName;

  @NotNull
  @ManyToOne(cascade=CascadeType.PERSIST)
  private Department department;

  @ElementCollection(fetch = FetchType.EAGER)
  private Set<Address> addresses;

  public Long getId() {
    return id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(final String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(final String lastName) {
    this.lastName = lastName;
  }

  public Department getDepartment() {
    return department;
  }

  public void setDepartment(final Department department) {
    this.department = department;
  }

  public Set<Address> getAddresses() {
    return addresses;
  }

  public void setAddresses(final Set<Address> addresses) {
    this.addresses = addresses;
  }

  @Override
  public String toString() {
    return "User{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", department=" + department +
            ", addresses=" + addresses +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    User user = (User) o;

    if (id != null ? !id.equals(user.id) : user.id != null) return false;
    if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
    if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
    if (department != null ? !department.equals(user.department) : user.department != null) return false;
    return addresses != null ? addresses.equals(user.addresses) : user.addresses == null;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
    result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
    result = 31 * result + (department != null ? department.hashCode() : 0);
    result = 31 * result + (addresses != null ? addresses.hashCode() : 0);
    return result;
  }
}
