export class GerarInteiroAleatorio {
  gerarInteiroAleatorio(minimo, maximo) {
    let min = Math.ceil(minimo);
    let max = Math.floor(maximo);
    return Math.floor(Math.random() * (max - min)) + min;
  }
}
