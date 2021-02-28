console.log('*****************************************************************************');
console.log('reduce');
let array = [1,2,3,4,5,6];
console.log(array);

let reduce = array.reduce(function(acumulado, atual, index, array){
    return acumulado + atual;
},0);

console.log(reduce);


console.log('*****************************************************************************');
console.log('reduceRight');
let reduceRight = array.reduceRight(function(acumulador, atual, index, array){
    return acumulador + atual;
},0);
console.log(reduceRight);

console.log('*****************************************************************************');
console.log('indexOf');
console.log(array.indexOf(2));

console.log('*****************************************************************************');
console.log('lastIndexOf');
console.log(array.lastIndexOf(2));

console.log('*****************************************************************************');
console.log('isArray');
console.log(Array.isArray(array));
