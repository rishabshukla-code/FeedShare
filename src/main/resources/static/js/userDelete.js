function confirmUserDelete(usn) {
    var confirmDelete = confirm("Are you sure you want to delete this Account?");
    if (confirmDelete) {
        window.location.href = "/deleteUser.htm?usn="+ usn;
    }
}
