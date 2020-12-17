var telaAtual = true;
var buttons = 1;

const insertTitleModal = () => {
    titleModal.innerHTML = telaAtual ? `Login` : `Cadastro`;
}
const insertbuttonsModal = () => {
    buttonsModal.innerHTML = telaAtual ? `<button type="button" class="btn btn-primary" onclick="login()">Entrar</button>`
        :
        `<button type="button" class="btn btn-primary" onclick="insertbodyModal()">Voltar</button>
    <button type="button" class="btn btn-primary" onclick="register()">Proximo</button>`;
}
const insertbodyModalDois = () => {
    bodyModal.innerHTML = `
    <div class="form-group">
        <label for="exampleInputEmail1">Nome da empresa</label>
        <input type="name" class="form-control" id="txtNomeEmpresa" placeholder="ex: Dagda Solutions">
    </div>
    <div class="form-group">
        <label for="exampleInputEmail1">CNPJ</label>
        <input type="text" class="form-control" id="exampleInputCnpj" placeholder="(99.999.999/9999-99)">
    </div>
    <div class="form-group">
        <label for="exampleInputPassword1">Telefone</label>
        <input type="text" class="form-control" id="exampleInputTelefone" placeholder="(__)_____-____">
    </div>`

    buttonsModal.innerHTML = `<button type="button" class="btn btn-primary" onclick="insertbodyModal()">Voltar</button>
    <button type="button" class="btn btn-primary" onclick="registerII()">Registrar</button>`;
}

const insertbodyModal = () => {
    bodyModal.innerHTML = telaAtual ?
        `<div class="form-group">
            <label for="exampleInputEmail1">Email</label>
            <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email">
        </div>
        <div class="form-group">
            <label for="exampleInputPassword1">Password</label>
            <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
        </div>
        <div class="d-flex justify-content-center">
            <span>Cadastre-se <a href="#" style="color:#fed136;" onclick="telaModal(false)">Aqui</a></span>
        </div>`
        :
        `<div class="form-group">
            <label for="exampleInputEmail1">Nome completo</label>
            <input type="name" class="form-control" id="txtName" placeholder="ex: João souza">
        </div>
        <div class="form-group">
            <label for="exampleInputEmail1">Email</label>
            <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="seuemail@dominino.com">
        </div>
        <div class="form-group">
            <label for="exampleInputPassword1">Senha</label>
            <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
        </div>`;
}

var arrLogin;
const login = () => {
    arrLogin = {
        email: exampleInputEmail1.value,
        senha: exampleInputPassword1.value,
    }
    console.log(arrLogin);
    validacaoLogin();
    return arrLogin;
}

let arrRegister = [];
let arrRegisterII;

const register = () => {
    buttons = 2
    arrRegister.push(txtName.value);
    arrRegister.push(exampleInputEmail1.value);
    arrRegister.push(exampleInputPassword1.value);
    insertbodyModalDois();

}
const registerII = () => {
    arrRegister.push(txtNomeEmpresa.value);
    arrRegister.push(exampleInputCnpj.value);
    arrRegister.push(exampleInputTelefone.value);

    if (arrRegister.length == 6) {
        arrRegisterII = {
            nome: arrRegister[0],
            email: arrRegister[1],
            senha: arrRegister[2],
            empresa: arrRegister[3],
            cnpj: arrRegister[4],
            telefone: arrRegister[5],
        };
    };
    console.log(arrRegister.length)
    console.log(arrRegister)
    validaçãoRegistro();
}

const acionaFunction = () => {
    insertTitleModal();
    insertbodyModal();
    insertbuttonsModal();
}

const telaModal = (condicao) => {
    telaAtual = condicao;
    acionaFunction();
}



acionaFunction();

//validação do cadastro 
function validaçãoRegistro() {
    groupNotification.innerHTML = '';
    const erros = [];

    const { nome, email, telefone, empresa, senha, cnpj } = arrRegisterII;


    const notification = (alerta) => {
        groupNotification.innerHTML += `<div class="notification"><label>${alerta}</label></div>`
    }
    if (nome.length == 0) {
        notification("Campo 'Nome' está vazio.")
        erros.push('1');
    }
    if (email.indexOf("@") == -1 || email.indexOf(".") == -1) {
        notification("O formato de email é: usuario@extensao.com");
        erros.push('1');
    }
    if (telefone.length !== 11) {
        notification("O telefone deve conter 11 caracteres ");
        erros.push('1');
    }
    if (empresa.length == 0) {
        notification("Campo 'Empresa' está vazio.")
        erros.push('1');
    }
    if (senha.length < 8) {
        notification("Senha deve conter no minimo 8 caracteres")
        erros.push('1');
    }
    if (cnpj.length !== 14) {
        notification("Seu CNPJ deve conter 14 caracteres exp: 99.999.999/9999-99")
        erros.push('1');
    }
    setTimeout(() => {
        groupNotification.innerHTML = '';
    }, 5000)

}

function validacaoLogin() {


    if (arrLogin.senha.length < 8) {

        alert("A senha deve conter no minino 8 caracteres")

    } else if (arrLogin.email.indexOf("@") == -1) {

        alert("O formato de email é: usuario@extensao.com");

    } else if (arrLogin.email.indexOf(".") == -1) {

        alert("O formato de email é: usuario@extensao.com");

    } else {

        window.location.href = "escolha.html";
        return false;

    };


}





