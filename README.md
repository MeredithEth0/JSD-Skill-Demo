
# Code Skill Demo for HMCTS Junior Software Developer

## Overview

This is a simple task management app for caseworkers. It contains two menus; One to create cases, and the other to view active cases.

## Instructions

To experience this demo both the back-end server and front-end server must be run. To do this please follow these instructions below:

- CD to "task-api" in terminal.
- Run command "./mvnw spring-boot:run"
- Allow this to finish running, it will confirm to you in the terminal that it is live.
- To run the front-end you will need to host the "index.html". I have used the VSC extension "Live server" to do this.
- This should then open the web interface automatically for you.

## Features
This demo contains the following features:
- Create tasks with validation (enforces field entry)
- Real time task list
- Responsive layout (Uses media queries to adapt page layout for mobile use)
- Data persists between request and page refreshes
- A versatile web interface

## Tech decisions

### Why use a H2 database?
I have used a H2 database due to this project being a demo, I did not feel it would be suitable to build a full POSTgre SQL server for this as it would potentially make it more difficult for prospective employers or reviewers to run this demo. The H2 fits the need quite well while still showing knowledge of how to work with a database to store information.

### Why use a web based interface?
I have used a web based interface as it more aligns with my existing skill and preference towards building front end applications. It allows the application (in service situations) to be highly accessible by not enforcing an application be installed initially to interact with this system. It is simply a case of navigating to a web page to access the system. This in my opinion improves the UX and makes the system more accessible.

### Why use a simpler vanilla JS setup for the interface?
I have set the interface this way as the core of this project is to show my skill with Java to design an API to allow form entry to create a case and store that case in a database where the list of cases can be viewed on said interface. I felt that using a more advanced library for the interface would draw away from the point of this project.

### Why use Spring Boot?
I used Spring Boot because it gives a sensible, opinionated structure for a small API without a lot of boilerplate, while still being close to how real production services are built.

## Testing

### Backend testing
For back-end testing I focused primarily on two things:
- Testing the service logic in isolation
- Checking that the whole Spring Boot application still starts correctly after changes.

To run these tests for yourself please use the following commands in the terminal.
```
> cd task-api
. ./mvnw test
```

The main back-end test is TaskServiceTest. It uses JUnit 5 with a mocked TaskRepository, so no real database is needed. It sends a sample TaskRequest into TaskService.createTask and checks that save is called and that the returned TaskResponse has the expected data. This confirms the core “create task” logic works correctly end to end in the service layer.

TaskApiApplicationTests is a simple integration test using @SpringBootTest that just starts the full Spring Boot application. If the context fails to start because of a wiring or configuration problem, this test will fail. Together these tests provide a small safety net: if the create logic or configuration is broken, the test suite will catch it early.

### Frontend testing
To test the front-end I have conducted a lighthouse test. The score of the lighthouse test is as follows.

On the initial test the scores were:

![Lighthouse test 1 scores](image.png)

All scores are showing as good except for the accessibility rating. Upon investigation the lighthouse test reports that the accessibility issues are as follows:
#### Contrast:
"Background and foreground colors do not have a sufficient contrast ratio."
#### Names & Labels:
"Form elements do not have associated labels" <br>
"Select elements do not have associated label elements"

To resolve these issues I implemented two changes.<br>
Firstly I changed the text colour and added an outer box around the text. This allows for further distinction from the text to the background.

Secondly I implemented labels on the form elements and select elements to satisfy the other accessibility issues.

I then was able to run the second Lighthouse test which returned scores as such.

![Lighthouse test 2 scores](image-1.png)

This has resolved the issues lighthouse had brought up making the interface more accessible for the user.

The SEO issues that have been reported on the lighthouse tests have been ignored. The issue comes from a requirement for meta description which would not affect an internally hosted interface such as this.

> I have attached both reports to this project and they are referenced below.
> - [Lighthouse test report 1](<External Documents/Lighthouse test 1.pdf>)
> - [Lighthouse test report 2](<External Documents/Lighthouse test 2.pdf>)
## Simplified Project Structure

Below is a small diagram showing a simplified structure of my project.

```
DTS-JSD-EM/
├── task-api/ # Spring Boot REST API
│ ├── pom.xml # Maven config
│ ├── src/main/java/... # Controllers + Services + Entities
│ └── src/test/... # Unit Tests (2 passing)
├── task-frontend/ # HTML/CSS/JS Frontend
│ ├── index.html
│ ├── style.css
│ └── script.js
└── README.md
```