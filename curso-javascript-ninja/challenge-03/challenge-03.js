let myvar = {}

let pessoa = {
    nome: 'Igor',
    sobrenome: 'Gabriel',
    sexo: 'masculino',
    idade: 23,
    altura: 1.80,
    peso: 85,
    andando: false,
    caminhandoQuantosMetros: 0
}

pessoa.fazerAniversario = function(){
    pessoa.idade++;
}

pessoa.andar = function(metrosCaminhados){
    pessoa.caminhandoQuantosMetros+= metrosCaminhados;
    pessoa.andando = true;
}

pessoa.parar = function(){
    pessoa.andando = false;
}

pessoa.nomeCompleto = function(){
    return 'Olá ! Meu nome é ' + pessoa.nome + ' ' + pessoa.sobrenome;
}

pessoa.mostrarIdade = function(){
    return 'Olá ! Eu tenho ' + pessoa.idade + ' anos!';
}

pessoa.mostrarAltura = function(){
    return 'Minha altura é ' + pessoa.altura;
}

