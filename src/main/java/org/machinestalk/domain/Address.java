package org.machinestalk.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

  private String streetNumber;

  private String streetName;

//  @NotEmpty
//  @Size(max = 40)
  private String city;

//  @NotEmpty
//  @Size(max = 5)
  private String postalCode;

//  @NotEmpty
//  @Size(max = 3)
  private String country;

  public String getStreetNumber() {
    return streetNumber;
  }

  public void setStreetNumber(final String streetNumber) {
    this.streetNumber = streetNumber;
  }

  public String getStreetName() {
    return streetName;
  }

  public void setStreetName(final String streetName) {
    this.streetName = streetName;
  }

  public String getCity() {
    return city;
  }

  public void setCity(final String city) {
    this.city = city;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(final String postalCode) {
    this.postalCode = postalCode;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(final String country) {
    this.country = country;
  }

  @Override
  public String toString() {
    return streetNumber +
            ", " + streetName +
            ", " + city +
            ", " + postalCode +
            ", " + country
            ;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Address address = (Address) o;

    if (streetNumber != null ? !streetNumber.equals(address.streetNumber) : address.streetNumber != null) return false;
    if (streetName != null ? !streetName.equals(address.streetName) : address.streetName != null) return false;
    if (city != null ? !city.equals(address.city) : address.city != null) return false;
    if (postalCode != null ? !postalCode.equals(address.postalCode) : address.postalCode != null) return false;
    return country != null ? country.equals(address.country) : address.country == null;
  }

  @Override
  public int hashCode() {
    int result = streetNumber != null ? streetNumber.hashCode() : 0;
    result = 31 * result + (streetName != null ? streetName.hashCode() : 0);
    result = 31 * result + (city != null ? city.hashCode() : 0);
    result = 31 * result + (postalCode != null ? postalCode.hashCode() : 0);
    result = 31 * result + (country != null ? country.hashCode() : 0);
    return result;
  }
}
