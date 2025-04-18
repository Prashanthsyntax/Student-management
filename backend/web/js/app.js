document.getElementById("studentForm").addEventListener("submit", async function (e) {
    e.preventDefault();
  
    const form = e.target;
  
    const data = {
      id: form.id.value,
      name: form.name.value,
      email: form.email.value,
      age: form.age.value,
      department: form.department.value
    };
  
    try {
      const response = await fetch("http://localhost:8080/student-management/student", {
        method: "POST",
        headers: {
          "Content-Type": "application/x-www-form-urlencoded"
        },
        body: new URLSearchParams(data)
      });
  
      const result = await response.text();
      alert(result);
      form.reset();
    } catch (error) {
      alert("Error adding student: " + error.message);
    }
  });
  