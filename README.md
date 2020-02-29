# spring-crm-rest-security

REST API with Spring Boot and Spring Security. It uses inMemoryAuthentication for employees.

1. Run your REST application.

2. In Postman access the REST endpoint: GET /api/employees
You will initial get a 401 error: Unauthorized

3. To resolve this, in Postman, click the "Authorization" section of the request.

4. In the "Type" drop-down list, select "Basic Auth"

5. Employee credentials:

username: john
password: test123
role: EMPLOYEE

username: mary
password: test123
role: MANAGER

username: susan
password: test123
role: ADMIN

6. Using Postman send the request again
If the user is authenticated, then they'll get the results of REST request. If not, they'll
see a 401 error

EMPLOYEE role can perform following:
1. Get a list of all employees.  GET /api/employees
2. Get a single employee.  GET /api/employees/{employeeId}

MANAGER role can perform following
1. Add a new employee.  POST /api/employees
2. Update an existing employee.  PUT /api/employees

 ADMIN role can perform following
1. Delete a employee.  DELETE /api/employees/{employeeId}
2. View Application metrics. GET /actuator
