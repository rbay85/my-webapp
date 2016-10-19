var userNameInput;
var userSecretInput;
var span;
$( function(){

    userNameInput = $("#userName");
    userSecretInput = $("#userSecret");
    span = $("#txtmsg");
});

function f(){

    span.text(userNameInput.val() + ": " + userSecretInput.val());
}