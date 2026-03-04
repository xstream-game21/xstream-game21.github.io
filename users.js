// User Database Manager - Stores registered users in localStorage with persistence
class UserDatabase {
    constructor() {
        this.usersKey = 'graphicinfinity_users';
        this.initializeDatabase();
    }

    initializeDatabase() {
        if (!localStorage.getItem(this.usersKey)) {
            localStorage.setItem(this.usersKey, JSON.stringify({}));
        }
    }

    registerUser(username, email, password) {
        const users = this.getAllUsers();
        
        // name validation 
        if (!username || username.trim() === '') {
            return { success: false, message: 'Username cannot be empty!' };
        }

        // Check if username already exists (kung wala, create new sa temporary database, click lang ung file)
        if (users[username]) {
            return { success: false, message: `Username "${username}" is already taken! Please choose a different username.` };
        }

        // Check if email already exists
        for (let user in users) {
            if (users[user].email.toLowerCase() === email.toLowerCase()) {
                return { success: false, message: 'Email already registered! Please use a different email or log in.' };
            }
        }

        // Validation of password
        if (password.length < 6) {
            return { success: false, message: 'Password must be at least 6 characters long.' };
        }

        // Store user permanently in localStorage (hindi papo ito backend database ng MySQL)
        users[username] = {
            username: username,
            email: email,
            password: this.hashPassword(password), // Hash password for security
            createdAt: new Date().toISOString(),
            lastLogin: null
        };

        localStorage.setItem(this.usersKey, JSON.stringify(users));
        console.log('User registered successfully:', username);
        return { success: true, message: 'Account created successfully! Please log in.' };
    }

    loginUser(username, password) {
        const users = this.getAllUsers();

        // Check if username exists
        if (!users[username]) {
            return { success: false, message: `Username "${username}" not found! Please sign up first.` };
        }

        // Check password
        if (!this.verifyPassword(password, users[username].password)) {
            console.log('Wrong password attempt for:', username);
            return { success: false, message: 'Incorrect password! Please try again.' };
        }

        // Update last login
        users[username].lastLogin = new Date().toISOString();
        localStorage.setItem(this.usersKey, JSON.stringify(users));

        console.log('Login successful:', username);
        return { success: true, message: 'Login successful!', user: users[username] };
    }

    // Simple password hashing (for production, use bcrypt)
    hashPassword(password) {
        let hash = 0;
        if (password.length === 0) return hash.toString();
        for (let i = 0; i < password.length; i++) {
            const char = password.charCodeAt(i);
            hash = ((hash << 5) - hash) + char;
            hash = hash & hash; // Convert to 32bit integer
        }
        return 'hashed_' + Math.abs(hash).toString(16);
    }

    // Verify password with hash
    verifyPassword(password, hash) {
        return this.hashPassword(password) === hash;
    }

    getAllUsers() {
        const stored = localStorage.getItem(this.usersKey);
        return stored ? JSON.parse(stored) : {};
    }

    userExists(username) {
        const users = this.getAllUsers();
        return users.hasOwnProperty(username);
    }

    getUser(username) {
        const users = this.getAllUsers();
        return users[username] || null;
    }

    // Get all registered usernames
    getAllUsernames() {
        const users = this.getAllUsers();
        return Object.keys(users);
    }

    // Get user email
    getUserEmail(username) {
        const user = this.getUser(username);
        return user ? user.email : null;
    }

    // Check if email exists
    emailExists(email) {
        const users = this.getAllUsers();
        for (let user in users) {
            if (users[user].email.toLowerCase() === email.toLowerCase()) {
                return true;
            }
        }
        return false;
    }

    // Delete user account (for future use)
    deleteUser(username) {
        const users = this.getAllUsers();
        if (users[username]) {
            delete users[username];
            localStorage.setItem(this.usersKey, JSON.stringify(users));
            return { success: true, message: 'Account deleted successfully' };
        }
        return { success: false, message: 'User not found' };
    }

    // Change password
    changePassword(username, oldPassword, newPassword) {
        const users = this.getAllUsers();

        if (!users[username]) {
            return { success: false, message: 'User not found' };
        }

        // Verify old password
        if (!this.verifyPassword(oldPassword, users[username].password)) {
            return { success: false, message: 'Current password is incorrect!' };
        }

        // Validate new password
        if (newPassword.length < 6) {
            return { success: false, message: 'New password must be at least 6 characters long.' };
        }

        // Update password
        users[username].password = this.hashPassword(newPassword);
        localStorage.setItem(this.usersKey, JSON.stringify(users));
        return { success: true, message: 'Password changed successfully!' };
    }
}

// Create global instance
const userDB = new UserDatabase();