$('.pgNum-button').click(function() {
    // Store's a reference to the clicked button
    var clickedButton = $(this);
    
    // Send AJAX request
    $.get('/AjaxProfile.htm', { pageNumber: clickedButton.val() })
    .done(function(response) {
        // Update the content of the post div with the new response
        $('.post').html(response);
    })
    .fail(function(xhr, status, error) {
        // This will handle failed AJAX request
        console.error('Error:', error);
    });
});
