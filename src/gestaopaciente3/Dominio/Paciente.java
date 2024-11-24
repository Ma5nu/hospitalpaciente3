package gestaopaciente3.Dominio;

public class Paciente {
    private int id;
    private double altura;
    private double peso;

    // Construtor
    public Paciente(int id, double altura, double peso) {
        this.id = id;
        this.altura = altura;
        this.peso = peso;
    }

    // Getters
    public int getId() {
        return id;
    }

    public double getAltura() {
        return altura;
    }

    public double getPeso() {
        return peso;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    // Método para listar paciente
    public void listarPaciente() {
        System.out.println("ID: " + id + ", Altura: " + altura + ", Peso: " + peso);
    }
}
