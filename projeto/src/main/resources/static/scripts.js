const formulario = document.querySelector("form");
const user_nome = document.querySelector("[name='nome']");
const user_username = document.querySelector("[name='username']");
const user_email = document.querySelector("[name='email']");
const user_senha = document.querySelector("[name='senha']");
const user_telefone = document.querySelector("[name='telefone']");

function cadastrar() {
    
    fetch("http://localhost:8082/usuarios",
    {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        method: 'POST',
        body: JSON.stringify({
            nome: user_nome.value,
            email: user_email.value,
            username: user_username.value,
            senha: user_senha.value,
            telefone: user_telefone.value
        })
    })
    .then(function(result) { console.log(result) })
    .catch(function(result) { console.log(result) })
};

function limpar(){
    user_nome.value = ''
    user_email.value = ''
    user_senha.value = ''
    user_username.value = ''
    user_telefone.value = ''
}

formulario.addEventListener('submit', function(event) {
    event.preventDefault();

    cadastrar();

    limpar();

});