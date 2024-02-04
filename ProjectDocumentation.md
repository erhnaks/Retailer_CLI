# **Documentation**

## **Task 1:**

## GitHub link for development cycle:

[Retailer_CLI - GitHub](https://github.com/erhnaks/Retailer_CLI)

### Branches:

- main
- development
- feature-class
- feature-retailer-business-logic-implementation
- bug-testcase-in-infinite-loop

## Initial Tests Failures:

In order to begin developing test scenarios, I attempted to run the tests only to have them fail. 
I discovered that one test was stuck in an infinite loop, which was the reason tests were failing to finish. 
In order to match the date == Thursday, I also had to adjust another test that involved adding one day to the current date.

Initial Test:

`LocalDate thursday = LocalDate.now();
while (!thursday.getDayOfWeek().equals(DayOfWeek.THURSDAY)) {
thursday.plusDays(1);
}`

After the infinite loop problem was fixed:

`LocalDate thursday = LocalDate.now();
while (!thursday.getDayOfWeek().equals(DayOfWeek.THURSDAY)) {
thursday = thursday.plusDays(1);
}`

Screenshot of the initial test run:

![Screenshot 2024-02-03 122703](https://github.com/erhnaks/String_Calculator/assets/97620234/1ad7ec89-3126-4958-af48-51d3b2e25738)

Another problem with the test testDiscount2For1OnThursdays() is that it was failing because the sum seemed off. 
Would you kindly look at the below? The original test case was manually corrected and commented out. 
Kindly inform me if this was incorrect.

Comparison of the corrected version with the initial test receipt (NB: PLEASE SEE COMMENT):

![image](https://github.com/erhnaks/String_Calculator/assets/97620234/66dcbb76-aab7-4a6e-a1a9-11fa3efe8d3c)

### Running the tests:

![Screenshot 2024-02-04 111114](https://github.com/erhnaks/String_Calculator/assets/97620234/3b43c803-7c5e-4879-b030-7ef98960cd39)

### CLI test-run:

![Screenshot 2024-02-04 112456](https://github.com/erhnaks/String_Calculator/assets/97620234/3990f8b5-de8c-4225-877f-3ff4875e1840)

## **Task 2:**

### React and Spring implementation:

#### Get call Postman response:
`[
  {
    "type": "BOOK",
    "price": 5
  },
  {
    "type": "CD",
    "price": 10
  },
  {
    "type": "DVD",
    "price": 15
  }
]`

#### Post response in Postman returns 201 Created
`curl -X 'POST' \
  'http://localhost:8080/api/trolley' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "items": [
    {
      "type": "BOOK",
      "quantity": 1
    },     {
      "type": "CD",
      "quantity": 2
    }, 
    {
      "type": "DVD",
      "quantity": 3
    }
  ]
}'`

#### Get call to retrieve trollies list with Postman:
`[
  {
    "items": [
      {
        "product": {
          "type": "DVD",
          "price": 15
        },
        "quantity": 3,
        "discount": 0,
        "total": 45
      },
      {
        "product": {
          "type": "BOOK",
          "price": 5
        },
        "quantity": 1,
        "discount": 0,
        "total": 5
      },
      {
        "product": {
          "type": "CD",
          "price": 10
        },
        "quantity": 2,
        "discount": 0,
        "total": 20
      }
    ],
    "totalDiscount": 15
  }
]`

## Retailer initial screen:

![image](https://github.com/erhnaks/Retailer_CLI/assets/97620234/75c24fa5-55ea-46a6-a822-fbb16d0af165)

### Retailer before checkout:

![image](https://github.com/erhnaks/Retailer_CLI/assets/97620234/67365143-535f-4536-8115-ebcd3dcfa167)

### Retailer after checkout:

![image](https://github.com/erhnaks/Retailer_CLI/assets/97620234/327d928b-7842-4cc9-9f32-f594a78b1d8b)

### Retailer checkout 2:

![image](https://github.com/erhnaks/Retailer_CLI/assets/97620234/fa8b1c13-0bd7-4830-8292-8979a8104ca5)

### Retailer checkout 3:

![image](https://github.com/erhnaks/Retailer_CLI/assets/97620234/13707ea5-e087-4cba-a6dc-4a0fed497323)

### Retailer report:

##### Note: The current implementation presents a list of the five latest completed transactions. Clicking on any transaction within this history list updates the displayed summary selections. However, the ability to select multiple transactions from the history list and calculate the total sum of those selected transactions is currently unavailable. Although feature is required as one of task 2 requirement and is intended to be developed. As part of this development process, there will be a learning phase for me to understand the necessary techniques and methodologies to implement this functionality effectively.

![image](https://github.com/erhnaks/Retailer_CLI/assets/97620234/d0a869cb-699c-4e1d-9176-927606db91e5)


Author: Erhan Aksu