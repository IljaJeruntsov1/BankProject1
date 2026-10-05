document.addEventListener("DOMContentLoaded", loadAccounts);

async function loadAccounts() {
    try {
        const response = await fetch("/api/accounts", {
            method: "GET",
            credentials: "include"
        });

        if (response.status === 401) {
            window.location.href = "/login.html";
            return;
        }

        if (!response.ok) {
            throw new Error("Не удалось загрузить данные");
        }

        const data = await response.json();

        console.log("Данные от сервера:", data);

        const userName = document.getElementById("userName");

        if (userName) {
            userName.textContent = data.customerName;
        }

        displayAccounts(data.accounts);

    } catch (error) {
        console.error("Ошибка загрузки данных:", error);

        const userName = document.getElementById("userName");
        const totalBalance = document.getElementById("totalBalance");
        const tableBody = document.getElementById("accountsTableBody");

        if (userName) userName.textContent = "Ошибка";
        if (totalBalance) totalBalance.textContent = "Ошибка";

        if (tableBody) {
            tableBody.innerHTML = `
                <tr>
                    <td colspan="5">
                        Не удалось загрузить данные счета
                    </td>
                </tr>
            `;
        }
    }
}


function displayAccounts(accounts) {
    const tableBody = document.getElementById("accountsTableBody");
    const totalBalance = document.getElementById("totalBalance");

    if (!tableBody || !totalBalance) return;

    tableBody.innerHTML = "";

    if (!accounts?.length) {
        tableBody.innerHTML = `
            <tr>
                <td colspan="5">У вас пока нет счетов</td>
            </tr>
        `;

        totalBalance.textContent = "0,00 EUR";
        return;
    }

    let totalAvailable = 0;
    const currency = accounts[0].currency;

    accounts.forEach(account => {
        totalAvailable += Number(account.available);

        const row = document.createElement("tr");

        row.innerHTML = `
            <td>
                <strong>${escapeHtml(account.accountName)}</strong>
                <br>
                ${escapeHtml(account.iban)}
            </td>

            <td>${formatMoney(account.balance)}</td>
            <td>${formatMoney(account.reserved)}</td>
            <td>${formatMoney(account.available)}</td>
            <td>${escapeHtml(account.currency)}</td>
        `;

        tableBody.appendChild(row);
    });

    totalBalance.textContent =
        `${formatMoney(totalAvailable)} ${currency}`;
}


function formatMoney(value) {
    return Number(value).toFixed(2).replace(".", ",");
}


function escapeHtml(value) {
    return String(value)
        .replaceAll("&", "&amp;")
        .replaceAll("<", "&lt;")
        .replaceAll(">", "&gt;")
        .replaceAll('"', "&quot;")
        .replaceAll("'", "&#039;");
}


const logoutButton = document.getElementById("logoutButton");

if (logoutButton) {
    logoutButton.addEventListener("click", logout);
}


async function logout() {
    try {
        await fetch("/auth/logout", {
            method: "POST",
            credentials: "include"
        });
    } catch (error) {
        console.error("Ошибка выхода:", error);
    }

    window.location.href = "/login.html";
}


const newPaymentButton =
    document.getElementById("newPaymentButton");

if (newPaymentButton) {
    newPaymentButton.addEventListener("click", () => {
        console.log("Открываем форму нового платежа");
    });
}