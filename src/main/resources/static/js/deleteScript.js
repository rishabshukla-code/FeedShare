function confirmDelete(postId) {
    var confirmDelete = confirm("Are you sure you want to delete this post?");
    if (confirmDelete) {
        window.location.href = "/deletePost.htm?id=" + postId;
    }
}
