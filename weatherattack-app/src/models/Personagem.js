export default class Personagem {
  constructor(nome, poderes) {
    this.nome = nome;
    this.coins = 0;
    this.battles = 0;
    this.wins = 0;
    this.loses = 0;
    this.equipamentos = [];
    this.poderes = poderes;
  }
}
