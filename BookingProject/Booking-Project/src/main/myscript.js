function mechanicList() {
    fetch("http://localhost:8080/Mechanic/list")
        .then((response) => {
            if (!response.ok) {
                throw new Error("Network response was not ok: " + response.statusText);
            }
            return response.json();
        })
        .then((mechanics) => {
            const dataTable = document.getElementById("Shopdetails");
            dataTable.innerHTML = ""; // Clear existing rows

            mechanics.forEach(mechanic => {
                const row = `
                    <tr>
                        <td>${mechanic.name}</td>
                        <td>${mechanic.emailId}</td>
                        <td>${mechanic.mobileno}</td>
                        <td>${mechanic.vehicleName}</td>
                        <td>${mechanic.address}</td>
                        <td>${mechanic.city}</td>
                        <td>${mechanic.opentime}</td>
                        <td>${mechanic.closetime}</td>
                    </tr>
                `;
                dataTable.innerHTML += row;
            });
        })
        .catch(error => console.error("Error fetching mechanics:", error));
}

function confirmmechanicList() {
    const email = localStorage.getItem("userEmail");
    if (!email) {
        alert("You must be logged in to view your bookings!");
        return;
    }

    fetch(`http://localhost:8080/ConfirmBooking/list/${email}`)
        .then(response => {
            if (!response.ok) {
                throw new Error("Network response was not ok: " + response.statusText);
            }
            return response.json();
        })
        .then(bookings => {
            const dataTable = document.getElementById("confirmedbookingdetails");
            const loadingRow = document.getElementById("loading");
            if (loadingRow) loadingRow.remove();
            dataTable.innerHTML = "";

            if (bookings.length === 0) {
                dataTable.innerHTML = `<tr><td colspan="12">No confirmed bookings found for ${email}</td></tr>`;
                return;
            }

            bookings.forEach(booking => {
                const row = `
                    <tr>
                        <td>${booking.name}</td>
                        <td>${booking.emailId}</td>
                        <td>${booking.mobileno}</td>
                        <td>${booking.address}</td>
                        <td>${booking.city}</td>
                        <td>${booking.vehicleName}</td>
                        <td>${booking.servicedetails}</td>
                        <td>${booking.servicedate}</td>
                        <td>${booking.servicetime}</td>
                        <td>${booking.shopname}</td>
                        <td>${booking.mechanicname}</td>
                        <td>${booking.mechanicmobileno}</td>
                    </tr>`;
                dataTable.innerHTML += row;
            });
        })
        .catch(error => {
            console.error("Error fetching confirmed bookings:", error);
        });
}


function bookinglist() {
    fetch("http://localhost:8080/Booking/list")
        .then(response => {
            if (!response.ok) {
                throw new Error("Network response was not ok: " + response.statusText);
            }
            return response.json();
        })
        .then((Booking) => {
            const dataTable = document.getElementById("bookingdetails");
            dataTable.innerHTML = "";

            const today = new Date();
            today.setHours(0, 0, 0, 0);

            let hasValidBookings = false;

            Booking.forEach(book => {
                const serviceDate = new Date(book.servicedate);

                // ✅ Show only "Booked" status + no mechanic assigned + future date
                if ((!book.mechanicname || book.mechanicname.trim() === "") &&
                    book.status === "Booked" &&
                    serviceDate >= today) 
                {
                    hasValidBookings = true;

                    const row = `
                        <tr>
                            <td>${book.id}</td>
                            <td>${book.name}</td>
                            <td>${book.emailId}</td>
                            <td>${book.mobileno}</td>
                            <td>${book.address}</td>
                            <td>${book.city}</td>
                            <td>${book.vehicleName}</td>
                            <td>${book.vehiclemodel}</td>
                            <td>${book.servicedetails}</td>
                            <td>${book.servicedate}</td>
                            <td>${book.servicetime}</td>
                            <td>
                                <button 
                                    onclick="selectCustomer('${encodeURIComponent(JSON.stringify(book))}')"
                                    style="background:#16a085; color:#fff; padding:6px 12px; border:none; border-radius:6px; cursor:pointer;">
                                    Select
                                </button>
                            </td>
                        </tr>`;
                    dataTable.innerHTML += row;
                }
            });

            if (!hasValidBookings) {
                dataTable.innerHTML = `
                    <tr>
                        <td colspan="12" style="color:red; text-align:center;">
                            ✅ All bookings are assigned or completed.
                        </td>
                    </tr>`;
            }
        })
        .catch(error => console.error("Error fetching bookings:", error));
}


// When user clicks “Select”
function selectCustomer(customerData) {
    // Save to localStorage
    localStorage.setItem("selectedCustomer", customerData);

    // Redirect to confirm booking page
    window.location.href = "confirmbooking.html";
}


function userslist() {
    fetch("http://localhost:8080/customer/list")
        .then((response) => {
            if (!response.ok) {
                throw new Error("Network response was not ok: " + response.statusText);
            }
            return response.json();
        })
        .then((customer) => {
            const dataTable = document.getElementById("usersdetails");
            dataTable.innerHTML = ""; // Clear existing rows

            customer.forEach(cust => {
                const row = `
                    <tr>
                        <td>${cust.name}</td>
                        <td>${cust.emailId}</td>
                    </tr>
                `;
                dataTable.innerHTML += row;
            });
        })
        .catch(error => console.error("Error fetching customer:", error));
}

function shopslist() {
    fetch("http://localhost:8080/shop/list")
        .then((response) => {
            if (!response.ok) {
                throw new Error("Network response was not ok: " + response.statusText);
            }
            return response.json();
        })
        .then((shop) => {
            const dataTable = document.getElementById("shopdetails");
            dataTable.innerHTML = ""; // Clear existing rows

            shop.forEach(shops => {
                const row = `
                    <tr>
                        <td>${shops.name}</td>
                        <td>${shops.emailId}</td>
                    </tr>
                `;
                dataTable.innerHTML += row;
            });
        })
        .catch(error => console.error("Error fetching customer:", error));
}