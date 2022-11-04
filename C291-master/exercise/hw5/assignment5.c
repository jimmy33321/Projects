/*
 * Name: Jimmy Yu
 * Username: jimmyu
 * Date Created: 4/15/14
 * Assignment: Assignment 5 Part 2
*/

#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>

//Declare DOB Structure (Date of Birth)
struct DOB {
	int month;
	int day;
	int year;
};
typedef struct DOB DOB;

//Declare FamilyHistory Structure
struct FamilyHistory {
	bool heart; //heart problems
	bool blood; //blood pressure
	bool cancer;
};
typedef struct FamilyHistory FamilyHistory;

//Declare HealthProfile structure
struct HealthProfile {
	char firstName[15];
	char lastName[15];
	char gender;
	DOB birth; //date of birth struct
	int height; //in inches
	double weight; //in pounds
	FamilyHistory history; //family history struct
};
typedef struct HealthProfile HealthProfile;

//Function Prototypes
int calculateAge(HealthProfile p);
double calculateBMI(HealthProfile p);
void calculateHeartRate(HealthProfile p);

//function calculates a person's age given their HealthProfile
int calculateAge(HealthProfile p) {
	int result = (2018 - p.birth.year);
	return result;
}

//function calculates a person's BMI given their HealthProfile
//and prints the BMI values chart
double calculateBMI(HealthProfile p) {
	//print BMI values
	printf("BMI Values\n");
	printf("Underweight: less than 18.5\n");
	printf("Normal:      between 18.5 and 24.9\n");
	printf("Overweight:  between 25 and 29.9\n");
	printf("Obese:       30 or greater\n");
	
	double result;
	result = ((p.weight * 703) / (p.height * p.height));
	return result;
}

//function calculates a person's heart rate and target heart rate
//given their HealthProfile and check if their heart rate is outside
//the target range as well as if the person has a family history of
//heart problems or blood pressure
void calculateHeartRate(HealthProfile p) {
	double max;
	max = (220 - calculateAge(p));
	printf("Your maximum heart rate is %.2lf\n",max);
	double rangeMin;
	double rangeMax;
	rangeMin = ((max * .01) * 50);
	rangeMax = ((max * .01) * 75);
	printf("Your target heart rate range is between %.2lf and %.2lf\n",rangeMin,rangeMax);
	if (max < rangeMin || max > rangeMax) {
		if (p.history.heart == true || p.history.blood == true) {
			printf("Your heart rates is outside the target range and\n");
			printf("you have a family history of heart problems and/or blood pressure.\n");
			printf("You should see your physician.\n");
		}
	}
}

int main(void) {
	//create necessary structs for holding input
	HealthProfile person;
	DOB personDOB;
	FamilyHistory personFH;
	
	char input[15];
	int input2;
	double input3;
	char input4;
	
	//start getting input
	printf("Please enter your first name.\n");
	scanf("%s",&input);
	strcpy(person.firstName, input);
	printf("Please enter your last name.\n");
	scanf("%s",&input);
	strcpy(person.lastName, input);
	printf("Please enter your gender (m/f).\n");
	getchar();
	scanf("%c",&input4);
	person.gender = input4;
	
	//date of birth input
	printf("Please enter your birth month (1-12).\n");
	scanf("%d",&input2);
	personDOB.month = input2;
	printf("Please enter your birth day.\n");
	scanf("%d",&input2);
	personDOB.day = input2;
	printf("Please enter your birth year.\n");
	scanf("%d",&input2);
	personDOB.year = input2;
	person.birth = personDOB;
	
	//continue getting input
	printf("Please enter your height in inches.\n");
	scanf("%d",&input2);
	person.height = input2;
	printf("Please enter your weight in pounds.\n");
	scanf("%lf",&input3);
	person.weight = input3;
	
	//family history input
	printf("Please type (y/n) if you have family history of heart problems.\n");
	getchar();
	scanf("%c",&input4);
	if (input4 == 'y') {
		personFH.heart = true;
	} else {
		personFH.heart = false;
	}
	printf("Please type (y/n) if you have family history of blood pressure.\n");
	getchar();
	scanf("%c",&input4);
	if (input4 == 'y') {
		personFH.blood = true;
	} else {
		personFH.blood = false;
	}
	printf("Please type (y/n) if you have family history of cancer.\n");
	getchar();
	scanf("%c",&input4);
	if (input4 == 'y') {
		personFH.cancer = true;
	} else {
		personFH.cancer = false;
	}
	person.history = personFH;
	
	//done getting input, calculate some things, check some stuff and print some results
	input2 = calculateAge(person);
	printf("Your age is %d.\n",input2);
	input3 = calculateBMI(person);
	printf("Your BMI is %.2lf\n",input3);
	if (input3 < 18.5 && person.history.cancer == true) {
		printf("You are underweight and have a family history of cancer.\n");
		printf("You should see your oncologist.\n");
	} else if (input3 >= 30 && person.history.cancer == true) {
		printf("You are obese and have a family history of cancer.\n");
                printf("You should see your oncologist.\n");
	}
	calculateHeartRate(person);
	
	return (0);
}
