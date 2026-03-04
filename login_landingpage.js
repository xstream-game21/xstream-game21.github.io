const container = document.querySelector('.container');
const LoginLink = document.querySelector('.SignInLink');
const RegisterLink = document.querySelector('.SignUpLink');

function clearLoginError() {
    const errorDiv = document.getElementById('loginError');
    if (errorDiv) {
        errorDiv.style.display = 'none';
        errorDiv.innerText = '';
    }
}

function clearRegisterError() {
    const errorDiv = document.getElementById('registerError');
    const successDiv = document.getElementById('registerSuccess');
    const strengthStatus = document.getElementById('password-strength-status');
    const matchStatus = document.getElementById('password-match-status');

    if (errorDiv) {
        errorDiv.style.display = 'none';
        errorDiv.innerText = '';
    }
    if (successDiv) {
        successDiv.style.display = 'none';
        successDiv.innerText = '';
    }
    if (strengthStatus) {
        strengthStatus.style.display = 'none';
        strengthStatus.className = 'password-strength-status';
        strengthStatus.innerText = '';
    }
    if (matchStatus) {
        matchStatus.style.display = 'none';
        matchStatus.className = 'password-match-status';
        matchStatus.innerText = '';
    }
}

// clear errors on load
clearLoginError();
clearRegisterError();

// show register panel
RegisterLink.addEventListener('click', () => {
    container.classList.add('active');
    clearLoginError();
});

// show login panel
LoginLink.addEventListener('click', () => {
    container.classList.remove('active');
    clearRegisterError();
});

// clear errors on load
clearLoginError();