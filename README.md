### **Backend Server:**

1. Mave

2. Spring boot for web service 

3. Embedded relational database for data storage (MySQL) 

4. JPA as an Object Relational Mapping tool (Hibernate as implementation) 

5. Spring Security oauth2 for user authentication and authorization checking 

7. Spring REST Controller for building API endpoints 

8. Swagger 2 used to API Testing

### **Client Service:**

1. ReactJs for client service

### **Admin User credentials:**

by default system will create data tables and a user when application running

username: **admin** 

password: **admin**

### **System Installation guideline:**



**Host deployment:**

1. Project clone from **github** by using the given URL **https://github.com/ahsumon85/microblogging-assessment.git**
2. Need to install **MySQL** Database to run Backend service
3. Follow the **micro-blogging-backend/src/main/resources/application.properties** and make sure database **username** & **password**
4. Then will run using IDE or Commanad Line
5. Follow the link to see API **http://localhost:8082/swagger-ui.html**

**Docker deployment:**

1. Project clone from **github** by using the given URL **https://github.com/ahsumon85/microblogging-assessment.git**
2. Go to the **micro-blogging-backend/src/main/resources/application.properties** and change **mysqldb** instead of **localhost**
3. Go to the $ cd **micro-blogging-backend/** and **$ mav clean install** to build java project
4. **$ pwd**
   /home/ahasan/Desktop/microblogging-assessment
5. **$ docker-compose up**
6. **$ docker ps**
7.  Follow the link to view client UI **http://localhost:8086**

