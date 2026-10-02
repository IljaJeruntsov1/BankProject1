const API_URL = "";


/* =====================================
   APPLICATION
===================================== */

const applicationForm = document.getElementById("applicationForm");

if (applicationForm) {

    applicationForm.addEventListener("submit", async function (event) {

        event.preventDefault();

        const message = document.getElementById("applicationMessage");

        const data = {
            firstName: document.getElementById("firstName").value,
            lastName: document.getElementById("lastName").value,
            dateOfBirth: document.getElementById("dateOfBirth").value,
            email: document.getElementById("email").value,
            phone: document.getElementById("phone").value
        };

        console.log("Sending application:");
        console.log(JSON.stringify(data));

        try {

            const response = await fetch(
                API_URL + "/application/create",
                {
                    method: "POST",

                    headers: {
                        "Content-Type": "application/json"
                    },

                    body: JSON.stringify(data)
                }
            );

            const result = await response.text();

            console.log("Status:", response.status);
            console.log("Response:", result);

            if (response.ok) {

                message.className = "success-message";

                message.innerText =
                    "Заявка успешно отправлена.";

                applicationForm.reset();

            } else {

                message.className = "error-message";

                message.innerText =
                    result || "Не удалось отправить заявку.";
            }

        } catch (error) {

            console.error("Application error:", error);

            message.className = "error-message";

            message.innerText =
                "Не удалось подключиться к серверу.";
        }

    });
}


/* =====================================
   LOGIN
===================================== */

const loginForm = document.getElementById("loginForm");

if (loginForm) {

    loginForm.addEventListener("submit", async function (event) {

        event.preventDefault();

        const message = document.getElementById("loginMessage");

        const username =
            document.getElementById("username").value;

        const password =
            document.getElementById("password").value;

        const data = {
            username: username,
            password: password
        };

        console.log("Login:", username);

        try {

            const response = await fetch(
                API_URL + "/auth/login",
                {
                    method: "POST",

                    headers: {
                        "Content-Type": "application/json"
                    },

                    body: JSON.stringify(data)
                }
            );

            const result = await response.text();

            console.log("Login status:", response.status);
            console.log("Login response:", result);

            if (response.ok) {

                localStorage.setItem(
                    "username",
                    username
                );

                window.location.href =
                    "/dashboard.html";

            } else {

                message.className =
                    "error-message";

                message.innerText =
                    result ||
                    "Неверный логин или пароль.";
            }

        } catch (error) {

            console.error("Login error:", error);

            message.className =
                "error-message";

            message.innerText =
                "Не удалось подключиться к серверу.";
        }

    });
}


/* =====================================
   DASHBOARD
===================================== */

const dashboardUsername =
    document.getElementById("dashboardUsername");

if (dashboardUsername) {

    const username =
        localStorage.getItem("username");

    if (!username) {

        window.location.href =
            "/login.html";

    } else {

        dashboardUsername.innerText =
            username;
    }
}


/* =====================================
   LOGOUT
===================================== */

function logout() {

    localStorage.removeItem("username");

    window.location.href =
        "/login.html";
}