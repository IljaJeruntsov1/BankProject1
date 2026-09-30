   document.getElementById("registerForm").addEventListener("submit", async function (event) {

       event.preventDefault();

       const data = {
           firstName: document.getElementById("firstName").value,
           lastName: document.getElementById("lastName").value,
           dateOfBirth: document.getElementById("dateOfBirth").value || null,
           email: document.getElementById("email").value,
           phone: document.getElementById("phone").value,
           username: document.getElementById("username").value,
           password: document.getElementById("password").value
       };

       try {

           const response = await fetch("/api/auth/register", {
               method: "POST",
               headers: {
                   "Content-Type": "application/json"
               },
               body: JSON.stringify(data)
           });

           const result = await response.json();

           if (response.ok) {
               document.getElementById("message").textContent =
                   "Регистрация успешна! Customer Number: ";

               document.getElementById("registerForm").reset();

           } else {

               document.getElementById("message").textContent =
                   "Ошибка регистрации";

               console.log(result);
           }

       } catch (error) {

           document.getElementById("message").textContent =
               "Сервер недоступен";

           console.error(error);
       }

   });