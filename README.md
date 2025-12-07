# Spring Boot Basics API  
A simple Spring Boot project demonstrating basic REST API endpoints using `@RestController`, `@RequestParam` and CRUD operations.

---

## Features
This project includes simple examples for learning Spring Boot fundamentals:

- `/sum` → Add two numbers  
- `/info` → Return a simple JSON object 
- `/greet` → Greeting with RequestParam  
- `/upper` → Convert text to uppercase  
- `/random` → Random number (1–100)  

Perfect for beginners learning Spring Boot REST APIs.


## Update 
### **1. Migrated Storage From HashMap to Course Object**  
Refactored the previous implementation that used a `HashMap<Integer, Course>` and migrated it into a proper **Course object list**, following clean OOP design.

### **2. Refactored All CRUD APIs**
- Updated `Create`, `GetAll`, `GetById`, `Update`, and `Delete` methods.  
- Improved response handling with `ResponseEntity`.  
- Added `createdDate`, `updatedDate`, and `isActive` fields.  



# How to Download & Run the Application

Follow the steps below to set up and run this Spring Boot application locally with MySQL.

---

## 1. Clone the Repository

```bash
git clone https://github.com/Hjufaili/SpringBoot.git
cd SpringBoot
```



## 2. Build the Project 

```bash
mvn clean install
```



## 3. Configure MySQL Database

```sql
CREATE DATABASE courseDBDataBase;
```



## 4. Update application.properties

```css
src/main/resources/application.properties
```
Replace YOUR_USERNAME and YOUR_PASSWORD with your real MySQL credentials



## 5. Run the Application

#### Option A — Run with Maven
```bush
mvn spring-boot:run
```

#### Option B — Run the JAR file
```bush
java -jar target/demo-0.0.1-SNAPSHOT.jar
```
