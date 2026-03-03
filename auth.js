// Authentication Manager
function isUserLoggedIn() {
    return sessionStorage.getItem('userLoggedIn') === 'true';
}

function setUserLoggedIn(username) {
    sessionStorage.setItem('userLoggedIn', 'true');
    sessionStorage.setItem('username', username);
}

function logout() {
    sessionStorage.removeItem('userLoggedIn');
    sessionStorage.removeItem('username');
    sessionStorage.removeItem('cartData');
    window.location.href = 'login.html';
}

function requireLogin() {
    if (!isUserLoggedIn()) {
        alert('Please log in to continue shopping!');
        window.location.href = 'login.html';
        return false;
    }
    return true;
}

function displayUsername() {
    if (isUserLoggedIn()) {
        const username = sessionStorage.getItem('username');
        const userElements = document.querySelectorAll('.menu-username, .profile-name');
        userElements.forEach(el => {
            if (el) el.textContent = username;
        });
    }
}

// Run on page load
document.addEventListener('DOMContentLoaded', displayUsername);