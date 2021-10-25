# Covid-tracking-backend
With Corona spreading around the world you might be in the situation where you need to tell authorities which
people you have met the last 14 days. This backend provides various API's for achieving that

# Features implemented
 - The user cann add people that he/she met at a specific day
 - User can add the location where he/she met that person
 - (optional) The user can group the list of “meetings” by date or person

# End points

## 1. Add Primary contact 

URL : http://localhost:8080/word/get

Method : POST

Input : JSON Object  

Sample Input :
{
	"contactName" : "Ross",
	"date" :	"06/11/2021",
	"userId":"amru1994"
}

Sample Output: 
{
    "contactId": 2,
    "userId": "amru1994",
    "contactName": "Ross",
    "location": null,
    "date": "06/11/2021"
}

## 2.  Add location

URL :  http://localhost:8080/covidtrack/update 

Method : PUT

Input : JSON Object  

Sample Input :
{
    "contactId": 2,
    "location": "Berlin"
}

Sample Output: 

{
    "contactId": 2,
    "userId": "amru1994",
    "contactName": "Ross",
    "location": "Berlin",
    "date": "06/11/2021"
}

## 3. Get primary contact by type (date/person)

URL : http://localhost:8080/covidtrack/getByType

Method : POST

Input : JSON Object  

Sample Input :
{
	"userId" : "amru1994",
	"type":"name",
	"searchPerson": "Ross"
}
or
{
	"userId" : "amru1994",
	"type":"name",
	"searchDate": "06/11/2021"
}

Sample Output: 
[{
    "contactId": 2,
    "userId": "amru1994",
    "contactName": "Ross",
    "location": "Berlin",
    "date": "06/11/2021"
}]

## 4. Get all contacts

URL : http://localhost:8080/covidtrack/getall/{USERID}

Method : GET  

Input : JSON Object  

Sample Output: 
[{
    "contactId": 2,
    "userId": "amru1994",
    "contactName": "Ross",
    "location": "Berlin",
    "date": "06/11/2021"
}]
