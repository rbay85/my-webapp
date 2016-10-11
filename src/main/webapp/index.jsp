<head>
    <script
            src="http://code.jquery.com/jquery-3.1.1.min.js">
    </script>
</head>
<body>
    <span id="txtmsg"> no message still </span>
    <br/>
    <input type="text" id="userName"/>
    <br/>
    <input type="password" id="userSecret"/>
    <br/>
    <input type="button" value="go!" onclick="f();"/>
    <script>

        var userNameInput;
        var span;
        $(function(){

            userNameInput = $("#userName");
            userSecretInput = $("#userSecret");
            span = $("#txtmsg");
        });

        function f(){

            span.text(userNameInput.val() + ": " + userSecretInput.val());
        }
    </script>
</body>

