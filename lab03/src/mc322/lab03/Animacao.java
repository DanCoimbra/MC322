public class Animacao {
    AquarioLombriga aqualomb;
    String actions;
    boolean primeira_exibicao;
    
    Animacao(String inputlist) {
        int size_aqua = Integer.parseInt(inputlist.substring(0,2));
        int size_lomb = Integer.parseInt(inputlist.substring(2,4));
        int pos_tail = Integer.parseInt(inputlist.substring(4,6));
        this.aqualomb = new AquarioLombriga(size_aqua, size_lomb, pos_tail);
        this.actions = inputlist.substring(6);
        this.primeira_exibicao = true;
    }
    
    public void apresenta() {
        this.aqualomb.apresenta();
    }
    
    public boolean passo() {
        if (primeira_exibicao) {
            this.apresenta();
            this.primeira_exibicao = false;
        }

        if (this.actions.length() > 0) {
            if (this.actions.charAt(0) == 'C')
                this.aqualomb.crescer();
            else if (this.actions.charAt(0) == 'M')
                this.aqualomb.mover();
            else
                this.aqualomb.virar();
            
            this.actions = this.actions.substring(1);
            return true;
        } else
            return false;
    }
}