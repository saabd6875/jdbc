// we placed all the JS code into a separate file

let stud = null;

// //stand-alone call that triggers every time the index.html file is loaded
// $.get("http://localhost:8080/hello", function(data){ // GET call towards our java endpoint: "/hello" -> you can find it in BasicController class
//     console.log(data); // we print the info returned by the Java endpoint
// })
// //stand-alone call that triggers every time the index.html file is loaded
// $.get("http://localhost:8080/getUniversityList", function(data){ // GET call towards our java endpoint: "/getUniversityList" -> you can find it in BasicController class
//     console.log(data);
//     populateDropDown(data);  // we created a function that dinamically takes the University objects and creates a drop down in html
// })

//this function interogatted the "/student" endpoint and receives a student object
//that is later shown in the browser by adding HTML
function  getStudent(){
    $.get("http://localhost:8080/student", function(data){  //{John 23 Oslomet}
        console.log(data);
        stud = {
            name: data.name, //John
            age: data.age, //23
            university: data.university //OsloMet
        }
        // this is where we add HTML over the values we have inside the object and we construct
        //the following structure: <p>John</p> <p>23</p> <p>Oslomet</p>
        //by using document.getElementById we inject the above html code into index.html
        document.getElementById("stud1").innerHTML = "<p>" +stud.name + "</p>" +
            "<p>" +stud.age + "</p>" +
            "<p>" +stud.university + "</p>"
    })
}

function getListOfStudents(){  // same behaviour as the above, but this time we receive an array of students
    $.get("http://localhost:8080/getStudents", function(data){  //this GET call will retrieve from "/students" endpoint a list
        console.log(data);
        // need to read the array of objects that we received
        // [stud1,stud2,stud3]
        let dinamicHtml= "<ul>";
        data.forEach(function(stud){
            // dynamically create html arround the list of object
            dinamicHtml +="<li>" +stud.name + " " +stud.age + " "+stud.university +
                "<button onclick='updateStudent(" + stud.id + ")'>Edit</button>" +
                "<button onclick='deleteStudent(" + stud.id + ")'>Delete</button>" +"</li>"
        })
        dinamicHtml+="</ul>"
        document.getElementById("students").innerHTML = dinamicHtml;
    })
}

//deprecated - we used this method to send a hardcoded object towards the Java endpoint
// function sendStudent(){
//     student = {    //here we instantiate a simple JS student obj
//         "name": "John",
//         "age": 35,
//         "university":"Oslomet"
//     }
//     // here we use "$.post" to send this package towards a java endpoint
//     // please notice "student" is considered a parameter to be sent
//     $.post("http://localhost:8080/receiveStudent", student, function(data){
//         console.log(data);
//     })
// }

//the advanced method to send objects towards Java
// we use the inputs filled in by the user
function sendStudentFromInput(){
    student = {
        "name": document.getElementById("name").value,
        "age": document.getElementById("age").value,
        "university": document.getElementById("university").value
    }
    console.log(student); //good for debugging in case the elements from student are no
    $.post("http://localhost:8080/insertStudentInDB",student, function (data){})
}

function updateStudent(id){
    document.getElementById("idStud").innerHTML = id;
    $.get("http://localhost:8080/getStudentsFromDB?id="+id, function(data){
        document.getElementById("nameEdit").value = data.name;
        document.getElementById("ageEdit").value = data.age;
        document.getElementById("universityEdit").value = data.university;
    })


    console.log(id);
    // go to new page to open form ->studentForm.html
    // pre-fill the info inside form->sendinfo in the new page
    // make the changes in the form
    // send it to java -> this should be as it was
}

function deleteStudent(id){
    $.ajax({
        url: 'http://localhost:8080/deleteStudent?id='+id,
        type: 'DELETE',
        success: function(result) {
            // Do something with the result
        }
    });
}

function updateStudentInDb(){
        student = {
            "id": document.getElementById("idStud").innerHTML,
            "name": document.getElementById("nameEdit").value,
            "age": document.getElementById("ageEdit").value,
            "university": document.getElementById("universityEdit").value
        }
        console.log( document.getElementById("idStud").value);
        console.log(student); //good for debugging in case the elements from student are no
        $.post("http://localhost:8080/updateStudent",student, function (data){})

}
// function sendStudentFromInput2(){
//     student = {
//         "name": document.getElementById("name2").value,
//         "age": document.getElementById("age2").value,
//         "university": document.getElementById("university2").value,
//     }
//     console.log(student); //good for debugging in case the elements from student are no
//     $.post("http://localhost:8080/receiveStudent",student, function (data){})
// }

//creates html based on the objects it receives - a similar aproach has been applied earlier, the element of novelty here
// is the fact that we created a new function to write the code in
function populateDropDown(data){
    let html = "<select id='university2'>";
    for (const univ of data){
        html+="<option>"+ univ.name+"</option>";
    }
    html+= "</select>"
    document.getElementById("university2").innerHTML = html;
}