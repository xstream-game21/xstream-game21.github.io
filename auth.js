// Authentication Manager for GraphicInfinity
// Manages session login (temporary) vs persistent registration (permanent)

function isUserLoggedIn() {
    return sessionStorage.getItem('userLoggedIn') === 'true';
}

function setUserLoggedIn(username) {
    sessionStorage.setItem('userLoggedIn', 'true');
    sessionStorage.setItem('username', username);
    console.log('User logged in:', username);
}

function logout() {
    const username = sessionStorage.getItem('username');
    console.log('User logged out:', username);
    
    sessionStorage.removeItem('userLoggedIn');
    sessionStorage.removeItem('username');
    sessionStorage.removeItem('userEmail');
    sessionStorage.removeItem('cartData');
    
    alert('You have been logged out. See you soon! 👋');
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

// Get current user info
function getCurrentUser() {
    if (isUserLoggedIn()) {
        return {
            username: sessionStorage.getItem('username'),
            email: sessionStorage.getItem('userEmail')
        };
    }
    return null;
}

// Get user from permanent database
function getPermanentUserInfo(username) {
    return userDB.getUser(username);
}

// Check if user exists in database (for verification)
function userExistsInDatabase(username) {
    return userDB.userExists(username);
}

// Run on page load
document.addEventListener('DOMContentLoaded', displayUsername);