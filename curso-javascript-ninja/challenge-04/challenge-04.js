let isTruthy = function(argumento){
    return argumento ? true : false;
}

isTruthy(1);

let carro = {
    marca: 'Fiat',
    modelo: 'Fiesta',
    placa: 'DXJ-2578',
    ano: 2000,
    cor: 'Branco',
    quantasPortas: 2,
    assentos: 5,
    quantidadePessoas: 0
}

carro.mudaCor = function(cor){
    carro.cor = cor;
}

carro.obterCor = function(){
    return carro.cor;
}

carro.obterModelo = function(){
    return carro.modelo;
}

carro.obterMarca = function(){
    return carro.marca;
}

carro.obterMarcaModelo = function(){
    return 'Esse carro é um ' + carro.marca  + ' ' + carro.modelo;
}

carro.adicionaPessoas = function(qtdPessoas){
    if(carro.quantidadePessoas === carro.assentos){
        return 'O carro já está lotado!';
    }else if((carro.quantidadePessoas + qtdPessoas) > carro.assentos){
        if((carro.quantidadePessoas - qtdPessoas) === 1){
            return 'Só cabem mais ' + (carro.assentos - carro.quantidadePessoas) + ' pessoa!';
        }
        return 'Só cabem mais ' + (carro.assentos - carro.quantidadePessoas) + ' pessoas!';
    }else{
        carro.quantidadePessoas+= qtdPessoas;
        return 'Já temos ' + carro.quantidadePessoas +  ' pessoas no carro!'
    }
}