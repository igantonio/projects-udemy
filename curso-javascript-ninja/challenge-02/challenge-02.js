function soma(x,y){
    return x+y;
}

let somaFuncao = soma(10,5) + 5;

somaFuncao;

let varSemValor;

function adicionaValor(){
    varSemValor = 5;
    return 'O valor da variável agora é ' + varSemValor;
}

adicionaValor();

function validarPreenchimento(x,y,z){
    let mensagem = 'Preencha todos os valores corretamente!';

    if(x === undefined || y === undefined || z === undefined){
        return mensagem;
    }

    return (x * y * z) + 2;
}

validarPreenchimento(10,2,2);

function validarERetornar(x,y,z){


    if(x !== undefined && y === undefined && z === undefined){
        return x;
    }
    else if(x !== undefined && y !== undefined && z === undefined){
        return x + y;
    }
    else if(x !== undefined && y !== undefined && z !== undefined){
        return (x + y) / z;
    }
    else if(x === undefined && y === undefined && z === undefined){
        return false;
    }
    else{
        return null;
    }
}