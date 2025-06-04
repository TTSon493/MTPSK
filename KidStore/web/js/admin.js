const allSideMenu = document.querySelectorAll('#sidebar .side-menu.top li a');

allSideMenu.forEach(item => {
    const li = item.parentElement;

    item.addEventListener('click', function () {
        allSideMenu.forEach(i => {
            i.parentElement.classList.remove('active');
        })
        li.classList.add('active');
    })
});




// TOGGLE SIDEBAR
const menuBar = document.querySelector('#content nav .bx.bx-menu');
const sidebar = document.getElementById('sidebar');

menuBar.addEventListener('click', function () {
    sidebar.classList.toggle('hide');
})







const searchButton = document.querySelector('#content nav form .form-input button');
const searchButtonIcon = document.querySelector('#content nav form .form-input button .bx');
const searchForm = document.querySelector('#content nav form');

searchButton.addEventListener('click', function (e) {
    if (window.innerWidth < 576) {
        e.preventDefault();
        searchForm.classList.toggle('show');
        if (searchForm.classList.contains('show')) {
            searchButtonIcon.classList.replace('bx-search', 'bx-x');
        } else {
            searchButtonIcon.classList.replace('bx-x', 'bx-search');
        }
    }
})





if (window.innerWidth < 768) {
    sidebar.classList.add('hide');
} else if (window.innerWidth > 576) {
    searchButtonIcon.classList.replace('bx-x', 'bx-search');
    searchForm.classList.remove('show');
}


window.addEventListener('resize', function () {
    if (this.innerWidth > 576) {
        searchButtonIcon.classList.replace('bx-x', 'bx-search');
        searchForm.classList.remove('show');
    }
})



//const switchMode = document.getElementById('switch-mode');
//
//switchMode.addEventListener('change', function () {
//    if (this.checked) {
//        document.body.classList.add('dark');
//    } else {
//        document.body.classList.remove('dark');
//    }
//})

const switchMode = document.getElementById('switch-mode');
const darkModeKey = 'darkModeEnabled';

// Function to set dark mode
function setDarkMode() {
    document.body.classList.add('dark');
    localStorage.setItem(darkModeKey, 'enabled');
}

// Function to remove dark mode
function removeDarkMode() {
    document.body.classList.remove('dark');
    localStorage.removeItem(darkModeKey);
}

// Function to toggle dark mode
function toggleDarkMode() {
    if (switchMode.checked) {
        setDarkMode();
    } else {
        removeDarkMode();
    }
}

// Check dark mode state on page load
window.addEventListener('load', function () {
    const darkModeEnabled = localStorage.getItem(darkModeKey);
    if (darkModeEnabled === 'enabled') {
        switchMode.checked = true;
        setDarkMode();
    }
});

// Listen for mode change
switchMode.addEventListener('change', toggleDarkMode);


document.querySelectorAll("#toggleButton").forEach(function (button) {
    button.addEventListener("click", function () {
        if (this.textContent.trim() === "Banned") {
            this.classList.remove("red");
            this.style.background = "transparent";
            this.style.border = "2px solid #007bff";
            this.style.borderradius = "6px";
            this.style.color = "#007bff";
            this.style.padding = "10px 20px"; // added padding
            this.style.cursor = "pointer"; // added cursor style
            this.style.fontSize = "16px"; // added font size
            this.style.transition = "all 0.3s ease"; // added transition

            this.onmouseover = function () {
                this.style.backgroundColor = "#007bff";
                this.style.color = "#fff";
            };
            // Remove hover effect
            this.onmouseout = function () {
                this.style.backgroundColor = "transparent";
                this.style.color = "#007bff";
            };
        } else {
            this.classList.add("red");
            this.style.background = "transparent";
            this.style.border = "2px solid #FF0000";
            this.style.borderradius = "6px";
            this.style.color = "#FF0000";
            this.style.padding = "10px 20px"; // added padding
            this.style.cursor = "pointer"; // added cursor style
            this.style.fontSize = "16px"; // added font size
            this.style.transition = "all 0.3s ease"; // added transition

            this.onmouseover = function () {
                this.style.backgroundColor = "#FF0000";
                this.style.color = "#fff";
            };
            // Remove hover effect
            this.onmouseout = function () {
                this.style.backgroundColor = "transparent";
                this.style.color = "#FF0000";
            };
        }
    });
});

/* logout script */
// Get the modal and buttons
var modal = document.getElementById("logoutConfirmationDialog");
var logoutButton = document.getElementById("logoutButton");
var confirmLogoutButton = document.getElementById("confirmLogout");
var cancelButton = document.getElementById("cancelButton");

// Function to display the modal
function showModal() {
    modal.style.display = "block";
}

// Function to close the modal
function closeModal() {
    modal.style.display = "none";
}

// Event listeners
logoutButton.addEventListener("click", function () {
    window.location.href = "LogoutController";
});

// Event listener for Cancel button
cancelButton.addEventListener("click", function () {
    window.location.href = "http://localhost:8080/adminpage/admin_account.jsp"; // Redirect to specified URL
});

// Event listener to close modal when clicking outside the modal content
window.addEventListener("click", function (event) {
    if (event.target === modal) {
        showModal();
    }
});

// Logout functionality
confirmLogoutButton.addEventListener("click", function () {
    // Add your logout logic here, e.g., redirecting to logout page
    // window.location.href = "logout.php";
    alert("Logged out successfully!");
    closeModal();
});