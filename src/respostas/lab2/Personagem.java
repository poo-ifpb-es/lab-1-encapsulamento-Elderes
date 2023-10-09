public class Personagem {

    private int saude;
    private int experiencia;

    public Personagem(String nome) {
        this.saude = 100;
    }

    public void sofrerDano() {
        int dano = Math.max(10 - (this.experiencia / 100), 1);
        this.saude -= dano;
    }

    public void recuperarSaude() {
        this.saude = 100;
    }

    public void ganharExperiencia() {
        this.experiencia = Math.min(this.experiencia + 100, 1000);
    }

    public int getSaude() {
        return saude;
    }

    public int getExperiencia() {
        return experiencia;
    }
}
