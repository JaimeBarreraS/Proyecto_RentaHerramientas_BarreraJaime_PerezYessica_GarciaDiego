const passInput = document.getElementById("pass");
const ojo = document.getElementById("ojo");

ojo.addEventListener("click", function() {
    const isPassword = passInput.type === "password";
    passInput.type = isPassword ? "text" : "password";

});