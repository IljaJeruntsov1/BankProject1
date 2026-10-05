document.addEventListener("DOMContentLoaded", function () {

    const loginForm =
        document.getElementById("loginForm");

    const loginMessage =
        document.getElementById("loginMessage");


    loginForm.addEventListener(
        "submit",
        async function (event) {

            event.preventDefault();

            const username =
                document.getElementById("username").value;

            const password =
                document.getElementById("password").value;


            loginMessage.textContent =
                "Выполняется вход...";


            try {

                const response = await fetch(
                    "/auth/login",
                    {
                        method: "POST",

                        headers: {
                            "Content-Type": "application/json"
                        },

                        credentials: "include",

                        body: JSON.stringify({
                            username: username,
                            password: password
                        })
                    }
                );


                if (!response.ok) {

                    const message =
                        await response.text();

                    loginMessage.textContent =
                        message || "Неверный логин или пароль";

                    return;
                }


                // Логин успешен
                window.location.href =
                    "/dashboard.html";

            } catch (error) {

                console.error(
                    "Ошибка входа:",
                    error
                );

                loginMessage.textContent =
                    "Ошибка соединения с сервером";
            }

        }
    );

});