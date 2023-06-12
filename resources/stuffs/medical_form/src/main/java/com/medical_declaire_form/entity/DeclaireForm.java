package com.medical_declaire_form.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "declaire_form")
public class DeclaireForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "form_id")
    private int id;
    @Column(name = "declaire_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date declaireDate;
    @Column(name = "name")
    private String personName;
    @Column(name = "birth_year")
    private int birthYear;
    @Column(name = "gender")
    private String gender;
    @Column(name = "country")
    private String country;
    @Column(name = "citizen_id")
    private String citizenId;
    @Column(name = "vehicle")
    private String vehicle;
    @Column(name = "vehicle_number")
    private String vehicleNumber;
    @Column(name = "vehicle_sit_number")
    private String vehicleSitNumber;
    @Column(name = "departure_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date departureDate;
    @Column(name = "return_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date returnDate;
    @Column(name = "visited_places")
    private String visitedPlaces;
    @Column(name = "address_city")
    private String city;
    @Column(name = "address_town")
    private String town;
    @Column(name = "address_ward")
    private String ward;
    @Column(name = "address_street")
    private String street;
    @Column(name = "phone")
    private String phone;
    @Column(name = "email")
    private String email;
    @Column(name = "symptoms_fever")
    private int fever;
    @Column(name = "symptoms_cough")
    private int cough;
    @Column(name = "symptoms_hard_breath")
    private int hardBreath;
    @Column(name = "symptoms_hurt_throat")
    private int hurtThroat;
    @Column(name = "symptoms_vomit")
    private int vomit;
    @Column(name = "symptoms_diarrhea")
    private int diarrhea;
    @Column(name = "symptoms_skin_hemorrhage")
    private int skinHemorrhage;
    @Column(name = "symptoms_skin_rash")
    private int skinRash;
    @Column(name = "contact_animal")
    private int contactAnimal;
    @Column(name = "contact_infected_people")
    private int contactInfectedPeople;

    public DeclaireForm(Date declaireDate, String personName, int birthYear, String gender, String country, String citizenId, String vehicle, String vehicleNumber, String vehicleSitNumber, Date departureDate, Date returnDate, String visitedPlaces, String city, String town, String ward, String street, String phone, String email, int fever, int cough, int hardBreath, int hurtThroat, int vomit, int diarrhea, int skinHemorrhage, int skinRash, int contactAnimal, int contactInfectedPeople) {
        this.declaireDate = declaireDate;
        this.personName = personName;
        this.birthYear = birthYear;
        this.gender = gender;
        this.country = country;
        this.citizenId = citizenId;
        this.vehicle = vehicle;
        this.vehicleNumber = vehicleNumber;
        this.vehicleSitNumber = vehicleSitNumber;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.visitedPlaces = visitedPlaces;
        this.city = city;
        this.town = town;
        this.ward = ward;
        this.street = street;
        this.phone = phone;
        this.email = email;
        this.fever = fever;
        this.cough = cough;
        this.hardBreath = hardBreath;
        this.hurtThroat = hurtThroat;
        this.vomit = vomit;
        this.diarrhea = diarrhea;
        this.skinHemorrhage = skinHemorrhage;
        this.skinRash = skinRash;
        this.contactAnimal = contactAnimal;
        this.contactInfectedPeople = contactInfectedPeople;
    }

    public DeclaireForm() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDeclaireDate() {
        return declaireDate;
    }

    public void setDeclaireDate(Date declaireDate) {
        this.declaireDate = declaireDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCitizenId() {
        return citizenId;
    }

    public void setCitizenId(String citizenId) {
        this.citizenId = citizenId;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getVehicleSitNumber() {
        return vehicleSitNumber;
    }

    public void setVehicleSitNumber(String vehicleSitNumber) {
        this.vehicleSitNumber = vehicleSitNumber;
    }

    public String getVisitedPlaces() {
        return visitedPlaces;
    }

    public void setVisitedPlaces(String visitedPlaces) {
        this.visitedPlaces = visitedPlaces;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getFever() {
        return fever;
    }

    public void setFever(int fever) {
        this.fever = fever;
    }

    public int getCough() {
        return cough;
    }

    public void setCough(int cough) {
        this.cough = cough;
    }

    public int getHardBreath() {
        return hardBreath;
    }

    public void setHardBreath(int hardBreath) {
        this.hardBreath = hardBreath;
    }

    public int getHurtThroat() {
        return hurtThroat;
    }

    public void setHurtThroat(int hurtThroat) {
        this.hurtThroat = hurtThroat;
    }

    public int getVomit() {
        return vomit;
    }

    public void setVomit(int vomit) {
        this.vomit = vomit;
    }

    public int getDiarrhea() {
        return diarrhea;
    }

    public void setDiarrhea(int diarrhea) {
        this.diarrhea = diarrhea;
    }

    public int getSkinHemorrhage() {
        return skinHemorrhage;
    }

    public void setSkinHemorrhage(int skinHemorrhage) {
        this.skinHemorrhage = skinHemorrhage;
    }

    public int getSkinRash() {
        return skinRash;
    }

    public void setSkinRash(int skinRash) {
        this.skinRash = skinRash;
    }

    public int getContactAnimal() {
        return contactAnimal;
    }

    public void setContactAnimal(int contactAnimal) {
        this.contactAnimal = contactAnimal;
    }

    public int getContactInfectedPeople() {
        return contactInfectedPeople;
    }

    public void setContactInfectedPeople(int contactInfectedPeople) {
        this.contactInfectedPeople = contactInfectedPeople;
    }
}