package entities;

import values.*;

public class Student {

    private FirstName firstName;
    private LastName lastName;
    private MobileNumber mobileNumber;
    private Gender gender;

    private Student(FirstName firstName, LastName lastName, MobileNumber mobileNumber, Gender gender){
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobileNumber = mobileNumber;
        this.gender = gender;
    }

    public static Student createValidStudent(){
        return new Student(new FirstName("firstName"), new LastName("lastName"), new MobileNumber("1231231231"), Gender.MALE);
    }

    public FirstName getFirstName() {
        return firstName;
    }

    public Gender getGender() {
        return gender;
    }

    public LastName getLastName() {
        return lastName;
    }

    public MobileNumber getMobileNumber() {
        return mobileNumber;
    }
}
