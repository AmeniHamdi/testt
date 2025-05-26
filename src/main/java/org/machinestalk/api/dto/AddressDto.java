package org.machinestalk.api.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class AddressDto {

  private String streetNumber;

  String streetName;

  @NotEmpty
  @Size(max = 40)
  private String city;

  @NotEmpty
  @Size(max = 5)
  private String postalCode;

  @NotEmpty
  @Size(max = 3)
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
}
