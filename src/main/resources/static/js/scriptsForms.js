$(function () {

    $("#btn-cadastro").click(function (e) {
        e.preventDefault();
        salvaDadosCadastro();
    });
    $("#btn-login").click(function (e) {
        e.preventDefault();
        window.location.href = "index.html";
    });

    setMasks();
    toggleForms();
    formsValidations();
})

function setMasks() {
    $('#cpf').mask('000.000.000-00', {reverse: true});
    $('#dataNascimento').mask('00/00/0000');
    $('#telefone').mask('(00) 00000-0000');
    $('#celular').mask('(00) 00000-0000');
    $('#celular').mask('(00) 00000-0000');
    $('#numero').mask('ZZZZ0', {
        translation: {
            'Z': {
                pattern: /[0-9]/, optional: true
            }
        }
    });
    $('#cep').mask('00000-000');
}

function toggleForms() {
    $('#pills-tab > li > a').click(function () {
        let abaAtiva = $(this).hasClass('active');
        if (!abaAtiva) {
            setTimeout(function () {
                $('.botao-entrar').toggleClass('d-none d-block');
            }, 150);
        }
    });
}

function formsValidations() {
    $("#form-login").validate({
        rules: {
            emailLogin: {required: true},
            senhaLogin: {required: true}
        },
        messages: {
            emailLogin: {
                required: 'O campo e-mail é de preenchimento obrigatório.',
                email: 'Informe um e-mail válido.'
            },
            senhaLogin: {required: 'O campo senha é de preenchimento obrigatório.'}
        },
        errorClass: 'error-message col-lg-12',
        errorElement: 'label'
    })

    $("#form-cadastro").validate({
        rules: {
            apelido: {required: true, minlength: 2},
            nomeCompleto: {required: true, minlength: 6},
            cpf: {required: true, cpf: true},
            dataNascimento: {required: true},
            telefone: {required: true},
            emailCadastro: {required: true, email: true},
            senhaCadastro: {required: true},
            cSenha: {required: true, equalTo: '#senhaCadastro'},
            rua: {required: true},
            numero: {required: true},
            bairro: {required: true},
            cidade: {required: true},
            cep: {required: true}
        },
        messages: {
            apelido: {
                required: 'O apelido é de preenchimento obrigatório.',
                minlength: 'Digite no mínimo dois caracteres.'
            },
            nomeCompleto: {
                required: 'O nome completo é de preenchimento obrigatório.',
                minlength: 'Digite no mínimo seis caracteres.'
            },
            cpf: {required: 'O CPF é de preenchimento obrigatório.', cpf: 'Informe um CPF válido.'},
            dataNascimento: {required: 'A data de nascimento é de preenchimento obrigatório'},
            telefone: {required: 'O telefone é de preenchimento obrigatório'},
            emailCadastro: {required: 'O e-mail é de preenchimento obrigatório.', email: 'Informe um e-mail válido.'},
            senhaCadastro: {required: 'A senha é de preenchimento obrigatório.'},
            cSenha: {required: 'É necessário confirmar a senha.', equalTo: 'As senhas não coincidem.'},
            rua: {required: 'Qual a sua rua?'},
            numero: {required: 'Qual o número de onde você mora?'},
            bairro: {required: 'Qual seu bairro?'},
            cidade: {required: 'Qual sua cidade?'},
            cep: {required: 'Qual seu CEP?'},
        },
        errorClass: 'error-message col-lg-12',
        errorElement: 'label'
    })

}

function salvaDadosCadastro() {
    if (checkCamposPreenchidos() === true) {
        let apelido = $("#apelido").val();
        let nomeCompleto = $("#nomeCompleto").val();
        let cpf = $("#cpf").val();
        let dataNascimento = $("#dataNascimento").val();
        let genero = $("#combo-genero option:selected").val();
        let telefone = $("#telefone").val();
        let celular = $("#celular").val();
        let emailCadastro = $("#emailCadastro").val();
        let senhaCadastro = $("#senhaCadastro").val();
        let rua = $("#rua").val();
        let numero = $("#numero").val();
        let bairro = $("#bairro").val();
        let cidade = $("#cidade").val();
        let estado = $("#combo-estados option:selected").val();
        let cep = $("#cep").val();
        let complemento = $("#complemento").val();

        var dadosCadastro = {
            apelido: apelido,
            nomeCompleto: nomeCompleto,
            cpf: cpf,
            dataNascimento: dataNascimento,
            genero: genero,
            telefone: telefone,
            celular: celular,
            emailCadastro: emailCadastro,
            senhaCadastro: senhaCadastro,
            rua: rua,
            numero: numero,
            bairro: bairro,
            cidade: cidade,
            estado: estado,
            cep: cep,
            complemento: complemento
        };
        localStorage.setItem("dadosCadastro", JSON.stringify(dadosCadastro));
        alert("Dados salvos com sucesso!");
        window.location.href = "index.html";
    }
};


function checkCamposPreenchidos() {
    let apelido = $("#apelido").val();
    let nomeCompleto = $("#nomeCompleto").val();
    let cpf = $("#cpf").val();
    let dataNascimento = $("#dataNascimento").val();
    let genero = $("#combo-genero option:selected").val();
    let telefone = $("#telefone").val();
    let celular = $("#celular").val();
    let emailCadastro = $("#emailCadastro").val();
    let senhaCadastro = $("#senhaCadastro").val();
    let rua = $("#rua").val();
    let numero = $("#numero").val();
    let bairro = $("#bairro").val();
    let cidade = $("#cidade").val();
    let estado = $("#combo-estados option:selected").val();
    let cep = $("#cep").val();
    let complemento = $("#complemento").val();

    if (apelido === "" || nomeCompleto === "" || cpf === "" || dataNascimento === ""
        || genero === "" || telefone === "" || emailCadastro === ""
        || senhaCadastro === "" || rua === "" || numero === "" || bairro === ""
        || cidade === "" || estado === "" || cep === "" || complemento === "") {
        alert("Preencha todos os campos marcados com *");
        return false;
    } else {
        return true;
    }
}

