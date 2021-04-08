public class AppLab03 {
    public static void main(String[] args) {
        Animacao anim = new Animacao("080403MCMVM");
        while (anim.passo())
            anim.apresenta();
    }
}