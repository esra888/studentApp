## Student Registration System :woman_student: :man_student:

An application of student registration system.

The application provide this tasks:

- Listing the offered courses, defining a new course and updating existing courses
- Listing the students defined in the system, defining a new student and updating the existing student
- Listing of courses by a student, dropping a course, enrolling in a course
- Preventing students from enrolling more than the maximum number of students (quota) specified in the course description while taking the course and getting an error in this case.
- Listing the students registered for the courses defined in the system. 



### Requiremets 🔧

- Java version 8 or higher.
- Maven.

## Installation 🔌

1. Press the Fork button (top right the page) to save copy of this project on your account.
2. Download the repository files (project) from the download section or clone this project by typing in the bash the following command:

```
git clone https://github.com/esra888/DemoApplication.git
```

1. Imported it in Intellij IDEA or any other Java IDE.
2. Run the application :D

### Usage

In this code have used two entities called Student and Course, entities have a @ManyToMany relationship with each other. You can see this relationship below:

Course.java : 

```java
@ManyToMany(targetEntity = Student.class, mappedBy = "courses",
        cascade = {CascadeType.PERSIST, CascadeType.DETACH,CascadeType.MERGE,
                CascadeType.REFRESH})
private List<Student> students;
```

Student.java:

```java
@ManyToMany(targetEntity = Course.class, cascade = {CascadeType.PERSIST, CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH} )
@JoinTable(
        name="t_student_courses",
        joinColumns=
        @JoinColumn( name="student_id", referencedColumnName="id"),
inverseJoinColumns=@JoinColumn(name="course_id", referencedColumnName="id"))
private List<Course> courses;
```

#### Running The Test 

##### For test and manipulate Student Entity :

In Postman ( [postman](https://www.postman.com/downloads/) ) you have to type the following statements: 

- **For see all students :**

  (../student/all)

- **For see courses of student has with given id:**

  (../student/{id}/courses)

- **For update student with given id :**

  (../student/update/{id})

- **For create new student :**

  (../student/create)

- **For delete student with given id :**

  (../student/delete/{id})

##### For test and manipulate Course Entity : 

- **For see all courses :**

  (../courses/all)

- **For see students of course has with given id:**

  (../courses/{id}/students)

- **For update course with given id :**

  (../courses/update/{id})

- **For create new course :**

  (../courses/create)

- **For delete course with given id :**

  (../courses/delete/{id})
